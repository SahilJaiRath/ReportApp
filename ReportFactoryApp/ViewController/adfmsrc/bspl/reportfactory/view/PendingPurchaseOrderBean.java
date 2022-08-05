package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class PendingPurchaseOrderBean {
    public PendingPurchaseOrderBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 
    
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String GroupCd = (String) pvIter.getCurrentRow().getAttribute("GroupCd");
             if(GroupCd.equalsIgnoreCase("ALL"))
             {
                 GroupCd = "%";
             }
             else{
                     GroupCd = GroupCd.split(",")[0];
                 }
        
        String ValidTill = (String) pvIter.getCurrentRow().getAttribute("Pending_Purchase_Order_ValidTill");
            ValidTill = ValidTill.split(",")[0];
        
        String AppStatus = (String) pvIter.getCurrentRow().getAttribute("Pending_Purchase_Order_AppStatus");

        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callPendingPurchaseOrder");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);
        operationBinding.getParamsMap().put("LV_APPSTATUS", AppStatus);
        operationBinding.getParamsMap().put("LV_VALIDTILL", ValidTill);
        operationBinding.getParamsMap().put("LV_ITEMGROUP", GroupCd);

        Object result = operationBinding.execute();
        
        }

}
