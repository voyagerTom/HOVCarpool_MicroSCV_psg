package com.example.HOVCarpool.dao;

import com.example.HOVCarpool.entity.ApproveTodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApproveTodoListRepository extends JpaRepository<ApproveTodoList,Integer> {


    List<ApproveTodoList> findByUserIdAndRoleId(int userId , int roleId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO approve_todo_list (user_id, role_id, msg,apply_time)  VALUES(:userId,:roleId,:msg,:applyTime);", nativeQuery = true)
    int addAproveInfo(int userId, int roleId, String msg, String applyTime);

}
