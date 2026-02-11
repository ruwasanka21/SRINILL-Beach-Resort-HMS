
package GUI;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Reports_Finance extends javax.swing.JPanel {

    public Reports_Finance() {
        initComponents();
        DayLoad();
        MonthLoad();
        YearLoad();
    }
    public void DayLoad() {
    try {
        DefaultComboBoxModel<String> com = new DefaultComboBoxModel<>();
        com.addElement("-- Select Day --");

        for (int i = 1; i <= 31; i++) {
            com.addElement(String.valueOf(i));
        }

        ComboDay.setModel(com);
        ComboDay.setSelectedIndex(0);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading days: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    public void MonthLoad() { 
        try {
            DefaultComboBoxModel<String> com = new DefaultComboBoxModel<>();
            com.addElement("-- Select Month --");

            String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            };

            for (String month : months) {
                com.addElement(month);
            }

            ComboMonth.setModel(com);
            ComboMonth.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading months: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void YearLoad() { 
    try {
        DefaultComboBoxModel<String> com = new DefaultComboBoxModel<>();
        com.addElement("-- Select Year --");

        for (int year = 2022; year <= 2028; year++) {
            com.addElement(String.valueOf(year));
        }

        ComboYear.setModel(com);
        ComboYear.setSelectedIndex(4);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading years: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnIncome = new javax.swing.JButton();
        btnExpense = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        ComboYear = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        ComboMonth = new javax.swing.JComboBox<>();
        btnIncome3 = new javax.swing.JButton();
        btnExpense1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        ComboDay = new javax.swing.JComboBox<>();

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnIncome.setBackground(new java.awt.Color(255, 153, 102));
        btnIncome.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnIncome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnIncome.setText("View All Income Details");
        btnIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncomeActionPerformed(evt);
            }
        });

        btnExpense.setBackground(new java.awt.Color(102, 102, 255));
        btnExpense.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnExpense.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnExpense.setText("View All Expense Details");
        btnExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpenseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExpense, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(btnIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Yealy :");

        ComboYear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Monthly :");

        ComboMonth.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        btnIncome3.setBackground(new java.awt.Color(255, 153, 102));
        btnIncome3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnIncome3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnIncome3.setText("View All Income Details");
        btnIncome3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncome3ActionPerformed(evt);
            }
        });

        btnExpense1.setBackground(new java.awt.Color(102, 102, 255));
        btnExpense1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnExpense1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnExpense1.setText("View All Expense Details");
        btnExpense1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpense1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Daily :");

        ComboDay.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnIncome3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExpense1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboDay, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboDay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncome3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(322, 322, 322))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(271, Short.MAX_VALUE))
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

    private void btnIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncomeActionPerformed
        // TODO add your handling code here:
        try {
            ReportView r = new ReportView("src\\Reports\\All_Income.jasper");
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnIncomeActionPerformed

    private void btnExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpenseActionPerformed
        // TODO add your handling code here:
        try {
            ReportView r = new ReportView("src\\Reports\\All_Expense.jasper");
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnExpenseActionPerformed

    private void btnExpense1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpense1ActionPerformed
        // TODO add your handling code here:
        String yearStr = (String) ComboYear.getSelectedItem();
        String monthStr = (String) ComboMonth.getSelectedItem();
        String dayStr = (String) ComboDay.getSelectedItem();

        try {
            int year = Integer.parseInt(yearStr);
            int month = 1;
            int day = 1; 

            String filterType = "yearly";

            if (monthStr != null && !monthStr.equals("-- Select Month --")) {
                month = convertMonthNameToNumber(monthStr);
                filterType = "monthly";
            }

            if (dayStr != null && !dayStr.equals("-- Select Day --")) {
                day = Integer.parseInt(dayStr);
                filterType = "daily";
            }
            LocalDate selectedDate = LocalDate.of(year, month, day);
            Date reportDate = java.sql.Date.valueOf(selectedDate);

            HashMap<String, Object> dateParams = new HashMap<>();
            dateParams.put("SelectDate", reportDate);
            dateParams.put("filterType", filterType);

            ReportView r = new ReportView("src\\Reports\\Filter_Expense.jasper", dateParams);
            r.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnExpense1ActionPerformed

    private void btnIncome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncome3ActionPerformed
        // TODO add your handling code here:
        String yearStr = (String) ComboYear.getSelectedItem();
        String monthStr = (String) ComboMonth.getSelectedItem();
        String dayStr = (String) ComboDay.getSelectedItem();

        try {
            int year = Integer.parseInt(yearStr);
            int month = 1;
            int day = 1; 

            String filterType = "yearly";

            if (monthStr != null && !monthStr.equals("-- Select Month --")) {
                month = convertMonthNameToNumber(monthStr);
                filterType = "monthly";
            }

            if (dayStr != null && !dayStr.equals("-- Select Day --")) {
                day = Integer.parseInt(dayStr);
                filterType = "daily";
            }
            LocalDate selectedDate = LocalDate.of(year, month, day);
            Date reportDate = java.sql.Date.valueOf(selectedDate);

            HashMap<String, Object> dateParams = new HashMap<>();
            dateParams.put("SelectDate", reportDate);
            dateParams.put("filterType", filterType);

            ReportView r = new ReportView("src\\Reports\\Filter_Income.jasper", dateParams);
            r.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnIncome3ActionPerformed
    
    //convert month text to integer
    private int convertMonthNameToNumber(String monthName) {
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("January", 1);
        monthMap.put("February", 2);
        monthMap.put("March", 3);
        monthMap.put("April", 4);
        monthMap.put("May", 5);
        monthMap.put("June", 6);
        monthMap.put("July", 7);
        monthMap.put("August", 8);
        monthMap.put("September", 9);
        monthMap.put("October", 10);
        monthMap.put("November", 11);
        monthMap.put("December", 12);

        Integer number = monthMap.get(monthName);
        return number != null ? number : 1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboDay;
    private javax.swing.JComboBox<String> ComboMonth;
    private javax.swing.JComboBox<String> ComboYear;
    private javax.swing.JButton btnExpense;
    private javax.swing.JButton btnExpense1;
    private javax.swing.JButton btnIncome;
    private javax.swing.JButton btnIncome3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
