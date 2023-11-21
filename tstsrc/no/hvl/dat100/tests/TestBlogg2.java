package no.hvl.dat100.tests;

import no.hvl.dat100.jplab11.oppgave2.Bilde;
import no.hvl.dat100.jplab11.oppgave2.Tekst;
import no.hvl.dat100.jplab11.oppgave3.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// Har laget egne tester for morroskyld og for å kunne være mer sikker på at metodene er korrekte
public class TestBlogg2 {

    @Test
    public void testSearch() {
        Blogg samling = new Blogg(2);

        Tekst innlegg1 = new Tekst(1,"Ole Olsen","23-10","en tekst");
        Bilde innlegg2 = new Bilde(2,"Oline Olsen","24-10","et bilde","http://www.picture.com/oo.jpg");

        samling.leggTil(innlegg1);
        samling.leggTil(innlegg2);

        assertArrayEquals(new int[]{1}, samling.search("Ole"));
        assertArrayEquals(new int[]{1, 2}, samling.search("Ol"));
        assertArrayEquals(new int[]{2}, samling.search("et bilde"));
        assertArrayEquals(new int[]{}, samling.search("lommemannen"));
    }

    @Test
    public void testleggTilUtvid() {
        Blogg samling = new Blogg(1);

        Tekst innlegg1 = new Tekst(1,"Ole Olsen","23-10","en tekst");
        Bilde innlegg2 = new Bilde(2,"Oline Olsen","24-10","et bilde","http://www.picture.com/oo.jpg");

        assertTrue(samling.leggTilUtvid(innlegg1));
        assertFalse(samling.leggTilUtvid(innlegg1));
        assertEquals(1, samling.getAntall());

        assertTrue(samling.leggTilUtvid(innlegg2));
        assertFalse(samling.leggTilUtvid(innlegg2));
        assertEquals(2, samling.getAntall());
    }

}
