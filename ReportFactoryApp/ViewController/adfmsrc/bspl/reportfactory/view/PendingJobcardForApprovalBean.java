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

public class PendingJobcardForApprovalBean {
    public PendingJobcardForApprovalBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        
        String  Unit = (String) pvIter.getCurrentRow().getAttribute("SubLedger_UnitCode");
        if(Unit.equalsIgnoreCase("ALL"))
        {
            Unit = "All";
        }
        else{
                Unit = Unit.split(",")[0];
            }
       String JCStatus = (String) pvIter.getCurrentRow().getAttribute("PendingJobcardForApproval_Approved");
        
        System.out.println("Parameters -- UnitCode-" + Unit +  "PendingJobcardForApproval_Approved" + JCStatus );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callPendingJobcardforApproval");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_JCSTATUS",JCStatus);

     
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
    
    
    
    

