package com.example.imt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация UI компонентов
        EditText heightInput = findViewById(R.id.Height);
        EditText weightInput = findViewById(R.id.Weight);
        Button calculateButton = findViewById(R.id.button);
        Button historyButton = findViewById(R.id.button5);

        // Кнопка для расчёта ИМТ
        calculateButton.setOnClickListener(v -> {
            String heightText = heightInput.getText().toString();
            String weightText = weightInput.getText().toString();

            if (!heightText.isEmpty() && !weightText.isEmpty()) {
                double height = Double.parseDouble(heightText) / 100.0; // Convert cm to meters
                double weight = Double.parseDouble(weightText);
                double bmi = weight / (height * height);

                // Сохранение ИМТ в SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("IMT_Data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("BMI", (float) bmi);
                editor.apply();

                // Переход на страницу с результатом
                Intent intent = new Intent(MainActivity.this, NextPageActivity.class);
                intent.putExtra("BMI_VALUE", bmi);
                startActivity(intent);
            }
        });

        // Кнопка для перехода в историю
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryPage.class);
            startActivity(intent);
        });

        // В MainActivity.java
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        // Добавляем ИМТ в базу данных при нажатии кнопки "Рассчитать"
        calculateButton.setOnClickListener(v -> {
            String heightText = heightInput.getText().toString();
            String weightText = weightInput.getText().toString();

            if (!heightText.isEmpty() && !weightText.isEmpty()) {
                double height = Double.parseDouble(heightText) / 100.0; // Convert cm to meters
                double weight = Double.parseDouble(weightText);
                double bmi = weight / (height * height);

                // Сохраняем ИМТ в базе данных
                databaseHelper.addBMI(bmi);

                Intent intent = new Intent(MainActivity.this, NextPageActivity.class);
                intent.putExtra("BMI_VALUE", bmi);
                startActivity(intent);
            }
        });

    }
}
