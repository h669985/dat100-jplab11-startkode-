package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;

public class Blogg {

    Innlegg[] innlegg;


    public Blogg() {
        this.innlegg = new Innlegg[20];

    }

    public Blogg(int lengde) {
        this.innlegg = new Innlegg[lengde];
    }

    public int getAntall() {
        int antall = 0;
        for (int i = 0; i < this.innlegg.length; i++) {
            if (this.innlegg[i] != null) {
                antall++;
            }
        }
        return antall;
    }

    public Innlegg[] getSamling() {
        return this.innlegg;

    }

    public int finnInnlegg(Innlegg innlegg) {
        if(this.getAntall()!=0){
            for (int i = 0; i < this.getAntall(); i++) {
                if (innlegg.erLik(this.innlegg[i])) {
                    return i;
                }
            }}return -1;

    }

    public boolean finnes(Innlegg innlegg) {
        if(this.getAntall()!=0){
        for (int i = 0; i < this.getAntall(); i++) {
            if (innlegg.getId() == this.innlegg[i].getId()) {
                return true;
            }
        }	}return false;
    }

    public boolean ledigPlass() {
        if(this.innlegg[this.innlegg.length-1]==null){
			return true;

		}
return false;
    }

    public boolean leggTil(Innlegg innlegg) {
        if (this.getAntall() < this.innlegg.length && !this.finnes(innlegg)) {
            this.innlegg[this.getAntall()] = innlegg;
            return true;
        }
        return false;
    }

    public String toString() {
        String value = "";
        value += getAntall() +"\n";

        for(int i = 0; i < getAntall(); i++){
            value += this.innlegg[i].toString() ;
        }
               return value;
    }

    // valgfrie oppgaver nedenfor

    public void utvid() {
        throw new UnsupportedOperationException(TODO.method());
    }

    public boolean leggTilUtvid(Innlegg innlegg) {

        throw new UnsupportedOperationException(TODO.method());

    }

    public boolean slett(Innlegg innlegg) {

        throw new UnsupportedOperationException(TODO.method());
    }

    public int[] search(String keyword) {

        throw new UnsupportedOperationException(TODO.method());

    }
}