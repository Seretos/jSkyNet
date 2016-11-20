package test.net.sky.network;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import net.sky.network.CLayer;
import net.sky.network.CNetwork;
import net.sky.network.CSynapse;
import net.sky.network.INeuron;

import static org.mockito.Mockito.*;
import java.util.List;

public class CNetworkTest {
	private CNetwork network;

	@Before
	public void setUp() throws Exception {
		network = new CNetwork();
	}

	@Test
	public void getLayers() {
		CLayer inputLayer = Mockito.mock(CLayer.class);
		CLayer hiddenLayer = Mockito.mock(CLayer.class);
		CLayer outputLayer = Mockito.mock(CLayer.class);
		network.addLayer(inputLayer);
		network.addLayer(hiddenLayer);
		network.addLayer(outputLayer);

		List<CLayer> layers = network.getLayers();

		assertEquals(3, layers.size());
		assertSame(inputLayer, layers.get(0));
		assertSame(hiddenLayer, layers.get(1));
		assertSame(outputLayer, layers.get(2));
	}

	@Test
	public void getSynapses() {
		CSynapse syn1 = Mockito.mock(CSynapse.class);
		INeuron syn1Source = Mockito.mock(INeuron.class);
		INeuron syn1Target = Mockito.mock(INeuron.class);
		CSynapse syn2 = Mockito.mock(CSynapse.class);
		INeuron syn2Source = Mockito.mock(INeuron.class);
		INeuron syn2Target = Mockito.mock(INeuron.class);

		when(syn1.getSourceNeuron()).thenReturn(syn1Source);
		when(syn1.getTargetNeuron()).thenReturn(syn1Target);

		network.addSynapse(syn1);

		verify(syn1Source).addOutputSynapse(Matchers.same(syn1));
		verify(syn1Target).addInputSynapse(Matchers.same(syn1));

		when(syn2.getSourceNeuron()).thenReturn(syn2Source);
		when(syn2.getTargetNeuron()).thenReturn(syn2Target);

		network.addSynapse(syn2);

		verify(syn2Source).addOutputSynapse(Matchers.same(syn2));
		verify(syn2Target).addInputSynapse(Matchers.same(syn2));

		List<CSynapse> synapses = network.getSynapses();

		assertEquals(2, synapses.size());
		assertSame(syn1, synapses.get(0));
		assertSame(syn2, synapses.get(1));
	}

	@Test
	public void execute() {
		CLayer inputLayer = Mockito.mock(CLayer.class);
		CLayer hiddenLayer = Mockito.mock(CLayer.class);
		CLayer outputLayer = Mockito.mock(CLayer.class);

		network.addLayer(inputLayer);
		network.addLayer(hiddenLayer);
		network.addLayer(outputLayer);

		network.execute();

		verify(inputLayer).request();
		verify(hiddenLayer).request();
		verify(outputLayer).request();

		verify(inputLayer).response();
		verify(hiddenLayer).response();
		verify(outputLayer).response();
	}

	@Test
	public void run() {
		CLayer inputLayer = Mockito.mock(CLayer.class);
		CLayer hiddenLayer = Mockito.mock(CLayer.class);
		CLayer outputLayer = Mockito.mock(CLayer.class);

		network.addLayer(inputLayer);
		network.addLayer(hiddenLayer);
		network.addLayer(outputLayer);

		network.run(1);

		verify(inputLayer).request();
		verify(hiddenLayer).request();
		verify(outputLayer).request();

		verify(inputLayer).response();
		verify(hiddenLayer).response();
		verify(outputLayer).response();
	}
}
