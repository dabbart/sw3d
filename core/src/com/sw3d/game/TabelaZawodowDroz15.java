package com.sw3d.game;

import java.util.ArrayList;

public class TabelaZawodowDroz15 {
    private TabelaTeam gospodarze;
    private TabelaTeam goscie;
    private ArrayList tabelaBiegow;

    public TabelaZawodowDroz15()
    {
        this.gospodarze = new TabelaTeam();
        this.goscie = new TabelaTeam();
        this.tabelaBiegow = new ArrayList();

        tabelaBiegow.add(new TabelaBieg(1));
        tabelaBiegow.add(new TabelaBieg(2));
        tabelaBiegow.add(new TabelaBieg(3));
        tabelaBiegow.add(new TabelaBieg(4));
        tabelaBiegow.add(new TabelaBieg(5));
        tabelaBiegow.add(new TabelaBieg(6));
        tabelaBiegow.add(new TabelaBieg(7));
        tabelaBiegow.add(new TabelaBieg(8));
        tabelaBiegow.add(new TabelaBieg(9));
        tabelaBiegow.add(new TabelaBieg(10));
        tabelaBiegow.add(new TabelaBieg(11));
        tabelaBiegow.add(new TabelaBieg(12));
        tabelaBiegow.add(new TabelaBieg(13));
        tabelaBiegow.add(new TabelaBieg(14));
        tabelaBiegow.add(new TabelaBieg(15));
    }

    public TabelaTeam getGospodarze() {
        return gospodarze;
    }

    public void setGospodarze(TabelaTeam gospodarze) {
        this.gospodarze = gospodarze;
    }

    public TabelaTeam getGoscie() {
        return goscie;
    }

    public void setGoscie(TabelaTeam goscie) {
        this.goscie = goscie;
    }

    public ArrayList getTabelaBiegow() {
        return tabelaBiegow;
    }

    public void setTabelaBiegow(ArrayList tabelaBiegow) {
        this.tabelaBiegow = tabelaBiegow;
    }
}
