package Modules;

import JNA.WindowHook;

import java.awt.*;

public class Keyboard {

    private static WindowHook.User32 jnaLib = WindowHook.User32.INSTANCE;
    private static final int KEYEVENTF_KEYUP = 0x0002;


    public static void keyClick(int kc) throws AWTException {
        jnaLib.keybd_event(kc, 0, 0, 0);

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jnaLib.keybd_event(kc, 0, KEYEVENTF_KEYUP, 0);
    }

}
