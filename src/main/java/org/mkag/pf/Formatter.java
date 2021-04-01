package org.mkag.pf;

import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;

public class Formatter {
  SimpleDateFormat simpleDateFormat;
  XSSFDataFormat dataFormat;

  public Formatter(Workbook wb) {
    dataFormat = (XSSFDataFormat) wb.createDataFormat();
    dataFormat.putFormat((short) 3,"##########");
    simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
  }
}
