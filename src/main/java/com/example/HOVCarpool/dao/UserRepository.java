package com.example.HOVCarpool.dao;
//<!--	createBy Chen Ting Yu 2024-->
import com.example.HOVCarpool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);

    User findByEmail(String Email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO approve_todo_list (user_id, role_id, msg,apply_time)  VALUES(:userId,:roleId,:msg,:applyTime);", nativeQuery = true)
    int addAproveInfo(int userId, int roleId, String msg, String applyTime);


    @Query(value = "SELECT u.*  FROM `user` u \n" +
            "JOIN user_role_map urm ON u.id = urm.user_id \n" +
            "JOIN `role` r ON r.id = urm.role_id \n" +
            "WHERE u.id =:driverId AND r.role_name=:userType", nativeQuery = true)
    User findUserTypeById(int driverId, String userType);


    @Query(value = "SELECT u.*  FROM `user` u \n" +
            "JOIN user_role_map urm ON u.id = urm.user_id \n" +
            "JOIN `role` r ON r.id = urm.role_id \n" +
            "WHERE r.role_name=:userType", nativeQuery = true)
    List<User> findUserByType(String userType);
}
