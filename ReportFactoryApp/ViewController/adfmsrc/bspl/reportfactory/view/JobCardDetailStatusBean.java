package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class JobCardDetailStatusBean {
    public JobCardDetailStatusBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String UnitCode = (String) pvIter.getCurrentRow().getAttribute("SubLedger_UnitCode");
            UnitCode = UnitCode.split(",")[0];
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callJobCardDetailStatus");
        operationBinding.getParamsMap().put("LV_UnitCode",UnitCode);
      

        Object result = operationBinding.execute();
        
        }

        
}
