package com.agency.View;

import com.agency.Helper.DatabaseTableModel;
import com.agency.Helper.GUIUtils;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminGUI extends JFrame {

    private JPanel wrapper;
    private JPanel w_top;
    private JPanel w_bottom;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_username;
    private JTextField fld_user_password;
    private JTextField fld_user_full_name;
    private JComboBox cmb_user_category;
    private JButton btn_user_add;
    private JTextField fld_selected_user_id;
    private JButton btn_user_delete;
    private JPanel pnl_user_top;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_search;
    private JScrollPane scrl_hotel_list;
    private JPanel pnl_hotel_list;
    private JTable tbl_hotel_list;
    private JTextField fld_selected_hotel_id;

    // User Table:
    private DefaultTableModel mdl_user_list;
    private final Object[] col_user_list = {"ID", "Username", "Password", "Full Name", "Category"};

    // Hotel Table:
    private DefaultTableModel mdl_hotel_list;
    Object[] col_hotel_list = {"ID", "Name", "City", "Region", "Address", "Email", "Phone", "Star", "Summer Period Start", "Summer Period End", "Winter Period Start", "Winter Period End"};

    private final User admin;

    public AdminGUI(User admin) {
        this.admin = admin;

        GUIUtils.defaultBehaviour(this,wrapper,1000,500);

        initializePage();
        initializeUserTab();
        initializeHotelTab();

        // TODO: Make Search, Update and Delete possible!
    }

    private void initializePage() {
        lbl_welcome.setText("Welcome, "+admin.getFullName());
    }

    private void initializeUserTab() {
        mdl_user_list = new DatabaseTableModel();
        GUIUtils.initializeTab(User.fetchUsers(),mdl_user_list,tbl_user_list,col_user_list,fld_selected_user_id);
    }

    public void loadUserModel() {
        GUIUtils.loadModel(tbl_user_list,mdl_user_list,User.fetchUsers());
    }

    private void initializeHotelTab() {
        mdl_hotel_list = new DatabaseTableModel();
        GUIUtils.initializeTab(Hotel.fetchHotels(),mdl_hotel_list,tbl_hotel_list,col_hotel_list,fld_selected_hotel_id);
    }

    public void loadHotelModel() {
        GUIUtils.loadModel(tbl_hotel_list,mdl_hotel_list,Hotel.fetchHotels());
    }

    // TODO: Delete this main!
    public static void main(String[] args) {
        Helper.setLayout();
        AdminGUI adminGUI = new AdminGUI(User.fetchUser("admin"));
    }
}
