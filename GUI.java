import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener{

  private Container pane;
  private JButton searchButton;
  private JButton trainButton;
  private JTextField in;
  private JTextField out;
  private Label inputLabel;

  public static double CtoF(double t){
    return t*(9.0/5.0)+32.0;
  }

  public static double FtoC(double t){
    return (t-32.0)*(5.0/9.0);
  }

  public GUI() {
    this.setTitle("Neural Network Based Price Projection");
    this.setSize(400,400);
    this.setLocation(100,100);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    pane = this.getContentPane();
    pane.setLayout(new FlowLayout());

    trainButton = new JButton("Train network");
    trainButton.addActionListener(this);
    trainButton.setActionCommand("Train network");
    searchButton = new JButton("Search");
    searchButton.addActionListener(this);
    searchButton.setActionCommand("Search");
    in = new JTextField(10);
    inputLabel = new Label("Enter ticker");
    out = new JTextField(10);

    pane.add(inputLabel);
    pane.add(in);
    pane.add(searchButton);
    pane.add(trainButton);
    pane.add(out);
  }

  public void actionPerformed(ActionEvent e) {
    if ("Train network".equals(e.getActionCommand())){
    }
    if ("Search".equals(e.getActionCommand())){
        
    }
  }

  public static void main(String[] args) {
    GUI g = new GUI();
    g.setVisible(true);
  }

}

