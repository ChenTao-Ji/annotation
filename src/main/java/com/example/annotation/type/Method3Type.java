package com.example.annotation.type;

import org.springframework.stereotype.Service;

/**
 * @author chentao.ji
 */
@Service
public class Method3Type implements Type {

    @Override
    public void invoked(String arge) {
        System.out.println("method3 type 校验执行");
    }

}
