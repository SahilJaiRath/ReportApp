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

public class FGYieldProductionBean {
    public FGYieldProductionBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Prod_cd = (String) pvIter.getCurrentRow().getAttribute("ItemWiseSale_ProductCode");
        if(Prod_cd.equalsIgnoreCase("ALL"))
        {
            Prod_cd = "%";
        }
        else{
                Prod_cd = Prod_cd.split(",")[0];
            }
       
        System.out.println("Parameters -- UnitCode-" + Unit +  "ItemWiseSale_ProductCode"  + Prod_cd);
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callFGYieldProduction");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_PRODUCTCD", Prod_cd);
        
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
    
    
    
    

