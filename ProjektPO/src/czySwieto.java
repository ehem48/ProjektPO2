import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class czySwieto extends JFrame {

    private JPanel JPanel1;
    private JButton powrótButton;
    private JButton wyjdźButton;
    private JButton sprawdzButton;
    private JTextField MiesiactextField1;
    private JTextField DzientextField2;
    private JTextField RoktextField3;
    private JButton wróćButton;

    private LocalDate data;
    private List<LocalDate> swieta;

    public static void main(String[] args) {
        czySwieto CzySwieto = new czySwieto();
        CzySwieto.setVisible(true);
    }

    public czySwieto() {
        super("Sprawdź czy data jest świętem");
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

        sprawdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String rok = RoktextField3.getText();
                    String miesiac = MiesiactextField1.getText();
                    String dzien = DzientextField2.getText();

                    int intRok = Integer.parseInt(rok);
                    int intMiesiac = Integer.parseInt(miesiac);
                    int intDzien = Integer.parseInt(dzien);

                    // Dodaj walidację daty
                    if (isValidDate(intRok, intMiesiac, intDzien)) {
                        data = LocalDate.of(intRok, intMiesiac, intDzien);

                        if (czySwieto(data)) {
                            sprawdzButton.setText("To jest święto!");
                        } else {
                            sprawdzButton.setText("To nie jest święto.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(czySwieto.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(czySwieto.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.swieta = new ArrayList<>();
        odczytajSwietaZPliku("swieta.csv");
    }

    private void odczytajSwietaZPliku(String sciezka) {
        try (BufferedReader br = new BufferedReader(new FileReader(sciezka))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Dostosuj format daty do "dd.MM.yyyy"
                LocalDate swieto = LocalDate.parse(linia.trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                swieta.add(swieto);
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu pliku: " + e.getMessage());
        } catch (DateTimeException e) {
            System.out.println("Błąd podczas parsowania daty: " + e.getMessage());
        }
    }

    private boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    private boolean czySwieto(LocalDate data) {
        return swieta.contains(data);
    }
}
