package com.example.annotation.demo;

public class Test {

    /**
     * 无单独校验
     */
    @Jiecha(userId = 1)
    public void method1(String userId) {
        System.out.println("method1" + "    userId:" + userId);
    }

    /**
     * 校验类型2
     * @param arge
     */
    @Jiecha(userId = 2, type = "method2Type")
    public void method2(String userId, String arge) {
        System.out.println("method2" + "    userId:" + userId + "   arge:" + arge);
    }

    /**
     * 校验类型3
     * @param arge
     */
    @Jiecha(userId = 3, type = "method3Type")
    public void method3(String userId, String arge) {
        System.out.println("method3" + "    userId:" + userId + "   arge:" + arge);
    }

}
