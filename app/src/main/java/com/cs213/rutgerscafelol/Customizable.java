package com.cs213.rutgerscafelol;

/**
 * Describe an object as customizable when implemented
 * @author mss390 amp487
 *
 */
public interface Customizable {
    /**
     * add a customization
     * @param obj the object
     * @return true if it was added false if not
     */
    boolean add(Object obj);

    /**
     * remove a customization
     * @param obj the object
     * @return true if done
     */
    boolean remove(Object obj);
}
