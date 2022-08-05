package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class AssemblyProductionDetainBean {
    public AssemblyProductionDetainBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Product_Cd = (String) pvIter.getCurrentRow().getAttribute("Assembly_prd_dtl_prdcd");
        if(Product_Cd.equalsIgnoreCase("ALL"))
        {
            Product_Cd = "%";
        }
        else{
        Product_Cd = Product_Cd.split(",")[0];
        }
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callAssemblyProductionDetail");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_Product_cd", Product_Cd);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);
        Object result = operationBinding.execute();
    }
}
