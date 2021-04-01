package org.mkag.pf.models;

public enum FileLocation {
  MONZO("D:/markg/Downloads/MonzoTransactions.xlsx"),
  HALIFAX("D:/markg/Downloads/11133760_20212429_0203.xlsx");

  public String locations;

  FileLocation(String locations){this.locations = locations;}

  @Override
  public String toString() {
    return "FileLocation{" +
        "locations='" + locations + '\'' +
        '}';
  }


}
