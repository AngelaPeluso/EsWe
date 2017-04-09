package com.soprasteria.interfaces.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.soprasteria.interfaces.TelefonoDAO;
import com.soprasteria.model.TelefonoModel;

public class DefaultTelefonoDAO implements TelefonoDAO {
	final static Logger logger = Logger.getLogger(DefaultTelefonoDAO.class);

    public TelefonoModel getTelefonoInfo(String nomeTelefono) {
       TelefonoModel toReturn = new TelefonoModel();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT modello, colore, sistema "
                    + "FROM telefono WHERE modello = ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeTelefono);
            ResultSet res = stmt.executeQuery();
            if (res.first()) {
                toReturn.setModello(res.getString("modello"));
                toReturn.setColore(res.getString("colore"));
                toReturn.setSistema(res.getString("sistema"));
            } else {
                toReturn = null;
            }
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return toReturn;
    }

    public boolean insertTelefono(TelefonoModel telefonoModel) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO telefono (modello, colore, sistema) VALUE "
                    + "(?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, telefonoModel.getModello());
            stmt.setString(2, telefonoModel.getColore());
            stmt.setString(3, telefonoModel.getSistema());
            if (stmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
        	System.err.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return false;
    }

}
