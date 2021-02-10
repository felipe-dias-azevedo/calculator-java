package com.felipe;

import javax.swing.*;
import java.awt.*;

public class App {
    private JPanel panelMain;
    private JButton buttonZero;
    private JButton buttonOne;
    private JButton buttonTwo;
    private JButton buttonThree;
    private JButton buttonFour;
    private JButton buttonFive;
    private JButton buttonSix;
    private JButton buttonSeven;
    private JButton buttonEight;
    private JButton buttonNine;
    private JButton buttonEquals;
    private JButton buttonDot;
    private JButton buttonClear;
    private JButton buttonPlus;
    private JButton buttonMinus;
    private JButton buttonTimes;
    private JButton buttonDivided;
    private JButton buttonClearEntry;
    private JLabel frontPanel;

    private String operationOne = "";
    private String operationTwo = "";
    private Boolean firstOperator = true;
    private Double operationOneNum;
    private Double operationTwoNum;
    private Boolean isRationalNumber = false;
    private Double result;
    private Operators operator;

    public void addNum(String number) {
        if (firstOperator) {
            operationOne += number;
            operationOneNum = Double.valueOf(operationOne);
            frontPanel.setText(operationOne);
        } else {
            operationTwo += number;
            operationTwoNum = Double.valueOf(operationTwo);
            frontPanel.setText(operationOne + " " +  operator.getDescription() + " " + operationTwo);
        }
    }

    public void resetForSecondData() {
        firstOperator = false;
        operationOneNum = Double.valueOf(operationOne);
        operationTwo = "";
        operationTwoNum = null;
        frontPanel.setText(operationOne + " " + operator.getDescription());
    }

    public App() {
        buttonClear.addActionListener(e -> {
            firstOperator = true;
            operationOne = "";
            operationOneNum = null;
            operationTwo = "";
            operationTwoNum = null;
            isRationalNumber = false;
            frontPanel.setText(operationOne);
        });
        buttonClearEntry.addActionListener(e -> {
            if (firstOperator) {
                if (operationOne.length() - 1 > 0) {
                    String lastWord = operationOne.substring(operationOne.length() - 1);
                    if (lastWord.equals(".")) {
                        isRationalNumber = false;
                    }
                    operationOne = operationOne.substring(0, operationOne.length() - 1);
                    operationOneNum = Double.valueOf(operationOne);
                } else {
                    operationOne = "";
                    operationOneNum = null;
                }
                frontPanel.setText(operationOne);
            } else {
                if (operationTwo.length() - 1 > 0) {
                    operationTwo = operationTwo.substring(0, operationTwo.length() - 1);
                    operationTwoNum = Double.valueOf(operationTwo);
                } else {
                    operationTwo = "";
                    operationTwoNum = null;
                }
                frontPanel.setText(operationOne + " " +  operator.getDescription() + " " + operationTwo);
            }
        });
        buttonEquals.addActionListener(e -> {

            switch (operator.getNumber()) {
                case 1:
                    result = operationOneNum + operationTwoNum;
                    break;
                case 2:
                    result = operationOneNum - operationTwoNum;
                    break;
                case 3:
                    result = operationOneNum * operationTwoNum;
                    break;
                case 4:
                    result = operationOneNum / operationTwoNum;
                    break;
                default:
                    result = operationOneNum;
                    break;
            }
            if (operator.getShowDecimals() || isRationalNumber) {
                JOptionPane.showMessageDialog(null, String.format("Equals: %.2f", result));
            } else {
                // TODO: fix if number is integer (e.g.: 72 not rational)
                JOptionPane.showMessageDialog(null, String.format("Equals: %.0f", result));
            }
        });
        buttonDot.addActionListener(e -> {
            isRationalNumber = true;
            if (!operationOne.equals("") || (!operationOne.equals("") && !operationTwo.equals(""))) {
                addNum(".");
            }
        });
        buttonPlus.addActionListener(e -> {
            if (!operationOne.equals("")) {
                operator = Operators.ADDITION;
                resetForSecondData();
            }
        });
        buttonMinus.addActionListener(e -> {
            if (!operationOne.equals("")) {
                operator = Operators.SUBTRACTION;
                resetForSecondData();
            }
        });
        buttonTimes.addActionListener(e -> {
            if (!operationOne.equals("")) {
                operator = Operators.MULTIPLICATION;
                resetForSecondData();
            }
        });
        buttonDivided.addActionListener(e -> {
            if (!operationOne.equals("")) {
                operator = Operators.DIVISION;
                resetForSecondData();
            }
        });
        buttonZero.addActionListener(e -> {
            if (firstOperator) {
                if (!operationOne.equals("")) {
                    addNum("0");
                }
            } else {
                if (!operationTwo.equals("")) {
                    addNum("0");
                }
            }
        });
        buttonOne.addActionListener(e -> addNum("1"));
        buttonTwo.addActionListener(e -> addNum("2"));
        buttonThree.addActionListener(e -> addNum("3"));
        buttonFour.addActionListener(e -> addNum("4"));
        buttonFive.addActionListener(e -> addNum("5"));
        buttonSix.addActionListener(e -> addNum("6"));
        buttonSeven.addActionListener(e -> addNum("7"));
        buttonEight.addActionListener(e -> addNum("8"));
        buttonNine.addActionListener(e -> addNum("9"));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new App().panelMain);
        frame.setPreferredSize(new Dimension(330, 380));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
