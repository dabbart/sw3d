package com.sw3d.game;

import java.util.ArrayList;

public class TabelaBieg {
    private int numerBiegu;
    private int punktyGospodarzy;
    private int punktyGosci;
    private int punktyGosciBieg;
    private int PunktyGospodarzyBieg;
    private float najlepszyCzas;
    private ArrayList tabela;

    public TabelaBieg()
    {
        this.numerBiegu = 0;
        this.punktyGospodarzy = 0;
        this.punktyGosci = 0;
        this.najlepszyCzas = 0f;

        this.tabela = new ArrayList();
        tabela.add(new TabelaBiegIndywidualna());
        tabela.add(new TabelaBiegIndywidualna());
        tabela.add(new TabelaBiegIndywidualna());
        tabela.add(new TabelaBiegIndywidualna());
    }

    public TabelaBieg(int numerBiegu, float najlepszyCzas, ArrayList tabela) {
        this.numerBiegu = numerBiegu;
        this.najlepszyCzas = najlepszyCzas;
        this.tabela = tabela;
    }

    public TabelaBieg(int numerBiegu) {
        this.numerBiegu = numerBiegu;
        this.najlepszyCzas = 0f;
        this.punktyGosci = 0;
        this.punktyGospodarzy = 0;

        this.tabela = new ArrayList();
        tabela.add(new TabelaBiegIndywidualna());
        tabela.add(new TabelaBiegIndywidualna());
        tabela.add(new TabelaBiegIndywidualna());
        tabela.add(new TabelaBiegIndywidualna());
    }

    public ArrayList getTabela() {
        return tabela;
    }

    public void setTabela(ArrayList tabela) {
        this.tabela = tabela;
    }

    public float getNajlepszyCzas() {
        return najlepszyCzas;
    }

    public void setNajlepszyCzas(float najlepszyCzas) {
        this.najlepszyCzas = najlepszyCzas;
    }

    public int getPunktyGosci() {
        return punktyGosci;
    }

    public void setPunktyGosci(int punktyGosci) {
        this.punktyGosci = punktyGosci;
    }

    public int getPunktyGospodarzy() {
        return punktyGospodarzy;
    }

    public void setPunktyGospodarzy(int punktyGospodarzy) {
        this.punktyGospodarzy = punktyGospodarzy;
    }

    public int getNumerBiegu() {
        return numerBiegu;
    }

    public void setNumerBiegu(int numerBiegu) {
        this.numerBiegu = numerBiegu;
    }

    public int getPunktyGosciBieg() {
        return punktyGosciBieg;
    }

    public void setPunktyGosciBieg(int punktyGosciBieg) {
        this.punktyGosciBieg = punktyGosciBieg;
    }

    public int getPunktyGospodarzyBieg() {
        return PunktyGospodarzyBieg;
    }

    public void setPunktyGospodarzyBieg(int punktyGospodarzyBieg) {
        PunktyGospodarzyBieg = punktyGospodarzyBieg;
    }
}
