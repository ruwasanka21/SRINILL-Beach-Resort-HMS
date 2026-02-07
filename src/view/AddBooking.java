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
import control.sendEmailHandler;
import java.awt.Component;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import model.DataObject;
import java.util.List;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ASUS
 */
public class AddBooking extends javax.swing.JFrame {

    private String bookingId;
    private String view;

    /**
     * Creates new form MainMenu
     */
    public AddBooking(String bookingId, String View) {
        this.bookingId = bookingId;
        this.view = View;
        initComponents();
        setLocationRelativeTo(null);
        loadRoomNoToComboBox();
        txtPriceForRoom.setText("0.00");
        txtTotalPrice.setText("0.00");
        setWIndowMoveble();
        checkOutPane.setVisible(false);
        if (bookingId != null) {
            setBookingDetails();
            btnSave.setText("Update");
            lblTitle.setText("  Update Booking");
            if (view.equals("view")) {
                lblTitle.setText("  View Booking Details");
                btnSave.setVisible(false);
                btnClear.setVisible(false);
            } else if (view.equals("checkOut")) {
                lblTitle.setText("  View Booking Details");
                btnSave.setVisible(false);
                btnClear.setVisible(false);
                checkOutPane.setVisible(true);
                setCheckOutDetails();
            }
        }
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

    private void loadRoomNoToComboBox() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rst = null;

        try {
            conn = DatabaseLayer.getConnection();
            String sql = "SELECT room_no, room_type, room_price FROM room WHERE room_status = 1";
            ps = conn.prepareStatement(sql);
            rst = ps.executeQuery();

            // Make sure to import java.util.List and java.util.ArrayList
            List<DataObject> rooms = new ArrayList<>();

            while (rst.next()) {
                DataObject data = new DataObject("room_no"); // default attribute for toString()
                data.addProperty("room_no", rst.getString("room_no"));
                data.addProperty("room_type", rst.getString("room_type"));
                data.addProperty("room_price", rst.getBigDecimal("room_price").toString());
                rooms.add(data);
            }

            comboRoomNo.setModel(new DefaultComboBoxModel<>(rooms.toArray(new DataObject[0])));

            comboRoomNo.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    if (value instanceof DataObject) {
                        DataObject data = (DataObject) value; // cast here
                        setText(data.get("room_no") + " - " + data.get("room_type"));
                    } else {
                        setText(value == null ? "" : value.toString());
                    }
                    return this;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load rooms: " + e.getMessage());
        }
    }

    private void saveBooking() {

        String cusName = txtCusName.getText();
        String cusTelephone = txtCusTelephone.getText();
        String cusAddress = txtCusAddress.getText();
        String cusNIC = txtCusNIC.getText();

        String bookingDate = txtBookingDate.getText();
        String checkInDate = txtCheckinDate.getText();
        String checkOutDate = txtCheckoutDate.getText();

        String noOfPax = txtNoOfPax.getText();
        int child = txtChild.getText().isEmpty() ? 0 : Integer.parseInt(txtChild.getText());

        String bookingFrom = comboBookingFrom.getSelectedItem().toString();
        String roomNo = comboRoomNo.getSelectedItem().toString();

        String roomPrice = Validation.formatWithTwoDigitsWithGrouping(txtPriceForRoom.getText());
        String totalPrice = Validation.formatWithTwoDigitsWithGrouping(txtTotalPrice.getText());

        String remark = txtRemark.getText();

        if (!Validation.isNotEmpty(cusName)) {
            JOptionPane.showMessageDialog(this, "Enter customer name!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCusName.requestFocus();
            return;
        }
        if (!Validation.isNotEmpty(cusTelephone)) {
            JOptionPane.showMessageDialog(this, "Enter telephone no!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCusTelephone.requestFocus();
            return;
        }
        if (!Validation.isNotEmpty(cusAddress)) {
            JOptionPane.showMessageDialog(this, "Enter customer address!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCusAddress.requestFocus();
            return;
        }
        if (!Validation.isNotEmpty(cusNIC)) {
            JOptionPane.showMessageDialog(this, "Enter customer NIC / Passport No!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCusNIC.requestFocus();
            return;
        }
        if (!Validation.isNotEmpty(checkInDate)) {
            JOptionPane.showMessageDialog(this, "Enter check in date!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCheckinDate.requestFocus();
            return;
        }
        if (!Validation.isNotEmpty(checkOutDate)) {
            JOptionPane.showMessageDialog(this, "Enter check out date!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCheckoutDate.requestFocus();
            return;
        }

        if (!Validation.isNotEmpty(noOfPax)) {
            JOptionPane.showMessageDialog(this, "Enter no of pax!", "Error", JOptionPane.ERROR_MESSAGE);
            txtNoOfPax.requestFocus();
            return;
        }
        if (roomPrice.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Select a room!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCheckoutDate.requestFocus();
            return;
        }
        if (bookingId == null) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this?",
                    "Confirm", JOptionPane.YES_NO_OPTION
            );
            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            String query = "INSERT INTO booking ("
                    + "customer_name, customer_tel_no, customer_address, customer_nic, "
                    + "booking_date, checkin_date, checkout_date, "
                    + "no_of_pax, child, booking_from, "
                    + "room_no, room_price, total_price, remark, "
                    + "checkout_subtotal, additional_charges, discount_amount, grand_total, "
                    + "payment_method, cash_payment, card_payment, balance, booking_status"
                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try (Connection con = DatabaseLayer.getConnection();
                    PreparedStatement pst = con.prepareStatement(query)) {

                pst.setString(1, cusName);
                pst.setString(2, cusTelephone);
                pst.setString(3, cusAddress);
                pst.setString(4, cusNIC);

                pst.setDate(5, java.sql.Date.valueOf(bookingDate));
                pst.setDate(6, java.sql.Date.valueOf(checkInDate));
                pst.setDate(7, java.sql.Date.valueOf(checkOutDate));

                pst.setInt(8, Validation.getIntFromString(noOfPax));
                pst.setInt(9, child);
                pst.setString(10, bookingFrom);

                pst.setString(11, roomNo);
                pst.setBigDecimal(12, Validation.getBigDecimalByFormattedString(roomPrice));
                pst.setBigDecimal(13, Validation.getBigDecimalByFormattedString(totalPrice));
                pst.setString(14, remark);

                pst.setDouble(15, 0.00);   // checkout_subtotal
                pst.setDouble(16, 0.00);   // additional_charges
                pst.setDouble(17, 0.00);   // discount_amount
                pst.setDouble(18, 0.00);   // grand_total

                pst.setString(19, null);   // payment_method
                pst.setDouble(20, 0.00);   // cash_payment
                pst.setDouble(21, 0.00);   // card_payment
                pst.setDouble(22, 0.00);   // balance

                pst.setInt(23, Options.BOOKING_CONFIRM);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Booking saved successfully!");
                if (Options.isEnableEmail) {
                    sendEmailHandler.sendMailForAddBooking(cusName, cusAddress, checkInDate, bookingFrom);
                }
                clearAll();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving booking");
            }
        } else {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this?",
                    "Confirm", JOptionPane.YES_NO_OPTION
            );
            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            String updateQuery = "UPDATE booking SET "
                    + "customer_name = ?, customer_tel_no = ?, customer_address = ?, customer_nic = ?, "
                    + "booking_date = ?, checkin_date = ?, checkout_date = ?, "
                    + "no_of_pax = ?, child = ?, booking_from = ?, "
                    + "room_no = ?, room_price = ?, total_price = ?, remark = ? "
                    + "WHERE booking_id = ?";

            try (Connection con = DatabaseLayer.getConnection();
                    PreparedStatement pst = con.prepareStatement(updateQuery)) {

                pst.setString(1, cusName);
                pst.setString(2, cusTelephone);
                pst.setString(3, cusAddress);
                pst.setString(4, cusNIC);

                pst.setDate(5, java.sql.Date.valueOf(bookingDate));
                pst.setDate(6, java.sql.Date.valueOf(checkInDate));
                pst.setDate(7, java.sql.Date.valueOf(checkOutDate));

                pst.setInt(8, Validation.getIntFromString(noOfPax));
                pst.setInt(9, child);
                pst.setString(10, bookingFrom);

                pst.setString(11, roomNo);
                pst.setBigDecimal(12, Validation.getBigDecimalByFormattedString(roomPrice));
                pst.setBigDecimal(13, Validation.getBigDecimalByFormattedString(totalPrice));
                pst.setString(14, remark);

                pst.setString(15, bookingId);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Booking updated successfully!");
                bookingId = null;
                clearAll();
                this.dispose();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating booking");
            }
        }

    }

    private void setTotal() {
        try {
            String checkInDateStr = txtCheckinDate.getText();
            String checkOutDateStr = txtCheckoutDate.getText();
            String priceStr = txtPriceForRoom.getText();

            if (checkInDateStr.isEmpty() || checkOutDateStr.isEmpty() || priceStr.isEmpty()) {
                txtTotalPrice.setText("0.00");
                return;
            }

            // Define the date format you are using in your text fields
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // e.g., 2026-01-10

            LocalDate checkInDate = LocalDate.parse(checkInDateStr, formatter);
            LocalDate checkOutDate = LocalDate.parse(checkOutDateStr, formatter);

            // Calculate number of days
            int days = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.");
                txtTotalPrice.setText("0.00");
                txtCheckoutDate.setText("");
                return;
            }

            // Calculate total
            BigDecimal totalAmount = Validation.getBigDecimalOrZeroFromString(priceStr).multiply(
                    Validation.getBigDecimalOrZeroFromString(Integer.toString(days)));

            // Set to total text field (formatted)
            txtTotalPrice.setText(Validation.formatWithTwoDigitsWithGrouping(totalAmount.toString()));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error calculating total: " + e.getMessage());
            txtTotalPrice.setText("0.00");
        }
    }

    private void clearAll() {
        // Clear text fields
        txtCusName.setText("");
        txtCusTelephone.setText("");
        txtCusAddress.setText("");
        txtCusNIC.setText("");

        txtCheckinDate.setText("");
        txtCheckoutDate.setText("");

        txtNoOfPax.setText("");
        txtChild.setText("0");

        if (comboBookingFrom.getItemCount() > 0) {
            comboBookingFrom.setSelectedIndex(0);
        }
        if (comboRoomNo.getItemCount() > 0) {
            comboRoomNo.setSelectedIndex(0);
        }
        txtPriceForRoom.setText("0.00");
        txtTotalPrice.setText("0.00");

        txtRemark.setText("");
    }

    private void setPrice() {
        Object selected = comboRoomNo.getSelectedItem();

        if (selected instanceof DataObject) {
            DataObject data = (DataObject) selected;
            BigDecimal price = Validation.getBigDecimalFromString(data.get("room_price")); // get room_price
            if (comboBookingFrom.getSelectedItem().equals("Local")) {
                price = price.subtract(Validation.getBigDecimalFromString("2000"));
            }
            txtPriceForRoom.setText(Validation.formatWithTwoDigitsWithGrouping(price.toString())); // set to your text field
            setTotal();
        } else {
            txtPriceForRoom.setText("0.00"); // clear if nothing selected
        }
    }

    private void setBookingDetails() {

        String sql = "SELECT customer_name, customer_tel_no, customer_address, customer_nic, "
                + "booking_date, checkin_date, checkout_date, no_of_pax, child, "
                + "booking_from, booking.room_no, booking.room_price, total_price, remark, room_type "
                + "FROM booking LEFT JOIN room ON room.room_no = booking.room_no WHERE booking_id = ?";

        try {
            Connection con = DatabaseLayer.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookingId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txtCusName.setText(rs.getString("customer_name"));
                txtCusTelephone.setText(rs.getString("customer_tel_no"));
                txtCusAddress.setText(rs.getString("customer_address"));
                txtCusNIC.setText(rs.getString("customer_nic"));

                txtBookingDate.setText(rs.getString("booking_date"));
                txtCheckinDate.setText(rs.getString("checkin_date"));
                txtCheckoutDate.setText(rs.getString("checkout_date"));

                txtNoOfPax.setText(rs.getString("no_of_pax"));
                txtChild.setText(rs.getString("child"));

                comboBookingFrom.setSelectedItem(rs.getString("booking_from"));
                String roomNo = rs.getString("room_no");

                ComboBoxModel<DataObject> model = comboRoomNo.getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    DataObject item = model.getElementAt(i);
                    if (roomNo.equals(item.get("room_no"))) {
                        comboRoomNo.setSelectedItem(item);
                        break;
                    }
                }

                txtPriceForRoom.setText(
                        Validation.formatWithTwoDigitsWithGrouping(rs.getString("room_price"))
                );

                txtTotalPrice.setText(
                        Validation.formatWithTwoDigitsWithGrouping(rs.getString("total_price"))
                );

                txtRemark.setText(rs.getString("remark"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to load booking details",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setCheckOutDetails() {
        String sql = "SELECT actual_checkin, actual_checkout, checkout_subtotal, additional_charges, "
                + "additional_note, discount_amount, grand_total, payment_method, cash_payment, "
                + "card_payment, balance FROM booking WHERE booking_id = ?";

        try {
            Connection con = DatabaseLayer.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookingId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lblDetails.setText(
                        "<html>"
                        + "Actual Check-In : " + rs.getString("actual_checkin") + "<br>"
                        + "Actual Check-Out : " + rs.getString("actual_checkout") + "<br><br>"
                        + "Sub Total : " + rs.getBigDecimal("checkout_subtotal") + "<br>"
                        + "Additional Charges : " + rs.getBigDecimal("additional_charges") + "<br>"
                        + "Note : " + rs.getString("additional_note") + "<br>"
                        + "Discount : " + rs.getBigDecimal("discount_amount") + "<br><br>"
                        + "Grand Total : " + rs.getBigDecimal("grand_total") + "<br>"
                        + "Payment Method : " + rs.getString("payment_method") + "<br>"
                        + "Cash Pay : " + rs.getBigDecimal("cash_payment") + "<br>"
                        + "Card Pay : " + rs.getBigDecimal("card_payment") + "<br>"
                        + "Balance : " + rs.getBigDecimal("balance")
                        + "</html>"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to load booking details",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
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
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCusAddress = new com.alee.laf.text.WebTextField();
        txtCusName = new com.alee.laf.text.WebTextField();
        txtCusTelephone = new com.alee.laf.text.WebTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCusNIC = new com.alee.laf.text.WebTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCheckoutDate = new com.alee.extended.date.WebDateField();
        jLabel7 = new javax.swing.JLabel();
        txtCheckinDate = new com.alee.extended.date.WebDateField();
        jLabel8 = new javax.swing.JLabel();
        comboRoomNo = new com.alee.laf.combobox.WebComboBox();
        txtNoOfPax = new com.alee.laf.text.WebTextField();
        txtChild = new com.alee.laf.text.WebTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnClear = new com.alee.laf.button.WebButton();
        btnSave = new com.alee.laf.button.WebButton();
        jLabel11 = new javax.swing.JLabel();
        txtRemark = new com.alee.laf.text.WebTextField();
        jLabel13 = new javax.swing.JLabel();
        comboBookingFrom = new com.alee.laf.combobox.WebComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtBookingDate = new com.alee.extended.date.WebDateField();
        txtTotalPrice = new com.alee.laf.text.WebFormattedTextField();
        txtPriceForRoom = new com.alee.laf.text.WebFormattedTextField();
        checkOutPane = new javax.swing.JPanel();
        lblDetails = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();

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
        lblTitle.setText("  Add Booking");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 20));

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Customer Name :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Telephone No :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Address :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, 40));

        txtCusAddress.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCusAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusAddressActionPerformed(evt);
            }
        });
        jPanel2.add(txtCusAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 320, 40));

        txtCusName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusNameActionPerformed(evt);
            }
        });
        jPanel2.add(txtCusName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 320, 40));

        txtCusTelephone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCusTelephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusTelephoneActionPerformed(evt);
            }
        });
        jPanel2.add(txtCusTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 320, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("NIC / Passport :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 150, 40));

        txtCusNIC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCusNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusNICActionPerformed(evt);
            }
        });
        jPanel2.add(txtCusNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 320, 40));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, 280));

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Out :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 50, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No of pax :");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Room No :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 150, 40));

        txtCheckoutDate.setEditable(false);
        txtCheckoutDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCheckoutDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCheckoutDate.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd")
        );
        txtCheckoutDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCheckoutDateFocusGained(evt);
            }
        });
        txtCheckoutDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckoutDateActionPerformed(evt);
            }
        });
        jPanel3.add(txtCheckoutDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 240, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Check In :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 40));

        txtCheckinDate.setEditable(false);
        txtCheckinDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCheckinDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCheckinDate.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd")
        );
        txtCheckinDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCheckinDateFocusGained(evt);
            }
        });
        txtCheckinDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckinDateActionPerformed(evt);
            }
        });
        jPanel3.add(txtCheckinDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 240, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Child :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 70, 40));

        comboRoomNo.setMaximumRowCount(10);
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
        jPanel3.add(comboRoomNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 240, 40));

        txtNoOfPax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNoOfPax.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNoOfPax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoOfPaxActionPerformed(evt);
            }
        });
        jPanel3.add(txtNoOfPax, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 190, 40));

        txtChild.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtChild.setText("0");
        txtChild.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 170, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Price for room :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 130, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Price :");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 130, 40));

        btnClear.setText("CLEAR");
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel3.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 600, 100, 50));

        btnSave.setText("SAVE");
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel3.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 600, 100, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Remark :");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 130, 40));

        txtRemark.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtRemark, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 480, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Booking From :");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 40));

        comboBookingFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Booking.com", "Agoda", "Travel Agent", "Local", "Other" }));
        comboBookingFrom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboBookingFrom.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboBookingFromPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel3.add(comboBookingFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 240, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Booking Date :");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 40));

        txtBookingDate.setEditable(false);
        txtBookingDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBookingDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBookingDate.setDate(new java.util.Date());

        txtBookingDate.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd")
        );
        jPanel3.add(txtBookingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 240, 40));

        txtTotalPrice.setEditable(false);
        txtTotalPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPrice.setText("0.00");
        txtTotalPrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 240, 40));

        txtPriceForRoom.setEditable(false);
        txtPriceForRoom.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPriceForRoom.setText("0.00");
        txtPriceForRoom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtPriceForRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 240, 40));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 780, 670));

        checkOutPane.setBackground(new java.awt.Color(0, 51, 102));
        checkOutPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        checkOutPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDetails.setForeground(new java.awt.Color(255, 255, 255));
        lblDetails.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDetails.setText("Customer Name :");
        lblDetails.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        checkOutPane.add(lblDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 500, 320));

        lblTitle1.setBackground(new java.awt.Color(0, 0, 0));
        lblTitle1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblTitle1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1.setText("Check-Out Details");
        lblTitle1.setOpaque(true);
        checkOutPane.add(lblTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 20));

        jPanel4.add(checkOutPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 520, 370));

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
        saveBooking();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void comboRoomNoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboRoomNoPopupMenuWillBecomeInvisible
        // Get the selected item from the combo box
        setPrice();
    }//GEN-LAST:event_comboRoomNoPopupMenuWillBecomeInvisible

    private void txtCheckinDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCheckinDateFocusGained
        setTotal();
    }//GEN-LAST:event_txtCheckinDateFocusGained

    private void txtCheckoutDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCheckoutDateFocusGained
        setTotal();
    }//GEN-LAST:event_txtCheckoutDateFocusGained

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearAll();
    }//GEN-LAST:event_btnClearActionPerformed

    private void comboBookingFromPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboBookingFromPopupMenuWillBecomeInvisible
        setPrice();
    }//GEN-LAST:event_comboBookingFromPopupMenuWillBecomeInvisible

    private void txtCusNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusNameActionPerformed
        txtCusTelephone.requestFocus();
    }//GEN-LAST:event_txtCusNameActionPerformed

    private void txtCusTelephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusTelephoneActionPerformed
        txtCusAddress.requestFocus();
    }//GEN-LAST:event_txtCusTelephoneActionPerformed

    private void txtCusAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusAddressActionPerformed
        txtCusNIC.requestFocus();
    }//GEN-LAST:event_txtCusAddressActionPerformed

    private void txtCusNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusNICActionPerformed
        txtCheckinDate.requestFocus();
    }//GEN-LAST:event_txtCusNICActionPerformed

    private void txtCheckinDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckinDateActionPerformed
        txtCheckoutDate.requestFocus();
    }//GEN-LAST:event_txtCheckinDateActionPerformed

    private void txtNoOfPaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoOfPaxActionPerformed
        comboBookingFrom.requestFocus();
    }//GEN-LAST:event_txtNoOfPaxActionPerformed

    private void txtCheckoutDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckoutDateActionPerformed
        txtNoOfPax.requestFocus();
    }//GEN-LAST:event_txtCheckoutDateActionPerformed

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
            java.util.logging.Logger.getLogger(AddBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBooking(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnClear;
    private com.alee.laf.button.WebButton btnSave;
    private javax.swing.JPanel checkOutPane;
    private com.alee.laf.combobox.WebComboBox comboBookingFrom;
    private com.alee.laf.combobox.WebComboBox comboRoomNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDetails;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private com.alee.extended.date.WebDateField txtBookingDate;
    private com.alee.extended.date.WebDateField txtCheckinDate;
    private com.alee.extended.date.WebDateField txtCheckoutDate;
    private com.alee.laf.text.WebTextField txtChild;
    private com.alee.laf.text.WebTextField txtCusAddress;
    private com.alee.laf.text.WebTextField txtCusNIC;
    private com.alee.laf.text.WebTextField txtCusName;
    private com.alee.laf.text.WebTextField txtCusTelephone;
    private com.alee.laf.text.WebTextField txtNoOfPax;
    private com.alee.laf.text.WebFormattedTextField txtPriceForRoom;
    private com.alee.laf.text.WebTextField txtRemark;
    private com.alee.laf.text.WebFormattedTextField txtTotalPrice;
    // End of variables declaration//GEN-END:variables
}
