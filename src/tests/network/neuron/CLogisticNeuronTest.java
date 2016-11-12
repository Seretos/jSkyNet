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
		neuron.calculate(-2.0);
		assertEquals(0,neuron.getValue().compareTo(0.11920292202211757));
		
		neuron.calculate(-0.2);
		assertEquals(0,neuron.getValue().compareTo(0.45016600268752216));
		
		neuron.calculate(0.0);
		assertEquals(0,neuron.getValue().compareTo(0.5));
		
		neuron.calculate(0.2);
		assertEquals(0,neuron.getValue().compareTo(0.549833997312478));
		
		neuron.calculate(0.7);
		assertEquals(0,neuron.getValue().compareTo(0.668187772168166));
		
		neuron.calculate(1.0);
		assertEquals(0,neuron.getValue().compareTo(0.7310585786300049));
		
		neuron.calculate(20.0);
		assertEquals(0,neuron.getValue().compareTo(0.9999999979388463));
	}

}
