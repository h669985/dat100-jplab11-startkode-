package no.hvl.dat100.jplab11.oppgave6;

import no.hvl.dat100.jplab11.oppgave1.Innlegg;
import no.hvl.dat100.jplab11.oppgave3.Blogg;

public class HtmlBlogg extends Blogg {

	public HtmlBlogg() {
		super();
	}
	
	private static String HTMLPREFIX = 
			"<html>\n\t<head>\n\t\t<title>DAT100 Blogg</title>\n\t</head>\n\t<body>\n";
	
	private static String HTMLPOSTFIX = 
			"\t</body>\n</html>";
	
	@Override
	public String toString() {
		StringBuilder htmlblogg = new StringBuilder();

		htmlblogg.append(HTMLPREFIX);

		for (Innlegg i : getSamling()) {
			if (i != null){
				htmlblogg.append(i.toHTML());
			}
		}

		htmlblogg.append(HTMLPOSTFIX);

		return htmlblogg.toString();
	}
}
