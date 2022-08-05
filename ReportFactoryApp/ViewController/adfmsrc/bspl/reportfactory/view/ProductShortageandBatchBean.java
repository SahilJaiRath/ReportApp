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

public class ProductShortageandBatchBean {
    public ProductShortageandBatchBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String ProdCode = (String) pvIter.getCurrentRow().getAttribute("ProductShortageandBatch_ProductName");
        ProdCode = ProdCode.split(",")[0];
        
        String ReqQty = (String)pvIter.getCurrentRow().getAttribute("LV_p1");
       


                    Random rand = new Random();

        int SID = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+SID);
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("SID", SID);
        
        System.out.println("Parameters -- UnitCode-" + Unit + "LV_LocWip-" + ProdCode + "ProductShortageandBatch_ProductName-" );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callProductShortageandBatch");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_SID", SID);
        operationBinding.getParamsMap().put("LV_PRODUCT", ProdCode);
        operationBinding.getParamsMap().put("LV_REQUESTQTY", ReqQty);

       
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
    
    
    
    

