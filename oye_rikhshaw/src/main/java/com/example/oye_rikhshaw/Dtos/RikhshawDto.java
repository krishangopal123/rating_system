package com.example.oye_rikhshaw.Dtos;

import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;

@Entity


@Table(name = "Ratings")
public class RikhshawDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer Id;


    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Integer getTotalRatingsValue() {
        return totalRatingsValue;
    }

    public void setTotalRatingsValue(Integer totalRatingsValue) {
        this.totalRatingsValue = totalRatingsValue;
    }

    public Integer getTotalUserGivenRatings() {
        return totalUserGivenRatings;
    }

    public void setTotalUserGivenRatings(Integer totalUserGivenRatings) {
        this.totalUserGivenRatings = totalUserGivenRatings;
    }

    public Integer getId() {
        return Id;
    }
    @Column(name="telephoneNumber",unique = true,nullable = false)
    private String telephoneNumber;

    private Integer totalRatingsValue;
    private Integer totalUserGivenRatings;

}
