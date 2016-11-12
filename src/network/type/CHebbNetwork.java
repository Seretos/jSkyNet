package network.type;

import network.CLayer;
import network.CNetwork;
import network.CSynapse;
import network.INeuron;

public class CHebbNetwork extends CNetwork{
	private Double learn;
	
	public CHebbNetwork(Double l){
		learn = l;
	}
	
	public void execute(){
		for(CLayer layer : layers){
			layer.request();
		}
		for(CLayer layer : layers){
			layer.response();
		}
		recalculate();
		for(CLayer layer : layers){
			layer.clear();
		}
	}
	
	private void recalculate(){
		for(CLayer layer : layers){
			for(INeuron neuron : layer.getNeurons()){
				for(CSynapse syn : neuron.getInputSynapses()){
					syn.setWeight(syn.getWeight() + (learn * syn.getInput() * neuron.getValue()));
				}
			}
		}
	}
}