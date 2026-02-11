
package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Employee extends javax.swing.JPanel {

    public Employee() {
        initComponents();
        empTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        empTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        empTable.getColumnModel().getColumn(5).setPreferredWidth(160);
        loadEmployeeData();
        addTableClickListener();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmpId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmpAge = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmpAddress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmpPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmpEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEmpSalary = new javax.swing.JTextField();
        txtEmpNIC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        radioButtonMale = new javax.swing.JRadioButton();
        radioButtonFemale = new javax.swing.JRadioButton();
        comboBoxEmp = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        empTable = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 102));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel2.setText("Employee Details");

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/add.png"))); // NOI18N
        jButton1.setText("Assign Task");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/eye.png"))); // NOI18N
        jButton2.setText("View Task");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("ID :");

        txtEmpId.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Name :");

        txtEmpName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Age :");

        txtEmpAge.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmpAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpAgeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Address :");

        txtEmpAddress.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmpAddress.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Gender :");

        txtEmpPhone.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmpPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpPhoneActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Phone :");

        txtEmpEmail.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Email :");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Job Role :");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Salary: ");

        txtEmpSalary.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        txtEmpNIC.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("NIC :");

        buttonGroup1.add(radioButtonMale);
        radioButtonMale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        radioButtonMale.setText("Male");
        radioButtonMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonMaleActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioButtonFemale);
        radioButtonFemale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        radioButtonFemale.setText("Female");
        radioButtonFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonFemaleActionPerformed(evt);
            }
        });

        comboBoxEmp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        comboBoxEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chef", "Servent", "Cashier", "Cleaner", "Room Helper" }));

        btnSave.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmpPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmpSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmpNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmpId, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmpAge, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(radioButtonMale, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioButtonFemale)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpAge, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioButtonMale)
                    .addComponent(radioButtonFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        empTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        empTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "EMP ID", "Name", "Age", "Gender", "Phone", "Email", "Job_Role", "Salary"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        empTable.setRowHeight(30);
        empTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(empTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

   private void loadEmployeeData() {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        String sql = "SELECT * FROM employees ORDER by employee_id";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) empTable.getModel();
        model.setRowCount(0); // Clear existing rows before adding new data

        while (rs.next()) {
            Object[] row = {
                rs.getInt("employee_id"), 
                rs.getString("Name"), 
                rs.getInt("Age"),  
                rs.getString("Gender"), 
                rs.getString("Phone"), 
                rs.getString("Email"), 
                rs.getString("Job_Role"), 
                rs.getDouble("Salary")
            };
            model.addRow(row); // Add row to JTable
        }

    } catch (SQLException ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error loading employee data!");
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
   
   private void addTableClickListener() {
        empTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent em) {
                int rownumber = empTable.getSelectedRow();
                    if (rownumber != -1) {
                        String roomNo = empTable.getValueAt(rownumber, 0).toString();
                        //load data to textfield and combobox
                        try {
                            Statement s =  DatabaseLayer.mycon().createStatement();
                            ResultSet rs = s.executeQuery("SELECT * FROM employees WHERE employee_id = '"+roomNo+"'");
                            
                            if (rs.next()) {
                                txtEmpId.setText(rs.getString("employee_id"));
                                txtEmpNIC.setText(rs.getString("NIC"));
                                txtEmpName.setText(rs.getString("Name"));
                                txtEmpAge.setText(rs.getString("Age"));
                                txtEmpAddress.setText(rs.getString("Address"));
                                txtEmpPhone.setText(rs.getString("Phone"));
                                txtEmpEmail.setText(rs.getString("Email"));
                                comboBoxEmp.setSelectedItem(rs.getString("Job_Role"));
                                txtEmpSalary.setText(rs.getString("Salary"));
                                
                                String paidStatus = rs.getString("Gender");
                                if ("Male".equalsIgnoreCase(paidStatus)) {
                                    radioButtonMale.setSelected(true);
                                } else if ("Female".equalsIgnoreCase(paidStatus)) {
                                    radioButtonFemale.setSelected(true);
                                }
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }
            }
        });
    }


    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String empID = txtEmpId.getText();
        String NIC = txtEmpNIC.getText();
        String name = txtEmpName.getText();
        String age = txtEmpAge.getText();
        String address = txtEmpAddress.getText();
        String gender = radioButtonMale.isSelected() ? "Male" : "Female";
        String phone = txtEmpPhone.getText();
        String email = txtEmpEmail.getText();
        String jobRole = comboBoxEmp.getSelectedItem().toString();
        String salaray = txtEmpSalary.getText();
        
        StringBuilder errorMsg = new StringBuilder();

    // Basic validations
    if (empID.isEmpty()) errorMsg.append("- Employee ID is required.\n");
    if (NIC.isEmpty() || !NIC.matches("^\\d{9}[VvXx]?$|^\\d{12}$")) errorMsg.append("- Valid NIC is required.\n");
    if (name.isEmpty()) errorMsg.append("- Name is required.\n");
    if (age.isEmpty() || !age.matches("\\d+") || Integer.parseInt(age) < 18)
        errorMsg.append("- Valid Age (>= 18) is required.\n");
    if (address.isEmpty()) errorMsg.append("- Address is required.\n");
    if (gender.isEmpty()) errorMsg.append("- Please select Gender.\n");
    if (phone.isEmpty() || !phone.matches("^\\d{10}$")) errorMsg.append("- Valid 10-digit Phone Number is required.\n");
    if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) errorMsg.append("- Valid Email is required.\n");
    if (jobRole.isEmpty()) errorMsg.append("- Job Role must be selected.\n");
    if (salaray.isEmpty() || !salaray.matches("\\d+(\\.\\d{1,2})?")) errorMsg.append("- Valid Salary is required.\n");

    if (errorMsg.length() > 0) {
        JOptionPane.showMessageDialog(this, "Please fix the following errors:\n\n" + errorMsg.toString(),
                "Validation Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
        
        
        
        
        try{
            String sql="INSERT INTO employees(employee_id, NIC, Name, Age, Address, Gender, Phone, Email, Job_Role, Salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            
            pst.setString(1, empID);
            pst.setString(2, NIC);
            pst.setString(3, name);
            pst.setInt(4, Integer.parseInt(age));
            pst.setString(5, address);
            pst.setString(6, gender);
            pst.setString(7, phone);
            pst.setString(8, email);
            pst.setString(9, jobRole);
            pst.setString(10, salaray);
            
            int rowsInserted = pst.executeUpdate();
            
            if (rowsInserted > 0) {

                JOptionPane.showMessageDialog(this, "Employee ID: " + empID + " Saved Successfully!");
                loadEmployeeData();
                
                txtEmpId.setText("");
                txtEmpNIC.setText("");
                txtEmpName.setText("");
                txtEmpAge.setText("");
                txtEmpAddress.setText("");
                radioButtonMale = null;
                txtEmpPhone.setText("");
                txtEmpEmail.setText("");
                comboBoxEmp = null;
                txtEmpSalary.setText("");
            
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Save Data!");
            }
     
            }catch(SQLException ex){
                ex.printStackTrace(); // Log SQL exceptions
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            
            }
        
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void empTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_empTableMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
       Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;
        
        String empID = txtEmpId.getText();
        String NIC = txtEmpNIC.getText();
        String name = txtEmpName.getText();
        String age = txtEmpAge.getText();
        String address = txtEmpAddress.getText();
        String gender = radioButtonMale.isSelected() ? "Male" : "Female";
        String phone = txtEmpPhone.getText();
        String email = txtEmpEmail.getText();
        String jobRole = comboBoxEmp.getSelectedItem().toString();
        String salaray = txtEmpSalary.getText();
        
        
        try {
            String sql = "UPDATE employees SET NIC = ?, Name = ?, Age = ?, Address = ?, Gender = ?, Phone = ?, Email = ?, Job_Role = ?, Salary = ? WHERE employee_id = ?";
            pst = con.prepareStatement(sql);
            
            pst.setString(1, NIC);
            pst.setString(2, name);
            pst.setInt(3, Integer.parseInt(age));
            pst.setString(4, address);
            pst.setString(5, gender);
            pst.setString(6, phone);
            pst.setString(7, email);
            pst.setString(8, jobRole);
            pst.setString(9, salaray);
            pst.setString(10, empID);
        
        int rowsUpdated = pst.executeUpdate();

        // Check if the update was successful
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Employee ID: " + empID + " Updated Successfully!");
            loadEmployeeData();  // Refresh the data in the table after the update
            
            txtEmpId.setText("");
            txtEmpNIC.setText("");
            txtEmpName.setText("");
            txtEmpAge.setText("");
            txtEmpAddress.setText("");
            radioButtonMale = null;
            txtEmpPhone.setText("");
            txtEmpEmail.setText("");
            comboBoxEmp = null;
            txtEmpSalary.setText("");
        
        
        } else {
            JOptionPane.showMessageDialog(this, "Failed to Update Employee Data.");
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtEmpAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpAgeActionPerformed

    private void radioButtonMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonMaleActionPerformed

    private void radioButtonFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonFemaleActionPerformed

    private void txtEmpPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpPhoneActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        EmployeAssignTask empAssignTask = new EmployeAssignTask(); 

        JFrame frame = new JFrame("Assign Task to Employee");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.getContentPane().add(empAssignTask);
        frame.pack(); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ViewAssignTask empAssignTask = new ViewAssignTask(); 

        JFrame frame = new JFrame("View Tasks");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.getContentPane().add(empAssignTask);
        frame.pack(); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to delete?", 
            "Confirm Format", // title
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            String itemID = txtEmpId.getText().trim();
            try {
                Statement s =  DatabaseLayer.mycon().createStatement();
                s.executeUpdate("DELETE  FROM employees WHERE employee_id = '"+itemID+"'");
                loadEmployeeData();
                
                txtEmpId.setText("");
            txtEmpNIC.setText("");
            txtEmpName.setText("");
            txtEmpAge.setText("");
            txtEmpAddress.setText("");
            buttonGroup1.clearSelection();  
            txtEmpPhone.setText("");
            txtEmpEmail.setText("");
            comboBoxEmp.setSelectedIndex(0);
            txtEmpSalary.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboBoxEmp;
    private javax.swing.JTable empTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioButtonFemale;
    private javax.swing.JRadioButton radioButtonMale;
    private javax.swing.JTextField txtEmpAddress;
    private javax.swing.JTextField txtEmpAge;
    private javax.swing.JTextField txtEmpEmail;
    private javax.swing.JTextField txtEmpId;
    private javax.swing.JTextField txtEmpNIC;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtEmpPhone;
    private javax.swing.JTextField txtEmpSalary;
    // End of variables declaration//GEN-END:variables
}
