
package GUI;

import java.util.Date;
import java.text.SimpleDateFormat;
import javafx.application.Platform;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Dashboard_Reception extends javax.swing.JFrame {

    JpanelLoader jpload = new JpanelLoader();

    public Dashboard_Reception() {
        initComponents();
        this.setExtendedState(Dashboard_Reception.MAXIMIZED_BOTH);
        updateDateTime();

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
        //btnBooking.doClick();
    }
    private void updateDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = sdf.format(new Date());
        DateLabel.setText(currentDateTime);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home_btn_grp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        DateLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnSignOut = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnReservation = new javax.swing.JToggleButton();
        btnBooking = new javax.swing.JToggleButton();
        btnCheckinOut = new javax.swing.JToggleButton();
        btnRooms = new javax.swing.JToggleButton();
        btnSettings = new javax.swing.JToggleButton();
        btnRecords = new javax.swing.JToggleButton();
        btnInfo = new javax.swing.JToggleButton();
        btnEvents = new javax.swing.JToggleButton();
        panal_load = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(355, 734));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DateLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        DateLabel.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(DateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 40, 419, 20));

        jPanel5.setBackground(new java.awt.Color(0, 51, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/customer.png"))); // NOI18N
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 8, -1, 24));

        btnSignOut.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnSignOut.setText("Sign Out");
        btnSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignOutActionPerformed(evt);
            }
        });
        jPanel5.add(btnSignOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("tharindu");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 8, 105, 24));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 170, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SRINILL BEACH RESORT ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 632, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 70));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home_btn_grp.add(btnReservation);
        btnReservation.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnReservation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/reserv.png"))); // NOI18N
        btnReservation.setText("Reservation");
        btnReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservationActionPerformed(evt);
            }
        });
        jPanel2.add(btnReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, 181, 47));

        home_btn_grp.add(btnBooking);
        btnBooking.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnBooking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/booking.png"))); // NOI18N
        btnBooking.setText("Booking");
        btnBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingActionPerformed(evt);
            }
        });
        jPanel2.add(btnBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 69, 181, 47));

        home_btn_grp.add(btnCheckinOut);
        btnCheckinOut.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnCheckinOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/check in.png"))); // NOI18N
        btnCheckinOut.setText("Check in / out");
        btnCheckinOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckinOutActionPerformed(evt);
            }
        });
        jPanel2.add(btnCheckinOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 177, 181, 47));

        home_btn_grp.add(btnRooms);
        btnRooms.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/rooms.png"))); // NOI18N
        btnRooms.setText("Rooms");
        btnRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomsActionPerformed(evt);
            }
        });
        jPanel2.add(btnRooms, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 123, 181, 47));

        home_btn_grp.add(btnSettings);
        btnSettings.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/settings.png"))); // NOI18N
        btnSettings.setText("Settings");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        jPanel2.add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 393, 181, 47));

        home_btn_grp.add(btnRecords);
        btnRecords.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/reports.png"))); // NOI18N
        btnRecords.setText("Reports");
        btnRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordsActionPerformed(evt);
            }
        });
        jPanel2.add(btnRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 339, 181, 47));

        home_btn_grp.add(btnInfo);
        btnInfo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/invo.png"))); // NOI18N
        btnInfo.setText("Information");
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        jPanel2.add(btnInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 285, 181, 47));

        home_btn_grp.add(btnEvents);
        btnEvents.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnEvents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/img/event.png"))); // NOI18N
        btnEvents.setText("Events");
        btnEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEventsActionPerformed(evt);
            }
        });
        jPanel2.add(btnEvents, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 231, 181, 47));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 195, 590));

        panal_load.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panal_load.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCalendar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCalendar1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jCalendar1.setSundayForeground(new java.awt.Color(255, 0, 0));
        jCalendar1.setTodayButtonText("Today");
        jCalendar1.setWeekOfYearVisible(false);
        jCalendar1.setWeekdayForeground(new java.awt.Color(51, 51, 255));
        panal_load.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 15, 1110, 560));

        getContentPane().add(panal_load, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 80, 1140, 590));

        jLabel6.setText("© Software by Code Breakers  |  TEL : 0764315233  |  Email : codebreackers@gmail.com  |  License ID : 100476");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 675, 762, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservationActionPerformed
        // TODO add your handling code here:
        try {
            Reservation Reservation = new Reservation();
            jpload.jPanelLoader(panal_load, Reservation);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReservationActionPerformed

    private void btnBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingActionPerformed
        // TODO add your handling code here:
        try {
            Booking Booking = new Booking();
            jpload.jPanelLoader(panal_load, Booking);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBookingActionPerformed

    private void btnCheckinOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckinOutActionPerformed
        // TODO add your handling code here:
        try {
            Checkinout Checkinout = new Checkinout();
            jpload.jPanelLoader(panal_load, Checkinout);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCheckinOutActionPerformed

    private void btnRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomsActionPerformed
        // TODO add your handling code here:
        try {
            Rooms Rooms = new Rooms();
            jpload.jPanelLoader(panal_load, Rooms);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRoomsActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        // TODO add your handling code here:
        try {
            Settings_Reception Settings_Reception = new Settings_Reception();
            jpload.jPanelLoader(panal_load, Settings_Reception);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordsActionPerformed
        // TODO add your handling code here:
        try {
            Reports_Reception Records = new Reports_Reception();
            jpload.jPanelLoader(panal_load, Records);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRecordsActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
        try {
            Information Information = new Information();
            jpload.jPanelLoader(panal_load, Information);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEventsActionPerformed
        // TODO add your handling code here:
        try {
            Events Events = new Events();
            jpload.jPanelLoader(panal_load, Events);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Database Connection Error. Try Again. ( Database Offline )", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEventsActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Dashboard_Reception().setVisible(true);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void btnSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignOutActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
            this, // parent component
            "Are you sure you want to sign out?",
            "Confirm Sign Out",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE 
        );

        if (response == JOptionPane.YES_OPTION) {
            new Home().setVisible(true);
            this.dispose();
        } else {
        }
        
    }//GEN-LAST:event_btnSignOutActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard_Reception.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Reception.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Reception.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Reception.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard_Reception().setVisible(true);
            }
            
        });
        Platform.setImplicitExit(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DateLabel;
    private javax.swing.JToggleButton btnBooking;
    private javax.swing.JToggleButton btnCheckinOut;
    private javax.swing.JToggleButton btnEvents;
    private javax.swing.JToggleButton btnInfo;
    private javax.swing.JToggleButton btnRecords;
    private javax.swing.JToggleButton btnReservation;
    private javax.swing.JToggleButton btnRooms;
    private javax.swing.JToggleButton btnSettings;
    private javax.swing.JButton btnSignOut;
    private javax.swing.ButtonGroup home_btn_grp;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel panal_load;
    // End of variables declaration//GEN-END:variables
}
