package com.app.fabmenu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;

import com.app.fabmenu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Animation fabOpenAnimation;
    private Animation fabCloseAnimation;
    private boolean isFabMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setFabHandler(new FabHandler());

        getAnimations();


    }

    private void getAnimations() {

        fabOpenAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_open);

        fabCloseAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_close);

    }

    private void expandFabMenu() {

        ViewCompat.animate(binding.baseFloatingActionButton).rotation(45.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
        binding.createLayout.startAnimation(fabOpenAnimation);
        binding.shareLayout.startAnimation(fabOpenAnimation);
        binding.createFab.setClickable(true);
        binding.shareFab.setClickable(true);
        isFabMenuOpen = true;


    }

    private void collapseFabMenu() {

        ViewCompat.animate(binding.baseFloatingActionButton).rotation(0.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
        binding.createLayout.startAnimation(fabCloseAnimation);
        binding.shareLayout.startAnimation(fabCloseAnimation);
        binding.createFab.setClickable(false);
        binding.shareFab.setClickable(false);
        isFabMenuOpen = false;

    }


    public class FabHandler {

        public void onBaseFabClick(View view) {

            if (isFabMenuOpen)
                collapseFabMenu();
            else
                expandFabMenu();


        }

        public void onCreateFabClick(View view) {

            Snackbar.make(binding.coordinatorLayout, "Create FAB tapped", Snackbar.LENGTH_SHORT).show();

        }

        public void onShareFabClick(View view) {

            Snackbar.make(binding.coordinatorLayout, "Share FAB tapped", Snackbar.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onBackPressed() {

        if (isFabMenuOpen)
            collapseFabMenu();
        else
            super.onBackPressed();
    }
}
