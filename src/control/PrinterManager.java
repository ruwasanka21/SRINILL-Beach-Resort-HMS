/**
 * GENIUS SOFT CONFIDENTIAL
 *
 * [2015] - [2015] Genius Soft (PVT) LTD
 *
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of Genius Soft (PVT) LTD and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Genius Soft (PVT) LTD and its
 * suppliers and may be covered by Sri Lanka and Foreign Patents, patents in process, and are protected by trade secret
 * or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Genius Soft (PVT) LTD.
 */
package control;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class PrinterManager {

    public static String A4_PRINTER = "EPSON-LX-300";
    public static String LQ_50_PRINTER = "EPSON-LQ-50";
    public static String POS_PRINTER = "POS-80C";
    public static String BARCODE_PRINTER = "ZDesigner GT800 (EPL)";

    public static final String CACHIER_PRINTER = PrinterManager.POS_PRINTER;
    public static String KOT_PRINTER ="KOT" ;
    public static String BOT_PRINTER = PrinterManager.POS_PRINTER;
    public static final String ORDER_PRINTER = PrinterManager.POS_PRINTER;

    private static String defaultPrinter = PrinterManager.BARCODE_PRINTER;

    /**
     * Get the value of defaultPrinter
     *
     * @return the value of defaultPrinter
     */
    public static String getDefaultPrinter() {
        return defaultPrinter;
    }

    /**
     * Set the value of defaultPrinter
     *
     * @param defaultPrinter new value of defaultPrinter
     */
    public static void setDefaultPrinter(String defaultPrinter) {
        PrinterManager.defaultPrinter = defaultPrinter;
    }

    private static void printReport(String reportSource, Map<String, Object> param, String printer) throws SQLException, JRException, PrinterException {

        String printerNameShort = printer;
        reportSource = "report/" + reportSource + ".jasper";
//        try {
//            param.put("shopName", new String(Options.getShopName().getBytes(), "UTF-8"));
//            param.put("address1", new String(Options.getAddress1().getBytes(), "UTF-8"));
//            param.put("address2", new String(Options.getAddress2().getBytes(), "UTF-8"));
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(CommonControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        param.put("telNo", Options.getTelNo());
        //Connection con = DatabaseConnection.getDatabaseConnection();
        JasperReport jr = (JasperReport) JRLoader.loadObject(new File(reportSource));
        JasperPrint jp = JasperFillManager.fillReport(jr, param, new JREmptyDataSource());

        printReportFromSelectePrinter(printerNameShort, jp);
    }

    public static boolean printReportFromSelectePrinter(JasperPrint jp) throws JRException, PrinterException {
        return printReportFromSelectePrinter(PrinterManager.defaultPrinter, jp, 1);
    }

    public static boolean printReportFromSelectePrinter(JasperPrint jp, int numberOfCopies) throws JRException, PrinterException {
        return printReportFromSelectePrinter(PrinterManager.defaultPrinter, jp, numberOfCopies);
    }

    public static boolean printReportFromSelectePrinter(String printerNameShort, JasperPrint jp) throws JRException, PrinterException {
        return printReportFromSelectePrinter(printerNameShort, jp, 1);
    }

    public static boolean printReportFromSelectePrinter(String printerNameShort, JasperPrint jp, int numberOfCopies) throws JRException, PrinterException {
        if (!jp.getPages().isEmpty()) {
            PrinterJob printerJob = PrinterJob.getPrinterJob();

//        PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
//        printerJob.defaultPage(pageFormat);
            int selectedService = 0;

            AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printerNameShort, null));

            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);

            try {
                printerJob.setPrintService(printService[selectedService]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new PrinterException();
            }

            JRPrintServiceExporter exporter;
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            //   printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
            printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 82, 3000, MediaPrintableArea.MM));
            printRequestAttributeSet.add(new Copies(numberOfCopies));

            // these are deprecated
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
            return true;
        } else {
            return false;
        }
    }


    public static void openCashDrawer() throws PrintException {
        byte[] open = {27, 112, 0, 25, 25};

        PrintServiceAttributeSet printserviceattributeset = new HashPrintServiceAttributeSet();
        printserviceattributeset.add(new PrinterName(POS_PRINTER, null));
        PrintService[] printservice = PrintServiceLookup.lookupPrintServices(null, printserviceattributeset);

        if (printservice.length != 1) {
            System.out.println("Printer not found");
            return;
        }

        PrintService pservice = printservice[0];
        DocPrintJob job = pservice.createPrintJob();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(open, flavor, null);
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        job.print(doc, aset);
    }

    public static void paperCutter() throws PrintException {
        byte[] cutter = {29, 86, 49};

        PrintServiceAttributeSet printserviceattributeset = new HashPrintServiceAttributeSet();
        printserviceattributeset.add(new PrinterName(POS_PRINTER, null));
        PrintService[] printservice = PrintServiceLookup.lookupPrintServices(null, printserviceattributeset);
        if (printservice.length != 1) {
            System.out.println("Printer not found");
        }
        PrintService pservice = printservice[0];
        DocPrintJob job = pservice.createPrintJob();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(cutter, flavor, null);
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        job.print(doc, aset);
    }
}
