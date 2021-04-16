package com.cs213.rutgerscafelol;
/**
 * Class to contain constants for sizes of Coffees
 * @author mss390 amp487
 *
 */
public enum Size {

    SHORT,TALL,GRANDE,VENTI;

    /**
     * method to convert size of a coffee to a string that can be displayed
     * @return the string with coffee size details
     */
    @Override
    public String toString() {
        switch(this){
            case TALL:
                return "Tall";
            case SHORT:
                return "Short";
            case GRANDE:
                return "Grande";
            case VENTI:
                return "Venti";
            default:
                return null;
        }

    }
}
