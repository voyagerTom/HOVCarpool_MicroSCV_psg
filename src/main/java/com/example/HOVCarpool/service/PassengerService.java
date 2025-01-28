package com.example.HOVCarpool.service;

import com.example.HOVCarpool.dto.OrderCarpoolDTO;
import com.example.HOVCarpool.entity.Carpool;
import com.example.HOVCarpool.entity.CarpoolMap;
import com.example.HOVCarpool.responsecustom.APIResponse;

import java.util.List;

public interface PassengerService {

    boolean ordercarpool(OrderCarpoolDTO carPoolID);

    boolean cancelOrderCarpoolById(OrderCarpoolDTO orderCarpoolDTO);
    List<Carpool> checkMyCarpool(int userId);

    List<CarpoolMap>  checkmyLastCarpool (int userId);

    boolean applyForDriver(int userId, String IDDco);


}
