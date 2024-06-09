package com.grupo.engine.pool;

/**
 * The Poolable interface represents objects that can be reset and reused in object pools.
 */
public interface Poolable {
    /**
     * Resets the state of the object, preparing it for reuse.
     */
    void reset();
}
