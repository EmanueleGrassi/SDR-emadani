import java.util.Random;


public class SignalGenerator {
	 /**
     *  genera un segnale QPSK random
     */
    public static Signal RandomQPSK(int size) throws Exception {
        double[] re = new double[size];
        double[] im = new double[size];
        for (int i = 0; i < size; i++) {
            double d = Math.random();
            if (d < 0.5)
                re[i] = 1/Math.sqrt(2);
            else
                re[i] = -1/Math.sqrt(2);
            double p = Math.random();
            if (p < 0.5)
                im[i] = 1/Math.sqrt(2);
            else
                im[i] = -1/Math.sqrt(2);
        }

        return new Signal(new ArraySignal(re, im));
    }

    /**
     *  genera un rumore bianco gaussiano
     */
    public static Signal Noise(double snr, int size) throws Exception {
        Random campione = null;
        double snr_linearizzato = Math.pow(10, (snr / 10));
        double pot_rumore = (1/snr_linearizzato);
        double[] re = new double[size];
        double[] im = new double[size];

        for (int i = 0; i < size; i++) {
            campione = new Random();
            re[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
            campione = new Random();
            im[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
        }

        return new Signal(new ArraySignal(re, im));
    }
}
