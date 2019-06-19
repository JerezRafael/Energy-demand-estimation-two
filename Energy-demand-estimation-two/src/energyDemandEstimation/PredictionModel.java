package energyDemandEstimation;

import energyDemandEstimation.misc.Solution;

public class PredictionModel {

	public static double apply (double[] year, Solution solution) {
		
		double[] parameters = solution.getParameters();
		
		double output = parameters[0];
		
		for (int i = 1; i < year.length; i++) {
			output += parameters[i * 2 - 1] * Math.pow(year[i], parameters [i * 2]);
		}
		
		return output;
	}
}
