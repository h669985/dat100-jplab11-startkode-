package no.hvl.dat100.jplab11.oppgave4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave3.*;

public class SkrivBlogg {

	public static boolean skriv(Blogg samling, String mappe, String filnavn) {
		File file = new File(mappe + filnavn); // Lager filen
		String filtekst = samling.toString(); // Lager filteksten

		try (PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8)) { //Lager en filskriver
			writer.print(filtekst); // Skrivet til filen
			return true;

		} catch (IOException e) { //Mer generic error handling
			System.out.println(e.getMessage());
			return false;
		}
	}
}
