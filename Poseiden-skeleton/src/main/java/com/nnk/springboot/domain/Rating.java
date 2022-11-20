package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "moodysRating not be null")
    @Column(name = "moodys_rating")
    private String moodysRating;

    @NotNull(message = "sandPrating not be null")
    @Column(name = "sand_prating")
    private String sandPrating;

    @NotNull(message = "fitchRating not be null")
    @Column(name = "fitch_rating")
    private String fitchRating;

    @Column(name = "order_number")
    private Integer orderNumber;

    public Rating() {}

    public Rating(String moodysRating, String sandPrating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPrating = moodysRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPrating() {
        return sandPrating;
    }

    public void setSandPrating(String sandPrating) {
        this.sandPrating = sandPrating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
