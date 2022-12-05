public class Converter {

    public static double stepsToKmConvert(int steps) { //исправлено с return Math.rint(steps * 0.75 / 1000); на steps * 0.00075
        return Math.rint(steps * 0.00075);
    }
    public static int stepsInCaloriesConvert(int steps) {   // исправлено с x * 50 / 1000 на x * 0.05
        return (int) (steps * 0.05);
    }
}