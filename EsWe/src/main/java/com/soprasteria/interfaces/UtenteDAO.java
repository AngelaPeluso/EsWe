package com.soprasteria.interfaces;

import com.soprasteria.model.UtenteModel;

public interface UtenteDAO {

    public UtenteModel getUtenteInfo(String numeroTelefono);

    public boolean updateUtenteInfo(String numeroTelefono, String date);

    public boolean insertUtente(UtenteModel utenteModel, String nomeTelefono);

    public boolean deleteUtente(String numeroTelefono);
}
