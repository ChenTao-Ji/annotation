package com.example.annotation.interceptor;

import com.example.annotation.demo.Jiecha;
import com.example.annotation.demo.Test;
import com.example.annotation.type.Type;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author chentao
 */
@Component
public class TestInterceptor implements HandlerInterceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            Class clazz = handler.getClass();
            if (handler instanceof HandlerMethod) {
                //在HandlerMethod中获取保存请求Controller的字段
                Field beanType = clazz.getDeclaredField("beanType");
                beanType.setAccessible(true);
                //在HandlerMethod中获取保存请求Controller中method的字段
                Field method = clazz.getDeclaredField("method");
                method.setAccessible(true);

                /**
                 * 获取Controller类路径
                 * class com.example.annotation.test.TestController
                 */
                String controllerName = beanType.get(handler).toString();

                /**
                 * 去除类路径第一个class及空格
                 * com.example.annotation.test.TestController
                 */
                String beanTypeClassName = controllerName.substring(controllerName.indexOf(" ") + 1);

                //获取controller类类型
                Class beanTypeClass = Class.forName(beanTypeClassName);

                /**
                 * 拼接Controller全路径 + .
                 * com.example.annotation.test.TestController.
                 */
                String value = beanTypeClass.getName() + ".";


                /**
                 * 获取method路径
                 * public void com.example.annotation.test.TestController.method1(java.lang.String)
                 */
                String methodAllName = method.get(handler).toString();

                //public void com.example.annotation.test.TestController.method1
                String methodName = methodAllName.substring(0, methodAllName.indexOf("("));

                //method1
                methodName = methodName.substring(methodName.lastIndexOf(".") + 1);


                String methodParam = methodAllName.substring(methodAllName.indexOf("(") + 1, methodAllName.lastIndexOf(")"));

                String[] methodParamTypes = methodParam.split(",");

                Class[] paramTypes = null;

                if (null != methodParamTypes && methodParamTypes.length != 0) {
                    paramTypes = new Class[methodParamTypes.length];
                    for (int i = 0; i < methodParamTypes.length; i++) {
                        String paramType = methodParamTypes[i];
                        Class param = Class.forName(paramType);
                        paramTypes[i] = param;
                    }
                }

                //开始正常业务（controller及method均已获取）
                Method trueMethod = beanTypeClass.getDeclaredMethod(methodName, paramTypes);

                boolean b = trueMethod.isAnnotationPresent(Jiecha.class);

                if (b) {
                    //获取注解对象
                    Jiecha jiecha = trueMethod.getAnnotation(Jiecha.class);
                    System.out.println("userId:" + jiecha.userId());
                    System.out.println("type:" + jiecha.type());
                    System.out.println("arge:" + jiecha.arge());
                    if (!StringUtils.isEmpty(jiecha.type())) {

                        Object object = applicationContext.getBean(jiecha.type());

                        if (object instanceof Type) {
                            Type type = (Type) object;
                            type.invoked(jiecha.arge());
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
