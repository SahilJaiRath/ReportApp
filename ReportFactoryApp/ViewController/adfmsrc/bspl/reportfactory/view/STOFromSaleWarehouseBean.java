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

public class STOFromSaleWarehouseBean {
    public STOFromSaleWarehouseBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String WarehouseName = (String) pvIter.getCurrentRow().getAttribute("WarehouseName");
//        String ChallanNo = (String) pvIter.getCurrentRow().getAttribute("Challan_No");
//        String ItemCode = (String) pvIter.getCurrentRow().getAttribute("PendingToD28_ItemCode");
     
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callSTOfromSalesWarehouse");
        operationBinding.getParamsMap().put("LV_WAREHOUSENAME",WarehouseName);
//        operationBinding.getParamsMap().put("LV_ChallanNo", ChallanNo);
//        operationBinding.getParamsMap().put("LV_ItemCode",ItemCode);


        Object result = operationBinding.execute();
        
        }

}
