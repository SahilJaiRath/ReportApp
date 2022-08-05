package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import java.net.InetAddress;

import java.net.UnknownHostException;

import java.time.Year;

import java.util.Map;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class GoodsinTransitBean {
    public GoodsinTransitBean() {
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }

       private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("SubLedger_UnitCode");
        
                Unit = Unit.split(",")[0];
            
        
        
        
        String ChallanNo = (String) pvIter.getCurrentRow().getAttribute("GoodsinTransit_ChallanNo");
       
        
        
        
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
            
       
        
        System.out.println("Parameters :- fromDate:" + fromDate + " ||UNIT CODE:" + Unit + "|| toDate:" + toDate + "ChallanNo" +
                             ChallanNo );
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callGoodsinTransit");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_CHALLANNO",ChallanNo);

       
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);
        Object result = operationBinding.execute();
    }
       

}
