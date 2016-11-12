package tests.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import network.neuron.CLinearNeuron;

public class CLinearNeuronTest {

	private CLinearNeuron neuron;
	@Before
	public void setUp() throws Exception {
		neuron = new CLinearNeuron();
	}

	@Test
	public void calculate() {
		neuron.calculate(0.2);
		assertEquals(0,neuron.getValue().compareTo(0.2));
		
		neuron.calculate(-0.2);
		assertEquals(0,neuron.getValue().compareTo(-0.2));
		
		neuron.calculate(0.7);
		assertEquals(0,neuron.getValue().compareTo(0.7));
		
		neuron.setIntersection(1.0);
		
		neuron.calculate(0.2);
		assertEquals(0,neuron.getValue().compareTo(1.2));
		
		neuron.calculate(-0.2);
		assertEquals(0,neuron.getValue().compareTo(0.8));
		
		neuron.calculate(0.7);
		assertEquals(0,neuron.getValue().compareTo(1.7));
		
		neuron.setGradient(2.0);
		
		neuron.calculate(0.2);
		assertEquals(0,neuron.getValue().compareTo(1.4));
		
		neuron.calculate(-0.2);
		assertEquals(0,neuron.getValue().compareTo(0.6));
		
		neuron.calculate(0.7);
		assertEquals(0,neuron.getValue().compareTo(2.4));
	}

}
