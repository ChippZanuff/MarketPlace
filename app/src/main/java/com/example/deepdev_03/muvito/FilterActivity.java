package com.example.deepdev_03.muvito;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.deepdev_03.muvito.Utils.NumbersFormatter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class FilterActivity extends AppCompatActivity implements View.OnFocusChangeListener {


    SeekBar seekBar;
    TextView textView;
    TableRow tableRowView, tableRowSort, tableRowPublished, tableRowCategory, tableRowLocation;
    TextView textViewView, textViewSort, textViewPublished;
    EditText editTextMinPrice, editTextMaxPrice;
    private boolean isEmptyEditText = false;
    private boolean isEditText = false;
    /*private TextWatcher watcher = new TextWatcher() {

        int minPrice;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().contains("\u20BD")) {
                String text = editTextMinPrice.getText().toString();
                editTextMinPrice.setText(text + "\u20BD");
                isEmptyEditText = false;
            }

            if (editTextMinPrice.getText().length() == 1) {
                editTextMinPrice.removeTextChangedListener(watcher);             // remove the listener
                editTextMinPrice.setText("");        // update the text
                editTextMinPrice.addTextChangedListener(watcher);
                Selection.setSelection(editTextMinPrice.getText(), editTextMinPrice.getText().length());
            }
            else{
                Selection.setSelection(editTextMinPrice.getText(), editTextMinPrice.getText().length()-1);
            }

                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);

                DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
                symbols.setGroupingSeparator('\u2008');

                formatter.setDecimalFormatSymbols(symbols);

                String str = editTextMinPrice.getText().toString().substring(0, editTextMinPrice.length() - 1);

                minPrice = Integer.parseInt(str);

                isEditText=true;
                editTextMinPrice.removeTextChangedListener(watcher);
                editTextMinPrice.setText(minPrice + "\u20BD");
                editTextMinPrice.addTextChangedListener(watcher);
                Log.e("Formatted: ", formatter.format(minPrice));

        }
    };*/



    final int step = 1;
    final int max = 5;
    final int min = 0;

    int valueProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        valueProgress = 4;

        seekBar = (SeekBar) findViewById(R.id.seekBarDistance);
        textView = (TextView) findViewById(R.id.textViewFilterDistance);
        tableRowView = (TableRow) findViewById(R.id.tableRowView);
        tableRowSort = (TableRow) findViewById(R.id.tableRowSort);
        tableRowPublished = (TableRow) findViewById(R.id.tableRowPublished);
        tableRowCategory = (TableRow) findViewById(R.id.tableRowCategory);
        tableRowLocation = (TableRow) findViewById(R.id.tableRowLocation);
        textViewView = (TextView) findViewById(R.id.textViewFilterView);
        textViewSort = (TextView) findViewById(R.id.textViewFilterSort);
        textViewPublished = (TextView) findViewById(R.id.textViewFilterPublished);
        editTextMinPrice = (EditText) findViewById(R.id.editTextMinPrice);
        editTextMaxPrice = (EditText) findViewById(R.id.editTextMaxPrice);


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

        //editTextMinPrice.setOnFocusChangeListener(this);

        this.initEditText();

        /*final MaskedTextChangedListener listener = new MaskedTextChangedListener(
                "[000] [000] [000]",
                true,
                editTextMinPrice,
                null,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue) {
                        Log.d(MainActivity.class.getSimpleName(), extractedValue);
                        Log.d(MainActivity.class.getSimpleName(), String.valueOf(maskFilled));
                    }
                }
        );

        editTextMinPrice.addTextChangedListener(listener);
        editTextMinPrice.setOnFocusChangeListener(listener);*/
//        editTextMinPrice.setHint(listener.placeholder());


//        editTextMinPrice.addTextChangedListener(new TextWatcher() {
//            int a;
//            private int caretPosition = 0;
//            private String afterText;
//            private final WeakReference<EditText> field = new WeakReference(editTextMinPrice);
//            TextWatcher listener = this;
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
//
//                DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
//                symbols.setGroupingSeparator('\u2008');
//
//                formatter.setDecimalFormatSymbols(symbols);
//
//              a =  Integer.parseInt(editTextMinPrice.getText().toString());
//                afterText = editTextMinPrice.getText().toString();
//                Log.e("Formatted: ", formatter.format(a));
//               //   s.replace(0,s.length(),""+a);
//            //    editTextMinPrice.setText(""+a);
//
//
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//
//            }
//
//            public void afterTextChanged(Editable edit) {
//            //  s.replace(0,s.length(),""+a);
//              //  return;
//                ((EditText)this.field.get()).removeTextChangedListener((TextWatcher)this);
//                if(edit != null) {
//                    edit.replace(0, edit.length(), ""+a);
//                }
//
//                ((EditText)this.field.get()).setSelection(this.caretPosition);
//
//                ((EditText)this.field.get()).addTextChangedListener((TextWatcher)this);
//                TextWatcher var10000 = this.listener;
//                if(this.listener != null) {
//                    var10000.afterTextChanged(edit);
//                }
//
//
//            }
//        });
//
//


//        editTextMinPrice.setOnKeyListener(new View.OnKeyListener()
//            {
//                public boolean onKey(View v, int keyCode, KeyEvent event)
//                {
//
//                    if(event.getAction() == KeyEvent.ACTION_DOWN &&
//                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                        editTextMinPrice.setText(editTextMinPrice.getText() + "\u20BD");
//                        return true;
//                    }
//                    return false;
//                }
//            }
//        );

        editTextMaxPrice.setOnKeyListener(new View.OnKeyListener() {
                                              public boolean onKey(View v, int keyCode, KeyEvent event) {

                                                  if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                                          (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                                      editTextMaxPrice.setText(editTextMaxPrice.getText() + "\u20BD");
                                                      return true;
                                                  }
                                                  return false;
                                              }
                                          }
        );


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


    public void onClickTableRow(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(FilterActivity.this);
        alert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        switch (view.getId()) {
            case R.id.tableRowCategory:
                Intent intent_categories = new Intent(getApplicationContext(), CategoriesActivity.class);
                startActivity(intent_categories);
                break;

            case R.id.tableRowLocation:
                Intent intent_location = new Intent(this, AddLocationActivity.class);
                startActivity(intent_location);
                break;

            case R.id.tableRowView:

                alert.setTitle("Вид главной");

                final ArrayAdapter<String> arrayAdapterView = new ArrayAdapter<String>(FilterActivity.this, android.R.layout.simple_list_item_1);
                arrayAdapterView.add("Плиткой");
                arrayAdapterView.add("Списком");

                alert.setAdapter(arrayAdapterView, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strView = arrayAdapterView.getItem(which);
                        textViewView.setText(strView);
                        dialog.dismiss();
                    }
                });
                alert.show();
                break;

            case R.id.tableRowSort:

                alert.setTitle("Сортировка");

                final ArrayAdapter<String> arrayAdapterSort = new ArrayAdapter<String>(FilterActivity.this, android.R.layout.simple_list_item_1);
                arrayAdapterSort.add("По умолчанию");
                arrayAdapterSort.add("По удаленности");
                arrayAdapterSort.add("По стоимости");
                arrayAdapterSort.add("По новизне");

                alert.setAdapter(arrayAdapterSort, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strView = arrayAdapterSort.getItem(which);
                        textViewSort.setText(strView);
                        dialog.dismiss();
                    }
                });
                alert.show();
                break;

            case R.id.tableRowPublished:

                alert.setTitle("Сортировка");

                final ArrayAdapter<String> arrayAdapterPublished = new ArrayAdapter<String>(FilterActivity.this, android.R.layout.simple_list_item_1);
                arrayAdapterPublished.add("За 24 часа");
                arrayAdapterPublished.add("За 7 дней");
                arrayAdapterPublished.add("За все время");

                alert.setAdapter(arrayAdapterPublished, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strView = arrayAdapterPublished.getItem(which);
                        textViewPublished.setText(strView);
                        dialog.dismiss();
                    }
                });
                alert.show();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {


    }

    public void onClickCloseFilter(View view) {
        finish();
    }

    private void initEditText()
    {
        this.editTextMinPrice.addTextChangedListener(new NumbersFormatter(editTextMinPrice));
        this.editTextMaxPrice.addTextChangedListener(new NumbersFormatter(editTextMaxPrice));
    }
}


