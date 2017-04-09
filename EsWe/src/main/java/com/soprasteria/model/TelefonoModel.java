package com.soprasteria.model;

public class TelefonoModel {
	private String modello, colore, sistema;
	
	public TelefonoModel() {
	}
	public TelefonoModel(String modello, String colore, String sistema) {
		this.modello = modello;
		this.colore = colore;
		this.sistema = sistema;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Modello = ").append(modello).append("\n")
		.append("Colore = ").append(colore).append("\n")
		.append("Sistema = ").append(sistema).append("\n");			
		return builder.toString();
	}
	
}
