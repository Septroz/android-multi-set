package org.girino.frac.operators;

/**
 * operator for mandelbrot sets.. 
 * 
 * Zn+1 = Zn^2 * (|Re(Zn)| + i*|Im(Zn)|)^2 + C
 * Z0 = 0
 * @author girino
 */
public class SimonOperator extends FractalOperator {
	
	Complex tmp = new Complex();      // Zn^2
	Complex absZ = new Complex();     // |Re|+i|Im|
	Complex tmp2 = new Complex();     // (|Re|+i|Im|)^2

	protected void beforeIteration(int step, Complex Z, Complex C, int maxiter) {
		// no C transform needed for +C formula
	}

	@Override
	protected void resumeIteration(int step, Complex Z, Complex C, int maxiter) {
		// no C transform needed
	}

	protected void step(int step, Complex Z, Complex C, int maxiter) {
		// absZ = |Re(Z)| + i*|Im(Z)|
		absZ.set(Math.abs(Z.getReal()), Math.abs(Z.getImag()));
		
		// tmp = Z * Z
		tmp.ltimes(Z, Z);
		
		// tmp2 = absZ * absZ
		tmp2.ltimes(absZ, absZ);
		
		// tmp = tmp * tmp2 = Z^2 * (|Re|+i|Im|)^2
		tmp.ltimes(tmp, tmp2);
		
		// Z = tmp + C
		Z.ladd(tmp, C);
	}
}
