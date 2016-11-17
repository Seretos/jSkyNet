package network.type.backpropagation;

import network.INeuron;

public class CDeltaNeuron {
	private INeuron neuron;
	private float delta;
	
	public CDeltaNeuron(INeuron n, float d){
		neuron = n;
		delta = d;
	}
	
	public INeuron getNeuron(){
		return neuron;
	}
	
	public float getDelta(){
		return delta;
	}
	
	public void addDelta(float d){
		delta *= d;
	}
}
