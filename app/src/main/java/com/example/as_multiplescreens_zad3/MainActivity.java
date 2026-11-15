package com.example.as_multiplescreens_zad3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<Intent> orderLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String orderId = result.getData().getStringExtra(OrderSummaryActivity.EXTRA_ORDER_ID);
                    Snackbar.make(findViewById(R.id.main),
                            getString(R.string.order_confirmed, orderId),
                            Snackbar.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, R.string.order_canceled, Toast.LENGTH_SHORT).show();
                }
            });

    private EditText etAddress;
    private Spinner spSize;
    private SeekBar sbQuantity;
    private CheckBox cbCheese, cbGarlic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddress = findViewById(R.id.etAddress);
        spSize = findViewById(R.id.spSize);
        sbQuantity = findViewById(R.id.sbQuantity);
        cbCheese = findViewById(R.id.cbCheese);
        cbGarlic = findViewById(R.id.cbGarlic);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.pizza_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSize.setAdapter(adapter);

        findViewById(R.id.btnNext).setOnClickListener(v -> validateAndOpenSummary());
    }

    private void validateAndOpenSummary() {
        String address = etAddress.getText().toString().trim();
        if (address.isEmpty()) {
            etAddress.setError(getString(R.string.error_empty_address));
            return;
        }

        Intent intent = new Intent(this, OrderSummaryActivity.class);
        intent.putExtra(OrderSummaryActivity.EXTRA_ADDRESS, address);
        intent.putExtra(OrderSummaryActivity.EXTRA_SIZE_NAME, spSize.getSelectedItem().toString());
        intent.putExtra(OrderSummaryActivity.EXTRA_SIZE_INDEX, spSize.getSelectedItemPosition());
        intent.putExtra(OrderSummaryActivity.EXTRA_QUANTITY, sbQuantity.getProgress() + 1);
        intent.putExtra(OrderSummaryActivity.EXTRA_CHEESE, cbCheese.isChecked());
        intent.putExtra(OrderSummaryActivity.EXTRA_GARLIC, cbGarlic.isChecked());

        orderLauncher.launch(intent);
    }

    
}