package com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Cake")
public class Cake implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long cakeId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "image_url", unique = false, nullable = false)
    private String image;

    public Cake(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Cake() {

    }

    @JsonProperty("id")
    public Long getCakeId() {
        return cakeId;
    }

    public void setCakeId(Long cakeId) {
        this.cakeId = cakeId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("desc")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}