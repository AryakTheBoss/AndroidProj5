package com.cs213.rutgerscafelol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderCoffee extends AppCompatActivity {

    private Spinner size;
    private Coffee currentCoffee;
    private DecimalFormat format = new DecimalFormat("$#,##0.00");
    private EditText qty;
    private CheckBox cream,milk,syrup,caramel,whpcrm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);
        size = (Spinner) findViewById(R.id.size);
        currentCoffee = new Coffee(Size.SHORT,1); //default values

        EditText total = (EditText) findViewById(R.id.total);
        total.setEnabled(false);
        qty = (EditText) findViewById(R.id.quantity);
        qty.setText("1");
        qty.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String num = s.toString();
                try {
                    currentCoffee.setQuantity(Integer.parseInt(num));
                    total.setText(format.format(currentCoffee.itemPrice()));
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please Enter a Number.",Toast.LENGTH_SHORT).show();

                    currentCoffee.setQuantity(0);
                }
            }
        });
        total.setText(format.format(currentCoffee.itemPrice()));

        cream = (CheckBox) findViewById(R.id.creamCB);
         milk = (CheckBox) findViewById(R.id.milkCB);
         syrup = (CheckBox) findViewById(R.id.syrupCB);
         caramel = (CheckBox) findViewById(R.id.caramelCB);
        whpcrm = (CheckBox) findViewById(R.id.whpcrmCB);

        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch(position){
                    case 0:
                        currentCoffee.setSize(Size.SHORT);
                        break;
                    case 1:
                        currentCoffee.setSize(Size.TALL);
                        break;
                    case 2:
                        currentCoffee.setSize(Size.GRANDE);
                        break;
                    case 3:
                        currentCoffee.setSize(Size.VENTI);
                        break;

                    default:


                }
                total.setText(format.format(currentCoffee.itemPrice()));
                cream.setClickable(true);
                milk.setClickable(true);
                syrup.setClickable(true);
                caramel.setClickable(true);
                whpcrm.setClickable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                total.setText(R.string._0_00);
                cream.setClickable(false);
                milk.setClickable(false);
                syrup.setClickable(false);
                caramel.setClickable(false);
                whpcrm.setClickable(false);
            }

        });




        cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                   if(isChecked) { //if the new value is TRUE
                                                       currentCoffee.add(AddIns.CREAM);
                                                   }else{ //if the new value is FALSE
                                                       currentCoffee.remove(AddIns.CREAM);
                                                   }
                                                   //TODO update total box
                                                   total.setText(format.format(currentCoffee.itemPrice()));
                                               }
                                           }
        );
        milk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                 if(isChecked) { //if the new value is TRUE
                                                     currentCoffee.add(AddIns.MILK);
                                                 }else{ //if the new value is FALSE
                                                     currentCoffee.remove(AddIns.MILK);
                                                 }
                                                 //TODO update total box
                                                 total.setText(format.format(currentCoffee.itemPrice()));
                                             }
                                         }
        );
        syrup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                 if(isChecked) { //if the new value is TRUE
                                                     currentCoffee.add(AddIns.SYRUP);
                                                 }else{ //if the new value is FALSE
                                                     currentCoffee.remove(AddIns.SYRUP);
                                                 }
                                                 //TODO update total box
                                                 total.setText(format.format(currentCoffee.itemPrice()));
                                             }
                                         }
        );
        caramel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                 if(isChecked) { //if the new value is TRUE
                                                     currentCoffee.add(AddIns.CARAMEL);
                                                 }else{ //if the new value is FALSE
                                                     currentCoffee.remove(AddIns.CARAMEL);
                                                 }
                                                 //TODO update total box
                                                 total.setText(format.format(currentCoffee.itemPrice()));
                                             }
                                         }
        );
        whpcrm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                 if(isChecked) { //if the new value is TRUE
                                                     currentCoffee.add(AddIns.WHIPPED_CREAM);
                                                 }else{ //if the new value is FALSE
                                                     currentCoffee.remove(AddIns.WHIPPED_CREAM);
                                                 }
                                                 //TODO update total box
                                                 total.setText(format.format(currentCoffee.itemPrice()));
                                             }
                                         }
        );



    }


    /**
     * called when add to order button is tapped
     * @param v
     */
    public void addOrder(View v){
        Toast.makeText(getApplicationContext(),currentCoffee.toString()+" Added to Order.",Toast.LENGTH_LONG).show();
        References.customerOrder.add(currentCoffee);
        currentCoffee = new Coffee(Size.SHORT,1);
        qty.setText("1");
        size.setSelection(0);
        cream.setChecked(false);
        milk.setChecked(false);
        syrup.setChecked(false);
        caramel.setChecked(false);
        whpcrm.setChecked(false);

    }

}