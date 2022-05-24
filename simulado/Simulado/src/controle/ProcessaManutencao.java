package controle;

import modelo.ManutencaoDAO;

import java.util.ArrayList;

import modelo.Manutencao;


public class ProcessaManutencao {
	
	public static ArrayList<Manutencao> manutencoes = new ArrayList<>();
	private static ManutencaoDAO md = new ManutencaoDAO();
	
	public static void abrir() {
		manutencoes = md.ler();
	}
	
	public static void salvar() {
		md.escrever(manutencoes);
	}
	
	public static void carregar() {
		// Limpar a lista
		manutencoes = new ArrayList<>();
		// Preencher com testes
		manutencoes.add(new Manutencao(1, "22/05/2022", "injetora", 3, 2));
	}
	
}