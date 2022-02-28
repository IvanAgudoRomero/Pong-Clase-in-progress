package Vista;

import javax.swing.*;

public class Ranking extends JFrame {
    private JTextArea textArea;

    public Ranking(){
        textArea = new JTextArea();
        add(textArea);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ranking");
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        Ranking r = new Ranking();
    }
}
