package com.book.test;

import com.book.bean.User;
import com.book.utils.WebUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WebUtilsTest {

    @Test
    public void copyParamToBean() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username","admin");
        map.put("password","admin");
        map.put("email","admin");
        User user = WebUtils.copyParamToBean(map, User.class);
        System.out.println("user = " + user);
    }
}