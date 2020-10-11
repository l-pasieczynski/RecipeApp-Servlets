package pl.coderslab.model;

public class AddPlan {

    private int id;
    private int recipeId;
    private int displayOrder;
    private int dayNameId;
    private int planId;
    private String mealName;

    public AddPlan() {
    }

    public AddPlan(int recipeId, int displayOrder, int dayNameId, int planId, String mealName) {
        this.recipeId = recipeId;
        this.displayOrder = displayOrder;
        this.dayNameId = dayNameId;
        this.planId = planId;
        this.mealName = mealName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getDayNameId() {
        return dayNameId;
    }

    public void setDayNameId(int dayNameId) {
        this.dayNameId = dayNameId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
