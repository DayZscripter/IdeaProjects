import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        CheckingReports checkingReports = new CheckingReports();

        while (true) {
            myMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                for (int i = 1; i< 4; i++) {
                    monthlyReport.loadFile(i, "resources/m.20210" + i +".csv");
                }
            } else if (userInput == 2) {
                yearlyReport.loadFile(2021, "resources/y.2021.csv");
            } else if (userInput == 3) {
                if (!(monthlyReport.months.isEmpty() || yearlyReport.years.isEmpty())) {
                    checkingReports.compareMonthlyAndYearlyReports(monthlyReport, yearlyReport);
                } else System.out.println("считайте годовой и месячные отчёты для их сверки");
            } else if (userInput == 4) {
                if (monthlyReport.months.isEmpty())
                    System.out.println("сперва считайте месячные отчеты");
                else monthlyReport.monthStatSout();
            } else if (userInput ==5) {
                if (yearlyReport.years.isEmpty())
                    System.out.println("сперва считайте годовой отчет");
                else yearlyReport.printYearStatistic(2021);
            } else if (userInput == 999) {
                break;
            } else System.out.println("такой команды пока нет");
        }
    }

    private  static void myMenu() {
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("999. Закрыть программу");
    }
}