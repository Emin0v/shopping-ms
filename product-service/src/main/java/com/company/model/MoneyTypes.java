package com.company.model;

public enum MoneyTypes {

    USD("Dolar", "$"),
    EUR("Euro", "Eur"),
    TL("Turk Lirasi", "Tl");

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
