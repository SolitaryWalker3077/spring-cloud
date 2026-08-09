package com.common.demo.utils;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class JWTUtilsTest {

    @Test
    void genJwt() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 1);
        claim.put("name", "zhangsan");
        System.out.println(JWTUtils.genJwt(claim));
    }
}