package no.hvl.dat100.jplab11.ekstra;

import no.hvl.dat100.jplab11.oppgave1.Innlegg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Du får meg ikke til å skrive sammenligne istedenfor compare
public class InnleggCompare {

    public static int compareID(Innlegg innlegg1, Innlegg innlegg2) {
        return Integer.compare(innlegg1.getId(), innlegg2.getId());
    }

    public static int compareBruker(Innlegg innlegg1, Innlegg innlegg2) {
        return CharSequence.compare(innlegg1.getBruker(), innlegg2.getBruker());
    }

    public static int compareDato(Innlegg innlegg1, Innlegg innlegg2, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate thisDato = LocalDate.parse(innlegg2.getDato(), formatter);
        LocalDate thatDato = LocalDate.parse(innlegg1.getDato(), formatter);
        return thisDato.compareTo(thatDato);
    }

    public static int compareLikes(Innlegg innlegg1, Innlegg innlegg2) {
        return Integer.compare(innlegg2.getLikes(), innlegg1.getLikes()); // Sorterer via antall likes, ingen bryr seg om innlegg med 0 likes
    }

    public static int compareInnlegg(InnleggCompareType TYPE, Innlegg innlegg1, Innlegg innlegg2, String tidsformat) {
        switch (TYPE) {
            case ID -> { // Stigende rekkefølge
                return compareID(innlegg1, innlegg2);
            }
            case BRUKER -> { // Alfabetisk rekkefølge
                return compareBruker(innlegg1, innlegg2);
            }
            case DATO -> { // Nyeste dato først
                return compareDato(innlegg1 ,innlegg2, tidsformat);
            }
            case LIKES -> { //Etter antall likes
                return compareLikes(innlegg1, innlegg2);
            }
            default -> throw new RuntimeException(TYPE + "er ikke en gyldig type, eller ikke implementert enda");
        }

    }

}
