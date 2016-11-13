package tests.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import network.neuron.CLogisticNeuron;

public class CLogisticNeuronTest {

	private CLogisticNeuron neuron;
	@Before
	public void setUp() throws Exception {
		neuron = new CLogisticNeuron();
	}

	@Test
	public void calculate() {
		neuron.calculate(-2.0f);
		assertTrue(0.11920292f == neuron.getValue());
		
		neuron.calculate(-0.2f);
		assertTrue(0.45016602f == neuron.getValue());
		
		neuron.calculate(0.0f);
		assertTrue(0.5f == neuron.getValue());
		
		neuron.calculate(0.2f);
		assertTrue(0.54983395f == neuron.getValue());
		
		neuron.calculate(0.7f);
		assertTrue(0.66818774f == neuron.getValue());
		
		neuron.calculate(1.0f);
		assertTrue(0.7310586f == neuron.getValue());
		
		neuron.calculate(20.0f);
		assertTrue(1.0f == neuron.getValue());
	}

}
