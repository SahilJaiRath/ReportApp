package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class NonMovingItemBean {
    public NonMovingItemBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String GroupCD = (String) pvIter.getCurrentRow().getAttribute("GroupCd");
        if(GroupCD.equalsIgnoreCase("ALL"))
        {
            GroupCD = "%";
        }
        else
        {
            GroupCD = GroupCD.split(",")[0];
        }
        String SubGroupCd = (String)pvIter.getCurrentRow().getAttribute("SubGroupCd");
        if(SubGroupCd.equalsIgnoreCase("ALL"))
        {
            SubGroupCd = "%";
        }
        else
        {
            SubGroupCd = SubGroupCd.split(",")[0];
        }
        String New_ProductCD = (String)pvIter.getCurrentRow().getAttribute("Non_Moving_Item_prd");
        New_ProductCD = New_ProductCD.split(" , ")[0];
        
        String Non_moving_Day = (String)pvIter.getCurrentRow().getAttribute("Non_Moving_Item_Day");
        oracle.jbo.domain.Date AsOnDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callNonMovingItem");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ASONDT", AsOnDate);
        operationBinding.getParamsMap().put("LV_GROUPCODE",GroupCD);
        operationBinding.getParamsMap().put("LV_NEW",New_ProductCD);
        operationBinding.getParamsMap().put("LV_DAYS", Non_moving_Day);
        operationBinding.getParamsMap().put("LV_SUBGROUP", SubGroupCd);       
        
        Object result = operationBinding.execute();
        
    }
}
