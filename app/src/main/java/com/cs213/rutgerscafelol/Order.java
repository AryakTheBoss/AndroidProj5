package com.cs213.rutgerscafelol;

import java.util.ArrayList;
/**
 * Class to hold customer order
 * @author mss390 amp487
 *
 */
public class Order implements Customizable{

    private int orderNumber;
    private static int totalOrders = 0; //used to keep track of how many order instances have been created
    private ArrayList<MenuItem> items;
    public static final float SALES_TAX = 1.06625f;
    private final float DEFAULT = 0.0f;

    /**
     * constructor for creating a new customer order
     */
    public Order(){
        items = new ArrayList<>();
        totalOrders++;
        orderNumber = totalOrders;
    }

    /**
     * add an item to the total order
     * @param obj the menu item to be added
     * @return true if the object was added successfully
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem){
            for(MenuItem i : items){
                if(i.equals(obj)){ //if the item being added already exists, just merge their quantities.
                    i.setQuantity(i.getQuantity()+((MenuItem) obj).getQuantity());
                    return true;
                }
            }
            return items.add((MenuItem)obj);
        }else{
            return false;
        }
    }

    /**
     * remove a menu item from the order
     * @param obj the item to remove
     * @return true if the item exists in the order
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem){
            return items.remove(obj);
        }else{
            return false;
        }
    }

    /**
     * getter for the items in a list
     * @return the array-list of menu item
     */
    public ArrayList<MenuItem> getItems(){
        return items;
    }

    /**
     * setter method for the items in the list
     * @param mi the array-list of items to add
     */
    public void setItems(ArrayList<MenuItem> mi){
        items = mi;
    }

    /**
     * method to find subtotal (the price of items before tax)
     * @return the float subtotal value
     */
    public float orderSubTotal(){
        float price = DEFAULT;
        for(MenuItem i : items){
            price += i.itemPrice();
        }
        return price;
    }

    /**
     * calculate the total order price by multiplying by sales tax constant
     * @return the float total order price
     */
    public float orderTotal(){
        return orderSubTotal()*SALES_TAX;
    }

    /**
     * getter method for the order of the number from within the total list
     * @return the order number
     */
    public int getOrderNumber(){
        return orderNumber;
    }

    /**
     * converts this order into a string. Used for file export
     * @return the string representation of an order
     */
    @Override
    public String toString(){
        String result = "----- Order #"+orderNumber+" -----";
        for(MenuItem mi : items){
            result += "\n"+mi.toString();
        }
        return result;

    }

}
