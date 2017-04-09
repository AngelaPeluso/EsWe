package com.soprasteria.control;

import com.soprasteria.model.UtenteModel;
import com.soprasteria.view.UtenteView;

public class UtenteController {

    private UtenteModel utenteModel;
    private UtenteView utenteView;

    public UtenteController(UtenteModel utenteModel, UtenteView utenteView) {
        this.utenteModel = utenteModel;
        this.utenteView = utenteView;
    }

    public void updateView(UtenteModel utenteModel) {
        utenteView.setUtente(utenteModel);
    }

    public void updateModel(UtenteView utenteView) {
        this.utenteModel = utenteView.getUtente();
    }

    public UtenteView getUtenteView() {
        return utenteView;
    }

    public void setUtenteView(UtenteView utenteView) {
        this.utenteView = utenteView;
    }

    public UtenteModel getUtenteModel() {
        return utenteModel;
    }

    public void setUtenteModel(UtenteModel utenteModel) {
        this.utenteModel = utenteModel;
    }

}
