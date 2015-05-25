
public class Main {
	public static void main(String[] args) throws Exception
    {
		Signal signal;
        for(int i = 1; i<4; i++) {
            for(int j = 1; j <=4; j++) {
                signal = new Signal("./sequenze/Sequenza_"+i+"/output_"+j+".dat");
                if(j!=2) System.out.print(signal);
            }
        }
    }
}
