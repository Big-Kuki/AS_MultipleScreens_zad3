package com.example.as_multiplescreens_zad3;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class OrderSummaryActivity extends AppCompatActivity {

    public static final String EXTRA_ADDRESS = "com.example.zamowieniepizzy.ADDRESS";
    public static final String EXTRA_SIZE_NAME = "com.example.zamowieniepizzy.SIZE_NAME";
    public static final String EXTRA_SIZE_INDEX = "com.example.zamowieniepizzy.SIZE_INDEX";
    public static final String EXTRA_QUANTITY = "com.example.zamowieniepizzy.QUANTITY";
    public static final String EXTRA_CHEESE = "com.example.zamowieniepizzy.CHEESE";
    public static final String EXTRA_GARLIC = "com.example.zamowieniepizzy.GARLIC";
    public static final String EXTRA_ORDER_ID = "com.example.zamowieniepizzy.ORDER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        TextView tvSummary = findViewById(R.id.tvOrderSummary);
        tvSummary.setText(prepareSummary());

        findViewById(R.id.btnConfirm).setOnClickListener(v -> confirmOrder());
        findViewById(R.id.btnEdit).setOnClickListener(v -> finish());
        findViewById(R.id.btnCall).setOnClickListener(v -> callPizzeria());
    }

    private String prepareSummary() {
        Intent i = getIntent();
        String address = i.getStringExtra(EXTRA_ADDRESS);
        String sizeName = i.getStringExtra(EXTRA_SIZE_NAME);
        int sizeIndex = i.getIntExtra(EXTRA_SIZE_INDEX, 0);
        int quantity = i.getIntExtra(EXTRA_QUANTITY, 1);
        boolean cheese = i.getBooleanExtra(EXTRA_CHEESE, false);
        boolean garlic = i.getBooleanExtra(EXTRA_GARLIC, false);

        int basePrice = (sizeIndex == 0) ? 30 : (sizeIndex == 1 ? 40 : 50);
        int extrasPrice = (cheese ? 5 : 0) + (garlic ? 3 : 0);
        int total = (basePrice + extrasPrice) * quantity;

        return getString(R.string.order_details_format,
                address,
                sizeName,
                quantity,
                getString(cheese ? R.string.yes : R.string.no),
                getString(garlic ? R.string.yes : R.string.no),
                total);
    }

    private void confirmOrder() {
        String orderId = "PZ-" + (100 + new Random().nextInt(900));
        Intent result = new Intent();
        result.putExtra(EXTRA_ORDER_ID, orderId);
        setResult(RESULT_OK, result);
        finish();
    }

    private void callPizzeria() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(getString(R.string.pizzeria_phone)));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_no_app, Toast.LENGTH_SHORT).show();
        }
    }
}