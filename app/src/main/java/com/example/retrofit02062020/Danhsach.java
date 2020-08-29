package com.example.retrofit02062020;

public class Danhsach {
    /**
     * khoahoc : Lập trình Android
     */
    private String khoahoc;

    public String getKhoahoc() {
        return khoahoc;
    }

    public void setKhoahoc(String khoahoc) {
        this.khoahoc = khoahoc;
    }

    @Override
    public String toString() {
        return "Danhsach{" +
                "khoahoc='" + khoahoc + '\'' +
                '}';
    }
}