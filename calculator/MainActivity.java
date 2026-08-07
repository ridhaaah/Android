package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText1 = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        resultTextView = findViewById(R.id.textView);

        Button addButton = findViewById(R.id.button);
        Button subButton = findViewById(R.id.button2);
        Button mulButton = findViewById(R.id.button3);
        Button divButton = findViewById(R.id.button4);

        addButton.setOnClickListener(v -> calculate('+'));
        subButton.setOnClickListener(v -> calculate('-'));
        mulButton.setOnClickListener(v -> calculate('*'));
        divButton.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operator) {
        String s1 = editText1.getText().toString();
        String s2 = editText2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Please enter numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    return;
            }

            resultTextView.setText(String.format(Locale.getDefault(), "Result: %.2f", result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}