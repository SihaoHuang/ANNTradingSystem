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
import javax.swing.JTextArea;
import javax.swing.text.*;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import financialdata.*;

public class GUI extends JFrame implements ActionListener{ 
  static GUI theGUI;

  JPanel pnPanel0;
  JTable tbFundementals;
  JButton btSearch;
  JTextArea taInput;
  JLabel lbFundementals;
  JTextField tfCurrentPrice;
  JTextPane tpTicker;
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

  String [][]dataFundementals = new String[][] { new String[] {"Volume", "21"}, 
                                                  new String[] {"PE Ratio", "22"}, 
                                                  new String[] {"Earnings per share", "23"}, 
                                                  new String[] {"52 Wk low", ""}, 
                                                  new String[] {"52 Wk high", ""}, 
                                                  new String[] {"Today's low", ""}, 
                                                  new String[] {"Today's high", ""}, 
                                                  new String[] {"Market cap", ""}, 
                                                  new String[] {"Short ratio", ""}, 
                                                  new String[] {"Previous close", ""}, 
                                                  new String[] {"Exchange", ""} };
  
  static StyleContext sc = new StyleContext();
  static final DefaultStyledDocument tickerBox = new DefaultStyledDocument(sc);
  // public static void main(String args[]) 
  // {
  //   // try 
  //   // {
  //   //     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  //   // }
  //   // catch ( ClassNotFoundException e ) 
  //   // {
  //   // }
  //   // catch ( InstantiationException e ) 
  //   // {
  //   // }
  //   // catch ( IllegalAccessException e ) 
  //   // {
  //   // }
  //   // catch ( UnsupportedLookAndFeelException e ) 
  //   // {
  //   // }
  //   theGUI = new GUI();
  // } 

  public void actionPerformed(ActionEvent e) {
    if ("Search".equals(e.getActionCommand())){  
      NormalStock st = StockFetcher.getStock("AAPL");
      dataFundementals[0][1] = Double.toString(st.getVolume());
      dataFundementals[1][1] = Double.toString(st.getPe());
      dataFundementals[2][1] = Double.toString(st.getEps());
      dataFundementals[3][1] = Double.toString(st.getWeek52low());
      dataFundementals[4][1] = Double.toString(st.getWeek52high());
      dataFundementals[5][1] = Double.toString(st.getDaylow());
      dataFundementals[6][1] = Double.toString(st.getDayhigh());
      dataFundementals[7][1] = Double.toString(st.getMarketcap());
      dataFundementals[8][1] = Double.toString(st.getShortRatio());
      dataFundementals[9][1] = Double.toString(st.getPreviousClose());
      dataFundementals[10][1] = st.getExchange();
    }
    if ("Train Network".equals(e.getActionCommand())){ 
      DriverV2 driver = new DriverV2(80,3,100); //get parameters    
    }
    if ("Load Stocks".equals(e.getActionCommand())){     
      DriverV2 driver = new DriverV2(80,3,100);
    }
  }

  public GUI() 
  {
    super("Neural network based stock price projection");

    pnPanel0 = new JPanel();
    GridBagLayout gbPanel0 = new GridBagLayout();
    GridBagConstraints gbcPanel0 = new GridBagConstraints();
    pnPanel0.setLayout( gbPanel0 );

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
    btSearch.addActionListener(this);
    btSearch.setActionCommand("Search"); //
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

    taInput = new JTextArea(2,10);
    gbcPanel0.gridx = 13;
    gbcPanel0.gridy = 10;
    gbcPanel0.gridwidth = 5;
    gbcPanel0.gridheight = 2;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( taInput, gbcPanel0 );
    pnPanel0.add( taInput );

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

    tpTicker = new JTextPane(tickerBox);
    gbcPanel0.gridx = 1;
    gbcPanel0.gridy = 1;
    gbcPanel0.gridwidth = 9;
    gbcPanel0.gridheight = 3;
    gbcPanel0.fill = GridBagConstraints.BOTH;
    gbcPanel0.weightx = 1;
    gbcPanel0.weighty = 0;
    gbcPanel0.anchor = GridBagConstraints.NORTH;
    gbPanel0.setConstraints( tpTicker, gbcPanel0 );
    pnPanel0.add( tpTicker );

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
    btTrainNetwork.addActionListener(this);
    btTrainNetwork.setActionCommand("Train Network"); //
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
    btLoadStocks.addActionListener(this);
    btLoadStocks.setActionCommand("Load Stocks"); //
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
