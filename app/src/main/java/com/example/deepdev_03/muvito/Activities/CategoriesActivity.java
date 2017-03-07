package com.example.deepdev_03.muvito.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deepdev_03.muvito.Adapters.CategoriesAdapter;
import com.example.deepdev_03.muvito.R;
import com.example.deepdev_03.muvito.Fragments.SubcategoriesFragment;


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

        final int images [] = { R.drawable.view_grid, R.drawable.ic_skirt, R.drawable.ic_polo, R.drawable.ic_rocking_horse,
                R.drawable.ic_button, R.drawable.ic_car, R.drawable.ic_smartphone, R.drawable.ic_photo_camera,
                R.drawable.ic_television, R.drawable.ic_socket, R.drawable.ic_washing_machine, R.drawable.ic_sofa,
                R.drawable.ic_tools, R.drawable.ic_mortar, R.drawable.ic_basketball, R.drawable.ic_game_controller,
                R.drawable.ic_trumpet, R.drawable.ic_dog,R.drawable.ic_portfolio, R.drawable.ic_pacman };

        listView.setAdapter(new CategoriesAdapter(this,categories,images));

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

