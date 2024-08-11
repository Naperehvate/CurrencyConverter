package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency);
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency);
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonConvert = findViewById(R.id.buttonConvert);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        double amount = Double.parseDouble(editTextAmount.getText().toString());
        String fromCurrency = spinnerFromCurrency.getSelectedItem().toString();
        String toCurrency = spinnerToCurrency.getSelectedItem().toString();

        double conversionRate = getConversionRate(fromCurrency, toCurrency);
        double result = amount * conversionRate;

        textViewResult.setText(String.format("%.2f %s", result, toCurrency));
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        // Задаем фиксированные коэффициенты конвертации
        switch (fromCurrency) {
            case "USD":
                switch (toCurrency) {
                    case "EUR": return 0.85;
                    case "RUB": return 74.0;
                }
                break;
            case "EUR":
                switch (toCurrency) {
                    case "USD": return 1.18;
                    case "RUB": return 87.0;
                }
                break;
            case "RUB":
                switch (toCurrency) {
                    case "USD": return 0.014;
                    case "EUR": return 0.011;
                }
                break;
        }
        return 1.0;  // Если не удалось определить коэффициент конвертации, возвращаем 1
    }
}
