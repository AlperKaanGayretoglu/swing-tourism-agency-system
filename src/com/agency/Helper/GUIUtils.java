package com.agency.Helper;

import com.agency.Config.Config;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GUIUtils {
    private GUIUtils() {}

    /**
     * Gives a JFrame the required default behaviour.
     * @param theGUI is the GUI to be given default behaviour
     * @param mainWrapper
     * @param width
     * @param height
     */
    public static void defaultBehaviour(JFrame theGUI, JPanel mainWrapper, int width, int height) {
        theGUI.add(mainWrapper);

        theGUI.setSize(width, height);
        theGUI.setLocation(Helper.getScreenCenterFor(theGUI.getSize()));

        theGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        theGUI.setTitle(Config.PROJECT_TITLE);

        theGUI.setVisible(true);
    }

    public static <T extends FieldListable> void initializeTab(List<T> elements, DefaultTableModel mdl_list, JTable table_list, Object[] col_list, JTextField fld_selected) {
        mdl_list.setColumnIdentifiers(col_list);

        loadModel(table_list,mdl_list,elements);

        table_list.setModel(mdl_list);
        table_list.getTableHeader().setReorderingAllowed(false);

        table_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_user_id = table_list.getValueAt(table_list.getSelectedRow(), 0).toString();
                fld_selected.setText(select_user_id);
            } catch (Exception exception) {
                // TODO: Find a way to deal with this Exception
            }
        });
    }

    public static <T extends FieldListable> void loadModel(JTable table_list, DefaultTableModel mdl_list, List<T> elements) {
        DefaultTableModel clearModel = (DefaultTableModel) table_list.getModel();
        clearModel.setRowCount(0); // clear the table
        for (FieldListable element : elements) { // update the table
            mdl_list.addRow(element.getAllFields());
        }
    }
}
