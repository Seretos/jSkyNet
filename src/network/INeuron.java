package network;

import java.util.List;

public interface INeuron {
	public void addInputSynapse(CSynapse synapse);
	public void addOutputSynapse(CSynapse synapse);
	
	public List<CSynapse> getInputSynapses();
	public List<CSynapse> getOutputSynapses();
	
	public void setValue(float val);
	public float getValue();
	
	public void request();
	public void response();
	public void clear();
	
	public void calculate(float val);
	
	public void setThreshold(float t);
	public float getThreshold();
}
