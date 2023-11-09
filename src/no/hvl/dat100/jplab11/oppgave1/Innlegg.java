package no.hvl.dat100.jplab11.oppgave1;

import no.hvl.dat100.jplab11.common.TODO;

public abstract class Innlegg {

	// De fire objektvariable skal ikke være synlige utenfor klassen. (setter private nøkkelord)
	private int id;

	private String bruker;

	private String dato;

	private int likes;
	
	public Innlegg() {
		
	}
	
	public Innlegg(int id, String bruker, String dato) {

		this.id = id;
		this.bruker = bruker;
		this.dato = dato;
		likes = 0; // Konstruktøren som bare tar tre parametre skal sette likes lik 0.

	}

	public Innlegg(int id, String bruker, String dato, int likes) {
		this.id = id;
		this.bruker = bruker;
		this.dato = dato;
		this.likes = likes;
	}
	
	public String getBruker() {
		return bruker;
	}

	public void setBruker(String bruker) {
		this.bruker = bruker;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public int getId() {
		return id;
	}

	public int getLikes() {
		return likes;
	}

	/*
	Lager egen setLikes for morro, selv om den kommer mest sannynlig til å bare brukes i doLike() metoden.
	Men det er også nyttig for å generalisere koden, som gjør den mer vedlikeholdbar og ekspansiv.
	I tillegg er et best å la en metode fokusere på kun en ting, det gjør koden mer lesbar.
	*/
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public void doLike () {
		setLikes(getLikes() + 1);
	}
	
	public boolean erLik(Innlegg innlegg) {
		return getId() == innlegg.getId();
	}
	
	@Override
	public String toString() {
		return  getId()     + "\n" + // '\n' ble lest som tallet 1 av testkoden, i c++ er '\n' mest vanlig å bruke, kansje ikke i Java
				getBruker() + '\n' +
				getDato()   + '\n' +
				getLikes()  + '\n';
	}
	
	// Metoden nedenfor er kun for valgfri oppgave 6
	public String toHTML() {
		
		throw new UnsupportedOperationException(TODO.method());
				
	}
}
