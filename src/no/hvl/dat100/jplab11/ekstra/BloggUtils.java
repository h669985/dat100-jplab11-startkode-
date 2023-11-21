package no.hvl.dat100.jplab11.ekstra;

import no.hvl.dat100.jplab11.oppgave1.Innlegg;

// Har valgt å gjøre litt ekstraarbeid for morrskyld så her er diverse metoder for å manipulere bloggsamlinger
public class BloggUtils {

    public static boolean erSquished(Innlegg[] samling) {
        for(int i = 0; i < samling.length-1; i++) {
            if(samling[i] == null && samling[i+1] != null) {
                return false;
            }
        }
        return true;
    }

    public static void squish(Innlegg[] samling) {
        while(!erSquished(samling)) {
            for (int i = 0; i < samling.length-1; i++) {
                if (samling[i] == null) {
                    samling[i] = samling[i+1];
                    samling[i+1] = null;
                }
            }
        }
    }

    public static boolean erSortert(InnleggCompareType TYPE, Innlegg[] samling, String tidsformat) {
        for(int i = 0; i < samling.length-1; i++) {
            if(samling[i+1] != null) {
                if(InnleggCompare.compareInnlegg(TYPE, samling[i], samling[i+1], tidsformat) > 0) {
                    return false;
                }
            }
        }
        return true;
    }


    /*
    Man kan velge hvordan innleggene skal sorteres ut ifra ID, Dato, og likes. Evt bruker i fremtiden
    Bryr meg ikke om å ha en god sort for nå, vet at denne er ikke optimal for større data
    */
    public static void sorter(InnleggCompareType TYPE, Innlegg[] samling, String tidsformat) {
        while (!erSortert(TYPE, samling, tidsformat)) {
            for(int i = 0; i < samling.length-1; i++) {
                if(samling[i+1] != null) { //Foretrekker å neste denne istedenfor å smekke sammen med neste if, siden denne må evalueres først
                    if((InnleggCompare.compareInnlegg(TYPE, samling[i], samling[i+1], tidsformat) > 0)) {
                        Innlegg temp = samling[i];
                        samling[i] = samling[i+1];
                        samling[i+1] = temp;
                    }
                }
            }
        }
    }
}
