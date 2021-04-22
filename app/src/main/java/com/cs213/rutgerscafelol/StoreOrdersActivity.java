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
    private final int NEGATIVEONE = -1;
    private final int ZERO = 0;
    private int positionSelected = -1;

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
        //References.orders.getOrders().get(0).getItems()
        ArrayList<String> converted = new ArrayList<>();
        ArrayList<MenuItem> items = References.orders.getOrders().get(FIRST).getItems();
        for(MenuItem i : items){
            converted.add(i.toString());
        }
        adapter = new RecyclerViewAdapter(this, converted);
        orderList.setAdapter(adapter);
        total.setText(format.format(References.orders.getOrders().get(FIRST).orderTotal()));
        List<String> orderNumss =  new ArrayList<String>();
        for(Order o : References.orders.getOrders()){
            orderNumss.add(Integer.toString(o.getOrderNumber()));
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, orderNumss);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNum.setAdapter(adapter2);

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

                ArrayList<String> converted = new ArrayList<>();
                ArrayList<MenuItem> items = References.orders.getOrders().get(position).getItems();
                for(MenuItem i : items){
                    converted.add(i.toString());
                }
                adapter = new RecyclerViewAdapter(getApplicationContext(), converted);
                orderList.setAdapter(adapter);
                total.setText(format.format(References.orders.getOrders().get(position).orderTotal()));

                positionSelected = position;
            }

            /**
             * if nothing is selected, set price to zero, and reset position selected to minus one
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                total.setText(R.string._0_00);
                positionSelected = NEGATIVEONE;
            }

        });
    }

    /**
     *  if order is canceled, remove from list of orders, and update recylerview
     * @param v
     */
    public void cancelOrder(View v){

        if(positionSelected == NEGATIVEONE){
            Toast.makeText(this, "No order is selected.", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Order> storeOrders = References.orders.getOrders();
        storeOrders.remove(positionSelected);
        References.orders.setOrders(storeOrders);
        //repopulate
        if(References.orders.getOrders().isEmpty()){
            Toast.makeText(this, "There are no more orders!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        List<String> orderNumss =  new ArrayList<String>();
        for(Order o : References.orders.getOrders()){
            orderNumss.add(Integer.toString(o.getOrderNumber()));
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, orderNumss);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNum.setAdapter(adapter2);
        total.setText(format.format(References.orders.getOrders().get(FIRST).orderTotal()));
        ArrayList<String> converted = new ArrayList<>();
        ArrayList<MenuItem> items = References.orders.getOrders().get(FIRST).getItems();
        for(MenuItem i : items){
            converted.add(i.toString());
        }
        adapter = new RecyclerViewAdapter(this, converted);
        orderList.setAdapter(adapter);
        Toast.makeText(this, "Order was cancelled.", Toast.LENGTH_SHORT).show();
        positionSelected = ZERO;

    }
}