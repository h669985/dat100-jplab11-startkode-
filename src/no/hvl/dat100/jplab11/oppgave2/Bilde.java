package no.hvl.dat100.jplab11.oppgave2;

public class Bilde extends Tekst {

	private String url;
	
	public Bilde(int id, String bruker, String dato, String tekst, String url) {
		super(id, bruker, dato, tekst);
		this.url = url;
	}

	public Bilde(int id, String bruker, String dato, int likes, String tekst, String url) {
		super(id, bruker, dato, likes, tekst);
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return super.toString().replace(
						  "TEKST",
					 "BILDE") +
				            	 getUrl() + '\n';
	}

	// Metoden nedenfor er kun for valgfri oppgave 6
	public String toHTML() {
		return super.toHTML().replace(
				     "\t\t<hr>\n",
				"\t\t<iframe src=\"" + getUrl() + "\" height=600 width=800></iframe><hr>\n");
	}
}
