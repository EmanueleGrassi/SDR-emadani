import java.util.Arrays;

public class ArraySignal {

    private int size;
    private double[] reali;
    private double[] immaginari;

    public ArraySignal(double[] reali, double[] immaginari) throws Exception {
        if(reali.length == immaginari.length) {
            this.reali = reali;
            this.immaginari = immaginari;
            this.size = reali.length;
        }
        else {
            throw new Exception("Il numero di campioni reali ed immaginari deve essere uguale.");
        }
    }

    /**
     * Divide i valori in blocchi da n campioni.
     *
     * @param from
     * @param to
     * @return una parte dell'ArraySignal
     */
    public ArraySignal slice(int from, int to) throws Exception {
        return new ArraySignal(
                Arrays.copyOfRange(reali, from, to),
                Arrays.copyOfRange(immaginari, from, to)
        );
    }

    public int size() {
        return this.size;
    }

    public double[] getReali() {
        return this.reali;
    }

    public double[] getImmaginari() {
        return this.immaginari;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i=0; i<size; i++) {
            result += new Complex(reali[i], immaginari[i]).toString() + "\n";
        }
        return result;
    }
}