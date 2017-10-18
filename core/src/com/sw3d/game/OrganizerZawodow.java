package com.sw3d.game;

import com.badlogic.gdx.utils.Json;
import sun.security.ssl.Debug;

public class OrganizerZawodow{
    private TabelaZawodowDroz15 tabelaZawodow;
    private TabelaTeam goscie;
    private TabelaTeam gospodarze;
    private Json tabelaZawodowJson;

    OrganizerZawodow()
    {
       tabelaZawodow = new TabelaZawodowDroz15();
       TabelaZawodnik bob_blox = new TabelaZawodnik(8, "Bob Blox", new int[7], 0);
       this.goscie = tabelaZawodow.getGoscie();
       this.gospodarze = tabelaZawodow.getGospodarze();
       tabelaZawodowJson = new Json();
       tabelaZawodowJson.addClassTag("TabelaZawodnik", TabelaZawodnik.class);
       tabelaZawodowJson.addClassTag("TabelaBieg", TabelaBieg.class);
       tabelaZawodowJson.addClassTag("TabelaBiegIndywidualna", TabelaBiegIndywidualna.class);
       //tabelaZawodowJson.setElementType(TabelaZawodowDroz15.class, "zawodnicy", TabelaZawodnik.class);

        goscie.setNazwaDrozyny("Vildpiggarna");
        goscie.getZawodnicy();
        gospodarze.setNazwaDrozyny("TK Jaja");

       System.out.println(tabelaZawodowJson.prettyPrint(tabelaZawodow));
    }
}
