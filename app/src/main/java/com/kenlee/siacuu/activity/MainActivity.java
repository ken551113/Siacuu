package com.kenlee.siacuu.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kenlee.siacuu.R;
import com.kenlee.siacuu.fragment.StoreFragment;
import com.kenlee.siacuu.fragment.HomeFragment;
import com.kenlee.siacuu.fragment.MapFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private int menu = 0;
    private HomeFragment homeFm;
    private StoreFragment storeFm;
    private MapFragment mapFm;

    public static final int  FRAGMENT_HOME = 0;
    public static final int  FRAGMENT_STORE = 1;
    public static final int  FRAGMENT_MAP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
//        if (!EasyPermissions.hasPermissions(MainActivity.this, perms)) {
//            EasyPermissions.requestPermissions(MainActivity.this, "定位需要GPS權限",
//                    100, perms);
//        }
        showFragment(FRAGMENT_HOME);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home_fragment:
                        showFragment(FRAGMENT_HOME);
                        item.setChecked(true);
                        break;
                    case R.id.store_fragment:
                        showFragment(FRAGMENT_STORE);
                        item.setChecked(true);
                        break;
                    case R.id.map_fragment:
                        showFragment(FRAGMENT_MAP);
                        item.setChecked(true);
                        break;
                }
                return false;
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showFragment(int index){


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);

        switch(index){

            case FRAGMENT_HOME:
                /**
                 * 如果Fragment為空，就建立一個實例
                 * 如果不為空，就把它從棧中顯示出來
                 * */
                menu = 0;
                if(homeFm == null){
                    homeFm = new HomeFragment();
                    ft.add(R.id.main_container,homeFm);
                }else {
                    ft.show(homeFm);
                }
                break;

            case FRAGMENT_STORE:
                menu = 1;

                if(storeFm == null){
                    storeFm = new StoreFragment();
                    ft.add(R.id.main_container,storeFm);
                }else {
                    ft.show(storeFm);
                }
                break;

            case FRAGMENT_MAP:
                menu = 2;
                if(mapFm == null){
                    mapFm = new MapFragment();
                    ft.add(R.id.main_container,mapFm);
                }else {
                    ft.show(mapFm);
                }
                break;
        }

    ft.commit();

    }

    public void hideFragment(FragmentTransaction ft){
        //如果不为空，就先隐藏起来
        if (homeFm!=null){
            ft.hide(homeFm);
        }
        if(storeFm!=null) {
            ft.hide(storeFm);
        }
        if(mapFm!=null) {
            ft.hide(mapFm);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(menu == 0 && item.getItemId() == R.id.action_add){
            startActivity(new Intent(MainActivity.this, PostActivity.class));
        }
        if(menu == 1 && item.getItemId() == R.id.action_add){
            startActivity(new Intent(MainActivity.this, AddStoreActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
