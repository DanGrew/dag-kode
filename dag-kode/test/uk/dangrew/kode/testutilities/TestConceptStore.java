package uk.dangrew.kode.testutilities;

import uk.dangrew.kode.concept.ConceptStore;
import uk.dangrew.kode.storage.structure.MappedObservableStoreManagerImpl;

public class TestConceptStore extends MappedObservableStoreManagerImpl<String, TestConcept> implements ConceptStore<TestConcept> {

    public TestConceptStore() {
        super(c -> c.properties().id());
    }//End Constructor

    @Override public TestConcept createConcept(String name) {
        TestConcept concept = new TestConcept(name);
        store(concept);
        return concept;
    }//End Method

    @Override public TestConcept createConcept(String id, String name) {
        TestConcept concept = new TestConcept(id, name);
        store(concept);
        return concept;
    }//End Method

    @Override public void store(TestConcept concept) {
        super.store(concept);
    }//End Method

    @Override public void removeConcept(TestConcept concept) {
        remove(concept.properties().id());
    }//End Method

}//End Class
