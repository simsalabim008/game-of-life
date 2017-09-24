package lifegui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;

public class LifeFrame extends JFrame implements Runnable {
  // constante
  private static final int SLAAPTIJD = 500;

  // eigen attributen
  private Thread spel = null;
  private LifePanel lifePanel = null; // spel Game of Life met grafische weergave
  private int generatie = 0;  // teller voor het aantal generaties

  private static final long serialVersionUID = 1L;
  private JPanel jContentPane = null;
  private JButton volgendeKnop = null;
  private JButton startStopKnop = null;
  private JLabel genLabel = null;
  private JButton nieuwKnop = null;

  /**
   * This is the default constructor
   */
  public LifeFrame() {
    super();
    initialize();
    mijnInit();
  }
  
  // eigen public methoden
  
  /**
   * Start de animatie.
   */
  public void start() {
    if(spel == null) {
      spel = new Thread(this);
      spel.start();
    }
  }

  /**
   * Stopt de animatie.
   */
  public void stop() {
    if(spel != null) {
      spel = null;
    }
  }

  /**
   * Animatie van Game of Life.
   * Op het scherm wordt op gezette tijden een nieuwe generatie
   * cellen getoond.
   */
  public void run() {
    while(spel != null) {
      lifePanel.volgende();
      lifePanel.repaint();
      generatie++;
      genLabel.setText("Generatie: " + generatie);
      try {
        Thread.sleep(SLAAPTIJD);
      }
      catch (InterruptedException ex) {}
    }
  }
  
  // eigen private methoden
  
  /**
   * Maakt een nieuw panel met cellen en plaats die in de gui
   */
  private void mijnInit() {
    lifePanel = new LifePanel();
    add(lifePanel);
    lifePanel.setLocation(5, 5);
  }
  
  /**
   * Toont de volgende generatie
   */
  private void volgendeKnopAction() {
    lifePanel.volgende();
    lifePanel.repaint();
    generatie++;
    genLabel.setText("Generatie: " + generatie);
  }
  
  /**
   * Start of stop de animatie.
   */
  private void startStopKnopAction() {
    if(startStopKnop.getText().equals("Start")) {
      startStopKnop.setText("Stop");
      start();
    }
    else {
      startStopKnop.setText("Start");
      stop();
    }
  }
  
  /**
   * Stopt zonodig de animatie en vervangt dan het huidige 
   * panel met cellen door een nieuw, leeg panel 
   */
  private void nieuwKnopAction() {
    stop();
    startStopKnop.setText("Start");
    lifePanel.wis();
    lifePanel.repaint();
    generatie = 0;
    genLabel.setText("Generatie: " + generatie);
  }
  

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    this.setSize(460, 370);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(getJContentPane());
    this.setTitle("Game of Life");
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      genLabel = new JLabel();
      genLabel.setBounds(new Rectangle(228, 298, 94, 31));
      genLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
      genLabel.setText("Generatie:");
      jContentPane = new JPanel();
      jContentPane.setLayout(null);
      jContentPane.setBackground(new Color(167, 192, 192));
      jContentPane.add(getVolgendeKnop(), null);
      jContentPane.add(getStartStopKnop(), null);
      jContentPane.add(genLabel, null);
      jContentPane.add(getNieuwKnop(), null);
    }
    return jContentPane;
  }

  /**
   * This method initializes volgendeKnop	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getVolgendeKnop() {
    if (volgendeKnop == null) {
      volgendeKnop = new JButton();
      volgendeKnop.setBounds(new Rectangle(14, 298, 89, 31));
      volgendeKnop.setFont(new Font("Dialog", Font.PLAIN, 12));
      volgendeKnop.setText("Volgende");
      volgendeKnop.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          volgendeKnopAction();
        }
      });
    }
    return volgendeKnop;
  }

  /**
   * This method initializes startStopKnop	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getStartStopKnop() {
    if (startStopKnop == null) {
      startStopKnop = new JButton();
      startStopKnop.setBounds(new Rectangle(117, 298, 97, 31));
      startStopKnop.setFont(new Font("Dialog", Font.PLAIN, 12));
      startStopKnop.setText("Start");
      startStopKnop.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          startStopKnopAction();
        }
      });
    }
    return startStopKnop;
  }

  /**
   * This method initializes nieuwKnop	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getNieuwKnop() {
    if (nieuwKnop == null) {
      nieuwKnop = new JButton();
      nieuwKnop.setBounds(new Rectangle(336, 298, 99, 31));
      nieuwKnop.setFont(new Font("Dialog", Font.PLAIN, 12));
      nieuwKnop.setText("Nieuw spel");
      nieuwKnop.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          nieuwKnopAction();
        }
      });
    }
    return nieuwKnop;
  }
  
  public static void main(String[] args) {
    LifeFrame gui = new LifeFrame();
    gui.setVisible(true);
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
