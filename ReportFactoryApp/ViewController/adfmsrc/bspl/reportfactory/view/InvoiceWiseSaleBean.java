package bspl.reportfactory.view;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class  InvoiceWiseSaleBean {
    public InvoiceWiseSaleBean() {
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
        else
        {
            Unit = Unit.split(",")[0];
        }
        String ItemName = (String) pvIter.getCurrentRow().getAttribute("InvoiceWiseSale_ItemName");
        if(ItemName.equalsIgnoreCase("ALL"))
        {
            ItemName = "%";
        }
        else
        {
            ItemName = ItemName.split(",")[0];
        }
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");    
            
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callInvoiceWiseSale");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ItemName",ItemName);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);


        Object result = operationBinding.execute();
        
        }

}
