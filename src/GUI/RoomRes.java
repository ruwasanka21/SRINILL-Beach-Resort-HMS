package GUI;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
//import java.util.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Modern Hotel Room Reservation System - Strictly Sized to (1157, 594)
 * Fully Scrollable (Vertical and Horizontal)
 * @author THARINDU
 */
public class RoomRes extends JPanel {
    
    // Modern Color Palette
    private final Color COLOR_PRIMARY = new Color(99, 102, 241);
    private final Color COLOR_SUCCESS = new Color(34, 197, 94);
    private final Color COLOR_DANGER = new Color(239, 68, 68);
    private final Color COLOR_BACKGROUND = new Color(248, 250, 252);
    private final Color COLOR_SURFACE = Color.WHITE;
    private final Color COLOR_TEXT_PRIMARY = new Color(15, 23, 42);
    private final Color COLOR_BORDER = new Color(226, 232, 240);
    
    // Room status tracking
    private Map<String, Boolean> roomStatus = new HashMap<>();
    private Calendar currentMonth = Calendar.getInstance();
    private List<RoomInfo> rooms = new ArrayList<>(); 
    
    // EXACT DIMENSIONS REQUESTED
    private static final int TARGET_WIDTH = 1157;
    private static final int TARGET_HEIGHT = 594;
    
    // Inner Cell Sizes
    private static final int ROOM_COLUMN_WIDTH = 150; 
    private static final int DAY_COLUMN_WIDTH = 45;
    private static final int ROW_HEIGHT = 45;
    private static final int DAYS_IN_MONTH = 31;
    
    private JPanel gridPanel;

    private static class RoomInfo {
        int roomNo;
        String label;
        RoomInfo(int roomNo, String label) {
            this.roomNo = roomNo;
            this.label = label;
        }
    }

    public RoomRes() {
        // 1. STRICTLY ENFORCE THE REQUESTED SIZE
        Dimension strictSize = new Dimension(TARGET_WIDTH, TARGET_HEIGHT);
        setPreferredSize(strictSize);
        setMinimumSize(strictSize);
        setMaximumSize(strictSize);
        
        fetchRoomsFromDatabase();
        initializeUI();
        //setupModernStyling();
        refreshCalendar();
        //panal_load.set
    }

    private void fetchRoomsFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/srinill_beach_resort_2"; 
        String username = "root"; 
        String password = ""; 
   
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT `room_no`, `category` FROM `rooms`")) {
            
            rooms.clear();
            while (rs.next()) {
                int roomNo = rs.getInt("room_no");
                String roomType = rs.getString("category");
                String label = (roomType != null && !roomType.trim().isEmpty()) ? roomType + " " + roomNo : "Room " + roomNo;
                rooms.add(new RoomInfo(roomNo, label));
            }
            
            if (rooms.isEmpty()) loadFallbackRooms();
            
        } catch (SQLException e) {
            loadFallbackRooms();
        }
        // Sort rooms sequentially
        rooms.sort((r1, r2) -> Integer.compare(r1.roomNo, r2.roomNo));
    }
    
    public void refreshCalendar() {

        if (gridPanel == null) {
            return;
        }
        
        for (Component comp : gridPanel.getComponents()) {
        if (comp instanceof ModernRoomButton) {
            ModernRoomButton btn = (ModernRoomButton) comp;
            btn.booked = false;
            btn.setBackground(COLOR_SUCCESS);
        }
    }

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DatabaseLayer.mycon();

            // Get all reservations
            String sql = "SELECT room_id, checkInDate, checkOutDate FROM roomreservation";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int room = rs.getInt("room_id");
                java.sql.Date checkIn = rs.getDate("checkInDate");
                java.sql.Date checkOut = rs.getDate("checkOutDate");

                // Convert to Calendar for looping
                Calendar start = Calendar.getInstance();
                start.setTime(checkIn);

                Calendar end = Calendar.getInstance();
                end.setTime(checkOut);

                while (!start.after(end)) {
                    int day = start.get(Calendar.DAY_OF_MONTH);

                    markRoomBooked(room, day);

                    start.add(Calendar.DATE, 1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ignored) {
            }
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception ignored) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private void markRoomBooked(int roomNo, int day) {
        for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof ModernRoomButton) {
                ModernRoomButton btn = (ModernRoomButton) comp;

                if (btn.roomNo == roomNo && btn.day == day) {
                    btn.setBackground(COLOR_DANGER); // red
                    btn.booked = true;               // mark as booked
                }
            }
        }
    }

   

    private void loadFallbackRooms() {
        for (int i = 1; i <= 20; i++) { // Increased fallback rooms to test vertical scrolling
            rooms.add(new RoomInfo(100 + i, "Suite " + (100+i)));
        }
    }

    private void initializeUI() {
        setBackground(COLOR_BACKGROUND);
        setLayout(new BorderLayout(0, 0));
        
        add(createModernHeader(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
        add(createModernFooter(), BorderLayout.SOUTH);
    }

    private JPanel createModernHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_SURFACE);
        header.setPreferredSize(new Dimension(TARGET_WIDTH, 80));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_BORDER),
                new EmptyBorder(15, 30, 15, 30)
        ));

        JLabel title = new JLabel("Room Availability - "
                + new SimpleDateFormat("MMMM yyyy").format(currentMonth.getTime()));
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(COLOR_TEXT_PRIMARY);
        header.add(title, BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setBackground(COLOR_SURFACE);

        // CREATE BUTTON
        JButton refreshBtn = createModernButton("Refresh", COLOR_PRIMARY, Color.WHITE);

        // CLICK EVENT
        refreshBtn.addActionListener(e -> {
            refreshCalendar();
        });

        actions.add(refreshBtn);
        header.add(actions, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainContent() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(COLOR_BACKGROUND);
        container.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel card = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(COLOR_SURFACE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        card.add(createScrollableCalendar(), BorderLayout.CENTER);
        container.add(card, BorderLayout.CENTER);
        
        return container;
    }

    private JScrollPane createScrollableCalendar() {
        // --- CRITICAL FIX: Calculate Exact Grid Dimensions ---
        // Width = (Days * WidthPerDay) + Gaps
        int totalGridWidth = (DAYS_IN_MONTH * DAY_COLUMN_WIDTH) + (DAYS_IN_MONTH - 1); 
        // Height = (Rooms * HeightPerRow) + Gaps
        int totalGridHeight = (rooms.size() * ROW_HEIGHT) + (rooms.size() - 1);

        // 1. Center Grid (Buttons)
        gridPanel = new JPanel(new GridLayout(rooms.size(), DAYS_IN_MONTH, 1, 1));
        gridPanel.setBackground(COLOR_BORDER);
        // Force the layout to overflow by setting its preferred size
        gridPanel.setPreferredSize(new Dimension(totalGridWidth, totalGridHeight));
        
        // 2. Row Headers (Room Names fixed to the left)
        JPanel roomLabels = new JPanel(new GridLayout(rooms.size(), 1, 1, 1));
        roomLabels.setBackground(COLOR_BORDER);
        // Match the height of the grid panel
        roomLabels.setPreferredSize(new Dimension(ROOM_COLUMN_WIDTH, totalGridHeight));

        for (RoomInfo room : rooms) {
            JLabel lbl = new JLabel("  " + room.label);
            lbl.setOpaque(true);
            lbl.setBackground(COLOR_SURFACE);
            lbl.setPreferredSize(new Dimension(ROOM_COLUMN_WIDTH, ROW_HEIGHT));
            roomLabels.add(lbl);
            
            for (int day = 1; day <= DAYS_IN_MONTH; day++) {
                gridPanel.add(new ModernRoomButton(room.roomNo, day, false));
            }
        }

        // 3. Column Headers (Day Numbers 1-31 fixed to the top)
        JPanel dayHeaders = new JPanel(new GridLayout(1, DAYS_IN_MONTH, 1, 1));
        dayHeaders.setBackground(COLOR_BORDER);
        // Match the width of the grid panel
        dayHeaders.setPreferredSize(new Dimension(totalGridWidth, 40));
        
        for (int day = 1; day <= DAYS_IN_MONTH; day++) {
            JLabel dayLbl = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            dayLbl.setOpaque(true);
            dayLbl.setBackground(COLOR_SURFACE);
            dayLbl.setPreferredSize(new Dimension(DAY_COLUMN_WIDTH, 40));
            dayHeaders.add(dayLbl);
        }

        // 4. Top-Left Corner Label
        JLabel corner = new JLabel("ROOMS", SwingConstants.CENTER);
        corner.setOpaque(true);
        corner.setBackground(COLOR_SURFACE);
        corner.setFont(new Font("SansSerif", Font.BOLD, 10));
        corner.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, COLOR_BORDER));

        // Assemble seamlessly using native JScrollPane features
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setRowHeaderView(roomLabels);
        scrollPane.setColumnHeaderView(dayHeaders);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Ensure scrollbars appear when needed
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Smooth scrolling adjustments
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(25);
        
        return scrollPane;
    }

    private JPanel createModernFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(COLOR_SURFACE);
        // Fixed height for footer
        footer.setPreferredSize(new Dimension(TARGET_WIDTH, 60)); 
        footer.setBorder(new EmptyBorder(10, 30, 10, 30));
        
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        legend.setOpaque(false);
        legend.add(createLegendItem("Available", COLOR_SUCCESS));
        legend.add(createLegendItem("Booked", COLOR_DANGER));
        
        footer.add(legend, BorderLayout.WEST);
        return footer;
    }

    private JPanel createLegendItem(String text, Color color) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        p.setOpaque(false);
        JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(15, 15));
        box.setBackground(color);
        p.add(box);
        p.add(new JLabel(text));
        return p;
    }

    private JButton createModernButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void setupModernStyling() {
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch (Exception ignored) {}
    }

    private class ModernRoomButton extends JButton {
        private int roomNo;
        private int day;
        private boolean booked;
        
        public ModernRoomButton(int r, int d, boolean booked) {
            this.roomNo = r;
            this.day = d;
            this.booked = booked;
        
            setBackground(booked ? COLOR_DANGER : COLOR_SUCCESS);
            setBorder(BorderFactory.createLineBorder(COLOR_SURFACE, 1));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(DAY_COLUMN_WIDTH, ROW_HEIGHT));
            
            addActionListener(e -> showReservationDialog());
        }
        
        private void showReservationDialog() {

        String monthYear = new SimpleDateFormat("MMMM yyyy")
                .format(currentMonth.getTime());

        String message =
                "Room No: " + roomNo +
                "\nDate: " + day + " " + monthYear +
                "\nStatus: " + (booked ? "Booked" : "Available");

        if (booked) {
            JOptionPane.showMessageDialog(
                    RoomRes.this,
                    message,
                    "Room Already Booked",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {

            int confirm = JOptionPane.showConfirmDialog(
                    RoomRes.this,
                    message + "\n\nDo you want to reserve this room?",
                    "Confirm Reservation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                
                LocalDate selectedDate = LocalDate.of(
                        currentMonth.get(Calendar.YEAR),
                        currentMonth.get(Calendar.MONTH) + 1,
                        day
                );

                Reservation resPanel = new Reservation(roomNo, RoomRes.this);

                JFrame frame = new JFrame("Room Reservation");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(900, 600);
                frame.setLocationRelativeTo(null);
                frame.add(resPanel);
                frame.setVisible(true);
                
                
//                booked = true;
//                setBackground(COLOR_DANGER);
//
//                JOptionPane.showMessageDialog(
//                        RoomRes.this,
//                        "Reservation Successful!",
//                        "Success",
//                        JOptionPane.INFORMATION_MESSAGE
//                );
            }
        }
    }
    }
}