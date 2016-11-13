package network.neuron;

import network.CBaseNeuron;

public class CLinearNeuron extends CBaseNeuron{
	private float gradient;
	private float intersection;
	
	public CLinearNeuron(){
		gradient = 1.0f;
		intersection = 0.0f;
	}
	
	public void calculate(float val) {
		super.setValue(gradient * val + intersection);
	}
	
	public void setGradient(float g){
		gradient = g;
	}
	
	public void setIntersection(float i){
		intersection = i;
	}
}
