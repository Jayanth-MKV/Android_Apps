package com.jntuk.ucev.placementsportal.models.jobs;

public class job {

    int id;
    String jobName;
    String company;
    int image;
    String Link;
    String Description;
    String Tag;

    public job(int id, String jobName, String company, int image, String link, String description, String tag) {
        this.id = id;
        this.jobName = jobName;
        this.company = company;
        this.image = image;
        Link = link;
        Description = description;
        Tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }
}
