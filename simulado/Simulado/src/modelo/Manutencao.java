package modelo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Manutencao {

	private int id;
	private String data;
	private String equipamento;
	private Double custoHora;
	private Double tempGasto;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Manutencao(int id, String data, String equipamento, double custoHora, double tempGasto) {
		this.id = id;
		this.data = data;
		this.equipamento = equipamento;
		this.custoHora = custoHora;
		this.tempGasto = tempGasto;
	}
	
	public Manutencao(String linha) {
		this.id = Integer.parseInt(linha.split(";")[0]);
		this.data = linha.split(";")[1];
		this.equipamento = linha.split(";")[2];
		this.custoHora = Double.parseDouble(linha.split(";")[3]);
		this.tempGasto = Double.parseDouble(linha.split(";")[4]);
	}

	public int getId() {
		return id;
	}
	
	public String getId(String s) {
		return String.format("%d", id);
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getData() {
		return data;
	}
	public String getData(String s) {
		return sdf.format(data);
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public Double getCustoHora() {
		return custoHora;
	}
	public String getCustoHora(String s) {
		return String.format("%.0f", custoHora);
	}

	public void setCustoHora(Double custoHora) {
		this.custoHora = custoHora;
	}
	
	public Double getTempGasto() {
		return tempGasto;
	}
	
 public String getTempoGasto(String s) {
		return String.format("%.0f",tempGasto);
	}
 
	public void setTempGasto(Double tempGasto) {
		this.tempGasto = tempGasto;
	}
	
	public double getTotal() {
		return tempGasto*custoHora;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manutencao other = (Manutencao) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return  id + "\t" + data + "\t" + equipamento + "\t" + custoHora
				+ "\t" + tempGasto + "\t" + getTotal();
	}

	public String toCSV() {
		return  id + ";" + sdf.format(data)  + ";" +  equipamento + ";" + custoHora
				+ ";" + tempGasto + getTotal()+"\r\n";
	}

}