/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.CommonControl;
import control.DatabaseLayer;
import control.Options;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ASUS
 */
public class ViewBooking extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public ViewBooking() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataToTable();
        setWIndowMoveble();
        JTableHeader header = tblBooking.getTableHeader();
        header.setFont(new Font("Tohoma", Font.PLAIN, 16));
    }
    
    private void setWIndowMoveble() {
        lblTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblTitileMouseDragged(evt);
            }
        });
        lblTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTitileMousePressed(evt);
            }
        });
    }

    private void lblTitileMouseDragged(java.awt.event.MouseEvent evt) {
        this.setLocation(evt.getXOnScreen() - Options.selectedWindowXPosition, evt.getYOnScreen());
    }

    private void lblTitileMousePressed(java.awt.event.MouseEvent evt) {
        Options.selectedWindowXPosition = evt.getX();
    }

    private void loadDataToTable() {
        String[] columnList = {
            "booking_id",
            "customer_name",
            "customer_tel_no",
            "customer_address",
            "booking_date",
            "checkin_date",
            "checkout_date",
            "booking_from",
            "no_of_pax",
            "room_no"
        };

        String sql = "SELECT booking_id, customer_name, customer_tel_no, customer_address, "
                + "booking_date, checkin_date, checkout_date, booking_from ,no_of_pax, room_no "
                + "FROM booking WHERE booking_status = ? ORDER BY booking_id DESC";

        try {
            Connection con = DatabaseLayer.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Options.BOOKING_CONFIRM);
            ResultSet rst = pst.executeQuery();

            CommonControl.loadDataToTable(tblBooking, rst, columnList);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading booking data",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCheckIn() {

        int viewRow = tblBooking.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a booking first",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to check-in this booking?",
                "Confirm", JOptionPane.YES_NO_OPTION
        );
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        // Handle sorted tables
        int modelRow = tblBooking.convertRowIndexToModel(viewRow);
        String bookingId = tblBooking.getModel().getValueAt(modelRow, 0).toString();

        String sql = "UPDATE booking SET booking_status = ?, actual_checkin = CURRENT_TIMESTAMP WHERE booking_id = ?";

        try {
            Connection con = DatabaseLayer.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Options.CHECK_IN_STATUS);
            pst.setString(2, bookingId);

            int updated = pst.executeUpdate();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this,
                        "Check-in updated successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                loadDataToTable(); // refresh table
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to update check-in",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBooking() {
        int viewRow = tblBooking.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a booking first",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this booking?",
                "Confirm", JOptionPane.YES_NO_OPTION
        );
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        // Handle sorted tables
        int modelRow = tblBooking.convertRowIndexToModel(viewRow);
        String bookingId = tblBooking.getModel().getValueAt(modelRow, 0).toString();

        String sql = "UPDATE booking SET booking_status = ? WHERE booking_id = ?";

        try {
            Connection con = DatabaseLayer.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Options.BOOKING_CANCELED);
            pst.setString(2, bookingId);

            int updated = pst.executeUpdate();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this,
                        "Booking deleted successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                loadDataToTable(); // refresh table
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to delete booking",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBookingDetails() {
        int viewRow = tblBooking.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a booking first",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Handle sorted tables
        int modelRow = tblBooking.convertRowIndexToModel(viewRow);
        String bookingId = tblBooking.getModel().getValueAt(modelRow, 0).toString();

        AddBooking addBooking = new AddBooking(bookingId, "");
        addBooking.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadDataToTable();
            }
        });
        addBooking.setVisible(true);

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
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnDelete = new com.alee.laf.button.WebButton();
        btnEdit = new com.alee.laf.button.WebButton();
        btnCheckIn = new com.alee.laf.button.WebButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel-Management-System | Srinill Beach Resort");
        setMinimumSize(new java.awt.Dimension(1366, 738));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMinimize.setBackground(new java.awt.Color(0, 153, 153));
        lblMinimize.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setText("__");
        lblMinimize.setOpaque(true);
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
        });
        jPanel1.add(lblMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1295, 0, 20, 17));

        lblClose.setBackground(new java.awt.Color(204, 0, 0));
        lblClose.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        lblClose.setForeground(new java.awt.Color(255, 255, 255));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("X");
        lblClose.setOpaque(true);
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });
        jPanel1.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, 50, 17));

        lblTitle.setBackground(new java.awt.Color(0, 0, 0));
        lblTitle.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("  View Booking");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 20));

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBooking.setAutoCreateRowSorter(true);
        tblBooking.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Booking ID", "Customer Name", "Telephone", "Address", "Booked Date", "Check In", "Check Out", "Booking From", "No Of Pax", "Room No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBooking.setRowHeight(30);
        jScrollPane1.setViewportView(tblBooking);
        if (tblBooking.getColumnModel().getColumnCount() > 0) {
            tblBooking.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblBooking.getColumnModel().getColumn(1).setPreferredWidth(140);
            tblBooking.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblBooking.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblBooking.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblBooking.getColumnModel().getColumn(5).setPreferredWidth(10);
            tblBooking.getColumnModel().getColumn(6).setPreferredWidth(10);
            tblBooking.getColumnModel().getColumn(7).setPreferredWidth(30);
            tblBooking.getColumnModel().getColumn(8).setPreferredWidth(5);
            tblBooking.getColumnModel().getColumn(9).setPreferredWidth(10);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 590));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1330, 590));

        jPanel5.setBackground(new java.awt.Color(0, 51, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setText("DELETE");
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, 100, 40));

        btnEdit.setText("UPDATE");
        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel5.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 100, 40));

        btnCheckIn.setText("CHECK IN");
        btnCheckIn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInActionPerformed(evt);
            }
        });
        jPanel5.add(btnCheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 100, 40));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1310, 60));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1347, 700));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1367, 738));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        updateCheckIn();
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteBooking();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        updateBookingDetails();
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCheckIn;
    private com.alee.laf.button.WebButton btnDelete;
    private com.alee.laf.button.WebButton btnEdit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblBooking;
    // End of variables declaration//GEN-END:variables
}
