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

public class ShortExpressRegisterBean {
    public ShortExpressRegisterBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String RepType = (String)pvIter.getCurrentRow().getAttribute("ShortExcessRegister_RepType");
       
            
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate"); 


//                    Random rand = new Random();
//
//        int num = rand.nextInt(9023632) + 1000000;
//        
//        System.out.println("==========="+num);
//        ADFContext adfCtx = ADFContext.getCurrent();
//        Map pageFlowScope = adfCtx.getPageFlowScope();
//        Object val = pageFlowScope.put("Sid", num);
        
        System.out.println("Parameters -- UnitCode-" + Unit +  "from_Date" +fromDate
                            + "To_Date" + toDate  );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callShortExcessRegister");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_REPTYPE", RepType);

//        operationBinding.getParamsMap().put("LV_SID", num);
        
        

        operationBinding.getParamsMap().put("FRDATE",fromDate );
        operationBinding.getParamsMap().put("TODATE", toDate);
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
    
    
    
    

