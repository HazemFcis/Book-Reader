package com.example.hazem.draduation;

/**
 * Created by Hazem on 8/3/2018.
 */

public class Model {
    private String ID;
    private String Image1;
    private String Title;
    private String Year;
    private String Description;
    private String publisher;
    private Double Rate;

    public Model(String ID, String image1, String title, String year, String description, String publisher, Double ra) {
        this.ID = ID;
        Image1 = image1;
        Title = title;
        Year = year;
        Description = description;
        this.publisher = publisher;
        this.Rate = ra;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }
}
