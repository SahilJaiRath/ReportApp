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

public class ItemWiseSaleBean {
    public ItemWiseSaleBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Inv_Type = (String) pvIter.getCurrentRow().getAttribute("ItemWiseSale_InvoiceType");
        
        if(Inv_Type.equalsIgnoreCase("ALL"))
        {
            Inv_Type = "%";
        }
        else{
                Inv_Type = Inv_Type.split(",")[0];
            }
        
        String Stock_Typ = (String) pvIter.getCurrentRow().getAttribute("ItemWiseSale_StockType");
        
        if(Stock_Typ.equalsIgnoreCase("ALL"))
        {
            Stock_Typ = "%";
        }
        else{
                Stock_Typ = Stock_Typ.split(",")[0];
            }
        
        String Product_Code = (String) pvIter.getCurrentRow().getAttribute("ItemWiseSale_ProductCode");
        if(Product_Code.equalsIgnoreCase("ALL"))
        {
            Product_Code = "%";
        }
        else{
                Product_Code = Product_Code.split(",")[0];
            }
        
        
            
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate"); 


                   
        System.out.println("Parameters -- UnitCode-" + Unit  +  "from_Date" +fromDate + "To_Date" + toDate + "ItemWiseSale_InvoiceType" + 
                            Inv_Type+ "ItemWiseSale_StockType" + Stock_Typ + "ItemWiseSale_ProductCode" + Product_Code  );
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callItemWiseSale");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_INVOICETYPE", Inv_Type );
        operationBinding.getParamsMap().put("LV_STOCKTYPE", Stock_Typ );
        operationBinding.getParamsMap().put("LV_PRODUCTCODE", Product_Code );
        operationBinding.getParamsMap().put("FRDATE",fromDate );
        operationBinding.getParamsMap().put("TODATE", toDate);
        Object result = operationBinding.execute();

        
    }
}
    
    
    
    

