package com.sw3d.game;

public class TabelaZawodowDroz15 {
    private TabelaTeam gospodarze;
    private TabelaTeam goscie;
    private TabelaBieg[] tabelaBiegow;

    public TabelaZawodowDroz15()
    {
        this.gospodarze = new TabelaTeam();
        this.goscie = new TabelaTeam();
        this.tabelaBiegow = new TabelaBieg[15];
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

    public TabelaBieg[] getTabelaBiegow() {
        return tabelaBiegow;
    }

    public void setTabelaBiegow(TabelaBieg[] tabelaBiegow) {
        this.tabelaBiegow = tabelaBiegow;
    }
}
