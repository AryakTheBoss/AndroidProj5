package com.cs213.rutgerscafelol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Class contains controls for the Main menu
 * @author mss390 amp487 Mayank Singamreddy Aryak Pande
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.mm);
        findViewById(R.id.orderCoff).setOnClickListener(new View.OnClickListener() {
            /**
             * open the order coffee activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderCoffee.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.orderDonuts).setOnClickListener(new View.OnClickListener() {
            /**
             * open the order donuts activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderDonuts.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.orderDeets).setOnClickListener(new View.OnClickListener() {
            /**
             * open the shopping cart activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShoppingCart.class);
                if(References.customerOrder.getItems().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Your shopping cart is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
            }
        });

        findViewById(R.id.storeOrders).setOnClickListener(new View.OnClickListener() {
            /**
             * open the store orders activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoreOrdersActivity.class);
                if(References.orders.getOrders().isEmpty()){
                    Toast.makeText(getApplicationContext(), "There are currently no Orders!", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
            }
        });

    }






}