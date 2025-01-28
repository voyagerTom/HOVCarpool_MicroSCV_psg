package com.example.HOVCarpool.entity;


import jakarta.persistence.*;

@Entity
@Table(name="approve_todo_list")
public class ApproveTodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="atl_id")
    private int atlId;       // 待審核清單ID

    @Column(name="user_id")
    private int userId;      // 用戶ID

    @Column(name="role_id")
    private int roleId;      // 角色ID
    private String msg;      // 說明該筆資料是要申請什麼


    @Column(name="apply_time")
    private String applyTime;

    @Column(name="is_approve")
    private String isApprove;   //審核狀態


    public int getAtlId() {
        return atlId;
    }

    public void setAtlId(int atlId) {
        this.atlId = atlId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }
}
