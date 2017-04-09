package com.soprasteria.control;

import com.soprasteria.model.TelefonoModel;
import com.soprasteria.view.TelefonoView;

public class TelefonoController {

    private TelefonoModel model;
    private TelefonoView view;

    public TelefonoController(TelefonoModel model, TelefonoView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView(TelefonoModel telefono) {
        view.setTelefono(telefono);
    }

    public void updateModel(TelefonoView view) {
        this.model = view.getTelefono();
    }

    public TelefonoModel getModel() {
        return model;
    }

    public void setModelTelefono(TelefonoModel model) {
        this.model = model;
    }

    public TelefonoView getView() {
        return view;
    }

    public void setView(TelefonoView view) {
        this.view = view;
    }

}
