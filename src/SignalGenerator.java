import java.util.Random;


public class SignalGenerator {
	 /**
     *  genera un segnale QPSK random
     */
    public static Signal RandomQPSK(int size) throws Exception {
        double[] reali = new double[size];
        double[] immaginari = new double[size];
        for (int i = 0; i < size; i++) {
            double d = Math.random();
            if (d < 0.5)
                reali[i] = 1/Math.sqrt(2);
            else
                reali[i] = -1/Math.sqrt(2);
            double p = Math.random();
            if (p < 0.5)
                immaginari[i] = 1/Math.sqrt(2);
            else
                immaginari[i] = -1/Math.sqrt(2);
        }

        return new Signal(size, reali, immaginari);
    }

    /**
     *  genera un rumore bianco gaussiano
     */
    public static Signal Noise(double snr, int size) throws Exception {
        Random campione = null;
        double snr_linearizzato = Math.pow(10, (snr / 10));
        double pot_rumore = (1/snr_linearizzato);
        double[] reali = new double[size];
        double[] immaginari = new double[size];

        for (int i = 0; i < size; i++) {
            campione = new Random();
            reali[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
            campione = new Random();
            immaginari[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
        }

        return new Signal(size, reali, immaginari);
    }
}
