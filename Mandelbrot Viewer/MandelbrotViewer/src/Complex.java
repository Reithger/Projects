/*
 * Mac Clevinger
 * April 10, 2018
 * Assignment 3
 * Class: Complex
 * 
 * This is a helper class for the MandelbrotViewer class that represents Complex values, which are
 * composed of two elements: Real and Complex values. Allows for Adding, Subtracting, Multiplying, and
 * performing Modulus.
 * 
 * Additional Feature: Multiple versions of the multiply() method to allow for variations of the Mandelbrot
 * to be calculated.
 * 
 */

/**
 * This is a helper class for the MandelbrotViewer class that represents Complex values, which are
 * composed of two elements: Real and Complex values. Allows for Adding, Subtracting, Multiplying, and
 * performing Modulus.
 *  
 * @author Mac Clevinger
 * @version 1.0.0
 * @since 2018-03-26
 */

public class Complex {
							/** Instance Variable of double type for the Real number value of the Complex object */
	private double real;	/** Instance Variable of double type for the Complex number value of the Complex object */
	private double complex; 
	
	/**
	 * This method constructs a new Complex Object from two double values representing
	 * its Real and Complex values.
	 * 
	 * @param rl This parameter is the Real value of the new Complex object.
	 * @param cmplx This parameter is the Complex value of the new Complex object.
	 */
	
	public Complex(double rl, double cmplx){
		real = rl;
		complex = cmplx;
	}
	
	
	/**
	 * This method constructs a new Complex Object from a pre-existing Complex object,
	 * copying the values over.
	 * 
	 * @param c This parameter is a Complex object that's values are copied.
	 */
	public Complex(Complex c){
		real = c.getReal();
		complex = c.getComplex();
	}
	
	/**
	 * This method returns the Real value instance variable 
	 * 
	 * @return Returns the value of the instance variable Real associated to this object
	 */
	
	public double getReal(){
		return real;
	}
	
	/**
	 * This method returns the Complex value instance variable
	 * 
	 * @return Returns the value of the instance variable Complex associated to this object
	 */
	
	public double getComplex(){
		return complex;
	}
	
	/**
	 * This method calculates the summation of two Complex objects, saving the
	 * produced values to the Complex object calling this method.
	 * 
	 * @param val This parameter is a Complex object that's values are added to the calling Complex object.
	 */
	
	public void add(Complex val){
		real += val.getReal();
		complex += val.getComplex();
	}
	
	/**
	 * This method calculates the difference of two Complex objects, saving the
	 * produced values to the Complex object calling this method.
	 * 
	 * @param val This parameter is a Complex object that's values are deducted from the calling Complex object.
	 */
	
	public void subtract(Complex val){
		real -= val.getReal();
		complex -= val.getComplex();
	}
	
	/**
	 * This method calculates the product of two Complex objects, saving the
	 * produced values to the Complex object calling this method.
	 * 
	 * @param val This parameter is a Complex object that's values are multiplied to the calling Complex object.
	 */
	
	public void multiply(Complex val){
		double copyReal = getReal();	//Values are copied due to issues of values changing between equations; maintains consistency.
		double copyCompl = getComplex();
		double othReal = val.getReal();
		double othCompl = val.getComplex();
		real = copyReal * othReal - copyCompl * othCompl;
		complex = copyReal * othCompl + copyCompl * othReal;
		//This is a segment of code left in because, while it was wrong for the Mandelbrot, it looked really cool and I want easy access to getting it again.
		//The problem was that, in the second equation, it recognized that the first had changed when it should have been using the original Real value.
		/*
		  real = getReal() * val.getReal() - getComplex() * val.getComplex();
		  complex = getReal() * val.getComplex() + getComplex() * val.getReal();
		*/
	}
	
	/**
	 * This method is a variation of the previously described multiply(Complex c) method;
	 * the calculation of the Complex variable is done using the Real value calculated in the
	 * same process, instead of using values from before any calculations are performed.
	 * 
	 * @param val This parameter is a Complex object that's values are multiplied to the calling Complex object.
	 */
	
	public void multiplyVar1(Complex val){
		real = getReal() * val.getReal() - getComplex() * val.getComplex();
		complex = getReal() * val.getComplex() + getComplex() * val.getReal();
	}
	
	/**
	 * This method is the second variation of the previously described multiply(Complex c) method;
	 * the calculation of the Real variable is done using the Complex value calculated in the
	 * same process, instead of using values from before any calculations are performed.
	 * 
	 * @param val This parameter is a Complex object that's values are multiplied to the calling Complex object.
	 */
	
	public void multiplyVar2(Complex val){
		complex = getReal() * val.getComplex() + getComplex() * val.getReal();
		real = getReal() * val.getReal() - getComplex() * val.getComplex();
	}
	
	/**
	 * This method calculates the distance of this Complex value from the origin using
	 * the pythagoras theorem to do so.
	 * 
	 * @return Returns a double value that is the square root of the sum of the Real and Complex values squared.
	 */
	
	public double modulus(){
		return Math.sqrt(Math.pow(getReal(), 2) + Math.pow(getComplex(), 2));
	}
}