
package GUI;

import java.awt.Container;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import GUI.DatabaseLayer;

public class ReportView extends JFrame
{
    public ReportView(String fileName)
    {
        this(fileName, null);
        this.setLocationRelativeTo(null);
    }
    public ReportView(String fileName, HashMap para)
    {
        super("Hotel Management System (Report Viewer)");

        DatabaseLayer d = new DatabaseLayer();
        Connection con = d.mycon();
                
        try
        {
            JasperPrint print = JasperFillManager.fillReport(fileName, para, con);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);            
        } 
        catch (JRException jRException)
        {
            System.out.println(jRException);
        }
        setBounds(2, 2, 900, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
