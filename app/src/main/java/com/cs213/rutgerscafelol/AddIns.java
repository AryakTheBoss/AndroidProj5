package com.cs213.rutgerscafelol;

/**
 * Class contains constants for add-in items in Coffees
 * @author mss390 amp487
 *
 */
public enum AddIns {
CREAM,SYRUP,MILK,CARAMEL,WHIPPED_CREAM;

    /**
     *Converts add-in items into string values to be displayed in guis
     *
     */
    @Override
    public String toString() {
        switch(this){
            case MILK:
                return "Milk";
            case CREAM:
                return "Cream";
            case SYRUP:
                return "Syrup";
            case CARAMEL:
                return "Caramel";
            case WHIPPED_CREAM:
                return "Whipped Cream";
            default:
                return "null";
        }
    }
}
