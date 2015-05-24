public class Complex {

    private double reale;
    private double immaginario;

    public Complex(double reale, double immaginario) {
        this.reale = reale;
        this.immaginario = immaginario;
    }

    public Complex() {
        this.reale = 0;
        this.immaginario = 0;
    }

    public static Complex somma(Complex c1, Complex c2) throws Exception {
        if(c1 == null) {
            throw new Exception("c1 must not be null");
        }
        double paRe = c1.getReale() + c2.getReale();
        double paIm = c1.getImmaginario() + c2.getImmaginario();

        return new Complex(paRe, paIm);
    }

    public static Complex differenza(Complex c1, Complex c2) {
        double paRe = c1.getReale() - c2.getReale();
        double paIm = c1.getImmaginario() - c2.getImmaginario();

        return new Complex(paRe, paIm);
    }

    public static Complex prodotto(Complex c1, Complex c2) {
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

    public static double abs(Complex c) {
        return Math.hypot(c.getReale(), c.getImmaginario());
    }

    public static Complex reciproco(Complex c) throws Exception {
    	if (c.getReale() == 0 && c.getImmaginario() == 0) {
    		throw new Exception("c1 must not be 0");
    	}
        double scalare = Math.pow(c.getReale(), 2) + Math.pow(c.getImmaginario(), 2);
        Complex result = new Complex(c.getReale() / scalare, -(c.getImmaginario() / scalare));
    	return result;
    }

    public static Complex rapporto(Complex c1, Complex c2) throws Exception {
        return Complex.prodotto(c1, Complex.reciproco(c2));
    }

    public static Complex exp(Complex c) {
        return new Complex(
                Math.exp(c.getReale()) * Math.cos(c.getImmaginario()),
                Math.exp(c.getReale()) * Math.sin(c.getImmaginario())
        );
    }

    public static Complex sin(Complex c) {
        return new Complex(
                Math.cosh(c.getImmaginario()) * Math.sin(c.getReale()),
                Math.sinh(c.getImmaginario()) * Math.cos(c.getReale())
        );
    }

    public static Complex cos(Complex c) {
        return new Complex(
                Math.cosh(c.getImmaginario()) * Math.cos(c.getReale()),
                -1 * Math.sinh(c.getImmaginario()) * Math.sin(c.getReale())
        );
    }

    public static Complex sinh(Complex c) {
        return new Complex(
                Math.sinh(c.getReale()) * Math.cos(c.getImmaginario()),
                Math.cosh(c.getReale()) * Math.sin(c.getImmaginario())
        );
    }

    public static Complex cosh(Complex c) {
        return new Complex(
                Math.cosh(c.getReale()) * Math.cos(c.getImmaginario()),
                Math.sinh(c.getReale()) * Math.sin(c.getImmaginario())
        );
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

    public Complex moduloQuadro() {
        return this.complessoConiugato(this);
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