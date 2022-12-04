public class Converter {

    public static double stepsToKmConvert(int x) {
        return Math.rint(x * 0.75 / 1000);
    }
    public static int stepsInCaloriesConvert(int x) {
        return (x * 50 / 1000);
    }
}