package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class StockStatusBean {
    public StockStatusBean() {
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Item_code = (String) pvIter.getCurrentRow().getAttribute("ItemCd");
        if(Item_code.equalsIgnoreCase("ALL"))
        {
            Item_code = "%";
        }
        else
        {
            Item_code = Item_code.split(",")[0];
        }
        String finance_year = (String) pvIter.getCurrentRow().getAttribute("FinYear");
        
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callStockStatus");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ITEM_CD", Item_code);
        operationBinding.getParamsMap().put("LV_FIN_YEAR", finance_year);
        
        Object result = operationBinding.execute();
        
        
    }
}
