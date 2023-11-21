package no.hvl.dat100.jplab11.oppgave7;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import no.hvl.dat100.jplab11.ekstra.BloggUtils;
import no.hvl.dat100.jplab11.ekstra.InnleggCompareType;
import no.hvl.dat100.jplab11.oppgave1.Innlegg;
import no.hvl.dat100.jplab11.oppgave2.Bilde;
import no.hvl.dat100.jplab11.oppgave2.Tekst;
import no.hvl.dat100.jplab11.oppgave6.HtmlBlogg;

public class BloggApp {

	public static String toHTML() {

		Tekst innlegg2 = new Tekst(1, "Sven-Olai", "23-10-2022",
				"Lars, hva er status for den siste obligatoriske innleveringen?");
		Bilde innlegg1 = new Bilde(2, "Lars", "24-10-2022",
				"Ser bra ut! - har lagt ved output-eksempel fra enhetstester",
				"https://home.hvl.no/ansatte/lmkr/dat100/junitscreenshot.png");

		innlegg1.doLike();
		innlegg1.doLike();
		innlegg2.doLike();

		HtmlBlogg samling = new HtmlBlogg();

		samling.leggTil(innlegg1);
		samling.leggTil(innlegg2);

		// Tester datosortering, sorteres etter nyeste
		Innlegg[] innleggsamling = samling.getSamling();

		BloggUtils.sorter(InnleggCompareType.DATO, innleggsamling, "dd-MM-yyyy");

		return samling.toString();
	}

	private ServerSocket welcomeSocket;

	public BloggApp(ServerSocket welcomeSocket) {
		this.welcomeSocket = welcomeSocket;
	}

	public void process() {

		try {

			System.out.println("SERVER ACCEPTING");

			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			String text = inFromClient.readLine();

			System.out.println("SERVER RECEIVED: " + text);

			String htmlbody = toHTML();

			String header = "HTTP/1.1 200 OK\n" + "Server: DAT100 HTTP Server : 1.0\n" + "Date: " + (new Date()) + "\n"
					+ "Content-type: " + "text/html" + "\n" + "Content-length: " + htmlbody.length() + "\n" + "\n";

			String outtext = header + htmlbody;

			System.out.println("SERVER SENDING: " + outtext);

			outToClient.write(outtext.getBytes());
			outToClient.flush();

			outToClient.close();
			inFromClient.close();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			connectionSocket.close();

		} catch (IOException ex) {

			System.out.println("TCPServer: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);

		}
	}
}
