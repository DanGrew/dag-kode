package uk.dangrew.kode.javafx.utility;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Utility for creating {@link Change}s for test purposes.
 */
public class ListChangeUtility {

    /**
     * Provides a {@link Change} representing an addition of the given elements.
     * @param added the {@link List} of added elements.
     * @param <TypeT> the type of element.
     * @return the {@link Change}.
     */
    public static <TypeT> Change<TypeT> simpleAddChange(List<TypeT> added){
        Change<TypeT> change = mock(Change.class);
        when(change.next())
                .thenReturn(true)
                .thenReturn(false);
        when(change.wasAdded()).thenReturn(true);
        when(change.getAddedSubList()).thenReturn(added);
        return change;
    }

    /**
     * Provides a {@link Change} representing a removal of the given element.
     * @param removed the removed element.
     * @param <TypeT> the type of element.
     * @return the {@link Change}.
     */
    public static <TypeT> Change<TypeT> simpleRemovedChange(TypeT removed){
        return simpleRemovedChange(Collections.singletonList(removed));
    }

    /**
     * Provides a {@link Change} representing a removal of the given elements.
     * @param removed the {@link List} of removed elements.
     * @param <TypeT> the type of element.
     * @return the {@link Change}.
     */
    public static <TypeT> Change<TypeT> simpleRemovedChange(List<TypeT> removed){
        Change<TypeT> change = mock(Change.class);
        when(change.next())
                .thenReturn(true)
                .thenReturn(false);
        when(change.wasRemoved()).thenReturn(true);
        when(change.getRemoved()).thenReturn(removed);
        return change;
    }

    /**
     * Provides a {@link Change} representing an addition and removal of the given elements.
     * @param added the {@link List} of added elements.
     * @param removed the {@link List} of removed elements.
     * @param <TypeT> the type of element.
     * @return the {@link Change}.
     */
    public static <TypeT> Change<TypeT> simpleAddedAndRemovedChange(List<TypeT> added, List<TypeT> removed){
        Change<TypeT> change = mock(Change.class);
        when(change.next())
                .thenReturn(true)
                .thenReturn(false);

        when(change.wasAdded()).thenReturn(true);
        when(change.getAddedSubList()).thenReturn(added);

        when(change.wasRemoved()).thenReturn(true);
        when(change.getRemoved()).thenReturn(removed);

        return change;
    }
}
