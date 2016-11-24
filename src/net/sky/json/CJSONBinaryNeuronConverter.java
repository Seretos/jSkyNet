package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.CNeuron;
import net.sky.network.INeuron;
import net.sky.network.neuron.CBinaryNeuron;

public class CJSONBinaryNeuronConverter extends CJSONDefaultNeuronConverter implements IJSONNeuronConverter{

	public JSONObject convertNeuron(INeuron neuron) {
		JSONObject jsonNeuron = null;

		if(neuron instanceof CBinaryNeuron){
			jsonNeuron = super.convertNeuron(neuron);
			jsonNeuron.put("type", "CBinaryNeuron");
		}
		
		return jsonNeuron;
	}

	public INeuron convertJSONNeuron(JSONObject jsonNeuron) {
		if(jsonNeuron.get("type") == "CBinaryNeuron"){
			CBinaryNeuron neuron = new CBinaryNeuron((float)jsonNeuron.get("threshold"));
			this.setNeuronSettings(neuron,jsonNeuron);
			return neuron;
		}
		
		return null;
	}
}
