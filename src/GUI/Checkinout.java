/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prash
 */
public class Checkinout extends javax.swing.JPanel {
    
    
    
    
    
    public Checkinout() {
        initComponents();
        roomcheckTable.setDefaultRenderer(Object.class, new StatusColorRenderer());
        roomcheckTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        roomcheckTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        roomcheckTable.getColumnModel().getColumn(2).setPreferredWidth(10);
        loadRoomReservationData();
        loadHallReservationData();
        
        roomcheckTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        roomcheckTableMouseClicked(evt);
            }
        });
 
    }

    class StatusColorRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String status = table.getValueAt(row, 6).toString(); // assuming status is at column index 6

        if (status.equalsIgnoreCase("Checked-IN")) {
            c.setBackground(new Color(198, 239, 206)); // light green
        } else if (status.equalsIgnoreCase("Checked-OUT")) {
            c.setBackground(new Color(255, 199, 206)); // light red
        } else {
            c.setBackground(Color.WHITE);
        }

        // Keep selected row visible with highlight
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        } else {
            c.setForeground(Color.BLACK);
        }

        return c;
    }
}

    
    private void loadRoomReservationData() {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        String sql = "SELECT * FROM `roomcheckin`";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel)roomcheckTable.getModel();
        model.setRowCount(0); 

        while (rs.next()) {
            Object[] row = {
                rs.getInt("checkIn_id"), 
                rs.getInt("reservation_id"), 
                rs.getInt("customer_id"),
                rs.getString("customerName"), 
                rs.getInt("room_id"), 
                rs.getString("reserved_date"), 
                rs.getString("status"), 
                rs.getString("checkInDate"),
                rs.getString("checkOutDate")
            };
            model.addRow(row); // Add row to JTable
        }

    } catch (SQLException ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error loading Data!!!");
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
    
    private void roomcheckTableMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {  // Check for double click
            int selectedRow = roomcheckTable.getSelectedRow();
            if (selectedRow != -1) {
                int checkInId = (int) roomcheckTable.getValueAt(selectedRow, 0);
                int customerId = (int) roomcheckTable.getValueAt(selectedRow, 2);
                String customerName = roomcheckTable.getValueAt(selectedRow, 3).toString();

                Customer_Add_On addOnPanel = new Customer_Add_On(checkInId, customerId, customerName);

                JFrame frame = new JFrame("Add-On Features for " + customerName);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(addOnPanel);
                frame.pack();
                frame.setLocationRelativeTo(null); // Center the frame
                frame.setVisible(true);
        }
    }
}

    private void loadHallReservationData() {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        String sql = "SELECT * FROM `hallcheckin`";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel)hallcheckTable.getModel();
        model.setRowCount(0); // Clear existing rows before adding new data

        while (rs.next()) {
            Object[] row = {
                rs.getInt("checkIn_id"), 
                rs.getInt("reservation_id"), 
                rs.getInt("customer_id"),
                rs.getString("customerName"), 
                rs.getString("hall_Name"), 
                rs.getString("reserved_date"), 
                rs.getString("status"), 
                rs.getString("checkInDate"),
                rs.getString("checkOutDate")
            };
            model.addRow(row); // Add row to JTable
        }

    } catch (SQLException ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error loading Data!!!");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomcheckTable = new javax.swing.JTable();
        btnRoomCheckOUT = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ComboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hallcheckTable = new javax.swing.JTable();
        btnhallCheckOUT = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ComboSearch1 = new javax.swing.JComboBox<>();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();

        jPanel7.setBackground(new java.awt.Color(255, 51, 51));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setText("Check In / Out Details");

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

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        roomcheckTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        roomcheckTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Check-IN_ID", "Reservation_ID", "Customer_ID", "Customer_Name", "Room_ID", "Reservation_Date", "Status", "CheckIN_Date", "CheckOUT_Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        roomcheckTable.setRowHeight(30);
        jScrollPane1.setViewportView(roomcheckTable);
        if (roomcheckTable.getColumnModel().getColumnCount() > 0) {
            roomcheckTable.getColumnModel().getColumn(0).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(1).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(2).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(3).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(4).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(5).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(6).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(7).setResizable(false);
            roomcheckTable.getColumnModel().getColumn(8).setResizable(false);
        }

        btnRoomCheckOUT.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnRoomCheckOUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/remove.png"))); // NOI18N
        btnRoomCheckOUT.setText("Check OUT");
        btnRoomCheckOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomCheckOUTActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Search By :");

        ComboSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Check-In ID", "Reservation ID", "Customer ID", "Customer Name", "Room ID", "Status" }));

        txtSearch.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/search x30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRoomCheckOUT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 281, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRoomCheckOUT)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Room Booking", jPanel3);

        hallcheckTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        hallcheckTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Check-IN_ID", "Reservation_ID", "Customer_ID", "Customer_Name", "Room_ID", "Reservation_Date", "Status", "CheckIN_Date", "CheckOUT_Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        hallcheckTable.setRowHeight(25);
        jScrollPane2.setViewportView(hallcheckTable);
        if (hallcheckTable.getColumnModel().getColumnCount() > 0) {
            hallcheckTable.getColumnModel().getColumn(0).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(1).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(2).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(3).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(4).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(5).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(6).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(7).setResizable(false);
            hallcheckTable.getColumnModel().getColumn(8).setResizable(false);
        }

        btnhallCheckOUT.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnhallCheckOUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/remove.png"))); // NOI18N
        btnhallCheckOUT.setText("Check OUT");
        btnhallCheckOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhallCheckOUTActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Search By :");

        ComboSearch1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Check-In ID", "Reservation ID", "Customer ID", "Customer Name", "Room ID", "Status" }));
        ComboSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSearch1ActionPerformed(evt);
            }
        });

        txtSearch1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch1KeyReleased(evt);
            }
        });

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/search x30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnhallCheckOUT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 281, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(btnhallCheckOUT)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Hall Booking", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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

    private void txtSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1KeyReleased

    private void ComboSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSearch1ActionPerformed

    private void btnhallCheckOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhallCheckOUTActionPerformed
        // TODO add your handling code here:
        int selectedRow = hallcheckTable.getSelectedRow(); 

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to check out.");
            return;
        }
        
        int customerId = Integer.parseInt(hallcheckTable.getValueAt(selectedRow, 2).toString());
        String checkInID = hallcheckTable.getValueAt(selectedRow, 0).toString();
        String reservationID = hallcheckTable.getValueAt(selectedRow, 1).toString();

        
        String checkoutDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;

        try {
            String sql = "UPDATE hallcheckin SET status = ?, checkOutDate = ? WHERE checkIn_id = ?";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, "Checked-OUT");
            pst.setString(2, checkoutDate);
            pst.setInt(3, Integer.parseInt(checkInID));

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                String sql1 = "DELETE FROM hallreservation  WHERE reservation_id  = ?";
                pst1 = con.prepareStatement(sql1);
                pst.setString(1, reservationID);
                pst.executeUpdate();
            
                JOptionPane.showMessageDialog(this, "Customer of customer ID "+customerId +"successfully checked out.");
                loadHallReservationData(); 
                
                PaymentHall paymentPage = new PaymentHall(checkInID);
                JFrame paymentFrame = new JFrame("Payment Page");
                paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                paymentFrame.getContentPane().add(paymentPage);
                paymentFrame.pack();
                paymentFrame.setLocationRelativeTo(null); 
                paymentFrame.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(this, "Error: No matching record found.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnhallCheckOUTActionPerformed

    private void btnRoomCheckOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomCheckOUTActionPerformed

        int selectedRow = roomcheckTable.getSelectedRow(); 

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to check out.");
            return;
         }
        
        String checkInID = roomcheckTable.getValueAt(selectedRow, 0).toString();
        String customerID = roomcheckTable.getValueAt(selectedRow, 2).toString();
        String roomNo = roomcheckTable.getValueAt(selectedRow, 4).toString();
        String reservationID = roomcheckTable.getValueAt(selectedRow, 1).toString();

        // Get current date
        String checkoutDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;

        try {
            String sql = "UPDATE roomcheckin SET status = ?, checkOutDate = ? WHERE checkIn_id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, "Checked-OUT");
            pst.setString(2, checkoutDate);
            pst.setInt(3, Integer.parseInt(checkInID));

            int rowsUpdated = pst.executeUpdate();
            
            if (rowsUpdated > 0) {
                String sql2 = "UPDATE rooms SET availability = 'Available' WHERE room_no= ?";
                pst1 = con.prepareStatement(sql2);
                pst1.setString(1, roomNo); 
                pst1.executeUpdate();
                
                String sql3 = "DELETE FROM roomreservation WHERE reservation_id = ?";
                pst2 = con.prepareStatement(sql3);
                pst2.setString(1, reservationID); 
                pst2.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Customer of customer ID"+customerID +"successfully checked out.");
                loadRoomReservationData(); 
                
                Payment paymentPage = new Payment(checkInID);
                JFrame paymentFrame = new JFrame("Payment Page");
                paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                paymentFrame.getContentPane().add(paymentPage);
                paymentFrame.pack();
                paymentFrame.setLocationRelativeTo(null); 
                paymentFrame.setVisible(true);
                
                
            } else {
                JOptionPane.showMessageDialog(this, "Error: No matching record found.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnRoomCheckOUTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboSearch;
    private javax.swing.JComboBox<String> ComboSearch1;
    private javax.swing.JButton btnRoomCheckOUT;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnhallCheckOUT;
    private javax.swing.JTable hallcheckTable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable roomcheckTable;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables
}
