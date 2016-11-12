package network;

import java.util.List;

public interface INeuron {
	public void addInputSynapse(CSynapse synapse);
	public void addOutputSynapse(CSynapse synapse);
	
	public List<CSynapse> getInputSynapses();
	public List<CSynapse> getOutputSynapses();
	
	public void setValue(Double val);
	public Double getValue();
	
	public void request();
	public void response();
	public void clear();
	
	public void calculate(Double val);
	
	public void setThreshold(Double t);
	public Double getThreshold();
}
