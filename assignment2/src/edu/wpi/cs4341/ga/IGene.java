package edu.wpi.cs4341.ga;

/**
 * Provides a common interface to allow for genes to be expressed in a simplified manner.
 * @param <T> The Object that this IGene is wrapping.
 */
public interface IGene<T>{
    /**
     * Gets the value stored in this IGene
     * @param value the value to set.
     */
    void set(T value);

    /**
     * @return the value stored in this IGene
     */
    T get();
}
