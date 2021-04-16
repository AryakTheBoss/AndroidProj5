package com.cs213.rutgerscafelol;

import java.util.Objects;
/**
 * Class to represent the items within the Menu
 * @author mss390 amp487
 *
 */
public class MenuItem {

public int quantity;
private final float DEFAULT = 0.0f;

    /**
     * Constructor for menu items
     * @param quantity the amount of the item to create
     */
    public MenuItem(int quantity){
    this.quantity = quantity;
}

    /**
     * getter method for the quantity of the items
     * @return the quantity of the item
     */
    public int getQuantity(){
    return quantity;
    }

    /**
     * setter method for the quantity of the item
     * @param q the quantity to set to
     */
    public void setQuantity(int q){
        quantity = q;
    }

    /**
     * convert the item and its quantity to display to user
     * @return the string to display
     */
    @Override
    public String toString() {
        return "("+quantity+")";
    }

    /**
     * check if the menu item is equal to another
     * @param o the item to check against
     * @return true if the items being compared are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return quantity == menuItem.quantity;
    }



    public float itemPrice(){
        return DEFAULT;
    }
}
