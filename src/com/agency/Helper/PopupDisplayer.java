package com.agency.Helper;

import javax.swing.*;

public class PopupDisplayer {
    public enum PopupType {
        EMPTY_FIELDS("Error!","Please fill out all the fields!", JOptionPane.ERROR_MESSAGE),
        USER_NOT_FOUND("Error!","User not found.", JOptionPane.ERROR_MESSAGE),
        INCORRECT_PASSWORD("Error!","The password is incorrect.", JOptionPane.ERROR_MESSAGE);

        private final String title;
        private final String message;
        private final int optionPane;

        PopupType(String title, String message, int optionPane) {
            this.title = title;
            this.message = message;
            this.optionPane = optionPane;
        }

        public String getTitle() {
            return title;
        }
        public String getMessage() {
            return message;
        }
        public int getOptionPane() {
            return optionPane;
        }

    }

    public static void display(PopupType popupType) {
        display(popupType.title,popupType.message,popupType.optionPane);
    }

    public static void display(String title, String message, int optionPane) {
        JOptionPane.showMessageDialog(null, message, title,optionPane);
    }

}
