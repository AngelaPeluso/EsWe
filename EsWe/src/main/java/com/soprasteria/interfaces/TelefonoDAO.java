package com.soprasteria.interfaces;

import java.awt.List;

import com.soprasteria.model.TelefonoModel;

public interface TelefonoDAO {

    public TelefonoModel getTelefonoInfo(String nomeTelefono);

    public boolean insertTelefono(TelefonoModel telefono);

}
