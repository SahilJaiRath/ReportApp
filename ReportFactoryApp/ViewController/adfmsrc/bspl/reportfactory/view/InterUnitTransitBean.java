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

public class InterUnitTransitBean {
    public InterUnitTransitBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
         DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String INV_Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Bill_Unit = (String) pvIter.getCurrentRow().getAttribute("InterUnitTransit_UnitCode");

        
            Bill_Unit = Bill_Unit.split(",")[0];
            
            
            
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callInterUnitTransitReport");
        operationBinding.getParamsMap().put("LV_INVUNIT",INV_Unit);
        operationBinding.getParamsMap().put("LV_BILLUNIT",Bill_Unit);


        Object result = operationBinding.execute();
        
        }

}
