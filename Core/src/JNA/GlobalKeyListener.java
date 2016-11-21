package JNA;

import Modules.Config;

import Modules.Mouse;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalKeyListener {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static ArrayList<int[]> temp;

    public static void initGlobalKeyListener() {
        NativeKeyListener globalKey = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                int kc = nativeKeyEvent.getRawCode();
                int m = nativeKeyEvent.getModifiers();


                if (kc == 121) {
                    if (Config.worker == null) {
                        System.out.println("Thread is null.");
                        Config.worker = new Thread() {
                            @Override
                            public void run() {
                                while (true) {
                                    long start = System.currentTimeMillis();

                                    if (this.isInterrupted()) {
                                        break;
                                    }

                                    Mouse.mouseMove(0, -2);

                                    try {
                                        sleep(20);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        break;
                                    }

                                    ArrayList<int[]> l = ImagePack.getPixels(ImagePack.getScreenshot(new Rectangle(0, 0,
                                            (int) screenSize.getWidth(), (int) screenSize.getHeight())));

                                    if (temp != null) {
                                        if (ImagePack.compare(temp, l)) {
                                            break;
                                        }
                                    }

                                    temp = l;

                                    long finish = System.currentTimeMillis();
                                    int r = (int) (finish - start);

//                                    if (r < 500) {
//                                        try {
//                                            sleep(500 - r);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                            break;
//                                        }
//                                    }

                                    System.out.println(r);
                                }

                                temp = null;
                                Config.worker = null;
                                System.out.println("Thread ended.");
                            }
                        };
                    }

                    Config.worker.start();
                } else if (kc == 122) {
                    if (Config.worker != null) {
                        System.out.println("Thread is not null.");
                        Config.worker.interrupt();
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
