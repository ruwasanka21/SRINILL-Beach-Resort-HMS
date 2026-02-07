/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.CommonControl;
import control.DatabaseLayer;
import control.Options;
import control.PrintStatus;
import control.Validation;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import model.DataObject;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author ASUS
 */
public class CheckOut extends javax.swing.JFrame {

    private String bookingId;

    /**
     * Creates new form MainMenu
     */
    public CheckOut() {
        initComponents();
        setLocationRelativeTo(null);
        JTableHeader header = tblBooking.getTableHeader();
        header.setFont(new Font("Tohoma", Font.PLAIN, 16));
        loadDataToTable();
        setWIndowMoveble();
        txtSubTotal.setText("0.00");
        comboPayMethod.setSelectedIndex(0);
        selectedCombo();
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
            "checkout_date",
            "room_no"
        };

        String sql = "SELECT booking_id, customer_name,"
                + "checkout_date, booking_from ,no_of_pax, room_no "
                + "FROM booking WHERE booking_status = ? ORDER BY booking_id DESC";

        try {
            Connection con = DatabaseLayer.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Options.CHECK_IN_STATUS);
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

    private void viewBookingDetails() {
        int viewRow = tblBooking.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a booking first",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int modelRow = tblBooking.convertRowIndexToModel(viewRow);
        String bookingId = tblBooking.getModel().getValueAt(modelRow, 0).toString();

        AddBooking addBooking = new AddBooking(bookingId, "view");
        addBooking.setVisible(true);
    }

    private void setBookingDetails() {
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
        bookingId = tblBooking.getModel().getValueAt(modelRow, 0).toString();

        String sql = "SELECT customer_name, total_price FROM booking WHERE booking_id = ?";

        try {
            Connection con = DatabaseLayer.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookingId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txtCusName.setText(rs.getString("customer_name"));
                txtSubTotal.setText(Validation.formatWithTwoDigitsWithGrouping(rs.getString("total_price")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to load booking details",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateGrandTotal() {

        String subTotal = txtSubTotal.getText();
        String addCharges = txtAddCharges.getText();
        String disAmount = txtDisAmount.getText();

        BigDecimal grandTotal = BigDecimal.ZERO;
        grandTotal = grandTotal.add(Validation.getBigDecimalOrZeroFromString(subTotal))
                .add(Validation.getBigDecimalOrZeroFromString(addCharges)).subtract(Validation.getBigDecimalOrZeroFromString(disAmount));
        txtGrandTotal.setText(Validation.formatWithTwoDigitsWithGrouping(grandTotal.toString()));
    }

    private void calculateBalance() {
        String cashPay = txtCashPay.getText();
        String cardPay = txtCardPay.getText();
        String grandTotal = txtGrandTotal.getText();

        BigDecimal balance = BigDecimal.ZERO;
        balance = Validation.getBigDecimalOrZeroFromString(grandTotal).subtract(Validation.getBigDecimalOrZeroFromString(cashPay))
                .subtract(Validation.getBigDecimalOrZeroFromString(cardPay));

        txtBalance.setText(Validation.formatWithTwoDigitsWithGrouping(balance.toString()));
    }
    
    private void clearAll() {
        txtCusName.setText("");
        txtSubTotal.setText("0.00");
        txtAddCharges.setText("0.00");
        txtAddNote.setText("");
        txtDisAmount.setText("0.00");
        comboPayMethod.setSelectedIndex(0);
        txtCashPay.setText("0.00");
        txtCardPay.setText("0.00");
        txtGrandTotal.setText("0.00");
        txtBalance.setText("0.00");
        tblBooking.clearSelection();
    }

    private void saveCheckOut() {
        String subTotal = Validation.formatWithTwoDigitsWithGrouping(txtSubTotal.getText());
        String addCharge = Validation.formatWithTwoDigitsWithGrouping(txtAddCharges.getText());
        String addNote = txtAddNote.getText();
        String dis = Validation.formatWithTwoDigitsWithGrouping(txtDisAmount.getText());
        String grandTotal = Validation.formatWithTwoDigitsWithGrouping(txtGrandTotal.getText());
        String payMethod = comboPayMethod.getSelectedItem().toString();
        String cashPay = Validation.formatWithTwoDigitsWithGrouping(txtCashPay.getText());
        String cardPay = Validation.formatWithTwoDigitsWithGrouping(txtCardPay.getText());
        String balance = Validation.formatWithTwoDigitsWithGrouping(txtBalance.getText());


        if (!Validation.isNotEmpty(txtCusName.getText())) {
            JOptionPane.showMessageDialog(this, "Select Customer From Table!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!addCharge.equals("0.00")) {
            if (!Validation.isNotEmpty(addNote)) {
                JOptionPane.showMessageDialog(this, "Enter Additional Charge Note!", "Error", JOptionPane.ERROR_MESSAGE);
                txtAddNote.requestFocus();
                return;
            }
        }
        if (cashPay.equals("0.00") && payMethod.equals("CASH")) {
            JOptionPane.showMessageDialog(this, "Enter Amount For Cash!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCashPay.requestFocus();
            return;
        }
        if (cardPay.equals("0.00") && payMethod.equals("CARD")) {
            JOptionPane.showMessageDialog(this, "Enter Amount For Card!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCashPay.requestFocus();
            return;
        }
        if (Validation.getBigDecimalOrZeroFromString(balance).compareTo(BigDecimal.ZERO) == 1) {
            JOptionPane.showMessageDialog(this, "Customer Credits Not Allowed!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCashPay.requestFocus();
            return;
        }
        
        if (Validation.getBigDecimalOrZeroFromString(balance).compareTo(BigDecimal.ZERO) == -1) {
            JOptionPane.showMessageDialog(this, "Negative Balance Not Allowed!", "Error", JOptionPane.ERROR_MESSAGE);
            txtCashPay.requestFocus();
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to check-out this?",
                    "Confirm", JOptionPane.YES_NO_OPTION
            );
            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            String updateQuery = "UPDATE booking SET "
                    + "actual_checkout = CURRENT_TIMESTAMP, checkout_subtotal = ?, additional_charges = ?, additional_note = ?, "
                    + "discount_amount = ?, grand_total = ?, payment_method = ?, "
                    + "cash_payment = ?, card_payment = ?, balance = ?, "
                    + "booking_status = ? "
                    + "WHERE booking_id = ?";

            try (Connection con = DatabaseLayer.getConnection();
                    PreparedStatement pst = con.prepareStatement(updateQuery)) {

                pst.setBigDecimal(1, Validation.getBigDecimalByFormattedString(subTotal));
                pst.setBigDecimal(2, Validation.getBigDecimalByFormattedString(addCharge));
                pst.setString(3, addNote);
                pst.setBigDecimal(4, Validation.getBigDecimalByFormattedString(dis));
                pst.setBigDecimal(5, Validation.getBigDecimalByFormattedString(grandTotal));
                pst.setString(6, payMethod);
                pst.setBigDecimal(7, Validation.getBigDecimalByFormattedString(cashPay));
                pst.setBigDecimal(8, Validation.getBigDecimalByFormattedString(cardPay));
                pst.setBigDecimal(9, Validation.getBigDecimalByFormattedString(balance));
                pst.setInt(10, Options.CHECK_OUT_STATUS);
                pst.setString(11, bookingId);
                
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Booking Check-Out successfully!");
                loadDataToTable();
                bookingId = null;
                clearAll();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating booking");
            }
    }
    
    private void selectedCombo() {
        if (comboPayMethod.getSelectedItem().equals("CASH")) {
            txtCardPay.setText("0.00");
            txtCardPay.setEditable(false);
            txtCashPay.setEditable(true);
            
        } else if (comboPayMethod.getSelectedItem().equals("CARD")) {
            txtCashPay.setText("0.00");
            txtCashPay.setEditable(false);
            txtCardPay.setEditable(true);
        } else if (comboPayMethod.getSelectedItem().equals("BOTH")) {
            txtCashPay.setText("0.00");
            txtCardPay.setText("0.00");
            txtCashPay.setEditable(true);
            txtCardPay.setEditable(true);
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
    
    private void printInvoice() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("bookingId", bookingId);
            CommonControl.printReport("invoice_a5", params, PrintStatus.WITH_VIEW);
        } catch (JRException | SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboPayMethod = new com.alee.laf.combobox.WebComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnClear = new com.alee.laf.button.WebButton();
        btnSave = new com.alee.laf.button.WebButton();
        btnPrint = new com.alee.laf.button.WebButton();
        jLabel8 = new javax.swing.JLabel();
        txtSubTotal = new com.alee.laf.text.WebFormattedTextField();
        txtAddCharges = new com.alee.laf.text.WebFormattedTextField();
        txtDisAmount = new com.alee.laf.text.WebFormattedTextField();
        txtGrandTotal = new com.alee.laf.text.WebFormattedTextField();
        txtCashPay = new com.alee.laf.text.WebFormattedTextField();
        txtCardPay = new com.alee.laf.text.WebFormattedTextField();
        txtBalance = new com.alee.laf.text.WebFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAddNote = new com.alee.laf.text.WebTextField();
        btnView = new com.alee.laf.button.WebButton();
        btnCheckOut = new com.alee.laf.button.WebButton();
        txtCusName = new com.alee.laf.text.WebTextField();
        btnUpdate = new com.alee.laf.button.WebButton();

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
        lblTitle.setText("  Check Out");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 20));

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tblBooking.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Booking ID", "Customer Name", "Check Out ", "Room No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBooking.setRowHeight(30);
        jScrollPane1.setViewportView(tblBooking);
        if (tblBooking.getColumnModel().getColumnCount() > 0) {
            tblBooking.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblBooking.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblBooking.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 660));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 830, 600));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Additional Charges :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 180, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sub Total :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Payment Method :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 150, 40));

        comboPayMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CASH", "CARD", "BOTH" }));
        comboPayMethod.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboPayMethod.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboPayMethodPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel2.add(comboPayMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 240, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cash Payment :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 150, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Balance :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 150, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Card Payment :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 150, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Grand Total :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 180, 60));

        btnClear.setText("CLEAR");
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel2.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 100, 50));

        btnSave.setText("SAVE");
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, 100, 50));

        btnPrint.setText("PRINT");
        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 100, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Discount Amount :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, 40));

        txtSubTotal.setEditable(false);
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setText("0.00");
        txtSubTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });
        txtSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubTotalKeyReleased(evt);
            }
        });
        jPanel2.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 240, 40));

        txtAddCharges.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAddCharges.setText("0.00");
        txtAddCharges.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAddCharges.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAddChargesMouseClicked(evt);
            }
        });
        txtAddCharges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddChargesActionPerformed(evt);
            }
        });
        txtAddCharges.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddChargesKeyReleased(evt);
            }
        });
        jPanel2.add(txtAddCharges, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 240, 40));

        txtDisAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisAmount.setText("0.00");
        txtDisAmount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDisAmount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDisAmountMouseClicked(evt);
            }
        });
        txtDisAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDisAmountActionPerformed(evt);
            }
        });
        txtDisAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDisAmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDisAmountKeyReleased(evt);
            }
        });
        jPanel2.add(txtDisAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 240, 40));

        txtGrandTotal.setEditable(false);
        txtGrandTotal.setForeground(new java.awt.Color(153, 0, 0));
        txtGrandTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGrandTotal.setText("0.00");
        txtGrandTotal.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jPanel2.add(txtGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 240, 70));

        txtCashPay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCashPay.setText("0.00");
        txtCashPay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCashPay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCashPayMouseClicked(evt);
            }
        });
        txtCashPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCashPayActionPerformed(evt);
            }
        });
        txtCashPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashPayKeyReleased(evt);
            }
        });
        jPanel2.add(txtCashPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 240, 40));

        txtCardPay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCardPay.setText("0.00");
        txtCardPay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCardPay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCardPayMouseClicked(evt);
            }
        });
        txtCardPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCardPayActionPerformed(evt);
            }
        });
        txtCardPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCardPayKeyReleased(evt);
            }
        });
        jPanel2.add(txtCardPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 240, 40));

        txtBalance.setEditable(false);
        txtBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBalance.setText("0.00");
        txtBalance.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 240, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Note :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 70, 40));

        txtAddNote.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtAddNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 360, 40));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 460, 660));

        btnView.setText("VIEW");
        btnView.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        jPanel4.add(btnView, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 20, 100, 40));

        btnCheckOut.setText("< CHECK OUT");
        btnCheckOut.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });
        jPanel4.add(btnCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 150, 40));

        txtCusName.setEditable(false);
        txtCusName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(txtCusName, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 300, 40));

        btnUpdate.setText("UPDATE");
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel4.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 20, 100, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1347, 700));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1367, 738));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
//        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        viewBookingDetails();
    }//GEN-LAST:event_btnViewActionPerformed

    private void txtSubTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubTotalKeyReleased

        calculateGrandTotal();
    }//GEN-LAST:event_txtSubTotalKeyReleased

    private void txtAddChargesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddChargesKeyReleased

        calculateGrandTotal();
    }//GEN-LAST:event_txtAddChargesKeyReleased

    private void txtDisAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisAmountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisAmountKeyPressed

    private void txtDisAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisAmountKeyReleased
        calculateGrandTotal();
    }//GEN-LAST:event_txtDisAmountKeyReleased

    private void txtCashPayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashPayKeyReleased
        calculateBalance();
    }//GEN-LAST:event_txtCashPayKeyReleased

    private void txtCardPayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardPayKeyReleased
        calculateBalance();
    }//GEN-LAST:event_txtCardPayKeyReleased

    private void txtAddChargesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAddChargesMouseClicked
        txtAddCharges.selectAll();
    }//GEN-LAST:event_txtAddChargesMouseClicked

    private void txtDisAmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDisAmountMouseClicked
        txtDisAmount.selectAll();
    }//GEN-LAST:event_txtDisAmountMouseClicked

    private void txtCashPayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCashPayMouseClicked
        txtCashPay.selectAll();
    }//GEN-LAST:event_txtCashPayMouseClicked

    private void txtCardPayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCardPayMouseClicked
        txtCardPay.selectAll();
    }//GEN-LAST:event_txtCardPayMouseClicked

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        setBookingDetails();
        calculateGrandTotal();
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        txtAddCharges.requestFocus();
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void txtAddChargesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddChargesActionPerformed
        txtDisAmount.requestFocus();
    }//GEN-LAST:event_txtAddChargesActionPerformed

    private void txtDisAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisAmountActionPerformed
        comboPayMethod.requestFocus();

    }//GEN-LAST:event_txtDisAmountActionPerformed

    private void comboPayMethodPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboPayMethodPopupMenuWillBecomeInvisible
        selectedCombo();
        txtCashPay.requestFocus();
    }//GEN-LAST:event_comboPayMethodPopupMenuWillBecomeInvisible

    private void txtCashPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCashPayActionPerformed
        txtCardPay.requestFocus();
    }//GEN-LAST:event_txtCashPayActionPerformed

    private void txtCardPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCardPayActionPerformed
        btnSave.requestFocus();
    }//GEN-LAST:event_txtCardPayActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        bookingId = null;
        clearAll();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveCheckOut();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateBookingDetails();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        printInvoice();
    }//GEN-LAST:event_btnPrintActionPerformed

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
            java.util.logging.Logger.getLogger(CheckOut.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCheckOut;
    private com.alee.laf.button.WebButton btnClear;
    private com.alee.laf.button.WebButton btnPrint;
    private com.alee.laf.button.WebButton btnSave;
    private com.alee.laf.button.WebButton btnUpdate;
    private com.alee.laf.button.WebButton btnView;
    private com.alee.laf.combobox.WebComboBox comboPayMethod;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblBooking;
    private com.alee.laf.text.WebFormattedTextField txtAddCharges;
    private com.alee.laf.text.WebTextField txtAddNote;
    private com.alee.laf.text.WebFormattedTextField txtBalance;
    private com.alee.laf.text.WebFormattedTextField txtCardPay;
    private com.alee.laf.text.WebFormattedTextField txtCashPay;
    private com.alee.laf.text.WebTextField txtCusName;
    private com.alee.laf.text.WebFormattedTextField txtDisAmount;
    private com.alee.laf.text.WebFormattedTextField txtGrandTotal;
    private com.alee.laf.text.WebFormattedTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}
