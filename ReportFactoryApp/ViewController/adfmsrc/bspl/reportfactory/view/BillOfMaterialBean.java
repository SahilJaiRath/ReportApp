package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class BillOfMaterialBean {
    public BillOfMaterialBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 

    public void onClickButton(ActionEvent actionEvent) {
        
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String BOM_product_cd = (String) pvIter.getCurrentRow().getAttribute("BOM_Product_CD");
        if(BOM_product_cd.equalsIgnoreCase("ALL"))
        {
            BOM_product_cd = "%";
        }
        else
        {
                BOM_product_cd =BOM_product_cd.split(",")[0];
        }
            
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callBillOfMaterial");
        operationBinding.getParamsMap().put("LV_BOM_PRODUCT",BOM_product_cd);
        

        Object result = operationBinding.execute();
        
        }

}
