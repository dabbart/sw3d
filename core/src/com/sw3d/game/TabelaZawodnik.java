package com.sw3d.game;

public class TabelaZawodnik {
    private int numerZawodnika;
    private String imieZawodnika;
    private int[] punktyBieg;
    private int punktyRazem;

    public TabelaZawodnik(int numerZawodnika, String imieZawodnika, int[] punktyBieg, int punktyRazem) {
        this.numerZawodnika = numerZawodnika;
        this.imieZawodnika = imieZawodnika;
        this.punktyBieg = punktyBieg;
        this.punktyRazem = punktyRazem;
    }

    public TabelaZawodnik(){
        new TabelaZawodnik(8, "nameless", new int[7], 0);
    }

    public int getNumerZawodnika() {
        return numerZawodnika;
    }

    public void setNumerZawodnika(int numerZawodnika) {
        this.numerZawodnika = numerZawodnika;
    }

    public String getImieZawodnika() {
        return imieZawodnika;
    }

    public void setImieZawodnika(String imieZawodnika) {
        this.imieZawodnika = imieZawodnika;
    }

    public int[] getPunktyBieg() {
        return punktyBieg;
    }

    public void setPunktyBieg(int[] punktyBieg) {
        this.punktyBieg = punktyBieg;
    }

    public int getPunktyRazem() {
        return punktyRazem;
    }

    public void setPunktyRazem(int punktyRazem) {
        this.punktyRazem = punktyRazem;
    }
}
