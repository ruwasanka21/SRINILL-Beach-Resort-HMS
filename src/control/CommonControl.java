/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author ASUS
 */
import static control.CommonControl.printReport;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.DataObject;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import static net.sf.jasperreports.engine.JasperPrintManager.printReport;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CommonControl {

    /**
     * Load data from a ResultSet into a JComboBox
     *
     * @param comboBox The JComboBox to fill
     * @param rst The ResultSet containing data
     * @param attribute The column name to fetch from ResultSet
     */
    public static void loadDataToComboBox(JComboBox<String> comboBox, ResultSet rst, String attribute) {
        try {
            comboBox.removeAllItems(); // clear existing items
            while (rst.next()) {
                comboBox.addItem(rst.getString(attribute));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadDataObjectsIntoComboBox(JComboBox comboBox, ResultSet rst, String[] columnList,
            String defaultAttribute, boolean clearCombobox) throws SQLException {

        if (clearCombobox) {
            comboBox.removeAllItems();
        }

        while (rst.next()) {
            DataObject object = new DataObject(defaultAttribute);
            for (int i = 0; i < columnList.length; i++) {
                object.addProperty(columnList[i], rst.getString(columnList[i]));
            }
            comboBox.addItem(object);
        }
    }

    public static void loadDataToTable(JTable table, ResultSet rst, String[] columnList) throws SQLException {
        DefaultTableModel dtm1 = (DefaultTableModel) table.getModel();
        int rw = dtm1.getRowCount();
        for (int i = 0; i < rw; i++) {
            dtm1.removeRow(0);
        }
        while (rst.next()) {
            Object[] rowCells = new Object[columnList.length];
            for (int i = 0; i < columnList.length; i++) {

                if (!Validation.isNotEmpty(columnList[i])) {
                    continue;
                }

                rowCells[i] = rst.getString(columnList[i]);
            }
            dtm1.addRow(rowCells);
        }
        rst.getStatement().close();
        rst.close();
    }

    public static void printReport(String reporName, Map<String, Object> param, PrintStatus status)
            throws JRException, SQLException {

        String reportSource = "report/" + reporName + ".jasper";

        if (param == null) {
            param = new HashMap<>();
        }
        Connection con = DatabaseLayer.getConnection();
        JasperReport jr = (JasperReport) JRLoader.loadObject(new File(reportSource));
        JasperPrint jp = JasperFillManager.fillReport(jr, param, con);

        try {
            if (Validation.isJasperReportNotEmpty(jp)) {
                boolean emailReport = false;
                switch (status) {
                    case ONE_COPY_AND_EMAIL:
                        status = PrintStatus.ONE_COPY;
                        emailReport = true;
                        break;

                    case TWO_COPIES_AND_EMAIL:
                        status = PrintStatus.TWO_COPIES;
                        emailReport = true;
                        break;

                    case WITH_VIEW_AND_EMAIL:
                        status = PrintStatus.WITH_VIEW;
                        emailReport = true;
                        break;
                }

                jasperPrint(jp, status);

            } else {
                JOptionPane.showMessageDialog(null, "Report has no pages!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Printer connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void jasperPrint(JasperPrint jp, PrintStatus status) throws JRException, PrinterException {
        jasperPrint(jp, status, true, null);
    }

    public static void jasperPrint(JasperPrint jp, PrintStatus status, boolean withView, String exportFileNameWithPath)
            throws JRException, PrinterException {

        if (Validation.isJasperReportNotEmpty(jp)) {

            if (withView) {
                switch (status) {
                    case ONE_COPY:
                        PrinterManager.printReportFromSelectePrinter(PrinterManager.POS_PRINTER, jp);
                        break;

                    case TWO_COPIES:
                        PrinterManager.printReportFromSelectePrinter(PrinterManager.POS_PRINTER, jp, 2);
                        break;

                    case SAVE_PDF:
                        JasperExportManager.exportReportToPdfFile(jp, exportFileNameWithPath);
                        break;

                    default:
                        JasperViewer.viewReport(jp, false); //for jasper view

                }

            } else {
                switch (status) {
                    case ONE_COPY:
                        JasperPrintManager.printReport(jp, false);   //No jasper view
                        break;

                    case TWO_COPIES:
                        JasperPrintManager.printReport(jp, false);   //No jasper view
                        JasperPrintManager.printReport(jp, false);   //No jasper view
                        break;

                    case SAVE_PDF:
                        JasperExportManager.exportReportToPdfFile(jp, exportFileNameWithPath);
                        break;

                    default:
                        JasperViewer.viewReport(jp, false); //for jasper view

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Report has no pages in jasperPrint!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static void printReportNew(String reportName, Map<String, Object> params, PrintStatus status)
            throws JRException, SQLException, FileNotFoundException {

        // 1. Load the Jasper report file (.jasper) from resources
        //String reportPath = "report/" + reportName + ".jasper"; // adjust path as needed
        String reportPath = "C:/Users/ASUS/Documents/NetBeansProjects/Srinill-Beach-Resort/report/" + reportName + ".jasper";
        InputStream reportStream = new FileInputStream(reportPath);
        //InputStream reportStream = CommonControl.class.getClassLoader().getResourceAsStream(reportPath);

        if (reportStream == null) {
            throw new JRException("Report file not found: " + reportPath);
        }

        // 2. Fill the report with parameters and database connection
        Connection con = null;
        try {
            con = DatabaseLayer.getConnection(); // your method to get DB connection
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, con);

            // 3. Handle viewing or printing based on PrintStatus
            switch (status) {
                case WITH_VIEW:
                    // Show the report in JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false); // false = not exit on close
                    viewer.setVisible(true);
                    break;

                case ONE_COPY:
                    // Send the report directly to printer
                    JasperPrintManager.printReport(jasperPrint, true);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown PrintStatus: " + status);
            }
        } finally {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            if (reportStream != null) {
                try {
                    reportStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
