package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Supplier_Details_Jpanel extends javax.swing.JPanel {
    
    JpanelLoader jpload = new JpanelLoader();
    private javax.swing.JPanel select_JPanel;

    
    public Supplier_Details_Jpanel(javax.swing.JPanel select_JPanel) {
        this.select_JPanel = select_JPanel;
        initComponents();
        supTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        supTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        supTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        supTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        
        tb_load();
        doubleClicked();
    }

    public void doubleClicked() {
        supTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { 
                    int rownumber = supTable.getSelectedRow();
                    if (rownumber != -1) {
                        String supID = supTable.getValueAt(rownumber, 1).toString();
                        Supplier_Select_Jpanel selectPanel = new Supplier_Select_Jpanel(select_JPanel);
                        selectPanel.btnView(supID);
                        jpload.jPanelLoader(select_JPanel, selectPanel);
                    }
                }
            }
        });
    }

    public void tb_load() {
        try {
            DefaultTableModel dt =  (DefaultTableModel) supTable.getModel();
            dt.setRowCount(0);
            
            Statement s =  DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM supplier ORDER BY supID");
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
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                
                dt.addRow(v);
                
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        supTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        ComboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ComboSort = new javax.swing.JComboBox<>();

        supTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        supTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID", "Name", "Category", "Address", "Phone", "Email", "Bank", "Account No", "Added Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supTable.setRowHeight(38);
        supTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(supTable);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Search By :");

        ComboSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ComboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Address", "Phone", "Category", "Email" }));
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
        ComboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID (Min - Max)", "ID (Max - Min)", "Name (A - Z)", "Name (Z - A)", "Category (A - Z)", "Category (Z - A)", "Date Latest", "Date Oldest" }));
        ComboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboSort, 0, 189, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSort, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ComboSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSearchActionPerformed
        // TODO add your handling code here:
        txtSearch.requestFocusInWindow();
    }//GEN-LAST:event_ComboSearchActionPerformed

    private void ComboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSortActionPerformed
        // TODO add your handling code here:
        String sortBy = (String) ComboSort.getSelectedItem(); 
        String columnName = "";
        String sortOrder = "ASC";

        switch (sortBy) {
            case "ID (Min - Max)":
                columnName = "supID";
                sortOrder = "ASC";
                break;
            case "ID (Max - Min)":
                columnName = "supID";
                sortOrder = "DESC";
                break;
            case "Name (A - Z)":
                columnName = "supName";
                sortOrder = "ASC";
                break;
            case "Name (Z - A)":
                columnName = "supName";
                sortOrder = "DESC";
                break;
            case "Category (A - Z)":
                columnName = "supCategory";
                sortOrder = "ASC";
                break;
            case "Category (Z - A)":
                columnName = "supCategory";
                sortOrder = "DESC";
                break;
            case "Date Latest":
                columnName = "AddedDate";
                sortOrder = "DESC";
                break;
            case "Date Oldest":
                columnName = "AddedDate";
                sortOrder = "ASC";
                break;
            default:
                return;
        }
        

        try {
            DefaultTableModel dt = (DefaultTableModel) supTable.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            String query = "SELECT * FROM supplier ORDER BY " + columnName + " " + sortOrder;
            ResultSet rs = s.executeQuery(query);

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 9; i++) {
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

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void supTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_supTableMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String searchBy = (String) ComboSearch.getSelectedItem(); 
        String columnName = "";
        
        switch (searchBy) {
            case "ID":
                columnName = "supID";
                break;
            case "Name":
                columnName = "supName";
                break;
            case "Category":
                columnName = "supCategory";
                break;
            case "Phone":
                columnName = "supPhone";
                break;
            case "Address":
                columnName = "supAddress";
                break;
            case "Email":
                columnName = "supEmail";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid search category!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        String search = txtSearch.getText().trim();

        try {
            DefaultTableModel dt = (DefaultTableModel) supTable.getModel();
            dt.setRowCount(0);

            Statement s = DatabaseLayer.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM supplier WHERE " + columnName + " LIKE '%" + search + "%'");

            int count = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(String.valueOf(count));
                for (int i = 1; i <= 9; i++) {
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboSearch;
    private javax.swing.JComboBox<String> ComboSort;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable supTable;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
