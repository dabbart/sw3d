package com.sw3d.game;

public class TabelaTeam {
    private String nazwaDrozyny;
    private TabelaZawodnik[] zawodnicy;
    private boolean isTeamGospodarze;

    public TabelaTeam(String nazwaDrozyny, TabelaZawodnik[] zawodnicy, boolean isTeamGospodarze) {
        this.nazwaDrozyny = nazwaDrozyny;
        this.zawodnicy = zawodnicy;
        this.isTeamGospodarze = isTeamGospodarze;
    }

    public TabelaTeam()
    {
        new TabelaTeam("nazwa drozyny", new TabelaZawodnik[7], false);
    }

    public TabelaTeam(boolean isTeamGospodarze)
    {
        new TabelaTeam("nazwa drozyny", new TabelaZawodnik[7], isTeamGospodarze);
    }

    public String getNazwaDrozyny() {
        return nazwaDrozyny;
    }

    public void setNazwaDrozyny(String nazwaDrozyny) {
        this.nazwaDrozyny = nazwaDrozyny;
    }

    public TabelaZawodnik[] getZawodnicy() {
        return zawodnicy;
    }

    public void setZawodnicy(TabelaZawodnik[] zawodnicy) {
        this.zawodnicy = zawodnicy;
    }

    public boolean isTeamGospodarze() {
        return isTeamGospodarze;
    }

    public void setTeamGospodarze(boolean teamGospodarze) {
        isTeamGospodarze = teamGospodarze;
    }
}
