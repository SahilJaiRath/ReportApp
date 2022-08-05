package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

//import oracle.adf.mbean.share.config.adfc.String;
import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class BOMTreeBean {
    public BOMTreeBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String BOM_TREE_PROD = (String) pvIter.getCurrentRow().getAttribute("BOMTree_BOM_Prd_cd");
        BOM_TREE_PROD = BOM_TREE_PROD.split(" , ")[0];
        
        String RequiredBatch = (String) pvIter.getCurrentRow().getAttribute("RequiredBatch");
        
            
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callBOMTree");
        operationBinding.getParamsMap().put("LV_BOMTREE_PRD_CD",BOM_TREE_PROD);
        operationBinding.getParamsMap().put("LV_REQUESTQTY", RequiredBatch);
        

        Object result = operationBinding.execute();
        
    }
}
