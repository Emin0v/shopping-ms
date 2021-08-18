package com.company.model;

public enum MoneyTypes {

    USD("Dolar", "$"),
    EUR("Euro", "E"),
    TL("Turk Lirasi", "T");

    private String label;
    private String symbol;

    MoneyTypes(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
