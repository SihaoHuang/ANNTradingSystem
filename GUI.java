import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class GUI extends JFrame {
  static GUI theGUI;

  JPanel pnPanel0;
  JTable tbFundementals;
  JButton btSearch;
  JTextField tfInput;
  JLabel lbFundementals;
  JTextField tfCurrentPrice;
  JTextField tfTicker;
  JTextField tfPredictedPrice;
  JLabel lbPredictedPrice;
  JTextField tfName;
  JButton btTrainNetwork;
  JSlider sdWidth;
  JLabel lbWidth;
  JLabel lbDepth;
  JSlider sdDepth;
  JLabel lbIterations;
  JSlider sdIterations;
  JButton btLoadStocks;

  public static void main(String args[]) 
  {
    try 
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch ( ClassNotFoundException e ) 
    {
    }
    catch ( InstantiationException e ) 
    {
    }
    catch ( IllegalAccessException e ) 
    {
    }
    catch ( UnsupportedLookAndFeelException e ) 
    {
    }
    theGUI = new GUI();
  } 

  public GUI() 
  {
    super("Neural network based stock price projection");

    pnPanel0 = new JPanel();
    GridBagLayout gbPanel0 = new GridBagLayout();
    GridBagConstraints gbcPanel0 = new GridBagConstraints();
    pnPanel0.setLayout( gbPanel0 );

    String [][]dataFundementals = new String[][] { new String[] {"Voume", "21"}, 
                                                  new String[] {"PE Ratio", "22"}, 
                                                  new String[] {"Earnings per share", 
                                                  "23"}, 
                                                  new String[] {"52 Wk low", ""}, 
                                                  new String[] {"52 Wk high", ""}, 
                                                  new String[] {"Today's low", ""}, 
                                                  new String[] {"Today's high", ""}, 
                                                  new String[] {"Market cap", ""}, 
                                                  new String[] {"Short ratio", ""}, 
                                                  new String[] {"Previous close", ""}, 
                                                  new String[] {"Exchange", ""} };
    String []colsFundementals = new String[] { "", "" };
    tbFundementals = new JTable( dataFundementals, colsFundementals );
    gbcPanel0.gridx = 1;
    gbcPanel0.gridy = 6;
    gbcPanel0.gridwidth = 10;
    gbcPanel0.gridheight = 8;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 1;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( tbFundementals, gbcPanel0 );
    pnPanel0.add( tbFundementals );

    btSearch = new JButton("Search");
    gbcPanel0.gridx = 13;
    gbcPanel0.gridy = 13;
    gbcPanel0.gridwidth = 4;
    gbcPanel0.gridheight = 1;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( btSearch, gbcPanel0 );
    pnPanel0.add( btSearch );

    tfInput = new JTextField( );
    gbcPanel0.gridx = 13;
    gbcPanel0.gridy = 11;
    gbcPanel0.gridwidth = 5;
    gbcPanel0.gridheight = 2;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( tfInput, gbcPanel0 );
    pnPanel0.add( tfInput );

    lbFundementals = new JLabel( "Fundementals"  );
    gbcPanel0.gridx = 1;
    gbcPanel0.gridy = 5;
    gbcPanel0.gridwidth = 10;
    gbcPanel0.gridheight = 1;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 1;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( lbFundementals, gbcPanel0 );
    pnPanel0.add( lbFundementals );

    tfCurrentPrice = new JTextField( );
    gbcPanel0.gridx = 13;
    gbcPanel0.gridy = 4;
    gbcPanel0.gridwidth = 5;
    gbcPanel0.gridheight = 2;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( tfCurrentPrice, gbcPanel0 );
    pnPanel0.add( tfCurrentPrice );

    tfTicker = new JTextField( );
    gbcPanel0.gridx = 1;
    gbcPanel0.gridy = 1;
    gbcPanel0.gridwidth = 9;
    gbcPanel0.gridheight = 3;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( tfTicker, gbcPanel0 );
    pnPanel0.add( tfTicker );

    tfPredictedPrice = new JTextField( );
    gbcPanel0.gridx = 13;
    gbcPanel0.gridy = 8;
    gbcPanel0.gridwidth = 5;
    gbcPanel0.gridheight = 2;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( tfPredictedPrice , gbcPanel0 );
    pnPanel0.add( tfPredictedPrice  );

    lbPredictedPrice = new JLabel( "Predicted Price"  );
    gbcPanel0.gridx = 14;
    gbcPanel0.gridy = 7;
    gbcPanel0.gridwidth = 3;
    gbcPanel0.gridheight = 1;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 1;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( lbPredictedPrice, gbcPanel0 );
    pnPanel0.add( lbPredictedPrice );

    tfName = new JTextField( );
    gbcPanel0.gridx = 13;
    gbcPanel0.gridy = 1;
    gbcPanel0.gridwidth = 5;
    gbcPanel0.gridheight = 2;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( tfName, gbcPanel0 );
    pnPanel0.add( tfName );

    btTrainNetwork = new JButton( "Train Network"  );
   gbcPanel0.gridx = 15;
   gbcPanel0.gridy = 19;
   gbcPanel0.gridwidth = 4;
   gbcPanel0.gridheight = 2;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 0;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( btTrainNetwork, gbcPanel0 );
   pnPanel0.add( btTrainNetwork );

   sdWidth = new JSlider( );
   gbcPanel0.gridx = 5;
   gbcPanel0.gridy = 16;
   gbcPanel0.gridwidth = 9;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 0;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( sdWidth, gbcPanel0 );
   pnPanel0.add( sdWidth );

   lbWidth = new JLabel( "Width"  );
   gbcPanel0.gridx = 1;
   gbcPanel0.gridy = 16;
   gbcPanel0.gridwidth = 3;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 1;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( lbWidth, gbcPanel0 );
   pnPanel0.add( lbWidth );

   lbDepth = new JLabel( "Depth"  );
   gbcPanel0.gridx = 1;
   gbcPanel0.gridy = 18;
   gbcPanel0.gridwidth = 3;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 1;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( lbDepth, gbcPanel0 );
   pnPanel0.add( lbDepth );

   sdDepth = new JSlider( );
   sdDepth.setValue( 6 );
   gbcPanel0.gridx = 5;
   gbcPanel0.gridy = 18;
   gbcPanel0.gridwidth = 9;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 0;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( sdDepth, gbcPanel0 );
   pnPanel0.add( sdDepth );

   lbIterations = new JLabel( "Iterations"  );
   gbcPanel0.gridx = 1;
   gbcPanel0.gridy = 20;
   gbcPanel0.gridwidth = 3;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 1;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( lbIterations, gbcPanel0 );
   pnPanel0.add( lbIterations );

   sdIterations = new JSlider( );
   sdIterations.setValue( 6 );
   gbcPanel0.gridx = 5;
   gbcPanel0.gridy = 20;
   gbcPanel0.gridwidth = 9;
   gbcPanel0.gridheight = 1;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 0;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( sdIterations, gbcPanel0 );
   pnPanel0.add( sdIterations );

   btLoadStocks = new JButton( "Load Stocks"  );
   gbcPanel0.gridx = 15;
   gbcPanel0.gridy = 16;
   gbcPanel0.gridwidth = 4;
   gbcPanel0.gridheight = 2;
   gbcPanel0.fill = GridBagConstraints.BOTH;
   gbcPanel0.weightx = 1;
   gbcPanel0.weighty = 0;
   gbcPanel0.anchor = GridBagConstraints.NORTH;
   gbPanel0.setConstraints( btLoadStocks, gbcPanel0 );
   pnPanel0.add( btLoadStocks );

    setDefaultCloseOperation( EXIT_ON_CLOSE );

    setContentPane( pnPanel0 );
    pack();
    setVisible( true );
  } 
} 
