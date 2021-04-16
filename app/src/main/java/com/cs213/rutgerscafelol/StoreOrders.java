package com.cs213.rutgerscafelol;

import java.util.ArrayList;
/**
 * Class to hold the orders of all customers
 * @author mss390 amp487
 *
 */
public class StoreOrders implements Customizable{

    private ArrayList<Order> orders;

    /**
     * constructs a new store order
     */
    public StoreOrders(){
        orders = new ArrayList<>();
    }

    /**
     * adds an order to the list
     * @param obj the object
     * @return true if added false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            return orders.add((Order)obj);
        }else{
            return false;
        }
    }

    /**
     * removes and order from teh list
     * @param obj the object
     * @return true if added false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            return orders.remove(obj);
        }else{
            return false;
        }
    }

    /**
     * gets the orders
     * @return the order list
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }

    /**
     * sets the order list
     * @param oo the list to set it to
     */
    public void setOrders(ArrayList<Order> oo){
        orders = oo;
    }
}
