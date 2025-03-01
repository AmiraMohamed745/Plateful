package com.example.plateful.search.mainsearch.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenter;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenterImpl;
import com.example.plateful.view.AlertDialogMessage;
import com.example.plateful.view.DestinationNavigator;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class MainSearchScreen extends Fragment implements MainSearchScreenView {

    private ImageView imageViewProfilePhoto;

    private TextInputEditText textInputEditTextMainSearchBar;

    private TextView textViewSearchByCategory;
    private TextView textViewViewAll;

    private RecyclerView recyclerViewSearchByCategory;
    private SearchByCategoryAdapter searchByCategoryAdapter;

    private MainSearchScreenPresenter mainSearchScreenPresenter;

    private void setUpPresenter() {
        MealRemoteDataSource mealRemoteDataSource = new MealRemoteDataSourceImpl(requireContext());
        MealRepository mealRepository = MealRepositoryImpl.getInstance(mealRemoteDataSource, null);
        mainSearchScreenPresenter = new MainSearchScreenPresenterImpl(this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_search_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewProfilePhoto = view.findViewById(R.id.imageView_ic_profile_photo);
        textInputEditTextMainSearchBar = view.findViewById(R.id.textInputEditText_MainSearchBar);
        textViewSearchByCategory = view.findViewById(R.id.textView_SearchByCategory);
        textViewViewAll = view.findViewById(R.id.textView_ViewAll);

        recyclerViewSearchByCategory = view.findViewById(R.id.recyclerView_SearchByCategory);
        recyclerViewSearchByCategory.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewSearchByCategory.setLayoutManager(linearLayoutManager);

        setUpPresenter();
        setUpSearchByCategoryAdapter();
        mainSearchScreenPresenter.loadCategories();

        textViewViewAll.setOnClickListener(view1 -> {
            DestinationNavigator.navigateToViewAllCategoriesScreen(requireView());
        });
    }

    private void setUpSearchByCategoryAdapter() {
        searchByCategoryAdapter = new SearchByCategoryAdapter(requireContext());
        recyclerViewSearchByCategory.setAdapter(searchByCategoryAdapter);
    }



    @Override
    public void displayCategories(List<Category> categories) {
        searchByCategoryAdapter.setCategories(categories);
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void onDestroyView() {
        mainSearchScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}