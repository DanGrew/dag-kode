package uk.dangrew.kode.testutilities;

import uk.dangrew.kode.concept.ConceptStore;
import uk.dangrew.kode.storage.structure.MappedObservableStoreManagerImpl;

public class TestConceptAlternateStore extends MappedObservableStoreManagerImpl<String, TestConceptAlternate> implements ConceptStore<TestConceptAlternate> {

    public TestConceptAlternateStore() {
        super(c -> c.properties().id());
    }//End Constructor

    @Override public TestConceptAlternate createConcept(String name) {
        TestConceptAlternate concept = new TestConceptAlternate(name);
        store(concept);
        return concept;
    }//End Method

    @Override public TestConceptAlternate createConcept(String id, String name) {
        TestConceptAlternate concept = new TestConceptAlternate(id, name);
        store(concept);
        return concept;
    }//End Method

    @Override public void store(TestConceptAlternate concept) {
        super.store(concept);
    }//End Method

    @Override public void removeConcept(TestConceptAlternate concept) {
        remove(concept.properties().id());
    }//End Method

}//End Class
