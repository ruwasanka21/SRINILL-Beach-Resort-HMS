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
public class Room {

    private String roomNo;
    private String roomType;
    private int roomStatus;
    private double roomPrice;
    private String roomLastUpdateTime;

    public Room() {
    }

    public Room(String roomNo, String roomType, int roomStatus, double roomPrice, String roomLastUpdateTime) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
        this.roomLastUpdateTime = roomLastUpdateTime;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomLastUpdateTime() {
        return roomLastUpdateTime;
    }

    public void setRoomLastUpdateTime(String roomLastUpdateTime) {
        this.roomLastUpdateTime = roomLastUpdateTime;
    }
}
