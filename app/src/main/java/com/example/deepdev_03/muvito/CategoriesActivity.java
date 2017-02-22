package com.example.deepdev_03.muvito;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class CategoriesActivity extends AppCompatActivity {

    SubcategoriesFragment fragment;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarCategoriesActivity);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fragment = new SubcategoriesFragment();

        final ListView listView = (ListView) findViewById(R.id.listViewCategoriesActivity);
        title = (TextView) findViewById(R.id.textViewToolbarCategoriesActivity);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewSelectedCategoriesItem);

        final String categories[] = {"Все объявления", "Женский гардероб", "Мужской гардероб", "Детские товары", "Хэндмейд",
                "Авто и мото", "Смартфоны и планшеты", "Фото- и видеотехника", "Компьютерная техника", "Электроника",
                "Бытовая техника", "Для дома и дачи", "Ремонт и строительство", "Красота и здоровье", "Спорт и отдых",
                "Хобби и развлечения", "Музыкальные инструменты", "Животные", "Услуги", "Прочее"};


        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,
                R.layout.categories_item, R.id.textViewCategoriesItem, categories);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position != 0) {

                        title.setText(categories[position]);

                        Bundle bundle = new Bundle();
                        position--;
                        bundle.putInt("SelectedPosition", position);
                        SubcategoriesFragment fragment = new SubcategoriesFragment();
                        fragment.setArguments(bundle);

                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                                .add(R.id.fragment_place, fragment)
                                .commit();
                }
            }
        });
    }

    public void onClickBackCategories (View view){
        closeActivityOrFragment ();
    }

    @Override
    public void onBackPressed() {
        closeActivityOrFragment ();
    }


    public void closeActivityOrFragment (){

        fragment = (SubcategoriesFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_place);

        if(fragment == null ) {
            finish();
        } else {
            title = (TextView) findViewById(R.id.textViewToolbarCategoriesActivity);
            title.setText("Категория");

            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right)
                    .remove(fragment)
                    .commit();
        }
    }

}

