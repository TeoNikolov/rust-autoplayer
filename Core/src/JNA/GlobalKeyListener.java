package JNA;

import Modules.Config;

import Modules.Mouse;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalKeyListener {

    private static ArrayList<int[]> temp;

    public static void initGlobalKeyListener() {
        NativeKeyListener globalKey = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                int kc = nativeKeyEvent.getRawCode();
                int m = nativeKeyEvent.getModifiers();


                if (kc == Config.scanHK) {
                    if (Config.scanner == null) {
                        System.out.println("Thread is null.");
                        Config.scanner = new Thread() {
                            @Override
                            public void run() {
                                int mickeys = 0;

                                while (true) {
                                    long start = System.currentTimeMillis();

                                    if (this.isInterrupted()) {
                                        break;
                                    }

                                    Mouse.mouseMove(0, Config.mouseYMove);

                                    try {
                                        sleep(Config.screencapDelay);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        break;
                                    }

                                    ArrayList<int[]> l = ImagePack.getPixels(ImagePack.getScreenshot(new Rectangle(0, 0,
                                            (int) Config.screenSize.getWidth(), (int) Config.screenSize.getHeight())));

                                    if (temp != null) {
                                        if (ImagePack.compare(temp, l) && mickeys > Config.loopGrace) {
                                            break;
                                        }
                                    }

                                    temp = l;

                                    long finish = System.currentTimeMillis();
                                    int r = (int) (finish - start);

                                    System.out.println(r);
                                    mickeys++;
                                }

                                temp = null;
                                Config.scanner = null;
                                System.out.println("Thread ended.");
                            }
                        };
                    }

                    Config.scanner.start();
                } else if (kc == Config.panicHK) {
                    if (Config.scanner != null) {
                        System.out.println("Thread is not null.");
                        Config.scanner.interrupt();
                    }
                }
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

            }

            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

            }
        };

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            System.out.println("A problem occurred during initialization. OS Type and Architecture?");
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(globalKey);
    }

}
