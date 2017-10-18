package com.sw3d.game;

import java.util.ArrayList;

public class TabelaTeam {
    private String nazwaDrozyny;
    private ArrayList zawodnicy;
    private boolean isTeamGospodarze;

    public TabelaTeam(String nazwaDrozyny, ArrayList zawodnicy, boolean isTeamGospodarze) {
        this.nazwaDrozyny = nazwaDrozyny;
        this.zawodnicy = zawodnicy;
        this.isTeamGospodarze = isTeamGospodarze;
    }

    public TabelaTeam()
    {
       this.nazwaDrozyny = "nazwa drozyny";
       this.zawodnicy = new ArrayList();
       zawodnicy.add(new TabelaZawodnik());
       zawodnicy.add(new TabelaZawodnik());
       zawodnicy.add(new TabelaZawodnik());
       zawodnicy.add(new TabelaZawodnik());
       zawodnicy.add(new TabelaZawodnik());
       zawodnicy.add(new TabelaZawodnik());
       zawodnicy.add(new TabelaZawodnik());
       this.isTeamGospodarze = true;
    }

    public TabelaTeam(boolean isTeamGospodarze)
    {
        this.nazwaDrozyny = "nazwa drozyny";
        this.zawodnicy = new ArrayList();
        zawodnicy.add(new TabelaZawodnik());
        zawodnicy.add(new TabelaZawodnik());
        zawodnicy.add(new TabelaZawodnik());
        zawodnicy.add(new TabelaZawodnik());
        zawodnicy.add(new TabelaZawodnik());
        zawodnicy.add(new TabelaZawodnik());
        zawodnicy.add(new TabelaZawodnik());
        this.isTeamGospodarze = isTeamGospodarze;
    }

    public String getNazwaDrozyny() {
        return nazwaDrozyny;
    }

    public void setNazwaDrozyny(String nazwaDrozyny) {
        this.nazwaDrozyny = nazwaDrozyny;
    }

    public ArrayList getZawodnicy() {
        return zawodnicy;
    }

    public void setZawodnicy(ArrayList zawodnicy) {
        this.zawodnicy = zawodnicy;
    }

    public boolean isTeamGospodarze() {
        return isTeamGospodarze;
    }

    public void setTeamGospodarze(boolean teamGospodarze) {
        isTeamGospodarze = teamGospodarze;
    }
}
