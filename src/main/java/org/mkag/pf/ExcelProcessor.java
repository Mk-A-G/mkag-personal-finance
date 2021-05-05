package org.mkag.pf;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.mkag.pf.models.Transaction;

public interface ExcelProcessor {

  Map<String, List<Transaction>> process(String location) throws IOException;

  Map<String, List<Transaction>> findTransactions(String... dates);

  String uploadMonzoDocs(String doc);

  String uploadHalifaxDoc(String doc);

  List<String> listMonthlyProfits(String doc);
}
