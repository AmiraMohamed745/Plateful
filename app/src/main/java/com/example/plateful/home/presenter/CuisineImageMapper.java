package com.example.plateful.home.presenter;

import com.example.plateful.R;

import java.util.HashMap;
import java.util.Map;

public class CuisineImageMapper {

    private static final Map<String, Integer> CUISINE_IMAGE_MAP = new HashMap<>();

    static {
        CUISINE_IMAGE_MAP.put("american", R.drawable.flag_american);
        CUISINE_IMAGE_MAP.put("british", R.drawable.flag_british);
        CUISINE_IMAGE_MAP.put("canadian", R.drawable.flag_candian);
        CUISINE_IMAGE_MAP.put("chinese", R.drawable.flag_chinese);
        CUISINE_IMAGE_MAP.put("croatian", R.drawable.flag_croatian);
        CUISINE_IMAGE_MAP.put("dutch", R.drawable.flag_dutch);
        CUISINE_IMAGE_MAP.put("egyptian", R.drawable.flag_egyptian);
        CUISINE_IMAGE_MAP.put("filipino", R.drawable.flag_flippino);
        CUISINE_IMAGE_MAP.put("french", R.drawable.flag_french);
        CUISINE_IMAGE_MAP.put("greek", R.drawable.flag_greek);
        CUISINE_IMAGE_MAP.put("indian", R.drawable.flag_indian);
        CUISINE_IMAGE_MAP.put("irish", R.drawable.flag_irish);
        CUISINE_IMAGE_MAP.put("italian", R.drawable.flag_italian);
        CUISINE_IMAGE_MAP.put("jamaican", R.drawable.flag_jamican);
        CUISINE_IMAGE_MAP.put("japanese", R.drawable.flag_japanese);
        CUISINE_IMAGE_MAP.put("kenyan", R.drawable.flag_kenyan);
        CUISINE_IMAGE_MAP.put("malaysian", R.drawable.flag_malaysian);
        CUISINE_IMAGE_MAP.put("mexican", R.drawable.flag_mexican);
        CUISINE_IMAGE_MAP.put("moroccan", R.drawable.flag_moroccon);
        CUISINE_IMAGE_MAP.put("polish", R.drawable.flag_polish);
        CUISINE_IMAGE_MAP.put("portuguese", R.drawable.flag_portuguese);
        CUISINE_IMAGE_MAP.put("russian", R.drawable.flag_russian);
        CUISINE_IMAGE_MAP.put("spanish", R.drawable.flag_spanish);
        CUISINE_IMAGE_MAP.put("thai", R.drawable.flag_thai);
        CUISINE_IMAGE_MAP.put("tunisian", R.drawable.flag_tunisian);
        CUISINE_IMAGE_MAP.put("turkish", R.drawable.flag_turkish);
        CUISINE_IMAGE_MAP.put("ukrainian", R.drawable.flag_ukrainian);
        CUISINE_IMAGE_MAP.put("uruguayan", R.drawable.flag_uruguayan);
        CUISINE_IMAGE_MAP.put("vietnamese", R.drawable.flag_vietnamese);
    }

    public static int getImageForCuisine(String cuisineName) {
        if (cuisineName == null) {
            return R.drawable.bg_placeholder;
        }
        Integer imageRes = CUISINE_IMAGE_MAP.get(cuisineName.toLowerCase());
        return (imageRes != null) ? imageRes : R.drawable.bg_placeholder;
    }

}
