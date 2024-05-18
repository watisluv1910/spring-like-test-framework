package com.wladischlau;

import java.util.Map;

/**
 * @author Vladislav Nasevich
 */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);

        // TODO: init all not lazy singletons

        context.setFactory(objectFactory);
        return context;
    }
}
