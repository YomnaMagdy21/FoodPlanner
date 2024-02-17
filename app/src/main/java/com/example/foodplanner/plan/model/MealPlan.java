package com.example.foodplanner.plan.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodplanner.model.Meal;

@Entity(tableName = "plans_table")
public class MealPlan {

    @PrimaryKey(autoGenerate = true)
    int id;
    Meal meal;
    String day;
    String strMeal;
    String strMealThumb;
    String idMeal;

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
