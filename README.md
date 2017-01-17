# Advanced Machine Learning Trading System
<h3>Full implementation of a backpropagating neural network and financial datafeed without the use of any preexisting libraries.
This is a project for Stuyvesant's AP Computer Science class by team LongXShortY.</h3>

Brief introduction to the project:
The neural network, which forms the core of this project, consists of then Neuron class and the NeuralNetwork class. The network is formed by a known number of inputs and outputs, together with a grid of interconnected neurons with a specified depth and width. The weights and are initialized and stored in arrays, where they are subsequently adjusted through backpropagation during training. <br> 
Fundemental data is obtained from YahooFinance through a web query. Volume and price time series are retrieved from a csv file on the Yahoo server which is updated periodically, and they are passed through a gaussian normalization function before being concatenated with fundemental data to form the input set to the network. As the backpropagating network itself is not recurrent (i.e. not time-dependent), pseudo time-dependence is allowed through a moving window which slides across the live datafeed.<br>
The network is trained with the processed input set to a output value, and is repeated 503 times for every stock in the S&P500 index to allow it to generalize price movement characteristics. This whole process forms a single iteration, which is then repeated a specified number of times to decrease the cost function. This needs to be tweaked so that overfitting does not occur. <br> 

Instructions: <br>
Compile and run Terminal.java. Instructions will be presented. This allows access of equity mode (financial datafeed and neural network), handwriting recognition mode, and GUI mode. Note that training the network will take a very long period of time, typically ranging from 1 hour to 10 hours. <br>

Known bug list:
-Terminal.java does not catch any wrong input formats or invalid user inputs


