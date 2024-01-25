import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel JPanel1;
    private JButton formatujDatęButton;
    private JButton przesuńDatęButton;
    private JButton obliczRóżnicęMiędzyDatamiButton;
    private JButton wyjdźButton;
    private JButton sprawdźCzyŚwiętoButton;
    private JButton dodajŚwiętoButton;


    public static void main(String[] args) {
        Main main=new Main();
        main.setVisible(true);
    }




    public Main() {
        super("Ekran główny");
        this.setContentPane(this.JPanel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.pack();

        formatujDatęButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                formatowanieDaty FormatowanieDaty=new formatowanieDaty();
                FormatowanieDaty.setVisible(true);
            }
        });
        przesuńDatęButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                przesuniecieDaty PrzesuniecieDaty=new przesuniecieDaty();
                PrzesuniecieDaty.setVisible(true);
            }
        });
        obliczRóżnicęMiędzyDatamiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                roznicaDat RoznicaDat=new roznicaDat();
                RoznicaDat.setVisible(true);
            }
        });
        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        sprawdźCzyŚwiętoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                czySwieto obj=new czySwieto();
                obj.setVisible(true);
            }
        });
        dodajŚwiętoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dodajSwieto obj=new dodajSwieto();
                obj.setVisible(true);
            }
        });
    }
}