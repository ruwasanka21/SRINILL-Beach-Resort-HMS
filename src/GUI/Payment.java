
package GUI;

import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Payment extends javax.swing.JPanel {
    
    
     private String checking_id;
     private String roomNO;
     private String checkInDate;
     private String checkOutDate;
     private double totalPrice;
     private int reservationId;
     private double updatedPrice;
    
    public Payment(String checking_id) {
        initComponents();
        generateNextInvoiceNumber();
        this.checking_id = checking_id;
        loadCustomerReservationDetails(checking_id);
        calculateReservationPrice(roomNO, checkInDate, checkOutDate);
        
        
        txtDiscount.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { calculateOnTheFly(); }
        public void removeUpdate(DocumentEvent e) { calculateOnTheFly(); }
        public void changedUpdate(DocumentEvent e) { calculateOnTheFly(); }
    });
        
        txtPaid.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { calculateOnTheFly(); }
        public void removeUpdate(DocumentEvent e) { calculateOnTheFly(); }
        public void changedUpdate(DocumentEvent e) { calculateOnTheFly(); }
    });

    }
    
    private void loadCustomerReservationDetails(String checking_id) {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        
        String sql = "SELECT * FROM roomcheckin WHERE checkIn_id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, checking_id);
        rs = pst.executeQuery();

        if (rs.next()) {
            txtCustomerName.setText(rs.getString("customerName"));
            String customerIdStr = rs.getString("customer_id");
            txtCustomerID.setText(customerIdStr); 
            reservationId = rs.getInt("reservation_id");
            txtReservationID.setText(String.valueOf(reservationId));
            txtReservationDate.setText(rs.getString("reserved_date"));
            txtRoomID.setText(rs.getString("room_id"));
            roomNO= txtRoomID.getText();
            txtCheckInID.setText(rs.getString("checkIn_id"));
            checkInDate = rs.getString("checkInDate").trim();  
            txtCheckOutDate.setText(rs.getString("checkOutDate"));
            checkOutDate = rs.getString("checkOutDate").trim();
            
            
        } else {
            JOptionPane.showMessageDialog(this, "No record found for check-in ID: " + checking_id);
        }
        
        loadFeaturesByCheckInID(checking_id);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving data: " + ex.getMessage());
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
    
    private void generateNextInvoiceNumber() {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT MAX(invoiceNo) AS lastInvoice FROM payments";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        String nextInvoiceNo;
        if (rs.next()) {
            String lastInvoice = rs.getString("lastInvoice");
            if (lastInvoice != null && !lastInvoice.isEmpty()) {
                // Assuming invoice number format is numeric like "1001", "1002"...
                int newInvoice = Integer.parseInt(lastInvoice) + 1;
                nextInvoiceNo = String.valueOf(newInvoice);
            } else {
                nextInvoiceNo = "1001"; // Default starting number
            }
        } else {
            nextInvoiceNo = "1001"; // Default starting number
        }

        txtInvoiceNo.setText(nextInvoiceNo); // Assuming you have a JTextField named txtInvoiceNo

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error generating invoice number: " + ex.getMessage());
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
    
    private void calculateReservationPrice(String roomID, String checkInDateStr, String checkOutDateStr) {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Step 1: Get room price
        String sql = "SELECT price FROM rooms WHERE room_no = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, roomID);
        rs = pst.executeQuery();

        double pricePerNight = 0;
        if (rs.next()) {
            pricePerNight = rs.getDouble("price");
        } else {
            JOptionPane.showMessageDialog(this, "Room price not found.");
            return;
        }

        // Step 2: Parse dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        checkInDateStr = checkInDateStr.trim();
        checkOutDateStr = checkOutDateStr.trim();
        Date checkInDate = sdf.parse(checkInDateStr);
        Date checkOutDate = sdf.parse(checkOutDateStr);

        // Step 3: Calculate duration
        long diffInMillis = checkOutDate.getTime() - checkInDate.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        if (diffInDays == 0) diffInDays = 1; // Minimum charge for 1 night

        // Step 4: Calculate total price
        totalPrice = diffInDays * pricePerNight;
        
        updateReservationPriceBasedOnFeatures(reservationId);

        // Set the total price in the amount textbox
        txtTotal.setText(String.format("%.2f", updatedPrice));

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error calculating reservation price: " + ex.getMessage());
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
    
    private void updateReservationPriceBasedOnFeatures(int reservationId) {
    try {
        Connection conn = DatabaseLayer.mycon();

        
        // Step 2: Calculate total selected feature price
        String featurePriceSql = "SELECT SUM(f.price) AS feature_total " +
                                 "FROM checkin_features cf " +
                                 "JOIN features f ON cf.feature_id = f.featureID " +
                                 "WHERE cf.checkin_id = ?";
        PreparedStatement featureStmt = conn.prepareStatement(featurePriceSql);
        featureStmt.setInt(1, Integer.parseInt(checking_id)); 
        ResultSet featureRs = featureStmt.executeQuery();

        double featureTotal = 0.0;
        if (featureRs.next()) {
            featureTotal = featureRs.getDouble("feature_total");
        }
        featureRs.close();
        featureStmt.close();

        // Step 3: Update reservation price
        updatedPrice = totalPrice + featureTotal;
        
        String updateSql = "UPDATE roomreservation SET price = ? WHERE reservation_id = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
        updateStmt.setString(1, String.format("%.2f", updatedPrice));
        updateStmt.setInt(2, reservationId);
        updateStmt.executeUpdate();
        updateStmt.close();

        conn.close();

        JOptionPane.showMessageDialog(this, "Reservation price updated successfully to " + updatedPrice);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating reservation price: " + ex.getMessage());
    }
}
    
    private void calculateOnTheFly() {
    try {
        
        double total = Double.parseDouble(txtTotal.getText().trim());
        double discount = txtDiscount.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtDiscount.getText().trim());
        double paid = txtPaid.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtPaid.getText().trim());

        // Assuming discount is in percentage
        double discountValue = total * (discount / 100);

        if (discountValue > total) {
            txtNetValue.setText("");
            txtBalance.setText("");
            txtValue.setText("");
            return;
        }

        double netTotal = total - discountValue;
        double balance = netTotal - paid;

        // Show the calculated values
        txtValue.setText(String.format("%.2f", discountValue)); // Discount in currency
        txtNetValue.setText(String.format("%.2f", netTotal));
        txtBalance.setText(String.format("%.2f", balance));

    } catch (NumberFormatException e) {
        txtNetValue.setText("");
        txtBalance.setText("");
        txtValue.setText("");
    }
}
    
    private void loadFeaturesByCheckInID(String checkInId) {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT f.featureName " +
                     "FROM checkin_features cf " +
                     "JOIN features f ON cf.feature_id = f.featureID " +
                     "WHERE cf.checkin_id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, checkInId);
        rs = pst.executeQuery();

        // Example: populate a JTextArea or console output
        StringBuilder featuresList = new StringBuilder("Features:\n");

        while (rs.next()) {
            featuresList.append("- ").append(rs.getString("featureName")).append("\n");
        }

        // Display in a JTextArea or similar
        txtFeaturesUsed.setText(featuresList.toString()); // assuming you have a JTextArea named txtFeaturesUsed

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving features: " + ex.getMessage());
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

    
    //Insert Income to Finace table
    private void insertIncome() { 
        String itemID = "Reservation ID : "+txtReservationID.getText().trim();
        String price = txtNetValue.getText().trim();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addedDate = formatter.format(new Date());
        String des = "Room Income";
        try {
            Statement s =  DatabaseLayer.mycon().createStatement();
            s.executeUpdate("INSERT INTO income (Name, LKR, Des, Date) VALUES ('"+itemID+"', '"+price+"', '"+des+"','"+addedDate+"')");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtInvoiceNo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();
        txtReservationID = new javax.swing.JTextField();
        txtCheckInID = new javax.swing.JTextField();
        txtRoomID = new javax.swing.JTextField();
        txtReservationDate = new javax.swing.JTextField();
        txtCheckOutDate = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        txtValue = new javax.swing.JTextField();
        txtNetValue = new javax.swing.JTextField();
        txtPaid = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFeaturesUsed = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        jPanel7.setBackground(new java.awt.Color(255, 153, 51));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setText("Payment Details");

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

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel4.setText("Invoice No: ");

        txtInvoiceNo.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtInvoiceNo.setText("-");
        txtInvoiceNo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Customer Name :");

        txtCustomerName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCustomerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCustomerName.setText("-");
        txtCustomerName.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Customer ID :");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Reservaion ID :");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Check IN ID :");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Room ID :");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Reservation Date :");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Check Out Date :");

        txtCustomerID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtReservationID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtCheckInID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtRoomID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtReservationDate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtCheckOutDate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Total :");

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTotal.setText("-");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Discount :");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Value :");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Net Total :");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Paid :");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Balance :");

        txtDiscount.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountActionPerformed(evt);
            }
        });

        txtValue.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtNetValue.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNetValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNetValueActionPerformed(evt);
            }
        });

        txtPaid.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtBalance.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBalanceActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(51, 102, 255));
        btnPrint.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/print.png"))); // NOI18N
        btnPrint.setText("Print & Save");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(153, 255, 153));
        btnSave.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(237, 237, 237))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNetValue, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                        .addComponent(txtPaid)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtBalance)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnPrint)
                                .addGap(37, 37, 37)
                                .addComponent(btnSave)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTotal))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNetValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnSave))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        txtFeaturesUsed.setColumns(20);
        txtFeaturesUsed.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtFeaturesUsed.setRows(5);
        jScrollPane1.setViewportView(txtFeaturesUsed);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Add-Ones :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(5, 5, 5)
                                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtCheckInID, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(txtReservationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel2))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCustomerID, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addComponent(txtReservationID))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInvoiceNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCustomerName))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtReservationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtReservationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCheckInID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE)))
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

    private void txtDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscountActionPerformed

    private void txtNetValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNetValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNetValueActionPerformed

    private void txtBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBalanceActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon(); 
    PreparedStatement pst = null;

    try {
        String sql = "INSERT INTO payments (invoiceNo, reservation_id, amount, payment_method, status) " +
                     "VALUES (?, ?, ?, ?, ?)";

        pst = con.prepareStatement(sql);

        // Collect values from UI
        String invoiceNo = txtInvoiceNo.getText().trim();
        String reservationId = txtReservationID.getText().trim();
        String amount = txtNetValue.getText().trim(); 
        String paymentMethod = "Cash"; 
        String status = "Completed"; 

        // Set values in prepared statement
        pst.setString(1, invoiceNo);
        pst.setString(2, reservationId);
        pst.setString(3, amount);
        pst.setString(4, paymentMethod);
        pst.setString(5, status);
        
        insertIncome();
        
        // Execute update
        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Payment saved successfully!");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving payment: " + ex.getMessage());
    } finally {
        try {
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        insertIncome();
        
        StringBuilder bill = new StringBuilder();

    bill.append("   HOTEL Panorama, Kalutara\n");
    bill.append("       Payment Receipt\n");
    bill.append("==============================\n");
    bill.append("Invoice No : ").append(txtInvoiceNo.getText()).append("\n");
    bill.append("Reservation ID : ").append(txtReservationID.getText()).append("\n");
    bill.append("Customer Name : ").append(txtCustomerName.getText()).append("\n");
    bill.append("Room No : ").append(txtRoomID.getText()).append("\n");
    bill.append("Check-In : ").append(txtCheckInID.getText()).append("\n");
    bill.append("Check-Out : ").append(txtCheckOutDate.getText()).append("\n");
    bill.append("------------------------------\n");
    
    
    String featuresUsed = txtFeaturesUsed.getText();
    bill.append("Features Used:\n").append(featuresUsed).append("\n");
    bill.append("------------------------------\n");


    
    bill.append("Total        : ").append(txtTotal.getText()).append("\n");
    bill.append("Discount (%) : ").append(txtDiscount.getText()).append("\n");
    bill.append("Discount Amt : ").append(txtValue.getText()).append("\n");
    bill.append("Net Total    : ").append(txtNetValue.getText()).append("\n");
    bill.append("Paid         : ").append(txtPaid.getText()).append("\n");
    bill.append("Balance      : ").append(txtBalance.getText()).append("\n");
    bill.append("==============================\n");
    bill.append("Thank you! Have a great day!\n");

    JTextArea area = new JTextArea(bill.toString());
    area.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
    area.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(area);
    scrollPane.setPreferredSize(new Dimension(400, 400));

    // Show bill in a dialog with print option
    int option = JOptionPane.showConfirmDialog(this, scrollPane, "Print Bill", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        try {
            area.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Error printing: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtCheckInID;
    private javax.swing.JTextField txtCheckOutDate;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JLabel txtCustomerName;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextArea txtFeaturesUsed;
    private javax.swing.JLabel txtInvoiceNo;
    private javax.swing.JTextField txtNetValue;
    private javax.swing.JTextField txtPaid;
    private javax.swing.JTextField txtReservationDate;
    private javax.swing.JTextField txtReservationID;
    private javax.swing.JTextField txtRoomID;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables

    private void setDefaultCloseOperation(int DISPOSE_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
