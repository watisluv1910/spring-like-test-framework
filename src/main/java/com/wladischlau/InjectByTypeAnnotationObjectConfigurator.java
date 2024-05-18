package com.wladischlau;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * @author Vladislav Nasevich
 */
public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object o = context.getObject(field.getType());
                field.set(t, o);
            }
        }
    }
}
