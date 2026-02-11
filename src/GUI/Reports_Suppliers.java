package GUI;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Reports_Suppliers extends javax.swing.JPanel {

    public Reports_Suppliers() {
        initComponents();
        supLoad();
    }
    
    public void supLoad() {
        try {
            DefaultComboBoxModel<String> baseModel = new DefaultComboBoxModel<>();
            baseModel.addElement("-- Select --");

            try (Statement s = DatabaseLayer.mycon().createStatement();
                 ResultSet rs = s.executeQuery("SELECT supName FROM supplier ORDER BY supName")) {

                while (rs.next()) {
                    baseModel.addElement(rs.getString("supName"));
                }
            }
            DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>();

            for (int i = 0; i < baseModel.getSize(); i++) {
                String item = baseModel.getElementAt(i);
                model1.addElement(item);
                model2.addElement(item);
                model3.addElement(item);
            }
            ComboSup.setModel(model1);
            ComboSup.setSelectedIndex(0);

            ComboSup2.setModel(model2);
            ComboSup2.setSelectedIndex(0);
            
            ComboSup3.setModel(model3);
            ComboSup3.setSelectedIndex(0);

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
        btnAllSupDetails = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ComboSup = new javax.swing.JComboBox<>();
        btnView = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ComboSearch1 = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        ComboSup2 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        fromDate2 = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        toDate2 = new com.toedter.calendar.JDateChooser();
        btnSupPaid = new javax.swing.JButton();
        btnSupUnPaid = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btnAllSupPaid = new javax.swing.JButton();
        btnAllSupUnPaid = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        toDate = new com.toedter.calendar.JDateChooser();
        btnAllSupUnPaid_Date = new javax.swing.JButton();
        btnAllSupPaid_Date = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAllItemDetails = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        ComboSup3 = new javax.swing.JComboBox<>();
        btnView1 = new javax.swing.JButton();

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setRequestFocusEnabled(false);
        jPanel11.setVerifyInputWhenFocusTarget(false);

        btnAllSupDetails.setBackground(new java.awt.Color(255, 153, 102));
        btnAllSupDetails.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAllSupDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnAllSupDetails.setText("View All Supplier's Details");
        btnAllSupDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllSupDetailsActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Supplier :");

        ComboSup.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSupActionPerformed(evt);
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

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Status :");

        ComboSearch1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Paid", "Unpaid" }));
        ComboSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSearch1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(ComboSearch1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ComboSup, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAllSupDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAllSupDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSup, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnView)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setRequestFocusEnabled(false);
        jPanel13.setVerifyInputWhenFocusTarget(false);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Supplier :");

        ComboSup2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSup2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSup2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("From Date :");

        fromDate2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("To Date :");

        toDate2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        btnSupPaid.setBackground(new java.awt.Color(255, 204, 204));
        btnSupPaid.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnSupPaid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/paid.png"))); // NOI18N
        btnSupPaid.setText("Supplier's Paid PO");
        btnSupPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupPaidActionPerformed(evt);
            }
        });

        btnSupUnPaid.setBackground(new java.awt.Color(255, 204, 204));
        btnSupUnPaid.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnSupUnPaid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/unpaid.png"))); // NOI18N
        btnSupUnPaid.setText("Supplier's Unpaid PO");
        btnSupUnPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupUnPaidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSupPaid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSupUnPaid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboSup2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toDate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fromDate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboSup2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fromDate2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSupPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSupUnPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setRequestFocusEnabled(false);
        jPanel12.setVerifyInputWhenFocusTarget(false);

        btnAllSupPaid.setBackground(new java.awt.Color(255, 204, 204));
        btnAllSupPaid.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAllSupPaid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/paid.png"))); // NOI18N
        btnAllSupPaid.setText(" All Supplier's Paid PO");
        btnAllSupPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllSupPaidActionPerformed(evt);
            }
        });

        btnAllSupUnPaid.setBackground(new java.awt.Color(255, 204, 204));
        btnAllSupUnPaid.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAllSupUnPaid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/unpaid.png"))); // NOI18N
        btnAllSupUnPaid.setText(" All Supplier's Unpaid PO");
        btnAllSupUnPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllSupUnPaidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAllSupPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAllSupUnPaid, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAllSupPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAllSupUnPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel14.setRequestFocusEnabled(false);
        jPanel14.setVerifyInputWhenFocusTarget(false);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("From Date :");

        fromDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("To Date :");

        toDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        btnAllSupUnPaid_Date.setBackground(new java.awt.Color(255, 204, 204));
        btnAllSupUnPaid_Date.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAllSupUnPaid_Date.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/unpaid.png"))); // NOI18N
        btnAllSupUnPaid_Date.setText(" All Supplier's Unpaid PO");
        btnAllSupUnPaid_Date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllSupUnPaid_DateActionPerformed(evt);
            }
        });

        btnAllSupPaid_Date.setBackground(new java.awt.Color(255, 204, 204));
        btnAllSupPaid_Date.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAllSupPaid_Date.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/paid.png"))); // NOI18N
        btnAllSupPaid_Date.setText(" All Supplier's Paid PO");
        btnAllSupPaid_Date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllSupPaid_DateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAllSupPaid_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAllSupUnPaid_Date, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnAllSupPaid_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAllSupUnPaid_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fromDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setText("Inventory Details");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        btnAllItemDetails.setBackground(new java.awt.Color(255, 153, 102));
        btnAllItemDetails.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAllItemDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnAllItemDetails.setText("View All Item's Details");
        btnAllItemDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllItemDetailsActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Supplier :");

        ComboSup3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSup3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSup3ActionPerformed(evt);
            }
        });

        btnView1.setBackground(new java.awt.Color(255, 255, 102));
        btnView1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnView1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/eye.png"))); // NOI18N
        btnView1.setText("View");
        btnView1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAllItemDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSup3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnView1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAllItemDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboSup3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnView1))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ComboSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSupActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        String sup = (String) ComboSup.getSelectedItem();

        if (sup == null || sup.equals("-- Select --")) {
            JOptionPane.showMessageDialog(null, "Please select a supplier first.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HashMap<String, Object> SelectSup = new HashMap<>();
        SelectSup.put("selectSup", sup);

        try {
            ReportView r = new ReportView("src\\Reports\\Select_Suppliers_Details.jasper", SelectSup);
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnViewActionPerformed

    private void ComboSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSearch1ActionPerformed

    private void ComboSup2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSup2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSup2ActionPerformed

    private void btnAllSupDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllSupDetailsActionPerformed
        // TODO add your handling code here:
        try {
            ReportView r = new ReportView("src\\Reports\\All_Suppliers_Details.jasper");
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAllSupDetailsActionPerformed

    private void btnAllSupPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllSupPaidActionPerformed
        // TODO add your handling code here:
        try {
            ReportView r = new ReportView("src\\Reports\\All_Suppliers_Paid_PO.jasper");
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAllSupPaidActionPerformed

    private void btnAllSupUnPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllSupUnPaidActionPerformed
        // TODO add your handling code here:
        try {
            ReportView r = new ReportView("src\\Reports\\All_Suppliers_UnPaid_PO.jasper");
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAllSupUnPaidActionPerformed

    private void btnAllSupPaid_DateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllSupPaid_DateActionPerformed
        // TODO add your handling code here:
        Date fromdate = fromDate.getDate();
        Date todate = toDate.getDate();

        if (fromdate == null || todate == null) {
            JOptionPane.showMessageDialog(null, "Please select both From and To dates.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HashMap<String, Object> dateParams = new HashMap<>();
        dateParams.put("fromDate", fromdate);
        dateParams.put("toDate", todate);

        try {
            ReportView r = new ReportView("src\\Reports\\All_Suppliers_Paid_PO_Date.jasper", dateParams);
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAllSupPaid_DateActionPerformed

    private void btnAllSupUnPaid_DateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllSupUnPaid_DateActionPerformed
        // TODO add your handling code here:
        Date fromdate = fromDate.getDate();
        Date todate = toDate.getDate();

        if (fromdate == null || todate == null) {
            JOptionPane.showMessageDialog(null, "Please select both From and To dates.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HashMap<String, Object> dateParams = new HashMap<>();
        dateParams.put("fromDate", fromdate);
        dateParams.put("toDate", todate);

        try {
            ReportView r = new ReportView("src\\Reports\\All_Suppliers_UnPaid_PO_Date.jasper", dateParams);
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAllSupUnPaid_DateActionPerformed

    private void btnSupPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupPaidActionPerformed
        // TODO add your handling code here:
        String sup = (String) ComboSup2.getSelectedItem();

        if (sup == null || sup.equals("-- Select --")) {
            JOptionPane.showMessageDialog(null, "Please select a supplier first.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date fromdate = fromDate2.getDate();
        Date todate = toDate2.getDate();

        HashMap<String, Object> dateParams = new HashMap<>();
        dateParams.put("fromDate", fromdate);
        dateParams.put("toDate", todate);
        dateParams.put("selectSup", sup);

        try {
            ReportView r = new ReportView("src\\Reports\\Select_Suppliers_Paid_PO_Date.jasper", dateParams);
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSupPaidActionPerformed

    private void btnSupUnPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupUnPaidActionPerformed
        // TODO add your handling code here:
        String sup = (String) ComboSup2.getSelectedItem();

        if (sup == null || sup.equals("-- Select --")) {
            JOptionPane.showMessageDialog(null, "Please select a supplier first.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date fromdate = fromDate2.getDate();
        Date todate = toDate2.getDate();

        HashMap<String, Object> dateParams = new HashMap<>();
        dateParams.put("fromDate", fromdate);
        dateParams.put("toDate", todate);
        dateParams.put("selectSup", sup);

        try {
            ReportView r = new ReportView("src\\Reports\\Select_Suppliers_UnPaid_PO_Date.jasper", dateParams);
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSupUnPaidActionPerformed

    private void btnAllItemDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllItemDetailsActionPerformed
        // TODO add your handling code here:
        try {
            ReportView r = new ReportView("src\\Reports\\All_Item_Details.jasper");
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAllItemDetailsActionPerformed

    private void ComboSup3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSup3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSup3ActionPerformed

    private void btnView1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView1ActionPerformed
        // TODO add your handling code here:
        String sup = (String) ComboSup3.getSelectedItem();

        if (sup == null || sup.equals("-- Select --")) {
            JOptionPane.showMessageDialog(null, "Please select a supplier first.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HashMap<String, Object> SelectSup = new HashMap<>();
        SelectSup.put("selectSup", sup);

        try {
            ReportView r = new ReportView("src\\Reports\\Select_Item_Details.jasper", SelectSup);
            r.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnView1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboSearch1;
    private javax.swing.JComboBox<String> ComboSup;
    private javax.swing.JComboBox<String> ComboSup2;
    private javax.swing.JComboBox<String> ComboSup3;
    private javax.swing.JButton btnAllItemDetails;
    private javax.swing.JButton btnAllSupDetails;
    private javax.swing.JButton btnAllSupPaid;
    private javax.swing.JButton btnAllSupPaid_Date;
    private javax.swing.JButton btnAllSupUnPaid;
    private javax.swing.JButton btnAllSupUnPaid_Date;
    private javax.swing.JButton btnSupPaid;
    private javax.swing.JButton btnSupUnPaid;
    private javax.swing.JButton btnView;
    private javax.swing.JButton btnView1;
    private com.toedter.calendar.JDateChooser fromDate;
    private com.toedter.calendar.JDateChooser fromDate2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.toedter.calendar.JDateChooser toDate;
    private com.toedter.calendar.JDateChooser toDate2;
    // End of variables declaration//GEN-END:variables
}
