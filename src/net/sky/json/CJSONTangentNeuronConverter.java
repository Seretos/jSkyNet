package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.INeuron;
import net.sky.network.neuron.CNormalizeNeuron;
import net.sky.network.neuron.CTangentNeuron;

public class CJSONTangentNeuronConverter extends CJSONDefaultNeuronConverter implements IJSONNeuronConverter{
	public JSONObject convertNeuron(INeuron neuron) {
		JSONObject jsonNeuron = null;

		if(neuron instanceof CTangentNeuron){
			jsonNeuron = super.convertNeuron(neuron);
			jsonNeuron.put("type", "CTangentNeuron");
		}
		
		return jsonNeuron;
	}
	
	public INeuron convertJSONNeuron(JSONObject jsonNeuron) {
		if(jsonNeuron.get("type") == "CTangentNeuron"){
			CTangentNeuron neuron = new CTangentNeuron();
			this.setNeuronSettings(neuron,jsonNeuron);
			return neuron;
		}
		
		return null;
	}
}
