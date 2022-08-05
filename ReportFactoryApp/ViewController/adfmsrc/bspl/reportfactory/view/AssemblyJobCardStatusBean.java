package bspl.reportfactory.view;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class AssemblyJobCardStatusBean {
    public AssemblyJobCardStatusBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 
    
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Asmbly_JCNo = (String) pvIter.getCurrentRow().getAttribute("Asmbly_JCNo");
        if( Asmbly_JCNo.equalsIgnoreCase("ALL"))
        {
            Asmbly_JCNo = "%";
        }
       
        String AsmblyJC_Prod_Cd = (String) pvIter.getCurrentRow().getAttribute("AsmblyJC_Prod_Cd");
        if( AsmblyJC_Prod_Cd .equalsIgnoreCase("ALL"))
        {
            AsmblyJC_Prod_Cd= "%";
        }
        else
        {
            AsmblyJC_Prod_Cd = AsmblyJC_Prod_Cd.split(" , ")[0];
        }
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callAssemblyJobCardStatusReport");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_Asmbly_JCNo", Asmbly_JCNo);
        operationBinding.getParamsMap().put("LV_AsmblyJC_Prod_Cd", AsmblyJC_Prod_Cd);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);

        Object result = operationBinding.execute();
        
        }

    }
