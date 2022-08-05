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

public class ItemWiseTotalInventoryBean {
    public ItemWiseTotalInventoryBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("SubLedger_UnitCode");
        if(Unit.equalsIgnoreCase("ALL"))
        {
            Unit = "%";
        }
        else{
                Unit = Unit.split(",")[0];
            }
        
        
        String ProductCode = (String) pvIter.getCurrentRow().getAttribute("ItemWiseTotalInventory_ProductCd");
        if(ProductCode.equalsIgnoreCase("ALL"))
        {
            ProductCode = "%";
        }
        else{
                ProductCode = ProductCode.split(",")[0];
            }
        
        
        System.out.println("Parameters -- SubLedger_UnitCode-" + Unit + "ItemWiseTotalInventory_ProductCd-" + ProductCode  );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callItemWiseTotalInventory");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_PRODUCTCODE", ProductCode);
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
    
    
    
    

