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
    private final int DEFAULT_QTY = 1;
    private final int FIRST = 0;
    private final int SECOND = 1;
    private final int THIRD = 2;
    private final int FOURTH = 3;
    private final int FIFTH = 4;
    private final int SIXTH = 5;
    private final int SEVENTH = 6;
    private final int EIGHTH = 7;
    private final int NINTH = 8;
    private final int TENTH = 9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donuts);
        flavor = (Spinner) findViewById(R.id.flavors);
        currentDonut = new Donut(Flavor.CHOCOLATE_FROSTED, DEFAULT_QTY); //default values
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

                    currentDonut.setQuantity(DEFAULT_QTY);
                }
            }
        });
        total.setText(format.format(currentDonut.itemPrice())); //update total


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
                    case FIRST:
                        currentDonut.setFlavor(Flavor.CHOCOLATE_FROSTED);
                        break;
                    case SECOND:
                        currentDonut.setFlavor(Flavor.GLAZED);
                        break;
                    case THIRD:
                        currentDonut.setFlavor(Flavor.MAPLE_SYRUP_FROSTED);
                        break;
                    case FOURTH:
                        currentDonut.setFlavor(Flavor.BLUEBERRY);
                        break;
                    case FIFTH:
                        currentDonut.setFlavor(Flavor.STRAWBERRY);
                        break;
                    case SIXTH:
                        currentDonut.setFlavor(Flavor.PLAIN);
                        break;
                    case SEVENTH:
                        currentDonut.setFlavor(Flavor.BOSTON_CREAM);
                        break;
                    case EIGHTH:
                        currentDonut.setFlavor(Flavor.JELLY_FILLED);
                        break;
                    case NINTH:
                        currentDonut.setFlavor(Flavor.POWDERED_SUGAR);
                        break;
                    case TENTH:
                        currentDonut.setFlavor(Flavor.DOUBLE_CHOCOLATE);
                        break;

                    default:


                }
                total.setText(format.format(currentDonut.itemPrice()));

            }

            /**
             * if nothign is selected set total to default value
             * @param parentView
             */
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
        currentDonut = new Donut(Flavor.CHOCOLATE_FROSTED, DEFAULT_QTY);
        qty.setText("1");
        flavor.setSelection(FIRST);


    }
}