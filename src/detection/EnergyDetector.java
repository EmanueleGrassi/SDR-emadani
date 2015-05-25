package detection;
import java.util.List;

import signal.Signal;
import signal.SignalGenerator;

public class EnergyDetector {
	
    private Signal signal;
    private int tentativi;

    private double soglia;

    public EnergyDetector(Signal signal, int tentativi) throws Exception {
        this.signal = signal;
        this.tentativi = tentativi;
        this.soglia = this.generateThreshold();
    }

    /**
     *  genera il threshold
     */
    public double generateThreshold() throws Exception {
    	
        double[] energy = new double[tentativi];
        Signal noise = SignalGenerator.Noise(this.signal.getSNR(), 10000);

        for(int i = 0; i < tentativi; i++){
            energy[i] = noise.getEnergy();
        }

        Threshold threshold = new Threshold(energy);
        return threshold.genera();
    }
    
    /**
     * calcola il numero di successi
     */
    public double detected() throws Exception {
        int detected = 0;
        List<Signal> signals = signal.separa(tentativi);
        for(Signal s : signals) {
        	if (this.detection(s))
        		detected ++;
        }
        return detected;
    }
    
    /**
     * probability of detection in percentuale
     */
    public double getPoD() throws Exception {
        return this.detected() / (double)this.tentativi * (double)100;
    }

    /**
     *  se l'energia è minore del threshold non ho detection
     */
    private boolean detection(Signal s) throws Exception {
        return s.getEnergy() > soglia;
    }
    
    public double getThreshold() {
        return soglia;
    }

}