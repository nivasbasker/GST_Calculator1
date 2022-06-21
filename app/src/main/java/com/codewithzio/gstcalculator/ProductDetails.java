package com.codewithzio.gstcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

public class ProductDetails extends AppCompatActivity {

    String TABLE_NAME = "Products";
    StoreDB database;

    EditText name, desc, net, gross;
    Slider gst;
    int _gst, _net = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        name = findViewById(R.id.inp1);
        desc = findViewById(R.id.inp2);
        net = findViewById(R.id.inp3);
        gst = findViewById(R.id.inp4);
        gross = findViewById(R.id.gross);

        Intent i = getIntent();
        if (i.getBooleanExtra("type", false)) {
            name.setText(i.getStringExtra("name"));
            desc.setText(i.getStringExtra("desc"));

            net.setText(String.valueOf(i.getIntExtra("net", 0)));
            gst.setValue(i.getIntExtra("gst", 0));
            gross.setText(String.valueOf(i.getIntExtra("gross", 0)));
        }

        gst.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                _gst = (int) value;
                _net = Integer.parseInt(net.getText().toString());
                gross.setText(String.valueOf(_net + (_gst * _net / 100)));
            }
        });

    }

    public void add_product(View view) {
        database = new StoreDB(this, "StoreDB", TABLE_NAME);
        ModelProducts product = new ModelProducts(
                name.getText().toString(),
                desc.getText().toString(),
                Integer.parseInt(net.getText().toString()),
                _gst,
                Integer.parseInt(gross.getText().toString()));

        if (!database.isTableExists(TABLE_NAME))
            database.createProductTable("");

        database.addproduct(product);
        database.close();
        Toast.makeText(this, "added successfully", Toast.LENGTH_SHORT).show();

    }
}