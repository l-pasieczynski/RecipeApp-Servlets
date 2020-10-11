package pl.coderslab.model;

import java.time.LocalDateTime;

public class Plan {

    private int id;
    private String name;
    private String description;
    private LocalDateTime created;
    private int adminId;

    public Plan(int id, String name, String description, LocalDateTime created, int adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.adminId = adminId;
    }

    public Plan(String name, String description, LocalDateTime created, int adminId) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.adminId = adminId;
    }

    public Plan(String name) {
        this.name = name;
    }

    public Plan(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public Plan() {
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}