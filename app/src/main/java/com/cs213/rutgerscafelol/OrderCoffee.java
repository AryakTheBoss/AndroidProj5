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

/**
 * Class contains controls for the ordering coffees menu
 * @author mss390 amp487 Mayank Singamreddy Aryak Pande
 *
 */
public class OrderCoffee extends AppCompatActivity {

    private Spinner size;
    private Coffee currentCoffee;
    private DecimalFormat format = new DecimalFormat("$#,##0.00");
    private EditText qty;
    private CheckBox cream,milk,syrup,caramel,whpcrm;
    private final int DEFAULT_QTY = 1;
    private final int SHORT = 0;
    private final int TALL = 1;
    private final int GRANDE = 2;
    private final int VENTI = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);
        size = (Spinner) findViewById(R.id.size);
        currentCoffee = new Coffee(Size.SHORT, DEFAULT_QTY); //default values
        setTitle(R.string.order_coffee);
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

            /**
             * update price everytime quantity is changed
             * @param s the string in the box currently
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String num = s.toString();
                try {
                    currentCoffee.setQuantity(Integer.parseInt(num)); //parse and update
                    total.setText(format.format(currentCoffee.itemPrice()));
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),R.string.enter_a_num,Toast.LENGTH_SHORT).show();

                    currentCoffee.setQuantity(DEFAULT_QTY);
                }
            }
        });
        total.setText(format.format(currentCoffee.itemPrice())); //update price

        cream = (CheckBox) findViewById(R.id.creamCB);
         milk = (CheckBox) findViewById(R.id.milkCB);
         syrup = (CheckBox) findViewById(R.id.syrupCB); //initalize all checkboxes
         caramel = (CheckBox) findViewById(R.id.caramelCB);
        whpcrm = (CheckBox) findViewById(R.id.whpcrmCB);

        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Method changes total based on size currently selected
             * @param parentView
             * @param selectedItemView
             * @param position the current index position that it is selected
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch(position){
                    case SHORT:
                        currentCoffee.setSize(Size.SHORT);
                        break;
                    case TALL:
                        currentCoffee.setSize(Size.TALL);
                        break;
                    case GRANDE:
                        currentCoffee.setSize(Size.GRANDE);
                        break;
                    case VENTI:
                        currentCoffee.setSize(Size.VENTI);
                        break;

                    default:


                }
                total.setText(format.format(currentCoffee.itemPrice())); //update total
                cream.setClickable(true);
                milk.setClickable(true);
                syrup.setClickable(true); //make sure they are enabled
                caramel.setClickable(true);
                whpcrm.setClickable(true);
            }

            /**
             * If nothing is selected, disable the checkboxes
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                total.setText(R.string._0_00);
                cream.setClickable(false);
                milk.setClickable(false); //disable the checkboxes
                syrup.setClickable(false);
                caramel.setClickable(false);
                whpcrm.setClickable(false);
            }

        });


        /* ADD LISTENERS TO EACH CHECKBOX */

        cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                   if(isChecked) { //if the new value is TRUE
                                                       currentCoffee.add(AddIns.CREAM);
                                                   }else{ //if the new value is FALSE
                                                       currentCoffee.remove(AddIns.CREAM);
                                                   }

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
        Toast.makeText(getApplicationContext(),currentCoffee.toString()+" Added to Order.",Toast.LENGTH_LONG).show(); //show message
        References.customerOrder.add(currentCoffee); //add coffee to the order
        currentCoffee = new Coffee(Size.SHORT, DEFAULT_QTY); //create a new coffee
        qty.setText("1");
        size.setSelection(SHORT);
        cream.setChecked(false);
        milk.setChecked(false);
        syrup.setChecked(false); //reset all check boxes
        caramel.setChecked(false);
        whpcrm.setChecked(false);

    }

}