package GUI;

import javax.swing.JOptionPane;

public class Reports_Admin extends javax.swing.JPanel {

    JpanelLoader jpload = new JpanelLoader();
    
    public Reports_Admin() {
        initComponents();
        btnReservations.doClick();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnReservations = new javax.swing.JToggleButton();
        btnCustomers = new javax.swing.JToggleButton();
        btnSuppliers = new javax.swing.JToggleButton();
        btnFinance = new javax.swing.JToggleButton();
        btnEmployees = new javax.swing.JToggleButton();
        btnFacilities = new javax.swing.JToggleButton();
        Reports_JPanel = new javax.swing.JPanel();

        jPanel7.setBackground(new java.awt.Color(255, 51, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setText("Reports Details");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(740, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(btnReservations);
        btnReservations.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnReservations.setText("Reservations");
        btnReservations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservationsActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnCustomers);
        btnCustomers.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnCustomers.setText("Customers");
        btnCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomersActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnSuppliers);
        btnSuppliers.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnSuppliers.setText("Suppliers");
        btnSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuppliersActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnFinance);
        btnFinance.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnFinance.setText("Finance");
        btnFinance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinanceActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnEmployees);
        btnEmployees.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnEmployees.setText("Employees");
        btnEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeesActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnFacilities);
        btnFacilities.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnFacilities.setText("Facilities");
        btnFacilities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacilitiesActionPerformed(evt);
            }
        });

        Reports_JPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout Reports_JPanelLayout = new javax.swing.GroupLayout(Reports_JPanel);
        Reports_JPanel.setLayout(Reports_JPanelLayout);
        Reports_JPanelLayout.setHorizontalGroup(
            Reports_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Reports_JPanelLayout.setVerticalGroup(
            Reports_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Reports_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnReservations)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCustomers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuppliers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEmployees)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFacilities)
                        .addGap(0, 447, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReservations)
                    .addComponent(btnCustomers)
                    .addComponent(btnSuppliers)
                    .addComponent(btnFinance)
                    .addComponent(btnEmployees)
                    .addComponent(btnFacilities))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Reports_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppliersActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Suppliers Reports_Suppliers = new Reports_Suppliers();
            jpload.jPanelLoader(Reports_JPanel, Reports_Suppliers);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, " Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuppliersActionPerformed

    private void btnReservationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservationsActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Reservations Reports_Reservations = new Reports_Reservations();
            jpload.jPanelLoader(Reports_JPanel, Reports_Reservations);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReservationsActionPerformed

    private void btnCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomersActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Customers Reports_Customers = new Reports_Customers();
            jpload.jPanelLoader(Reports_JPanel, Reports_Customers);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCustomersActionPerformed

    private void btnFinanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinanceActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Finance Reports_Inventory = new Reports_Finance();
            jpload.jPanelLoader(Reports_JPanel, Reports_Inventory);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFinanceActionPerformed

    private void btnEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeesActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Employees Reports_Employees = new Reports_Employees();
            jpload.jPanelLoader(Reports_JPanel, Reports_Employees);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEmployeesActionPerformed

    private void btnFacilitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacilitiesActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Facilities Reports_Facilities = new Reports_Facilities();
            jpload.jPanelLoader(Reports_JPanel, Reports_Facilities);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFacilitiesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Reports_JPanel;
    private javax.swing.JToggleButton btnCustomers;
    private javax.swing.JToggleButton btnEmployees;
    private javax.swing.JToggleButton btnFacilities;
    private javax.swing.JToggleButton btnFinance;
    private javax.swing.JToggleButton btnReservations;
    private javax.swing.JToggleButton btnSuppliers;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
