package br.com.produtos.model;

import java.text.SimpleDateFormat;
import java.util.Date;

//Classe de Data e Hora.
public class Data {
	Date d;
	SimpleDateFormat fmtData = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat fmtHora = new SimpleDateFormat("HH:mm:ss");

	public Data(Date d) {
		this.d = d;
	}

	public String getData() {
		return fmtData.format(d);
	}

	public String getHora() {
		return fmtHora.format(d);
	}

}
