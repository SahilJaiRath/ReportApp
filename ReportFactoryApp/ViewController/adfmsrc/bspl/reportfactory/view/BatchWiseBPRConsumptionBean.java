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

public class BatchWiseBPRConsumptionBean {
    public BatchWiseBPRConsumptionBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String ProductName = (String) pvIter.getCurrentRow().getAttribute("BatchWiseBPRConsumption_ProductName");
         ProductName = ProductName.split(",")[0];
        
         String BatchNo = (String) pvIter.getCurrentRow().getAttribute("BatchWiseBPRConsumption_BatchNo");
          BatchNo = BatchNo.split(",")[0]; 


                   
        System.out.println("Parameters -- UnitCode-" + Unit   + "ProductName" +  ProductName+ "BatchNo" + BatchNo  );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callBatchWiseBPRConsumption");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ProductName", ProductName );
        operationBinding.getParamsMap().put("LV_BatchNo", BatchNo );
        
        Object result = operationBinding.execute();

        
    }
}
    
    
    
    

