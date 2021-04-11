package org.polomka.app.ui;

import org.polomka.app.entity.ClientEntity;
import org.polomka.app.manager.ClientManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class SubForm extends JFrame {
    private JPanel mainPanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField patronymicField;
    private JTextField birthdayField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField imagePathField;
    private JButton applyButton;
    private JComboBox genderBox;
    private JLabel labelOperation;
    private JTextField idField;

    private ClientEntity client;

    public SubForm(ClientEntity client) {

        setContentPane(mainPanel);
        setTitle("Polomka");
        setMinimumSize(new Dimension(1000, 500));

        this.client = client;

        genderBox.addItem("м");
        genderBox.addItem("ж");

        idField.setEditable(false);

        if (this.client == null) {
            labelOperation.setText("Добавление");
            applyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("ADD");

                    System.out.println(getDataFromFields());

                    try {
                        ClientManager.add(getDataFromFields());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    dispose();
                    try {
                        new ClientsTableFrom();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
        } else {
            labelOperation.setText("Редактирование");
            applyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("UPDATE");

                    ClientManager.update(getDataFromFields());

                    dispose();
                    try {
                        new ClientsTableFrom();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            setDataInFields();
        }
        setVisible(true);
    }

    public void setDataInFields() {
        idField.setText(Integer.toString(client.getId()));
        firstNameField.setText(client.getFirstN());
        lastNameField.setText(client.getLastN());
        patronymicField.setText(client.getPatron());
        birthdayField.setText(client.getBirthday().toString());
        emailField.setText(client.getEmail());
        phoneField.setText(client.getPhone());
        imagePathField.setText(client.getPhotoPath());
    }

    public ClientEntity getDataFromFields() {
        if (this.client == null) {
            return new ClientEntity(
                    -1,
                    lastNameField.getText(),
                    firstNameField.getText(),
                    patronymicField.getText(),
                    Date.valueOf(birthdayField.getText()),
                    new Time(System.currentTimeMillis()),
                    emailField.getText(),
                    phoneField.getText(),
                    genderBox.getSelectedItem().toString(),
                    imagePathField.getText()
            );
        } else {
            return new ClientEntity(
                    Integer.parseInt(idField.getText()),
                    lastNameField.getText(),
                    firstNameField.getText(),
                    patronymicField.getText(),
                    Date.valueOf(birthdayField.getText()),
                    new Time(System.currentTimeMillis()),
                    emailField.getText(),
                    phoneField.getText(),
                    genderBox.getSelectedItem().toString(),
                    imagePathField.getText()
            );
        }
    }
}
