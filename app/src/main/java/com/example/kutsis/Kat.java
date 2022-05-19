package com.example.kutsis;


public class Kat {
    private long id;
    private String adi;
    private int logo;

    public Kat(long id, String adi, int logo) {
        this.id = id;
        this.adi = adi;
        this.logo = logo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
