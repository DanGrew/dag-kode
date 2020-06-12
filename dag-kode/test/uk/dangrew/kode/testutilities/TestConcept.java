package uk.dangrew.kode.testutilities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

public class TestConcept implements Concept {

    public static final String COPY_SUFFIX = "-COPY";

    private final Properties properties;
    private final ObjectProperty<Object> exampleProperty;

    public TestConcept(String name){
        this(new Properties(name));
    }

    public TestConcept(String name, String id){
        this(new Properties(name, id));
    }

    public TestConcept(Properties properties){
        this.properties = properties;
        this.exampleProperty = new SimpleObjectProperty<>();
    }

    @Override public Properties properties() {
        return properties;
    }

    public ObjectProperty<Object> exampleProperty() {
        return exampleProperty;
    }

    @Override public Concept duplicate() {
        TestConcept copy = new TestConcept(properties.nameProperty().get() + COPY_SUFFIX);
        copy.exampleProperty.set(exampleProperty.get());
        return copy;
    }
}
