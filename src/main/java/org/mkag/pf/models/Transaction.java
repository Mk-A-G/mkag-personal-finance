package org.mkag.pf.models;



public class Transaction {

  private String date;
  private String name;
  private String accountNumber;
  private String sortCode;

  public static class Builder {

    private String date;
    private String name;
    private String accountNumber;
    private String sortCode;


    public Builder(String date) {
      this.date = date;
    }

    public Builder name(String name) {
      this.name = name;

      return this;  //By returning the builder each time, we can create a fluent interface.
    }

    public Builder accountNumber(String accountNumber) {
      this.accountNumber = accountNumber;

      return this;
    }

    public Builder sortCode(String sortCode) {
      this.sortCode = sortCode;

      return this;
    }

    public Transaction build() {
      Transaction transaction = new Transaction();
      transaction.date = this.date;
      transaction.name = this.name;
      transaction.accountNumber = this.accountNumber;
      transaction.sortCode = this.sortCode;

      return transaction;
    }
  }
  public Transaction() {
    //Constructor is now private.
  }

  public String getDate() {
    return date;
  }

  public String getName() {
    return name;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getSortCode() {
    return sortCode;
  }



  @Override
  public String toString() {
    return "Transaction{" +
        "date='" + date + '\'' +
        ", name='" + name + '\'' +
        ", accountNumber='" + accountNumber + '\'' +
        ", sortCode='" + sortCode + '\'' +
        '}';
  }
}



