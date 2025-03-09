package com.example.plateful.authentication.signout.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plateful.R;
import com.example.plateful.authentication.model.User;
import com.example.plateful.authentication.signout.model.MealCloudDataSourceImpl;
import com.example.plateful.authentication.signout.presenter.ProfileScreenPresenter;
import com.example.plateful.authentication.signout.presenter.ProfileScreenPresenterImpl;
import com.example.plateful.authentication.socialaccountsignin.model.GoogleSignInHelper;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.utils.DestinationNavigator;
import com.example.plateful.utils.UserSession;


public class ProfileScreen extends Fragment implements ProfileScreenView{

    // For debugging
    private static final String TAG = ProfileScreen.class.getSimpleName();

    private ProfileScreenPresenter profileScreenPresenter;

    private TextView textView_Username;
    private TextView textView_Email;

    private Button buttonSignOut;
    private Button buttonBackUpData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView_Username = view.findViewById(R.id.textView_username);
        textView_Email = view.findViewById(R.id.textView_email);

        buttonSignOut = view.findViewById(R.id.button_sign_out);
        buttonBackUpData = view.findViewById(R.id.button_back_up_data);

        String webClientId = "611080036624-fbcvsud6if4ejq93mp0438ob9u74upfd.apps.googleusercontent.com";
        GoogleSignInHelper googleSignInHelper = new GoogleSignInHelper(requireContext(), webClientId);

        profileScreenPresenter = new ProfileScreenPresenterImpl(
                this,
                googleSignInHelper,
                MealRepositoryImpl.getInstance(
                        new MealRemoteDataSourceImpl(requireContext()),
                        MealLocalDataSourceImpl.getInstance(requireContext()),
                        new MealCloudDataSourceImpl()
                ));

        profileScreenPresenter.getCurrentUserData();

        buttonSignOut.setOnClickListener(onSignOutButtonClicked -> {
            profileScreenPresenter.signOutUser();
        });

        buttonBackUpData.setOnClickListener(view1 -> {
            Log.d(TAG, "Backup data button pressed: ");
            onBackUpData();
        });
    }

    @Override
    public void onSignOut() {
        DestinationNavigator.navigateToWelcomeScreen(requireView());
    }

    @Override
    public void onBackUpData() {
        profileScreenPresenter.backUpUserFavoriteAndPlannedMeals();
    }

    @Override
    public void onBackUpDataSuccess() {
        Toast.makeText(requireContext(), getString(R.string.data_backed_up_successfully), Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayUserData(User user) {
        textView_Username.setText(user.getUsername());
        textView_Email.setText(user.getEmail());
    }

    @Override
    public void onDestroyView() {
        profileScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}

