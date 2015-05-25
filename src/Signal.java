import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Signal {

    private int size;
    private double[] reali;
    private double[] immaginari;
    private String path;


    /**
     *  crea un segnale da file
     */
    public Signal(String path) throws Exception {
        FileSignal s = new FileSignal(path);
        this.path = path;
        this.size = s.SIZE;
		this.reali = s.getReali();
		this.immaginari = s.getImmaginari();
    }

    public Signal(int size, double[] reali, double[] immaginari) {
		super();
		this.size = size;
		this.reali = reali;
		this.immaginari = immaginari;
	}

    public Signal dividi(int from, int to) throws Exception {
        return new Signal(
        		to-from, //giusto?
                Arrays.copyOfRange(reali, from, to),
                Arrays.copyOfRange(immaginari, from, to)
        );
    }
    
	/**
     * metodo per separare il segnale in più sezioni da n campioni
     */
    public List<Signal> separa(int n) throws Exception {
        List<Signal> result = new ArrayList<>();
        int i = 0;
        while(i<this.size) {
            result.add(dividi(i, i+n));
            i+=n;
        }
        return result;
    }

    public double getEnergy() {
        double energy = 0;

        for (int i = 0; i< this.size; i++) {
            Complex c = new Complex(this.getReali()[i], this.getImmaginari()[i]);
            energy += Math.pow(Complex.modulo(c), 2);
        }
        
        return energy/this.size;
    }

    /**
     *  snr in decibel
     */
    public double getSNR()
    {
        double snr = 1.0/(this.getEnergy()-1);
        return 10 * Math.log10(snr);
    }

    public double getDetection(int tentativi) throws Exception {
        EnergyDetector energyDetector = new EnergyDetector(this, tentativi);
        return energyDetector.getPoD();
    }

    public double getThreshold(int attempts) throws Exception {
        EnergyDetector energyDetector = new EnergyDetector(this, attempts);
        return energyDetector.getThreshold();
    }



    public double[] getReali() {
        return this.reali;
    }

    public double[] getImmaginari() {
        return this.immaginari;
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