package com.example.HOVCarpool.service.serviceImp;

import com.example.HOVCarpool.controller.PassengerContoller;
import com.example.HOVCarpool.dao.*;
import com.example.HOVCarpool.dto.OrderCarpoolDTO;
import com.example.HOVCarpool.entity.ApproveTodoList;
import com.example.HOVCarpool.entity.Carpool;
import com.example.HOVCarpool.entity.CarpoolMap;
import com.example.HOVCarpool.entity.UserRoleMap;
import com.example.HOVCarpool.service.PassengerService;
import com.example.HOVCarpool.tool.TimeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerServiceImp implements PassengerService {
    private static final Logger log= LoggerFactory.getLogger(PassengerServiceImp.class);

    @Autowired
    private CarpoolRepository carpoolRepository;

    @Autowired
    private CarpoolMapRepository carpoolMapRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleMapRepository userRoleMapRepository;

    @Autowired
    private ApproveTodoListRepository approveTodoListRepository;

    @Override
    @Transactional
    public boolean ordercarpool(OrderCarpoolDTO orderCarpoolDTO) {
        int carpoolId = orderCarpoolDTO.getCarpoolId();

        //todo 審核機制 【該carpool是否已滿 / 該P是否重複預約 】
        //確認carpool是否被重複預約
        //預防預約後取消又預定
        int userId = orderCarpoolDTO.getUserId();
        CarpoolMap carpoolMap = carpoolMapRepository.checkReorder(carpoolId, userId);

        if (carpoolMap != null) {
            String isCancel = carpoolMap.getIsCancel();
            if (isCancel.equalsIgnoreCase("N")) {
//                System.out.println("重複預約");
                log.info("重複預約");
                return false;
            }

        }


        //確認是否已滿位
        Carpool carpool = carpoolRepository.findById(carpoolId).orElse(null);
        ;
        int leftSeat = carpool.getPickAmt() - carpool.getOrderAmt();
        if (leftSeat <= 0) {
            System.out.println("座位已滿");
            return false;
        }

        //todo 更新 carool order_AMT +1
        int isBookOk1 = carpoolRepository.carpoolAddUser(carpoolId);

        //todo carpool_map 添加一筆資料
        String now = TimeTool.getTimeNow();
        int isBookOk2 = carpoolMapRepository.addCarpoolMap(carpoolId, userId, String.valueOf(now));

        if (isBookOk1 == 1 && isBookOk2 == 1) {
            System.out.println("carpoolId:" + carpoolId + " _加入一位共乘");
            System.out.println("carpoolId:" + carpoolId + " _map預約成功");
            return true;
        }
        return false;
    }


    @Transactional
    @Override
    public boolean cancelOrderCarpoolById(OrderCarpoolDTO orderCarpoolDTO) {
        int cmId = orderCarpoolDTO.getCmid();

        //todo 防呆預防沒有預定的人來取消
        CarpoolMap carpoolMap = carpoolMapRepository.findByCmid(cmId);
        //驗證P是否重複取消
        String isCancel = carpoolMap.getIsCancel();
        if (carpoolMap == null ) {
            System.out.println("無此訂單或已取消 訂單號:" + cmId);
            return false;
        }

        int userId = carpoolMap.getUserId();
        int carpoolId = carpoolMap.getCarpoolId();
        if (isCancel.equalsIgnoreCase("Y")) {
            System.out.println("userId: " + userId + " 車次【" + carpoolId + "】無預定或已取消 ");
            return false;
        }

        //todo 更新 carool order_AMT -1
        //新寫法
        Carpool carpoolRTN= carpoolRepository.findById(carpoolId).orElse(null);
        carpoolRTN.setOrderAmt(carpoolRTN.getOrderAmt()-1);
        carpoolRTN=carpoolRepository.save(carpoolRTN);

        //todo 取消cappool_map紀錄
        CarpoolMap carpoolMapRTN = carpoolMapRepository.findById(cmId).orElse(null);
        carpoolMapRTN.setIsCancel("Y");
        carpoolMapRTN=carpoolMapRepository.save(carpoolMapRTN);


        if (carpoolMapRTN.getCmid()!=0 && carpoolRTN.getId()!=0 ) {
            System.out.println("carpoolId:" + carpoolRTN.getId() + " _移除一位共乘");
            System.out.println("carpoolMapId:" +carpoolMapRTN.getCmid() + " carpool_map取消搭乘成功");
            return true;
        }
        return false;
    }

    @Override
    public List<CarpoolMap> checkmyLastCarpool(int userId) {

        return carpoolMapRepository.findFirstByUserIdOrderByOrderTimeDesc(userId);
    }


    @Override
    public List<Carpool> checkMyCarpool(int userId) {
        //todo 取得已預訂的carpoolId
        List<CarpoolMap> carpoolMaps = carpoolMapRepository.findByUserIdAndIsCancel(userId, "N");

        //todo 用carpoolID取找共乘資料
        List<Carpool> carpools = new ArrayList<>();

        for (CarpoolMap carpoolMap : carpoolMaps) {
            int carpoolId = carpoolMap.getCarpoolId();
            Carpool carpool = carpoolRepository.findById(carpoolMap.getCarpoolId()).orElse(null);
            carpools.add(carpool);
        }

        return carpools;

    }

    @Override
    public boolean applyForDriver(int userId, String IDDco) {
        //司機代號，其實也可設計成到DB抓資料
        int driverId = 200;

        //todo case: 審查是否已為司機 to urm table 查看
        //如果userID 存在urm中，則回傳false
        UserRoleMap userRoleMap = userRoleMapRepository.findByUserIdAndRoleId(userId, driverId);
        if (userRoleMap != null) {
            System.out.println("userId: " + userId + " 您已是司機，無須再申請");
            return false;
        }

        //todo case:審核中
        //findByUserIdAndRoldId
        List<ApproveTodoList> approveTodoLists =approveTodoListRepository.findByUserIdAndRoleId(userId,driverId);
        for (ApproveTodoList approveTodoList:approveTodoLists){
            if (approveTodoList.getIsApprove().equalsIgnoreCase("TBD")){
                System.out.println("案件審核中，請稍後");
                return false;
            }
        }

        //todo 申請審核放到approveTodoList
        String now = TimeTool.getTimeNow();
        int isApplyOK = approveTodoListRepository.addAproveInfo(userId, driverId, "申請成為司機", now);
        if (isApplyOK == 1) {
            System.out.println("已完成申請【轉職司機】，待審核");
            return true;
        }
        return false;

    }
}
