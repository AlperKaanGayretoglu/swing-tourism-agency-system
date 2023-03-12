package com.agency.Helper;

import com.agency.Config.Config;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Helper {

    public static Point getScreenCenterFor(Dimension panelSize) {
        return new Point(
                (Toolkit.getDefaultToolkit().getScreenSize().width - panelSize.width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - panelSize.height) / 2
        );
    }

    public static void setLayout() {
        setLayout(Config.DEFAULT_LAYOUT_STYLE);
    }

    public static void setLayout(String layoutStyle) {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (info.getName().equals(layoutStyle)) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}
