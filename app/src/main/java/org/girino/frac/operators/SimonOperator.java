package org.girino.frac.operators;

public class SimonOperator extends FractalOperator {
	
	Complex tmp = new Complex();
	Complex tmp2 = new Complex();
	Complex absZ = new Complex();

	protected void beforeIteration(int step, Complex Z, Complex C, int maxiter) {}
	@Override
	protected void resumeIteration(int step, Complex Z, Complex C, int maxiter) {}

	protected void step(int step, Complex Z, Complex C, int maxiter) {
		// absZ = |Re(Z)| + i*|Im(Z)|
		double ax = Math.abs(Z.getReal());
		double ay = Math.abs(Z.getImag());
		absZ.setReal(ax);
		absZ.setImag(ay);
		
		// tmp = Z * Z
		tmp.ltimes(Z, Z);
		
		// tmp2 = absZ * absZ
		tmp2.ltimes(absZ, absZ);
		
		// tmp = tmp * tmp2
		tmp.ltimes(tmp, tmp2);
		
		// Z = tmp + C  (your formula: z^2 * (|re|+i|im|)^2 + c)
		Z.ladd(tmp, C);
	}
}
