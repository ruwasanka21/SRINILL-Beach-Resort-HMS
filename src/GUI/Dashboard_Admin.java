
package GUI;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Dashboard_Admin extends javax.swing.JFrame {

    JpanelLoader jpload = new JpanelLoader();
    public Dashboard_Admin() {
        initComponents();
        this.setExtendedState(Dashboard_Reception.MAXIMIZED_BOTH);

        updateDateTime();
        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
        //btnEmployee.doClick();
    }
    private void updateDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = sdf.format(new Date());

        DateLabel.setText(currentDateTime);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home_btn_grp = new javax.swing.ButtonGroup();
        Dashboard_panal = new javax.swing.JPanel();
        DateLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCustomer = new javax.swing.JToggleButton();
        btnSupplier = new javax.swing.JToggleButton();
        btnFinance = new javax.swing.JToggleButton();
        btnEmployee = new javax.swing.JToggleButton();
        btnSettings = new javax.swing.JToggleButton();
        btnRecords = new javax.swing.JToggleButton();
        btnFacilities = new javax.swing.JToggleButton();
        btnInventory = new javax.swing.JToggleButton();
        panal_load = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(355, 734));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Dashboard_panal.setBackground(new java.awt.Color(0, 130, 200));
        Dashboard_panal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Dashboard_panalMouseClicked(evt);
            }
        });
        Dashboard_panal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DateLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        DateLabel.setForeground(new java.awt.Color(204, 204, 204));
        Dashboard_panal.add(DateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 44, 419, 20));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SRINILL BEACH RESORT - BACK OFFICE");
        Dashboard_panal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 632, 30));

        jPanel1.setBackground(new java.awt.Color(0, 130, 200));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/emp.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setText("Sign Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("meshan");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 105, 20));

        Dashboard_panal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1225, 0, -1, 77));

        getContentPane().add(Dashboard_panal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 70));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home_btn_grp.add(btnCustomer);
        btnCustomer.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/customer.png"))); // NOI18N
        btnCustomer.setText("Customer");
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });
        jPanel2.add(btnCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 10, 145, 47));

        home_btn_grp.add(btnSupplier);
        btnSupplier.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/supplier.png"))); // NOI18N
        btnSupplier.setText("Supplier");
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        jPanel2.add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 65, 145, 47));

        home_btn_grp.add(btnFinance);
        btnFinance.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnFinance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/finance.png"))); // NOI18N
        btnFinance.setText("Finance");
        btnFinance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinanceActionPerformed(evt);
            }
        });
        jPanel2.add(btnFinance, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 175, 145, 47));

        home_btn_grp.add(btnEmployee);
        btnEmployee.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/emp.png"))); // NOI18N
        btnEmployee.setText("Employee");
        btnEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeActionPerformed(evt);
            }
        });
        jPanel2.add(btnEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 120, 145, 47));

        home_btn_grp.add(btnSettings);
        btnSettings.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/settings.png"))); // NOI18N
        btnSettings.setText("Settings");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        jPanel2.add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 395, 145, 47));

        home_btn_grp.add(btnRecords);
        btnRecords.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/reports.png"))); // NOI18N
        btnRecords.setText("Reports");
        btnRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordsActionPerformed(evt);
            }
        });
        jPanel2.add(btnRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 340, 145, 47));

        home_btn_grp.add(btnFacilities);
        btnFacilities.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnFacilities.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnFacilities.setText("Facilities");
        btnFacilities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacilitiesActionPerformed(evt);
            }
        });
        jPanel2.add(btnFacilities, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 285, 145, 47));

        home_btn_grp.add(btnInventory);
        btnInventory.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/inventory.png"))); // NOI18N
        btnInventory.setText("Inventory");
        btnInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventoryActionPerformed(evt);
            }
        });
        jPanel2.add(btnInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 230, 145, 47));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 78, 160, 597));

        panal_load.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panal_load.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panal_load, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 78, 1177, 597));

        jLabel6.setText("© Software by Code Breakers  |  TEL : 0764315233  |  Email : codebreackers@gmail.com  |  License ID : 100476");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 678, 762, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        // TODO add your handling code here:
        try {
            Customer Customer = new Customer();
            jpload.jPanelLoader(panal_load, Customer);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        // TODO add your handling code here:
        try {
            Supplier Supplier = new Supplier();
            jpload.jPanelLoader(panal_load, Supplier);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnFinanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinanceActionPerformed
        // TODO add your handling code here:
        try {
            Finance Finance = new Finance();
            jpload.jPanelLoader(panal_load, Finance);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFinanceActionPerformed

    private void btnEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeActionPerformed
        // TODO add your handling code here:
        try {
            Employee emp = new Employee();
            jpload.jPanelLoader(panal_load, emp);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEmployeeActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        // TODO add your handling code here:
        try {
            Settings_Admin Settings = new Settings_Admin();
            jpload.jPanelLoader(panal_load, Settings);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordsActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Admin Records = new Reports_Admin();
            jpload.jPanelLoader(panal_load, Records);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRecordsActionPerformed

    private void btnFacilitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacilitiesActionPerformed
        // TODO add your handling code here:
        try {
            Facilities fac = new Facilities();
            jpload.jPanelLoader(panal_load, fac);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFacilitiesActionPerformed

    private void btnInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryActionPerformed
        // TODO add your handling code here:
        try {
            Inventory Inventory = new Inventory();
            jpload.jPanelLoader(panal_load, Inventory);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnInventoryActionPerformed

    private void Dashboard_panalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Dashboard_panalMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Dashboard_Admin().setVisible(true);
    }//GEN-LAST:event_Dashboard_panalMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
            this, // parent component
            "Are you sure you want to sign out?",
            "Confirm Sign Out",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE 
        );

        if (response == JOptionPane.YES_OPTION) {
            new Home().setVisible(true);
            this.dispose();
        } else {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboard_panal;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JToggleButton btnCustomer;
    private javax.swing.JToggleButton btnEmployee;
    private javax.swing.JToggleButton btnFacilities;
    private javax.swing.JToggleButton btnFinance;
    private javax.swing.JToggleButton btnInventory;
    private javax.swing.JToggleButton btnRecords;
    private javax.swing.JToggleButton btnSettings;
    private javax.swing.JToggleButton btnSupplier;
    private javax.swing.ButtonGroup home_btn_grp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panal_load;
    // End of variables declaration//GEN-END:variables
}
