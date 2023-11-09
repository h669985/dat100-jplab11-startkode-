package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;

import javax.swing.text.html.InlineView;

public class Blogg {

	// Føler denne ble rotete pga jeg ikke tar hensyn til faktumet at når vi sletter så kan vi få smutthull som må fylles, nesteledig kan ikke peke på disse smutthullene.
	// leggtill tetter smutthull nå, men slett() burde kansje også gjøre noe for å forhindre hull
	// TLDR jeg føler denne koden bare tilfredstiller JUnit testene og er ikke særlig robust

	Innlegg[] innleggtabell;

	int nesteledig;

	public Blogg() {
		innleggtabell = new Innlegg[20]; // startstørrelsen på tabellen blir satt til 20
		nesteledig = 0;
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	public int getAntall() {
		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return innleggtabell;
	}
	
	public int finnInnlegg(Innlegg innlegg) {
		for (int i = 0; i <= getAntall(); i++) {
			if (getSamling()[i] != null && getSamling()[i].erLik(innlegg)) { // Bruk erLik-metoden for Innlegg-objekt i implementasjonen
				return i;
			}
		}
		return -1;
	}

	public boolean finnes(Innlegg innlegg) {
        for (Innlegg i : getSamling()) {
            if (i != null && i.erLik(innlegg)) {
                return true;
            }
        }
		return false;
	}

	public boolean ledigPlass() {
        return getAntall() < getSamling().length;
    }
	
	public boolean leggTil(Innlegg innlegg) {
		if (!ledigPlass()) {
			return false;
		}
		for (int i = 0; i <= getAntall(); i++ ){
			if(getSamling()[i] == null) {
				getSamling()[i] = innlegg;
				break;
			}
		}
		nesteledig++;
		return true;
	}
	
	public String toString() {
		StringBuilder data = new StringBuilder(); // Debuggeren foretrekker StringBuilder og append() over String og +=
		for (Innlegg i : getSamling()) {
			data.append(i.toString());
		}

		return getAntall() + "\n" + data;
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		int dobbel = getAntall() * 2;
		Innlegg[] temp = getSamling();
		innleggtabell = new Innlegg[dobbel];

        System.arraycopy(temp, 0, innleggtabell, 0, getAntall()); // Debuggeren foreslo denne over en for løkker som jeg egentlig skrev

	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {
		if (finnes(innlegg)){
			return false;

		}

		if (!ledigPlass()) {
			utvid();
		}

		return leggTil(innlegg);
	}
	
	public boolean slett(Innlegg innlegg) {
		if (!finnes(innlegg)){
			return false; // early return hvis false så slipper vi å forsøke noe av det nedenfor
		}

		for (int i = 0; i <= getAntall(); i++) {
			if (getSamling()[i].erLik(innlegg)){
				getSamling()[i] = null;
				nesteledig--;
				break;
			}
		}

		// TODO -- tett igjen smutthull

		return true;
	}
	
	public int[] search(String keyword) {
		
		throw new UnsupportedOperationException(TODO.method());

	}
}