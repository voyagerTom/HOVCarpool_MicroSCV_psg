package com.example.HOVCarpool.dao;

import com.example.HOVCarpool.entity.Carpool;
import com.example.HOVCarpool.entity.CarpoolMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarpoolMapRepository extends JpaRepository<CarpoolMap, Integer> {
    @Transactional
    @Modifying
    @Query("update CarpoolMap c set c.isCancel = ?1 where c.carpoolId = ?2 and isCancel=\"N\"")
    int updateIscancelByCarpoolId(@NonNull String isCancel, @NonNull int carpoolId);

//    <!--	createBy Chen Ting Yu 2024-->
    //查看是否同一個carpool被同一個user預定
    @Query(value = "SELECT * FROM carpool_map cm WHERE CarPool_id =:carpoolId AND User_id =:userId AND is_cancel='N' ", nativeQuery = true)
    CarpoolMap checkReorder(int carpoolId, int  userId);

    //屬性開頭都大寫(駝峰式命名)，且屬性要在entity裡面
    CarpoolMap findByCmid(int cmid);

    //添加1筆共乘資料
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carpool_map (carpool_id, user_id,order_time,is_cancel) VALUES(:carpoolId,:userId,:bookTime,'N')", nativeQuery = true)
    int addCarpoolMap(int carpoolId, int  userId,String bookTime);


    @Modifying
    @Transactional
    @Query(value = "UPDATE hov.carpool_map SET  is_cancel='Y' WHERE cmid= :cmid ", nativeQuery = true)
    int cancelOrderCarpool(int cmid);

    //資時間排序找出最新的資料
    List<CarpoolMap>  findFirstByUserIdOrderByOrderTimeDesc(int userId);

    //取得passenger所有共乘資料
    List<CarpoolMap> findByUserIdAndIsCancel(int userId,String isCancel);

    List<CarpoolMap> findByCarpoolId(int carpoolId);



}
