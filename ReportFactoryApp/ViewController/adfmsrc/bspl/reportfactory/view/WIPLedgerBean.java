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

public class WIPLedgerBean {
    public WIPLedgerBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String LocWip = (String) pvIter.getCurrentRow().getAttribute("LocWip");
        if(LocWip.equalsIgnoreCase("ALL"))
        {
            LocWip = "%";
        }
        else{
                LocWip = LocWip.split(",")[0];
            }
        String WIPLgr_PrcssFilter = (String) pvIter.getCurrentRow().getAttribute("WIPLgr_PrcssFilter");
        System.out.println(WIPLgr_PrcssFilter);
        if(WIPLgr_PrcssFilter.equalsIgnoreCase("ALL"))
        {
            WIPLgr_PrcssFilter = "%";
        }
        else{
//           
             WIPLgr_PrcssFilter = WIPLgr_PrcssFilter.split(",")[0];


        }
        System.out.println(WIPLgr_PrcssFilter);
        String WIPLgr_ItemFilter = (String) pvIter.getCurrentRow().getAttribute("WIPLgr_ItemFilter");     
        if(WIPLgr_ItemFilter.equalsIgnoreCase("ALL"))
        {
            WIPLgr_ItemFilter = "%";
        }
        else{
                WIPLgr_ItemFilter = WIPLgr_ItemFilter.split(",")[0];
            }
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate"); 


                    Random rand = new Random();

        int SID = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+SID);
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("SID", SID);
        
        System.out.println("Parameters -- UnitCode-" + Unit + "LV_LocWip-" + LocWip + "LV_WIPLgr_PrcssFilter-" + WIPLgr_PrcssFilter
                           + "LV_WIPLgr_ItemFilter-" + WIPLgr_ItemFilter + "from_Date" +fromDate + "To_Date" + toDate  );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callWIPLedgerreport");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_SID", SID);
        operationBinding.getParamsMap().put("LV_LocWip", LocWip);
        operationBinding.getParamsMap().put("LV_WIPLgr_PrcssFilter", WIPLgr_PrcssFilter );
        operationBinding.getParamsMap().put("LV_WIPLgr_ItemFilter", WIPLgr_ItemFilter);
        operationBinding.getParamsMap().put("FRDATE",fromDate );
        operationBinding.getParamsMap().put("TODATE", toDate);
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
    
    
    
    

