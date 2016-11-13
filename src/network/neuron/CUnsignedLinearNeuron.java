package network.neuron;

public class CUnsignedLinearNeuron extends CLinearNeuron {
	public void calculate(float val) {
		if(val >= 0){
			super.calculate(val);
		}else{
			super.setValue(0.0f);
		}
	}
}
