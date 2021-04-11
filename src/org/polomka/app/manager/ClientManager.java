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

    public static void update(ClientEntity client) {
        try (Connection c = Application.getConnection()) {
            String sql = "UPDATE client SET LastName=?, FirstName=?, Patronymic=?, Birthday=?, RegistrationDate=?, Email=?, Phone=?, GenderCode=?, PhotoPath=? WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, client.getLastN());
            ps.setString(2, client.getFirstN());
            ps.setString(3, client.getPatron());
            ps.setDate(4, (Date) client.getBirthday());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setString(6, client.getEmail());
            ps.setString(7, client.getPhone());
            ps.setString(8, client.getGender());
            ps.setString(9, client.getPhotoPath());
            ps.setInt(10, client.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void add(ClientEntity client) throws SQLException {

        try (Connection c = Application.getConnection()) {

            String sql = "INSERT INTO client(LastName, FirstName, Patronymic, Birthday, RegistrationDate, Email, Phone, GenderCode, PhotoPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, client.getLastN());
            ps.setString(2, client.getFirstN());
            ps.setString(3, client.getPatron());
            ps.setDate(4, (Date) client.getBirthday());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setString(6, client.getEmail());
            ps.setString(7, client.getPhone());
            ps.setString(8, client.getGender());
            ps.setString(9, client.getPhotoPath());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(ClientEntity client) {
        try (Connection c = Application.getConnection()) {
            String sql = "DELETE FROM client WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, client.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
