package com.cs213.rutgerscafelol;

/**
 * Class to describe the item type Donut
 * @author mss390 amp487
 *
 */
public class Donut extends MenuItem{


    private Flavor flavor;
    private final float PRICE = 1.39f;
    private final int ONE = 1;



    /**
     * constructor for a donut
     *
     * @param flavor nine flavors of donuts exist
     * @param quantity the amount of donuts being ordered at a time
     */
    public Donut(Flavor flavor, int quantity){
        super(quantity);

        this.flavor = flavor;
    }

    /**
     * getter method for donut flavor
     * @return the donut flavor
     */
    public Flavor getFlavor(){
        return flavor;
    }


    /**
     * setter for donut flavor
     * @param flavor the donut flavor
     */
    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    /**
     * convert donut details into a string
     * @return the string of donut details
     */
    @Override
    public String toString() {
        return super.toString()+" "+flavor+" Donut"+(super.getQuantity()>ONE ? "s":"");
    }

    /**
     * check if the donut type is the same, to avoid duplicates
     * @param o the donut being checked
     * @return true if the donut details are the same
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Donut)) return false;
        Donut other = (Donut) o;
        return other.getFlavor() == flavor;
    }

    /**
     * calculate the price of the donut with type and flavor and quantity included
     * @return the float of the item price in total
     */
    @Override
    public float itemPrice() {
        return PRICE*super.getQuantity();
    }
}
