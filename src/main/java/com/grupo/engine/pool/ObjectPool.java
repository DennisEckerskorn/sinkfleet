package com.denniseckerskorn.engine.pool;

import java.util.Stack;

/**
 * The ObjectPool class is a generic class that manages a pool of reusable objects.
 * It allows the creation, borrowing, and returning of objects to and from the pool.
 *
 * @param <T> the type of objects managed by this pool, which must implement the Poolable interface
 */
public class ObjectPool<T extends Poolable> {
    private Stack<T> pool;
    private Class<T> clazz;

    /**
     * Constructor which creates the initial pool of reusable objects.
     *
     * @param clazz           the class of objects to create in the pool.
     * @param initialCapacity the initial capacity of the pool.
     */
    public ObjectPool(Class<T> clazz, int initialCapacity) {
        this.clazz = clazz;
        pool = new Stack<>();
        for (int i = 0; i < initialCapacity; i++) {
            pool.push(createNew());
        }
    }

    /**
     * Creates a new instance of the class type specified in the constructor.
     *
     * @return a new instance of the specified class.
     * @throws RuntimeException if there is an issue creating a new instance.
     */
    private T createNew() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a new instance of " + clazz, e);
        }
    }

    /**
     * Borrows an object from the pool. If the pool is empty, a new object is created.
     *
     * @return an instance of T from the pool.
     */

    public T borrowObject() {
        if (pool.isEmpty()) {
            return createNew();
        } else {
            return pool.pop();
        }
    }

    /**
     * Returns an object to the pool after resetting its state.
     *
     * @param object the object to be returned to the pool.
     */
    public void returnObject(T object) {
        object.reset();
        pool.push(object);
    }
}
