package com.cs213.rutgerscafelol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Class contains controls for the shopping cart menu
 * @author mss390 amp487 Mayank Singamreddy Aryak Pande
 *
 */
public class ShoppingCart extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener{

    private DecimalFormat format = new DecimalFormat("$#,##0.00");
    private EditText subtotal,total,tax,orderNum;
    private int positionMarked = -1;
    private RecyclerViewAdapter adapter;
    private RecyclerView orderList;
    private final int NEGATIVEONE = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        setTitle(R.string.order_details);
        subtotal = (EditText) findViewById(R.id.subtotal);
         tax = (EditText) findViewById(R.id.sales_tax);
         total = (EditText) findViewById(R.id.orderTotal);
         orderNum = (EditText) findViewById(R.id.orderNum);
        total.setEnabled(false);
        subtotal.setEnabled(false); //set all fields as non-editable
        tax.setEnabled(false);
        orderNum.setEnabled(false);
         orderList = findViewById(R.id.orderList);
        orderList.setLayoutManager(new LinearLayoutManager(this));
      updateRecyclerList(); //initialize the list
        subtotal.setText(format.format(References.customerOrder.orderSubTotal()));
        total.setText(format.format(References.customerOrder.orderTotal())); //calculate totals
        tax.setText(format.format(References.customerOrder.orderSubTotal()*(Order.SALES_TAX-1)));
        orderNum.setText(Integer.toString(References.customerOrder.getOrderNumber())); //show current order number
    }

    /**
     * updates the recycler view list
     */
    public void updateRecyclerList(){
        ArrayList<String> converted = new ArrayList<>();
        ArrayList<MenuItem> items = References.customerOrder.getItems();
        for(MenuItem i : items){ //add items
            converted.add(i.toString());
        }
        adapter = new RecyclerViewAdapter(this, converted);
        adapter.setClickListener(this);
        orderList.setAdapter(adapter); //set adapter
    }

    /**
     * called by place order button
     * @param v
     */
    public void placeOrder(View v){
        if(References.customerOrder.getItems().isEmpty()){
            Toast.makeText(this, R.string.order_empty, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        References.orders.add(References.customerOrder); //add this order to the store orders
        References.customerOrder = new Order(); //create a new order
        Toast.makeText(this, R.string.order_placed, Toast.LENGTH_SHORT).show();
        finish(); //go back to main menu
    }

    /**
     * called by remove button
     * @param v
     */
    public void removeItem(View v){

        ArrayList<MenuItem> items = References.customerOrder.getItems();
        if(positionMarked != NEGATIVEONE) { //if something is selected, remove it from the list, otherwise show a message and do nothing
            items.remove(positionMarked);
        }else{
            Toast.makeText(this, R.string.nothing_selected_cart, Toast.LENGTH_LONG).show();
            return;
        }
        References.customerOrder.setItems(items); //update the items in the order
        updateRecyclerList(); //update the recycler list
        subtotal.setText(format.format(References.customerOrder.orderSubTotal())); //update totals
        total.setText(format.format(References.customerOrder.orderTotal()));
        tax.setText(format.format(References.customerOrder.orderSubTotal()*(Order.SALES_TAX-1)));
        orderNum.setText(Integer.toString(References.customerOrder.getOrderNumber()));
        Toast.makeText(this, R.string.removed_done, Toast.LENGTH_SHORT).show();
        positionMarked = NEGATIVEONE;
    }

    //mark the item clicked for removal by the remove Item method
    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(this, adapter.getItem(position)+" is selected.", Toast.LENGTH_SHORT).show();
        positionMarked = position;
    }
}