/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.DatabaseLayer;
import control.EmailController;
import control.Options;
import control.Validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class RoomManagement extends javax.swing.JFrame {

    String roomNo;
    String type;
    String updatePrice;

    /**
     * Creates new form MainMenu
     */
    public RoomManagement() {
        initComponents();
        setLocationRelativeTo(null);
        setWIndowMoveble();
        loadRoomPrice();
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

    private void loadRoomPrice() {
        if (comboRoomNo.getSelectedItem() == null) {
            return;
        }

        String roomNo = comboRoomNo.getSelectedItem().toString();

        String sql = "SELECT room_price, room_type FROM room WHERE room_no = ?";

        try (Connection con = DatabaseLayer.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, roomNo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txtCurrentPrice.setText(
                        Validation.formatWithTwoDigitsWithGrouping(rs.getString("room_price"))
                );

                comboRoomType.setSelectedItem(rs.getString("room_type"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load room details",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateRoom() {
        roomNo = comboRoomNo.getSelectedItem().toString();
        type = comboRoomType.getSelectedItem().toString();
        updatePrice = Validation.formatWithTwoDigitsWithGrouping(txtUpdatePrice.getText());

        if (updatePrice.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Enter Update Price!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update this?",
                "Confirm", JOptionPane.YES_NO_OPTION
        );
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String updateQuery = "UPDATE room SET "
                + "room_type = ?, room_price = ? "
                + "WHERE room_no = ?";

        try (Connection con = DatabaseLayer.getConnection();
                PreparedStatement pst = con.prepareStatement(updateQuery)) {

            pst.setString(1, type);
            pst.setBigDecimal(2, Validation.getBigDecimalByFormattedString(updatePrice));
            pst.setString(3, roomNo);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Update successfully!");
            if (Options.isEnableEmail) {
                sendMail();
            }
            clearAll();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating Room");
        }
    }

    private void clearAll() {
        txtCurrentPrice.setText("");
        txtUpdatePrice.setText("");
        comboRoomNo.setSelectedIndex(0);
        comboRoomType.setSelectedIndex(0);
    }

    private void sendMail() {

        String subject = "Room Price Updated | Room No : " + roomNo;
        String message
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "</head>"
                + "<body style='margin:0; padding:20px; background-color:#f2f4f7; font-family:Segoe UI, Arial, sans-serif;'>"
                + "<table width='100%' cellspacing='0' cellpadding='0'>"
                + "<tr>"
                + "<td align='center'>"
                + "<table width='650' cellpadding='0' cellspacing='0' style='background:#ffffff; border-radius:10px; box-shadow:0 6px 18px rgba(0,0,0,0.12);'>"
                + /* ===== HEADER ===== */ "<tr style='background:#0d47a1;'>"
                + "<td style='padding:18px; color:#ffffff; text-align:center;'>"
                + "<h2 style='margin:0;'>Srinill Beach Resort</h2>"
                + "<p style='margin-top:6px; font-size:14px;'>Notification – Room Price Update</p>"
                + "</td>"
                + "</tr>"
                + /* ===== BODY ===== */ "<tr>"
                + "<td style='padding:30px; color:#333;'>"
                + /* ===== DETAILS TABLE ===== */ "<table width='100%' cellpadding='12' cellspacing='0' style='border-collapse:collapse; margin-top:18px;'>"
                + "<tr style='background:#f1f5f9;'>"
                + "<td style='border:1px solid #ddd; width:40%;'><strong>Room Number</strong></td>"
                + "<td style='border:1px solid #ddd;'>" + roomNo + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='border:1px solid #ddd;'><strong>Room Type</strong></td>"
                + "<td style='border:1px solid #ddd;'>" + type + "</td>"
                + "</tr>"
                + "<tr style='background:#f1f5f9;'>"
                + "<td style='border:1px solid #ddd;'><strong>Updated Room Price</strong></td>"
                + "<td style='border:1px solid #ddd; font-weight:bold; color:#0d47a1;'>LKR " + updatePrice + "</td>"
                + "</tr>"
                + "</table>"
                + "<p style='margin-top:30px; font-size:14px;'>"
                + "<strong>Srinill Beach Resort</strong><br>"
                + "Software by Meshan Miranda | 0764315233"
                + "</p>"
                + "</td>"
                + "</tr>"
                + /* ===== FOOTER ===== */ "<tr style='background:#f2f4f7;'>"
                + "<td style='padding:14px; text-align:center; font-size:12px; color:#777;'>"
                + "© 2026 Srinill Beach Resort"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</body>"
                + "</html>";
        EmailController.sendEmail(Options.RecieverEmailList, subject, message);
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
        jLabel3 = new javax.swing.JLabel();
        txtCurrentPrice = new com.alee.laf.text.WebTextField();
        jLabel12 = new javax.swing.JLabel();
        txtUpdatePrice = new com.alee.laf.text.WebTextField();
        jLabel6 = new javax.swing.JLabel();
        comboRoomNo = new com.alee.laf.combobox.WebComboBox();
        jLabel15 = new javax.swing.JLabel();
        comboRoomType = new com.alee.laf.combobox.WebComboBox();
        btnClear = new com.alee.laf.button.WebButton();
        btnSave = new com.alee.laf.button.WebButton();

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
        lblTitle.setText("  Room Management");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 20));

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Current Price :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, 40));

        txtCurrentPrice.setEditable(false);
        txtCurrentPrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtCurrentPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 320, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Update Price :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 150, 40));

        txtUpdatePrice.setText("0.00");
        txtUpdatePrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtUpdatePrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 320, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Room No :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 40));

        comboRoomNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "101", "102", "103", "104", "105", "106", "107", "108", "109" }));
        comboRoomNo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboRoomNo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboRoomNoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel2.add(comboRoomNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 320, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Type :");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 40));

        comboRoomType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single", "Double", "Bunker" }));
        comboRoomType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(comboRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 320, 40));

        btnClear.setText("CLEAR");
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel2.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 100, 50));

        btnSave.setText("SAVE");
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 100, 50));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, 350));

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

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        updateRoom();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearAll();
    }//GEN-LAST:event_btnClearActionPerformed

    private void comboRoomNoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboRoomNoPopupMenuWillBecomeInvisible
        loadRoomPrice();
    }//GEN-LAST:event_comboRoomNoPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(RoomManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnClear;
    private com.alee.laf.button.WebButton btnSave;
    private com.alee.laf.combobox.WebComboBox comboRoomNo;
    private com.alee.laf.combobox.WebComboBox comboRoomType;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private com.alee.laf.text.WebTextField txtCurrentPrice;
    private com.alee.laf.text.WebTextField txtUpdatePrice;
    // End of variables declaration//GEN-END:variables
}
