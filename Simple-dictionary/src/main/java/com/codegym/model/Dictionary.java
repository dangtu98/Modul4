package com.codegym.model;

public class Dictionary {
    private String en;
    private String vi;

    public Dictionary(String en, String vi) {
        this.en = en;
        this.vi = vi;
    }

    public Dictionary() {
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }
}
