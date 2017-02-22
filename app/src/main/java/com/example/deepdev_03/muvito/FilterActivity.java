package com.example.deepdev_03.muvito;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class FilterActivity extends AppCompatActivity {


    SeekBar seekBar;
    TextView textView;
    TableRow tableRowView, tableRowSort, tableRowPublished, tableRowCategory, tableRowLocation;

    final int step = 1;
    final int max = 5;
    final int min = 0;

    int valueProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        valueProgress = 4;  //убрать позже

        seekBar = (SeekBar) findViewById(R.id.seekBarDistance);
        textView = (TextView) findViewById(R.id.textViewFilterDistance);
        tableRowView = (TableRow) findViewById(R.id.tableRowView);
        tableRowSort = (TableRow) findViewById(R.id.tableRowSort);
        tableRowPublished = (TableRow) findViewById(R.id.tableRowPublished);
        tableRowCategory = (TableRow) findViewById(R.id.tableRowCategory);
        tableRowLocation = (TableRow) findViewById(R.id.tableRowLocation);


        seekBar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(
                getResources().getColor(R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN);


        seekBar.setMax((max - min) / step);
        seekBar.setProgress(valueProgress);
        setDistance();


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueProgress = progress;
                setDistance();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }

    public void onClickCloseFilter(View view) {
        finish();
    }

    public void setDistance() {
        switch (valueProgress) {
            case 0:
                textView.setText("На расстоянии 1 км");
                break;
            case 1:
                textView.setText("На расстоянии 5 км");
                break;
            case 2:
                textView.setText("На расстоянии 10 км");
                break;
            case 3:
                textView.setText("На расстоянии 25 км");
                break;
            case 4:
                textView.setText("На расстоянии 50 км");
                break;
            case 5:
                textView.setText("На расстоянии больше 50 км");
                break;
        }
    }




    public void onClickTableRow (View view) {
        switch (view.getId()) {
            case R.id.tableRowCategory:

                Toast toast = Toast.makeText(getApplicationContext(),
                        "aaaaaaaaaa", Toast.LENGTH_SHORT);
                toast.show();

                break;

            case R.id.tableRowLocation:

                Toast toastd = Toast.makeText(getApplicationContext(),
                        "dasdasdasd", Toast.LENGTH_SHORT);
                toastd.show();
                break;

            case R.id.tableRowView:

                break;

            case R.id.tableRowSort:

                break;

            case R.id.tableRowPublished:

                break;
            }
    }

}
