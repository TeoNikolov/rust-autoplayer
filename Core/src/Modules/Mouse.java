package Modules;

import JNA.WindowHook;

import java.awt.*;

public class Mouse {

    private static WindowHook.User32 jnaLib = WindowHook.User32.INSTANCE;
    private final static int MOUSEEVENTF_LEFTDOWN = 0x02;
    private final static int MOUSEEVENTF_LEFTUP = 0x04;
    private final static int MOUSEEVENTF_RIGHTDOWN = 0x08;
    private final static int MOUSEEVENTF_RIGHTUP = 0x10;
    private final static int MOUSEEVENTF_ABSOLUTE = 0x8000;
    private final static int MOUSEEVENTF_MOVE = 0x0001;

    public static void mouseDragDrop(Point from, Point to) throws InterruptedException {
            mousePress(from);
            Thread.sleep(100);
            mouseMove(new Point(0, 0));
            Thread.sleep(100);
            mouseRelease(to);
    }

//    public static void mousePressAlt() {
//        INPUT input = new INPUT();
//        input.type = new DWORD(INPUT.INPUT_MOUSE);
//
//        input.input.setType("mi");
////        input.input.mi.dx = new LONG(500 * 65536 / WindowHook.User32.INSTANCE.GetSystemMetrics(WindowHook.User32.SM_CXSCREEN));
////        input.input.mi.dy = new LONG(500 * 65536 / WindowHook.User32.INSTANCE.GetSystemMetrics(WindowHook.User32.SM_CYSCREEN));
//
//        input.input.mi.dx = new LONG(0);
//        input.input.mi.dy = new LONG(0);
//
//        input.input.mi.mouseData = new DWORD(0x0001);
//        input.input.mi.dwFlags = new DWORD(MOUSEEVENTF_LEFTDOWN | WindowHook.User32.MOUSEEVENTF_MOVE
//                | WindowHook.User32.MOUSEEVENTF_VIRTUALDESK);
//        input.input.mi.time = new DWORD(0);
//
//        INPUT[] inArray = {input};
//
//        int cbSize = input.size(); // mouse input struct size
//        DWORD nInputs = new DWORD(2); // number of inputs
//        DWORD result = WindowHook.User32.INSTANCE.SendInput(nInputs , inArray, cbSize);
//
//        System.out.println(result);
//    }

//    public static void mouseReleaseAlt() {
//        INPUT input = new INPUT();
//        input.type = new DWORD(INPUT.INPUT_MOUSE);
//
//        input.input.setType("mi");
//        input.input.mi.dx = new LONG(500 * 65536 / WindowHook.User32.INSTANCE.GetSystemMetrics(WindowHook.User32.SM_CXSCREEN));
//        input.input.mi.dy = new LONG(500 * 65536 / WindowHook.User32.INSTANCE.GetSystemMetrics(WindowHook.User32.SM_CYSCREEN));
//
//        input.input.mi.dx = new LONG(0);
//        input.input.mi.dy = new LONG(0);
//
//        input.input.mi.mouseData = new DWORD(0x0001);
//        input.input.mi.dwFlags = new DWORD(MOUSEEVENTF_LEFTUP | WindowHook.User32.MOUSEEVENTF_MOVE
//                | WindowHook.User32.MOUSEEVENTF_VIRTUALDESK);
//        input.input.mi.time = new DWORD(0);
//
//        INPUT[] inArray = new INPUT[]{input};
//
//        int cbSize = input.size(); // mouse input struct size
//        DWORD nInputs = new DWORD(2); // number of inputs
//        DWORD result = WindowHook.User32.INSTANCE.SendInput(nInputs , inArray, cbSize);
//
//        System.out.println(result);
//    }

    public static void mouseMove(Point xy) {
        jnaLib.SetCursorPos(xy.x, xy.y);
        jnaLib.mouse_event(0, xy.x, xy.y, 0, 0);
    }


    public static void mouseMove(int x, int y) {
//        jnaLib.SetCursorPos(x, y);
        jnaLib.mouse_event(MOUSEEVENTF_MOVE, x, y, 0, 0);
    }

    public static void mouseClick(boolean flag) throws InterruptedException {
        mousePress(flag);
        Thread.sleep(Config.clickDelay);
        mouseRelease(flag);
    }

    public static void mouseClick(int x, int y) throws InterruptedException {
        jnaLib.SetCursorPos(x, y);

        if (!Config.mouseSwapped) {
            jnaLib.mouse_event(MOUSEEVENTF_LEFTDOWN, x, y, 0, 0);
        } else {
            jnaLib.mouse_event(MOUSEEVENTF_RIGHTDOWN, x, y, 0, 0);
        }

        Thread.sleep(Config.clickDelay);

        if (!Config.mouseSwapped) {
            jnaLib.mouse_event(MOUSEEVENTF_LEFTUP, x, y, 0, 0);
        } else {
            jnaLib.mouse_event(MOUSEEVENTF_RIGHTUP, x, y, 0, 0);
        }
    }


    public static void mousePress(int x, int y) {
        jnaLib.SetCursorPos(x, y);

        if (!Config.mouseSwapped) {
            jnaLib.mouse_event(MOUSEEVENTF_LEFTDOWN, x, y, 0, 0);
        } else {
            jnaLib.mouse_event(MOUSEEVENTF_RIGHTDOWN, x, y, 0, 0);
        }
    }

    public static void mousePress(boolean flag) {
        Point xy = new Point(0, 0);

        if (flag) {
            xy = MouseInfo.getPointerInfo().getLocation();
            jnaLib.SetCursorPos(xy.x, xy.y);
            if (!Config.mouseSwapped) {
                jnaLib.mouse_event(MOUSEEVENTF_LEFTDOWN | MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);
            } else {
                jnaLib.mouse_event(MOUSEEVENTF_RIGHTDOWN| MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);
            }
        } else {
            if (!Config.mouseSwapped) {
                jnaLib.mouse_event(MOUSEEVENTF_LEFTDOWN, xy.x, xy.y, 0, 0);
            } else {
                jnaLib.mouse_event(MOUSEEVENTF_RIGHTDOWN, xy.x, xy.y, 0, 0);
            }
        }
    }

    public static void mousePress(Point xy) {
        jnaLib.SetCursorPos(xy.x, xy.y);

        if (!Config.mouseSwapped) {
            jnaLib.mouse_event(MOUSEEVENTF_LEFTDOWN | MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);
        } else {
            jnaLib.mouse_event(MOUSEEVENTF_RIGHTDOWN | MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);
        }
    }

    public static void mouseRelease(boolean flag) {
        Point xy = new Point(0, 0);

        if (flag) {
            xy = MouseInfo.getPointerInfo().getLocation();
            jnaLib.SetCursorPos(xy.x, xy.y);

            if (!Config.mouseSwapped) {
                jnaLib.mouse_event(MOUSEEVENTF_LEFTUP | MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);
            } else {
                jnaLib.mouse_event(MOUSEEVENTF_RIGHTDOWN | MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);            }
        } else {
            if (!Config.mouseSwapped) {
                jnaLib.mouse_event(MOUSEEVENTF_LEFTUP, xy.x, xy.y, 0, 0);
            } else {
                jnaLib.mouse_event(MOUSEEVENTF_RIGHTUP, xy.x, xy.y, 0, 0);
            }
        }
    }

    public static void mouseRelease(Point xy) {
        jnaLib.SetCursorPos(xy.x, xy.y);
        if (!Config.mouseSwapped) {
            jnaLib.mouse_event(MOUSEEVENTF_LEFTUP | MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);
        } else {
            jnaLib.mouse_event(MOUSEEVENTF_RIGHTUP | MOUSEEVENTF_ABSOLUTE, xy.x, xy.y, 0, 0);
        }
    }

}
