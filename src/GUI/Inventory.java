package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Inventory extends javax.swing.JPanel {

    JpanelLoader jpload = new JpanelLoader();
    private Inventory inventory_tb_load;
    
    public Inventory() {
        initComponents();
        inventory_tb_load = this;
        InventoryTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        InventoryTable.getColumnModel().getColumn(1).setPreferredWidth(20);
        InventoryTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        InventoryTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        InventoryTable.getColumnModel().getColumn(5).setPreferredWidth(160);
        InventoryTable.getColumnModel().getColumn(6).setPreferredWidth(150);
        tb_load();
        doubleClicked();
        expireLabel();
        refresh();
    }
    
    public void doubleClicked() {
        InventoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { 
                    int rownumber = InventoryTable.getSelectedRow();
                    if (rownumber != -1) {
                        String itemID = InventoryTable.getValueAt(rownumber, 1).toString();
                        Item_Select_Popup Item_Select_Popup = new Item_Select_Popup(inventory_tb_load, itemID);
                        Item_Select_Popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        Item_Select_Popup.setVisible(true);
                    }
                }
            }
        });
    }

    public void tb_load() {
        ComboSort.setSelectedIndex(0);
        try {
            DefaultTableModel dt =  (DefaultTableModel) InventoryTable.getModel();
            dt.setRowCount(0);
            
            Statement s =  DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM inventory ORDER BY itemID");
            int count = 1;
            
            while (rs.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(count));
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
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
    
    public void expireLabel() {
        try {
            Statement s = DatabaseLayer.mycon().createStatement();
            String selectQuery = "SELECT * FROM suporders";
            ResultSet rs = s.executeQuery(selectQuery);

            int expiredCount = 0;
            Date today = new Date();

            while (rs.next()) {
                Date expiryDate = rs.getDate("ExpireDate"); 
                if (expiryDate != null && expiryDate.before(today)) {
                    expiredCount++;
                }
            }

            if (expiredCount > 0) {
                lblWarning.setText("Expired Items : " + expiredCount);
                lblWarning.setVisible(true); 
                startBlinkingLabel(lblWarning); 
            } else {
                lblWarning.setVisible(false);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void refresh() {
    Timer refreshTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            expireLabel();
        }
    });
    refreshTimer.start();
}

    public void startBlinkingLabel(JLabel lblWarning) {
        Timer blinkTimer = new Timer(500, new ActionListener() {
            private boolean visible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                lblWarning.setVisible(visible);
                visible = !visible;
            }
        });
        blinkTimer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnAddItem = new javax.swing.JButton();
        btnAddItem1 = new javax.swing.JButton();
        btnStockEntry = new javax.swing.JButton();
        lblWarning = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        ComboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ComboSort = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(102, 255, 102));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Inventory Details");

        btnAddItem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/add.png"))); // NOI18N
        btnAddItem.setText("Add Item");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        btnAddItem1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/newstock.png"))); // NOI18N
        btnAddItem1.setText("New Stock");
        btnAddItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItem1ActionPerformed(evt);
            }
        });

        btnStockEntry.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnStockEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/purchase2.png"))); // NOI18N
        btnStockEntry.setText("Stock Entry");
        btnStockEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockEntryActionPerformed(evt);
            }
        });

        lblWarning.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblWarning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/warning2.png"))); // NOI18N
        lblWarning.setText("Expire Items - 00");
        lblWarning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblWarningMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153)
                .addComponent(btnAddItem1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStockEntry)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddItem)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddItem)
                .addComponent(btnAddItem1)
                .addComponent(btnStockEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Search By :");

        ComboSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Supplier", "Category" }));
        ComboSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboSearchMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ComboSearchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ComboSearchMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ComboSearchMouseReleased(evt);
            }
        });
        ComboSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSearchActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/search x30.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Sort By :");

        ComboSort.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID (Min - Max)", "ID (Max - Min)", "Name (A - Z)", "Name (Z - A)", "Qty (MIn - Max)", "Qty (Max - Min)", "Category (A - Z)", "Category (Z - A)", "Date Latest", "Date Oldest" }));
        ComboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSortActionPerformed(evt);
            }
        });

        InventoryTable.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID", "Name", "Qty", "Category", "Supplier", "Description", "Updated Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        InventoryTable.setRowHeight(35);
        jScrollPane1.setViewportView(InventoryTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(ComboSort, 0, 197, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboSort, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ComboSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void ComboSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSearchActionPerformed
        // TODO add your handling code here:
        txtSearch.requestFocusInWindow();
    }//GEN-LAST:event_ComboSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void ComboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSortActionPerformed
        // TODO add your handling code here:
        String sortBy = (String) ComboSort.getSelectedItem(); 
        String columnName = "";
        String sortOrder = "ASC";

        switch (sortBy) {
            case "ID (Min - Max)":
                columnName = "itemID";
                sortOrder = "ASC";
                break;
            case "ID (Max - Min)":
                columnName = "itemID";
                sortOrder = "DESC";
                break;
            case "Name (A - Z)":
                columnName = "itemName";
                sortOrder = "ASC";
                break;
            case "Name (Z - A)":
                columnName = "itemName";
                sortOrder = "DESC";
                break;
            case "Qty (MIn - Max)":
                columnName = "qty";
                sortOrder = "ASC";
                break;
            case "Qty (Max - Min)":
                columnName = "qty";
                sortOrder = "DESC";
                break;
            case "Category (A - Z)":
                columnName = "itemCategory";
                sortOrder = "ASC";
                break;
            case "Category (Z - A)":
                columnName = "itemCategory";
                sortOrder = "DESC";
                break;
            case "Date Latest":
                columnName = "UpdatedDate";
                sortOrder = "DESC";
                break;
            case "Date Oldest":
                columnName = "UpdatedDate";
                sortOrder = "ASC";
                break;
            default:
                return;
        }
        
        try {
            DefaultTableModel dt = (DefaultTableModel) InventoryTable.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            String query = "SELECT * FROM inventory ORDER BY " + columnName + " " + sortOrder;
            ResultSet rs = s.executeQuery(query);

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 7; i++) {
                    v.add(rs.getString(i));
                }
                dt.addRow(v);
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ComboSortActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        // TODO add your handling code here:
        Item_Add_Popup Item_Add_Popup = new Item_Add_Popup(this);
        Item_Add_Popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Item_Add_Popup.setVisible(true);

        this.setEnabled(false);

        Item_Add_Popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                btnAddItem.getTopLevelAncestor().setEnabled(true);
            }
        });
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        tb_load();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String searchBy = (String) ComboSearch.getSelectedItem(); 
        String columnName = "";

        switch (searchBy) {
            case "ID":
                columnName = "itemID";
                break;
            case "Name":
                columnName = "itemName";
                break;
            case "Supplier":
                columnName = "supplier";
                break;
            case "Category":
                columnName = "itemCategory";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid search category!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        
        String search = txtSearch.getText().trim();

        try {
            DefaultTableModel dt = (DefaultTableModel) InventoryTable.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM inventory WHERE " + columnName + " LIKE '%" + search + "%'");

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 7; i++) {
                    v.add(rs.getString(i));
                }
                dt.addRow(v);
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void ComboSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboSearchMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ComboSearchMouseClicked

    private void ComboSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboSearchMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSearchMouseExited

    private void ComboSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboSearchMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSearchMousePressed

    private void ComboSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboSearchMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ComboSearchMouseReleased

    private void btnAddItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItem1ActionPerformed
        // TODO add your handling code here:
        Item_New_Stock_Popup Item_New_Stock_Popup = new Item_New_Stock_Popup(this);
        Item_New_Stock_Popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Item_New_Stock_Popup.setVisible(true);

        this.setEnabled(false);

        Item_New_Stock_Popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                btnAddItem.getTopLevelAncestor().setEnabled(true);
            }
        });
    }//GEN-LAST:event_btnAddItem1ActionPerformed

    private void btnStockEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockEntryActionPerformed
        // TODO add your handling code here:
        Item_Stock_Entry Item_Stock_Entry = new Item_Stock_Entry();
        Item_Stock_Entry.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Item_Stock_Entry.setVisible(true);

        this.setEnabled(false);

        Item_Stock_Entry.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                btnStockEntry.getTopLevelAncestor().setEnabled(true);
            }
        });
    }//GEN-LAST:event_btnStockEntryActionPerformed

    private void lblWarningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWarningMouseClicked
        // TODO add your handling code here:
        Item_Expire_Items Item_Expire_Items = new Item_Expire_Items();
        Item_Expire_Items.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Item_Expire_Items.setVisible(true);

        this.setEnabled(false);

        Item_Expire_Items.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                lblWarning.getTopLevelAncestor().setEnabled(true);
            }
        });
    }//GEN-LAST:event_lblWarningMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboSearch;
    private javax.swing.JComboBox<String> ComboSort;
    private javax.swing.JTable InventoryTable;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnAddItem1;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStockEntry;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
