package com.example.plateful.search.category.view;

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
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenter;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenterImpl;
import com.example.plateful.search.mainsearch.view.MainSearchScreenView;
import com.example.plateful.view.AlertDialogMessage;

import java.util.List;


public class ViewAllCategoriesScreen extends Fragment implements MainSearchScreenView {

    private ImageView imageView_BackArrow;

    private TextView textViewSearchByCategory;

    private RecyclerView recyclerViewViewAllCategories; // GridLayout
    private ViewAllCategoriesAdapter viewAllCategoriesAdapter;

    private MainSearchScreenPresenter mainSearchScreenPresenter;

    private void setUpPresenter() {
        MealRemoteDataSource mealRemoteDataSource = new MealRemoteDataSourceImpl(requireContext());
        MealRepository mealRepository = MealRepositoryImpl.getInstance(mealRemoteDataSource, null);
        mainSearchScreenPresenter = new MainSearchScreenPresenterImpl((MainSearchScreenView) this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_all_categories_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView_BackArrow = view.findViewById(R.id.imageView_BackArrow);

        textViewSearchByCategory = view.findViewById(R.id.cardView_SearchByCategory);

        recyclerViewViewAllCategories = view.findViewById(R.id.recyclerView_ViewAllCategories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewViewAllCategories.setLayoutManager(gridLayoutManager);

        setUpPresenter();
        setUpViewAllCategoriesAdapter();
        mainSearchScreenPresenter.loadCategories();

    }

    private void setUpViewAllCategoriesAdapter() {
        viewAllCategoriesAdapter = new ViewAllCategoriesAdapter(requireContext());
        recyclerViewViewAllCategories.setAdapter(viewAllCategoriesAdapter);
    }


    @Override
    public void displayCategories(List<Category> categories) {
        viewAllCategoriesAdapter.setCategories(categories);
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