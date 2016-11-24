package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.INeuron;
import net.sky.network.neuron.CBinaryNeuron;
import net.sky.network.neuron.CLinearNeuron;

public class CJSONLinearNeuronConverter extends CJSONDefaultNeuronConverter implements IJSONNeuronConverter{
	public JSONObject convertNeuron(INeuron neuron) {
		JSONObject jsonNeuron = null;

		if(neuron instanceof CLinearNeuron){
			CLinearNeuron linNeuron = (CLinearNeuron)neuron;
			jsonNeuron = super.convertNeuron(neuron);
			jsonNeuron.put("type", "CLinearNeuron");
			jsonNeuron.put("gradient", linNeuron.getGradient());
			jsonNeuron.put("intersection", linNeuron.getIntersection());
		}
		
		return jsonNeuron;
	}
	
	public INeuron convertJSONNeuron(JSONObject jsonNeuron) {
		if(jsonNeuron.get("type") == "CLinearNeuron"){
			CLinearNeuron neuron = new CLinearNeuron();
			this.setNeuronSettings(neuron,jsonNeuron);
			neuron.setGradient((float)jsonNeuron.get("gradient"));
			neuron.setIntersection((float)jsonNeuron.get("intersection"));
			return neuron;
		}
		
		return null;
	}
}
