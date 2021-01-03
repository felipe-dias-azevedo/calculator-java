package com.felipe;

public enum Operators {

    ADDITION("+", 1, false),
    SUBTRACTION("-", 2, false),
    MULTIPLICATION("×", 3, false),
    DIVISION("÷", 4, true);

    private Operators(String description, Integer number, Boolean showDecimals) {
        this.description = description;
        this.number = number;
        this.showDecimals = showDecimals;
    }

    private String description;
    private Integer number;
    private Boolean showDecimals;

    public String getDescription() {
        return description;
    }

    public Integer getNumber() {
        return number;
    }

    public Boolean getShowDecimals() {
        return showDecimals;
    }
}
