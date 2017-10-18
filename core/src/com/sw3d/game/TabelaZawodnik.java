package com.sw3d.game;

public class TabelaZawodnik {
    private int numerZawodnika;
    private String imieZawodnika;
    private int punktyBieg1;
    private int punktyBieg2;
    private int punktyBieg3;
    private int punktyBieg4;
    private int punktyBieg5;
    private int punktyBieg6;
    private int punktyBieg7;
    private int punktyRazem;

    public TabelaZawodnik(int numerZawodnika, String imieZawodnika, int[] punktyBieg, int punktyRazem) {
        this.numerZawodnika = numerZawodnika;
        this.imieZawodnika = imieZawodnika;

        this.punktyBieg1 = punktyBieg[0];
        this.punktyBieg2 = punktyBieg[1];
        this.punktyBieg3 = punktyBieg[2];
        this.punktyBieg4 = punktyBieg[3];
        this.punktyBieg5 = punktyBieg[4];
        this.punktyBieg6 = punktyBieg[5];
        this.punktyBieg7 = punktyBieg[6];
        this.punktyRazem = punktyRazem;
    }

    public TabelaZawodnik(){
        new TabelaZawodnik(8, "The Nameless One", new int[7], 0);
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
        int[] punktyBieg = new int[7];
        punktyBieg[0] = punktyBieg1;
        punktyBieg[1] = punktyBieg2;
        punktyBieg[2] = punktyBieg3;
        punktyBieg[3] = punktyBieg4;
        punktyBieg[4] = punktyBieg5;
        punktyBieg[5] = punktyBieg6;
        punktyBieg[6] = punktyBieg7;
        return punktyBieg;
    }

    public void setPunktyBieg(int[] punktyBieg) {
        this.punktyBieg1 = punktyBieg[0];
        this.punktyBieg2 = punktyBieg[1];
        this.punktyBieg3 = punktyBieg[2];
        this.punktyBieg4 = punktyBieg[3];
        this.punktyBieg5 = punktyBieg[4];
        this.punktyBieg6 = punktyBieg[5];
        this.punktyBieg7 = punktyBieg[6];
    }

    public int getPunktyRazem() {
        return punktyRazem;
    }

    public void setPunktyRazem(int punktyRazem) {
        this.punktyRazem = punktyRazem;
    }
}
