package JNA;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;

public class WindowHook {

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        boolean SetCursorPos(int x, int y);
        void mouse_event(int dwFlags, int dx, int dy, int dwData, int dwExtraInfo);
        void keybd_event(int bVK, int bScan, int dwFlags, int dwExtraInfo);
        WinDef.INT_PTR SetWindowsHookEx(int idHook, LowLevelMouseProc callback, WinDef.INT_PTR hInstance, WinDef.UINT threadId);

        interface LowLevelMouseProc extends WinUser.HOOKPROC {
            WinDef.LRESULT callback(int nCode, WinDef.WPARAM wParam, GlobalMouseHook.MOUSEHOOKSTRUCT lParam);
        }

        /// DEBUG ///

        long MOUSEEVENTF_MOVE = 0x0001L;
        long MOUSEEVENTF_VIRTUALDESK = 0x4000L;
        long MOUSEEVENTF_ABSOLUTE = 0x8000L;

        WinDef.DWORD SendInput(WinDef.DWORD dWord, WinUser.INPUT[] input, int cbSize);

        int SM_CXSCREEN = 0x0;
        int SM_CYSCREEN = 0x1;
        int GetSystemMetrics(int index);

        /// ***** ///

        HWND FindWindowA(String winClass, String title);
        HWND SetActiveWindow(HWND hwnd);
//        boolean ShowWindow(HWND hWnd, int nCmdShow);
//        boolean BringWindowToTop(HWND hWnd);
        boolean SetForegroundWindow(HWND hWnd);


    }

}
