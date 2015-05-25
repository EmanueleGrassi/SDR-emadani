package detection;
import signal.Signal;


public class Main {
	public static void main(String[] args) throws Exception
    {
		Signal signal;
        for(int i = 1; i<4; i++) {
            for(int j = 1; j <=4; j++) {
              if(j!=2){
                signal = new Signal("./sequenze/Sequenza_"+i+"/output_"+j+".dat");
                stampa(signal);
              }
            }
        }
    }

	private static void stampa(Signal signal) {
		String s = "";
        s += "File: " + signal.getPath() + "\n";
        s += "SNR: " + signal.getSNR() + "\n";
        try {
            s += "Soglia: " + signal.getThreshold(1000) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        s += "Energia: " + signal.getEnergy() + "\n";
        try {
            s += "Detection (in percentuale): " + signal.getDetection(1000) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(s + "\n");
	}
	
	
}
