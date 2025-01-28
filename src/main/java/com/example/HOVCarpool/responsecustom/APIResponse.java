package com.example.HOVCarpool.responsecustom;

import com.example.HOVCarpool.entity.Carpool;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
//<!--	createBy Chen Ting Yu 2024-->
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {

    Object object;
    List<Carpool> carpoolList;
    HttpStatus httpCode;
    String Msg;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }


    public List<Carpool> getCarpoolList() {
        return carpoolList;
    }

    public void setCarpoolList(List<Carpool> carpoolList) {
        this.carpoolList = carpoolList;
    }
}
