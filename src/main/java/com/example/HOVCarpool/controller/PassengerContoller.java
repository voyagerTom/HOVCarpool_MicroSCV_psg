package com.example.HOVCarpool.controller;

import com.example.HOVCarpool.dao.CarpoolMapRepository;
import com.example.HOVCarpool.dao.CarpoolRepository;
import com.example.HOVCarpool.dto.OrderCarpoolDTO;
import com.example.HOVCarpool.entity.Carpool;
import com.example.HOVCarpool.entity.CarpoolMap;
import com.example.HOVCarpool.responsecustom.APIResponse;
import com.example.HOVCarpool.service.serviceImp.PassengerServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerContoller {
    private static final Logger log= LoggerFactory.getLogger(PassengerContoller.class);

    @Autowired
    private CarpoolRepository carpoolRepo;

    @Autowired
    private PassengerServiceImp ps;
    @Autowired
    private CarpoolMapRepository carpoolMapRepository;

    /**
     * make a carpool reservation
     *
     * @param
     */
    @PostMapping("/ordercarpool")
    @Operation(summary = "預定共乘",responses = {
            @ApiResponse(responseCode = "200", description = "回傳預定成功 & 車次資訊"),
            @ApiResponse(responseCode = "409", description = "回傳預定失敗")
    })

    public ResponseEntity<APIResponse> orderCarpool(@RequestBody OrderCarpoolDTO orderCarpoolDTO) {
        log.info("乘客:{} - 預約共乘車次:{} 開始",orderCarpoolDTO.getUserId(),orderCarpoolDTO.getCarpoolId());
        APIResponse apiResponse = new APIResponse();
        //todo 創建共乘訂購
        boolean isOrderOK = ps.ordercarpool(orderCarpoolDTO);
        if (isOrderOK) {
            //todo 查詢該角色最新的共乘預約
            List<CarpoolMap>  carpoolMaps = ps.checkmyLastCarpool(orderCarpoolDTO.getUserId());
            apiResponse.setObject(carpoolMaps);
            apiResponse.setHttpCode(HttpStatus.OK);
            apiResponse.setMsg("預約成功");
            log.info("預約共乘代碼: {} ",carpoolMaps.get(0).getCmid());
            log.info("乘客:{} - 預約共乘車次:{} 結束-success.",orderCarpoolDTO.getUserId(),orderCarpoolDTO.getCarpoolId());
            return ResponseEntity.ok().body(apiResponse);
        }

        apiResponse.setHttpCode(HttpStatus.OK);
        apiResponse.setMsg("預約失敗，請確認是否已滿位或重複預約");
        log.info("乘客:{} - 預約共乘車次:{} 結束. 預約結果: {}",orderCarpoolDTO.getUserId(),orderCarpoolDTO.getCarpoolId(),"fail，請確認是否已滿位或重複預約");

        //資源衝突
        return ResponseEntity.ok().body(apiResponse);

    }

    //取得已預訂的共乘
    @GetMapping("/checkmycarpool")
    @Operation(summary = "查詢共乘",description = "取得已預訂的車次")
    public ResponseEntity<APIResponse> checkMyCarpool(@RequestParam int userId) {
        log.info("乘客:{} - 查詢共乘車次 開始",userId);
        APIResponse apiResponse = new APIResponse();
        List<Carpool> carpools = ps.checkMyCarpool(userId);
        apiResponse.setCarpoolList(carpools);
        apiResponse.setHttpCode(HttpStatus.OK);
        apiResponse.setMsg("查詢成功");
        log.info("乘客:{} - 查詢共乘車次 結束",userId);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    //user取消自己的carpool
    @PostMapping("/cancelordercarpoolbyid")
    @Operation(summary = "取消")
    public ResponseEntity<APIResponse> cancelOrderCarpoolById(@RequestBody OrderCarpoolDTO orderCarpoolDTO) {
        log.info("乘客:{} - 取消共乘車次:{} 開始",orderCarpoolDTO.getUserId(),orderCarpoolDTO.getCarpoolId());
        APIResponse apiResponse = new APIResponse();
        boolean isCancelOk = ps.cancelOrderCarpoolById(orderCarpoolDTO);

        if (isCancelOk) {
            //todo 查詢該角色最新的共乘預約
            List<CarpoolMap>  carpoolMaps = ps.checkmyLastCarpool(orderCarpoolDTO.getUserId());
            apiResponse.setObject(carpoolMaps);
            apiResponse.setHttpCode(HttpStatus.OK);
            apiResponse.setMsg("取消成功");
            log.info("乘客:{} - 取消共乘車次:{} 結束-結果: success ",orderCarpoolDTO.getUserId(),orderCarpoolDTO.getCarpoolId());

            return ResponseEntity.ok().body(apiResponse);
        }

        apiResponse.setHttpCode(HttpStatus.OK);
        apiResponse.setMsg("取消失敗: 無此訂單或已取消");
        log.info("乘客:{} - 取消共乘車次:{} 結束-結果: fail ，請確認是否無此訂單或已取消 ",orderCarpoolDTO.getUserId(),orderCarpoolDTO.getCarpoolId());

        //資源衝突
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }


    @GetMapping("/getcarpoolbysite")
    public ResponseEntity<APIResponse> getCarpoolBySite(@RequestParam String site) {
        APIResponse apiResponse = new APIResponse();
        List<Carpool> carpools = carpoolRepo.findBySite(site);
        apiResponse.setObject(carpools);
        apiResponse.setHttpCode(HttpStatus.OK);
        apiResponse.setMsg("查詢成功");
        return ResponseEntity.ok().body(apiResponse);

    }


    @PutMapping("/applyfordriver")
    public ResponseEntity<APIResponse> applyForDriver(@RequestBody OrderCarpoolDTO orderCarpoolDTO) {
        int userId = orderCarpoolDTO.getUserId();
        APIResponse apiResponse = new APIResponse();
        boolean isApplyOK = ps.applyForDriver(userId, "收到申請文件");
        if (isApplyOK) {
            apiResponse.setMsg("已收到【轉職司機】申請，待管理員審核");
            apiResponse.setHttpCode(HttpStatus.OK);
            return ResponseEntity.ok().body(apiResponse);
        }
        apiResponse.setMsg("申請失敗:【案件審核中或審核未通過】");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }


}
