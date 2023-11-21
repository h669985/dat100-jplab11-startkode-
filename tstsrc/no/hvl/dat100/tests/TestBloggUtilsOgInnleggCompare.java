package no.hvl.dat100.tests;

import no.hvl.dat100.jplab11.ekstra.InnleggCompareType;
import no.hvl.dat100.jplab11.oppgave1.Innlegg;
import no.hvl.dat100.jplab11.oppgave2.Bilde;
import no.hvl.dat100.jplab11.oppgave2.Tekst;
import no.hvl.dat100.jplab11.oppgave3.Blogg;
import no.hvl.dat100.jplab11.ekstra.BloggUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBloggUtilsOgInnleggCompare {
    @Test
    public void testSquish() {
        Blogg samling = new Blogg(6);

        Tekst innlegg1 = new Tekst(1,"Ole Olsen",  "23-10","en tekst");
        Bilde innlegg2 = new Bilde(2,"Oline Olsen","24-10","et bilde","http://www.picture.com/oo.jpg");
        Tekst innlegg3 = new Tekst(3,"lommemannen","25-10","en skummel tekst");

        samling.leggTil(innlegg1);
        samling.leggTil(innlegg2);
        samling.leggTil(innlegg3);

        Innlegg[] innleggsamling = samling.getSamling();

        assertNotNull(innleggsamling[2]);
        assertNull(innleggsamling[3]);

        samling.slett(innlegg2);

        assertFalse(BloggUtils.erSquished(innleggsamling));
        assertNull(innleggsamling[1]);

        BloggUtils.squish(innleggsamling);

        assertTrue(BloggUtils.erSquished(innleggsamling));
        assertNotNull(innleggsamling[1]);
    }
    @Test
    public void testSorterViaID() {
        Blogg samling = new Blogg(4);

        Tekst innlegg1 = new Tekst(1,"Ole Olsen",  "23-10","en tekst");
        Bilde innlegg2 = new Bilde(2,"Oline Olsen","24-10","et bilde","http://www.picture.com/oo.jpg");
        Tekst innlegg3 = new Tekst(3,"lommemannen","25-10","en skummel tekst");

        samling.leggTil(innlegg3);
        samling.leggTil(innlegg2);
        samling.leggTil(innlegg1);

        Innlegg[] innleggsamling = samling.getSamling();

        assertFalse(innleggsamling[0].getId() < innleggsamling[1].getId());
        assertFalse(innleggsamling[1].getId() < innleggsamling[2].getId());
        assertFalse(BloggUtils.erSortert(InnleggCompareType.ID, innleggsamling, null));

        BloggUtils.sorter(InnleggCompareType.ID, innleggsamling, null);

        assertTrue(innleggsamling[0].getId() < innleggsamling[1].getId());
        assertTrue(innleggsamling[1].getId() < innleggsamling[2].getId());
        assertTrue(BloggUtils.erSortert(InnleggCompareType.ID, innleggsamling, null));
    }

    @Test
    public void testSorterViaDato() { //BloggApp tar i bruk datosortering for å teste at vi får de nyeste først
        Blogg samling = new Blogg(4);

        Tekst innlegg1 = new Tekst(1,"Ole Olsen",  "23-10-2022","en tekst");
        Bilde innlegg2 = new Bilde(2,"Oline Olsen","25-09-2022", "et bilde","http://www.picture.com/oo.jpg");
        Tekst innlegg3 = new Tekst(3,"lommemannen","25-10-2022","en skummel tekst");

        samling.leggTil(innlegg3);
        samling.leggTil(innlegg2);
        samling.leggTil(innlegg1);

        Innlegg[] innleggsamling = samling.getSamling();
        String format = "dd-MM-yyyy";

        assertFalse(BloggUtils.erSortert(InnleggCompareType.DATO, innleggsamling, format));

        BloggUtils.sorter(InnleggCompareType.DATO, innleggsamling, format);

        assertTrue(BloggUtils.erSortert(InnleggCompareType.DATO,innleggsamling, format));
    }

    @Test
    public void testSorterViaLikes() {
        Blogg samling = new Blogg(4);

        Tekst innlegg1 = new Tekst(1,"Ole Olsen",  "23-10",5,"en tekst");
        Bilde innlegg2 = new Bilde(2,"Oline Olsen","24-9", 4,"et bilde","http://www.picture.com/oo.jpg");
        Tekst innlegg3 = new Tekst(3,"lommemannen","25-10",0,"en skummel tekst");

        samling.leggTil(innlegg3);
        samling.leggTil(innlegg2);
        samling.leggTil(innlegg1);

        Innlegg[] innleggsamling = samling.getSamling();

        assertFalse(innleggsamling[0].getLikes() > innleggsamling[1].getLikes());
        assertFalse(innleggsamling[1].getLikes() > innleggsamling[2].getLikes());
        assertFalse(BloggUtils.erSortert(InnleggCompareType.LIKES, innleggsamling, null));

        BloggUtils.sorter(InnleggCompareType.LIKES, innleggsamling, null);

        assertTrue(innleggsamling[0].getLikes() > innleggsamling[1].getLikes());
        assertTrue(innleggsamling[1].getLikes() > innleggsamling[2].getLikes());
        assertTrue(BloggUtils.erSortert(InnleggCompareType.LIKES,innleggsamling, null));
    }

}
