package uk.dangrew.kode.utility.core;

public class DoubleMath {

    private final int scaleFactor;

    public DoubleMath(int scaleFactor){
        this.scaleFactor = scaleFactor;
    }

    public double doubleModulus(double a, double b){
        if ( b == 0.0 ) {
            return a;
        }
        int scaledA = (int)(a * scaleFactor);
        int scaledB = (int)(b * scaleFactor);
        int result = scaledA % scaledB;
        return (double) result / (double)scaleFactor;
    }

    public double doubleDivision(double a, double b){
        int scaledA = (int)Math.ceil(a * scaleFactor);
        int scaledB = (int)Math.ceil(b * scaleFactor);
        double result = (double)scaledA / (double)scaledB;
        return result;
    }

    public double doubleSubtraction(double a, double b){
        int scaledA = (int)Math.ceil(a * scaleFactor);
        int scaledB = (int)Math.ceil(b * scaleFactor);
        double result = (double)scaledA - (double)scaledB;
        return result / scaleFactor;
    }

    public double doubleAddition(double a, double b){
        int scaledA = (int)Math.ceil(a * scaleFactor);
        int scaledB = (int)Math.ceil(b * scaleFactor);
        double result = (double)scaledA + (double)scaledB;
        return result / scaleFactor;
    }
}
