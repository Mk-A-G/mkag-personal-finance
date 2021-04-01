package org.mkag.pf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mkag.pf.models.FileLocation;
import org.mkag.pf.models.Transaction;
import org.mkag.pf.models.Transaction.Builder;

public class HalifaxExcelProcessor extends ExcelProcessorImp {

  public Map<String, List<Transaction>> processData(Map<Integer, ArrayList<String>> data) {
    HashMap<String, List<Transaction>> fullTable = new HashMap<>();

    for (Entry<Integer, ArrayList<String>> entrySet : data.entrySet()) {
      if (entrySet.getKey() == 0) {
        continue;
      }

      ArrayList<String> cellList = data.get(entrySet.getKey());
      Transaction transaction = new Builder(cellList.get(0)).sortCode(cellList.get(2))
          .accountNumber("11133760").name(cellList.get(4)).build();
      if (fullTable.containsKey(cellList.get(0))) {
        List<Transaction> list = fullTable.get(cellList.get(0));
        list.add(transaction);
      } else {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        fullTable.put(cellList.get(0), transactions);
      }
    }

    return fullTable;
  }

  @Override
  public Map<String, List<Transaction>>  findTransactions(String... dates) {
    Map<String, List<Transaction>> allDataForAllDates = new HashMap<>();
    for (String date : dates) {
      allDataForAllDates.put(date, process(FileLocation.HALIFAX.locations).get(date));
    }
    return allDataForAllDates;
  }
}
