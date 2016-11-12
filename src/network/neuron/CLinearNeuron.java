package network.neuron;

import network.CBaseNeuron;

public class CLinearNeuron extends CBaseNeuron{
	private Double gradient;
	private Double intersection;
	
	public CLinearNeuron(){
		gradient = 1.0;
		intersection = 0.0;
	}
	
	public void calculate(Double val) {
		super.setValue(gradient * val + intersection);
	}
	
	public void setGradient(Double g){
		gradient = g;
	}
	
	public void setIntersection(Double i){
		intersection = i;
	}
}
