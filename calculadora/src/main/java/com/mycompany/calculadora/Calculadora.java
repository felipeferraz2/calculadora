/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private final JTextField display;
    private double num1, num2, resultado;
    private String operacao;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Aptos", Font.PLAIN, 11));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                ","
        };

        for (String texto : botoes) {
            JButton button = new JButton(texto);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
            display.setText(display.getText() + comando);
        } else if (comando.equals(",")) {
            // Adiciona a vírgula ao display
            if (!display.getText().contains(",")) { // Impede múltiplas vírgulas
                display.setText(display.getText() + ",");
            }
        
        } else if (comando.equals("C")) {
            display.setText("");
            num1 = num2 = resultado = 0;
            operacao = "";
        } else if (comando.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operacao) {
                case "+" -> resultado = num1 + num2;
                case "-" -> resultado = num1 - num2;
                case "*" -> resultado = num1 * num2;
                case "/" -> {
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        display.setText("Erro");
                        return;
                    }
                }
            }
            display.setText(String.valueOf(resultado));
        } else {
            if (!operacao.isEmpty()) {
                return; // Evitar múltiplas operações
            }
            operacao = comando;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }
}
