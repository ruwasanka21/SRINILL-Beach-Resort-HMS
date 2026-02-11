package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Facilities extends javax.swing.JPanel {

    public Facilities() {
        initComponents();
        HallTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        Rooms_Table_load();
        doubleClicked();
        load_Halls_Data();
        Table_Row_Click();
    }
    
    //Fetching Rooms data from rooms table in database - Kavindi
    public void Rooms_Table_load() {
        try {
            DefaultTableModel dt =  (DefaultTableModel) RoomsTable.getModel();
            dt.setRowCount(0);
            
            Statement s =  DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM rooms ORDER BY room_no");
            int count = 1;
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(count));
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                
                dt.addRow(v);
                
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    //double clicked Row - Kavindi
    public void doubleClicked() {
        RoomsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { 
                    int rownumber = RoomsTable.getSelectedRow();
                    if (rownumber != -1) {
                        String roomNo = RoomsTable.getValueAt(rownumber, 1).toString();
                        
                        //load data to textfield and combobox
                        try {
                            Statement s =  DatabaseLayer.mycon().createStatement();
                            ResultSet rs = s.executeQuery("SELECT * FROM rooms WHERE room_no = '"+roomNo+"'");
                            
                            if (rs.next()) {
                                txtRoomNo.setText(rs.getString("room_no"));
                                ComboCategory.setSelectedItem(rs.getString("category"));
                                ComboAvailability.setSelectedItem(rs.getString("availability"));
                                ComboStatus.setSelectedItem(rs.getString("status"));
                                ComboBedType.setSelectedItem(rs.getString("bed_type"));
                                ComboFloor.setSelectedItem(rs.getString("floor"));
                                txtPrice.setText(rs.getString("price"));
                                cbAC.setSelected(rs.getString("ac").equals("1"));  
                                cbfan.setSelected(rs.getString("fan").equals("1")); 
                                cbTV.setSelected(rs.getString("tv").equals("1"));
                                cbMiniBar.setSelected(rs.getString("mini_bar").equals("1")); 
                                cbBalcony.setSelected(rs.getString("balcony_view").equals("1"));
                                cbWifi.setSelected(rs.getString("wifi_access").equals("1"));
                                cbBathroom.setSelected(rs.getString("attached_bathroom").equals("1"));
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    
    //clear all textfields - Kavindi
    public void clear() {
        txtRoomNo.setText("");
        ComboCategory.setSelectedIndex(0);
        ComboAvailability.setSelectedIndex(0);
        ComboStatus.setSelectedIndex(0);
        ComboBedType.setSelectedIndex(0);
        ComboFloor.setSelectedIndex(0);
        txtPrice.setText("");
        cbAC.setSelected(false);
        cbfan.setSelected(false);
        cbTV.setSelected(false);
        cbMiniBar.setSelected(false);
        cbBalcony.setSelected(false);
        cbWifi.setSelected(false);
        cbBathroom.setSelected(false);
    }
    
    
    //Hall  - Thenuli
    private void load_Halls_Data() {
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM halls";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel)HallTable.getModel();
            model.setRowCount(0); // Clear existing rows before adding new data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("hall_id"),
                    rs.getString("hall_name"), 
                    rs.getString("capacity"), 
                    rs.getString("price"), 
                    rs.getString("Availability"), 
                    rs.getString("status"), 
                    rs.getString("created_at"), 

                };
                model.addRow(row); // Add row to JTable
            }

        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error loading Halls data!");
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
    
    //Hall  - Thenuli
    private void Table_Row_Click() {
        HallTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = HallTable.getSelectedRow();
                if (row != -1) {
                    // Get data from the selected row
                    String hallID = HallTable.getValueAt(row, 0).toString();
                    String name = HallTable.getValueAt(row, 1).toString();
                    String capacity = HallTable.getValueAt(row, 2).toString();
                    String price = HallTable.getValueAt(row, 3).toString();
                    String availability = HallTable.getValueAt(row, 4).toString();
                    String status = HallTable.getValueAt(row, 5).toString();
                    
                    // Set values in the text fields
                    txtHallID.setText(hallID);
                    txtHallName.setText(name);
                    txtHallCapacity.setText(capacity);
                    txtHallPrice.setText(price);
                    txtHallAvailability.setSelectedItem(availability);
                    txtHallStatus.setSelectedItem(status);
                    
                 
                }
            }
        });
    }
    
    //Hall - Thenuli
    public void clear_halls() {
        txtHallID.setText("");
        txtHallName.setText("");
        txtHallCapacity.setText("");
        txtHallPrice.setText("");
        txtHallAvailability.setSelectedIndex(0);
        txtHallStatus.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRoomNo = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        ComboCategory = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ComboAvailability = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        ComboStatus = new javax.swing.JComboBox<>();
        ComboBedType = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ComboFloor = new javax.swing.JComboBox<>();
        cbAC = new javax.swing.JCheckBox();
        cbWifi = new javax.swing.JCheckBox();
        cbTV = new javax.swing.JCheckBox();
        cbMiniBar = new javax.swing.JCheckBox();
        cbBathroom = new javax.swing.JCheckBox();
        cbBalcony = new javax.swing.JCheckBox();
        cbfan = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RoomsTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtHallID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHallCapacity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtHallAvailability = new javax.swing.JComboBox<>();
        btnHallSave = new javax.swing.JButton();
        btnHallUpdate = new javax.swing.JButton();
        txtHallStatus = new javax.swing.JComboBox<>();
        txtHallPrice = new javax.swing.JTextField();
        txtHallName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnHallDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        HallTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1189, 597));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 51, 102));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel2.setText("Facilitiy Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Room No :");

        txtRoomNo.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

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

        ComboCategory.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        ComboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Single", "Double", "Suite", "Deluxe" }));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Category :");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Availability :");

        ComboAvailability.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        ComboAvailability.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Available", "Booked" }));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("Status :");

        ComboStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        ComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Clean", "Dirty" }));

        ComboBedType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        ComboBedType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "King", "Queen", "Twin" }));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("Bed type :");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("Floor :");

        ComboFloor.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        ComboFloor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "1", "2", "3", "4" }));

        cbAC.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cbAC.setText("AC");

        cbWifi.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cbWifi.setText("Wi-Fi Access");

        cbTV.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cbTV.setText("TV");

        cbMiniBar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cbMiniBar.setText("Mini Bar");

        cbBathroom.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cbBathroom.setText("Attached Bathroom");

        cbBalcony.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cbBalcony.setText("Balcony View");

        cbfan.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cbfan.setText("Fan");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Price :");

        txtPrice.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboBedType, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbAC)
                                            .addComponent(cbMiniBar)
                                            .addComponent(cbBalcony))
                                        .addGap(67, 67, 67)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbTV)
                                            .addComponent(cbfan)
                                            .addComponent(cbWifi)))
                                    .addComponent(cbBathroom))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboCategory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboAvailability))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBedType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboFloor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTV)
                    .addComponent(cbAC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMiniBar)
                    .addComponent(cbfan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBalcony)
                    .addComponent(cbWifi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbBathroom)
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        RoomsTable.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        RoomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Room No", "Category", "Bed Type", "Floor", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RoomsTable.setRowHeight(30);
        jScrollPane1.setViewportView(RoomsTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Rooms  ", jPanel5);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("ID : ");

        txtHallID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Capacity : ");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Price : ");

        txtHallCapacity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Availability : ");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Status : ");

        txtHallAvailability.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtHallAvailability.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Available", "Reserved" }));

        btnHallSave.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnHallSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/save.png"))); // NOI18N
        btnHallSave.setText("Save");
        btnHallSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallSaveActionPerformed(evt);
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

        txtHallStatus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtHallStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Cleaned", "Dirty", "Repair" }));

        txtHallPrice.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        txtHallName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Name :");

        btnHallDelete.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnHallDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/remove.png"))); // NOI18N
        btnHallDelete.setText("Delete");
        btnHallDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnHallSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnHallUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHallDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHallStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHallAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHallPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHallCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHallID, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHallName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHallAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHallStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHallSave)
                    .addComponent(btnHallUpdate)
                    .addComponent(btnHallDelete))
                .addGap(128, 128, 128))
        );

        HallTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        HallTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Hall ID", "Name", "Capacity", "Price", "Availabiblity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HallTable.setRowHeight(30);
        jScrollPane2.setViewportView(HallTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Halls  ", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1117, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("  Vehicles  ", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1117, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("  Pools  ", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    //Kavindi
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String room_no = txtRoomNo.getText().trim();
        String category = (String) ComboCategory.getSelectedItem();
        String availability = (String) ComboAvailability.getSelectedItem();
        String status = (String) ComboStatus.getSelectedItem();
        String bed_type = (String) ComboBedType.getSelectedItem();
        String floor = (String) ComboFloor.getSelectedItem();
        String price = txtPrice.getText().trim();
        String ac = cbAC.isSelected() ? "1" : "0";
        String fan = cbfan.isSelected() ? "1" : "0";
        String tv = cbTV.isSelected() ? "1" : "0";
        String mini_bar = cbMiniBar.isSelected() ? "1" : "0";
        String balcony_view = cbBalcony.isSelected() ? "1" : "0";
        String wifi_access = cbWifi.isSelected() ? "1" : "0";
        String attached_bathroom = cbBathroom.isSelected() ? "1" : "0";

        if (room_no.isEmpty() || category == null || availability == null || status == null ||
            bed_type == null || floor == null || price.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!room_no.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Room number must be numeric.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!price.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Price must be a valid number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection con = DatabaseLayer.mycon();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM rooms WHERE room_no = ?");
            ps.setString(1, room_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Room number already exists. Please enter a unique room number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO rooms (room_no, category, availability, status, bed_type, floor, price, ac, fan, tv, mini_bar, balcony_view, wifi_access, attached_bathroom) VALUES ('"+room_no+"', '"+category+"', '"+availability+"', '"+status+"', '"+bed_type+"', '"+floor+"', '"+price+"', '"+ac+"', '"+fan+"', '"+tv+"', '"+mini_bar+"', '"+balcony_view+"', '"+wifi_access+"', '"+attached_bathroom+"')");

            Rooms_Table_load();
            clear();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    //Kavindi
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String room_no = txtRoomNo.getText().trim();
        String category = (String) ComboCategory.getSelectedItem();
        String availability = (String) ComboAvailability.getSelectedItem();
        String status =(String) ComboStatus.getSelectedItem();
        String bed_type = (String) ComboBedType.getSelectedItem();
        String floor = (String) ComboFloor.getSelectedItem();
        String price = txtPrice.getText();
        String ac = cbAC.isSelected() ? "1" : "0";
        String fan = cbfan.isSelected() ? "1" : "0";
        String tv = cbTV.isSelected() ? "1" : "0";
        String mini_bar = cbMiniBar.isSelected() ? "1" : "0";
        String balcony_view = cbBalcony.isSelected() ? "1" : "0";
        String wifi_access = cbWifi.isSelected() ? "1" : "0";
        String attached_bathroom = cbBathroom.isSelected() ? "1" : "0";
        
        try {
            Statement s =  DatabaseLayer.mycon().createStatement();
            s.executeUpdate("UPDATE rooms SET category = '"+category+"', availability = '"+availability+"', status = '"+status+"', bed_type = '"+bed_type+"', floor = '"+floor+"', price = '"+price+"', ac = '"+ac+"', fan = '"+fan+"', tv = '"+tv+"', mini_bar = '"+mini_bar+"', balcony_view = '"+balcony_view+"', wifi_access = '"+wifi_access+"', attached_bathroom = '"+attached_bathroom+"' WHERE room_no = '"+room_no+"'");
            JOptionPane.showMessageDialog(null, "Room updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            Rooms_Table_load();
            clear();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed
    //Kavindi
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
            String room_no = txtRoomNo.getText().trim();
            try {
                Statement s =  DatabaseLayer.mycon().createStatement();
                s.executeUpdate("DELETE FROM rooms WHERE room_no = '"+room_no+"'");
                JOptionPane.showMessageDialog(null, "Room Deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Rooms_Table_load();
                clear();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    
    //Thenuli
    private void btnHallSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallSaveActionPerformed
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        int hallID = Integer.parseInt(txtHallID.getText());
        String hallName = txtHallName.getText();
        String hallCapcity = txtHallCapacity.getText();
        String hallPrice = txtHallPrice.getText();
        String hallAvailabilty = txtHallAvailability.getSelectedItem().toString();
        String hallstatus = txtHallStatus.getSelectedItem().toString();

        try {
            String sql = "INSERT INTO halls (hall_id, hall_name, capacity, price, Availability, status) VALUES (?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, hallID);
            pst.setString(2, hallName);
            pst.setString(3, hallCapcity);
            pst.setString(4, hallPrice);
            pst.setString(5, hallAvailabilty);
            pst.setString(6, hallstatus);

            int rowsUpdated = pst.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Hall Added Successfully!!!!");
                load_Halls_Data();
                clear_halls();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Hall Added.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnHallSaveActionPerformed
    //Thenuli
    private void btnHallUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallUpdateActionPerformed
        // TODO add your handling code here:
        Connection con = DatabaseLayer.mycon();
        PreparedStatement pst = null;

        int hallID = Integer.parseInt(txtHallID.getText());
        String hallName = txtHallName.getText();
        String hallCapcity = txtHallCapacity.getText();
        String hallPrice = txtHallPrice.getText();
        String hallAvailabilty = txtHallAvailability.getSelectedItem().toString();
        String hallstatus = txtHallStatus.getSelectedItem().toString();

        try {
            String sql = "UPDATE halls SET hall_name = ?, capacity = ?, price = ?, Availability = ?, status = ? WHERE hall_id= ?";
            pst = con.prepareStatement(sql);

            pst.setString(1, hallName);
            pst.setString(2, hallCapcity);
            pst.setString(3, hallPrice);
            pst.setString(4, hallAvailabilty);
            pst.setString(5, hallstatus);
            pst.setInt(6, hallID);

            int rowsUpdated = pst.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Hall Updated Successfully!");
                load_Halls_Data();
                clear_halls();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Update Hall Data.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnHallUpdateActionPerformed
    //Thenuli
    private void btnHallDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallDeleteActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to delete?", 
            "Confirm Format", // title
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            String room_no = txtHallID.getText().trim();
            try {
                Statement s =  DatabaseLayer.mycon().createStatement();
                s.executeUpdate("DELETE FROM halls WHERE hall_id = '"+room_no+"'");
                JOptionPane.showMessageDialog(null, "Hall Deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                load_Halls_Data();
                clear_halls();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHallDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboAvailability;
    private javax.swing.JComboBox<String> ComboBedType;
    private javax.swing.JComboBox<String> ComboCategory;
    private javax.swing.JComboBox<String> ComboFloor;
    private javax.swing.JComboBox<String> ComboStatus;
    private javax.swing.JTable HallTable;
    private javax.swing.JTable RoomsTable;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHallDelete;
    private javax.swing.JButton btnHallSave;
    private javax.swing.JButton btnHallUpdate;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox cbAC;
    private javax.swing.JCheckBox cbBalcony;
    private javax.swing.JCheckBox cbBathroom;
    private javax.swing.JCheckBox cbMiniBar;
    private javax.swing.JCheckBox cbTV;
    private javax.swing.JCheckBox cbWifi;
    private javax.swing.JCheckBox cbfan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> txtHallAvailability;
    private javax.swing.JTextField txtHallCapacity;
    private javax.swing.JTextField txtHallID;
    private javax.swing.JTextField txtHallName;
    private javax.swing.JTextField txtHallPrice;
    private javax.swing.JComboBox<String> txtHallStatus;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRoomNo;
    // End of variables declaration//GEN-END:variables
}
