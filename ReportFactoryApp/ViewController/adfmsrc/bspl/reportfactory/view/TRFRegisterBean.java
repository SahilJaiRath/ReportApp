package bspl.reportfactory.view;

import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class TRFRegisterBean {
    public TRFRegisterBean() {
    }
        
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("SubLedger_UnitCode");
            Unit = Unit.split(",")[0];
        String ProductFilter = (String) pvIter.getCurrentRow().getAttribute("TRFRegister_ProductFilter");
            ProductFilter = ProductFilter.split(",")[0];
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callTRFRegister");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ProductFilter",ProductFilter);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);
        Object result = operationBinding.execute();
        
        }
        
    
}
