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

public class DailyInvoiceSaleBean {
    public DailyInvoiceSaleBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("SubLedger_UnitCode");
        if(Unit.equalsIgnoreCase("ALL"))
        {
            Unit = "%";
        }
        else{
                Unit = Unit.split(",")[0];
            }
        
        String Inv_No = (String)pvIter.getCurrentRow().getAttribute("DailyInvoice_InvoiceNo");
        
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        
        
      
        
        System.out.println("Parameters -- UnitCode-" + Unit + "from_Date" +fromDate
                            + "To_Date" + toDate + "Invoice No" + Inv_No );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callDailyInvoiceSale");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("FRDATE",fromDate );
        operationBinding.getParamsMap().put("TODATE", toDate);
        operationBinding.getParamsMap().put("LV_INVOICENO", Inv_No);
        Object result = operationBinding.execute();
        
        
        
        
        
    }
}
