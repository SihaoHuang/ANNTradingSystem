public class Datafeed{

  public Datafeed(){
  }

  public static Double getNewestPrice(String ticker)

  public static Double getNewestVolume(String ticker)

  public static Double getPE(String ticker)

  public static Double getCap(String ticker)

}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

// inspired by http://stackoverflow.com/questions/9093000/read-csv-file-from-internet
public class Yahoo_ReadCSVFromWebQuery {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://ichart.yahoo.com/table.csv?s=^GSPC&a=3&b=1&c=2014&d=3&e=15&f=2014&g=d");
            URLConnection urlConn = url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}