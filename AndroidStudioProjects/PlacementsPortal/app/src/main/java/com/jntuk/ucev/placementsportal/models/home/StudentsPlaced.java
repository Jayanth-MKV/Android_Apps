package com.jntuk.ucev.placementsportal.models.home;

public class StudentsPlaced {
    int id;
    int image;
    String name;
    String branch;
    String salary;
    String company;

    public StudentsPlaced(int id, int image, String name, String branch, String salary, String company) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.branch = branch;
        this.salary = salary;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
