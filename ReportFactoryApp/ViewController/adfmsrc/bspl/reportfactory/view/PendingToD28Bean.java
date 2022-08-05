package bspl.reportfactory.view;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class PendingToD28Bean {
    public PendingToD28Bean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String ChallanNo = (String) pvIter.getCurrentRow().getAttribute("Challan_No");
        String ItemCode = (String) pvIter.getCurrentRow().getAttribute("PendingToD28_ItemCode");
     
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callPendingToD28");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ChallanNo", ChallanNo);
        operationBinding.getParamsMap().put("LV_ItemCode",ItemCode);


        Object result = operationBinding.execute();
        
        }

}
