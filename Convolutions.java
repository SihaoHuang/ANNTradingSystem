import java.util.ArrayList;
import java.lang.Math;

/* Wshen data is not normalized, the change in value for a weight 
is disproportionately more influential for a larger independent value.
*/

public class Convolutions{

  public static ArrayList<Double> gaussianNormalization(ArrayList<Double> column){
    Double avg = mean(column);
    Double std = correctedStandardDeviation(column);
    ArrayList<Double> out = new ArrayList<Double>(); 
    for(Double columnVal:column){
      out.add((columnVal - avg)/std);
    }
    return out;
  }

  public static Double correctedStandardDeviation(ArrayList<Double> column){
    Double out = 0.0;
    Double avg = mean(column);
    for(Double each:column){
      out += Math.pow((each - avg),2);
    }
    return Math.sqrt((1.0/(column.size()-1.0))*out);
  }

  private static Double mean(ArrayList<Double> in){
    Double total = 0.0;
    for(Double each:in) total += each;
    return total/in.size();
  }

}