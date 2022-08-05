package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class  SRVDetailBean {
    public SRVDetailBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 
    
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String LocationFilter = (String) pvIter.getCurrentRow().getAttribute("SRVDetail_LocationFilter");
            if(LocationFilter.equalsIgnoreCase("ALL"))
            {
                LocationFilter = "%";
            }
            else
            {
                LocationFilter = LocationFilter.split(",")[0];
            }
        String PartName = (String) pvIter.getCurrentRow().getAttribute("StockAdjustment_PartName");
            if(PartName.equalsIgnoreCase("ALL"))
            {
                PartName = "%";
            }
            else
            {
                PartName = PartName.split(",")[0];
            }
        String GroupCd = (String) pvIter.getCurrentRow().getAttribute("GroupCd");
            if(GroupCd.equalsIgnoreCase("ALL"))
            {
                GroupCd = "%";
            }
            else
            {
                GroupCd = GroupCd.split(",")[0];
            }
        String SubGroupCd = (String) pvIter.getCurrentRow().getAttribute("SubGroupCd");
            if(SubGroupCd.equalsIgnoreCase("ALL"))
            {
                SubGroupCd = "%";
            }
            else
            {
                SubGroupCd = SubGroupCd.split(",")[0];
            }
        String ApproveFilter = (String) pvIter.getCurrentRow().getAttribute("SRVDetail_ApproveFilter");
        String Statusfilter = (String) pvIter.getCurrentRow().getAttribute("SRVDetail_Statusfilter");
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callSRVDetail");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_LocationFilter",LocationFilter);
        operationBinding.getParamsMap().put("LV_ITEM",PartName);
        operationBinding.getParamsMap().put("LV_GroupCd",GroupCd);
        operationBinding.getParamsMap().put("LV_SubGroupCd",SubGroupCd);
        operationBinding.getParamsMap().put("LV_ApproveFilter",ApproveFilter);
        operationBinding.getParamsMap().put("LV_Statusfilter",Statusfilter);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);

        Object result = operationBinding.execute();
        
        }

}
