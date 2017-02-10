package com.example.deepdev_03.muvito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    GridView gridView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
            setSupportActionBar(myToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            editText = (EditText) findViewById(R.id.editTextMainActivity);
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        editText.setHint("Поиск");
                    }
                }
            });
        
    }

    public void onClickCategories(View view){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

}

