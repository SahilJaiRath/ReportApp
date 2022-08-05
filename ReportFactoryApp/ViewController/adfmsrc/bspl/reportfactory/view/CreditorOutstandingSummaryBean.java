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

public class CreditorOutstandingSummaryBean {


    private RichInputText p1;
    private RichInputText p2;
    private RichInputText p3;
    private RichInputText p4;
    private RichInputText p5;
    private RichInputText p6;

    public CreditorOutstandingSummaryBean() {
    }
        
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Custcode = (String) pvIter.getCurrentRow().getAttribute("PndingSrvForQcVendorCd");
        Custcode = Custcode.split(",")[0];
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        String p1 = (String)this.getP1().getValue();
        String p2 = (String)this.getP2().getValue();
        String p3 = (String)this.getP3().getValue();
        String p4 = (String)this.getP4().getValue();
        String p5 = (String)this.getP5().getValue();
        String p6 = (String)this.getP6().getValue();
        
        
            Random rand = new Random();

            int SID = rand.nextInt(9023632) + 1000000;
            
            System.out.println("==========="+SID);
            ADFContext adfCtx = ADFContext.getCurrent();
            Map pageFlowScope = adfCtx.getPageFlowScope();
            Object val = pageFlowScope.put("SID", SID);
            
            System.out.println("Parameters -- UnitCode-" + Unit  +  "from_Date" + fromDate + "Customer" + Custcode + "p1" + p1 + "p2" + p2
                               + "p3" + p3 + "p4" + p4 + "p5" + p5 + "p6" + p6);
        
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callCreditorOutstandingSummary");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_Custcode",Custcode);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("LV_SID",SID);
        operationBinding.getParamsMap().put("LV_p1", Integer.parseInt(p1));
        operationBinding.getParamsMap().put("LV_p2", Integer.parseInt(p2));
        operationBinding.getParamsMap().put("LV_p3", Integer.parseInt(p3));
        operationBinding.getParamsMap().put("LV_p4", Integer.parseInt(p4));
        operationBinding.getParamsMap().put("LV_p5", Integer.parseInt(p5));
        operationBinding.getParamsMap().put("LV_p6", Integer.parseInt(p6));
        Object result = operationBinding.execute();
        
        }


    public void setP1(RichInputText p1) {
        this.p1 = p1;
    }

    public RichInputText getP1() {
        return p1;
    }

    public void setP2(RichInputText p2) {
        this.p2 = p2;
    }

    public RichInputText getP2() {
        return p2;
    }

    public void setP3(RichInputText p3) {
        this.p3 = p3;
    }

    public RichInputText getP3() {
        return p3;
    }

    public void setP4(RichInputText p4) {
        this.p4 = p4;
    }

    public RichInputText getP4() {
        return p4;
    }

    public void setP5(RichInputText p5) {
        this.p5 = p5;
    }

    public RichInputText getP5() {
        return p5;
    }

    public void setP6(RichInputText p6) {
        this.p6 = p6;
    }

    public RichInputText getP6() {
        return p6;
    }
}
