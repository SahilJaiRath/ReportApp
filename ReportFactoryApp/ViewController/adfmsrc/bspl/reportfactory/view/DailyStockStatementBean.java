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

public class DailyStockStatementBean {
    public DailyStockStatementBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String LocationFilter = (String) pvIter.getCurrentRow().getAttribute("DailyStockStatement_LocationFilter"); 
        LocationFilter = LocationFilter.split(",")[0];
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        


                    Random rand = new Random();

        int num = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+num);
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("Sid", num);
        
        System.out.println("Parameters -- UnitCode-" + Unit +  "from_Date" + fromDate
                            + "LocationFilter" + LocationFilter + "num" + num  );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callDailyStockStatementproc");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_LocationFilter", LocationFilter);
        operationBinding.getParamsMap().put("LV_SID", num);
        operationBinding.getParamsMap().put("FRDATE",fromDate );
        
        
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
    
    
    
    

