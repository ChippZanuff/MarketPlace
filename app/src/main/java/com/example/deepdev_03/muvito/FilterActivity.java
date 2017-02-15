package com.example.deepdev_03.muvito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }

    public void onClickCloseFilter(View view){
        finish();
    }
}
