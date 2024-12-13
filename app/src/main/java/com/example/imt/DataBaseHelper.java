package com.example.imt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// Удаляем модификатор public, так класс будет доступен только в пределах пакета
class DatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static final String DATABASE_NAME = "imt_history.db";
    private static final int DATABASE_VERSION = 1;

    // Имя таблицы и столбцов
    public static final String TABLE_NAME = "history";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BMI = "bmi";
    public static final String COLUMN_HEIGHT = "height";  // Новый столбец для роста
    public static final String COLUMN_WEIGHT = "weight";  // Новый столбец для веса

    // SQL-запрос на создание таблицы с новыми столбцами
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BMI + " REAL, " +
                    COLUMN_HEIGHT + " REAL, " +  // Добавили столбец для роста
                    COLUMN_WEIGHT + " REAL);";   // Добавили столбец для веса

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Обновление таблицы (удаление старой и создание новой)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Метод для добавления записи (BMI, рост и вес)
    public void addBMI(double bmi, double height, double weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BMI, bmi);       // Сохраняем ИМТ
        values.put(COLUMN_HEIGHT, height); // Сохраняем рост
        values.put(COLUMN_WEIGHT, weight); // Сохраняем вес
        db.insert(TABLE_NAME, null, values);
    }

    // Метод для получения всех записей (ИМТ, рост, вес)
    public Cursor getAllBMI() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Запрос для получения всех записей
        return db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_BMI, COLUMN_HEIGHT, COLUMN_WEIGHT},
                null, null, null, null, null);
    }

    // Метод для очистки всех данных из таблицы
    public void clearHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
