package network;

public class CSynapse {
	private INeuron sourceNeuron;
	private INeuron targetNeuron;
	private Double weight;
	private Double input;
	
	public CSynapse(INeuron source, INeuron target, Double w){
		sourceNeuron = source;
		targetNeuron = target;
		weight = w;
		input = 0.0;
	}
	
	public INeuron getSourceNeuron(){
		return sourceNeuron;
	}
	
	public INeuron getTargetNeuron(){
		return targetNeuron;
	}
	
	public void setWeight(Double w){
		weight = w;
	}
	
	public Double getWeight(){
		return weight;
	}
	
	public void setInput(Double i){
		input = i;
	}
	
	public Double getInput(){
		return input;
	}
	
	public Double calculate(){
		return input * weight;
	}
}
