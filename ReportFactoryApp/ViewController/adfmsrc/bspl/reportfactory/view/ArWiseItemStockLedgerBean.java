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

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class ArWiseItemStockLedgerBean {
    public ArWiseItemStockLedgerBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Location = (String) pvIter.getCurrentRow().getAttribute("LOC");
        String Group = (String) pvIter.getCurrentRow().getAttribute("AR_GROUP");
        String SubGroup = (String) pvIter.getCurrentRow().getAttribute("AR_SubGroup");
        String Item = (String) pvIter.getCurrentRow().getAttribute("AR_ITEM");
        String ArNo = (String) pvIter.getCurrentRow().getAttribute("AR_AR_NO");
        String RepType = (String) pvIter.getCurrentRow().getAttribute("rep_type");
            
            
        
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
        
        
        
        
        
    }
}
    
    
    
    

