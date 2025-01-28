package com.example.HOVCarpool.dao;

import com.example.HOVCarpool.entity.Carpool;
import com.example.HOVCarpool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface CarpoolRepository extends JpaRepository<Carpool, Integer> {
   List< Carpool> findBySite(String site);

   @Modifying
   @Transactional
   @Query(value = "UPDATE carpool SET order_AMT=order_AMT+1 WHERE id=:carpoolId", nativeQuery = true)
   int carpoolAddUser(int carpoolId);

   @Modifying
   @Transactional
   @Query(value = "UPDATE hov.carpool SET order_AMT=order_AMT-1 WHERE id=:carpoolId", nativeQuery = true)
   int carpoolCancelUser(int carpoolId);

   List<Carpool> findByDriverId(int driverId);


   //用在D取消carpool的更新項目
   @Modifying
   @Transactional
   @Query(value = "UPDATE carpool SET is_cancel ='Y', order_AMT=order_AMT-:userAMT WHERE id =:carpoolId", nativeQuery = true)
   int carpoolCancel(int userAMT,int carpoolId);


}
