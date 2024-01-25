import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class dodajSwieto extends JFrame {
    private JPanel JPanel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton powrótButton;
    private JButton wyjdźButton;
    private JButton dodajButton;

    public static void main(String[] args) {
        dodajSwieto dodajSwieto = new dodajSwieto();
        dodajSwieto.setVisible(true);
    }

    public dodajSwieto() {
        super("Dodaj święto do pliku");
        this.setContentPane(this.JPanel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.pack();

        powrótButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main Main = new Main();
                Main.setVisible(true);
            }
        });

        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String rok = textField1.getText();
                    String miesiac = textField2.getText();
                    String dzien = textField3.getText();

                    int intRok = Integer.parseInt(rok);
                    int intMiesiac = Integer.parseInt(miesiac);
                    int intDzien = Integer.parseInt(dzien);

                    if (!isValidDateInput(rok, miesiac, dzien)) {
                        throw new IllegalArgumentException("Nieprawidłowe dane wejściowe");
                    }

                    LocalDate data = LocalDate.of(intRok, intMiesiac, intDzien);

                    dodajDoPlikuCSV(data);

                    JOptionPane.showMessageDialog(dodajSwieto.this, "Data dodana do pliku CSV.", "Informacja", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dodajSwieto.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dodajSwieto.this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
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
        } catch (NumberFormatException | java.time.DateTimeException e) {
            return false;
        }
    }

    private void dodajDoPlikuCSV(LocalDate data) {
        String sciezka = "swieta.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sciezka, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = data.format(formatter);
            writer.write(formattedDate);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(dodajSwieto.this, "Błąd podczas zapisu do pliku: " + e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
