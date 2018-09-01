package com.example.annotation.test;

import com.example.annotation.demo.Jiecha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chentao.ji
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    /**
     * 无单独校验
     */
    @Jiecha(userId = 1)
    @RequestMapping(value = "/method1", method = RequestMethod.GET)
    @ResponseBody
    public String method1(String userId) {
        System.out.println("method1" + "    userId:" + userId);
        return "SUCCESS";
    }

    public String method() {
        return "method";
    }

    /**
     * 校验类型2
     * @param arge
     */
    @Jiecha(userId = 2, type = "method2Type")
    @RequestMapping(value = "/method2", method = RequestMethod.GET)
    @ResponseBody
    public String method2(String userId, String arge) {
        System.out.println("method2" + "    userId:" + userId + "   arge:" + arge);
        return "SUCCESS";
    }

    /**
     * 校验类型3
     * @param arge
     */
    @Jiecha(userId = 3, type = "method3Type")
    @RequestMapping(value = "/method3", method = RequestMethod.GET)
    @ResponseBody
    public String method3(String userId, String arge) {
        System.out.println("method3" + "    userId:" + userId + "   arge:" + arge);
        return "SUCCESS";
    }


}
