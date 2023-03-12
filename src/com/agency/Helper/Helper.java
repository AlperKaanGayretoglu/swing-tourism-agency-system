package com.agency.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static Point getScreenCenterFor(Dimension panelSize) {
        return new Point(
                (Toolkit.getDefaultToolkit().getScreenSize().width - panelSize.width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - panelSize.height) / 2
        );
    }

}
