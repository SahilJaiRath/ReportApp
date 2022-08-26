package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import java.util.Date;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.nav.RichLink;
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

import oracle.jbo.Row;

public class FinancialTransactionBean {
    private RichInputText bindingMonthWise;
    private RichOutputText voucherTypeBind;
    private RichLink transOfTheMonth;
    private RichOutputText monthNameBind;
    private RichColumn transactionMonthBind;

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
        
        ADFUtils.setEL("#{pageFlowScope.UnitCd}", Unit);
        ADFUtils.setEL("#{pageFlowScope.FromDT}", fromDate);
        ADFUtils.setEL("#{pageFlowScope.toDT}", toDate);
        
        
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
            
        String VoucheTy=(String)ADFUtils.resolveExpression("#{row.VoucherType}");
        
        ADFUtils.setEL("#{pageFlowScope.Voucher}", VoucheTy);

           
            vo.executeQuery();
            


    }
    
   
    
    public void onClickButton3(ActionEvent actionEvent) {
      
      System.out.println("**==="+ADFUtils.resolveExpression("#{pageFlowScope.UnitCd}"));
        System.out.println("**==="+ADFUtils.resolveExpression("#{pageFlowScope.FromDT}"));
        System.out.println("**==="+ADFUtils.resolveExpression("#{pageFlowScope.toDT}"));
        
        
        String Unit=(String)ADFUtils.resolveExpression("#{pageFlowScope.UnitCd}");

        oracle.jbo.domain.Date FrDate=(oracle.jbo.domain.Date)ADFUtils.resolveExpression("#{pageFlowScope.FromDT}");
        oracle.jbo.domain.Date ToDate=(oracle.jbo.domain.Date)ADFUtils.resolveExpression("#{pageFlowScope.toDT}");

        String VoucherTyp=(String)ADFUtils.resolveExpression("#{pageFlowScope.Voucher}");
        
       String mon=(String)ADFUtils.resolveExpression("#{row.Month}");
       System.out.println("========"+mon);
        

    AppModuleImpl am=(AppModuleImpl)resolvElDC("AppModuleDataControl");
      ViewObjectImpl vo = am.getTransactionsoftheMonthVO1();

        vo.setNamedWhereClauseParam("P474_UNIT_CD",Unit);
        vo.setNamedWhereClauseParam("P474_FROM_DATE", FrDate );
        vo.setNamedWhereClauseParam("P474_TO_DT",ToDate  );  
        vo.setNamedWhereClauseParam("P474_VOUCHER_TYPE", VoucherTyp);
        vo.setNamedWhereClauseParam("P474_MONTH",mon);
        
       
        vo.executeQuery();
        


    }
    
    
    public void onClickButton4(ActionEvent actionEvent) {
      
      System.out.println("**==="+ADFUtils.resolveExpression("#{pageFlowScope.UnitCd}"));
        
        
        
        String Unit=(String)ADFUtils.resolveExpression("#{pageFlowScope.UnitCd}");
        String VoucherNumber=(String)ADFUtils.resolveExpression("#{row.VouNo}");
        
        System.out.println("=========" + VoucherNumber );

       
        

    AppModuleImpl am=(AppModuleImpl)resolvElDC("AppModuleDataControl");
      ViewObjectImpl vo = am.getVoucherDetailreportVO1();

        vo.setNamedWhereClauseParam("P193_UNIT_CD",Unit);
        vo.setNamedWhereClauseParam("P193_VOUCH_NO1",VoucherNumber);

        
        
       
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

    public void setTransOfTheMonth(RichLink transOfTheMonth) {
        this.transOfTheMonth = transOfTheMonth;
    }

    public RichLink getTransOfTheMonth() {
        return transOfTheMonth;
    }

    public void setMonthNameBind(RichOutputText monthNameBind) {
        this.monthNameBind = monthNameBind;
    }

    public RichOutputText getMonthNameBind() {
        return monthNameBind;
    }

    public void setTransactionMonthBind(RichColumn transactionMonthBind) {
        this.transactionMonthBind = transactionMonthBind;
    }

    public RichColumn getTransactionMonthBind() {
        return transactionMonthBind;
    }
}
