package pasekmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasekMenu extends JFrame {

    public PasekMenu()
    {
        initComponents();
    }
    public void initComponents()
    {
        this.setTitle("Pasek menu");
        this.setBounds(300, 320, 400, 200);
        this.setJMenuBar(pasekMenu);
        
        JMenu menuPlik = pasekMenu.add(new JMenu("Plik"));
        menuPlik.setMnemonic('P');
        
        JMenuItem podMenuNowy = menuPlik.add(new JMenuItem("Nowy"));
        podMenuNowy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tu jest kod, który stworzy nowy plik");
            }
        });
        menuPlik.addSeparator();
        
        //
        Action actionSave = new ActionSave("Zapisz", "Zapisujemy plik", "ctrl S", KeyEvent.VK_Z);
        
        final JMenuItem podMenuZapisz = menuPlik.add(actionSave);
        buttonZapisz = new JButton(actionSave);
        actionSave.setEnabled(false);
        
        /*podMenuZapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Zapisujemy dane");
                podMenuZapisz.setEnabled(flagaObszaruTekstowego = false);
            }
        });
        podMenuZapisz.setToolTipText("Zapisanie pliku na dysku");
        podMenuZapisz.setMnemonic('Z');
        podMenuZapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));*/
        //
        menuPlik.add(new JMenuItem("Wczytaj"));
        menuPlik.addSeparator();
        JMenu menuOpcje = new JMenu("Opcje");
        menuPlik.add(menuOpcje);
        menuOpcje.add(new JMenuItem("Opcja1"));
        menuOpcje.add(new JMenuItem("Opcja2"));
        menuOpcje.add(tylkoDoOdczytu);
        
        tylkoDoOdczytu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tylkoDoOdczytu.isSelected())
                    actionSave.setEnabled(false);
                else
                    actionSave.setEnabled(flagaObszaruTekstowego);
            }
        });
        
        JMenu menuPomoc = pasekMenu.add(new JMenu("Pomoc"));
        menuPomoc.add(new JMenuItem("FAQ"));
        
        obszarTekstowy.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if(tylkoDoOdczytu.isSelected())
                    e.consume();
            }

            public void keyPressed(KeyEvent e) {
                if(tylkoDoOdczytu.isSelected())
                    e.consume();
                else if(!(obszarTekstowy.getText()+e.getKeyChar()).equals(przedZmianaObaszaruTekstowego) && czyToAscii(e.getKeyChar()))
                {
                    przedZmianaObaszaruTekstowego = obszarTekstowy.getText()+e.getKeyChar();
                    actionSave.setEnabled(flagaObszaruTekstowego = true);
                }
                System.out.println(obszarTekstowy.getText()+e.getKeyChar());
            }
        });
        
        this.getContentPane().setLayout(new GridLayout(2,1));
        this.getContentPane().add(obszarTekstowy);
        this.getContentPane().add(buttonZapisz);
        
        
        this.setDefaultCloseOperation(3);
    }
    
    private JMenuBar pasekMenu = new JMenuBar();
    private JCheckBoxMenuItem tylkoDoOdczytu = new JCheckBoxMenuItem("Tylko do odczytu");
    private JTextArea obszarTekstowy = new JTextArea();
    private boolean flagaObszaruTekstowego = false;
    private String przedZmianaObaszaruTekstowego = "";
    private JButton buttonZapisz;
    
    private class ActionSave extends AbstractAction
    {
        public ActionSave(String nazwa, String podpowiedz, String skrot, int mnemonicKey)
        {
            this.putValue(Action.NAME, nazwa);
            this.putValue(Action.SHORT_DESCRIPTION, podpowiedz);
            this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(skrot));
            this.putValue(Action.MNEMONIC_KEY, mnemonicKey);
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Zapisujemy dane");
            this.setEnabled(flagaObszaruTekstowego = false);
        }
        
    }
    
    private boolean czyToAscii(char zn)
    {
        for(int i = 0; i < 256; i++)
            if(zn == i)
                return true;
        
        return false;
    }
    
    public static void main(String[] args) {
        new PasekMenu().setVisible(true);
    }
    
}
