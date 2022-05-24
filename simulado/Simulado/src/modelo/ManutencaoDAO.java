package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Manutencao;

public class ManutencaoDAO {
	
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "";

	public ArrayList<Manutencao> ler() {
		ArrayList<Manutencao> linhas = new ArrayList<>();
		Manutencao m;
		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			while (linha != null) {
				m = new Manutencao(linha);
				linhas.add(m);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return linhas;
	}

	public void escrever(ArrayList<Manutencao> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(arquivo));
			for (Manutencao l : linhas) {
				bw.write(l.toCSV()+"\r\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}