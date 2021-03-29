package com.felipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class App implements KeyListener {
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
    private Operators operator;

    public void addNum(Character number) {
        if (firstOperator) {
            if (operationOne.equals("0")) {
                operationOne = operationOne.substring(0, 0);
            }
            operationOne += number;
            operationOneNum = Double.valueOf(operationOne);
            frontPanel.setText(operationOne);
        } else {
            if (operationTwo.equals("0")) {
                operationTwo = operationTwo.substring(0, 0);
            }
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

    public void clearButton() {
        firstOperator = true;
        operationOne = "";
        operationOneNum = null;
        operationTwo = "";
        operationTwoNum = null;
        isRationalNumber = false;
        frontPanel.setText(operationOne);
    }

    public void clearEntryButton() {
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
    }

    public void equalsButton() {
        Double result;
        if (!operationTwo.equals("")) {
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
                // JOptionPane.showMessageDialog(null, String.format("Equals: %.2f", result));
                frontPanel.setText(String.format( "%s %s %s = %.2f",
                        operationOne, operator.getDescription(), operationTwo, result));
            } else {
                // TODO: fix if number is integer (e.g.: 72 not rational)
                // JOptionPane.showMessageDialog(null, String.format("Equals: %.0f", result));
                frontPanel.setText(String.format( "%s %s %s = %.0f",
                        operationOne, operator.getDescription(), operationTwo, result));
            }
        }
    }

    public void dotButton() {
        isRationalNumber = true;
        if (!operationOne.equals("")) {
            addNum('.');
        } else {
            addNum('0');
            addNum('.');
        }
    }

    public void plusButton() {
        if (!operationOne.equals("")) {
            operator = Operators.ADDITION;
            resetForSecondData();
        }
    }

    public void minusButton() {
        if (!operationOne.equals("")) {
            operator = Operators.SUBTRACTION;
            resetForSecondData();
        }
    }

    public void timesButton() {
        if (!operationOne.equals("")) {
            operator = Operators.MULTIPLICATION;
            resetForSecondData();
        }
    }

    public void dividedButton() {
        if (!operationOne.equals("")) {
            operator = Operators.DIVISION;
            resetForSecondData();
        }
    }

    public void zeroButton() {
        if (firstOperator) {
            if (!operationOne.equals("0")) {
                addNum('0');
            }
        } else {
            if (!operationTwo.equals("0")) {
                addNum('0');
            }
        }
    }

    public App() {
        buttonClear.addActionListener(e -> clearButton());
        buttonClearEntry.addActionListener(e -> clearEntryButton());
        buttonEquals.addActionListener(e -> equalsButton());
        buttonDot.addActionListener(e -> dotButton());
        buttonPlus.addActionListener(e -> plusButton());
        buttonMinus.addActionListener(e -> minusButton());
        buttonTimes.addActionListener(e -> timesButton());
        buttonDivided.addActionListener(e -> dividedButton());
        buttonZero.addActionListener(e -> zeroButton());
        buttonOne.addActionListener(e -> addNum('1'));
        buttonTwo.addActionListener(e -> addNum('2'));
        buttonThree.addActionListener(e -> addNum('3'));
        buttonFour.addActionListener(e -> addNum('4'));
        buttonFive.addActionListener(e -> addNum('5'));
        buttonSix.addActionListener(e -> addNum('6'));
        buttonSeven.addActionListener(e -> addNum('7'));
        buttonEight.addActionListener(e -> addNum('8'));
        buttonNine.addActionListener(e -> addNum('9'));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_0:
                zeroButton();
                break;
            case KeyEvent.VK_1:
                addNum('1');
                break;
            case KeyEvent.VK_2:
                addNum('2');
                break;
            case KeyEvent.VK_3:
                addNum('3');
                break;
            case KeyEvent.VK_4:
                addNum('4');
                break;
            case KeyEvent.VK_5:
                addNum('5');
                break;
            case KeyEvent.VK_6:
                addNum('6');
                break;
            case KeyEvent.VK_7:
                addNum('7');
                break;
            case KeyEvent.VK_8:
                addNum('8');
                break;
            case KeyEvent.VK_9:
                addNum('9');
                break;
            case KeyEvent.VK_SLASH:
                dividedButton();
                break;
            case '+':
                plusButton();
                break;
            case KeyEvent.VK_MINUS:
                minusButton();
                break;
            case '*':
                timesButton();
                break;
            case KeyEvent.VK_PERIOD:
                dotButton();
                break;
            case KeyEvent.VK_BACK_SPACE:
                clearEntryButton();
                break;
            case 'c':
                clearButton();
                break;
            case KeyEvent.VK_ENTER:
                equalsButton();
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception e) {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        JFrame frame = new JFrame("Calculator");
        App app = new App();
        frame.setContentPane(app.panelMain);
        frame.setPreferredSize(new Dimension(360, 420));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(app);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
