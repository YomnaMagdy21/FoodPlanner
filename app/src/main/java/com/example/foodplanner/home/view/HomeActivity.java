package com.example.foodplanner.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.favmeal.view.FavoriteFragment;
import com.example.foodplanner.plan.view.PlanFragment;
import com.example.foodplanner.search.view.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    NavController navController;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer = findViewById(R.id.drawer);

        bottomNavigationView = findViewById(R.id.bottomAppBar);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.searchFragment);

        navController = Navigation.findNavController(this, R.id.nav_home_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    navController.navigate(R.id.homeFragment);            return true;
                } else if (itemId == R.id.search) {
                   // Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.searchFragment);            return true;
                } else if (itemId == R.id.fav) {
                    navController.navigate(R.id.favoriteFragment);            return true;
                } else if (itemId == R.id.plan) {
                    navController.navigate(R.id.planFragment);            return true;
                }

                return false;
            }
        });

        HomeFragment homeFragment=new HomeFragment();
        SearchFragment searchFragment=new SearchFragment();
        FavoriteFragment favoriteFragment=new FavoriteFragment();
        PlanFragment planFragment=new PlanFragment();



    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==android.R.id.home){
//            if(drawer.isDrawerOpen(GravityCompat.START)){
//                drawer.closeDrawer(GravityCompat.START);
//            }else{
//                drawer.openDrawer(GravityCompat.START);
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }




//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//    }


}
