package Modules;

import java.awt.*;

public class Config {

    public static Thread scanner; // Scan thread
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static boolean mouseSwapped = false;

    public static int mouseYMove = -2; // The value at which the mouse would be moved
    public static int screencapDelay = 20; // Delay after mouse movement and before screencap
    public static int cthreshold = 500000; // The threshold under which two images are considered "the same"
    public static int loopGrace = 100; // Number of loops to perform before image comparing starts
    public static int scanHK = 121; // F10
    public static int panicHK = 122; // F11

}
