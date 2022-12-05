public class StepTracker {

    int defaultPlan = 10000;
    int[] daysData;
    int[][] monthData;                                      //обьявили переменную массива

    public StepTracker() {                                  //конструктор
        this.daysData = new int[30];
        this.monthData = new int[12][daysData.length];
    }

    public void inputMonthDayStepsFunction(int inputMonth, int inputDay, int inputSteps) {
        if (checkerMonthDay(inputMonth, inputDay) && inputSteps>0) {
            this.monthData[inputMonth - 1][inputDay - 1] = inputSteps;
            System.out.println("сохранено ^_^");
        } else {
            System.out.println("данные не корректны, попробуйте еще раз");
        }
    }

    public void statMonthFunction(int numbMonth) {                  // 2- выводит статистику...
        if (numbMonth > 0 && numbMonth < 13) {
            countStepsPerDay(numbMonth);
            System.out.println();
            System.out.println("общее количество шагов в месяц - " + totalStepsInMonth(numbMonth));
            maxStepsInMonth(numbMonth);
            System.out.println("среднее количество шагов за месяц -  " + avarageStepsPerMonth(numbMonth));
            System.out.println("ваш километраж за месяц - " + Converter.stepsToKmConvert(totalStepsInMonth(numbMonth)));
            System.out.println("сожжено колокалорий за месяц - " + Converter.stepsInCaloriesConvert(totalStepsInMonth(numbMonth)));
            System.out.println("ваша лучшая серия шагов - " + bestSeries(numbMonth));
        }
    }

    public void countStepsPerDay(int month) { //считает колличество шагов за каждый день
        for (int i = 0; i < monthData[month - 1].length; i++) {
            System.out.print((i + 1) + " день: " + monthData[month - 1][i] + ", ");
        }
    }

    public int totalStepsInMonth(int month) { //общее колличество шагов в месяц
        int totalStepsPerMonth = 0;
        for (int i = 0; i < monthData[month - 1].length; i++) {
            totalStepsPerMonth = totalStepsPerMonth + monthData[month - 1][i];
        }
        return totalStepsPerMonth;
    }

    public void maxStepsInMonth(int month) { //максимальное колличество пройденных шагов в месяце
        int maxSteps = 0;
        for (int i = 0; i < monthData[month - 1].length; i++) {
            if (monthData[month - 1][i] > maxSteps) {
                maxSteps = monthData[month - 1][i];
            }
        }
        System.out.println("максимальное колличество шагов в этом месяце - " + maxSteps);
    }

    double avarageStepsPerMonth(int month) { //среднее колличество пройденных шагов а месяце
        return Math.rint(totalStepsInMonth(month) / 30.0);
    }

    int bestSeries(int month) {
        int currentSr = 0;
        int bestSr = 0;
        for (int i = 0; i < monthData[month - 1].length; i++) {
            if (monthData[month - 1][i] >= defaultPlan) {
                currentSr++;
                if (currentSr > bestSr) {
                    bestSr = currentSr;
                } else {
                    currentSr = 0;
                }
            }
        }
        return bestSr;
    }

    void changeGoal(int newDefaultPlan) {
        int oldGoal = defaultPlan;
        if (newDefaultPlan > 0) {
            defaultPlan = newDefaultPlan;
            System.out.println("прошлая цель - "+ oldGoal);
            System.out.println("ваша новая цель - " + defaultPlan);
        } else System.out.println("введите число больше нуля");
    }

    public boolean checkerMonthDay(int month, int days) {
        return (month > 0 && month < 13 && days > 0 && days < 31);
    }

    //private boolean checkerSteps(int steps) return (steps > 0);

}