package org.mkag.pf;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mkag.pf.models.FileLocation;
import org.mkag.pf.models.Transaction;

class ExcelProcessorImpTest {

  public static final String LOCATION = new String(
      "D:/markg/Downloads/11133760_20212429_0203.xlsx");


  @Test
  void testProcessDoesNotReturnANull() {
    ExcelProcessorImp underTest = new HalifaxExcelProcessor();
    Map<String, List<Transaction>> resultMap = underTest
        .process(LOCATION);

    Assertions.assertNotNull(resultMap);
  }

  @Test
  void testProcessDataReturnTheCorrectCollumHalifax() {
    ExcelProcessorImp underTest = new HalifaxExcelProcessor();
    Map<String, List<Transaction>> resultMap = underTest.process(FileLocation.HALIFAX.locations);

    Assertions.assertEquals("29/03/2021", resultMap.get("29/03/2021").get(0).getDate());
    Assertions.assertEquals("11133760", resultMap.get("29/03/2021").get(0).getAccountNumber());
    Assertions.assertEquals("CAPITAL ONE EUROPE", resultMap.get("29/03/2021").get(0).getName());
    Assertions.assertEquals("'11-00-74", resultMap.get("29/03/2021").get(0).getSortCode());

  }

  @Test
  void testProcessDataReturnTheCorrectCollumMonzo() {
    ExcelProcessorImp underTest = new MonzoExcelProcessor();
    Map<String, List<Transaction>> resultMap = underTest.process(FileLocation.MONZO.locations);

    Assertions.assertEquals("07/01/2020", resultMap.get("07/01/2020").get(0).getDate());
    Assertions.assertEquals("28617113", resultMap.get("07/01/2020").get(0).getAccountNumber());
    Assertions.assertEquals("McDonald's", resultMap.get("07/01/2020").get(0).getName());
    Assertions.assertEquals("'04-00-04", resultMap.get("07/01/2020").get(0).getSortCode());

  }
  @Test
  void testProcessDataReturn(){
    ExcelProcessorImp underTest = new HalifaxExcelProcessor();
    System.out.println(underTest.findTransactions("29/03/2021","22/03/2021","04/01/2021"));
  }


}