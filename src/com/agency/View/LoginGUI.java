package com.agency.View;

import com.agency.Helper.GUIUtils;
import com.agency.Helper.PopupDisplayer;
import com.agency.Helper.PopupDisplayer.PopupType;
import com.agency.Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame {

    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;

    public LoginGUI() {
        GUIUtils.defaultBehaviour(this,wrapper,400,400);
        setResizable(false);

        btn_login.addActionListener(e -> {
            String username = fld_username.getText();
            String password = String.valueOf(fld_password.getPassword());

            if (username.isBlank() || password.isBlank()) {
                PopupDisplayer.display(PopupType.EMPTY_FIELDS);
                return;
            }

            User user = User.fetchUser(username); // Get the user with this username from the database

            if (user == null) {
                PopupDisplayer.display(PopupType.USER_NOT_FOUND);
                return;
            }

            if (!user.getPassword().equals(password)) {
                PopupDisplayer.display(PopupType.INCORRECT_PASSWORD);
                return;
            }

            switch (user.getCategory()) {
                case ADMIN:
                    AdminGUI adminGUI = new AdminGUI(user);
                    break;
                case CUSTOMER:
                    // TODO: Implement CustomerGUI
                    // CustomerGUI custGUI = new CustomerGUI(user);
                    System.out.println("I am customer");
                    break;
            }
            dispose();
        });
    }
}
