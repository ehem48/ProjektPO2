import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;


public class formatowanieDaty extends JFrame {
    private JPanel JPanel1;
    private JRadioButton DMYRadioButton;
    private JRadioButton YMDRadioButton;
    private JRadioButton MDYRadioButton;
    private JButton powrótButton;
    private JButton wyjdźButton;
    private JButton wynikButton;
    private JTextField RoktextField1;
    private JTextField MiesiactextField2;
    private JTextField DzientextField3;

    private ButtonGroup buttonGroup;

    public static void main(String[] args) {
        formatowanieDaty formatowanieDaty = new formatowanieDaty();
        formatowanieDaty.setVisible(true);
    }

    public formatowanieDaty() {
        super("Zmień format daty");
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

        buttonGroup = new ButtonGroup();
        buttonGroup.add(DMYRadioButton);
        buttonGroup.add(YMDRadioButton);
        buttonGroup.add(MDYRadioButton);

        wynikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String rok = RoktextField1.getText();
                    String miesiac = MiesiactextField2.getText();
                    String dzien = DzientextField3.getText();

                    if (isValidDateInput(rok, miesiac, dzien)) {
                        int intRok = Integer.parseInt(rok);
                        int intMiesiac = Integer.parseInt(miesiac);
                        int intDzien = Integer.parseInt(dzien);

                        LocalDate data = LocalDate.of(intRok, intMiesiac, intDzien);
                        String formatDaty = "";

                        if (DMYRadioButton.isSelected()) {
                            formatDaty = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        } else if (YMDRadioButton.isSelected()) {
                            formatDaty = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        } else if (MDYRadioButton.isSelected()) {
                            formatDaty = data.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                        }

                        wynikButton.setText("Data w wybranym formacie: " + formatDaty);
                    } else {
                        JOptionPane.showMessageDialog(formatowanieDaty.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formatowanieDaty.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(formatowanieDaty.this, "Nieprawidłowe dane wejściowe", "Błąd", JOptionPane.ERROR_MESSAGE);
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