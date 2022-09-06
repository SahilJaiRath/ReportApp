package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class ArWiseItemStockLedgerBean {
    private RichButton executeBtnbinding;
    private RichButton getReportBinding;

    public ArWiseItemStockLedgerBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        String Group =null;
        String SubGroup =null;
        String Item    =null;
        String ArNo   =null;
        String RepType    =null;
        String Location=null;
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
         Item = (String) pvIter.getCurrentRow().getAttribute("AR_ITEM");
        ArNo = (String) pvIter.getCurrentRow().getAttribute("AR_AR_NO");
        
        if(null==pvIter.getCurrentRow().getAttribute("AR_Location")){
            Location="ALL";
        }
        else{
                Location = (String) pvIter.getCurrentRow().getAttribute("AR_Location");
            }
        if(null==pvIter.getCurrentRow().getAttribute("AR_GROUP")){
            Group="ALL";
        }
        else{
                Group = (String) pvIter.getCurrentRow().getAttribute("AR_GROUP");
            }
       
        if(null==pvIter.getCurrentRow().getAttribute("AR_SubGroup")){
            SubGroup="ALL";
        }
        else{
                SubGroup = (String) pvIter.getCurrentRow().getAttribute("AR_SubGroup");
            }
       
        if(null==pvIter.getCurrentRow().getAttribute("AR_ITEM") ||Item.equalsIgnoreCase("ALL")){
            Item="ALL";
        }
        else{
                Item = (String) pvIter.getCurrentRow().getAttribute("AR_ITEM");
            }
        if(null==pvIter.getCurrentRow().getAttribute("AR_AR_NO") ||ArNo.equalsIgnoreCase("ALL")){
            ArNo="ALL";
        }
        else{
                ArNo = (String) pvIter.getCurrentRow().getAttribute("AR_AR_NO");
            }
    
            
            
        
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate"); 


                    Random rand = new Random();

        int SID = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+SID);
//        ADFContext adfCtx = ADFContext.getCurrent();
//        Map pageFlowScope = adfCtx.getPageFlowScope();
//        Object val = pageFlowScope.put("SID", SID);
        ADFUtils.setEL("#{pageFlowScope.SID}", SID);
        
       String EMP=(String) ADFUtils.resolveExpression("#{pageFlowScope.empCode}");
        
        System.out.println("Parameters -- UnitCode-" + Unit + "LV_SID-" + SID + "LV_LOCATION-" + Location
                           + "LV_GROUP-" + Group + "from_Date" +fromDate + "To_Date" + toDate + "LV_SUBGROUP--" +
                           SubGroup + "LV_ITEM--" + Item  + "LV_ARNO--" + ArNo + "LV_REPTYPE--" + RepType);
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callARwiseItemStockLedger");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_SID", SID);
        operationBinding.getParamsMap().put("LV_LOCATION", Location);
        operationBinding.getParamsMap().put("LV_GROUP", Group);
        operationBinding.getParamsMap().put("LV_SUBGROUP", SubGroup);
        operationBinding.getParamsMap().put("LV_ITEM", Item);
        operationBinding.getParamsMap().put("LV_ARNO", ArNo);
        operationBinding.getParamsMap().put("LV_REPTYPE", RepType);
        operationBinding.getParamsMap().put("FRDATE",fromDate );
        operationBinding.getParamsMap().put("TODATE", toDate);
        operationBinding.getParamsMap().put("EMP_CD", EMP);
        Object result = operationBinding.execute();
        
        
        getGetReportBinding().setDisabled(false);
        
        
    }

    public void setExecuteBtnbinding(RichButton executeBtnbinding) {
        this.executeBtnbinding = executeBtnbinding;
    }

    public RichButton getExecuteBtnbinding() {
        return executeBtnbinding;
    }

    public void setGetReportBinding(RichButton getReportBinding) {
        this.getReportBinding = getReportBinding;
    }

    public RichButton getGetReportBinding() {
        return getReportBinding;
    }
}


