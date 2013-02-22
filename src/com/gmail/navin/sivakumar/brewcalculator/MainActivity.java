package com.gmail.navin.sivakumar.brewcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final int DRIP = R.id.radio_drip;
	public final int IMMERSION = R.id.radio_immersion;
	private int method; // indicates whether brew method is DRIP or IMMERSION
	private double absorption = 2; // absorption factor
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Calculate extraction yield based on inputs and display result */
    public void calculateYield(View view) {
    	// Read dose, water, and TDS
    	EditText editDose = (EditText) findViewById(R.id.edit_dose);
    	double dose = Double.parseDouble(editDose.getText().toString());
    	EditText editWater = (EditText) findViewById(R.id.edit_water);
    	double water = Double.parseDouble(editWater.getText().toString());
    	EditText editTds = (EditText) findViewById(R.id.edit_tds);
    	double tds = Double.parseDouble(editTds.getText().toString());
    	
    	// Calculate yield
    	double yield = calculateYield(dose, water, tds);
    	
    	// Update view to show yield
    	TextView viewYield = (TextView) findViewById(R.id.view_yield);
    	viewYield.setText(Double.toString(yield));
    }
    
    /** = yield when dose grams of coffee and water grams of water produce brew strength tds */
    private double calculateYield(double dose, double water, double tds) {
    	double effWater = water; // effective water used to extract coffee
    	switch (method) {
    	case DRIP:
    		effWater -= absorption*dose; // absorbed liquid is water
    		break;
    	case IMMERSION:
    		break; // absorbed liquid is coffee
    	}
    	
    	return effWater*(1+0.01*tds)*tds/dose;
    }
    
    /** Set method to immersion */
    public void onClickImmersion(View v) {
    	method = IMMERSION;
    }
    
    /** Set method to drip */
    public void onClickDrip(View v) {
    	method = DRIP;
    }
}
