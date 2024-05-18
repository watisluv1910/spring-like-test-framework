package com.wladischlau;

/**
 * The following environmental variable for jvm should be set in order
 * to use cglib proxies:
 *
 * <blockquote><pre>
 *     JDK_JAVA_OPTIONS: '--add-opens=java.base/java.lang=ALL-UNNAMED'
 * </pre></blockquote>
 *
 * @author Vladislav Nasevich
 */
public interface ProxyConfigurator {
    Object replaceWithProxy(Object t, Class<?> implClass);
}
