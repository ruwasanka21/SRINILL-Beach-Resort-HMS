/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
public class Booking {

    private int bookingId;
    private String customerName;
    private String customerTelNo;
    private String customerAddress;
    private String customerNic;

    private String bookingDate;
    private String checkinDate;
    private String checkoutDate;

    private int noOfPax;
    private int child;
    private String bookingFrom;

    private String roomNo;
    private double roomPrice;
    private double totalPrice;

    private String remark;

    private String actualCheckinDateTime;
    private String actualCheckoutDateTime;

    private double checkoutSubtotal;
    private double additionalCharges;
    private double discountAmount;
    private double grandTotal;

    private String paymentMethod;
    private double cashPayment;
    private double cardPayment;
    private double balance;

    private int bookingStatus;
    private String bookingLastUpdateTime;

    public Booking() {
    }

    // You can generate full constructor if needed
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTelNo() {
        return customerTelNo;
    }

    public void setCustomerTelNo(String customerTelNo) {
        this.customerTelNo = customerTelNo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNoOfPax() {
        return noOfPax;
    }

    public void setNoOfPax(int noOfPax) {
        this.noOfPax = noOfPax;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public String getBookingFrom() {
        return bookingFrom;
    }

    public void setBookingFrom(String bookingFrom) {
        this.bookingFrom = bookingFrom;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActualCheckinDateTime() {
        return actualCheckinDateTime;
    }

    public void setActualCheckinDateTime(String actualCheckinDateTime) {
        this.actualCheckinDateTime = actualCheckinDateTime;
    }

    public String getActualCheckoutDateTime() {
        return actualCheckoutDateTime;
    }

    public void setActualCheckoutDateTime(String actualCheckoutDateTime) {
        this.actualCheckoutDateTime = actualCheckoutDateTime;
    }

    public double getCheckoutSubtotal() {
        return checkoutSubtotal;
    }

    public void setCheckoutSubtotal(double checkoutSubtotal) {
        this.checkoutSubtotal = checkoutSubtotal;
    }

    public double getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(double additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(double cashPayment) {
        this.cashPayment = cashPayment;
    }

    public double getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(double cardPayment) {
        this.cardPayment = cardPayment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingLastUpdateTime() {
        return bookingLastUpdateTime;
    }

    public void setBookingLastUpdateTime(String bookingLastUpdateTime) {
        this.bookingLastUpdateTime = bookingLastUpdateTime;
    }
}
