package com.example.plateful.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "meal_table")
public class Meal {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("idMeal")
    private String idMeal;

    @ColumnInfo(name = "name")
    @SerializedName("strMeal")
    private String name;

    @ColumnInfo(name = "category")
    @SerializedName("strCategory")
    private String category;

    @ColumnInfo(name = "cuisine")
    @SerializedName("strArea")
    private String cuisine;

    @ColumnInfo(name = "instructions")
    @SerializedName("strInstructions")
    private String instructions;

    @ColumnInfo(name = "image")
    @SerializedName("strMealThumb")
    private String imageUrl;


    @ColumnInfo(name = "video")
    @SerializedName("strYoutube")
    private String videoUrl;

    @ColumnInfo(name = "ingredient_1")
    @SerializedName("strIngredient1")
    private String strIngredient1;

    @ColumnInfo(name = "ingredient_2")
    @SerializedName("strIngredient2")
    private String strIngredient2;

    @ColumnInfo(name = "ingredient_3")
    @SerializedName("strIngredient3")
    private String strIngredient3;

    @ColumnInfo(name = "ingredient_4")
    @SerializedName("strIngredient4")
    private String strIngredient4;

    @ColumnInfo(name = "ingredient_5")
    @SerializedName("strIngredient5")
    private String strIngredient5;

    @ColumnInfo(name = "ingredient_6")
    @SerializedName("strIngredient6")
    private String strIngredient6;

    @ColumnInfo(name = "ingredient_7")
    @SerializedName("strIngredient7")
    private String strIngredient7;

    @ColumnInfo(name = "ingredient_8")
    @SerializedName("strIngredient8")
    private String strIngredient8;

    @ColumnInfo(name = "ingredient_9")
    @SerializedName("strIngredient9")
    private String strIngredient9;

    @ColumnInfo(name = "ingredient_10")
    @SerializedName("strIngredient10")
    private String strIngredient10;

    @ColumnInfo(name = "ingredient_11")
    @SerializedName("strIngredient11")
    private String strIngredient11;

    @ColumnInfo(name = "ingredient_12")
    @SerializedName("strIngredient12")
    private String strIngredient12;

    @ColumnInfo(name = "ingredient_13")
    @SerializedName("strIngredient13")
    private String strIngredient13;

    @ColumnInfo(name = "ingredient_14")
    @SerializedName("strIngredient14")
    private String strIngredient14;

    @ColumnInfo(name = "ingredient_15")
    @SerializedName("strIngredient15")
    private String strIngredient15;

    @ColumnInfo(name = "ingredient_16")
    @SerializedName("strIngredient16")
    private Object strIngredient16;

    @ColumnInfo(name = "ingredient_17")
    @SerializedName("strIngredient17")
    private Object strIngredient17;

    @ColumnInfo(name = "ingredient_18")
    @SerializedName("strIngredient18")
    private Object strIngredient18;

    @ColumnInfo(name = "ingredient_19")
    @SerializedName("strIngredient19")
    private Object strIngredient19;

    @ColumnInfo(name = "ingredient_20")
    @SerializedName("strIngredient20")
    private Object strIngredient20;

    @ColumnInfo(name = "measure_1")
    @SerializedName("strMeasure1")
    private String strMeasure1;

    @ColumnInfo(name = "measure_2")
    @SerializedName("strMeasure2")
    private String strMeasure2;

    @ColumnInfo(name = "measure_3")
    @SerializedName("strMeasure3")
    private String strMeasure3;

    @ColumnInfo(name = "measure_4")
    @SerializedName("strMeasure4")
    private String strMeasure4;

    @ColumnInfo(name = "measure_5")
    @SerializedName("strMeasure5")
    private String strMeasure5;

    @ColumnInfo(name = "measure_6")
    @SerializedName("strMeasure6")
    private String strMeasure6;

    @ColumnInfo(name = "measure_7")
    @SerializedName("strMeasure7")
    private String strMeasure7;

    @ColumnInfo(name = "measure_8")
    @SerializedName("strMeasure8")
    private String strMeasure8;

    @ColumnInfo(name = "measure_9")
    @SerializedName("strMeasure9")
    private String strMeasure9;

    @ColumnInfo(name = "measure_10")
    @SerializedName("strMeasure10")
    private String strMeasure10;

    @ColumnInfo(name = "measure_11")
    @SerializedName("strMeasure11")
    private String strMeasure11;

    @ColumnInfo(name = "measure_12")
    @SerializedName("strMeasure12")
    private String strMeasure12;

    @ColumnInfo(name = "measure_13")
    @SerializedName("strMeasure13")
    private String strMeasure13;

    @ColumnInfo(name = "measure_14")
    @SerializedName("strMeasure14")
    private String strMeasure14;

    @ColumnInfo(name = "measure_15")
    @SerializedName("strMeasure15")
    private String strMeasure15;

    @ColumnInfo(name = "measure_16")
    @SerializedName("strMeasure16")
    private Object strMeasure16;

    @ColumnInfo(name = "measure_17")
    @SerializedName("strMeasure17")
    private Object strMeasure17;

    @ColumnInfo(name = "measure_18")
    @SerializedName("strMeasure18")
    private Object strMeasure18;

    @ColumnInfo(name = "measure_19")
    @SerializedName("strMeasure19")
    private Object strMeasure19;

    @ColumnInfo(name = "measure_20")
    @SerializedName("strMeasure20")
    private Object strMeasure20;

    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;

    public Meal() {
    }

    public Meal(String idMeal, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube, String strIngredient1, String strIngredient2, String strIngredient3, String strIngredient4, String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8, String strIngredient9, String strIngredient10, String strIngredient11, String strIngredient12, String strIngredient13, String strIngredient14, String strIngredient15, Object strIngredient16, Object strIngredient17, Object strIngredient18, Object strIngredient19, Object strIngredient20, String strMeasure1, String strMeasure2, String strMeasure3, String strMeasure4, String strMeasure5, String strMeasure6, String strMeasure7, String strMeasure8, String strMeasure9, String strMeasure10, String strMeasure11, String strMeasure12, String strMeasure13, String strMeasure14, String strMeasure15, Object strMeasure16, Object strMeasure17, Object strMeasure18, Object strMeasure19, Object strMeasure20, boolean isFavorite) {
        super();
        this.idMeal = idMeal;
        this.name = strMeal;
        this.category = strCategory;
        this.cuisine = strArea;
        this.instructions = strInstructions;
        this.imageUrl = strMealThumb;
        this.videoUrl = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strMeasure16 = strMeasure16;
        this.strMeasure17 = strMeasure17;
        this.strMeasure18 = strMeasure18;
        this.strMeasure19 = strMeasure19;
        this.strMeasure20 = strMeasure20;
        this.isFavorite = isFavorite;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public Object getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(Object strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public Object getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(Object strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public Object getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(Object strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public Object getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(Object strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public Object getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(Object strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public Object getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(Object strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public Object getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(Object strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public Object getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(Object strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public Object getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(Object strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public Object getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(Object strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @NonNull
    @Override
    public String toString() {
        return "Meal{" +
                "idMeal='" + idMeal + '\'' +
                ", strMeal='" + name + '\'' +
                ", strCategory='" + category + '\'' +
                ", strArea='" + cuisine + '\'' +
                ", strInstructions='" + instructions + '\'' +
                ", strMealThumb='" + imageUrl + '\'' +
                ", strYoutube='" + videoUrl + '\'' +
                ", strIngredient1='" + strIngredient1 + '\'' +
                ", strIngredient2='" + strIngredient2 + '\'' +
                ", strIngredient3='" + strIngredient3 + '\'' +
                ", strIngredient4='" + strIngredient4 + '\'' +
                ", strIngredient5='" + strIngredient5 + '\'' +
                ", strIngredient6='" + strIngredient6 + '\'' +
                ", strIngredient7='" + strIngredient7 + '\'' +
                ", strIngredient8='" + strIngredient8 + '\'' +
                ", strIngredient9='" + strIngredient9 + '\'' +
                ", strIngredient10='" + strIngredient10 + '\'' +
                ", strIngredient11='" + strIngredient11 + '\'' +
                ", strIngredient12='" + strIngredient12 + '\'' +
                ", strIngredient13='" + strIngredient13 + '\'' +
                ", strIngredient14='" + strIngredient14 + '\'' +
                ", strIngredient15='" + strIngredient15 + '\'' +
                ", strIngredient16=" + strIngredient16 +
                ", strIngredient17=" + strIngredient17 +
                ", strIngredient18=" + strIngredient18 +
                ", strIngredient19=" + strIngredient19 +
                ", strIngredient20=" + strIngredient20 +
                ", strMeasure1='" + strMeasure1 + '\'' +
                ", strMeasure2='" + strMeasure2 + '\'' +
                ", strMeasure3='" + strMeasure3 + '\'' +
                ", strMeasure4='" + strMeasure4 + '\'' +
                ", strMeasure5='" + strMeasure5 + '\'' +
                ", strMeasure6='" + strMeasure6 + '\'' +
                ", strMeasure7='" + strMeasure7 + '\'' +
                ", strMeasure8='" + strMeasure8 + '\'' +
                ", strMeasure9='" + strMeasure9 + '\'' +
                ", strMeasure10='" + strMeasure10 + '\'' +
                ", strMeasure11='" + strMeasure11 + '\'' +
                ", strMeasure12='" + strMeasure12 + '\'' +
                ", strMeasure13='" + strMeasure13 + '\'' +
                ", strMeasure14='" + strMeasure14 + '\'' +
                ", strMeasure15='" + strMeasure15 + '\'' +
                ", strMeasure16=" + strMeasure16 +
                ", strMeasure17=" + strMeasure17 +
                ", strMeasure18=" + strMeasure18 +
                ", strMeasure19=" + strMeasure19 +
                ", strMeasure20=" + strMeasure20 +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
