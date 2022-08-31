package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

public class ARWiseItemStockLedger {
    public ARWiseItemStockLedger() {
    }
    private String jaspeReportName;
    
    
    public String getJaspeReportName() 
    {
        String path = ADFUtils.jasperReportFileName("PRD0000000104");
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
