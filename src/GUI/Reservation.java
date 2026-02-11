
package GUI;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import GUI.HallReservation_Popup1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Reservation extends javax.swing.JPanel {
    private int customerID; 
    private String customerEmail;
    private String customerName;

    public Reservation(int customerID) {
    this.customerID = customerID;
}
    public int getCustomerID() {
        return customerID;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public String getCustomerName() {
        return customerName;
    }
    
    public Reservation() {
        initComponents();  
        txtPrice.setEditable(false);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButtonMale);
        genderGroup.add(radioButtonFemale);
        
        jComboBox2.addItem("-- Select --");
        jComboBox2.addItem("Room");
        jComboBox2.addItem("Hall");
        
        setupEventListeners();
        
        txtNIC.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            checkCustomerByNIC();
        }
         public void removeUpdate(DocumentEvent e) {
            checkCustomerByNIC();
        }
         public void changedUpdate(DocumentEvent e) {
                checkCustomerByNIC();
        }
        });


    }
    
    private void setupEventListeners() {
    jComboBox2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            String selectedType = jComboBox2.getSelectedItem().toString();

            if (selectedType.equals("Room")) {
                jLabel2.setText("Room No:");
                loadRoomNumbers();
            } else if (selectedType.equals("Hall")) {
                jLabel2.setText("Hall Name:");
                loadHallNames();
            }
 
        }
    });

    jComboBox3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            fetchPrice(); // Update price when Room/Hall changes
        }
    });
}

    
    private void loadRoomNumbers() {
        jComboBox3.removeAllItems();
        jComboBox3.addItem("-- Select --");
        
        Connection con = DatabaseLayer.mycon();
        String sql = "SELECT room_no FROM rooms WHERE availability = 'Available' order by room_no";
        try (PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                jComboBox3.addItem(rs.getString("room_no"));
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void loadHallNames() {
        jComboBox3.removeAllItems();
        jComboBox3.addItem("-- Select --");
        
        Connection con = DatabaseLayer.mycon();
        String sql = "SELECT hall_name FROM halls";
        try (PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            jComboBox3.addItem(rs.getString("hall_name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void fetchPrice(){
        if (jComboBox3.getSelectedItem() == null || jComboBox3.getSelectedItem().toString().equals("-- Select --") || jComboBox2.getSelectedItem().toString().equals("-") ) {
        txtPrice.setText(""); 
        return;
    }
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;
        ResultSet rs=null;
        
        try {
        String selectedType = jComboBox2.getSelectedItem().toString();
        String selectedRoomID = jComboBox3.getSelectedItem().toString();

        if (selectedType.equals("Room")) {
            String sql = "SELECT price FROM rooms WHERE room_no = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, selectedRoomID);
            rs = pst.executeQuery();

            if (rs.next()) {
                String price = rs.getString("price");
                txtPrice.setText(price);
            } else {
                txtPrice.setText("N/A");
                JOptionPane.showMessageDialog(this, "Room not found!");
            }
        }else if(selectedType.equals("Hall")){
            jLabel2.setText("Hall Name :");
            String sql1="SELECT price FROM halls WHERE hall_name = ?";
            pst = con.prepareStatement(sql1);
            pst.setString(1, selectedRoomID); //selectedRoomID = hall Name
            rs = pst.executeQuery();

            if (rs.next()) {
                String price = rs.getString("price");
                txtPrice.setText(price);
            } else {
                txtPrice.setText("N/A");
                JOptionPane.showMessageDialog(this, "Hall not found!");
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    }
    
    private void checkCustomerByNIC() {
    String nic = txtNIC.getText().trim();

    if (nic.length() < 10) {
        // Optional: only search when NIC is at least 5 characters
        return;
    }

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        con = DatabaseLayer.mycon();
        String sql = "SELECT * FROM customers WHERE NIC = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, nic);
        rs = pst.executeQuery();

        if (rs.next()) {
            // Existing customer found
            txtCustName.setText(rs.getString("name"));
            txtCustEmail.setText(rs.getString("email"));
            txtCustPhone.setText(rs.getString("phone"));
            txtCustAddress.setText(rs.getString("address"));
            String gender = rs.getString("gender");
            if ("Male".equalsIgnoreCase(gender)) {
                radioButtonMale.setSelected(true);
            } else {
                radioButtonFemale.setSelected(true);
            }

            lblCheck.setText("Customer already exists.");
            customerID = rs.getInt("customer_id");
            customerEmail = rs.getString("email");
            customerName = rs.getString("name");
            
            btnUpdate.setEnabled(true);  

        } else {
            // Not found – clear fields
            txtCustName.setText("");
            txtCustEmail.setText("");
            txtCustPhone.setText("");
            txtCustAddress.setText("");
            radioButtonMale.setSelected(false);
            radioButtonFemale.setSelected(false);
            lblCheck.setText("New customer.");
            btnUpdate.setEnabled(false);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error checking NIC: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblCustName = new javax.swing.JLabel();
        lblCustEmail = new javax.swing.JLabel();
        lblCustPhone = new javax.swing.JLabel();
        lblCustAddress = new javax.swing.JLabel();
        txtCustName = new javax.swing.JTextField();
        txtCustEmail = new javax.swing.JTextField();
        txtCustPhone = new javax.swing.JTextField();
        txtCustAddress = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        radioButtonMale = new javax.swing.JRadioButton();
        radioButtonFemale = new javax.swing.JRadioButton();
        lblCustPhone1 = new javax.swing.JLabel();
        txtNIC = new javax.swing.JTextField();
        lblCheck = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        spingReserveDate = new com.toedter.calendar.JSpinnerDateEditor();
        btnConfirm = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        checkInDate = new com.toedter.calendar.JDateChooser();
        jComboBox3 = new javax.swing.JComboBox<>();
        checkOutDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();

        jPanel7.setBackground(new java.awt.Color(255, 153, 51));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setText("Reservation Details");

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

        jCalendar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCalendar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Enter Customer Details");

        lblCustName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCustName.setText("Name :");

        lblCustEmail.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCustEmail.setText("Email :");

        lblCustPhone.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCustPhone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCustPhone.setText("Phone :");

        lblCustAddress.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCustAddress.setText("Address :");

        txtCustName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        txtCustEmail.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        txtCustPhone.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        txtCustAddress.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        btnClear.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/remove.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.setMinimumSize(new java.awt.Dimension(150, 150));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/check in.png"))); // NOI18N
        btnNext.setText("Next");
        btnNext.setMinimumSize(new java.awt.Dimension(150, 150));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Gender :");

        radioButtonMale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        radioButtonMale.setText("Male");
        radioButtonMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonMaleActionPerformed(evt);
            }
        });

        radioButtonFemale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        radioButtonFemale.setText("Female");

        lblCustPhone1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblCustPhone1.setText("NIC :");

        txtNIC.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        lblCheck.setBackground(new java.awt.Color(51, 255, 51));
        lblCheck.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCustPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCustAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblCustEmail)
                                            .addComponent(lblCustName)
                                            .addComponent(lblCustPhone1))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCustName)
                                    .addComponent(txtCustEmail)
                                    .addComponent(txtCustPhone)
                                    .addComponent(txtCustAddress)
                                    .addComponent(txtNIC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioButtonMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(radioButtonFemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGap(18, 18, 18)
                        .addComponent(lblCheck)
                        .addGap(238, 238, 238))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustPhone1)
                    .addComponent(txtNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCheck))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustName)
                    .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustEmail)
                    .addComponent(txtCustEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustPhone)
                    .addComponent(txtCustPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustAddress)
                    .addComponent(txtCustAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioButtonMale)
                    .addComponent(radioButtonFemale))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Enter Customer Details", jPanel6);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Type :");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Reserve Date :");

        jComboBox2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        spingReserveDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        btnConfirm.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/check in.png"))); // NOI18N
        btnConfirm.setText("Next");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("No :");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Check In Date :");

        checkInDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jComboBox3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        checkOutDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Check Out Date :");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Price :");

        txtPrice.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Status :");

        jComboBoxStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Pending", "Booked" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkOutDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkInDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spingReserveDate, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(txtPrice)))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spingReserveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(checkInDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(checkOutDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnConfirm)
                .addGap(78, 78, 78))
        );

        jTabbedPane1.addTab("Enter Reservations Details", jPanel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;
        ResultSet rs = null;

        String customerName = txtCustName.getText().trim();
        String customerEmail = txtCustEmail.getText().trim();
        String NIC = txtNIC.getText().trim();
        String customerPhone = txtCustPhone.getText().trim();
        String customerAddress = txtCustAddress.getText().trim();
        String customerGender = radioButtonMale.isSelected() ? "Male" : "Female";
        
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Customer name cannot be empty.");
            return;
        }
        if (!customerEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email address.");
            return;
        }
        if (!customerPhone.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits.");
            return;
        }
        if (customerAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address cannot be empty.");
            return;
        }
        
        this.customerEmail = customerEmail;
        this.customerName = customerName;

        try {
            // Step 1: Check if customer already exists by email
            String checkSql = "SELECT customer_id FROM customers WHERE NIC = ?";
            pst = con.prepareStatement(checkSql);
            pst.setString(1, NIC);
            rs = pst.executeQuery();

            if (rs.next()) {
            // Customer already exists
                int existingCustomerID = rs.getInt("customer_id");
                this.customerID = existingCustomerID;
                
                lblCheck.setText("Customer Already Registered");

//                JOptionPane.showMessageDialog(this, "Customer already exists. Customer ID: " + existingCustomerID);
//                Customer_Add_Popup1 addCustomer = new Customer_Add_Popup1(new Reservation(customerID));
//       
//                addCustomer.setVisible(true);

                // Switch to the second tab
                jTabbedPane1.setSelectedIndex(1);
            } else {
                // Step 2: Customer does not exist, insert new
                rs.close();
                pst.close();

                String insertSql = "INSERT INTO customers (name, gender, email, NIC, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, customerName);
                pst.setString(2, customerGender);
                pst.setString(3, customerEmail);
                pst.setString(4, NIC);
                pst.setString(5, customerPhone);
                pst.setString(6, customerAddress);

                int rowsInserted = pst.executeUpdate();
                rs = pst.getGeneratedKeys();

                if (rowsInserted > 0 && rs.next()) {
                int newCustomerID = rs.getInt(1);
                this.customerID = newCustomerID;

                JOptionPane.showMessageDialog(this, 
                    "Customer ID: " + newCustomerID + " Saved Successfully!");

                // Switch to the second tab
                jTabbedPane1.setSelectedIndex(1);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Save Data!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtCustName.setText("");
        txtCustEmail.setText("");
        txtCustPhone.setText("");
        txtCustAddress.setText("");
        radioButtonMale=null;
        txtNIC.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs=null;
    
    String reserType = jComboBox2.getSelectedItem().toString();
    String id_No = jComboBox3.getSelectedItem().toString();
    Date checkInDateValue = checkInDate.getDate();
    Date checkOutDateValue = checkOutDate.getDate();
    String price = txtPrice.getText();
    String status = jComboBoxStatus.getSelectedItem().toString();
    LocalDate currentDate = LocalDate.now();
    
    // === Validation Start ===
    if (reserType == null || reserType.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select reservation type.");
        return;
    }
    if (id_No == null || id_No.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a Room/Hall ID.");
        return;
    }
    if (checkInDateValue == null) {
        JOptionPane.showMessageDialog(this, "Please select a Check-in Date.");
        return;
    }
    if (reserType.equals("Room") && checkOutDateValue == null) {
        JOptionPane.showMessageDialog(this, "Please select a Check-out Date.");
        return;
    }
    if (price.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter the price.");
        return;
    }
    try {
        Double.parseDouble(price); 
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Price must be a valid number.");
        return;
    }
    if (reserType.equals("Room") && checkInDateValue.after(checkOutDateValue)) {
        JOptionPane.showMessageDialog(this, "Check-out date must be after Check-in date.");
        return;
    }
    // === Validation End ===

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String checkInDateString = sdf.format(checkInDateValue);
    String checkOutDateString = sdf.format(checkOutDateValue);

    if (reserType.equals("Room")) {
        try {
            
            String sql = "INSERT INTO roomreservation (customer_id, room_id, reserve_Date, checkInDate,checkOutDate, price, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, customerID); 
            pst.setString(2, id_No);  
            pst.setString(3, currentDate.toString());  
            pst.setString(4, checkInDateString);  
            pst.setString(5, checkOutDateString);
            pst.setString(6, price); 
            pst.setString(7, status); 
            
            int rowsInserted = pst.executeUpdate();
            
            if (rowsInserted > 0) {
                rs = pst.getGeneratedKeys();
                int reservID=0;
                if(rs.next()){
                    reservID=rs.getInt(1);
                }
                JOptionPane.showMessageDialog(this, "Reservation ID: "+reservID+"Room ID: "+id_No+" successfully reserved for the "+customerName);
                // After successful reservation, update room availability to 'Not Available'
                String updateAvailabilitySQL = "UPDATE rooms SET availability = 'Ocupied' WHERE room_no = ?";
                pst = con.prepareStatement(updateAvailabilitySQL);
                pst.setString(1, id_No);
                int rowsUpdated = pst.executeUpdate();
                
                // Send confirmation email
                try{
                    String subject = "Panorama hotel Room Reservation Complete!!!";
                    String body = "Dear " + customerName + ",\n\nYour room reservation is successful!\n"
                +"Reservation ID is: "+reservID
                + "\nYour Room ID is: " +id_No
                +"\nReserve Date: "+currentDate.toString()
                +"\nCheck IN Date: "+checkInDateString
                +"\nThanks for choosing US!!!"
                +" \n\nRegards,\nAdmin";
                Send_Email_Handler.sendEmail(customerEmail, subject, body);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Error sending Email!!!");
                }
 
                txtCustName.setText("");
                txtCustEmail.setText("");
                txtCustPhone.setText("");
                txtCustAddress.setText("");
                txtNIC.setText("");
                jTabbedPane1.setSelectedIndex(0);
               
                
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Save Data!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } else {
        try {
            // Corrected SQL statement
            String sql = "INSERT INTO hallreservation (customer_id, hallName, reserve_Date, checkInDate, price, status) VALUES (?, ?, ?, ?, ?, ?)";
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, customerID);  
            pst.setString(2, id_No); //id_No = Hall Name
            pst.setString(3, currentDate.toString());  
            pst.setString(4, checkInDateString);  
            pst.setString(5, price);  
            pst.setString(6, status);  
            
            int rowsInserted = pst.executeUpdate();
            
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Hall Name " + id_No + " successfully reserved for customer ID " + customerID);
                String updateAvailabilitySQL = "UPDATE halls SET Availability = 'Reserved' WHERE hallName = ?";
                pst = con.prepareStatement(updateAvailabilitySQL);
                pst.setString(1, id_No);
                HallReservation_Popup1 addHallReserv = new HallReservation_Popup1(new Reservation(customerID));
                addHallReserv.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Save Data!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void radioButtonMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonMaleActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        Connection con = null;
    PreparedStatement pst = null;

    String customerName = txtCustName.getText().trim();
    String customerEmail = txtCustEmail.getText().trim();
    String customerPhone = txtCustPhone.getText().trim();
    String customerAddress = txtCustAddress.getText().trim();
    String customerGender = radioButtonMale.isSelected() ? "Male" : "Female";
    String NIC = txtNIC.getText().trim();

    if (customerID == 0) {
        JOptionPane.showMessageDialog(this, "No existing customer selected for update.");
        return;
    }

    try {
        con = DatabaseLayer.mycon();
        String sql = "UPDATE customers SET name = ?, gender = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, customerName);
        pst.setString(2, customerGender);
        pst.setString(3, customerEmail);
        pst.setString(4, customerPhone);
        pst.setString(5, customerAddress);
        pst.setInt(6, customerID);

        int updated = pst.executeUpdate();
        if (updated > 0) {
            JOptionPane.showMessageDialog(this, "Customer updated successfully!");
            jTabbedPane1.setSelectedIndex(1);
            
        } else {
            JOptionPane.showMessageDialog(this, "Customer update failed.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    } finally {
        try {
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnUpdate;
    private com.toedter.calendar.JDateChooser checkInDate;
    private com.toedter.calendar.JDateChooser checkOutDate;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCheck;
    private javax.swing.JLabel lblCustAddress;
    private javax.swing.JLabel lblCustEmail;
    private javax.swing.JLabel lblCustName;
    private javax.swing.JLabel lblCustPhone;
    private javax.swing.JLabel lblCustPhone1;
    private javax.swing.JRadioButton radioButtonFemale;
    private javax.swing.JRadioButton radioButtonMale;
    private com.toedter.calendar.JSpinnerDateEditor spingReserveDate;
    private javax.swing.JTextField txtCustAddress;
    private javax.swing.JTextField txtCustEmail;
    private javax.swing.JTextField txtCustName;
    private javax.swing.JTextField txtCustPhone;
    private javax.swing.JTextField txtNIC;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
