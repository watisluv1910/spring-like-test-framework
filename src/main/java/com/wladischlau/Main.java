package com.wladischlau;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vladislav Nasevich
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run(
                "com.wladischlau",
                new HashMap<>(Map.of(Policeman.class, DefaultPoliceman.class))
        );
        Disinfector disinfector = context.getObject(Disinfector.class);
        disinfector.start(new Room());
    }
}
