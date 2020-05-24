package uk.dangrew.kode.javafx.color;

import javafx.scene.paint.Color;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class JavaFxNamedColours {

    private static final Map<Color, String> colours = new LinkedHashMap<>();

    public JavaFxNamedColours() {
        if (colours.isEmpty()) {
            initialiseColours();
        }
    }

    private void initialiseColours() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("javafx.scene.paint.Color");
        } catch (ClassNotFoundException e) {
            provideDefaultColours();
            return;
        }
        if (clazz == null) {
            return;

        }
        for (Field field : clazz.getFields()) {
            Object obj = null;
            try {
                obj = field.get(null);
            } catch (IllegalAccessException e) {
                provideDefaultColours();
                return;
            }
            if (obj instanceof Color) {
                colours.put((Color) obj, field.getName());
            }
        }
    }

    private void provideDefaultColours() {
        colours.clear();
        colours.put(Color.RED, "Red");
        colours.put(Color.BLUE, "Blue");
        colours.put(Color.GREEN, "Green");
    }

    public Collection<Color> getColours() {
        return Collections.unmodifiableCollection(colours.keySet());
    }

    public String getNameFor(Color colour) {
        return colours.get(colour);
    }
}
