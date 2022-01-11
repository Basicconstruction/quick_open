package frame;

public class Utils {
    public static int y = 39;//maybe for java1.8; 43 for java 17
    public static int x = 16;
    public static UiStarter uiStarter = new UiStarter();
    public static TemporaryCar temporaryCar = new TemporaryCar(uiStarter.getX());
}
