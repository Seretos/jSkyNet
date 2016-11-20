package test.net.sky.network.type;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.sky.network.CLayer;
import net.sky.network.CNeuron;
import net.sky.network.CSynapse;
import net.sky.network.CTrainingSet;
import net.sky.network.INeuron;
import net.sky.network.neuron.CBinaryNeuron;
import net.sky.network.type.CBackpropagationNetwork;

public class CBackpropagationNetworkIntegrationTest {
	private CBackpropagationNetwork network;

	@Before
	public void setUp() throws Exception {
		network = new CBackpropagationNetwork(0.1f, 0.01f);
	}

	@Test
	public void OR_network() {
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron output = new CBinaryNeuron(1.0f);
		CLayer inputLayer = new CLayer();
		CLayer outputLayer = new CLayer();
		CTrainingSet t1 = new CTrainingSet();
		CTrainingSet t2 = new CTrainingSet();
		CTrainingSet t3 = new CTrainingSet();
		List<CTrainingSet> sets = new ArrayList<CTrainingSet>();

		CSynapse syn1 = new CSynapse(input1, output, 0.1f);
		CSynapse syn2 = new CSynapse(input2, output, 0.1f);

		network.addSynapse(syn1);
		network.addSynapse(syn2);

		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		outputLayer.addNeuron(output);

		network.setInputLayer(inputLayer);
		network.setOutputLayer(outputLayer);

		t1.addInput(1.0f).addInput(0.0f).addOutput(1.0f);
		t2.addInput(0.0f).addInput(1.0f).addOutput(1.0f);
		t3.addInput(1.0f).addInput(1.0f).addOutput(1.0f);

		sets.add(t1);
		sets.add(t2);
		sets.add(t3);

		for (int i = 0; i < 100; i++) {
			network.train(sets, 1);
		}

		input1.calculate(1.0f);
		input2.calculate(0.0f);
		network.run(1);
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(1.0f);
		network.run(1);
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(1.0f);
		input2.calculate(1.0f);
		network.run(1);
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(0.0f);
		network.run(1);
		assertEquals(0, Math.round(output.getValue()));
	}

	@Test
	public void AND_network() {
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron output = new CNeuron();
		CLayer inputLayer = new CLayer();
		CLayer outputLayer = new CLayer();
		CTrainingSet t1 = new CTrainingSet();
		CTrainingSet t2 = new CTrainingSet();
		CTrainingSet t3 = new CTrainingSet();
		List<CTrainingSet> sets = new ArrayList<CTrainingSet>();

		CSynapse syn1 = new CSynapse(input1, output, 0.1f);
		CSynapse syn2 = new CSynapse(input2, output, 0.1f);

		output.setThreshold(1.0f);

		network.addSynapse(syn1);
		network.addSynapse(syn2);

		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		outputLayer.addNeuron(output);

		network.setInputLayer(inputLayer);
		network.setOutputLayer(outputLayer);

		t1.addInput(1.0f).addInput(0.0f).addOutput(0.0f);
		t2.addInput(0.0f).addInput(1.0f).addOutput(0.0f);
		t3.addInput(1.0f).addInput(1.0f).addOutput(1.0f);

		sets.add(t1);
		sets.add(t2);
		sets.add(t3);

		for (int i = 0; i < 100; i++) {
			network.train(sets, 1);
		}

		input1.calculate(1.0f);
		input2.calculate(0.0f);
		network.run(1);
		assertEquals(0, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(1.0f);
		network.run(1);
		assertEquals(0, Math.round(output.getValue()));

		input1.calculate(1.0f);
		input2.calculate(1.0f);
		network.run(1);
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(0.0f);
		network.run(1);
		assertEquals(0, Math.round(output.getValue()));
	}

	@Test
	public void XOR_network() {
		network = new CBackpropagationNetwork(0.1f, 0.3f);
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron hidden1 = new CNeuron();
		INeuron hidden2 = new CNeuron();
		INeuron hidden3 = new CNeuron();
		INeuron output = new CNeuron();
		CLayer inputLayer = new CLayer();
		CLayer hiddenLayer = new CLayer();
		CLayer outputLayer = new CLayer();
		CTrainingSet t1 = new CTrainingSet();
		CTrainingSet t2 = new CTrainingSet();
		CTrainingSet t3 = new CTrainingSet();
		List<CTrainingSet> sets = new ArrayList<CTrainingSet>();

		CSynapse syn1 = new CSynapse(input1, hidden1, 1.0f);
		CSynapse syn2 = new CSynapse(input1, hidden2, 0.5f);
		CSynapse syn3 = new CSynapse(input2, hidden2, 0.5f);
		CSynapse syn4 = new CSynapse(input2, hidden3, 1.0f);
		CSynapse syn5 = new CSynapse(hidden1, output, 1.0f);
		CSynapse syn6 = new CSynapse(hidden2, output, 0.1f);
		CSynapse syn7 = new CSynapse(hidden3, output, 1.0f);

		hidden2.setThreshold(1.0f);

		network.addSynapse(syn1);
		network.addSynapse(syn2);
		network.addSynapse(syn3);
		network.addSynapse(syn4);
		network.addSynapse(syn5);
		network.addSynapse(syn6);
		network.addSynapse(syn7);

		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		hiddenLayer.addNeuron(hidden1);
		hiddenLayer.addNeuron(hidden2);
		hiddenLayer.addNeuron(hidden3);
		outputLayer.addNeuron(output);

		network.setInputLayer(inputLayer);
		network.addLayer(hiddenLayer);
		network.setOutputLayer(outputLayer);

		t1.addInput(1.0f).addInput(1.0f).addOutput(0.0f);
		t2.addInput(1.0f).addInput(0.0f).addOutput(1.0f);
		t3.addInput(0.0f).addInput(1.0f).addOutput(1.0f);

		sets.add(t1);
		sets.add(t2);
		sets.add(t3);

		for (int i = 0; i < 100; i++) {
			network.train(sets, 2);
			/*
			 * System.out.println("--------------------------");
			 * System.out.println("syn1: " + syn1.getWeight());
			 * System.out.println("syn2: " + syn2.getWeight());
			 * System.out.println("syn3: " + syn3.getWeight());
			 * System.out.println("syn4: " + syn4.getWeight());
			 * System.out.println("syn5: " + syn5.getWeight());
			 * System.out.println("syn6: " + syn6.getWeight());
			 * System.out.println("syn7: " + syn7.getWeight());
			 */
		}
		input1.calculate(1.0f);
		input2.calculate(0.0f);
		network.run(2);
		// System.out.println("1.0 0.0 : " + output.getValue());
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(1.0f);
		network.run(2);
		// System.out.println("0.0 1.0 : " + output.getValue());
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(1.0f);
		input2.calculate(1.0f);
		network.run(2);
		// System.out.println("1.0 1.0 : " + output.getValue());
		assertEquals(0, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(0.0f);
		network.run(2);
		// System.out.println("0.0 0.0 : " + output.getValue());
		assertEquals(0, Math.round(output.getValue()));
	}

	@Test
	public void XOR_network2() {
		network = new CBackpropagationNetwork(0.1f, 0.3f);
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron hidden1 = new CNeuron();
		INeuron hidden2 = new CNeuron();
		INeuron hidden3 = new CNeuron();
		INeuron output = new CNeuron();
		CLayer inputLayer = new CLayer();
		CLayer hiddenLayer = new CLayer();
		CLayer outputLayer = new CLayer();
		CTrainingSet t1 = new CTrainingSet();
		CTrainingSet t2 = new CTrainingSet();
		CTrainingSet t3 = new CTrainingSet();
		CTrainingSet t4 = new CTrainingSet();
		CTrainingSet t5 = new CTrainingSet();
		CTrainingSet t6 = new CTrainingSet();
		List<CTrainingSet> sets = new ArrayList<CTrainingSet>();

		CSynapse syn1 = new CSynapse(input1, hidden1, 0.5f);
		CSynapse syn2 = new CSynapse(input1, hidden2, 0.1f);
		CSynapse syn3 = new CSynapse(input2, hidden2, 0.1f);
		CSynapse syn4 = new CSynapse(input2, hidden3, 0.5f);
		CSynapse syn5 = new CSynapse(hidden1, output, 1.0f);
		CSynapse syn6 = new CSynapse(hidden2, output, -2.0f);
		CSynapse syn7 = new CSynapse(hidden3, output, 1.0f);

		hidden2.setThreshold(1.0f);

		network.addSynapse(syn1);
		network.addSynapse(syn2);
		network.addSynapse(syn3);
		network.addSynapse(syn4);
		network.addSynapse(syn5);
		network.addSynapse(syn6);
		network.addSynapse(syn7);

		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		hiddenLayer.addNeuron(hidden1);
		hiddenLayer.addNeuron(hidden2);
		hiddenLayer.addNeuron(hidden3);
		outputLayer.addNeuron(output);

		network.setInputLayer(inputLayer);
		network.addLayer(hiddenLayer);
		network.setOutputLayer(outputLayer);

		t1.addInput(1.0f).addInput(1.0f).addOutput(0.0f);
		t2.addInput(1.0f).addInput(0.0f).addOutput(1.0f);
		t3.addInput(0.0f).addInput(1.0f).addOutput(1.0f);
		t4.addInput(0.5f).addInput(0.5f).addOutput(1.0f);
		t5.addInput(2.0f).addInput(0.0f).addOutput(0.0f);
		t6.addInput(0.0f).addInput(4.0f).addOutput(0.0f);

		sets.add(t1);
		sets.add(t2);
		sets.add(t3);
		sets.add(t4);
		sets.add(t5);
		// sets.add(t6);

		for (int i = 0; i < 100000; i++) {
			network.train(sets, 2);

			/*
			 * System.out.println("-------------------------");
			 * System.out.println("syn1: " + syn1.getWeight());
			 * System.out.println("syn2: " + syn2.getWeight());
			 * System.out.println("syn3: " + syn3.getWeight());
			 * System.out.println("syn4: " + syn4.getWeight());
			 * System.out.println("syn5: " + syn5.getWeight());
			 * System.out.println("syn6: " + syn6.getWeight());
			 * System.out.println("syn7: " + syn7.getWeight());
			 */

		}

		input1.calculate(1.0f);
		input2.calculate(0.0f);
		network.run(2);
		// System.out.println("1.0 0.0 : " + output.getValue());
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(1.0f);
		network.run(2);
		// System.out.println("0.0 1.0 : " + output.getValue());
		assertEquals(1, Math.round(output.getValue()));

		input1.calculate(1.0f);
		input2.calculate(1.0f);
		network.run(2);
		// System.out.println("1.0 1.0 : " + output.getValue());
		assertEquals(0, Math.round(output.getValue()));

		input1.calculate(0.0f);
		input2.calculate(0.0f);
		network.run(2);
		// System.out.println("0.0 0.0 : " + output.getValue());
		assertEquals(0, Math.round(output.getValue()));
	}
}
