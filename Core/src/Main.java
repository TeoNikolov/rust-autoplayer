import JNA.GlobalKeyListener;
import JNA.GlobalMouseHook;

public class Main {

    private static GlobalMouseHook mouseHook;

    public static void main(String[] args) {
        mouseHook = new GlobalMouseHook();
        mouseHook.setMouseHook();

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
