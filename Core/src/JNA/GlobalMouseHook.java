package JNA;

import Modules.Performer;
import com.sun.jna.*;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;
import com.sun.jna.platform.win32.WinUser.MSG;

import java.util.Arrays;
import java.util.List;

public class GlobalMouseHook {

    public final User32 USER32INST;
    public final Kernel32 KERNEL32INST;

    public GlobalMouseHook() {
        if (!Platform.isWindows()) {
            throw new UnsupportedOperationException("Not supported on this platform.");
        }
        USER32INST = User32.INSTANCE;
        KERNEL32INST = Kernel32.INSTANCE;
        mouseHook = hookTheMouse();
        Native.setProtected(true);

    }

    public static LowLevelMouseProc mouseHook;
    public HHOOK hhk;
    public Thread thrd;
    public boolean threadFinish = true;
    public boolean isHooked = false;
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;


    public void unsetMouseHook() {
        threadFinish = true;
        if (thrd.isAlive()) {
            thrd.interrupt();
            thrd = null;
        }
        isHooked = false;
    }

    public boolean isIsHooked() {
        return isHooked;
    }

    public void setMouseHook() {
        thrd = new Thread(() -> {
            try {
                if (!isHooked) {
                    hhk = USER32INST.SetWindowsHookEx(14, mouseHook, KERNEL32INST.GetModuleHandle(null), 0);
                    isHooked = true;
                    MSG msg = new MSG();
                    while ((USER32INST.GetMessage(msg, null, 0, 0)) != 0) {
                        USER32INST.TranslateMessage(msg);
                        USER32INST.DispatchMessage(msg);
                        if (!isHooked)
                            break;
                    }
                } else
                    System.out.println("The Hook is already installed.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
                System.err.println("Caught exception in MouseHook!");
            }
        }, "Named thread");
        threadFinish = false;
        thrd.start();

    }

    private interface LowLevelMouseProc extends HOOKPROC {
        LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam);
    }

    public LowLevelMouseProc hookTheMouse() {
        return (nCode, wParam, info) -> {
            if (nCode >= 0) {
                switch (wParam.intValue()) {
                    case GlobalMouseHook.WM_LBUTTONDOWN:
                        // do stuff
                        break;
                    case GlobalMouseHook.WM_RBUTTONDOWN:
                        //do stuff
                        break;
                    case GlobalMouseHook.WM_MBUTTONDOWN:
                        //do other stuff
                        break;
                    case GlobalMouseHook.WM_LBUTTONUP:
                        //do even more stuff
                        break;
                    case GlobalMouseHook.WM_MOUSEMOVE:
                        Performer.calcMovement(info.pt.x, info.pt.y);
                        break;
                    default:
                        break;
                }
                /****************************DO NOT CHANGE, this code unhooks mouse *********************************/
                if (threadFinish) {
                    USER32INST.PostQuitMessage(0);
                }
                /***************************END OF UNCHANGABLE *******************************************************/
            }
//                return USER32INST.CallNextHookEx(hhk, nCode, wParam, info.getPointer());
            return null;
        };
    }

    public class Point extends Structure {
        @Override
        protected List getFieldOrder() {
            return Arrays.asList("x", "y");
        }

        public class ByReference extends Point implements Structure.ByReference {}
        public NativeLong x;
        public NativeLong y;
    }

    public static class MOUSEHOOKSTRUCT extends Structure {

        @Override
        protected List getFieldOrder() {
            return Arrays.asList("pt", "hwnd", "wHitTestCode", "dwExtraInfo");
        }

        public static class ByReference extends MOUSEHOOKSTRUCT implements Structure.ByReference {
        }

        public POINT pt;
        public HWND hwnd;
        public int wHitTestCode;
        public ULONG_PTR dwExtraInfo;
    }
}