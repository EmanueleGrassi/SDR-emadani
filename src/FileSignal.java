import java.io.BufferedReader;
import java.io.FileReader;

public class FileSignal {

    public static final int SIZE = 1000000;
    private ArraySignal data;
    private String filename;

    private double reali[] = new double[SIZE];
    private double immaginari[] = new double[SIZE];

    public FileSignal(String filename) throws Exception {
        this.filename = filename;
        this.data = this.initializeListArraySignal();
    }


    /**
     * Restituisce il segnale.
     *
     * @return data
     * @throws Exception
     */
    public ArraySignal getSignal() throws Exception {
    	return this.data;
    }

    /**
     * Genero un ArraySignal partendo dal file.
     */
    private ArraySignal initializeListArraySignal() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null)
        {
        	double [] temp = this.getValue(line);
            reali[i] = temp[0];
            immaginari[i] = temp[1];
            i++;
        }
        reader.close();

        return new ArraySignal(reali, immaginari);
    }

    private double[] getValue(String line) {
        String[] values = line.split("\t");
        double[] result = {Double.parseDouble(values[0]), Double.parseDouble(values[1])};

        return result;
    }

}