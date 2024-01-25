import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.DateTimeException;


public class przesuniecieDaty extends JFrame {
    private JPanel JPanel1;
    private JButton wynikButton;
    private JButton wyjdzButton;
    private JButton powrotButton;
    private JTextField textField4;
    private JTextField MiesiactextField5;
    private JTextField DzientextField1;
    private JTextField ileMtextField2;
    private JTextField ileDtextField3;

    public static void main(String[] args) {
        przesuniecieDaty przesuniecieDaty = new przesuniecieDaty();
        przesuniecieDaty.setVisible(true);
    }

    public przesuniecieDaty() {
        super("Przesuń datę");
        this.setContentPane(this.JPanel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.pack();

        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main Main = new Main();
                Main.setVisible(true);
            }
        });

        wyjdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        wynikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dzien = Integer.parseInt(DzientextField1.getText());
                    int miesiac = Integer.parseInt(MiesiactextField5.getText());
                    int rok = Integer.parseInt(textField4.getText());
                    int ileMiesiecy = Integer.parseInt(ileMtextField2.getText());
                    int ileDni = Integer.parseInt(ileDtextField3.getText());

                    if (!isValidDateInput(textField4.getText(), MiesiactextField5.getText(), DzientextField1.getText())) {
                        throw new IllegalArgumentException("Niepoprawne dane miesiąca, dnia lub roku");
                    }

                    LocalDate data = LocalDate.of(rok, miesiac, dzien);
                    data = data.plusMonths(ileMiesiecy).plusDays(ileDni);

                    wynikButton.setText("Nowa data: " + data);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(przesuniecieDaty.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(przesuniecieDaty.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
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