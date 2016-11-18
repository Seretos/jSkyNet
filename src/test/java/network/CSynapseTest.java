package test.java.network;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.java.network.CSynapse;
import main.java.network.INeuron;

public class CSynapseTest {

	private CSynapse synapse;
	private INeuron input;
	private INeuron output;
	
	@Before
	public void setUp() throws Exception {
		input = Mockito.mock(INeuron.class);
		output = Mockito.mock(INeuron.class);
		synapse = new CSynapse(input,output,0.4f);
	}

	@Test
	public void initialize() {
		assertSame(input,synapse.getSourceNeuron());
		assertSame(output,synapse.getTargetNeuron());
		assertTrue(0.4f == synapse.getWeight());
		assertTrue(0.0f == synapse.getInput());

		synapse.setWeight(0.6f);
		assertTrue(0.6f == synapse.getWeight());
		
		synapse.setInput(0.5f);
		assertTrue(0.5f == synapse.getInput());
		
		assertTrue(0.3f == synapse.calculate());
	}

}
