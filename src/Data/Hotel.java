package Data;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kim Nha
 */
public class Hotel implements Comparable {

    private String id, name;
    private int roomAvailable;
    private String address;
    private String phoneNumber;
    private int rating;

    public Hotel() {
    }

    public Hotel(String id, String name, int roomAvailable, String address, String phoneNumber, int rating) {
        this.id = id;
        this.name = name;
        this.roomAvailable = roomAvailable;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(int roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return id + "-" + name + "-" + roomAvailable + "-" + address + "-" + phoneNumber + "-" + rating;
    }
    
    public void showProfile() {
        String msg = String.format("| %-5s| %-21s| %5d | %-73s| %-10s | %d %-4s |", id, name, roomAvailable, address, phoneNumber, rating, "star");
        System.out.println(msg);
    }

    @Override
    public int compareTo(Object other) {
        Hotel o = (Hotel)other;
        return o.name.compareToIgnoreCase(name);
    }
}