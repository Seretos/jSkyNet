package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.INeuron;
import net.sky.network.neuron.CLogisticNeuron;
import net.sky.network.neuron.CNormalizeNeuron;

public class CJSONNormalizeNeuronConverter extends CJSONDefaultNeuronConverter implements IJSONNeuronConverter{
	public JSONObject convertNeuron(INeuron neuron) {
		JSONObject jsonNeuron = null;

		if(neuron instanceof CNormalizeNeuron){
			jsonNeuron = super.convertNeuron(neuron);
			jsonNeuron.put("type", "CNormalizeNeuron");
		}
		
		return jsonNeuron;
	}
	
	public INeuron convertJSONNeuron(JSONObject jsonNeuron) {
		if(jsonNeuron.get("type") == "CNormalizeNeuron"){
			CNormalizeNeuron neuron = new CNormalizeNeuron();
			this.setNeuronSettings(neuron,jsonNeuron);
			return neuron;
		}
		
		return null;
	}
}
