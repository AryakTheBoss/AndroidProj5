package com.cs213.rutgerscafelol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class contains controls for the storing all orders menu
 * @author mss390 amp487 Mayank Singamreddy Aryak Pande
 *
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private DecimalFormat format = new DecimalFormat("$#,##0.00");
    private EditText total;
    private Spinner orderNum;
    private RecyclerViewAdapter adapter;
    private RecyclerView orderList;
    private final int FIRST = 0;
    private final int OUTOFBOUNDS = -1;

    private int positionSelected = OUTOFBOUNDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        setTitle(R.string.store_orders);
        total = (EditText) findViewById(R.id.totalBoxS);
        orderNum = (Spinner) findViewById(R.id.orders);
        total.setEnabled(false);
        orderList = findViewById(R.id.currentOrderList);
        orderList.setLayoutManager(new LinearLayoutManager(this));

       updateRecyclerView(FIRST); //initialize the list

        total.setText(format.format(References.orders.getOrders().get(FIRST).orderTotal())); //initalize the total
       updateSpinnerBox(); //initialize the list of orders

        orderNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * when an item is selected, updates recyclerview along with total
             * @param parentView
             * @param selectedItemView
             * @param position the position of the item
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

               updateRecyclerView(position); //update list
                total.setText(format.format(References.orders.getOrders().get(position).orderTotal())); //update total

                positionSelected = position; //update the position selected in case of removal
            }

            /**
             * if nothing is selected, set price to zero, and reset position selected to minus one
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                total.setText(R.string._0_00); //set to default string
                positionSelected = OUTOFBOUNDS;
            }

        });
    }

    /**
     * updates the dropdown box
     */
    public void updateSpinnerBox(){
        List<String> orderNumss =  new ArrayList<String>();
        for(Order o : References.orders.getOrders()){ //add the items
            orderNumss.add(Integer.toString(o.getOrderNumber()));
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, orderNumss);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNum.setAdapter(adapter2); //set the adapter
    }

    /**
     * updates the recycler view
     * @param orderIndex
     */
    public void updateRecyclerView(int orderIndex){
        ArrayList<String> converted = new ArrayList<>();
        ArrayList<MenuItem> items = References.orders.getOrders().get(orderIndex).getItems();
        for(MenuItem i : items){ //update list
            converted.add(i.toString());
        }
        adapter = new RecyclerViewAdapter(this, converted);
        orderList.setAdapter(adapter); //set adapter
    }

    /**
     *  if order is canceled, remove from list of orders, and update recylerview
     * @param v
     */
    public void cancelOrder(View v){

        if(positionSelected == OUTOFBOUNDS){
            Toast.makeText(this, R.string.no_order_selected, Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Order> storeOrders = References.orders.getOrders();
        storeOrders.remove(positionSelected);
        References.orders.setOrders(storeOrders);
        //repopulate
        if(References.orders.getOrders().isEmpty()){ //if there are no more orders, return to main menu
            Toast.makeText(this, R.string.no_more_orders, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        updateSpinnerBox();
        total.setText(format.format(References.orders.getOrders().get(FIRST).orderTotal())); //update total
        updateRecyclerView(FIRST); //set first position
        Toast.makeText(this, R.string.order_cancelled, Toast.LENGTH_SHORT).show();
        positionSelected = FIRST;

    }
}