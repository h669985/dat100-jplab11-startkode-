package no.hvl.dat100.jplab11.oppgave3;
import no.hvl.dat100.jplab11.oppgave1.*;

public class Blogg {

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

		getSamling()[nesteledig] = innlegg;

		// sorter(InnleggCompareType.ID, getSamling(), null); //Antar at ID på innlegget er rekkefølgen innleggene ble laget på og dermed sorter etter stigende ID

		nesteledig++;
		return true;
	}
	
	public String toString() {
		StringBuilder data = new StringBuilder(); // Debuggeren foretrekker StringBuilder og append() over String og += (String connotation)
		for (Innlegg i : getSamling()) {
			if (i != null)
				data.append(i.toString()); // Usikker på om append() konverterer til Streng på samme måte som toString() gjør, beholder metodekallet for vedlikeholdbarhet
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
		if (!finnes(innlegg)) {
			return false; // early return hvis false så slipper vi å forsøke noe av det nedenfor
		}

		for (int i = 0; i <= getAntall(); i++) {
			if (getSamling()[i].erLik(innlegg)){
				getSamling()[i] = null;
				nesteledig--;
				break;
			}
		}

		// squish(getSamling()); // Når vi sletter kan vi skape tomrom så squish(Innlegg[] samling) flytter alle null innlegg bakerst, ikke så effektivt da...

		return true;
	}
	
	public int[] search(String keyword) {
		// Forhåndsdeklarasjoner
		int[] muligetreff = new int[getAntall()];
		int antalltreff = 0;

		// Sjekker gjennom hele samlingen for keyword og kopierer Id-en inn i muligetreff hvis keyword ble funnet
		for (Innlegg innlegg : getSamling()) {
			if (innlegg.toString().contains(keyword)) {
				muligetreff[antalltreff] += innlegg.getId();
				antalltreff++;
			}
		}

		// Lager ny array på korrekt størrelse og kopierer inn i samme rekkefølge
		int[] resultat = new int[antalltreff];
        System.arraycopy(muligetreff, 0, resultat, 0, antalltreff); // System.arraycopy istedenfor for-loop

		return resultat;
	}
}