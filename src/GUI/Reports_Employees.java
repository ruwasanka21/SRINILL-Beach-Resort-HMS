
package GUI;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Reports_Employees extends javax.swing.JPanel {

    public Reports_Employees() {
        initComponents();
        empLoad();
    }
    public void empLoad() {
        try {
            DefaultComboBoxModel<String> baseModel = new DefaultComboBoxModel<>();
            baseModel.addElement("-- Select --");

            try (Statement s = DatabaseLayer.mycon().createStatement();
                 ResultSet rs = s.executeQuery("SELECT Name FROM employees ORDER BY Name")) {

                while (rs.next()) {
                    baseModel.addElement(rs.getString("Name"));
                }
            }
            DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();

            for (int i = 0; i < baseModel.getSize(); i++) {
                String item = baseModel.getElementAt(i);
                model1.addElement(item);
            }
            ComboEmp.setModel(model1);
            ComboEmp.setSelectedIndex(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnAllEmpDetails = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ComboEmp = new javax.swing.JComboBox<>();
        btnView = new javax.swing.JButton();

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAllEmpDetails.setBackground(new java.awt.Color(255, 153, 102));
        btnAllEmpDetails.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAllEmpDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnAllEmpDetails.setText("View All Employees's Details");
        btnAllEmpDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllEmpDetailsActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Employee :");

        ComboEmp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboEmpActionPerformed(evt);
            }
        });

        btnView.setBackground(new java.awt.Color(255, 255, 102));
        btnView.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/eye.png"))); // NOI18N
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAllEmpDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboEmp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(btnAllEmpDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(558, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(318, Short.MAX_VALUE))
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

    private void btnAllEmpDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllEmpDetailsActionPerformed
        // TODO add your handling code here:
        try {
            ReportView r = new ReportView("src\\Reports\\All_Employees_Details.jasper");
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAllEmpDetailsActionPerformed

    private void ComboEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboEmpActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        String emp = (String) ComboEmp.getSelectedItem();

        if (emp == null || emp.equals("-- Select --")) {
            JOptionPane.showMessageDialog(null, "Please select a Employee first.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HashMap<String, Object> SelectEmp = new HashMap<>();
        SelectEmp.put("selectEmp", emp);

        try {
            ReportView r = new ReportView("src\\Reports\\Select_Employee_Details.jasper", SelectEmp);
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboEmp;
    private javax.swing.JButton btnAllEmpDetails;
    private javax.swing.JButton btnView;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    // End of variables declaration//GEN-END:variables
}
