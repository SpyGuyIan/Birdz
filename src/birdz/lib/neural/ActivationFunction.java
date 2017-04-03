package birdz.lib.neural;

public interface ActivationFunction {
	double getValue(double input);
}

class SigmoidFunction implements ActivationFunction {

	@Override
	public double getValue(double input) {
		return 1.0 / (1 + Math.pow(Math.E, -input)); 
	}
	
}