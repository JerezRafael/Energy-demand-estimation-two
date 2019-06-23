package energyDemandEstimation.PredictionModel;

import energyDemandEstimation.misc.Solution;

public class PredictionModel {

	// Aplica el modelo de predicci�n exponencial al a�o con la soluci�n pasados por
	// par�metro
	public static long apply(double[] year, Solution solution) {

		double[] parameters = solution.getParameters();

		long output = (long) parameters[0];

		for (int i = 1; i < year.length; i++)
			output += parameters[i * 2 - 1] * Math.pow(year[i], parameters[i * 2]);

		return output;
	}
}
