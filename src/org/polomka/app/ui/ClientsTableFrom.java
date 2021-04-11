package org.polomka.app.ui;

import org.polomka.app.entity.ClientEntity;
import org.polomka.app.manager.ClientManager;
import org.polomka.app.misc.CustomTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ClientsTableFrom extends JFrame {
    private JPanel mainPanel;
    private JTable table;
    private JScrollPane scroll;

    private CustomTableModel<ClientEntity> model;

    public ClientsTableFrom() throws SQLException {
        setContentPane(mainPanel);

        setTitle("Lol");
        setMinimumSize(new Dimension(1000, 500));

        try {
            table.getTableHeader().setReorderingAllowed(false);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            model = new CustomTableModel<ClientEntity>(
                    ClientEntity.class,
                    ClientManager.getAll(),
                    new String[]{"ID", "Фамилия", "Имя", "Отчество", "Дата рождения", "Дата регистрации", "Email", "Телефон", "Гендер", "Путь до картинки"}
            );

            table.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        model.fireTableDataChanged();

        setVisible(true);
    }

}
