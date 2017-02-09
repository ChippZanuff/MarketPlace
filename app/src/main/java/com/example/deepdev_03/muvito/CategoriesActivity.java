package com.example.deepdev_03.muvito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarCategoriesActivity);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayoutMainActivity);

        String categories[] = {"Все объявления", "Женский гардероб","Мужской гардероб","Детские товары","Хэндмейд",
                "Авто и мото", "Смартфоны и планшеты","Фото- и видеотехника", "Компьютерная техника","Электроника",
                "Бытовая техника", "Для дома и дачи", "Ремонт и строительство", "Красота и здоровье", "Спорт и отдых",
                "Хобби и развлечения", "Музыкальные инструменты", "Животные", "Услуги", "Прочее"};

        for (int i = 0; i < categories.length; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            TextView textView = new TextView(this);
            textView.setText(categories[i]);
            textView.setTextSize(20);
            textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            row.addView(textView);
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        }
    }

    public void onClickBackCategories (View view){
        finish();
    }



}
