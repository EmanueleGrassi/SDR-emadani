import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un segnale tramite un ArraySignal (data) e
 * una String (path) per la generazione da file (output#.dat)
 * dell'ArraySignal.
 * Fornisce due costruttori diversi: 
 * il primo utilizza un ArraySignal,
 * mentre l'altro la directory del file (delegata a FileSignal).
 *
 */
public class Signal {

    private ArraySignal data;
    private String path;

    /**
     * Genero un segnale da un ArraySignal.
     *
     * @param data
     */
    public Signal(ArraySignal data) {
        this.data = data;
    }

    /**
     * Genero un segnale da un file.
     *
     * @param path
     */
    public Signal(String path) throws Exception {
        FileSignal s = new FileSignal(path);
        this.path = path;
        this.data = s.getSignal();
    }

    /**
     * Splitta un segnale in più segnali
     * da n campioni ciascuno.
     *
     * @param n
     * @return
     * @throws Exception
     */
    public List<Signal> split(int n) throws Exception {
        List<Signal> result = new ArrayList<>();
        int i = 0;
        while(i<data.size()) {
            result.add(new Signal(data.slice(i, i+n)));
            i+=n;
        }
        return result;
    }

    /**
     * Restituisce l'energia del segnale.
     *
     * @return energia del segnale
     */
    public double getEnergy() {
        double energy = 0;

        for (int i = 0; i< data.size(); i++) {
            Complex c = new Complex(this.data.getReali()[i], this.data.getImmaginari()[i]);
            energy += Math.pow(Complex.abs(c), 2);
        }

        energy = energy/this.data.size();

        return energy;
    }

    /**
     * Restituisce SNR del segnale.
     *
     * @return SNR in Db
     */
    public double getSNR()
    {
        double snrNonDB = 1.0/(this.getEnergy()-1);
        return 10 * Math.log10(snrNonDB);
    }

    /**
     * Restituisce la probabilità di detection.
     *
     * @param attempts
     * @return
     * @throws Exception
     */
    public double getDetection(int attempts) throws Exception {
        EnergyDetector energyDetector = new EnergyDetector(this, attempts);
        return energyDetector.getProbabilityOfDetection();
    }

    /**
     * Restituisce la soglia.
     *
     * @param attempts
     * @return
     * @throws Exception
     */
    public double getThreshold(int attempts) throws Exception {
        EnergyDetector energyDetector = new EnergyDetector(this, attempts);
        return energyDetector.getThreshold();
    }

    @Override
    public String toString() {
        String str = "";
        str += ">###" + this.path + "\n";
        str += ">SNR: " + this.getSNR() + "\n";
        str += ">Energia: " + this.getEnergy() + "\n";
        try {
            str += ">Soglia: " + this.getThreshold(1000) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            str += ">Detection (%): " + this.getDetection(1000) + "%\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("\n\n\n");
        return str;
    }
}