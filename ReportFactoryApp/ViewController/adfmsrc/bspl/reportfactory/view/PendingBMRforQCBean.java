package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class PendingBMRforQCBean {
    public PendingBMRforQCBean() {
    }
            public BindingContainer getBindings() {
                return BindingContext.getCurrent().getCurrentBindingsEntry();
                
            }
    
            private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String BatchFilter = (String) pvIter.getCurrentRow().getAttribute("PendingBMRForQC_BatchFilter");
        


        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callPendingBMRforQC");
        operationBinding.getParamsMap().put("LV_UNIT",Unit); 
        operationBinding.getParamsMap().put("LV_BatchFilter",BatchFilter);
        

        Object result = operationBinding.execute();
        
        }

        }

    
