package com.example.HOVCarpool.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="carpool_map")
public class CarpoolMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cmid;

    @Column(name = "carpool_id")
    private int carpoolId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "is_cancel")
    private String isCancel;

    @Column(name = "order_time")
    private String orderTime;


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

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
