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

public class BatchwiseBMRConsumptionBean {
    public BatchwiseBMRConsumptionBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Part_Name = (String) pvIter.getCurrentRow().getAttribute("BatchwiseBMRConsumption_partCode");
        
       
                Part_Name = Part_Name.split(",")[0];
        
        
        String Batch_No = (String) pvIter.getCurrentRow().getAttribute("BatchwiseBMRConsumption_BatchNo");
        
        
                Batch_No = Batch_No.split(",")[0];
            
        
       
        
        
            
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate"); 


                   
        System.out.println("Parameters -- UnitCode-" + Unit  +  "from_Date" +fromDate + "To_Date" + toDate + "BatchwiseBMRConsumption_partCode" + 
                            Part_Name+ "BatchwiseBMRConsumption_BatchNo" + Batch_No  );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callBatchwiseBMRConsumption");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_PARTNAME", Part_Name );
        operationBinding.getParamsMap().put("LV_BATCHNO", Batch_No );
        operationBinding.getParamsMap().put("FRDATE",fromDate );
        operationBinding.getParamsMap().put("TODATE", toDate);
        Object result = operationBinding.execute();

        
    }
}
    
    
    
    

