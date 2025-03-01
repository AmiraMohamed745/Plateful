package com.example.plateful.search.category.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse {


    @SerializedName("categories")
    private List<Category> categories;


    public CategoryResponse() {
    }

    public CategoryResponse(List<Category> categories) {
        super();
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
