package com.wladischlau;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Vladislav Nasevich
 */
public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {

    @Override
    public Object replaceWithProxy(Object t, Class<?> implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {

            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(
                        implClass,
                        (InvocationHandler) (proxy, method, args) -> getInvocationHandlerLogic(method, args, implClass, t));
            }

            return Proxy.newProxyInstance(
                    implClass.getClassLoader(),
                    implClass.getInterfaces(),
                    (proxy, method, args) -> getInvocationHandlerLogic(method, args, implClass, t)
            );
        }

        return t;
    }

    private static Object getInvocationHandlerLogic(Method method,
                                                    Object[] args,
                                                    Class<?> implClass,
                                                    Object t
    ) throws IllegalAccessException, InvocationTargetException {
        System.out.println(
                "********* What are you doing, bastard? " +
                method.getName() + " METHOD OF CLASS " +
                implClass.getName() + " IS DEPRECATED!"
        );
        return method.invoke(t, args);
    }
}
