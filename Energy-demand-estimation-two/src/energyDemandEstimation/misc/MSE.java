package energyDemandEstimation.misc;

import energyDemandEstimation.PredictionModel.PredictionModel;
import energyDemandEstimation.data.Data;

public class MSE {

	private final static Data data = new Data();

	// Calcula el MSE de la solución que se le pasa por parámetro y lo devuelve
	public static double error(Solution solution) {

		double error = 0;
		double[][] trainData = data.getTrainData();
		for (int i = 0; i < trainData.length; i++) {
			error += Math.sqrt(trainData[i][0] - PredictionModel.apply(trainData[i], solution));
		}
		error = error / trainData.length;
		return error;
	}
}
