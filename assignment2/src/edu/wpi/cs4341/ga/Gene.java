package edu.wpi.cs4341.ga;

/**
 * Provides a common interface to allow for genes to be expressed in a simplified manner.
 * @param <T> The Object that this Gene is wrapping.
 */
public class Gene<T>{
    private T value;

    public Gene(T value){
        this.value = value;
    }
    /**
     * Gets the value stored in this Gene
     * @param value the value to set.
     */
    void set(T value){
        this.value = value;
    }

    /**
     * @return the value stored in this Gene
     */
    public T get(){
        return value;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "value=" + value +
                '}';
    }
}
