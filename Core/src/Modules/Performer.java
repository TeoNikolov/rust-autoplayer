package Modules;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class Performer {

    private static boolean relative = true; // Disable to test absolute coordinates
    private static int nNMLoc = Config.mLoc;

    public static void calcMovement(int x, int y) {
        if (relative) {
            Config.mLoc -= (y - Config.initMLoc.y)/3;
            nNMLoc -= (y - Config.initMLoc.y)/3;
        } else {
            Config.mLoc -= (y - Config.initMLoc.y + Config.mLoc);
            nNMLoc -= (y - Config.initMLoc.y + Config.mLoc);
        }

        if (Config.mLoc < 0) {
            Config.mLoc = 0;
        } else if (Config.mLoc > Config.mickeys*Math.abs(Config.mouseYMove)) {
            Config.mLoc = Config.mickeys*Math.abs(Config.mouseYMove);
        }

        if (nNMLoc < 0) {
            nNMLoc = 0;
        }

//        System.out.println("NMLoc: " + Config.mLoc + " Mouse Pos: " + y + " Default Pos: " + Config.initMLoc.y);
//        System.out.println("N-NMLoc: " + nNMLoc);
    }

    public static void performTitanic() throws InterruptedException {
        playNote(Config.C, 1.5);
        playNote(Config.C, 0.5);
        playNote(Config.C, 1);
        playNote(Config.C, 1);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.C, 1);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.D, 1);

        playNote(Config.E, 2);
        playNote(Config.D, 2);

        // Bar

        playNote(Config.C, 1.5);
        playNote(Config.C, 0.5);
        playNote(Config.C, 1);
        playNote(Config.C, 1);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.C, 1);

        playNote(Config.G, 8);

        // Bar

        playNote(Config.C, 1.5);
        playNote(Config.C, 0.5);
        playNote(Config.C, 1);
        playNote(Config.C, 1);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.C, 1);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.D, 1);

        playNote(Config.E, 2);
        playNote(Config.D, 2);

        // Bar

        playNote(Config.C, 1.5);
        playNote(Config.C, 0.5);
        playNote(Config.C, 1);
        playNote(Config.C, 1);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.C, 1);

        playNote(Config.G, 4);

        playNote(Config.A, 2);
        playNote(Config.B, 2);

        // Bar

        playNote(Config.C, 4);

        playNote(Config.D, 3);
        playNote(Config.G, 1);

        playNote(Config.G, 2);
        playNote(Config.F, 1);
        playNote(Config.E, 0.5);
        playNote(Config.D, 2.5);
        playNote(Config.E, 1.5);
        playNote(Config.F, 0.5);

        // Bar

        playNote(Config.E, 2.5);
        playNote(Config.D, 1);
        playNote(Config.C, 0.5);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.B, 1);

        playNote(Config.A, 3);
        playNote(Config.B, 0.5);
        playNote(Config.A, 0.5);

        playNote(Config.G, 2);
        playNote(Config.F0, 2);

        // Bar

        playNote(Config.C, 4);

        playNote(Config.D, 3);
        playNote(Config.G, 1);

        playNote(Config.G, 2);
        playNote(Config.F, 1);
        playNote(Config.E, 0.5);
        playNote(Config.D, 2.5);
        playNote(Config.E, 1.5);
        playNote(Config.F, 0.5);

        // Bar

        playNote(Config.E, 2);
        playNote(Config.D, 1.5);
        playNote(Config.C, 0.5);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.B, 1);

        playNote(Config.B, 1);
        playNote(Config.C, 2);
        playNote(Config.D, 1);

        playNote(Config.E, 2);
        playNote(Config.D, 2);

        // Bar

        playNote(Config.C, 1);
    }

    public static void performDm() throws InterruptedException {
        playNote(Config.D0, 1);
        playNote(Config.E0, 1);
        playNote(Config.F0, 1);
        playNote(Config.G, 1);
        playNote(Config.A, 1);
        playNote(Config.AS, 1);
        playNote(Config.C, 1);
        playNote(Config.D, 1);
    }

    public static void performTestSong2() throws InterruptedException {

        playNote(Config.C, 1);
        playNote(Config.C, 0.25);
        playNote(Config.D, 1.25);
        playNote(Config.C, 1.5);
        //
        playNote(Config.F, 1.25);
        playNote(Config.E, 2.5);
        //
        playNote(Config.C, 1);
        playNote(Config.C, 0.25);
        playNote(Config.D, 1.25);
        playNote(Config.C, 1.5);
        //
        playNote(Config.G, 1.25);
        playNote(Config.F0, 2.5);
        //
        playNote(Config.C, 1);
        playNote(Config.C, 0.25);
        playNote(Config.C, 1.25);
        playNote(Config.A, 1.5);
        //
        playNote(Config.F0, 1.3);
        playNote(Config.E0, 1.3);
        playNote(Config.D0, 1.4);
        //
        playNote(Config.AS, 1);
        playNote(Config.AS, 0.25);
        playNote(Config.A, 1.25);
        playNote(Config.F0, 1.5);
        //
        playNote(Config.G, 1.25);
        playNote(Config.F0, 2.5);
    }

    public static void performTestSong() throws InterruptedException {
        playNote(Config.GS, 0.5);
        playNote(Config.F0, 7);
        playNote(Config.G, 0.5);

        playNote(Config.GS, 0.5);
        playNote(Config.F0, 3.5);
        playNote(Config.AS, 1);
        playNote(Config.CS, 1);
        playNote(Config.C, 1);
        playNote(Config.DS0, 1);

        playNote(Config.GS, 0.5);
        playNote(Config.F0, 6);
        playNote(Config.AS, 0.5);
        playNote(Config.DS, 0.5);

        playNote(Config.C, 4.5);
        playNote(Config.CS, 1);
        playNote(Config.C, 1);
        playNote(Config.GS, 0.5);
        playNote(Config.G, 0.5);

        playNote(Config.DS0, 1);
    }

    private static void playNote(double note, double beat) throws InterruptedException {
        int sDelta = (int) ((Config.clickDelay + Config.postResetDelay +
                        Config.postClickDelay + Config.postSetDelay) * beat);

        if (interrupted()) {
            throw new InterruptedException();
        }

        Mouse.mouseMove(0, Config.mickeys*4);
        sleep(Config.postResetDelay);
        Mouse.mouseMove(0, (int) (Config.mickeys*note*2));
        sleep(Config.postSetDelay);
        Mouse.mouseClick(false);

        sleep(Math.abs(sDelta - Config.postClickDelay));

        System.out.println("sDelta = " + sDelta + " | Beat: " + beat);
    }

    private static void playGhostNote(double beat) throws InterruptedException {
        sleep((long) ((Config.postResetDelay + Config.postSetDelay + Config.postClickDelay) * beat));
    }

}
