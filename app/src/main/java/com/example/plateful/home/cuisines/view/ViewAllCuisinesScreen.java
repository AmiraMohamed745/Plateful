package com.example.plateful.home.cuisines.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.home.cuisines.presenter.ViewAllCuisinesPresenter;
import com.example.plateful.home.cuisines.presenter.ViewAllCuisinesPresenterImpl;
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.utils.AlertDialogMessage;

import java.util.List;


public class ViewAllCuisinesScreen extends Fragment implements ViewAllCuisinesScreenView {

    private ImageView imageView_BackArrow;

    private TextView textViewBrowseAllCuisines;

    private RecyclerView recyclerViewViewAllCuisines;
    private ViewAllCuisinesAdapter viewAllCuisinesAdapter;

    private ViewAllCuisinesPresenter viewAllCuisinesPresenter;


    private void setUpPresenter() {
        MealRepository mealRepository = MealRepositoryImpl.getInstance(new MealRemoteDataSourceImpl(requireContext()), null);
        viewAllCuisinesPresenter = new ViewAllCuisinesPresenterImpl(this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_all_cuisines_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView_BackArrow = view.findViewById(R.id.imageView_BackArrow);

        textViewBrowseAllCuisines = view.findViewById(R.id.textView_Browse_All_Cuisines);

        recyclerViewViewAllCuisines = view.findViewById(R.id.recyclerView_ViewAllCuisines);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewViewAllCuisines.setLayoutManager(gridLayoutManager);

        setUpPresenter();
        setUpViewAllCuisinesAdapter();
        viewAllCuisinesPresenter.loadCuisines();
    }

    private void setUpViewAllCuisinesAdapter() {
        viewAllCuisinesAdapter = new ViewAllCuisinesAdapter(requireContext());
        recyclerViewViewAllCuisines.setAdapter(viewAllCuisinesAdapter);
    }

    @Override
    public void displayCuisines(List<Cuisine> cuisines) {
        viewAllCuisinesAdapter.setCuisinesList(cuisines);
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void onDestroyView() {
        viewAllCuisinesPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}