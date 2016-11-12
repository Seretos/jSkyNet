package tests.network;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import network.CNeuron;
import network.CSynapse;

public class CNeuronTest {

	private CNeuron neuron;
	@Before
	public void setUp() throws Exception {
		neuron = new CNeuron();
	}

	@Test
	public void initialize() {
		CSynapse inSyn1 = Mockito.mock(CSynapse.class);
		CSynapse inSyn2 = Mockito.mock(CSynapse.class);
		CSynapse outSyn1 = Mockito.mock(CSynapse.class);
		CSynapse outSyn2 = Mockito.mock(CSynapse.class);
		
		assertEquals(0,neuron.getThreshold().compareTo(0.0));
		assertEquals(0,neuron.getValue().compareTo(0.0));
		assertEquals(0,neuron.getInputSynapses().size());
		assertEquals(0,neuron.getOutputSynapses().size());
		
		neuron.setThreshold(0.4);
		assertEquals(0,neuron.getThreshold().compareTo(0.4));
		
		neuron.setValue(0.6);
		assertEquals(0,neuron.getValue().compareTo(0.6));
		
		neuron.addInputSynapse(inSyn1);
		neuron.addInputSynapse(inSyn2);
		neuron.addOutputSynapse(outSyn1);
		neuron.addOutputSynapse(outSyn2);
		assertEquals(2,neuron.getInputSynapses().size());
		assertEquals(2,neuron.getOutputSynapses().size());
	}
	
	@Test
	public void request(){
		CSynapse outSyn1 = Mockito.mock(CSynapse.class);
		CSynapse outSyn2 = Mockito.mock(CSynapse.class);
		
		neuron.addOutputSynapse(outSyn1);
		neuron.addOutputSynapse(outSyn2);
		
		neuron.setValue(0.6);
		neuron.request();
		
		verify(outSyn1).setInput(Matchers.eq(0.6));
		verify(outSyn2).setInput(Matchers.eq(0.6));
		assertEquals(0,neuron.getValue().compareTo(0.0));
	}
	
	@Test
	public void response(){
		CSynapse inSyn1 = Mockito.mock(CSynapse.class);
		CSynapse inSyn2 = Mockito.mock(CSynapse.class);

		neuron.addInputSynapse(inSyn1);
		neuron.addInputSynapse(inSyn2);
		
		when(inSyn1.calculate()).thenReturn(0.4);
		when(inSyn2.calculate()).thenReturn(0.5);
		
		neuron.response();
		neuron.clear();
		
		verify(inSyn1).setInput(Matchers.eq(0.0));
		verify(inSyn2).setInput(Matchers.eq(0.0));
		
		assertEquals(0,neuron.getValue().compareTo(0.9));
	}
	
	@Test
	public void response_withThreshold(){
		CSynapse inSyn1 = Mockito.mock(CSynapse.class);
		CSynapse inSyn2 = Mockito.mock(CSynapse.class);

		neuron.addInputSynapse(inSyn1);
		neuron.addInputSynapse(inSyn2);
		
		neuron.setThreshold(0.95);
		
		when(inSyn1.calculate()).thenReturn(0.4);
		when(inSyn2.calculate()).thenReturn(0.5);
		
		neuron.response();
		neuron.clear();
				
		verify(inSyn1).setInput(Matchers.eq(0.0));
		verify(inSyn2).setInput(Matchers.eq(0.0));
		
		assertEquals(0,neuron.getValue().compareTo(0.0));
	}

	@Test
	public void calculate(){
		neuron.calculate(0.1);
		assertEquals(0,neuron.getValue().compareTo(0.1));

		neuron.calculate(0.3);
		assertEquals(0,neuron.getValue().compareTo(0.3));
		
		neuron.setThreshold(0.4);
		neuron.calculate(0.3);
		assertEquals(0,neuron.getValue().compareTo(0.3));
	}
}
