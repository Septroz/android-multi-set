package org.girino.frac.operators;

public class SimonOperator extends FractalOperator {
	
	Complex tmp = new Complex();
	Complex tmp2 = new Complex();
	Complex absZ = new Complex();
	Complex tmp3 = new Complex(); // extra temp to avoid aliasing bug

	protected void beforeIteration(int step, Complex Z, Complex C, int maxiter) {}
	@Override
	protected void resumeIteration(int step, Complex Z, Complex C, int maxiter) {}

	protected void step(int step, Complex Z, Complex C, int maxiter) {
		double ax = Math.abs(Z.getReal());
		double ay = Math.abs(Z.getImag());
		absZ = new Complex(ax, ay);
		
		tmp.ltimes(Z, Z);              // Z^2
		tmp2.ltimes(absZ, absZ);       // (|re|+i|im|)^2
		tmp3.ltimes(tmp, tmp2);         // Z^2 * ...
		
		// scale down to keep it degree ~2 so it doesn't instantly blow up
		// this keeps your shape but makes it renderable
		double mag = Math.sqrt(tmp3.getReal()*tmp3.getReal() + tmp3.getImag()*tmp3.getImag());
		if (mag > 1e6) {
			tmp3.setReal(tmp3.getReal() / mag * 1e3);
			tmp3.setImag(tmp3.getImag() / mag * 1e3);
		}
		
		Z.ladd(tmp3, C);
	}
}
