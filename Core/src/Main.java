import JNA.GlobalKeyListener;
import JNA.GlobalMouseHook;
import Modules.Config;

public class Main {

    private static GlobalMouseHook mouseHook;

    public static void main(String[] args) {
        mouseHook = new GlobalMouseHook();
        mouseHook.setMouseHook();

        System.out.println(Config.postClickDelay);

        GlobalKeyListener.initGlobalKeyListener();
        setCloseOperation();
    }

    private static void setCloseOperation() {
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
            }
        });
    }

}
