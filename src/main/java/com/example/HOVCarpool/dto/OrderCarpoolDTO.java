package com.example.HOVCarpool.dto;

public class OrderCarpoolDTO {
    private int carpoolId;
    private int userId;
    private int cmid;

    public int getCmid() {
        return cmid;
    }

    public void setCmid(int cmid) {
        this.cmid = cmid;
    }

    public int getCarpoolId() {
        return carpoolId;
    }

    public void setCarpoolId(int carpoolId) {
        this.carpoolId = carpoolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
