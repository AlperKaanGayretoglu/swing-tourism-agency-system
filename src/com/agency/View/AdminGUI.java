package com.agency.View;

import com.agency.Config.Config;
import com.agency.Database.DataFetcher;
import com.agency.Helper.Helper;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
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

    // User Table:
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    private final User admin;

    public AdminGUI(User admin) {
        this.admin = admin;

        // Common Actions
        add(wrapper);

        setSize(1000, 500);
        setLocation(Helper.getScreenCenterFor(getSize()));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);

        setResizable(false);
        setVisible(true);
        // # Common Actions

        initializePage();
        initializeUsersTab();

        // TODO: Make Search, Update and Delete possible!
    }

    private void initializePage() {
        lbl_welcome.setText("Welcome, "+admin.getFullName());
    }

    private void initializeUsersTab() {
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) { // don't edit the first column (id column)
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Username", "Password", "Full Name", "Category"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        row_user_list = new Object[col_user_list.length];

        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_selected_user_id.setText(select_user_id);
            } catch (Exception exception) {
                // TODO: Find a way to deal with this Exception
            }
        });
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0); // clear the table
        int i = 0;
        for (User obj : DataFetcher.fetchUsers()) { // update the table
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getUsername();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getFullName();
            row_user_list[i++] = obj.getCategory();
            mdl_user_list.addRow(row_user_list);
        }
    }

    // TODO: Delete this main!
    public static void main(String[] args) {
        Helper.setLayout();
        AdminGUI adminGUI = new AdminGUI(DataFetcher.fetchUser("admin"));
    }
}
