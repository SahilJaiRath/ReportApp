package servlet;

import bspl.reportfactory.bean.ADFUtils;

import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;

import java.util.Map;

import java.util.Random;

import javax.imageio.ImageIO;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;
    import oracle.jbo.ApplicationModule;
import javax.faces.application.Application;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import oracle.adf.share.ADFContext;

@WebServlet(name = "ServletClass", urlPatterns = {"/reports/ServletClass" })
public class ServletClass extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void callJasper(String reportName, String serverpath, Map parameters, Connection conn, String DocNo,String RepFormat,
                           HttpServletResponse response) throws IOException {

        OutputStream ouputStream = null;
        String fileName=null;
        try 
        {
            /* If we want to pass the file as .jsaper , use below two lines*/
            File repFile = new File(serverpath + reportName + ".jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(repFile);


            /*  If we want to pass the file as .jrxml , use below two lines*/

            /*  InputStream input = new FileInputStream(new File(serverpath + reportName + ".jrxml"));
                JasperDesign design = JRXmlLoader.load(input);
                JasperReport report = JasperCompileManager.compileReport(design); 
            */

            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
            ouputStream = response.getOutputStream();
            
            
         
         
            JRExporter exporter = null;
            if (true) {
                
                if(RepFormat.equalsIgnoreCase("PDF")){
                     fileName = "Doc_" + reportName + ".pdf";  
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
                    exporter = new JRPdfExporter();
                }else{
                    fileName = "Doc_" + reportName + ".xls";  
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
                    exporter = new JRXlsExporter();
                    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                    
                    
                }
                
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
              exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
            }

            exporter.exportReport();
            String str = "<script>window.onload= function () { window.print();window.close();   }  </script>";
            ouputStream.write(str.getBytes());
            try {
                System.out.println("in Try1 connection closed");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (JRException e) {
            e.printStackTrace();
            try {
                System.out.println("in catch finally connection closed");
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
        } finally 
        {
            if (conn != null) {
                try {
                    System.out.println("Inside finally connection closed");
                    conn.close();
                } catch (SQLException e) {
                }
            }
            if (ouputStream != null) {
                ouputStream.flush();
                ouputStream.close();
            }
        }
    }
    public String newConvertStringToJboDate(String datestring) throws ParseException {
        System.out.println("Inside ADFUtils for date!");
     //   if (datestring.equals("-")) {
            //                    System.out.println("inside else FALSE !(Slash format)");
            //                java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("mm/dd/yyyy");
            //
            //                java.text.SimpleDateFormat sdfsql = new java.text.SimpleDateFormat("dd-MMM-yyyy");
            //                java.util.Date date = null;
            //                try {
            //                        System.out.println("Inside try block!");
            //                    date = sdf1.parse(datestring);
            //                } catch (Exception exc) {
            //                        System.out.println("Date error");
            //                }
            //                return new oracle.jbo.domain.Date(sdfsql.format(date));
      //   For server  
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
       // For Local
     //   SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
            Date date = format1.parse(datestring);
            System.out.println(format2.format(date));
            return format2.format(date);
       
    }
    public static boolean isValidFormat(String format, String value) {
            System.out.println("Inside valid format function!");
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
    
 
    
        
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType(CONTENT_TYPE);
        Random rand = new Random();
        int SessionId = rand.nextInt(9023632) + 1000000;
        Connection conn = null;
        PreparedStatement psfile = null;
        ResultSet reportPathRsfile = null;
        PreparedStatement ps = null;
        ResultSet reportPathRs = null;
        String reportName =null;
        String FilePath = null;
        BufferedImage image=null;
        
        String DocNo = null;
        String UnitCode = null;
        String RepFormat = null;
        String RepName = null;
        String ItemCode =  null;
        String ItemGroup = null;
        String SubGroup = null;
        String LocCode =  null;
        String FromDate = null;
        String ToDate = null;
        String EmpCode =  null;
        String SID =  null;
        String ReqQty = null;
        
        if(null==request.getParameter("DocNo")){
            
        }else{
            DocNo = request.getParameter("DocNo").toString();
        }
        if(null==request.getParameter("UnitCode")){
            
        }else{
            UnitCode = request.getParameter("UnitCode").toString();
        }
        if(null==request.getParameter("RepFormat")){
            
        }else{
            RepFormat = request.getParameter("RepFormat").toString();
        }
        if(null==request.getParameter("RepName")){
            
        }else{
            RepName = request.getParameter("RepName").toString();
        }
        if(null==request.getParameter("ItemCode")){
            
        }else{
            ItemCode = request.getParameter("ItemCode").toString();
        }
        if(null==request.getParameter("ItemGroup")){
            
        }else{
            ItemGroup = request.getParameter("ItemGroup").toString();
        }
        if(null==request.getParameter("SubGroup")){
            
        }else{
            SubGroup = request.getParameter("SubGroup").toString();
        }
        if(null==request.getParameter("LocCode")){
            
        }else{
            LocCode = request.getParameter("LocCode").toString();
        }
        if(null==request.getParameter("FromDate")){
            
        }else{
            FromDate = request.getParameter("FromDate").toString();
            System.out.println("=====FromDate========="+FromDate);
        }
        if(null==request.getParameter("ToDate")){
            
        }else{
            ToDate = request.getParameter("ToDate").toString();
            System.out.println("=====ToDate========="+ToDate);
        }
        
        if(null==request.getParameter("EmpCode")){
            
        }else{
            EmpCode = request.getParameter("EmpCode").toString();
        }
       
        if(null==request.getParameter("SID")){
            
        }else{
            SID = request.getParameter("SID").toString();
        }
        if(null==request.getParameter("ReqQty")){
            
        }else{
            ReqQty = request.getParameter("ReqQty").toString();
        }
         
        try 
        {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APPLICATIONDBDS");
            conn = ds.getConnection();
            String path;
            ps = conn.prepareStatement("SELECT PATH FROM SERVER_PATH WHERE MODULE = 'REPSERVERPATH'");
            reportPathRs = ps.executeQuery();
            path = null;
            //path = "/home/aakash/Jasper Reports/";
            while (reportPathRs.next()) 
            {
                path = reportPathRs.getString(1);
                System.out.println("Main Server path is---->>" + reportPathRs.getString(1));
            }
            int last_index = path.lastIndexOf("/");
            if (last_index == -1) {
                last_index = path.lastIndexOf("\\");
            }
            String serverpath = path.substring(0, last_index + 1);
            System.out.println("the server path is -->" + serverpath);
            System.out.println("Invoice No--->>:" + DocNo + " Unit-->>:" + UnitCode + " Invoice Mode-->>:" +
                               RepFormat + " Inv Param-->>:" );
            String defaultFileName = request.getParameter("defpath").toString();
            System.out.println("Default----->>:" + defaultFileName);
            if (!(defaultFileName == null || defaultFileName.equals(""))) 
            {
                defaultFileName = defaultFileName.substring(0, defaultFileName.lastIndexOf("."));
                System.out.println("default file name:" + defaultFileName);
                reportName = defaultFileName;
            } 
            else 
            {
//                if (InvoiceMode.equalsIgnoreCase("PRP")) 
//                {
//                    reportName = "r_pre_pack";
//                } else if (InvoiceMode.equalsIgnoreCase("POP")) {
//                    reportName = "r_post_pack";
//                }
            }

            Map parameters = new HashMap();
            
            if(RepName.equals("SRV")){
                System.out.println("When Srv Details");
                parameters.put("p_no", DocNo);
                parameters.put("p_unit", UnitCode);  
            }
           if(RepName.equals("ARW")){
                    parameters.put("P_AR_NO", DocNo);
                    parameters.put("P_UNIT_CD", UnitCode);
                    parameters.put("P_ITEM_CD", ItemCode);
                    parameters.put("P_ITEM_GROUP", ItemGroup);
                    parameters.put("P_ITEM_S_GROUP", SubGroup);
                    parameters.put("P_LOC_CD", LocCode);
                    parameters.put("P_FROM_DATE",newConvertStringToJboDate(FromDate)  );
                    parameters.put("P_TO_DATE",  newConvertStringToJboDate(ToDate));
                    parameters.put("P_CREATED_BY", EmpCode);
                    parameters.put("P_SESSION_ID", SID);
                }
            if(RepName.equals("SAOD")){
                
                     parameters.put("p_unit", UnitCode);
                     if(LocCode.equalsIgnoreCase("ALL"))
                     {
                         LocCode = "%";
                     }
                     else{
                             LocCode = LocCode.split(",")[0];
                             parameters.put("p_loc", LocCode);
                         }
                    
                     parameters.put("p_as_on_dt",newConvertStringToJboDate(FromDate)  );
                 }
            
            if(RepName.equals("PSB")){
                     DocNo = DocNo.split(",")[0];
                     parameters.put("p_unit", UnitCode);
                     parameters.put("p_prod", DocNo);
                     parameters.put("p_reqQty",ReqQty);
                     parameters.put("p_user",EmpCode );
                     parameters.put("p_session_id",SID  );
                 }
            if(RepName.equals("STKV")){
                     DocNo = DocNo.split(",")[0];
                     parameters.put("p_unit", UnitCode);
                     parameters.put("p_loc", LocCode);
                     if(DocNo.equalsIgnoreCase("ALL")){
                     parameters.put("p_item", "%");
                     }else{
                     parameters.put("p_item", DocNo);
                     }
                     parameters.put("p_fr_dt",newConvertStringToJboDate(FromDate)  );
                     parameters.put("p_to_dt",  newConvertStringToJboDate(ToDate));
                     parameters.put("p_sid",SID  );
                 }
            System.out.println("callinf jasper reportName:" + reportName);
            callJasper(reportName, serverpath, parameters, conn, DocNo,RepFormat,response);


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    System.out.println("Ever Most finally connection closed");
                    conn.close();
//                    reportPathRsfile.close();
                    reportPathRs.close();
                
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
