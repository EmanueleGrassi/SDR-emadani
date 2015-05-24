import java.util.List;

public class EnergyDetector {
    private Signal signal;
    private int attempts;

    private double threshold;

    /**
     * Istanzio un segnale e il numero di prove.
     *
     * @param signal
     * @param attempts
     */
    public EnergyDetector(Signal signal, int attempts) throws Exception {
        this.signal = signal;
        this.attempts = attempts;
        this.threshold = this.generateThreshold();
    }

    /**
     * Restituisce il numero di successi.
     *
     * @return
     * @throws Exception
     */
    public double detected() throws Exception {
        int detected = 0;
        List<Signal> signals = signal.split(attempts);
        for(Signal s : signals) {
            detected += this.detection(s) ? 1 : 0;
        }
        return detected;
    }

    public double getProbabilityOfDetection() throws Exception {
        return this.detected() / (double)this.attempts * (double)100;
    }

    /**
     * Genero la soglia (offline).
     *
     * @return
     * @throws Exception
     */
    public double generateThreshold() throws Exception {
        double[] energy = new double[attempts];

        Signal noise = SignalFactory.Noise(signal.getSNR(), 10000);

        for(int i = 0; i < attempts; i++){
            energy[i] = noise.getEnergy();
        }

        Threshold threshold = new Threshold(energy);

        return threshold.generate();
    }

    /**
     * Controllo che ci sia detection.
     *
     * @param s
     * @return
     * @throws Exception
     */
    private boolean detection(Signal s) throws Exception {
        return s.getEnergy() > threshold;
    }

    /**
     * Restituisce il valore della soglia.
     *
     * @return
     */
    public double getThreshold() {
        return threshold;
    }

}