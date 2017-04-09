package com.soprasteria.view;

import com.soprasteria.model.TelefonoModel;

public class TelefonoView {

    private TelefonoModel telefono;

    public TelefonoView(TelefonoModel telefono) {
        this.telefono = telefono;
    }

    public void printInfo() {
        System.out.println(telefono.toString());
    }

    public TelefonoModel getTelefono() {
        return telefono;
    }

    public void setTelefono(TelefonoModel telefono) {
        this.telefono = telefono;
    }

}
