package com.example.HOVCarpool.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role_map")
public class UserRoleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="urm_id")
    private int urmId;

    @Column(name="user_id")
    private int userId;

    @Column(name="role_id")
    private int roleId;

    public int getUrmId() {
        return urmId;
    }

    public void setUrmId(int urmId) {
        this.urmId = urmId;
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
}
