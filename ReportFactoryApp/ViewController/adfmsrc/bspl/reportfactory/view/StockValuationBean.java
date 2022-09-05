package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class StockValuationBean {
    private RichButton bindExecutebtn;

    public StockValuationBean() {
    }
    private String jaspeReportName;
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Location = (String) pvIter.getCurrentRow().getAttribute("LOC");
        String Item_code = (String) pvIter.getCurrentRow().getAttribute("ItemCd");
        if(Item_code.equalsIgnoreCase("ALL"))
        {
            Item_code ="%";
        }
        else
        {
            Item_code = Item_code.split(",")[0];
        }
        
        //        String rep_type = (String) pvIter.getCurrentRow().getAttribute("rep_type");
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        
        Random rand = new Random();

        int SID = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+SID);
        
        // ADFUtils.setValueToPageFlowScope("LV_SID",num);
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("SID",  SID);
        
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callStockValuation");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ITEM_CD", Item_code);
        operationBinding.getParamsMap().put("LV_Location", Location);
        //        operationBinding.getParamsMap().put("LV_rep_type", rep_type);
        operationBinding.getParamsMap().put("LV_frmDate", fromDate);
        operationBinding.getParamsMap().put("LV_tDate", toDate);
        operationBinding.getParamsMap().put("LV_SID", SID);
        
        Object result = operationBinding.execute();
    }
    public String getJaspeReportName() 
    {
        String path = ADFUtils.jasperReportFileName("MTL0000000131");
        System.out.println("Inside Getter of  Path: " + path);
        if (path != null) 
        {
            return path;
        }
        return jaspeReportName;
    }

    public void setJaspeReportName(String jaspeReportName) {
        this.jaspeReportName = jaspeReportName;
    }

    public void setBindExecutebtn(RichButton bindExecutebtn) {
        this.bindExecutebtn = bindExecutebtn;
    }

    public RichButton getBindExecutebtn() {
        return bindExecutebtn;
    }
}
