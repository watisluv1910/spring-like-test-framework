package com.wladischlau;

import org.reflections.Reflections;

/**
 * @author Vladislav Nasevich
 */
public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();
}
