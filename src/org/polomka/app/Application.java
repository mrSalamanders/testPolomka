package org.polomka.app;

import org.polomka.app.manager.ClientManager;
import org.polomka.app.ui.ClientsTableFrom;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    private Application instance;

    public static void main(String[] args) throws SQLException {
        new Application();
    }

    private Application() throws SQLException {
        instance = this;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ClientsTableFrom();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/polomka?serverTimeZone=UTC&characterEncoding=utf-8",
                "root",
                "1234");
    }
}
