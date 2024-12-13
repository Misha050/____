package com.example.imt;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryPage extends AppCompatActivity {

    private TextView historyTextView;  // Переменная для заголовка
    private TextView historyContent;   // Переменная для отображения истории
    private DatabaseHelper databaseHelper;  // Переменная для работы с базой данных

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);

        // Инициализация TextView и DatabaseHelper
        historyTextView = findViewById(R.id.historyTextView);
        historyContent = findViewById(R.id.historyContent);
        databaseHelper = new DatabaseHelper(this);

        // Загружаем все данные из базы данных
        loadHistory();

        Button buttonClear = findViewById(R.id.button4);
        Button buttonBack = findViewById(R.id.button3);

        buttonClear.setOnClickListener(v -> {
            // Очистить историю из базы данных
            databaseHelper.clearHistory();
            loadHistory();  // Перезагружаем историю после очистки
        });

        buttonBack.setOnClickListener(v -> {
            // Возврат на главный экран
            finish();
        });
    }

    // Метод для загрузки данных истории из базы данных и отображения их
    private void loadHistory() {
        // Получаем все данные из базы данных
        Cursor cursor = databaseHelper.getAllBMI();

        // Проверяем, есть ли данные
        if (cursor.getCount() == 0) {
            historyContent.setText("История пуста");
        } else {
            StringBuilder historyBuilder = new StringBuilder();
            while (cursor.moveToNext()) {
                historyBuilder.append("ИМТ: ").append(bmi).append("\n");
            }
            // Отображаем все данные
            historyContent.setText(historyBuilder.toString());
        }
        cursor.close();
    }
}
