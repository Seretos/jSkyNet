package main.java.network.type.backpropagation;

import java.util.ArrayList;
import java.util.List;

import main.java.network.INeuron;

public class CDeltaNeuronBag {
	private List<CDeltaNeuron> neurons;
	
	public CDeltaNeuronBag(){
		neurons = new ArrayList<CDeltaNeuron>();
	}
	
	public CDeltaNeuron addDelta(INeuron neuron, float delta){
		for(CDeltaNeuron dNeuron : neurons){
			if(dNeuron.getNeuron() == neuron){
				dNeuron.addDelta(delta);
				return dNeuron;
			}
		}
		CDeltaNeuron dNeuron = new CDeltaNeuron(neuron,delta);
		neurons.add(dNeuron);
		return dNeuron;
	}
	
	public List<CDeltaNeuron> getDeltaNeuron(){
		return neurons;
	}
}
