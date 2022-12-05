package com.nnk.springboot.domain;



import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "curveId not be null")
    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @NotNull(message = "term not be null")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "value not be null")
    @Column(name = "value")
    private Double value;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    public CurvePoint() { }

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
