package com.prography.musicana.feature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.provider.Settings.Secure;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        Log.d("android_id", "onCreate: " + android_id);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.getNavController());


        navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.home:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle.setVisibility(View.VISIBLE);
                        binding.tvTitle2.setVisibility(View.GONE);
                        binding.tvTitle.setText("Musicana");
                        binding.imgCatagory.setVisibility(View.VISIBLE);
                        break;
                    case R.id.favorite:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("Favorite");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgCatagory.setVisibility(View.GONE);
                        break;
                    case R.id.archives:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("Archives");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgCatagory.setVisibility(View.GONE);
                        break;
                    case R.id.profile:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        binding.tvTitle2.setText("Profile");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgCatagory.setVisibility(View.GONE);
                        break;
                    case R.id.editProfileFragment:

                        binding.toolbar.setVisibility(View.GONE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        break;
                }
            }
        });

        binding.cardInclude.profileImage.setOnClickListener(viewww -> {

        });

        binding.cardInclude.colasCard.setOnClickListener(view -> binding.contenerCard.setVisibility(View.GONE));
        binding.cardInclude.imStopStart.setOnClickListener(view -> {
            Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.my_animation);
            animator.setTarget(binding.cardInclude.profileImage);
            animator.start();
        });
    }


    @Override
    public void onFragmentInteraction(int id) {
        switch (id){
            case 0:
                navController.popBackStack();
                break;
            case R.id.editProfileFragment:
                navController.navigate(R.id.editProfileFragment);
                break;
        }
    }
}