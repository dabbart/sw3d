package com.sw3d.game;

public class TabelaBiegIndywidualna {
    private TabelaZawodnik zawodnik;
    private String kolorKasku;
    private boolean zmiana;
    private TabelaZawodnik zawodnikZmiana;
    private int miejsce;
    private int punkty;
    private float czas;

    public TabelaBiegIndywidualna()
    {
        new TabelaBiegIndywidualna("zielony", false, new TabelaZawodnik());
    }

    public TabelaBiegIndywidualna(String kolorKasku, boolean zmiana, TabelaZawodnik zawodnik) {
        this.kolorKasku = kolorKasku;
        this.zmiana = zmiana;
        this.zawodnik = zawodnik;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    public int getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(int miejsce) {
        this.miejsce = miejsce;
    }
    public boolean isZmiana() {
        return zmiana;
    }

    public void setZmiana(boolean zmiana) {
        this.zmiana = zmiana;
    }

    public String getKolorKasku() {
        return kolorKasku;
    }

    public void setKolorKasku(String kolorKasku) {
        this.kolorKasku = kolorKasku;
    }

    public float getCzas() {
        return czas;
    }

    public void setCzas(float czas) {
        this.czas = czas;
    }

    public TabelaZawodnik getZawodnik() {
        return zawodnik;
    }

    public void setZawodnik(TabelaZawodnik zawodnik) {
        this.zawodnik = zawodnik;
    }

    public TabelaZawodnik getZawodnikZmiana() {
        return zawodnikZmiana;
    }

    public void setZawodnikZmiana(TabelaZawodnik zawodnikZmiana) {
        this.zawodnikZmiana = zawodnikZmiana;
    }
}
