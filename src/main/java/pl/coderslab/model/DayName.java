package pl.coderslab.model;

public class DayName {

    private int id;
    private String name;
    private int displayOrder;

    public DayName() {
    }

    public DayName(String name, int displayOrder) {
        this.name = name;
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {
        return "Day [id=" + id + ", name=" + name + ", display order=" + displayOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }


}