package com.cs213.rutgerscafelol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.orderCoff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderCoffee.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.orderDonuts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderDonuts.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.orderDeets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShoppingCart.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.storeOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoreOrdersActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}