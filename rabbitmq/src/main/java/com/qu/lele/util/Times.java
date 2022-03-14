package com.qu.lele.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 屈光乐
 * @create: 2022-03-13 22-46
 */
public class Times {
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
