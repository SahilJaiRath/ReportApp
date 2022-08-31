package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.InitialContext;

import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import model.service.AppModuleImpl;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.util.JRLoader;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class  SRVDetailBean {
    public SRVDetailBean() {
    }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class); 
    private String jaspeReportName;
    
    public void onClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String LocationFilter = (String) pvIter.getCurrentRow().getAttribute("SRVDetail_LocationFilter");
            if(LocationFilter.equalsIgnoreCase("ALL"))
            {
                LocationFilter = "%";
            }
            else
            {
                LocationFilter = LocationFilter.split(",")[0];
            }
        String PartName = (String) pvIter.getCurrentRow().getAttribute("StockAdjustment_PartName");
            if(PartName.equalsIgnoreCase("ALL"))
            {
                PartName = "%";
            }
            else
            {
                PartName = PartName.split(",")[0];
            }
        String GroupCd = (String) pvIter.getCurrentRow().getAttribute("GroupCd");
            if(GroupCd.equalsIgnoreCase("ALL"))
            {
                GroupCd = "%";
            }
            else
            {
                GroupCd = GroupCd.split(",")[0];
            }
        String SubGroupCd = (String) pvIter.getCurrentRow().getAttribute("SubGroupCd");
            if(SubGroupCd.equalsIgnoreCase("ALL"))
            {
                SubGroupCd = "%";
            }
            else
            {
                SubGroupCd = SubGroupCd.split(",")[0];
            }
        String ApproveFilter = (String) pvIter.getCurrentRow().getAttribute("SRVDetail_ApproveFilter");
        String Statusfilter = (String) pvIter.getCurrentRow().getAttribute("SRVDetail_Statusfilter");
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callSRVDetail");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_LocationFilter",LocationFilter);
        operationBinding.getParamsMap().put("LV_ITEM",PartName);
        operationBinding.getParamsMap().put("LV_GroupCd",GroupCd);
        operationBinding.getParamsMap().put("LV_SubGroupCd",SubGroupCd);
        operationBinding.getParamsMap().put("LV_ApproveFilter",ApproveFilter);
        operationBinding.getParamsMap().put("LV_Statusfilter",Statusfilter);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);

        Object result = operationBinding.execute();
        
        }
    
    public Connection getConnection() throws Exception {
        InitialContext initialContext = new InitialContext();
        // Production setup you can use JNDI name
        DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/APPLICATIONDBDS");

        java.sql.Connection conn = ds.getConnection();
        return conn;
    }

    public void downloadJrxml(FacesContext facesContext, OutputStream outputStream) {
        Connection conn = null;
        try {
            String file_name = "srv_accounts.jasper";
            OperationBinding binding = ADFUtils.findOperation("fileNameForPrint");
            binding.getParamsMap().put("fileId", "MTL0000000125");
            binding.execute();
            System.out.println("Binding Result :" + binding.getResult().toString());
            if (binding.getResult() != null) {

                String result = binding.getResult().toString();
                int last_index = result.lastIndexOf("/");
                if (last_index == -1) {
                    last_index = result.lastIndexOf("\\");
                }
                String path = result.substring(0, last_index + 1) + file_name;
                InputStream input = new FileInputStream(path);
                System.out.println("path"+path);
                // InputStream input = new FileInputStream(binding.getResult().toString());
                //  InputStream input = new FileInputStream("//home//beta3//Jasper//Jour_prn1.jrxml");
                DCIteratorBinding poIter =
                    (DCIteratorBinding) getBindings().get("SRVDetailVO1Iterator");
                String p_srv_no = (String)poIter.getCurrentRow().getAttribute("SrvNo");
                String unitCode = poIter.getCurrentRow().getAttribute("UnitCd").toString();
                System.out.println("Srv No. :- " + p_srv_no + " " + unitCode);
                Map n = new HashMap();
                n.put("p_no", p_srv_no);
                n.put("p_unit", unitCode);

                conn = getConnection();

                JasperReport design = (JasperReport) JRLoader.loadObject(input);
                System.out.println("Path : " + input + " -------" + design + " param:" + n);

                @SuppressWarnings("unchecked")
                net.sf.jasperreports.engine.JasperPrint print = JasperFillManager.fillReport(design, n, conn);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
                byte pdf[] = JasperExportManager.exportReportToPdf(print);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.getOutputStream().write(pdf);
                response.getOutputStream().flush();
                response.getOutputStream().close();
                facesContext.responseComplete();
            } else
                System.out.println("File Name/File Path not found.");
        } catch (FileNotFoundException fnfe) {
            // TODO: Add catch code
            fnfe.printStackTrace();
            try {
                System.out.println("in finally connection closed");
                conn.close();
                conn = null;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            try {
                System.out.println("in finally connection closed");
                conn.close();
                conn = null;

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                System.out.println("in finally connection closed");
                conn.close();
                conn = null;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public String getJaspeReportName() 
    {
        String path = ADFUtils.jasperReportFileName("MTL0000000125");
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
}
