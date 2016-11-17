package network.type;

import java.util.List;

import network.CLayer;
import network.CNetwork;
import network.CSynapse;
import network.CTrainingSet;
import network.INeuron;
import network.type.backpropagation.CDeltaNeuron;
import network.type.backpropagation.CDeltaNeuronBag;

public class CBackpropagationNetwork extends CNetwork{
	private float learn;
	private float tolerance;
	private CLayer inputLayer;
	private CLayer outputLayer;
	
	public CBackpropagationNetwork(float l, float t){
		learn = l;
		tolerance = t;
	}
	
	public void setInputLayer(CLayer layer){
		super.addLayer(layer);
		inputLayer = layer;
	}
	
	public void setOutputLayer(CLayer layer){
		super.addLayer(layer);
		outputLayer = layer;
	}
	
	public void train(List<CTrainingSet> sets, int steps){
		for(CTrainingSet set : sets){
			setInputs(set);
			super.run(steps);
			CDeltaNeuronBag bag = buildOutputBag(set);
			int currentSteps = steps;
			do{
				bag = buildNextBag(bag);
				setInputs(set);
				currentSteps--;
				super.run(currentSteps);
			}while(currentSteps > 0);
		}
	}
	
	private CDeltaNeuronBag buildOutputBag(CTrainingSet set){
		CDeltaNeuronBag bag = new CDeltaNeuronBag();
		
		for(int i=0;i<set.getOutputs().size();i++){
			INeuron neuron = outputLayer.getNeurons().get(i);
			float netIn = 0.0f;
			for(CSynapse syn : neuron.getInputSynapses()){
				netIn += syn.getInput();
			}
			float val = neuron.getValue();
			/*if(Float.isInfinite(val)){
				val = 0.0f;
			}*/
			float delta = set.getOutputs().get(i) - val;
			float uDelta = delta;
			if(uDelta < 0){
				uDelta = -uDelta;
			}
			if(uDelta > tolerance){
				delta = netIn * delta;
				//System.out.println(delta);
				bag.addDelta(outputLayer.getNeurons().get(i), delta);
			}
		}
		
		return bag;
	}
	
	private CDeltaNeuronBag buildNextBag(CDeltaNeuronBag currentBag){
		CDeltaNeuronBag nextBag = new CDeltaNeuronBag();

		for(CDeltaNeuron dNeuron : currentBag.getDeltaNeuron()){
			for(CSynapse syn : dNeuron.getNeuron().getInputSynapses()){
				float uDelta = dNeuron.getDelta();
				if(uDelta < 0){
					uDelta = -uDelta;
				}
				if(uDelta > tolerance){
					Float nextWeight = learn * dNeuron.getDelta() * syn.getInput();
					/*if(nextWeight.isNaN()||nextWeight.isInfinite()){
						nextWeight = 0.0f;
					}*/
					syn.setWeight(syn.getWeight() + nextWeight);
					float delta = dNeuron.getDelta() * syn.getWeight();
					nextBag.addDelta(syn.getSourceNeuron(),delta);
				}
			}
		}
		
		return nextBag;
	}
	
	private void setInputs(CTrainingSet set){
		for(int i=0;i<set.getInputs().size();i++){
			inputLayer.getNeurons().get(i).calculate(set.getInputs().get(i));
		}
	}
}
