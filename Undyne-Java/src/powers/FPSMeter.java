package powers;

public class FPSMeter {

	private long lastTime;
	
	double[] samples = new double[10];
	int idx = 0;
	
	public FPSMeter() {
		lastTime = System.currentTimeMillis();
	}
	
	public double getFPS() {
		long currentTime = System.currentTimeMillis();
		double fps = 1.0/((currentTime - lastTime) / 1000.0);
		lastTime = currentTime;
		samples[idx] = fps;
		idx = (idx+1)%samples.length;
		double sum = 0;
		for(double d : samples) sum += d;
		return sum / samples.length;
	}

}
