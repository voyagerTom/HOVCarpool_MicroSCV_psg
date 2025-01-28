package com.example.HOVCarpool.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTool {


    public static String getTimeNow(){
        // 取得當前時間
        LocalDateTime now = LocalDateTime.now();

        // 定義格式化樣式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

      return formattedDateTime;
    }
}
