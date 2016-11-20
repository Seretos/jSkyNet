package test.net.sky.network;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import net.sky.network.CNeuron;
import net.sky.network.CSynapse;

public class CNeuronTest {

	private CNeuron neuron;

	@Before
	public void setUp() throws Exception {
		neuron = new CNeuron();
	}

	@Test
	public void id() {
		CNeuron neuron = new CNeuron();

		assertEquals(-1, neuron.getId());

		neuron.setId(2);

		assertEquals(2, neuron.getId());
	}

	@Test
	public void initialize() {
		CSynapse inSyn1 = Mockito.mock(CSynapse.class);
		CSynapse inSyn2 = Mockito.mock(CSynapse.class);
		CSynapse outSyn1 = Mockito.mock(CSynapse.class);
		CSynapse outSyn2 = Mockito.mock(CSynapse.class);

		assertTrue(0.0f == neuron.getThreshold());
		assertTrue(0 == neuron.getValue());
		assertEquals(0, neuron.getInputSynapses().size());
		assertEquals(0, neuron.getOutputSynapses().size());

		neuron.setThreshold(0.4f);
		assertTrue(0.4f == neuron.getThreshold());

		neuron.setValue(0.6f);
		assertTrue(0.6f == neuron.getValue());

		neuron.addInputSynapse(inSyn1);
		neuron.addInputSynapse(inSyn2);
		neuron.addOutputSynapse(outSyn1);
		neuron.addOutputSynapse(outSyn2);
		assertEquals(2, neuron.getInputSynapses().size());
		assertEquals(2, neuron.getOutputSynapses().size());
	}

	@Test
	public void request() {
		CSynapse outSyn1 = Mockito.mock(CSynapse.class);
		CSynapse outSyn2 = Mockito.mock(CSynapse.class);

		neuron.addOutputSynapse(outSyn1);
		neuron.addOutputSynapse(outSyn2);

		neuron.setValue(0.6f);
		neuron.request();

		verify(outSyn1).setInput(Matchers.eq(0.6f));
		verify(outSyn2).setInput(Matchers.eq(0.6f));
		assertTrue(0 == neuron.getValue());
	}

	@Test
	public void response() {
		CSynapse inSyn1 = Mockito.mock(CSynapse.class);
		CSynapse inSyn2 = Mockito.mock(CSynapse.class);

		neuron.addInputSynapse(inSyn1);
		neuron.addInputSynapse(inSyn2);

		when(inSyn1.calculate()).thenReturn(0.4f, 0.0f);
		when(inSyn2.calculate()).thenReturn(0.5f, 0.0f);

		neuron.response();
		neuron.clear();

		verify(inSyn1).setInput(Matchers.eq(0.0f));
		verify(inSyn2).setInput(Matchers.eq(0.0f));

		neuron.response();
		assertTrue(0.0f == neuron.getValue());
	}

	@Test
	public void response_withThreshold() {
		CSynapse inSyn1 = Mockito.mock(CSynapse.class);
		CSynapse inSyn2 = Mockito.mock(CSynapse.class);

		neuron.addInputSynapse(inSyn1);
		neuron.addInputSynapse(inSyn2);

		neuron.setThreshold(0.95f);

		when(inSyn1.calculate()).thenReturn(0.4f);
		when(inSyn2.calculate()).thenReturn(0.5f);

		neuron.response();
		neuron.clear();

		verify(inSyn1).setInput(Matchers.eq(0.0f));
		verify(inSyn2).setInput(Matchers.eq(0.0f));

		assertTrue(0.0f == neuron.getValue());
	}

	@Test
	public void unsetThreshold() {
		neuron.setThreshold(0.5f);

		neuron.unsetThreshold();

		assertTrue(0.0f == neuron.getThreshold());
	}

	@Test
	public void calculate() {
		neuron.calculate(0.1f);
		assertTrue(0.1f == neuron.getValue());

		neuron.calculate(0.3f);
		assertTrue(0.3f == neuron.getValue());
	}
}
