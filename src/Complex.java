public class Complex {

    private double reale;
    private double immaginario;

    public Complex(double reale, double immaginario) {
        this.reale = reale;
        this.immaginario = immaginario;
    }

    public Complex() {
        this(0,0);
    }

    public static Complex somma(Complex c1, Complex c2) throws Exception {
        if(c1 == null || c2 == null) {
            throw new Exception("i numeri complessi non devono essere nulli");
        }
        double paRe = c1.getReale() + c2.getReale();
        double paIm = c1.getImmaginario() + c2.getImmaginario();

        return new Complex(paRe, paIm);
    }

    public static Complex differenza(Complex c1, Complex c2) throws Exception {
    	if(c1 == null || c2 == null) {
            throw new Exception("i numeri complessi non devono essere nulli");
        }
        double paRe = c1.getReale() - c2.getReale();
        double paIm = c1.getImmaginario() - c2.getImmaginario();

        return new Complex(paRe, paIm);
    }

    public static Complex prodotto(Complex c1, Complex c2) throws Exception {
    	if(c1 == null || c2 == null) {
            throw new Exception("i numeri complessi non devono essere nulli");
        }
        double paRe = (c1.getReale() * c2.getReale()) - (c1.getImmaginario() * c2.getImmaginario());
        double paIm = (c1.getReale() * c2.getImmaginario()) + (c1.getImmaginario() * c2.getReale());

        return new Complex(paRe, paIm);
    }

    public static Complex prodottoScalare(Complex c, double s) {
        return new Complex(c.getReale() * s, c.getImmaginario() * s);
    }

    public static Complex complessoConiugato(Complex c) {
        return new Complex(c.getReale(), -c.getImmaginario());
    }

    public static double modulo(Complex c) {
        return Math.hypot(c.getReale(), c.getImmaginario());
    }

    public static Complex rapporto(Complex c1, Complex c2) throws Exception {
    	 if(c1 == null || c2 == null) {
             throw new Exception("i numeri complessi non devono essere nulli");
         }
         double paRe = ((c1.getReale()*c2.getReale()) +(c1.getImmaginario()*c2.getImmaginario()))/(Math.pow(c2.getReale(),2) + Math.pow(c2.getImmaginario(),2));
         double paIm = ((c2.getReale()*c1.getImmaginario()) +(c1.getReale()*c2.getImmaginario()))/(Math.pow(c2.getReale(),2) + Math.pow(c2.getImmaginario(),2));

         return new Complex(paRe, paIm);
    }

    public double getReale() {
        return this.reale;
    }

    public void setReale(double reale) {
        this.reale = reale;
    }

    public double getImmaginario() {
        return immaginario;
    }
    
    public void setImmaginario(double immaginario) {
        this.immaginario = immaginario;
    }

    @Override
    public String toString() {
        String complex = "";

        if (this.getImmaginario() == 0)
            complex += this.getReale();
        else if (this.getImmaginario() < 0 && this.getReale() != 0)
            complex = this.getReale() + " " + this.getImmaginario() + " j";
        else if (this.getReale() == 0)
            complex = this.getImmaginario() + " j";
        else
            complex = this.getReale() + " + " + this.getImmaginario() + " j";

        return complex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complex complex = (Complex) o;

        if (Double.compare(complex.immaginario, immaginario) != 0) return false;
        if (Double.compare(complex.reale, reale) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(reale);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(immaginario);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}