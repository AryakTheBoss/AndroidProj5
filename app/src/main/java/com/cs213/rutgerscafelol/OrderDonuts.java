package com.cs213.rutgerscafelol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
/**
 * Class contains controls for the ordering new donuts menu
 * @author mss390 amp487 Mayank Singamreddy Aryak Pande
 *
 */
public class OrderDonuts extends AppCompatActivity {

    private Spinner flavor;
    private Donut currentDonut;
    private EditText qty;
    private DecimalFormat format = new DecimalFormat("$#,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donuts);
        flavor = (Spinner) findViewById(R.id.flavors);
        currentDonut = new Donut(Flavor.CHOCOLATE_FROSTED,1); //default values
        setTitle(R.string.order_donuts);
        EditText total = (EditText) findViewById(R.id.total2);
        total.setEnabled(false);
        qty = (EditText) findViewById(R.id.quantDonuts);
        qty.setText("1");
        qty.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            /**
             * method updates the total price every time quantity is changed
             * @param s the input string
             * @param start the index of the string's beginning
             * @param before
             * @param count the length of the string
             */
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String num = s.toString();
                try {
                    currentDonut.setQuantity(Integer.parseInt(num));
                    total.setText(format.format(currentDonut.itemPrice()));
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),R.string.enter_a_num,Toast.LENGTH_SHORT).show();

                    currentDonut.setQuantity(0);
                }
            }
        });
        total.setText(format.format(currentDonut.itemPrice()));


        flavor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * if an item is selected, set the appropriate flavor
             * @param parentView
             * @param selectedItemView
             * @param position the position of the item selected
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch(position){
                    case 0:
                        currentDonut.setFlavor(Flavor.CHOCOLATE_FROSTED);
                        break;
                    case 1:
                        currentDonut.setFlavor(Flavor.GLAZED);
                        break;
                    case 2:
                        currentDonut.setFlavor(Flavor.MAPLE_SYRUP_FROSTED);
                        break;
                    case 3:
                        currentDonut.setFlavor(Flavor.BLUEBERRY);
                        break;
                    case 4:
                        currentDonut.setFlavor(Flavor.STRAWBERRY);
                        break;
                    case 5:
                        currentDonut.setFlavor(Flavor.PLAIN);
                        break;
                    case 6:
                        currentDonut.setFlavor(Flavor.BOSTON_CREAM);
                        break;
                    case 7:
                        currentDonut.setFlavor(Flavor.JELLY_FILLED);
                        break;
                    case 8:
                        currentDonut.setFlavor(Flavor.POWDERED_SUGAR);
                        break;
                    case 9:
                        currentDonut.setFlavor(Flavor.DOUBLE_CHOCOLATE);
                        break;

                    default:


                }
                total.setText(format.format(currentDonut.itemPrice()));

            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                total.setText(R.string._0_00);

            }

        });
    }

    /**
     * add donut to the current order
     * @param v
     */
    public void addDonuts(View v){
        Toast.makeText(getApplicationContext(),currentDonut.toString()+" Added to Order.",Toast.LENGTH_LONG).show();
        References.customerOrder.add(currentDonut);
        currentDonut = new Donut(Flavor.CHOCOLATE_FROSTED,1);
        qty.setText("1");
        flavor.setSelection(0);


    }
}