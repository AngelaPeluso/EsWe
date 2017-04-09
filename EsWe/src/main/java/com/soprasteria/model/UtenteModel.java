package com.soprasteria.model;

public class UtenteModel {
	private String name, surname, dataNascita,numTelefono, email;
    TelefonoModel telefono;
    
    public UtenteModel() {
	}
    
	public UtenteModel(String name, String surname, String dataNascita, String numTelefono, String email,
			TelefonoModel telefono) {
		this.name = name;
		this.surname = surname;
		this.dataNascita = dataNascita;
		this.numTelefono = numTelefono;
		this.email = email;
		this.telefono = telefono;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TelefonoModel getTelefono() {
		return telefono;
	}
	public void setTelefono(TelefonoModel telefono) {
		this.telefono = telefono;
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Name = ").append(name).append("\n")
		.append("Surname = ").append(surname).append("\n")
		.append("Data di Nascita = ").append(dataNascita).append("\n")
		.append("N.Telefono = ").append(numTelefono).append("\n")
		.append("Email = ").append(email).append("\n");
			
		return builder.toString();
	}
    
}
