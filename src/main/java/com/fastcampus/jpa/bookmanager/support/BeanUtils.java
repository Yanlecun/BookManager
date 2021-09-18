package com.fastcampus.jpa.bookmanager.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    // 리스너에게 빈을 주입하자
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    public static  <T> T getBean(Class<T> clazz) {
        // 해당 클래스에 맞는 빈을 리턴해줌
        return  applicationContext.getBean(clazz);
    }
}
