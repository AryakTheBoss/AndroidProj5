package com.cs213.rutgerscafelol;

import java.util.ArrayList;


/**
 * Class represents the item type Coffee
 * @author mss390 amp487
 *
 */
public class Coffee extends MenuItem implements Customizable{

    private ArrayList<AddIns> addons;
    private Size size;
    private final float SHORT_PRICE = 1.99f;
    private final float TALL_PRICE = 2.49f;
    private final float GRANDE_PRICE = 2.99f;
    private final float VENTI_PRICE = 3.49f;
    private final float ADDON_PRICE = 0.2f;
    private final int NONE = 0;

    /**
     * Constructor to create Coffee
     * @param size size of the coffee
     * @param quantity amount of the coffees
     */
    public Coffee(Size size, int quantity){
        super(quantity);
        this.size=size;
        addons = new ArrayList<>();
    }

    /**
     * Method to add add-ins to an order
     * @param obj the add-in to add
     * @return if completion is successful
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof AddIns) {
            return addons.add((AddIns)obj);
        }else{
            return false;
        }
    }

    /**
     * Remove an add-in from an order
     * @param obj add-in to remove from order item
     * @return true if the item exists
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof AddIns) {
            return addons.remove(obj);
        }else{
            return false;
        }
    }

    /**
     * Calculate the price of an item
     * @return the float price of the item with its size and add-in prices
     */
    @Override
    public float itemPrice() {
        if(size == null){
            return NONE;
        }
        switch(size){

            case SHORT:
                return (SHORT_PRICE + calculateAddons())*super.getQuantity();
            case TALL:
                return (TALL_PRICE + calculateAddons())*super.getQuantity();
            case GRANDE:
                return (GRANDE_PRICE + calculateAddons())*super.getQuantity();
            case VENTI:
                return (VENTI_PRICE + calculateAddons())*super.getQuantity();
            default:
                return NONE;
        }

    }

    /**
     * create string to print to user for prices per item
     * @return string to print for menu
     */
    @Override
    public String toString() {
        return super.toString()+" "+size+" Coffee with: "+addons;
    }

    /**
     * Check for if two items are the same, to see if count will increase or new type will be added
     * @param o The item to be checked against
     * @return true if the type of item and add-ins are a match
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Coffee)) return false;
        Coffee other = (Coffee) o;
        return other.getAddons().equals(addons) && other.getSize() == size;
    }

    /**
     * get for add-ons
     * @return add-ons arraylist
     */
    public ArrayList<AddIns> getAddons(){
        return addons;
    }

    /**
     * getter for item size
     * @return size of coffee
     */
    public Size getSize(){
        return size;
    }

    /**
     * setter for coffee size
     * @param s the size to set to
     */
    public void setSize(Size s){
        size = s;
    }

    /**
     * calculate the price of total add-ons
     * @return the float price of total add-ons
     */
    public float calculateAddons(){

        if(addons.isEmpty()){
            return NONE;
        }
        return addons.size()*ADDON_PRICE;

    }


}
