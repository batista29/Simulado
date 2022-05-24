package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import controle.ProcessaManutencao;
import modelo.ManutencaoDAO;
import modelo.Manutencao;

public class FormManutencoes extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	
	private JComboBox<String> Equipamentos;
	private String texto = "";
	private JLabel id, data, equipamento , custoHora, tempGasto; 
	private JTextField tfId,tfdata, tfcustoHora, tftempGasto;
	private JTextArea Tela;
	private JButton cadastrar, buscar, deletar, alterar;
	private int autoId = ProcessaManutencao.manutencoes.size() + 1;
	private String data2 = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	
	
	FormManutencoes(){
		setTitle("Formulario de Manutenção");
		setBounds(450, 200, 750, 600);
		painel = new JPanel();
		painel.setBackground(new Color(173,216,230));
		setContentPane(painel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		id = new JLabel("Id:");
		id.setBounds(50,30,64, 14);
		tfId = new JTextField(String.format("%d", autoId));
		tfId.setEditable(false);
		tfId.setBounds(50,55,86,25);
		painel.add(id);
		painel.add(tfId);
		
		
		data = new JLabel("Data:");
	    data.setBounds(50,85,50, 14);
	    tfdata = new JTextField(data2);
		tfdata.setBounds(50,110,86,25);
		tfdata.setEditable(false);
		painel.add(data);
		painel.add(tfdata);
		
	    equipamento = new JLabel("Equipamentos:");
	    equipamento.setBounds(50,140,100,14);
	    painel.add(equipamento);
	    Equipamentos = new JComboBox<String>(new String[] { "Britadeira","Esteira","Phlips","Martelo","Impressora" });
	    Equipamentos.setBounds(50,165,90, 25);
	    painel.add(Equipamentos);
	    
	    custoHora = new JLabel("Custo de hora:");
	    custoHora.setBounds(50,190, 100, 14);
	    tfcustoHora = new JTextField();
		tfcustoHora.setBounds(50,215,86,25);
		painel.add(tfcustoHora);
	    painel.add(custoHora);
	    
	    tempGasto = new JLabel("Tempo Gasto:");
	    tempGasto.setBounds(50,240, 100, 14);
	    tftempGasto = new JTextField();
		tftempGasto.setBounds(50,265,86,25);
		painel.add(tftempGasto);
		painel.add(tempGasto);
		
		Tela = new JTextArea();
		Tela.setBounds(50,300, 620, 200);
		Tela.setEnabled(true);
		Tela.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.pink));
		painel.add(Tela);
		preencherAreaDeTexto();
		

		cadastrar = new JButton("Cadastrar");
		buscar = new JButton("Buscar");
		alterar = new JButton("Alterar");
		deletar = new JButton("Apagar");
		cadastrar.setBounds(550, 55, 110, 30);
		buscar.setBounds(550, 105, 110, 30);
		deletar.setBounds(550, 195, 110, 30);
		alterar.setBounds(550, 250, 110, 30);
		alterar.setEnabled(false);
		deletar.setEnabled(false);
		
		painel.add(cadastrar);
		painel.add(deletar);
		painel.add(alterar);
		painel.add(buscar);
	 
		
		cadastrar.addActionListener(this);
		alterar.addActionListener(this);
		deletar.addActionListener(this);
		buscar.addActionListener(this);
}
	
	
	private void preencherAreaDeTexto() {
		texto = "";
		for (Manutencao p : ProcessaManutencao.manutencoes) {
			texto += p.toString()+"\n";
		}
		Tela.setText(texto);
	}
	
	
	int obterIndice(String item) {
		switch (item) {
		case "Id":
			return 0;
		case "Data":
			return 1;
		case "Equipamento":
			return 2;
		case "Custo e hora":
			return 3;
		case "Tempo Gasto":
			return 4;
		default:
			return -1;
		}
	}
	
	private void cadastrar() {
  String data = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		
		if (tfcustoHora.getText().length() !=0 && tftempGasto.getText().length() !=0) {
			
			ProcessaManutencao.manutencoes.add(new Manutencao(autoId , data, Equipamentos.getSelectedItem().toString(),
			Double.parseDouble(tfcustoHora.getText().toString()),Double.parseDouble(tftempGasto.getText())));
			  autoId++;
		} else {
			JOptionPane.showMessageDialog(this, "Favor Preencher todos as informações");
		}
		limparCampos();
		preencherAreaDeTexto();
		ProcessaManutencao.salvar();
	}
	
	private void limparCampos() {
		tfcustoHora.setText(null);
		tftempGasto.setText(null);
		
	}
	
	private void buscar() {
		String entrada = JOptionPane.showInputDialog(this,"Digite o id da Manutenção");

		boolean Nmr = true;
		if (entrada != null) {
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isDigit(entrada.charAt(i))) {
					Nmr = false;
				}
			}
		}else {
			Nmr = false;
		}
		if (Nmr) {
			int id = Integer.parseInt(entrada);
			
			boolean achou = false;
			for (Manutencao manu : ProcessaManutencao.manutencoes) {
				if (manu.getId() == id) {
					achou = true;
					int indice = ProcessaManutencao.manutencoes.indexOf(manu);
					tfId.setText(ProcessaManutencao.manutencoes.get(indice).getId("s"));
					tfdata.setText(ProcessaManutencao.manutencoes.get(indice).getData());
					Equipamentos.setSelectedIndex(obterIndice(ProcessaManutencao.manutencoes.get(indice).getEquipamento()));
					tfcustoHora.setText(ProcessaManutencao.manutencoes.get(indice).getCustoHora("s"));
					tftempGasto.setText(ProcessaManutencao.manutencoes.get(indice).getTempoGasto("s"));
					ProcessaManutencao.salvar();
					cadastrar.setEnabled(false);
					alterar.setEnabled(true);
					deletar.setEnabled(true);
					break;
				}
			}
			if (!achou) {
				JOptionPane.showMessageDialog(this, "Não encontrado");
			}
		}
	}
	private void alterar() {
		String data = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		int id = Integer.parseInt(tfId.getText());
		int indice = -1;
		
		for(Manutencao menu : ProcessaManutencao.manutencoes) {
			indice = ProcessaManutencao.manutencoes.indexOf(menu);
			
		}
		if(tfcustoHora.getText().length() !=0 && tftempGasto.getText().length() !=0){
			
			ProcessaManutencao.manutencoes.set(indice, new Manutencao(id, data, Equipamentos.getSelectedItem().toString(),
					Double.parseDouble(tfcustoHora.getText().toString()),Double.parseDouble(tftempGasto.getText())));
			preencherAreaDeTexto();
			limparCampos();
			
		
	} else {
		JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
	}
	cadastrar.setEnabled(false);
	alterar.setEnabled(true);
	deletar.setEnabled(false);
	tfId.setText(String.format("%d", ProcessaManutencao.manutencoes.size() + 1));
	ProcessaManutencao.salvar();
}
	
	private void deletar() {
		 int id = Integer.parseInt(tfId.getText());
		 int indice = -1;
		for(Manutencao m : ProcessaManutencao.manutencoes) {
			if (m.getId() == id) {
				indice =ProcessaManutencao.manutencoes.indexOf(m);
	}
		}
		ProcessaManutencao.manutencoes.remove(indice);
		preencherAreaDeTexto();
		limparCampos();
		cadastrar.setEnabled(false);
		alterar.setEnabled(false);
		deletar.setEnabled(true);
		ProcessaManutencao.salvar();
		tfId.setText(String.format("%d", ProcessaManutencao.manutencoes.size() + 1));
		
	}
	

	public static void main(String[] args) {
		ProcessaManutencao.abrir();
		new FormManutencoes().setVisible(true); 
		}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cadastrar) {
			cadastrar();
			}
		if (e.getSource() == deletar) {
			deletar();
		}
		if (e.getSource() == buscar) {
			buscar();
		}
		}
	}