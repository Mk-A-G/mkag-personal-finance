package org.mkag.pf;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mkag.pf.models.FileLocation;
import org.mkag.pf.models.Transaction;


public abstract class ExcelProcessorImp implements ExcelProcessor {

  @Override
  public Map<String, List<Transaction>> process(String location) {
    Map<Integer, ArrayList<String>> data = null;
    try (FileInputStream file = new FileInputStream(new File(location))) {
      Workbook workbook = new XSSFWorkbook(file);
      Formatter formatter = new Formatter(workbook);
      data = processing(Pair.of(workbook.getSheetAt(0), formatter));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (location.equals(FileLocation.HALIFAX.locations)) {
      HalifaxExcelProcessor halifaxExcelProcessor = new HalifaxExcelProcessor();
      return halifaxExcelProcessor.processData(data);
    }else {
      MonzoExcelProcessor monzoExcelProcessor = new MonzoExcelProcessor();
      return monzoExcelProcessor.processData(data);
    }
  }


  @Override
  public String uploadMonzoDocs(String doc) {
    return null;
  }

  @Override
  public String uploadHalifaxDoc(String doc) {
    return null;
  }

  @Override
  public List<String> listMonthlyProfits(String doc) {
    return Collections.emptyList();
  }


  public Map<Integer, ArrayList<String>> processing(
      Pair<Sheet, Formatter> pairOfWorkBookAndFormatter) {
    Sheet sheet = pairOfWorkBookAndFormatter.getLeft();
    Formatter formatter = pairOfWorkBookAndFormatter.getRight();
    Map<Integer, ArrayList<String>> data = new HashMap<>();

    int i = 0;
    for (Row row : sheet) {
      data.put(i, new ArrayList<>());
      for (Cell cell : row) {
        switch (cell.getCellType()) {
          case STRING:
            data.get(i).add(cell.getRichStringCellValue().getString());
            break;
          case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
              data.get(i).add(formatter.simpleDateFormat.format(cell.getDateCellValue()) + "");
            } else {
              data.get(i).add(cell.getNumericCellValue() + "");
            }
            break;
          default:
            data.get(i).add(" ");
        }
      }
      i++;
    }
    return data;
  }

}
