package com.codewithzio.gstcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InvoiceDetails extends AppCompatActivity {

    String TABLE_NAME = "Invoices";
    StoreDB database;

    int bill;
    String date;

    TextView day, bill_num;
    EditText net, gst, gross, customer;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);

        day = findViewById(R.id.day);
        bill_num = findViewById(R.id.bill);

        net = findViewById(R.id.net);
        gst = findViewById(R.id.gst);
        gross = findViewById(R.id.gross);
        customer = findViewById(R.id.inp2);

        bill_num.setText("INVOICE #");

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        preferences = getSharedPreferences("invoices", Context.MODE_PRIVATE);
        bill = preferences.getInt("total", 0);

    }

    public void save_inv(View view) {
        database = new StoreDB(this, "StoreDB", TABLE_NAME);
        ModelInvoice invoice = new ModelInvoice(
                "INV00" + (bill + 1),
                Integer.parseInt(net.getText().toString()),
                Integer.parseInt(gst.getText().toString()),
                Integer.parseInt(gross.getText().toString()),
                date,
                customer.getText().toString()
        );

        if (!database.isTableExists(TABLE_NAME))
            database.createInvTable("");

        database.addInvoice(invoice);
        //database.close();
        Toast.makeText(this, "saved successfully", Toast.LENGTH_LONG).show();
        preferences.edit().putInt("total", ++bill).apply();

    }

    public void print(View view) {

        Toast.makeText(this, "Feature coming soon...", Toast.LENGTH_LONG).show();
    }
}