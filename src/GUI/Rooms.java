package GUI;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Rooms extends javax.swing.JPanel {

    private Rooms rooms_Table;
    
    public Rooms() {
        initComponents();
        rooms_Table = this;
        RoomsTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        RoomsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        RoomsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        RoomsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        Rooms_Table_load();
    }
    // Fetching Rooms data from rooms table in rooms database
    public void Rooms_Table_load() {
        ComboSort.setSelectedIndex(0);
        try {
            DefaultTableModel dt = (DefaultTableModel) RoomsTable.getModel();
            dt.setRowCount(0);

            String query = "SELECT * FROM rooms ORDER BY room_no";

            try (Statement s = DatabaseLayer.mycon().createStatement();
                 ResultSet rs = s.executeQuery(query)) {

                int count = 1;
                while (rs.next()) {
                    Vector<String> v = new Vector<>();
                    v.add(String.valueOf(count));

                    for (int i = 1; i <= 7; i++) {
                        v.add(rs.getString(i));
                    }

                    for (int i = 8; i <= 14; i++) {
                        v.add(rs.getString(i).equals("1") ? "Yes" : "No");
                    }

                    dt.addRow(v);
                    count++;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Fetching match checkboxes with Rooms data from rooms table in rooms database
    public void CheckBox_load() {
        ComboSort.setSelectedIndex(0);
        try {
            DefaultTableModel dt = (DefaultTableModel) RoomsTable.getModel();
            dt.setRowCount(0);

            // Dynamically build the WHERE clause based on selected checkboxes
            StringBuilder whereClause = new StringBuilder("WHERE 1=1");

            if (cbAC.isSelected()) whereClause.append(" AND ac = '1'");
            if (cbFan.isSelected()) whereClause.append(" AND fan = '1'");
            if (cbTV.isSelected()) whereClause.append(" AND tv = '1'");
            if (cbMIniBar.isSelected()) whereClause.append(" AND mini_bar = '1'");
            if (cbBalconyView.isSelected()) whereClause.append(" AND balcony_view = '1'");
            if (cbWifi.isSelected()) whereClause.append(" AND wifi_access = '1'");
            if (cbBathroom.isSelected()) whereClause.append(" AND attached_bathroom = '1'");

            String query = "SELECT * FROM rooms " + whereClause + " ORDER BY room_no";

            try (Statement s = DatabaseLayer.mycon().createStatement();
                 ResultSet rs = s.executeQuery(query)) {

                int count = 1;
                while (rs.next()) {
                    Vector<String> v = new Vector<>();
                    v.add(String.valueOf(count));

                    // Add first 7 columns directly
                    for (int i = 1; i <= 7; i++) {
                        v.add(rs.getString(i));
                    }

                    // Convert binary columns (8-14) to "Yes"/"No"
                    for (int i = 8; i <= 14; i++) {
                        v.add(rs.getString(i).equals("1") ? "Yes" : "No");
                    }

                    dt.addRow(v);
                    count++;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RoomsTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        ComboSort = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        CombCategory = new javax.swing.JComboBox<>();
        ComboBed = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbAC = new javax.swing.JCheckBox();
        cbFan = new javax.swing.JCheckBox();
        cbMIniBar = new javax.swing.JCheckBox();
        cbTV = new javax.swing.JCheckBox();
        cbBalconyView = new javax.swing.JCheckBox();
        cbWifi = new javax.swing.JCheckBox();
        cbBathroom = new javax.swing.JCheckBox();
        checkInDate = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        checkOutDate = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnUpdateRoom = new javax.swing.JButton();

        RoomsTable.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        RoomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Room No", "Category", "Availability", "Status", "Bed Type", "Floor", "Price", "AC", "Fan", "TV", "Mini Bar", "Balcony", "Wifi", "Attach Bath"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RoomsTable.setRowHeight(35);
        jScrollPane1.setViewportView(RoomsTable);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Sort By :");

        ComboSort.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Room No (Min - Max)", "Room No (Max - Min)", "Price (Low - High)", "Price (High - Low)", "Available", "Occupied" }));
        ComboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSortActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Category :");

        CombCategory.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CombCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Single", "Standard", "Deluxe", "Suite" }));

        ComboBed.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboBed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "King", "Queen", "Twin" }));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Bed :");

        cbAC.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbAC.setForeground(new java.awt.Color(204, 0, 0));
        cbAC.setText("AC");
        cbAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbACActionPerformed(evt);
            }
        });

        cbFan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbFan.setForeground(new java.awt.Color(204, 0, 0));
        cbFan.setText("Fan");
        cbFan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFanActionPerformed(evt);
            }
        });

        cbMIniBar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbMIniBar.setForeground(new java.awt.Color(204, 0, 0));
        cbMIniBar.setText("Mini Bar");
        cbMIniBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMIniBarActionPerformed(evt);
            }
        });

        cbTV.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbTV.setForeground(new java.awt.Color(204, 0, 0));
        cbTV.setText("TV");
        cbTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTVActionPerformed(evt);
            }
        });

        cbBalconyView.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbBalconyView.setForeground(new java.awt.Color(204, 0, 0));
        cbBalconyView.setText("Balcony View");
        cbBalconyView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBalconyViewActionPerformed(evt);
            }
        });

        cbWifi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbWifi.setForeground(new java.awt.Color(204, 0, 0));
        cbWifi.setText("WiFi");
        cbWifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbWifiActionPerformed(evt);
            }
        });

        cbBathroom.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbBathroom.setForeground(new java.awt.Color(204, 0, 0));
        cbBathroom.setText("Attached Bathroom");
        cbBathroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBathroomActionPerformed(evt);
            }
        });

        checkInDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("In :");

        checkOutDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Out :");

        btnSearch.setBackground(new java.awt.Color(102, 102, 255));
        btnSearch.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/search x30.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 255, 102));
        btnReset.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 0, 0));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/remove.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbAC)
                                .addGap(18, 18, 18)
                                .addComponent(cbFan)
                                .addGap(18, 18, 18)
                                .addComponent(cbTV)
                                .addGap(18, 18, 18)
                                .addComponent(cbMIniBar)
                                .addGap(18, 18, 18)
                                .addComponent(cbBalconyView)
                                .addGap(18, 18, 18)
                                .addComponent(cbWifi)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbBathroom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboSort, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(CombCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboBed, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboBed, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(CombCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(btnSearch)
                        .addComponent(btnReset))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkInDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkOutDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbAC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbFan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbTV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbMIniBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbBalconyView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbWifi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbBathroom)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ComboSort, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(102, 255, 102));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Rooms Details | Check Availability");

        btnUpdateRoom.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnUpdateRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/update.png"))); // NOI18N
        btnUpdateRoom.setText("Update Rooms");
        btnUpdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateRoom)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnUpdateRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    
    //this form load when i clicked the update rooms button
    private void btnUpdateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRoomActionPerformed
        // TODO add your handling code here:
        Room_Update_Popup updateRoom = new Room_Update_Popup(this);
        updateRoom.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        updateRoom.setVisible(true);
    }//GEN-LAST:event_btnUpdateRoomActionPerformed

    //Click CheckBox - Attached Bathroom
    private void cbBathroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBathroomActionPerformed
        // TODO add your handling code here:
        CheckBox_load();
    }//GEN-LAST:event_cbBathroomActionPerformed
    
    //Click CheckBox - Wifi
    private void cbWifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbWifiActionPerformed
        // TODO add your handling code here:
        CheckBox_load();
    }//GEN-LAST:event_cbWifiActionPerformed
    
    //Click CheckBox - BalconyView
    private void cbBalconyViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBalconyViewActionPerformed
        // TODO add your handling code here:
        CheckBox_load();
    }//GEN-LAST:event_cbBalconyViewActionPerformed
    
    //Click CheckBox - TV
    private void cbTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTVActionPerformed
        // TODO add your handling code here:
        CheckBox_load();
    }//GEN-LAST:event_cbTVActionPerformed
    
    //Click CheckBox - MIniBar
    private void cbMIniBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMIniBarActionPerformed
        // TODO add your handling code here:
        CheckBox_load();
    }//GEN-LAST:event_cbMIniBarActionPerformed
    
    //Click CheckBox - Fan
    private void cbFanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFanActionPerformed
        // TODO add your handling code here:
        CheckBox_load();
    }//GEN-LAST:event_cbFanActionPerformed
    
    //Click CheckBox - AC
    private void cbACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbACActionPerformed
        // TODO add your handling code here:
        CheckBox_load();
    }//GEN-LAST:event_cbACActionPerformed
    
    //Soft By Combo box
    private void ComboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSortActionPerformed
        // TODO add your handling code here:
        String sortBy = (String) ComboSort.getSelectedItem();
        String columnName = "";
        String where = "";
        String sortOrder = "ASC";

        switch (sortBy) {
            case "Room No (Min - Max)":
                columnName = "room_no";
                sortOrder = "ASC";
                break;
            case "Room No (Max - Min)":
                columnName = "room_no";
                sortOrder = "DESC";
                break;
            case "Price (Low - High)":
                columnName = "price";
                sortOrder = "ASC";
                break;
            case "Price (High - Low)":
                columnName = "price";
                sortOrder = "DESC";
                break;
            case "Available":
                where = "availability = 'Available'";
                break;
            case "Occupied":
                where = "availability = 'Occupied'";
                break;
            default:
                return;
        }

        try {
            DefaultTableModel dt = (DefaultTableModel) RoomsTable.getModel();
            dt.setRowCount(0);

            String query = "SELECT * FROM rooms";
            if (!where.isEmpty()) {
                query += " WHERE " + where;
            }
            if (!columnName.isEmpty()) {
                query += " ORDER BY " + columnName + " " + sortOrder;
            }

            Statement s = DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery(query);

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                
                for (int i = 1; i <= 7; i++) {
                    v.add(rs.getString(i)); 
                }
                for (int i = 8; i <= 14; i++) {
                    v.add(rs.getString(i).equals("1") ? "Yes" : "No");
                }

                dt.addRow(v);
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ComboSortActionPerformed
    
    //Search button for check availability
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
        DefaultTableModel dt = (DefaultTableModel) RoomsTable.getModel();
        dt.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String checkin = checkInDate.getDate() != null ? sdf.format(checkInDate.getDate()) : null;
        String checkout = checkOutDate.getDate() != null ? sdf.format(checkOutDate.getDate()) : null;

        String category = (String) CombCategory.getSelectedItem();
        String bed = (String) ComboBed.getSelectedItem();

        String query = "SELECT * FROM rooms";
        boolean hasCondition = false;

        if (!category.equals("-- Select --")) {
            query += " WHERE category = '" + category + "'";
            hasCondition = true;
        }
        if (!bed.equals("-- Select --")) {
            query += (hasCondition ? " AND " : " WHERE ") + "bed_type = '" + bed + "'";
            hasCondition = true;
        }
        if (checkin != null && checkout != null) {
            query += (hasCondition ? " AND " : " WHERE ") + 
                     "room_no NOT IN (SELECT room_id FROM roomreservation WHERE checkInDate <= '" + checkout + "' AND checkOutDate >= '" + checkin + "')";
        }
        query += " ORDER BY room_no";

        Statement s = DatabaseLayer.mycon().createStatement();
        ResultSet rs = s.executeQuery(query);

        int count = 1;
        while (rs.next()) {
            Vector<String> v = new Vector<>();
            v.add(String.valueOf(count));
            for (int i = 1; i <= 7; i++) {
                v.add(rs.getString(i)); 
            }
            for (int i = 8; i <= 14; i++) {
                v.add(rs.getString(i).equals("1") ? "Yes" : "No");
            }
            dt.addRow(v);
            count++;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        Rooms_Table_load();
        cbAC.setSelected(false);
        cbFan.setSelected(false);
        cbTV.setSelected(false);
        cbMIniBar.setSelected(false);
        cbBalconyView.setSelected(false);
        cbWifi.setSelected(false);
        cbBathroom.setSelected(false);
        checkInDate.setDate(null);
        checkOutDate.setDate(null);
        ComboBed.setSelectedIndex(0);
        CombCategory.setSelectedIndex(0);
    }//GEN-LAST:event_jPanel7MouseClicked

    //Clear all selections
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        cbAC.setSelected(false);
        cbFan.setSelected(false);
        cbTV.setSelected(false);
        cbMIniBar.setSelected(false);
        cbBalconyView.setSelected(false);
        cbWifi.setSelected(false);
        cbBathroom.setSelected(false);
        checkInDate.setDate(null);
        checkOutDate.setDate(null);
        ComboBed.setSelectedIndex(0);
        CombCategory.setSelectedIndex(0);
        Rooms_Table_load();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CombCategory;
    private javax.swing.JComboBox<String> ComboBed;
    private javax.swing.JComboBox<String> ComboSort;
    private javax.swing.JTable RoomsTable;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateRoom;
    private javax.swing.JCheckBox cbAC;
    private javax.swing.JCheckBox cbBalconyView;
    private javax.swing.JCheckBox cbBathroom;
    private javax.swing.JCheckBox cbFan;
    private javax.swing.JCheckBox cbMIniBar;
    private javax.swing.JCheckBox cbTV;
    private javax.swing.JCheckBox cbWifi;
    private com.toedter.calendar.JDateChooser checkInDate;
    private com.toedter.calendar.JDateChooser checkOutDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
