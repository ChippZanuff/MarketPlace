package com.example.deepdev_03.muvito;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class AddItemActivity extends AppCompatActivity {

    TableLayout tableLayout;
    TableRow tableRow;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        tableLayout = (TableLayout) findViewById(R.id.tableLayoutAddItemActivity);
        tableRow = (TableRow) findViewById(R.id.tableRow);
        editText = (EditText) findViewById(R.id.editTextPrice);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthButton = size.x / 4;

            for (int j = 0; j < 4; j++) {
                ImageButton imageButton = new ImageButton(this);
                imageButton.setLayoutParams(new TableRow.LayoutParams(widthButton, widthButton));
                imageButton.setImageResource(R.drawable.plus_circle);
                tableRow.addView(imageButton, j);
            }

        editText.setOnKeyListener(new View.OnKeyListener()
                {
                    public boolean onKey(View v, int keyCode, KeyEvent event)
                    {
                        if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                  (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            editText.setText(editText.getText() + "\u20BD");
                            return true;
                        }
                        return false;
                    }
                }
        );
    }

    public void onClickCloseAddItemActivity(View view){
        finish();
    }

    public void onClickCategoriesAddItemActivity(View view){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void onClickLocationAddItemActivity (View view){
        Intent intent = new Intent(this, AddLocationActivity.class);
        startActivity(intent);
    }
}
