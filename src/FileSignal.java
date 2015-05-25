import java.io.BufferedReader;
import java.io.FileReader;

public class FileSignal {

    public static final int SIZE = 1000000;
    private String filename;

    private double reali[] = new double[SIZE];
    private double immaginari[] = new double[SIZE];

    public FileSignal(String filename) throws Exception {
        this.filename = filename;
        this.initializeSignal();
    }


    /**
     * Restituisce il segnale.
     *
     * @return data
     * @throws Exception
     */

    /**
     * Genero un Signal partendo dal file.
     */
    private void initializeSignal() throws Exception {
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
    }

    private double[] getValue(String line) {
        String[] values = line.split("\t");
        double[] result = {Double.parseDouble(values[0]), Double.parseDouble(values[1])};

        return result;
    }


	public static int getSize() {
		return SIZE;
	}


	public double[] getReali() {
		return reali;
	}


	public double[] getImmaginari() {
		return immaginari;
	}

}