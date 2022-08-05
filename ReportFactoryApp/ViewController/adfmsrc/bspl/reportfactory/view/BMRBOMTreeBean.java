package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class BMRBOMTreeBean {
    public BMRBOMTreeBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 
    
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String BMR_BT_BOMITEM = (String) pvIter.getCurrentRow().getAttribute("BMR_BT_BOMITEM");
//        if(BMR_BT_BOMITEM.equalsIgnoreCase("ALL"))
//        {
//            BMR_BT_BOMITEM = "%";
//        }
//        else
//        {
//            BMR_BT_BOMITEM = BMR_BT_BOMITEM.split(",")[0];
//        }
        BMR_BT_BOMITEM = BMR_BT_BOMITEM.split(",")[0];

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callBMRBOMTree");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_BMR_BT_BOMITEM", BMR_BT_BOMITEM);
      

        Object result = operationBinding.execute();
        
        }

}
