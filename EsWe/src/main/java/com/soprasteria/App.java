package com.soprasteria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.soprasteria.control.UtenteController;
import com.soprasteria.interfaces.TelefonoDAO;
import com.soprasteria.interfaces.UtenteDAO;
import com.soprasteria.interfaces.implementation.DefaultTelefonoDAO;
import com.soprasteria.interfaces.implementation.DefaultUtenteDAO;
import com.soprasteria.model.TelefonoModel;
import com.soprasteria.model.UtenteModel;
import com.soprasteria.view.UtenteView;

public class App {

    public static void main(String[] args) {
        try {
            int scelta;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
            UtenteModel utenteModel = new UtenteModel();
            TelefonoModel telefonoModel = null;
            String name, surname,dataNascita, numeroTelefono,email,modello, colore,sistema;
            UtenteView utenteView = new UtenteView(utenteModel);
            UtenteController utenteController = new UtenteController(utenteModel, utenteView);
            String menu = "MENU PRINCIPALE \n1.Inserisci utente e il suo numero di telefono\n2.Inserisci telefono\n"
                    + "3.Visualizza utente\n4.Aggiorna data di nascita dell'utente\n"
                    + "5.Cancella utente\n0.Esci";
            do {
                System.out.println(menu);
                scelta = Utils.parseNumber(in);
                switch (scelta) {
                    case 1:
                        name = Utils.insert(in, "Nome utente");
                        surname = Utils.insert(in, "Cognome utente");
                        dataNascita = Utils.insert(in, "Data di nascita");
                        email = Utils.insert(in, "Email utente");
                        numeroTelefono = Utils.insert(in, "Numero di telefono");
                        utenteModel = new UtenteModel();
                        utenteModel.setName(name);
                        utenteModel.setSurname(surname);
                        utenteModel.setDataNascita(dataNascita);
                        utenteModel.setEmail(email);
                        utenteModel.setNumTelefono(numeroTelefono);
                        modello = Utils.insert(in, "Modello del telefono");
                        telefonoModel = getTelefonoFromDB(modello);
                        if (telefonoModel != null) {
                            utenteModel.setTelefono(telefonoModel);
                            if (insertUtente(utenteModel, modello)) {
                                System.out.println("L' utente è stato inserito");
                            } else {
                                System.out.println("L'utente non è stato inserito");
                            }
                        } else {
                            System.out.println("Il telefono non è presente.");
                            System.out.println("Non puoi aggiungere un telefono non presente ad un utente");
                        }
                        break;
                    case 2:
                        modello = Utils.insert(in, "Modello del cellulare");
                        colore = Utils.insert(in, "Colore del cellulare");
                        sistema = Utils.insert(in, "Sitema operativo del cellulare");
                        telefonoModel = new TelefonoModel();
                        telefonoModel.setModello(modello);
                        telefonoModel.setColore(colore);
                        telefonoModel.setSistema(sistema);
                        if (insertTelefono(telefonoModel)) {
                            System.out.println("Telefono inserito");
                        } else {
                            System.out.println("Telefono non inserito");
                        }
                        break;
                    case 3:
                        numeroTelefono = Utils.insert(in, "numero di telefono");
                        utenteModel = getUtenteFromDB(numeroTelefono);
                        if (utenteModel != null) {
                            utenteController.setUtenteModel(utenteModel);
                            utenteController.updateView(utenteModel);
                            utenteView.printInfo();
                        } else {
                            System.out.println("Questo numero di telefono non è salvato in rubrica");
                        }
                        break;
                    case 4:
                        numeroTelefono = Utils.insert(in, "numero di telefono");
                        dataNascita = Utils.insert(in, "data di nascita"); //TODO formattare data
                        if (updateUser(numeroTelefono, dataNascita)) {
                            utenteModel = getUtenteFromDB(numeroTelefono);
                            utenteController.setUtenteModel(utenteModel);
                            utenteController.updateView(utenteModel);
                            System.out.println("Utente aggiornato:");
                            utenteView.printInfo();
                        } else {
                            System.out.println("Questo numero di telefono non è salvato in rubrica");
                        }
                        break;
                    case 5:
                        numeroTelefono = Utils.insert(in, "numero di telefono");
                        if (deleteUtenteFromDB(numeroTelefono)) {
                            System.out.println("Utente cancellato correttamente");
                        } else {
                            System.out.println("Questo numero di telefono non è salvato in rubrica");
                        }
                        break;
                    case 0:
                        System.out.println("Ciao");
                        break;
                    default:
                        System.out.println("Scelta errata!");
                        break;
                }
            } while (scelta != 0);
        } catch (IOException ex) {
        }
    }

    private static boolean updateUser(String utenteModel, String data) {
        UtenteDAO utenteDAO = new DefaultUtenteDAO();
        return utenteDAO.updateUtenteInfo(utenteModel, data);
    }

    private static UtenteModel getUtenteFromDB(String numeroTelefono) {
        UtenteDAO utenteDAO = new DefaultUtenteDAO();
        return utenteDAO.getUtenteInfo(numeroTelefono);
    }

    private static TelefonoModel getTelefonoFromDB(String modello) {
        TelefonoDAO telefonoDAO = new DefaultTelefonoDAO();
        return telefonoDAO.getTelefonoInfo(modello);
    }

    private static boolean insertTelefono(TelefonoModel telefonoModel) {
        TelefonoDAO telefonoDAO = new DefaultTelefonoDAO();
        return telefonoDAO.insertTelefono(telefonoModel);
    }

    private static boolean deleteUtenteFromDB(String numeroTelefono) {
        UtenteDAO utenteDAO = new DefaultUtenteDAO();
        return utenteDAO.deleteUtente(numeroTelefono);
    }

    private static boolean insertUtente(UtenteModel utenteModel, String modello) {
        UtenteDAO utenteDAO = new DefaultUtenteDAO();
        return utenteDAO.insertUtente(utenteModel, modello);
    }

}