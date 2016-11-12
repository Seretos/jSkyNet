package tests.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import network.neuron.CNormalizeNeuron;

public class CNormalizeNeuronTest {

	private CNormalizeNeuron neuron;
	@Before
	public void setUp() throws Exception {
		neuron = new CNormalizeNeuron();
	}

	@Test
	public void calculate() {
		neuron.calculate(-2.0);
		assertEquals(0,neuron.getValue().compareTo(0.05399096651318806));
		
		neuron.calculate(-0.2);
		assertEquals(0,neuron.getValue().compareTo(0.3910426939754559));
		
		neuron.calculate(0.0);
		assertEquals(0,neuron.getValue().compareTo(0.3989422804014327));
		
		neuron.calculate(0.2);
		assertEquals(0,neuron.getValue().compareTo(0.3910426939754559));
		
		neuron.calculate(0.7);
		assertEquals(0,neuron.getValue().compareTo(0.3122539333667613));
		
		neuron.calculate(1.0);
		assertEquals(0,neuron.getValue().compareTo(0.24197072451914337));
		
		neuron.calculate(20.0);
		assertEquals(0,neuron.getValue().compareTo(5.520948362159823E-88));
	}

}
