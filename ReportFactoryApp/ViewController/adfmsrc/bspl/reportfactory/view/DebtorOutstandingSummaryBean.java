package bspl.reportfactory.view;

import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class DebtorOutstandingSummaryBean {


    private RichInputText lv_p1;
    private RichInputText lv_p2;
    private RichInputText lv_p3;
    private RichInputText lv_p4;
    private RichInputText lv_p5;
    private RichInputText lv_p6;

    public DebtorOutstandingSummaryBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Custcode = (String)pvIter.getCurrentRow().getAttribute("CollectionCust");
//        Integer p1 = (Integer) pvIter.getCurrentRow().getAttribute("LV_p1");
//        Integer p2 = (Integer) pvIter.getCurrentRow().getAttribute("LV_p2");
//        Integer p3 = (Integer) pvIter.getCurrentRow().getAttribute("LV_p3");
//        Integer p4 = (Integer) pvIter.getCurrentRow().getAttribute("LV_p4");
//        Integer p5 = (Integer) pvIter.getCurrentRow().getAttribute("LV_p5");
//        Integer p6 = (Integer) pvIter.getCurrentRow().getAttribute("LV_p6");



        
        Custcode = Custcode.split(",")[0];
//        String p1 = (String) this.getP1().g
         String p1 = (String) this.getLv_p1().getValue();
         String p2 = (String) this.getLv_p2().getValue();
         String p3 = (String) this.getLv_p3().getValue();
        String p4 = (String) this.getLv_p4().getValue();
        String p5 = (String) this.getLv_p5().getValue();
        String p6 = (String) this.getLv_p6().getValue();

         
        
        
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        
        
        Random rand = new Random();

        int SID = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+SID);
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("SID", SID);
        
        System.out.println("Parameters -- UnitCode-" + Unit  +  "from_Date" +fromDate + "Customer" + Custcode + "p1" + p1 + "p2"+p2
                           +"p3"+p3+"p4"+p4+"p5"+p5+"p6"+p6);
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callDebtorOutstandingSummary");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_p1", Integer.parseInt(p1));
        operationBinding.getParamsMap().put("LV_p2",  Integer.parseInt(p2));
        operationBinding.getParamsMap().put("LV_p3",  Integer.parseInt(p3));
        operationBinding.getParamsMap().put("LV_p4",  Integer.parseInt(p4));
        operationBinding.getParamsMap().put("LV_p5",  Integer.parseInt(p5));
        operationBinding.getParamsMap().put("LV_p6",  Integer.parseInt(p6));




       /* operationBinding.getParamsMap().put("LV_p1", p3);
        operationBinding.getParamsMap().put("LV_p1", p4);
        operationBinding.getParamsMap().put("LV_p1", p5);
        operationBinding.getParamsMap().put("LV_p1", p6);*/

        operationBinding.getParamsMap().put("LV_CUSTOMER",Custcode );
        operationBinding.getParamsMap().put("LV_SID", SID);
        operationBinding.getParamsMap().put("FRDATE",fromDate );
        Object result = operationBinding.execute();
        
        
        
        
        
    }

    public void setLv_p1(RichInputText lv_p1) {
        this.lv_p1 = lv_p1;
    }

    public RichInputText getLv_p1() {
        return lv_p1;
    }

    public void setLv_p2(RichInputText lv_p2) {
        this.lv_p2 = lv_p2;
    }

    public RichInputText getLv_p2() {
        return lv_p2;
    }

    public void setLv_p3(RichInputText lv_p3) {
        this.lv_p3 = lv_p3;
    }

    public RichInputText getLv_p3() {
        return lv_p3;
    }

    public void setLv_p4(RichInputText lv_p4) {
        this.lv_p4 = lv_p4;
    }

    public RichInputText getLv_p4() {
        return lv_p4;
    }

    public void setLv_p5(RichInputText lv_p5) {
        this.lv_p5 = lv_p5;
    }

    public RichInputText getLv_p5() {
        return lv_p5;
    }

    public void setLv_p6(RichInputText lv_p6) {
        this.lv_p6 = lv_p6;
    }

    public RichInputText getLv_p6() {
        return lv_p6;
    }
}
