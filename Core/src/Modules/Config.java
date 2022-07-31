package Modules;

import java.awt.*;

public class Config {

    public static Thread scanner; // Scan thread
    public static Thread performerThread; // Scan thread
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static boolean mouseSwapped = false;

    public static int mickeys = 0; // Increases as mouse rises
    public static int mLoc = 0; // The current location of the mouse according to the Y axis and Mickeys
    public static Point initMLoc = new Point(0, 0); // The default mouse location in Rust
    public static int mouseYMove = -2; // The value at which the mouse would be moved
    public static int screencapDelay = 20; // Delay after mouse movement and before screencap
    public static int cthreshold = 300000; // The threshold under which two images are considered "the same"
    public static int loopGrace = 100; // Number of loops to perform before image comparing starts
    public static int forceHK = 119; // F8
    public static int scanHK = 121; // F10
    public static int panicHK = 122; // F11

    // Notes and Song Stuff
    static int bpm = 100; // 170 max advisable (for 1/4, 1/2 notes)

    static double D0 = 0.0; // 0 of 870
    static double DS0 = -0.12643; // 110 of 870
    static double E0 = -0.1954; // 170 of 870
    static double F0 = -0.24137; // 210 of 870
    static double FS = -0.28735; // 250 of 870
    static double G = -0.32758; // 285 of 870
    static double GS = -0.36781; // 320 of 870
    static double A = -0.41954; // 365 of 870
    static double AS = -0.45977; // 400 of 870
    static double B = -0.50574; // 440 of 870
    static double C = -0.56321; // 490 of 870
    static double CS = -0.62068; // 540 of 870
    static double D = -0.67816; // 590 of 870
    static double DS = -0.73563; // 640 of 870
    static double E = -0.82758; // 720 of 870
    static double F = -1.0; // 870 of 870

    // Delays

    public static int clickDelay = 80;
    public static int postResetDelay = 50;
    public static int postSetDelay = 80;
    public static int postClickDelay = (int) (((double) 60) / bpm*1000) - clickDelay - postResetDelay - postSetDelay;

}
