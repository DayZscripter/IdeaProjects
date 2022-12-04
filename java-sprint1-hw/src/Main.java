import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 4) {
            if (userInput == 1) {
                System.out.println("введите номер месяца от 1 до 12");
                int inputMonth = scanner.nextInt();
                System.out.println("введите день месяца от 1 до 30");
                int inputDay = scanner.nextInt();
                System.out.println("введите колличество шагов");
                int inputSteps = scanner.nextInt();
                stepTracker.inputMonthDayStepsFunction(inputMonth, inputDay, inputSteps);
            }
            if (userInput == 2) {
                System.out.println("введите номер месяца от 1 до 12");
                int statMonth = scanner.nextInt();
                stepTracker.statMonthFunction(statMonth);
            }
            if (userInput == 3) {
                System.out.println("введите вашу новую цель");
                int inputChangeGoal = scanner.nextInt();
                stepTracker.changeGoal(inputChangeGoal);
            }

            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }

    private static void printMenu() {
        System.out.println("      <<< ООО \"НоскиБегут software\" >>>");
        System.out.println("1 - Ведите колличество шагов за определенный день");
        System.out.println("2 - Показать статистику за определенный месяц");
        System.out.println("3 - Изменить цель по колличеству шагов в день");
        System.out.println("4 - Закрыть приложение");
    }
}