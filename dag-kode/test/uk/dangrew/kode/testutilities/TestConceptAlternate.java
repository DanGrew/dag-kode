package uk.dangrew.kode.testutilities;

import uk.dangrew.kode.concept.Properties;

public class TestConceptAlternate extends TestConcept {

    public TestConceptAlternate(String name){
        this(new Properties(name));
    }

    public TestConceptAlternate(String name, String id){
        this(new Properties(name, id));
    }

    public TestConceptAlternate(Properties properties){
        super(properties);
    }
}
