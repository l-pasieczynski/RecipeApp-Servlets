package pl.coderslab.model;

public class PlanDetails {
    private int dayNameId;
    private int recipeId;
    private int recipePlanId;
    private String dayName;
    private String mealName;
    private String recipeName;
    private String recipeDescription;

    public PlanDetails(int dayNameId, int recipeId, int recipePlanId, String dayName, String mealName, String recipeName, String recipeDescription) {
        this.dayNameId = dayNameId;
        this.recipeId = recipeId;
        this.recipePlanId = recipePlanId;
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    public int getRecipePlanId() {
        return recipePlanId;
    }

    public void setRecipePlanId(int recipePlanId) {
        this.recipePlanId = recipePlanId;
    }


    public PlanDetails(int dayNameId, int recipeId, String dayName, String mealName, String recipeName, String recipeDescription) {
        this.dayNameId = dayNameId;
        this.recipeId = recipeId;
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    public PlanDetails() {
    }

    public int getDayNameId() {
        return dayNameId;
    }

    public void setDayNameId(int dayNameId) {
        this.dayNameId = dayNameId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

}
