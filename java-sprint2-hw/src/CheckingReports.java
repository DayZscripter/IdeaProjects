import java.util.ArrayList;
import java.util.HashMap;

public class CheckingReports {

    HashMap<Integer, Integer> monthlyIncome = new HashMap<>();
    HashMap<Integer, Integer> monthlyExpenses = new HashMap<>();
    HashMap<Integer, Integer> yearlyIncome = new HashMap<>();
    HashMap<Integer, Integer> yearlyExpenses = new HashMap<>();

    public void fillInDataByMonth(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        for (Integer monthNumber : monthlyReport.months.keySet()) {
            ArrayList<MonthlyReport.MonthData> monthData = monthlyReport.months.get(monthNumber); //MonthData(itemName, isExpense  и тд)
            for (MonthlyReport.MonthData monthData1 : monthData) {
                if (monthData1.isExpense == false) {
                    monthlyIncome.put(monthNumber, monthlyIncome.getOrDefault(monthNumber, 0) +
                            monthData1.quantity * monthData1.sumOfOne);
                } else {
                    monthlyExpenses.put(monthNumber, monthlyExpenses.getOrDefault(monthNumber, 0) +
                            monthData1.quantity * monthData1.sumOfOne);
                }
            }
        }
        for (Integer yearNumber : yearlyReport.years.keySet()) {
            ArrayList<YearData> yearData = yearlyReport.years.get(yearNumber); // YearData(month, amount, isExpense)
            for (YearData yearData1 : yearData) {
                if (yearData1.isExpense == false) {
                    yearlyIncome.put(yearData1.month, yearData1.amount);
                } else {
                    yearlyExpenses.put(yearData1.month, yearData1.amount);
                }
            }
        }
    }

    public void compareMonthlyAndYearlyReports(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        fillInDataByMonth(monthlyReport, yearlyReport);
        boolean isTrueCompare = true;
        for (Integer monthNumber : monthlyIncome.keySet()) {
            if (!monthlyIncome.get(monthNumber).equals(yearlyIncome.get(monthNumber))) {
                isTrueCompare = false;
                System.out.println("При проверке отчетов о доходах, обнаружено несоответствие за " +
                        MonthName.monthName(monthNumber));
            }
        }
        for (Integer monthNumber : monthlyIncome.keySet()) {
            if (!monthlyExpenses.get(monthNumber).equals(yearlyExpenses.get(monthNumber))) {
                isTrueCompare = false;
                System.out.println("При проверке отчетов о расходах, обнаружено не соответствие за " +
                        MonthName.monthName(monthNumber));
            }
        }
        if (isTrueCompare == true) System.out.println("Проверка прошла успешно");
    }

    public static class MonthName {
        public static String monthName(Integer number) {
            switch (number) {   // оператор свич для названий месяцев
                case 1:
                    return "Январь";
                case 2:
                    return "Февраль";
                case 3:
                    return "Март";
                case 4:
                    return "Апрель";
                case 5:
                    return "Май";
                case 6:
                    return "Июнь";
                case 7:
                    return "Июль";
                case 8:
                    return "Август";
                case 9:
                    return "Сентябрь";
                case 10:
                    return "Октябрь";
                case 11:
                    return "Ноябрь";
                case 12:
                    return "Декабрь";
            }
            return null;
        }
    }
}