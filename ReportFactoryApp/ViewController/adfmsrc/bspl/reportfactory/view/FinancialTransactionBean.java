package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;

    import javax.el.ELContext;
    import javax.el.ExpressionFactory;
    import javax.el.ValueExpression;
    import javax.faces.context.FacesContext;
import javax.faces.application.Application;

public class FinancialTransactionBean {
    private RichInputText bindingMonthWise;
    private RichOutputText voucherTypeBind;

    public FinancialTransactionBean() {
        
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 
    
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");

        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callFinancialTransactions");
        
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);

        Object result = operationBinding.execute();
        
        
        
        
        }
    public static Object resolvElDC(String data) {   
         FacesContext fc = FacesContext.getCurrentInstance();        
         Application app = fc.getApplication();      
         ExpressionFactory elFactory = app.getExpressionFactory();   
         ELContext elContext = fc.getELContext();    
         ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{data." + data + ".dataProvider}", Object.class);   
         return valueExp.getValue(elContext);        
     }
    
    public void onClickButton2(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
//        String VoucherType = (String) pvIter.getCurrentRow().getAttribute("LV_p1");
      //  System.out.println("value of month--------  "+bindingMonthWise.getValue());
        

        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        
//        String type = (String) this.getType().getValue();
//        System.out.println(type);

        
//        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
//        OperationBinding operationBinding = bindings.getOperationBinding("callMonthwiseTotalTransaction");
//        
//        operationBinding.getParamsMap().put("LV_UNIT",Unit);
//        operationBinding.getParamsMap().put("FRDATE",fromDate);
//        operationBinding.getParamsMap().put("TODATE",toDate);
//        operationBinding.getParamsMap().put("LV_VOUCHERTYPE", bindingMonthWise.getValue());
//
//        Object result = operationBinding.execute();
        AppModuleImpl am=(AppModuleImpl)resolvElDC("AppModuleDataControl");
          ViewObjectImpl vo = am.getMonthWisetotalTransactionsVO1();
                    vo.setNamedWhereClauseParam("P473_UNIT_CD", Unit);
            vo.setNamedWhereClauseParam("P473_FROM_DATE", fromDate);
            vo.setNamedWhereClauseParam("P473_TO_DT", toDate);  
            vo.setNamedWhereClauseParam("P473_VOUCHER_TYPE", ADFUtils.resolveExpression("#{row.VoucherType}"));
            
           
            vo.executeQuery();
            


    }


    public void setBindingMonthWise(RichInputText bindingMonthWise) {
        this.bindingMonthWise = bindingMonthWise;
    }

    public RichInputText getBindingMonthWise() {
        return bindingMonthWise;
    }

    public void setVoucherTypeBind(RichOutputText voucherTypeBind) {
        this.voucherTypeBind = voucherTypeBind;
    }

    public RichOutputText getVoucherTypeBind() {
        return voucherTypeBind;
    }
}
