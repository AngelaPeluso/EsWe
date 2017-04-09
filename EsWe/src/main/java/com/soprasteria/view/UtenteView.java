package com.soprasteria.view;

import com.soprasteria.model.UtenteModel;

public class UtenteView {

    private UtenteModel utente;

    public UtenteView(UtenteModel utente) {
        this.utente = utente;
    }

    public void printInfo() {
        System.out.println(utente);
    }

    public UtenteModel getUtente() {
        return utente;
    }

    public void setUtente(UtenteModel utente) {
        this.utente = utente;
    }

}
