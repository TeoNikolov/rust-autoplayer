package JNA;

import Modules.Config;

import Modules.Mouse;
import Modules.Performer;
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
    private static boolean forced = false;

    public static void initGlobalKeyListener() {
        NativeKeyListener globalKey = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                int kc = nativeKeyEvent.getRawCode();
                int m = nativeKeyEvent.getModifiers();


                if (kc == Config.scanHK || kc == Config.forceHK) {
                    if (Config.scanner == null) {

                        if (kc == Config.forceHK) {
                            forced = true;
                        } else {
                            forced = false;
                            Config.mickeys = 0;
                        }

                        System.out.println("Thread is null.");
                        Config.scanner = new Thread() {
                            @Override
                            public void run() {

                                while (true) {
                                    long start = System.currentTimeMillis();

                                    if (this.isInterrupted()) {
                                        break;
                                    }

                                    Mouse.mouseMove(0, Config.mouseYMove);

                                    try {
                                        sleep(Config.screencapDelay);
                                    } catch (InterruptedException e) {

                                        break;
                                    }

                                    ArrayList<int[]> l = ImagePack.getPixels(ImagePack.getScreenshot(new Rectangle(0, 0,
                                            (int) Config.screenSize.getWidth(), (int) Config.screenSize.getHeight())));

                                    if (temp != null) {
                                        if (ImagePack.compare(temp, l) && (Config.mickeys > Config.loopGrace || forced)) {
                                            break;
                                        }
                                    }

                                    temp = l;

                                    long finish = System.currentTimeMillis();
                                    int r = (int) (finish - start);

                                    System.out.println(r);
                                    Config.mickeys++;
                                }

                                // Wait for mouse to auto-adjust, then capture default location
                                try {
                                    sleep(50);
                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
                                }

                                Config.initMLoc = MouseInfo.getPointerInfo().getLocation();
                                System.out.println("Mickeys: " + Config.mickeys);
                                Config.scanner = null;
                                System.out.println("Thread ended.");
                            }
                        };
                    }

                    Config.scanner.start();
                } else if (kc == Config.panicHK) {
                    if (Config.scanner != null) {
                        Config.scanner.interrupt();
                    }

                    if (Config.performerThread != null) {
                        Config.performerThread.interrupt();
                    }
                } else if (kc == 105) {
                    Mouse.mouseMove(0, Config.mouseYMove);
                } else if (kc == 104) {
                    try {
                        Performer.performDm();
                    } catch (InterruptedException ignored) {

                    }
                } else if (kc == 103) {
                    Config.mouseSwapped = !Config.mouseSwapped;
                } else if (kc == 102) {
                    if (Config.performerThread == null) {
                        Config.performerThread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Performer.performTestSong();
                                } catch (InterruptedException ignored) {

                                } finally {
                                    Config.performerThread = null;
                                }
                            }
                        };
                    }

                    Config.performerThread.start();
                } else if (kc == 101) {
                    if (Config.performerThread == null) {
                        Config.performerThread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Performer.performTestSong2();
                                } catch (InterruptedException ignored) {

                                } finally {
                                    Config.performerThread = null;
                                }
                            }
                        };
                    }

                    Config.performerThread.start();
                } else if (kc == 100) {
                    if (Config.performerThread == null) {
                        Config.performerThread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Performer.performTitanic();
                                } catch (InterruptedException ignored) {

                                } finally {
                                    Config.performerThread = null;
                                }
                            }
                        };
                    }

                    Config.performerThread.start();
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
