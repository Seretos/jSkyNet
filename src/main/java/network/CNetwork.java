package main.java.network;

import java.util.ArrayList;
import java.util.List;

public class CNetwork {
	protected List<CLayer> layers;
	protected List<CSynapse> synapses;
	
	public CNetwork(){
		layers = new ArrayList<CLayer>();
		synapses = new ArrayList<CSynapse>();
	}
	
	public void addLayer(CLayer layer){
		layers.add(layer);
	}
	
	public List<CLayer> getLayers(){
		return layers;
	}
	
	public void addSynapse(CSynapse synapse){
		synapses.add(synapse);
		synapse.getSourceNeuron().addOutputSynapse(synapse);
		synapse.getTargetNeuron().addInputSynapse(synapse);
	}
	
	public List<CSynapse> getSynapses(){
		return synapses;
	}
	
	public void execute(){
		for(CLayer layer : layers){
			layer.clear();
		}
		for(CLayer layer : layers){
			layer.request();
		}
		for(CLayer layer : layers){
			layer.response();
		}
	}
	
	public void run(int steps){
		for(int i=0;i<steps;i++){
			execute();
		}
	}
}
