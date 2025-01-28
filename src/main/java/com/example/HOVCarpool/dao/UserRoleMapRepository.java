package com.example.HOVCarpool.dao;

import com.example.HOVCarpool.entity.UserRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRoleMapRepository extends JpaRepository<UserRoleMap,Integer> {
    UserRoleMap findByUserIdAndRoleId(int userId,int roleId);

}
