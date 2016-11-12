package tests.network;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import network.CSynapse;
import network.INeuron;

public class CSynapseTest {

	private CSynapse synapse;
	private INeuron input;
	private INeuron output;
	
	@Before
	public void setUp() throws Exception {
		input = Mockito.mock(INeuron.class);
		output = Mockito.mock(INeuron.class);
		synapse = new CSynapse(input,output,0.4);
	}

	@Test
	public void initialize() {
		assertSame(input,synapse.getSourceNeuron());
		assertSame(output,synapse.getTargetNeuron());
		assertEquals(0,synapse.getWeight().compareTo(0.4));
		assertEquals(0,synapse.getInput().compareTo(0.0));

		synapse.setWeight(0.6);
		assertEquals(0,synapse.getWeight().compareTo(0.6));
		
		synapse.setInput(0.5);
		assertEquals(0,synapse.getInput().compareTo(0.5));
		
		Double result = synapse.calculate();
		assertEquals(0,result.compareTo(0.3));
	}

}
