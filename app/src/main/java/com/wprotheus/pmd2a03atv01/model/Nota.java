package com.wprotheus.pmd2a03atv01.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Nota implements Serializable {
    private final static long serialVersionUID = -9222788865381905174L;
    @SerializedName("n1")
    @Expose
    private int n1;
    @SerializedName("n2")
    @Expose
    private int n2;
    @SerializedName("n3")
    @Expose
    private int n3;

    public Nota(int n1, int n2, int n3) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getN3() {
        return n3;
    }

    public void setN3(int n3) {
        this.n3 = n3;
    }
}