/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando's
 */
public class Customer_Add_On extends javax.swing.JPanel {

    private int checkInId;
    private int customerId;
    private String customerName;

    public Customer_Add_On(int checkInId, int customerId, String customerName) {
        this.checkInId = checkInId;
        this.customerId = customerId;
        this.customerName = customerName;
        
        initComponents();
        
        txtCustomerID.setText(String.valueOf(customerId));
        txtCustomerName.setText(customerName);
        txtCheckIn.setText(String.valueOf(checkInId));
        
        loadExistingFeaturesFromDatabase();
    }
    
    private void loadExistingFeaturesFromDatabase() {
    try {
        Connection conn = DatabaseLayer.mycon();

        String sql = "SELECT f.featureName " +
                     "FROM checkin_features cf " +
                     "JOIN features f ON cf.feature_id = f.featureID " +
                     "WHERE cf.checkin_id = ?";
                     
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, checkInId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String feature = rs.getString("featureName");

            switch (feature) {
                case "Free Wi-Fi":
                    cbWIFI.setSelected(true);
                    break;
                case "Breakfast Buffet":
                    cbBreakfast.setSelected(true);
                    break;
                case "Swimming Pool":
                    cbSwimming.setSelected(true);
                    break;
                case "Spa Services":
                    cbSpa.setSelected(true);
                    break;
                case "Airport Shuttle":
                    cbAirport.setSelected(true);
                    break;
                case "Gym Access":
                    cbGym.setSelected(true);
                    break;
            }
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading features: " + ex.getMessage());
    }
}

    private void updateSelectedFeaturesInDatabase() {
    try {
        Connection conn = DatabaseLayer.mycon();

        // Step 1: Delete old features for this checkin_id
        String deleteSql = "DELETE FROM checkin_features WHERE checkin_id = ?";
        PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
        deleteStmt.setInt(1, checkInId);
        deleteStmt.executeUpdate();
        deleteStmt.close();

        // Step 2: Insert new features
        String insertSql = "INSERT INTO checkin_features (checkin_id, customer_id, feature_id) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = conn.prepareStatement(insertSql);

        // Helper to insert based on checkbox and featureID
        insertIfChecked(cbWIFI, 1, insertStmt);
        insertIfChecked(cbBreakfast, 2, insertStmt);
        insertIfChecked(cbSwimming, 3, insertStmt);
        insertIfChecked(cbSpa, 4, insertStmt);
        insertIfChecked(cbAirport, 5, insertStmt);
        insertIfChecked(cbGym, 6, insertStmt);
        

        insertStmt.close();
        conn.close();
        
        // ✅ Step 3: Get reservation ID and update price
        int reservationId = getReservationIdForCheckIn(checkInId);
        System.out.println("Reservation ID: " + reservationId); 
        //updateReservationPriceBasedOnFeatures(reservationId);

        JOptionPane.showMessageDialog(this, "Features updated successfully.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating features: " + ex.getMessage());
    }
}

    // Helper method to reduce redundancy
    private void insertIfChecked(JCheckBox checkbox, int featureId, PreparedStatement stmt) throws SQLException {
    if (checkbox.isSelected()) {
        stmt.setInt(1, checkInId);
        stmt.setInt(2, customerId);
        stmt.setInt(3, featureId);
        stmt.executeUpdate();
    }
}

    //get reservationID for checking
    private int getReservationIdForCheckIn(int checkInId) throws SQLException {
    Connection conn = DatabaseLayer.mycon();
    
    String sql = "SELECT reservation_id FROM roomcheckin WHERE checkIn_id = ?";
    
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, checkInId);

    ResultSet rs = stmt.executeQuery();
    int reservationId = -1;
    if (rs.next()) {
        reservationId = rs.getInt("reservation_id");
    }

    rs.close();
    stmt.close();
    conn.close();

    return reservationId;
}

    //Update price
//    private void updateReservationPriceBasedOnFeatures(int reservationId) {
//    try {
//        Connection conn = DatabaseLayer.mycon();
//
//        // Step 1: Get base room price from reservation
//        String getPriceSql = "SELECT price FROM roomreservation WHERE reservation_id = ?";
//        PreparedStatement getPriceStmt = conn.prepareStatement(getPriceSql);
//        getPriceStmt.setInt(1, reservationId);
//        ResultSet priceRs = getPriceStmt.executeQuery();
//
//        double basePrice = 0.0;
//        if (priceRs.next()) {
//            basePrice = Double.parseDouble(priceRs.getString("price"));
//        }
//        priceRs.close();
//        getPriceStmt.close();
//
//        // Step 2: Calculate total selected feature price
//        String featurePriceSql = "SELECT SUM(f.price) AS feature_total " +
//                                 "FROM checkin_features cf " +
//                                 "JOIN features f ON cf.feature_id = f.featureID " +
//                                 "WHERE cf.checkin_id = ?";
//        PreparedStatement featureStmt = conn.prepareStatement(featurePriceSql);
//        featureStmt.setInt(1, checkInId); // make sure checkInId is known
//        ResultSet featureRs = featureStmt.executeQuery();
//
//        double featureTotal = 0.0;
//        if (featureRs.next()) {
//            featureTotal = featureRs.getDouble("feature_total");
//        }
//        featureRs.close();
//        featureStmt.close();
//
//        // Step 3: Update reservation price
//        double updatedPrice = basePrice + featureTotal;
//        
//        String updateSql = "UPDATE roomreservation SET price = ? WHERE reservation_id = ?";
//        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
//        updateStmt.setString(1, String.format("%.2f", updatedPrice));
//        updateStmt.setInt(2, reservationId);
//        updateStmt.executeUpdate();
//        updateStmt.close();
//
//        conn.close();
//
//        JOptionPane.showMessageDialog(this, "Reservation price updated successfully to " + updatedPrice);
//
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Error updating reservation price: " + ex.getMessage());
//    }
//}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        cbWIFI = new javax.swing.JCheckBox();
        cbBreakfast = new javax.swing.JCheckBox();
        cbSwimming = new javax.swing.JCheckBox();
        cbSpa = new javax.swing.JCheckBox();
        cbAirport = new javax.swing.JCheckBox();
        cbGym = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCheckIn = new javax.swing.JTextField();

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/newstock.png"))); // NOI18N
        jLabel1.setText("Customer ADD ONS");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Customer ID: ");

        txtCustomerID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Customer Name:");

        txtCustomerName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        cbWIFI.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbWIFI.setText("Free Wi-Fi");

        cbBreakfast.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbBreakfast.setText("Breakfast Buffet");

        cbSwimming.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbSwimming.setText("Swimming Pool");

        cbSpa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbSpa.setText("Spa Services");

        cbAirport.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbAirport.setText("Airport Shuttle");

        cbGym.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbGym.setText("Gym");

        jButton1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/check in.png"))); // NOI18N
        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Check In ID:");

        txtCheckIn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbSpa)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbBreakfast)
                                            .addComponent(cbAirport))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbGym)
                                            .addComponent(cbSwimming)))))
                            .addComponent(cbWIFI)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbWIFI)
                    .addComponent(cbBreakfast)
                    .addComponent(cbSwimming))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSpa)
                    .addComponent(cbAirport)
                    .addComponent(cbGym))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        updateSelectedFeaturesInDatabase();
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbAirport;
    private javax.swing.JCheckBox cbBreakfast;
    private javax.swing.JCheckBox cbGym;
    private javax.swing.JCheckBox cbSpa;
    private javax.swing.JCheckBox cbSwimming;
    private javax.swing.JCheckBox cbWIFI;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCheckIn;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtCustomerName;
    // End of variables declaration//GEN-END:variables
}
