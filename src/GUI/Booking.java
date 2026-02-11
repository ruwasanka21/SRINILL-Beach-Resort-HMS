
package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Booking extends javax.swing.JPanel {

    public Booking() {
        initComponents();
        loadRoomReservationData();
        addRoomTableClickListener();
        loadHallBooking();
        addHallTableClickListener();
        hallBookTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        hallBookTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        hallBookTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        hallBookTable.getColumnModel().getColumn(5).setPreferredWidth(90);
        txtRoomPrice.setEditable(false);
    }
    
    private void loadRoomReservationData() {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        String sql = "SELECT r.reservation_id, r.customer_id, c.name AS customer_name, r.room_id, " +
                     "r.reserve_Date, r.checkInDate, r.price, r.status " +
                     "FROM roomreservation r " +
                     "INNER JOIN customers c ON r.customer_id = c.customer_id";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel)roomBookTabe.getModel();
        model.setRowCount(0); 

        while (rs.next()) {
            Object[] row = {
                rs.getInt("reservation_id"), 
                rs.getInt("customer_id"), 
                rs.getString("customer_name"),
                rs.getInt("room_id"), 
                rs.getString("reserve_Date"), 
                rs.getString("checkInDate"), 
                rs.getString("price"), 
                rs.getString("status")
            };
            model.addRow(row); // Add row to JTable
        }

    } catch (SQLException ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error loading Room Booking Data!!!");
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
    
    private void addRoomTableClickListener() {
        roomBookTabe.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = roomBookTabe.getSelectedRow();
                if (row != -1) {
                    // Get data from the selected row
                    String reservationID = roomBookTabe.getValueAt(row, 0).toString();
                    String customerID = roomBookTabe.getValueAt(row, 1).toString();
                    String customerName = roomBookTabe.getValueAt(row, 2).toString();
                    String roomID = roomBookTabe.getValueAt(row, 3).toString();
                    String reservDate = roomBookTabe.getValueAt(row, 4).toString();
                    String checkInDate = roomBookTabe.getValueAt(row, 5).toString();
                    String price = roomBookTabe.getValueAt(row, 6).toString();
                    String status = roomBookTabe.getValueAt(row, 7).toString();
                    
                    
                    // Set values in the text fields
                    txtRoomReserID.setText(reservationID);
                    txtRoomCustomerID.setText(customerID);
                    txtRoomCustomerName.setText(customerName);
                    txtRoomID.setText(roomID);
                    txtRoomReserveDate.setText(reservDate);
                    txtRoomCheckIN.setText(checkInDate);
                    txtRoomPrice.setText(price);
                    txtRoomStatus.setText(status);
                    
                    
                   
                }
            }
        });
    }
    
    private void addHallTableClickListener() {
        hallBookTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = hallBookTable.getSelectedRow();
                if (row != -1) {
                    // Get data from the selected row
                    String reservationID = hallBookTable.getValueAt(row, 0).toString();
                    String customerID = hallBookTable.getValueAt(row, 1).toString();
                    String customerName = hallBookTable.getValueAt(row, 2).toString();
                    String roomID = hallBookTable.getValueAt(row, 3).toString();
                    String reservDate = hallBookTable.getValueAt(row, 4).toString();
                    String checkInDate = hallBookTable.getValueAt(row, 5).toString();
                    String price = hallBookTable.getValueAt(row, 6).toString();
                    String status = hallBookTable.getValueAt(row, 7).toString();
                    
                    
                    // Set values in the text fields
                    txthallReservID.setText(reservationID);
                    txtHallCustomerID.setText(customerID);
                    txtHallCustomerName.setText(customerName);
                    txtHallName.setText(roomID);
                    txtHallReserDate.setText(reservDate);
                    txtHallCheckInDate.setText(checkInDate);
                    txtHallPrice.setText(price);
                    txtHallStatus.setText(status);
                    
                    
                   
                }
            }
        });
    }
    
    private void loadHallBooking() {
    Connection con = DatabaseLayer.mycon();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        String sql = "SELECT h.reservation_id, h.customer_id, c.name AS customer_name, h.hallName, " +
                     "h.reserve_Date, h.checkInDate, h.price, h.status " +
                     "FROM hallreservation h " +
                     "INNER JOIN customers c ON h.customer_id = c.customer_id";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) hallBookTable.getModel();
        model.setRowCount(0); // Clear existing rows before adding new data

        while (rs.next()) {
            Object[] row = {
                rs.getInt("reservation_id"), 
                rs.getInt("customer_id"), 
                rs.getString("customer_name"),
                rs.getString("hallName"), 
                rs.getString("reserve_Date"), 
                rs.getString("checkInDate"), 
                rs.getString("price"), 
                rs.getString("status")
            };
            model.addRow(row); // Add row to JTable
        }

    } catch (SQLException ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error loading hall booking data!!!");
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtRoomReserID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRoomCustomerID = new javax.swing.JTextField();
        txtRoomCustomerName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRoomID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRoomReserveDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRoomCheckIN = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtRoomPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRoomStatus = new javax.swing.JTextField();
        btnRoomClear = new javax.swing.JButton();
        btnRoomUpdate = new javax.swing.JButton();
        btnRoomDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomBookTabe = new javax.swing.JTable();
        btnroomCheckIn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txthallReservID = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtHallCustomerID = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtHallCustomerName = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtHallName = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtHallReserDate = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtHallCheckInDate = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtHallPrice = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtHallStatus = new javax.swing.JTextField();
        btnHallClear = new javax.swing.JButton();
        btnHallUpdate = new javax.swing.JButton();
        btnHallDelete = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        hallBookTable = new javax.swing.JTable();
        btnhallCheck = new javax.swing.JButton();

        jPanel7.setBackground(new java.awt.Color(102, 255, 204));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setText("Booking Details");

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

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Customer Name :");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Reservation ID :");

        txtRoomReserID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Customer ID :");

        txtRoomCustomerID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtRoomCustomerName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Room ID :");

        txtRoomID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Reservation Date :");

        txtRoomReserveDate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Check IN Date :");

        txtRoomCheckIN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Price :");

        txtRoomPrice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Status :");

        txtRoomStatus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        btnRoomClear.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnRoomClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/clear.png"))); // NOI18N
        btnRoomClear.setText("Clear");
        btnRoomClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomClearActionPerformed(evt);
            }
        });

        btnRoomUpdate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnRoomUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/update.png"))); // NOI18N
        btnRoomUpdate.setText("Update");
        btnRoomUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomUpdateActionPerformed(evt);
            }
        });

        btnRoomDelete.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnRoomDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/delete.png"))); // NOI18N
        btnRoomDelete.setText("Delete");
        btnRoomDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnRoomClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRoomUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRoomDelete))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtRoomCustomerID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txtRoomReserID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(19, 19, 19))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtRoomID)
                                .addComponent(txtRoomCheckIN)
                                .addComponent(txtRoomStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRoomPrice)
                                .addComponent(txtRoomCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtRoomReserveDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRoomReserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRoomCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRoomCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRoomReserveDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRoomCheckIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRoomPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRoomStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRoomClear)
                    .addComponent(btnRoomUpdate)
                    .addComponent(btnRoomDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roomBookTabe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        roomBookTabe.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        roomBookTabe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Reserve ID", "Cus ID", "Cus Name", "Room ID", "Reserve Date", "Check In Date", "Price", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        roomBookTabe.setRowHeight(30);
        jScrollPane1.setViewportView(roomBookTabe);

        btnroomCheckIn.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnroomCheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/check in.png"))); // NOI18N
        btnroomCheckIn.setText("Check IN");
        btnroomCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroomCheckInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnroomCheckIn)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnroomCheckIn)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Room Booking", jPanel3);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Reservation ID :");

        txthallReservID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Customer ID :");

        txtHallCustomerID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Customer Name :");

        txtHallCustomerName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Hall Name :");

        txtHallName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Reservation Date :");

        txtHallReserDate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Event Date :");

        txtHallCheckInDate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Price :");

        txtHallPrice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Status :");

        txtHallStatus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        btnHallClear.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnHallClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/clear.png"))); // NOI18N
        btnHallClear.setText("Clear");
        btnHallClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallClearActionPerformed(evt);
            }
        });

        btnHallUpdate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnHallUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/update.png"))); // NOI18N
        btnHallUpdate.setText("Update");
        btnHallUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallUpdateActionPerformed(evt);
            }
        });

        btnHallDelete.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnHallDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/delete.png"))); // NOI18N
        btnHallDelete.setText("Delete");
        btnHallDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(txthallReservID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtHallReserDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHallPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHallStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHallCustomerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHallCustomerID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHallCheckInDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txtHallName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnHallClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHallUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHallDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthallReservID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtHallCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtHallCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtHallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtHallReserDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtHallCheckInDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtHallPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtHallStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHallClear)
                    .addComponent(btnHallUpdate)
                    .addComponent(btnHallDelete))
                .addGap(76, 76, 76))
        );

        hallBookTable.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        hallBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Reserve ID", "Cus ID", "Cus Name", "Hall ID", "Reserve Date", "Event Date", "Price", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        hallBookTable.setRowHeight(30);
        jScrollPane4.setViewportView(hallBookTable);

        btnhallCheck.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnhallCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/check in.png"))); // NOI18N
        btnhallCheck.setText("Check IN");
        btnhallCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhallCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnhallCheck)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnhallCheck)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hall Booking", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
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

    private void btnHallClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallClearActionPerformed
        // TODO add your handling code here:
        txthallReservID.setText("");
        txtHallCheckInDate.setText("");
        txtHallCustomerID.setText("");
        txtHallCustomerName.setText("");
        txtHallPrice.setText("");
        txtHallReserDate.setText("");
        txtHallName.setText("");
        txtHallStatus.setText("");
    }//GEN-LAST:event_btnHallClearActionPerformed

    private void btnHallUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallUpdateActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        String reservationID = txthallReservID.getText();
        String customerID = txtHallCustomerID.getText();
        String customerName = txtHallCustomerName.getText();
        String hallName = txtHallName.getText();
        String reservDate = txtHallReserDate.getText();
        String checkInDate = txtHallCheckInDate.getText();
        String price = txtHallPrice.getText();
        String status = txtHallStatus.getText();

        try {
            String sql = "UPDATE hallreservation SET hallName = ?, reserve_Date = ?, checkInDate = ?, price = ?, status = ? " +
            "WHERE reservation_id = ? AND customer_id = ?";

            pst = con.prepareStatement(sql);
            pst.setString(1,hallName );
            pst.setString(2, reservDate);
            pst.setString(3, checkInDate);
            pst.setString(4, price);
            pst.setString(5, status);
            pst.setInt(6, Integer.parseInt(reservationID));
            pst.setInt(7, Integer.parseInt(customerID));

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Hall Reservation ID: " + reservationID + "\nCustomer name: "+customerName+" \nUpdated Successfully!");
                loadHallBooking();

                // Clear fields
                txthallReservID.setText("");
                txtHallCheckInDate.setText("");
                txtHallCustomerID.setText("");
                txtHallCustomerName.setText("");
                txtHallPrice.setText("");
                txtHallReserDate.setText("");
                txtHallName.setText("");
                txtHallStatus.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "No matching reservation found to update!");
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
    }//GEN-LAST:event_btnHallUpdateActionPerformed

    private void btnHallDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallDeleteActionPerformed
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        String reservationID = txthallReservID.getText();
        String customerID = txtHallCustomerID.getText();
        String hallName = txtHallName.getText();

        if (reservationID.isEmpty() || customerID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a reservation to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this hall reservation?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM hallreservation WHERE reservation_id = ? AND customer_id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(reservationID));
                pst.setInt(2, Integer.parseInt(customerID));

                int rowsDeleted = pst.executeUpdate();

                if (rowsDeleted > 0) {
                    // Update hall availability
                    String updateHallSQL = "UPDATE halls SET Availability = 'Available' WHERE hall_name = ?";
                    pst = con.prepareStatement(updateHallSQL);
                    pst.setString(1, hallName);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Hall reservation deleted successfully.");

                    loadHallBooking(); // Refresh the hall table

                    // Clear hall reservation fields
                    txthallReservID.setText("");
                    txtHallCustomerID.setText("");
                    txtHallCustomerName.setText("");
                    txtHallName.setText("");
                    txtHallReserDate.setText("");
                    txtHallCheckInDate.setText("");
                    txtHallPrice.setText("");
                    txtHallStatus.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No matching reservation found to delete.");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting reservation: " + ex.getMessage());
            } finally {
                try {
                    if (pst != null) pst.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnHallDeleteActionPerformed

    private void btnhallCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhallCheckActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to check in this customer?", "Confirm Check-In", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        String reservationID = txthallReservID.getText();
        String customerID = txtHallCustomerID.getText();
        String customerName = txtHallCustomerName.getText();
        String hallName = txtHallName.getText();
        String reservDate = txtHallReserDate.getText();
        LocalDate currentDate = LocalDate.now();

        try {
            String sql = "INSERT INTO `hallcheckin`(`reservation_id`, `customer_id`, `customerName`, `hall_Name`, `reserved_date`, `status`,`checkInDate`) VALUES (?,?,?,?,?,?,?)";

            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(reservationID));
            pst.setInt(2, Integer.parseInt(customerID));
            pst.setString(3, customerName);
            pst.setString(4, hallName);
            pst.setString(5, reservDate);
            pst.setString(6, "Checked-IN");
            pst.setString(7, currentDate.toString());

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Hall Reservation ID: " + reservationID + " Checked IN Successfully!");
                loadRoomReservationData();

                // Clear fields
                txthallReservID.setText("");
                txtHallCustomerID.setText("");
                txtHallCustomerName.setText("");
                txtHallName.setText("");
                txtHallReserDate.setText("");
                txtHallCheckInDate.setText("");
                txtHallPrice.setText("");
                txtHallStatus.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No matching reservation found to update!");
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
    }//GEN-LAST:event_btnhallCheckActionPerformed

    private void btnRoomClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomClearActionPerformed
        // TODO add your handling code here:
        txtRoomReserID.setText("");
        txtRoomCustomerID.setText("");
        txtHallCustomerName.setText("");
        txtRoomID.setText("");
        txtRoomReserveDate.setText("");
        txtRoomCheckIN.setText("");
        txtRoomPrice.setText("");
        txtRoomStatus.setText("");
    }//GEN-LAST:event_btnRoomClearActionPerformed

    private void btnRoomUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomUpdateActionPerformed
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        String reservationID = txtRoomReserID.getText();
        String customerID = txtRoomCustomerID.getText();
        String roomID = txtRoomID.getText();
        String reservDate = txtRoomReserveDate.getText();
        String checkInDate = txtRoomCheckIN.getText();
        String price = txtRoomPrice.getText();
        String status = txtRoomStatus.getText();

        try {
            String sql = "UPDATE roomreservation SET room_id = ?, reserve_Date = ?, checkInDate = ?, price = ?, status = ? " +
            "WHERE reservation_id = ? AND customer_id = ?";

            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(roomID));
            pst.setString(2, reservDate);
            pst.setString(3, checkInDate);
            pst.setString(4, price);
            pst.setString(5, status);
            pst.setInt(6, Integer.parseInt(reservationID));
            pst.setInt(7, Integer.parseInt(customerID));

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Room Reservation ID: " + reservationID + " Updated Successfully!");
                loadRoomReservationData();

                // Clear fields
                txtRoomReserID.setText("");
                txtRoomCustomerID.setText("");
                txtRoomID.setText("");
                txtRoomReserveDate.setText("");
                txtRoomCheckIN.setText("");
                txtRoomPrice.setText("");
                txtRoomStatus.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No matching reservation found to update!");
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
    }//GEN-LAST:event_btnRoomUpdateActionPerformed

    private void btnRoomDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomDeleteActionPerformed
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        String reservationID = txtRoomReserID.getText();
        String customerID = txtRoomCustomerID.getText();
        String roomID = txtRoomID.getText();

        if (reservationID.isEmpty() || customerID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a reservation to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this room reservation?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM roomreservation WHERE reservation_id = ? AND customer_id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(reservationID));
                pst.setInt(2, Integer.parseInt(customerID));

                int rowsDeleted = pst.executeUpdate();

                if (rowsDeleted > 0) {
                    String updateRoomSQL = "UPDATE rooms SET availability = 'Available' WHERE room_no = ?";
                    pst = con.prepareStatement(updateRoomSQL);
                    pst.setInt(1, Integer.parseInt(roomID));
                    pst.executeUpdate();
                    con.commit();

                    JOptionPane.showMessageDialog(this, "Room reservation deleted successfully.");
                    loadRoomReservationData(); // Refresh table

                    // Clear fields
                    txtRoomReserID.setText("");
                    txtRoomCustomerID.setText("");
                    txtRoomID.setText("");
                    txtRoomReserveDate.setText("");
                    txtRoomCheckIN.setText("");
                    txtRoomPrice.setText("");
                    txtRoomStatus.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No matching reservation found to delete.");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting reservation: " + ex.getMessage());
            } finally {
                try {
                    if (pst != null) pst.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnRoomDeleteActionPerformed

    private void btnroomCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroomCheckInActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to check in this customer?", "Confirm Check-In", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        String reservationID = txtRoomReserID.getText();
        String customerID = txtRoomCustomerID.getText();
        String customerName = txtRoomCustomerName.getText();
        String roomID = txtRoomID.getText();
        String reservDate = txtRoomReserveDate.getText();
        LocalDate currentDate = LocalDate.now();

        try {
            String sql = "INSERT INTO `roomcheckin`(`reservation_id`, `customer_id`, `customerName`, `room_id`, `reserved_date`, `status`,`checkInDate`) VALUES (?,?,?,?,?,?,?)";

            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(reservationID));
            pst.setString(2, customerID);
            pst.setString(3, customerName);
            pst.setString(4, roomID);
            pst.setString(5, reservDate);
            pst.setString(6, "Checked-IN");
            pst.setString(7, currentDate.toString());

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Room Reservation ID: " + reservationID + " Checked IN Successfully!");
                loadRoomReservationData();

                // Clear fields
                txtRoomReserID.setText("");
                txtRoomCustomerID.setText("");
                txtRoomCustomerName.setText("");
                txtRoomID.setText("");
                txtRoomReserveDate.setText("");
                txtRoomCheckIN.setText("");
                txtRoomPrice.setText("");
                txtRoomStatus.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No matching reservation found to update!");
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
    }//GEN-LAST:event_btnroomCheckInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHallClear;
    private javax.swing.JButton btnHallDelete;
    private javax.swing.JButton btnHallUpdate;
    private javax.swing.JButton btnRoomClear;
    private javax.swing.JButton btnRoomDelete;
    private javax.swing.JButton btnRoomUpdate;
    private javax.swing.JButton btnhallCheck;
    private javax.swing.JButton btnroomCheckIn;
    private javax.swing.JTable hallBookTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable roomBookTabe;
    private javax.swing.JTextField txtHallCheckInDate;
    private javax.swing.JTextField txtHallCustomerID;
    private javax.swing.JTextField txtHallCustomerName;
    private javax.swing.JTextField txtHallName;
    private javax.swing.JTextField txtHallPrice;
    private javax.swing.JTextField txtHallReserDate;
    private javax.swing.JTextField txtHallStatus;
    private javax.swing.JTextField txtRoomCheckIN;
    private javax.swing.JTextField txtRoomCustomerID;
    private javax.swing.JTextField txtRoomCustomerName;
    private javax.swing.JTextField txtRoomID;
    private javax.swing.JTextField txtRoomPrice;
    private javax.swing.JTextField txtRoomReserID;
    private javax.swing.JTextField txtRoomReserveDate;
    private javax.swing.JTextField txtRoomStatus;
    private javax.swing.JTextField txthallReservID;
    // End of variables declaration//GEN-END:variables
}
