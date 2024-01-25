import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;


public class roznicaDat extends JFrame {
    private JPanel JPanel1;
    private JTextField Rok1textField1;
    private JTextField Miesiac1textField2;
    private JTextField Dzien1textField3;
    private JTextField Rok2textField4;
    private JTextField Miesiac2textField5;
    private JTextField Dzien2textField6;
    private JButton formatujButton;
    private JButton powrotButton;
    private JButton wyjdzButton;

    public static void main(String[] args) {
        roznicaDat roznicaDat = new roznicaDat();
        roznicaDat.setVisible(true);
    }

    public roznicaDat() {
        super("Oblicz różnicę dat");
        this.setContentPane(this.JPanel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.pack();

        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main main = new Main();
                main.setVisible(true);
            }
        });

        wyjdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        formatujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String data1Text = Rok1textField1.getText() + "-" + Miesiac1textField2.getText() + "-" + Dzien1textField3.getText();
                    String data2Text = Rok2textField4.getText() + "-" + Miesiac2textField5.getText() + "-" + Dzien2textField6.getText();

                    if (!isValidDateInput(Rok1textField1.getText(), Miesiac1textField2.getText(), Dzien1textField3.getText()) ||
                            !isValidDateInput(Rok2textField4.getText(), Miesiac2textField5.getText(), Dzien2textField6.getText())) {
                        throw new IllegalArgumentException("Niepoprawne dane miesiąca, dnia lub roku");
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                    LocalDate data1 = LocalDate.parse(data1Text, formatter);
                    LocalDate data2 = LocalDate.parse(data2Text, formatter);

                    long roznicaDni = ChronoUnit.DAYS.between(data1, data2);

                    formatujButton.setText("Roznica w dniach to: " + roznicaDni);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(roznicaDat.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(roznicaDat.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private boolean isValidDateInput(String rok, String miesiac, String dzien) {
        try {
            int intRok = Integer.parseInt(rok);
            int intMiesiac = Integer.parseInt(miesiac);
            int intDzien = Integer.parseInt(dzien);

            LocalDate.of(intRok, intMiesiac, intDzien);
            return true;
        } catch (NumberFormatException | DateTimeException e) {
            return false;
        }
    }
}