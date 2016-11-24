package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.CNeuron;
import net.sky.network.INeuron;

public class CJSONDefaultNeuronConverter implements IJSONNeuronConverter{

	public JSONObject convertNeuron(INeuron neuron) {
		JSONObject jsonNeuron = new JSONObject();
		
		jsonNeuron.put("type", "CNeuron");
		jsonNeuron.put("id", neuron.getId());
		if(neuron.isThreshold()){
			jsonNeuron.put("threshold", neuron.getThreshold());
		}
		
		return jsonNeuron;
	}

	public INeuron convertJSONNeuron(JSONObject jsonNeuron) {
		CNeuron neuron = new CNeuron();
		this.setNeuronSettings(neuron,jsonNeuron);
		
		return neuron;
	}

	protected void setNeuronSettings(INeuron neuron, JSONObject jsonNeuron){
		neuron.setId((int)jsonNeuron.get("id"));
		if(jsonNeuron.containsKey("threshold")){
			neuron.setThreshold((float)jsonNeuron.get("threshold"));
		}
	}
}
