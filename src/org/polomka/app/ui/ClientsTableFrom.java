package org.polomka.app.ui;

import org.polomka.app.entity.ClientEntity;
import org.polomka.app.manager.ClientManager;
import org.polomka.app.misc.CustomTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class ClientsTableFrom extends JFrame {
    private JPanel mainPanel;
    private JTable table;
    private JScrollPane scroll;
    private JButton addButton;

    private CustomTableModel<ClientEntity> model;

    public ClientsTableFrom() throws SQLException {
        initUI();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if(row != -1 && e.getClickCount() == 2) {
                    dispose();
                    new SubForm(model.getRows().get(row));
                }
            }
        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int row = table.getSelectedRow();
                if(row != -1 && e.getKeyCode() == KeyEvent.VK_DELETE) {
                    ClientManager.delete(model.getRows().get(row));
                    model.getRows().remove(row);
                    model.fireTableDataChanged();
                }
            }
        });
        initButtons();
    }

    public void initUI() {
        setContentPane(mainPanel);

        setTitle("Polomka");
        setMinimumSize(new Dimension(1000, 500));
        table.setRowHeight(50);

        try {
            table.getTableHeader().setReorderingAllowed(false);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            model = new CustomTableModel<ClientEntity>(
                    ClientEntity.class,
                    ClientManager.getAll(),
                    new String[]{"ID", "Имя", "Фамилия", "Отчество", "Дата рождения", "Дата регистрации", "Email", "Телефон", "Гендер", "Путь до картинки", "Картинка"}
            );

            table.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TableColumn column = table.getColumn("Путь до картинки");
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);

        model.fireTableDataChanged();

        setVisible(true);
    }

    public void initButtons() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SubForm(null);
            }
        });
    }
}
