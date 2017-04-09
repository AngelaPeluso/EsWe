package com.soprasteria.interfaces.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.soprasteria.interfaces.UtenteDAO;
import com.soprasteria.model.TelefonoModel;
import com.soprasteria.model.UtenteModel;

public class DefaultUtenteDAO implements UtenteDAO {

    final static Logger logger = Logger.getLogger(DefaultUtenteDAO.class);

    public UtenteModel getUtenteInfo(String numeroTelefono) {
        UtenteModel utenteModel = new UtenteModel();
        TelefonoModel telefono = new TelefonoModel();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT u.name, u.surname, u.dataNascita, u.email, u.numTelefono, t.modello"
            			+" FROM utente u JOIN telefono t ON (u.idTelefono = t.id)"
            			+" WHERE numTelefono = ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroTelefono);
            ResultSet res = stmt.executeQuery();
            if (res.first()) {
                utenteModel.setName(res.getString("name"));
                utenteModel.setSurname(res.getString("surname"));
                utenteModel.setDataNascita(res.getString("dataNascita"));
                utenteModel.setNumTelefono(numeroTelefono);
                utenteModel.setEmail(res.getString("email"));
                telefono.setModello(res.getString("modello"));
                utenteModel.setTelefono(telefono);
            }
	        else {
	            utenteModel = null;
	        }
        } catch (SQLException e) {
        	logger.error(e);
            System.err.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return utenteModel;
    }

    public boolean updateUtenteInfo(String numeroTelefono, String date) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "UPDATE utente SET dataNascita = ?"
                    + " WHERE numTelefono = ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, date);
            stmt.setString(2, numeroTelefono);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
        	logger.error(e);
        	System.err.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return false;
    }

    public boolean insertUtente(UtenteModel utenteModel, String nomeTelefono) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO utente (name, surname, dataNascita, idTelefono, numTelefono, email) VALUE "
                    + "(?, ?, ?, ?, ?,?);";
            int id;
            String sql2 = "SELECT id FROM telefono WHERE modello = ? ;";
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setString(1, nomeTelefono);
            ResultSet res = stmt2.executeQuery();
            if (res.first()) {
                id = res.getInt("id");
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, utenteModel.getName());
                stmt.setString(2, utenteModel.getSurname());
                stmt.setString(3, utenteModel.getDataNascita());
                stmt.setInt(4, id);
                stmt.setString(5, utenteModel.getNumTelefono());
                stmt.setString(6, utenteModel.getEmail());
                
                if (stmt.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
        	logger.error(e);
        	System.err.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return false;
    }

    public boolean deleteUtente(String numeroTelefono) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "DELETE FROM utente"
                    + " WHERE numTelefono = ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroTelefono);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
        	logger.error(e);
        	System.err.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return false;
    }

}
