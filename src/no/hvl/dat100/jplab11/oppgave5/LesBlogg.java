package no.hvl.dat100.jplab11.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;
import no.hvl.dat100.jplab11.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String mappe, String filnavn) {
		try {
			//Gjør filen lesbar linje for linje
			BufferedReader reader = new BufferedReader(new FileReader(mappe + filnavn));

			//Oppretter samling med lengden oppgitt i første linje
			Blogg blogg = new Blogg(Integer.parseInt(reader.readLine()));

			//Konverterer filen til individuelle innlegg som settes inn i samling
			while (reader.ready()) {
				// Finner innleggstype
				String type = reader.readLine();

				// Fellesvariabler for TEKST og BILDE
				int id = Integer.parseInt(reader.readLine());
				String bruker = reader.readLine();
				String dato = reader.readLine();
				int likes = Integer.parseInt(reader.readLine());
				String innhold = reader.readLine();

				if (Objects.equals(type, TEKST)) {
					Tekst innlegg = new Tekst(id, bruker, dato, likes, innhold);
					blogg.leggTil(innlegg);

				} else if (Objects.equals(type, BILDE)) {
					String url = reader.readLine();
					Bilde innlegg = new Bilde(id, bruker, dato, likes, innhold, url);
					blogg.leggTil(innlegg);

				} else {
					throw new RuntimeException(type);
				}
			}

			return blogg;
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
