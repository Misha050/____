package com.example.imt;

import android.content.Intent;
import android.graphics.PorterDuff;
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

        // Получение значения ИМТ из намерения
        Intent intent = getIntent();
        double bmi = intent.getDoubleExtra("BMI_VALUE", 0.0);

        // Инициализация UI-компонентов
        TextView numberTextView = findViewById(R.id.number);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button backButton = findViewById(R.id.button2);

        // Отображение значения ИМТ
        numberTextView.setText(String.format("%.2f", bmi));

        // Нормализация ИМТ в диапазоне 0-100 для прогресса
        int progress = (int) Math.min(100, Math.max(0, bmi * 5));
        progressBar.setProgress(progress);

        // Установка цвета в зависимости от значения ИМТ
        if (bmi < 18.5) {
            // Синий цвет для недостаточного веса
            progressBar.getProgressDrawable().setColorFilter(0xFF0000FF, PorterDuff.Mode.SRC_IN);
        } else if (bmi < 25) {
            // Зеленый цвет для нормального веса
            progressBar.getProgressDrawable().setColorFilter(0xFF00FF00, PorterDuff.Mode.SRC_IN);
        } else if (bmi < 30) {
            // Желтый цвет для избыточного веса
            progressBar.getProgressDrawable().setColorFilter(0xFFFFFF00, PorterDuff.Mode.SRC_IN);
        } else {
            // Оранжевый цвет для ожирения
            progressBar.getProgressDrawable().setColorFilter(0xFFFFA500, PorterDuff.Mode.SRC_IN);
        }

        // Действие кнопки назад
        backButton.setOnClickListener(v -> {
            finish(); // Возвращение в MainActivity
        });
    }
}
