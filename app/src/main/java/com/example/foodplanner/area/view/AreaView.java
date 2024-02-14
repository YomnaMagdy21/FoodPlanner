package com.example.foodplanner.area.view;

import com.example.foodplanner.area.modelArea.Area;
import com.example.foodplanner.categories.modelC.Categories;

import java.util.List;

public interface AreaView {

    public void showCountries(List<Area> areas);
    public void showErrMsg(String error);
}
