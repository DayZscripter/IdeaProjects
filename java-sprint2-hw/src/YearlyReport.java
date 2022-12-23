import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    HashMap<Integer, ArrayList<YearData>> years = new HashMap<>();

    public void loadFile(int yearNumber, String path) {
        ArrayList<YearData> yearData = new ArrayList<>();
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",");   // month, amount, is_expense
            int mounth = Integer.parseInt(parts[0], 10);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearData year = new YearData(mounth, amount, isExpense);
            yearData.add(year);
        }
        years.put(yearNumber, yearData);
    }
    private String readFileContentsOrNull(String path) {  // один из способов считывания из файлов
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public void printYearStatistic(int yearNumber) {
        HashMap<Integer, Integer> incomeYear = new HashMap<>();
        HashMap<Integer, Integer> expensesYear = new HashMap<>();
        HashMap<Integer, Integer> profitYear = new HashMap<>();
        ArrayList<YearData> yearData = years.get(yearNumber);
        System.out.println(yearNumber);

        for (YearData yearData1 : yearData) {
            if (yearData1.isExpense == false) {
                incomeYear.put(yearData1.month, yearData1.amount);
            } else {
                expensesYear.put(yearData1.month, yearData1.amount);
            }
        }
        for (Integer monthNumber : incomeYear.keySet()) {
            if (expensesYear.containsKey(monthNumber)) {
                profitYear.put(monthNumber, incomeYear.get(monthNumber) - expensesYear.get(monthNumber));
            } else {
                profitYear.put(monthNumber, incomeYear.get(monthNumber));
            }
        }
        for (Integer monthNumber : profitYear.keySet()) {
            System.out.println("Прибыль за " + CheckingReports.MonthName.monthName(monthNumber) + " составила: " + profitYear.get(monthNumber) + " руб");
        }
        double sumExpenses = 0;
        for (Integer monthNumber : expensesYear.keySet()) {
            sumExpenses = sumExpenses + expensesYear.get(monthNumber);
        }
        System.out.println("Средний расход за все месяцы составил: " + (sumExpenses / expensesYear.size()) + " руб");

        double sumIncome = 0;
        for (Integer monthNumber : incomeYear.keySet()) {
            sumIncome = sumIncome + incomeYear.get(monthNumber);
        }
        System.out.println("Средний доход за все месяцы составил: " + (sumIncome / incomeYear.size()) + " руб");
    }
}




class YearData {
    int month;
    int amount;
    boolean isExpense;

    public YearData(int month, int amount, boolean isExpense) {  // конструктор
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}