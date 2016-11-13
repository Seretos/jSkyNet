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
		neuron.calculate(0.2f);
		assertTrue(0.2f == neuron.getValue());
		
		neuron.calculate(-0.2f);
		assertTrue(-0.2f == neuron.getValue());
		
		neuron.calculate(0.7f);
		assertTrue(0.7f == neuron.getValue());
		
		neuron.setIntersection(1.0f);
		
		neuron.calculate(0.2f);
		assertTrue(1.2f == neuron.getValue());
		
		neuron.calculate(-0.2f);
		assertTrue(0.8f == neuron.getValue());
		
		neuron.calculate(0.7f);
		assertTrue(1.7f == neuron.getValue());
		
		neuron.setGradient(2.0f);
		
		neuron.calculate(0.2f);
		assertTrue(1.4f == neuron.getValue());
		
		neuron.calculate(-0.2f);
		assertTrue(0.6f == neuron.getValue());
		
		neuron.calculate(0.7f);
		assertTrue(2.4f == neuron.getValue());
	}
}
