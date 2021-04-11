package org.polomka.app.manager;

import org.polomka.app.Application;
import org.polomka.app.entity.ClientEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    public static List<ClientEntity> getAll() throws SQLException {
        try (Connection c = Application.getConnection()) {
            String sql = "SELECT * FROM client";
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<ClientEntity> ls = new ArrayList<>();

            while (rs.next()) {
                ls.add(new ClientEntity(
                        rs.getInt("ID"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Patronymic"),
                        rs.getDate("Birthday"),
                        rs.getTimestamp("RegistrationDate"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("GenderCode"),
                        rs.getString("PhotoPath")
                ));
            }


            return ls;
        }
    }
}
