package com.brandongossen.bodg.clientmanager.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;


@Entity
@Table(name = "healthinfos")
public class HealthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private short heartRate;

    @Column(nullable = false)
    private String cardioFitness;

    @Column(nullable = false)
    private String bodyComposition;

    @Column(nullable = false)
    private String muscularStrength;

    @Column(nullable = false)
    private String muscularEndurance;

    @Column(nullable = false)
    private String flexibility;

    @ManyToOne
    @JsonManagedReference
    private User user;

    public HealthInfo() {
    }

    public HealthInfo(long id, short heartRate, String cardioFitness, String bodyComposition, String muscularStrength, String muscularEndurance, String flexibility, User user) {
        this.id = id;
        this.heartRate = heartRate;
        this.cardioFitness = cardioFitness;
        this.bodyComposition = bodyComposition;
        this.muscularStrength = muscularStrength;
        this.muscularEndurance = muscularEndurance;
        this.flexibility = flexibility;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(short heartRate) {
        this.heartRate = heartRate;
    }

    public String getCardioFitness() {
        return cardioFitness;
    }

    public void setCardioFitness(String cardioFitness) {
        this.cardioFitness = cardioFitness;
    }

    public String getBodyComposition() {
        return bodyComposition;
    }

    public void setBodyComposition(String bodyComposition) {
        this.bodyComposition = bodyComposition;
    }

    public String getMuscularStrength() {
        return muscularStrength;
    }

    public void setMuscularStrength(String muscularStrength) {
        this.muscularStrength = muscularStrength;
    }

    public String getMuscularEndurance() {
        return muscularEndurance;
    }

    public void setMuscularEndurance(String muscularEndurance) {
        this.muscularEndurance = muscularEndurance;
    }

    public String getFlexibility() {
        return flexibility;
    }

    public void setFlexibility(String flexibility) {
        this.flexibility = flexibility;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}