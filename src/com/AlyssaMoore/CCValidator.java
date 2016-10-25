package com.AlyssaMoore;

import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CCValidator extends JFrame {
    Scanner stringScanner = new Scanner(System.in);
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JLabel validMessageLabel;

    public CCValidator() {
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ccNumber = creditCardNumberTextField.getText();
                boolean valid = isVisaCreditCardNumberValid(ccNumber);

                if (valid) {
                    validMessageLabel.setText("Credit card number is valid");
                } else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private static boolean isVisaCreditCardNumberValid(String cc) {

        // Setting valid to true as default, total at zero
        boolean valid = true;
        int total = 0;
        // Converting number to a character array
        char[] chararray = cc.toCharArray();
        // if not exactly 16 numbers, number is not valid
        if (cc.length() != 16)
            valid = false;
            // if first numbre isn't 4, number is not valid (must subtract 0 to get correct number from char array)
        else if ((chararray[0] - '0') != 4)
            valid = false;
        // MOD 10 calculation starts at x = 0, goes until x = 16
        for (int x = 0; x < 16; ) {
            // odd places have their numbers multiplied by 2
            int valueodd = ((chararray[x] - '0') * 2);
            // if the result is greater than 10, extract each number into char array, then continue calculation
            if (valueodd >= 10) {
                char[] valuearray = Integer.toString(valueodd).toCharArray();
                valueodd = (valuearray[0] - '0') + (valuearray[1] - '0');
            }
            // Adding value of the odd numbers to total
            total += valueodd;
            // Going to the next number in sequence
            x += 1;
            // Even places don't need to be multipled, only added to the total
            int valueeven = (chararray[x] - '0');
            total += valueeven;
            // Going back to the odd numbered spaces, repeating loop
            x += 1;
        }
        // if the total as calculated above is not divisible by 10, number is not valid
        if (total % 10 != 0)
            valid = false;
        // returning validity
        return valid;
    }
}