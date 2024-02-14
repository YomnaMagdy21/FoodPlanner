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
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    NavController navController;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer = findViewById(R.id.drawer);

        bottomNavigationView = findViewById(R.id.bottomAppBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.favoriteFragment);

        navController = Navigation.findNavController(this, R.id.nav_home_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        HomeFragment homeFragment=new HomeFragment();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                drawer.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            navController.navigate(R.id.homeFragment);            return true;
        } else if (itemId == R.id.search) {
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.searchFragment);            return true;
        } else if (itemId == R.id.fav) {
            navController.navigate(R.id.favoriteFragment);            return true;
        } else if (itemId == R.id.plan) {
            navController.navigate(R.id.planFragment);            return true;
        }
        else {
            Toast.makeText(this, "can't", Toast.LENGTH_SHORT).show();

        }
        return false;
    }


}
