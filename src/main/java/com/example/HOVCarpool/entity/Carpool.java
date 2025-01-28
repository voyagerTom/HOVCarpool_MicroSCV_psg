package com.example.HOVCarpool.entity;

import jakarta.persistence.*;

//<!--	createBy Chen Ting Yu 2024-->
import java.sql.Timestamp;

@Entity
@Table(name="carpool")
public class Carpool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="driver_id")
    private int driverId;

    @Column(name="launch_Time")
    private String launchTime;
    private String site;
    private String destination;

    @Column(name="pick_Amt")
    private int pickAmt;
    @Column(name="order_Amt")
    private int orderAmt;


    @Column(name="is_cancel")
    private String isCancel;

    @Column(name="create_time")
    private String createTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }


    public String getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPickAmt() {
        return pickAmt;
    }

    public void setPickAmt(int pickAmt) {
        this.pickAmt = pickAmt;
    }

    public int getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(int orderAmt) {
        this.orderAmt = orderAmt;
    }


    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
