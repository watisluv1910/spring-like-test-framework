package com.wladischlau;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladislav Nasevich
 */
public class ObjectFactory {
    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();


    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        for (var aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        for (var aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    private static <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }

    private static <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);

        configure(t);

        invokeInit(implClass, t);

        t = wrapWithProxy(implClass, t);

        return t;
    }

    @SuppressWarnings("unchecked")
    private <T> T wrapWithProxy(Class<T> implClass, T t) {
        for (ProxyConfigurator c : proxyConfigurators) {
            t = (T) c.replaceWithProxy(t, implClass);
        }
        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(c -> c.configure(t, context));
    }
}
