package uk.dangrew.kode.observable;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

/**
 * TAKEN FROM JAVA as they have decided to hide coms classes.
 */
public class SimpleChange<K, V> extends MapChangeListener.Change<K, V> {

    private K key;
    private V old;
    private V added;
    private boolean removeOp;
    private boolean addOp;

    public SimpleChange(ObservableMap<K, V> set) {
        super(set);
    }

    public SimpleChange(ObservableMap<K, V> set, MapChangeListener.Change<? extends K, ? extends V> source) {
        super(set);
        key = source.getKey();
        old = source.getValueRemoved();
        added = source.getValueAdded();
        addOp = source.wasAdded();
        removeOp = source.wasRemoved();
    }

    public SimpleChange<K, V> setRemoved(K key, V old) {
        this.key = key;
        this.old = old;
        this.added = null;
        addOp = false;
        removeOp = true;
        return this;
    }

    public SimpleChange<K, V> setAdded(K key, V added) {
        this.key = key;
        this.old = null;
        this.added = added;
        addOp = true;
        removeOp = false;
        return this;
    }

    public SimpleChange<K, V> setPut(K key, V old, V added) {
        this.key = key;
        this.old = old;
        this.added = added;
        addOp = true;
        removeOp = true;
        return this;
    }

    @Override
    public boolean wasAdded() {
        return addOp;
    }

    @Override
    public boolean wasRemoved() {
        return removeOp;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValueAdded() {
        return added;
    }

    @Override
    public V getValueRemoved() {
        return old;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (addOp) {
            if (removeOp) {
                builder.append("replaced ").append(old).append(" by ").append(added);
            } else {
                builder.append("added ").append(added);
            }
        } else {
            builder.append("removed ").append(old);
        }
        builder.append(" at key ").append(key);
        return builder.toString();
    }
}
