package GUI;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Finance extends javax.swing.JPanel {

    private Finance income_tb_load;
    public int count = 0;
    
    public Finance() {
        initComponents();
        income_tb_load = this;
        income_table.getColumnModel().getColumn(2).setPreferredWidth(130);
        tb_load();
        tb_load_expense();
        tb_load_Dashboard();
        doubleClicked_income();
        doubleClicked_expense();
    }
    
    public void tb_load() {
        try {
            DefaultTableModel dt =  (DefaultTableModel) income_table.getModel();
            dt.setRowCount(0);
            
            Statement s =  DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM income ORDER BY ID");
            int count = 1;
            double total = 0.0;
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(count));
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));          
                dt.addRow(v);
                total += Double.parseDouble(rs.getString(4));
                count++;
            }
            
            txtTotalIncome.setText(String.format("%.2f", total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void tb_load_expense() {
        try {
            DefaultTableModel dt =  (DefaultTableModel) expense_table.getModel();
            dt.setRowCount(0);
            
            Statement s =  DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM expense ORDER BY ID");
            int count = 1;
            double total = 0.0;
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(count));
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));          
                dt.addRow(v);
                total += Double.parseDouble(rs.getString(4));
                count++;
            }
            
            txtTotalIncome1.setText(String.format("%.2f", total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void tb_load_Dashboard() {
        count = 1;
        try {
            DefaultTableModel dt =  (DefaultTableModel) finance_dash_table.getModel();
            dt.setRowCount(0);
            
            Statement s =  DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM income ORDER BY ID");
            double total = 0.0;
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(count));
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(""); 
                v.add(rs.getString(5));
                dt.addRow(v);
                total += Double.parseDouble(rs.getString(4));
                count++;
            }
            
            txtIncome_Dashboard.setText(String.format("%.2f", total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        try {
            DefaultTableModel dt =  (DefaultTableModel) finance_dash_table.getModel();
            
            Statement s =  DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM expense ORDER BY ID");
            double total = 0.0;
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(count));
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(""); 
                v.add(rs.getString(4));
                v.add(rs.getString(5));  
                dt.addRow(v);
                total += Double.parseDouble(rs.getString(4));
                count++;
            }
            
            txtExpense_Dashboard.setText(String.format("%.2f", total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        double income = Double.parseDouble(txtIncome_Dashboard.getText());
        double expense = Double.parseDouble(txtExpense_Dashboard.getText());
        double profit = income - expense;
        txtProfit_Dashboard.setText(String.valueOf(profit));
    }
    
    public void doubleClicked_income() {
        income_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { 
                    int rownumber = income_table.getSelectedRow();
                    if (rownumber != -1) {
                        String eventID = income_table.getValueAt(rownumber, 1).toString();
                        Finance_Income_Select_Popup Finance_Income_Select_Popup = new Finance_Income_Select_Popup(income_tb_load, eventID);
                        Finance_Income_Select_Popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        Finance_Income_Select_Popup.setVisible(true);
                    }
                }
            }
        });
    }
    
    public void doubleClicked_expense() {
        expense_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { 
                    int rownumber = expense_table.getSelectedRow();
                    if (rownumber != -1) {
                        String eventID = expense_table.getValueAt(rownumber, 1).toString();
                        Finance_Expense_Select_Popup Finance_Expense_Select_Popup = new Finance_Expense_Select_Popup(income_tb_load, eventID);
                        Finance_Expense_Select_Popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        Finance_Expense_Select_Popup.setVisible(true);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        ComboSearch_Dashboard = new javax.swing.JComboBox<>();
        txtSearch_Dashboard = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ComboSort_Dashboard = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        finance_dash_table = new javax.swing.JTable();
        txtProfit_Dashboard = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtExpense_Dashboard = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIncome_Dashboard = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        income_table = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        ComboSearch_Income = new javax.swing.JComboBox<>();
        txtSearch_Income = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        ComboSort_Income = new javax.swing.JComboBox<>();
        btnAdd_Income = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTotalIncome = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        ComboSearch_expense = new javax.swing.JComboBox<>();
        txtSearch_expense = new javax.swing.JTextField();
        btnSearch2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        ComboSort_expense = new javax.swing.JComboBox<>();
        btnAdd_expense = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        expense_table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTotalIncome1 = new javax.swing.JTextField();

        jPanel7.setBackground(new java.awt.Color(255, 153, 102));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Finance Details");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane6.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Search By :");

        ComboSearch_Dashboard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch_Dashboard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name" }));
        ComboSearch_Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSearch_DashboardActionPerformed(evt);
            }
        });

        txtSearch_Dashboard.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtSearch_Dashboard.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch_DashboardKeyReleased(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/search x30.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Sort By :");

        ComboSort_Dashboard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSort_Dashboard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID (Min - Max)", "ID (Max - Min)", "Name (A - Z)", "Name (Z - A)", "LKR (Min - Max)", "LKR (Max - Min)" }));
        ComboSort_Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSort_DashboardActionPerformed(evt);
            }
        });

        finance_dash_table.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        finance_dash_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID", "Name", "Description", "Income", "Expense", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        finance_dash_table.setRowHeight(29);
        jScrollPane1.setViewportView(finance_dash_table);

        txtProfit_Dashboard.setEditable(false);
        txtProfit_Dashboard.setBackground(new java.awt.Color(255, 255, 153));
        txtProfit_Dashboard.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        txtProfit_Dashboard.setForeground(new java.awt.Color(0, 153, 0));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Total Profit :");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Total Expenses :");

        txtExpense_Dashboard.setEditable(false);
        txtExpense_Dashboard.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        txtExpense_Dashboard.setForeground(new java.awt.Color(0, 0, 153));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Total Income :");

        txtIncome_Dashboard.setEditable(false);
        txtIncome_Dashboard.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        txtIncome_Dashboard.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSearch_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSort_Dashboard, 0, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIncome_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpense_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtProfit_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ComboSort_Dashboard)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSearch_Dashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboSearch_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpense_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncome_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProfit_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane6.addTab("Dashboard", jPanel3);

        income_table.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        income_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID", "Name", "Description", "LKR", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        income_table.setRowHeight(28);
        jScrollPane2.setViewportView(income_table);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Search By :");

        ComboSearch_Income.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch_Income.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name" }));
        ComboSearch_Income.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSearch_IncomeActionPerformed(evt);
            }
        });

        txtSearch_Income.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtSearch_Income.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch_IncomeKeyReleased(evt);
            }
        });

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/search x30.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Sort By :");

        ComboSort_Income.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSort_Income.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID (Min - Max)", "ID (Max - Min)", "Name (A - Z)", "Name (Z - A)", "LKR (Min - Max)", "LKR (Max - Min)" }));
        ComboSort_Income.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSort_IncomeActionPerformed(evt);
            }
        });

        btnAdd_Income.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAdd_Income.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/add.png"))); // NOI18N
        btnAdd_Income.setText("Add");
        btnAdd_Income.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_IncomeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Total Income:");

        txtTotalIncome.setEditable(false);
        txtTotalIncome.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        txtTotalIncome.setForeground(new java.awt.Color(0, 51, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSearch_Income, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch_Income, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSort_Income, 0, 187, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd_Income, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSearch_Income, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboSearch_Income, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAdd_Income)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboSort_Income)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane6.addTab("Income", jPanel4);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Search By :");

        ComboSearch_expense.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch_expense.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name" }));
        ComboSearch_expense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSearch_expenseActionPerformed(evt);
            }
        });

        txtSearch_expense.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtSearch_expense.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch_expenseKeyReleased(evt);
            }
        });

        btnSearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/search x30.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Sort By :");

        ComboSort_expense.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSort_expense.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID (Min - Max)", "ID (Max - Min)", "Name (A - Z)", "Name (Z - A)", "LKR (Min - Max)", "LKR (Max - Min)" }));
        ComboSort_expense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSort_expenseActionPerformed(evt);
            }
        });

        btnAdd_expense.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAdd_expense.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/add.png"))); // NOI18N
        btnAdd_expense.setText("Add");
        btnAdd_expense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_expenseActionPerformed(evt);
            }
        });

        expense_table.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        expense_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID", "Name", "Description", "LKR", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        expense_table.setRowHeight(28);
        jScrollPane3.setViewportView(expense_table);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Total Expenses:");

        txtTotalIncome1.setEditable(false);
        txtTotalIncome1.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        txtTotalIncome1.setForeground(new java.awt.Color(0, 51, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSearch_expense, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch_expense, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSort_expense, 0, 187, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd_expense, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSearch_expense, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboSearch_expense, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAdd_expense)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboSort_expense)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane6.addTab("Expense", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6)
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

    private void ComboSearch_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSearch_DashboardActionPerformed
        // TODO add your handling code here:
        txtSearch_Dashboard.requestFocusInWindow();
    }//GEN-LAST:event_ComboSearch_DashboardActionPerformed

    private void txtSearch_DashboardKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch_DashboardKeyReleased
        // TODO add your handling code here:
        String searchBy = (String) ComboSearch_Dashboard.getSelectedItem();
        String columnName = "";

        switch (searchBy) {
            case "ID":
                columnName = "ID";
                break;
            case "Name":
                columnName = "Name";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid search category!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        String search = txtSearch_Dashboard.getText().trim();

        try {
            DefaultTableModel incomeModel = (DefaultTableModel) finance_dash_table.getModel();
            incomeModel.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            ResultSet rsIncome = s.executeQuery("SELECT * FROM income WHERE " + columnName + " LIKE '%" + search + "%'");
            int incomeCount = 1;

            while (rsIncome.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(incomeCount));     
                v.add(rsIncome.getString("ID"));       
                v.add(rsIncome.getString("Name"));        
                v.add(rsIncome.getString("Des")); 
                v.add(rsIncome.getString("LKR"));       
                v.add("");  
                v.add(rsIncome.getString("Date"));   
                incomeModel.addRow(v);
                incomeCount++;
            }

            DefaultTableModel expenseModel = (DefaultTableModel) finance_dash_table.getModel();

            ResultSet rsExpense = s.executeQuery("SELECT * FROM expense WHERE " + columnName + " LIKE '%" + search + "%'");
            int expenseCount = 1;

            while (rsExpense.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(expenseCount));   
                v.add(rsExpense.getString("ID"));     
                v.add(rsExpense.getString("Name")); 
                v.add(rsExpense.getString("Des")); 
                v.add("");
                v.add(rsExpense.getString("LKR"));   
                v.add(rsExpense.getString("Date"));     
                expenseModel.addRow(v);
                expenseCount++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearch_DashboardKeyReleased

    private void ComboSort_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSort_DashboardActionPerformed
        // TODO add your handling code here:
        String sortBy = (String) ComboSort_Dashboard.getSelectedItem();
        String columnName = "";
        String sortOrder = "ASC";

        switch (sortBy) {
            case "ID (Min - Max)":
                columnName = "ID";
                sortOrder = "ASC";
                break;
            case "ID (Max - Min)":
                columnName = "ID";
                sortOrder = "DESC";
                break;
            case "Name (A - Z)":
                columnName = "Name";
                sortOrder = "ASC";
                break;
            case "Name (Z - A)":
                columnName = "Name";
                sortOrder = "DESC";
                break;
            case "LKR (Min - Max)":
                columnName = "LKR";
                sortOrder = "ASC";
                break;
            case "LKR (Max - Min)":
                columnName = "LKR";
                sortOrder = "DESC";
                break;
            default:
                return;
        }

        try {
            DefaultTableModel dt = (DefaultTableModel) finance_dash_table.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();

            ResultSet rsIncome = s.executeQuery("SELECT * FROM income ORDER BY " + columnName + " " + sortOrder);
            int count = 1;
            while (rsIncome.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));   
                v.add(rsIncome.getString("ID"));       
                v.add(rsIncome.getString("Name"));
                v.add(rsIncome.getString("Des"));   
                v.add(rsIncome.getString("LKR"));    
                v.add("");                          
                v.add(rsIncome.getString("Date")); 
                dt.addRow(v);
                count++;
            }

            ResultSet rsExpense = s.executeQuery("SELECT * FROM expense ORDER BY " + columnName + " " + sortOrder);
            while (rsExpense.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));           
                v.add(rsExpense.getString("ID"));          
                v.add(rsExpense.getString("Name")); 
                v.add(rsExpense.getString("Des"));     
                v.add("");                                 
                v.add(rsExpense.getString("LKR"));   
                v.add(rsExpense.getString("Date"));
                dt.addRow(v);
                count++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ComboSort_DashboardActionPerformed

    private void ComboSearch_IncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSearch_IncomeActionPerformed
        // TODO add your handling code here:
         txtSearch_Income.requestFocusInWindow();
    }//GEN-LAST:event_ComboSearch_IncomeActionPerformed

    private void txtSearch_IncomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch_IncomeKeyReleased
        // TODO add your handling code here:
        String searchBy = (String) ComboSearch_Income.getSelectedItem();
        String columnName = "";

        switch (searchBy) {
            case "ID":
            columnName = "ID";
            break;
            case "Name":
            columnName = "Name";
            break;
            default:
            JOptionPane.showMessageDialog(null, "Invalid search category!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String search = txtSearch_Income.getText().trim();

        try {
            DefaultTableModel dt = (DefaultTableModel) income_table.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM income WHERE " + columnName + " LIKE '%" + search + "%'");

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 5; i++) {
                    v.add(rs.getString(i));
                }
                dt.addRow(v);
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } 
    }//GEN-LAST:event_txtSearch_IncomeKeyReleased

    private void ComboSort_IncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSort_IncomeActionPerformed
        // TODO add your handling code here:
        String sortBy = (String) ComboSort_Income.getSelectedItem();
        String columnName = "";
        String sortOrder = "ASC";

        switch (sortBy) {
            case "ID (Min - Max)":
            columnName = "ID";
            sortOrder = "ASC";
            break;
            case "ID (Max - Min)":
            columnName = "ID";
            sortOrder = "DESC";
            break;
            case "Name (A - Z)":
            columnName = "Name";
            sortOrder = "ASC";
            break;
            case "Name (Z - A)":
            columnName = "Name";
            sortOrder = "DESC";
            break;
            case "LKR (Min - Max)":
            columnName = "LKR";
            sortOrder = "ASC";
            break;
            case "LKR (Max - Min)":
            columnName = "LKR";
            sortOrder = "DESC";
            break;
            default:
            return;
        }

        try {
            DefaultTableModel dt = (DefaultTableModel) income_table.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            String query = "SELECT * FROM income ORDER BY " + columnName + " " + sortOrder;
            ResultSet rs = s.executeQuery(query);

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 5; i++) {
                    v.add(rs.getString(i));
                }
                dt.addRow(v);
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ComboSort_IncomeActionPerformed

    private void btnAdd_IncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_IncomeActionPerformed
        // TODO add your handling code here:
        Finance_Income_Add_Popup Finance_Income_Add_Popup = new Finance_Income_Add_Popup(this);
        Finance_Income_Add_Popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Finance_Income_Add_Popup.setVisible(true);

        this.setEnabled(false);

        Finance_Income_Add_Popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                btnAdd_Income.getTopLevelAncestor().setEnabled(true);
            }
        });
    }//GEN-LAST:event_btnAdd_IncomeActionPerformed

    private void ComboSearch_expenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSearch_expenseActionPerformed
        // TODO add your handling code here:
        txtSearch_expense.requestFocusInWindow();
    }//GEN-LAST:event_ComboSearch_expenseActionPerformed

    private void txtSearch_expenseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch_expenseKeyReleased
        // TODO add your handling code here:
        String searchBy = (String) ComboSearch_expense.getSelectedItem();
        String columnName = "";

        switch (searchBy) {
            case "ID":
            columnName = "ID";
            break;
            case "Name":
            columnName = "Name";
            break;
            default:
            JOptionPane.showMessageDialog(null, "Invalid search category!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String search = txtSearch_expense.getText().trim();

        try {
            DefaultTableModel dt = (DefaultTableModel) expense_table.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM expense WHERE " + columnName + " LIKE '%" + search + "%'");

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 5; i++) {
                    v.add(rs.getString(i));
                }
                dt.addRow(v);
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearch_expenseKeyReleased

    private void ComboSort_expenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSort_expenseActionPerformed
        // TODO add your handling code here:
        String sortBy = (String) ComboSort_expense.getSelectedItem();
        String columnName = "";
        String sortOrder = "ASC";

        switch (sortBy) {
            case "ID (Min - Max)":
            columnName = "ID";
            sortOrder = "ASC";
            break;
            case "ID (Max - Min)":
            columnName = "ID";
            sortOrder = "DESC";
            break;
            case "Name (A - Z)":
            columnName = "Name";
            sortOrder = "ASC";
            break;
            case "Name (Z - A)":
            columnName = "Name";
            sortOrder = "DESC";
            break;
            case "LKR (Min - Max)":
            columnName = "LKR";
            sortOrder = "ASC";
            break;
            case "LKR (Max - Min)":
            columnName = "LKR";
            sortOrder = "DESC";
            break;
            default:
            return;
        }

        try {
            DefaultTableModel dt = (DefaultTableModel) expense_table.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            String query = "SELECT * FROM expense ORDER BY " + columnName + " " + sortOrder;
            ResultSet rs = s.executeQuery(query);

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 5; i++) {
                    v.add(rs.getString(i));
                }
                dt.addRow(v);
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ComboSort_expenseActionPerformed

    private void btnAdd_expenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_expenseActionPerformed
        // TODO add your handling code here:
        Finance_Expense_Add_Popup Finance_Expense_Add_Popup = new Finance_Expense_Add_Popup(this);
        Finance_Expense_Add_Popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Finance_Expense_Add_Popup.setVisible(true);

        this.setEnabled(false);

        Finance_Expense_Add_Popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                btnAdd_Income.getTopLevelAncestor().setEnabled(true);
            }
        });
    }//GEN-LAST:event_btnAdd_expenseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboSearch_Dashboard;
    private javax.swing.JComboBox<String> ComboSearch_Income;
    private javax.swing.JComboBox<String> ComboSearch_expense;
    private javax.swing.JComboBox<String> ComboSort_Dashboard;
    private javax.swing.JComboBox<String> ComboSort_Income;
    private javax.swing.JComboBox<String> ComboSort_expense;
    private javax.swing.JButton btnAdd_Income;
    private javax.swing.JButton btnAdd_expense;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JTable expense_table;
    private javax.swing.JTable finance_dash_table;
    private javax.swing.JTable income_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTextField txtExpense_Dashboard;
    private javax.swing.JTextField txtIncome_Dashboard;
    private javax.swing.JTextField txtProfit_Dashboard;
    private javax.swing.JTextField txtSearch_Dashboard;
    private javax.swing.JTextField txtSearch_Income;
    private javax.swing.JTextField txtSearch_expense;
    private javax.swing.JTextField txtTotalIncome;
    private javax.swing.JTextField txtTotalIncome1;
    // End of variables declaration//GEN-END:variables
}
