
package GUI;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Reports_Reservations extends javax.swing.JPanel {

    public Reports_Reservations() {
        initComponents();
        loadBookingChart();
        loadIncomeChart();
        
        // Group radio buttons here
        ButtonGroup group = new ButtonGroup();
        group.add(roomResv);
        group.add(hallReserve);

        roomResv.setSelected(true);
        jLabel4.setOpaque(true);
    }
    
    private void loadBookingChart() {
    try {
        // Clear existing content in jPanel12
        jPanel12.removeAll();
        jPanel12.revalidate();
        jPanel12.repaint();

        // Prepare dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        HashMap<Integer, Integer> bookingsPerMonth = new HashMap<>();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/panorama_hotel", "root", "");
        Statement stmt = conn.createStatement();

        /// Get hall bookings grouped by month
        ResultSet rs1 = stmt.executeQuery("SELECT MONTH(STR_TO_DATE(reserve_Date, '%Y-%m-%d')) AS month, COUNT(*) AS count FROM hallreservation GROUP BY month");
        while (rs1.next()) {
            int month = rs1.getInt("month");
            int count = rs1.getInt("count");
            bookingsPerMonth.put(month, bookingsPerMonth.getOrDefault(month, 0) + count);
        }

        // Get room bookings grouped by month
        ResultSet rs2 = stmt.executeQuery("SELECT MONTH(reserve_Date) AS month, COUNT(*) AS count FROM roomreservation GROUP BY month");
        while (rs2.next()) {
            int month = rs2.getInt("month");
            int count = rs2.getInt("count");
            bookingsPerMonth.put(month, bookingsPerMonth.getOrDefault(month, 0) + count);
        }

        conn.close();

        // Month names for display
        String[] monthNames = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };

        // Populate dataset
        for (int i = 1; i <= 12; i++) {
            int count = bookingsPerMonth.getOrDefault(i, 0);
            dataset.addValue(count, "Bookings", monthNames[i - 1]);
        }

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Monthly Bookings",       // Chart title
            "Month",                  // X-axis label
            "Total Bookings",         // Y-axis label
            dataset,                  // Data
            PlotOrientation.VERTICAL, // Orientation
            true,                     // Include legend
             true,                     // Tooltips
            false                     // URLs
        );
        
        if (chart == null) {
            JOptionPane.showMessageDialog(this, "Chart creation failed.");
         return;
        } else {
            
        }


        // Add chart to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(jPanel12.getSize());
        jPanel12.setLayout(new java.awt.BorderLayout());
        jPanel12.add(chartPanel, java.awt.BorderLayout.CENTER);
        jPanel12.validate();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error generating chart: " + ex.getMessage());
    }
}
    
    private void loadIncomeChart() {
    try {
        // Clear previous chart
        jPanel13.removeAll();
        jPanel13.revalidate();
        jPanel13.repaint();

        // Create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // DB Connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/panorama_hotel", "root", "");
        Statement stmt = conn.createStatement();

        // Get Hall Income
        ResultSet rs1 = stmt.executeQuery("SELECT SUM(price) AS total_income FROM hallreservation");
        if (rs1.next()) {
            double hallIncome = rs1.getDouble("total_income");
            dataset.addValue(hallIncome, "Income", "Hall");
        }

        // Get Room Income
        ResultSet rs2 = stmt.executeQuery("SELECT SUM(price) AS total_income FROM roomreservation");
        if (rs2.next()) {
            double roomIncome = rs2.getDouble("total_income");
            dataset.addValue(roomIncome, "Income", "Room");
        }

        conn.close();

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Total Income by Category",
            "Category",
            "Income (LKR)",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        // Embed chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(jPanel13.getSize());
        jPanel13.setLayout(new BorderLayout());
        jPanel13.add(chartPanel, BorderLayout.CENTER);
        jPanel13.validate();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading income chart: " + e.getMessage());
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMonthChooser2 = new com.toedter.calendar.JMonthChooser();
        btnReserv = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        roomResv = new javax.swing.JRadioButton();
        hallReserve = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMonthChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jMonthChooser1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("From:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("To:");

        jMonthChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jMonthChooser2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnReserv.setBackground(new java.awt.Color(255, 204, 204));
        btnReserv.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnReserv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/reports.png"))); // NOI18N
        btnReserv.setText("Generate Report");
        btnReserv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 204, 204));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        jLabel4.setText("View Reservations");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 102), 1, true));

        roomResv.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        roomResv.setText("Room Reservation");

        hallReserve.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        hallReserve.setText("Hall Reservation");
        hallReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallReserveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(38, 38, 38)
                                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roomResv)
                                    .addComponent(hallReserve))))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnReserv, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roomResv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hallReserve))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnReserv)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservActionPerformed
        // TODO add your handling code here:
        
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/panorama_hotel", "root", "");

        int startMonth = jMonthChooser1.getMonth() + 1;
        int endMonth = jMonthChooser2.getMonth() + 1;

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("fromMonth", startMonth);
        parameters.put("toMonth", endMonth);

        // Path to your compiled Jasper file
        String reportPath = "";
        
         if (hallReserve.isSelected()) {
            reportPath = "src/Reports/HallReserv.jasper";
        } else if (roomResv.isSelected()) {
            reportPath = "src/Reports/RoomReserv.jasper";
        } else {
            JOptionPane.showMessageDialog(this, "Please select a reservation type.");
            return;
        }

        JasperPrint jp = JasperFillManager.fillReport(reportPath, parameters, conn);

       
         JasperViewer.viewReport(jp, false);

        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading report: " + e.getMessage());
        e.printStackTrace();
    }

    }//GEN-LAST:event_btnReservActionPerformed

    private void hallReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallReserveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hallReserveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReserv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton hallReserve;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JRadioButton roomResv;
    // End of variables declaration//GEN-END:variables
}
