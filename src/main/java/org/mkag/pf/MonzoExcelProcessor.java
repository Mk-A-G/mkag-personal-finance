package org.mkag.pf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mkag.pf.models.Transaction;
import org.mkag.pf.models.Transaction.Builder;

public class MonzoExcelProcessor extends ExcelProcessorImp {

  public Map<String, List<Transaction>> processData(Map<Integer, ArrayList<String>> data) {
    HashMap<String, List<Transaction>> fullTable = new HashMap<>();

    for (Entry<Integer, ArrayList<String>> entrySet : data.entrySet()) {
      if (entrySet.getKey() == 0 || entrySet.getValue().get(1).equals(" ")) {
        continue;
      }

      ArrayList<String> cellList = data.get(entrySet.getKey());
      Transaction transaction = new Builder(cellList.get(1)).sortCode("'04-00-04")
          .accountNumber("28617113").name(cellList.get(4)).build();
      if (fullTable.containsKey(cellList.get(1))) {
        List<Transaction> list = fullTable.get(cellList.get(1));
        list.add(transaction);
      } else {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        fullTable.put(cellList.get(1), transactions);
      }
    }
    System.out.println(fullTable);
    return fullTable;
  }

  @Override
  public Map<String, List<Transaction>>  findTransactions(String... dates) {
    return null;
  }
}
