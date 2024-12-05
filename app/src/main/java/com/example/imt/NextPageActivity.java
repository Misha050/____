package com.example.imt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NextPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_page);

        // Retrieve BMI value from intent
        Intent intent = getIntent();
        double bmi = intent.getDoubleExtra("BMI_VALUE", 0.0);

        // Initialize UI components
        TextView numberTextView = findViewById(R.id.number);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button backButton = findViewById(R.id.button2);

        // Display BMI value
        numberTextView.setText(String.format("%.2f", bmi));

        // Set progress and color zones
        int progress = (int) Math.min(100, Math.max(0, bmi * 5)); // Normalize BMI to 0-100
        progressBar.setProgress(progress);

        // Back button action
        backButton.setOnClickListener(v -> {
            finish(); // Return to MainActivity
        });
    }
}
