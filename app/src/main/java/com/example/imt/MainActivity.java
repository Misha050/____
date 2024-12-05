package com.example.imt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Adjust padding for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        EditText heightInput = findViewById(R.id.Height);
        EditText weightInput = findViewById(R.id.Weight);
        Button calculateButton = findViewById(R.id.button);

        calculateButton.setOnClickListener(v -> {
            String heightText = heightInput.getText().toString();
            String weightText = weightInput.getText().toString();

            if (!heightText.isEmpty() && !weightText.isEmpty()) {
                double height = Double.parseDouble(heightText) / 100.0; // Convert cm to meters
                double weight = Double.parseDouble(weightText);
                double bmi = weight / (height * height);

                Intent intent = new Intent(MainActivity.this, NextPageActivity.class);
                intent.putExtra("BMI_VALUE", bmi);
                startActivity(intent);
            }
        });
    }
}
