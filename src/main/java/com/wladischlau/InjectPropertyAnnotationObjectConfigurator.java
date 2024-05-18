package com.wladischlau;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vladislav Nasevich
 */
public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private final Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        URL path = ClassLoader.getSystemClassLoader().getResource("application.properties");
        Stream<String> lines = new BufferedReader(new FileReader(Objects.requireNonNull(path).getFile())).lines();

        propertiesMap = lines
                .map(line -> line.trim().split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            var annotation = field.getAnnotation(InjectProperty.class);

            if (annotation != null) {
                String value = propertiesMap.get(
                        annotation.value().isBlank() ? field.getName() : annotation.value()
                );
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }
}
