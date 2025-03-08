package com.example.plateful.splashscreen.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.plateful.R;
import com.example.plateful.utils.DestinationNavigator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashScreen extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            DestinationNavigator.navigateToHomeScreen(requireView());
        } else {
            setUpLottieAnimation(view);
        }
    }

    private void setUpLottieAnimation(View view) {
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        NavOptions navOptions = new NavOptions.Builder()
                .setEnterAnim(R.anim.fade_in_from_splash_screen)
                .setExitAnim(R.anim.fade_out_from_splash_screen)
                .setPopUpTo(R.id.splashScreen, true)
                .build();
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                DestinationNavigator.navigateToWelcomeScreenWithAnimation(requireView(), navOptions);
            }
        });
    }

}