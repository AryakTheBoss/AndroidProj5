package com.cs213.rutgerscafelol;
/**
 * Class to contain constants for Donut Flavors
 * @author mss390 amp487
 *
 */
public enum Flavor {
    CHOCOLATE_FROSTED,STRAWBERRY,GLAZED,BOSTON_CREAM,JELLY_FILLED,BLUEBERRY,POWDERED_SUGAR,PLAIN,MAPLE_SYRUP_FROSTED,DOUBLE_CHOCOLATE;

    /**
     * convert the donut flavor into a string
     * @return the string of donut flavor
     */
    @Override
    public String toString() {
        switch(this){
            case CHOCOLATE_FROSTED:
                return "Chocolate Frosted";
            case GLAZED:
                return "Glazed";
            case MAPLE_SYRUP_FROSTED:
                return "Maple Syrup Frosted";
            case BLUEBERRY:
                return "Blueberry";
            case STRAWBERRY:
                return "Strawberry";
            case PLAIN:
                return "Plain";
            case BOSTON_CREAM:
                return "Boston Cream";
            case JELLY_FILLED:
                return "Jelly";
            case POWDERED_SUGAR:
                return "Sugar";
            case DOUBLE_CHOCOLATE:
                return "Double Chocolate";
            default:
                return "invalid";
        }
    }
}
