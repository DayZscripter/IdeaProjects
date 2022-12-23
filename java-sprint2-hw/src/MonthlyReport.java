import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    HashMap<Integer, ArrayList<MonthData>> months = new HashMap<>();

    public void loadFile(int monthNumber, String path) {    //загрузгужаем файл, сплитим, парсим
        String content = readFileContents(path);
        String[] lines = content.split("\n");
        ArrayList<MonthData> monthData = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",");   //разделяем по запятой
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]); //распарсил поля
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            MonthData month = new MonthData(itemName, isExpense, quantity, sumOfOne);
            monthData.add(month);
        }
        months.put(monthNumber, monthData);
    }

    private String readFileContents(String path) {   // метод считывания из файла (один из вариантов)
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }


    public HashMap<String, Integer> getMostProfitableItem(Integer monthNumber) {
        HashMap<String, Integer> profitItems = new HashMap<>();
        ArrayList<MonthData> monthData = months.get(monthNumber);
        String mostProfitItem = null;
        for (MonthData monthDataDatum : monthData) {
            if (!monthDataDatum.isExpense) {
                profitItems.put(monthDataDatum.itemName, profitItems.getOrDefault(monthDataDatum.itemName, 0) +
                        monthDataDatum.sumOfOne * monthDataDatum.quantity);
            }
        }
        for (String itemName : profitItems.keySet()) {
            if (mostProfitItem == null) {
                mostProfitItem = itemName;
                continue;
            }
            if (profitItems.get(mostProfitItem) < profitItems.get(itemName)) {
                mostProfitItem = itemName;
            }
        }
        Integer maxProfit = profitItems.get(mostProfitItem);
        profitItems.clear();
        profitItems.put(mostProfitItem, maxProfit);
        return profitItems;
    }

    public void monthStatSout() {   // вывод статы за месяц
        for (Integer monthNumber : months.keySet()) {
            System.out.println(CheckingReports.MonthName.monthName(monthNumber));
            HashMap<String, Integer> profileItems = getMostProfitableItem(monthNumber);
            for (String itemName : profileItems.keySet()) {
                System.out.println("Самый прибыльный товар, " + itemName +
                        ", был продан на сумму " + profileItems.get(itemName) + " рублей");
            }
            HashMap<String, Integer> expensiveItems = getHighestExpense(monthNumber);
            for (String itemName : expensiveItems.keySet()) {
                System.out.println("Самая большая трата пришлась на, " + itemName +
                        ", был продан на сумму " + expensiveItems.get(itemName) + " рублей");
            }
        }
    }

    private HashMap<String, Integer> getHighestExpense(Integer monthNumber) {
        HashMap<String, Integer> profitItems = new HashMap<>();
        ArrayList<MonthData> monthData = months.get(monthNumber);
        String mostProfitItem = null;
        for (MonthData monthDataDatum : monthData) {
            if (monthDataDatum.isExpense) {
                profitItems.put(monthDataDatum.itemName, profitItems.getOrDefault(monthDataDatum.itemName, 0) +
                        monthDataDatum.sumOfOne * monthDataDatum.quantity);
            }
        }
        for (String itemName : profitItems.keySet()) {
            if (mostProfitItem == null) {
                mostProfitItem = itemName;
                continue;
            }
            if (profitItems.get(mostProfitItem) < profitItems.get(itemName)) {
                mostProfitItem = itemName;
            }
        }
        Integer maxProfit = profitItems.get(mostProfitItem);
        profitItems.clear();
        profitItems.put(mostProfitItem, maxProfit);
        return profitItems;
    }


    class MonthData {   //класс для хранения инфы из  мес.отчетов
        String itemName;
        boolean isExpense;
        int quantity;
        int sumOfOne;

        public MonthData(String itemName, boolean isExpense, int quantity, int sumOfOne) { //конструктор
            this.itemName = itemName;
            this.isExpense = isExpense;
            this.quantity = quantity;
            this.sumOfOne = sumOfOne;
        }
    }
}