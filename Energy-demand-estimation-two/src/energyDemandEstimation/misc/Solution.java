package energyDemandEstimation.misc;

public class Solution {

	private double[] selectedVars;

	public Solution(double[] solucion) {
		this.selectedVars = solucion;
	}

	public double[] getSelectedVars() {
		return selectedVars;
	}

}
