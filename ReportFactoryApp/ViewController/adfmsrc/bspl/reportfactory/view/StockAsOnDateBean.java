package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class StockAsOnDateBean {
    private RichButton executeBtnBinding;

    public StockAsOnDateBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);
    private String jaspeReportName;

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Location = (String) pvIter.getCurrentRow().getAttribute("LOC");
            if(Location.equalsIgnoreCase("ALL"))
            {
                Location = "%";
            }
            else{
                    Location = Location.split(",")[0];
                }
        
        
//        String RepType = (String) pvIter.getCurrentRow().getAttribute("rep_type");
        
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");

        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_LOCATION", Location);
//        operationBinding.getParamsMap().put("LV_REPTYPE",RepType);
        operationBinding.getParamsMap().put("FRDATE",fromDate);

        Object result = operationBinding.execute();
        
        }
    public String getJaspeReportName() 
    {
        String path = ADFUtils.jasperReportFileName("MTL0000000132");
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

    public void setExecuteBtnBinding(RichButton executeBtnBinding) {
        this.executeBtnBinding = executeBtnBinding;
    }

    public RichButton getExecuteBtnBinding() {
        return executeBtnBinding;
    }
}
