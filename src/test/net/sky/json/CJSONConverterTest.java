package test.net.sky.json;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Test;

import net.sky.json.CJSONBinaryNeuronConverter;
import net.sky.json.CJSONConverter;
import net.sky.json.CJSONDefaultNeuronConverter;
import net.sky.json.CJSONLinearNeuronConverter;
import net.sky.json.CJSONLogisticNeuronConverter;
import net.sky.json.CJSONNormalizeNeuronConverter;
import net.sky.json.CJSONTangentNeuronConverter;
import net.sky.json.CJSONUnsignedLinearNeuronConverter;
import net.sky.network.CLayer;
import net.sky.network.CNetwork;
import net.sky.network.CNeuron;
import net.sky.network.CSynapse;
import net.sky.network.INeuron;
import net.sky.network.neuron.CBinaryNeuron;
import net.sky.network.neuron.CLinearNeuron;
import net.sky.network.neuron.CLogisticNeuron;
import net.sky.network.neuron.CNormalizeNeuron;
import net.sky.network.neuron.CTangentNeuron;
import net.sky.network.neuron.CUnsignedLinearNeuron;

public class CJSONConverterTest {

	@Test
	public void integrationTest() {
		CNetwork myNet = new CNetwork();
		CLayer inputLayer = new CLayer();
		CLayer hiddenLayer = new CLayer();
		CLayer outputLayer = new CLayer();
		INeuron input1 = new CNeuron();
		INeuron input2 = new CBinaryNeuron(1.0f);
		CLinearNeuron input3 = new CLinearNeuron();
		INeuron hidden1 = new CLogisticNeuron();
		INeuron hidden2 = new CNormalizeNeuron();
		INeuron hidden3 = new CTangentNeuron();
		INeuron output = new CUnsignedLinearNeuron();
		
		input3.setGradient(1.2f);
		input3.setIntersection(3.9f);
		
		CSynapse syn1 = new CSynapse(input1,hidden1,0.1f);
		CSynapse syn2 = new CSynapse(input1,hidden2,0.2f);
		CSynapse syn3 = new CSynapse(input1,hidden3,0.3f);
		
		CSynapse syn4 = new CSynapse(input2,hidden1,0.11f);
		CSynapse syn5 = new CSynapse(input2,hidden2,0.21f);
		CSynapse syn6 = new CSynapse(input2,hidden3,0.31f);
		
		CSynapse syn7 = new CSynapse(input3,hidden1,0.121f);
		CSynapse syn8 = new CSynapse(input3,hidden2,0.221f);
		CSynapse syn9 = new CSynapse(input3,hidden3,0.321f);
		
		CSynapse syn10 = new CSynapse(hidden1,output,0.1231f);
		CSynapse syn11 = new CSynapse(hidden2,output,0.2231f);
		CSynapse syn12 = new CSynapse(hidden3,output,0.3231f);
		
		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		inputLayer.addNeuron(input3);
		
		hiddenLayer.addNeuron(hidden1);
		hiddenLayer.addNeuron(hidden2);
		hiddenLayer.addNeuron(hidden3);
		
		outputLayer.addNeuron(output);
		
		myNet.addSynapse(syn1);
		myNet.addSynapse(syn2);
		myNet.addSynapse(syn3);
		myNet.addSynapse(syn4);
		myNet.addSynapse(syn5);
		myNet.addSynapse(syn6);
		myNet.addSynapse(syn7);
		myNet.addSynapse(syn8);
		myNet.addSynapse(syn9);
		myNet.addSynapse(syn10);
		myNet.addSynapse(syn11);
		myNet.addSynapse(syn12);
		
		myNet.setInputLayer(inputLayer);
		myNet.addLayer(hiddenLayer);
		myNet.setOutputLayer(outputLayer);
		
		CJSONConverter converter = new CJSONConverter(new CJSONDefaultNeuronConverter());
		converter.addNeuronConverter(new CJSONBinaryNeuronConverter());
		converter.addNeuronConverter(new CJSONUnsignedLinearNeuronConverter());
		converter.addNeuronConverter(new CJSONLinearNeuronConverter());
		converter.addNeuronConverter(new CJSONLogisticNeuronConverter());
		converter.addNeuronConverter(new CJSONNormalizeNeuronConverter());
		converter.addNeuronConverter(new CJSONTangentNeuronConverter());
		
		JSONObject json = converter.convertNetwork(myNet);
		//System.out.println(json.toJSONString());
		
		CNetwork loadNet = new CNetwork();
		converter.convertJSONObject(json, loadNet);

		assertEquals(3,loadNet.getLayers().size());
		assertEquals(3,loadNet.getInputLayer().getNeurons().size());
		assertEquals(3,loadNet.getLayer(1).getNeurons().size());
		assertEquals(1,loadNet.getOutputLayer().getNeurons().size());
		assertEquals(12,loadNet.getSynapses().size());
		
		assertTrue(loadNet.getInputLayer().getNeuron(0) instanceof CNeuron);
		assertTrue(loadNet.getInputLayer().getNeuron(0) != input1);

		assertTrue(loadNet.getInputLayer().getNeuron(1) instanceof CBinaryNeuron);
		assertTrue(loadNet.getInputLayer().getNeuron(1).isThreshold());
		assertTrue(loadNet.getInputLayer().getNeuron(1).getThreshold() == 1.0f);
		assertTrue(loadNet.getInputLayer().getNeuron(1) != input2);
		
		assertTrue(loadNet.getInputLayer().getNeuron(2) instanceof CLinearNeuron);
		assertTrue(loadNet.getInputLayer().getNeuron(2) != input3);
		CLinearNeuron loadInput3 = (CLinearNeuron)loadNet.getInputLayer().getNeuron(2);
		assertTrue(loadInput3.getGradient() == 1.2f);
		assertTrue(loadInput3.getIntersection() == 3.9f);
		
		assertTrue(loadNet.getLayer(1).getNeuron(0) instanceof CLogisticNeuron);
		assertTrue(loadNet.getLayer(1).getNeuron(0) != hidden1);
		
		assertTrue(loadNet.getLayer(1).getNeuron(1) instanceof CNormalizeNeuron);
		assertTrue(loadNet.getLayer(1).getNeuron(1) != hidden2);
		
		assertTrue(loadNet.getLayer(1).getNeuron(2) instanceof CTangentNeuron);
		assertTrue(loadNet.getLayer(1).getNeuron(2) != hidden3);
		
		assertTrue(loadNet.getOutputLayer().getNeuron(0) instanceof CUnsignedLinearNeuron);
		assertTrue(loadNet.getOutputLayer().getNeuron(0) != output);
		
		assertSame(loadNet.getLayer(1).getNeuron(0),loadNet.getInputLayer().getNeuron(0).getOutputSynapses().get(0).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(0).getOutputSynapses().get(0).getWeight() == 0.1f);
		
		assertSame(loadNet.getLayer(1).getNeuron(1),loadNet.getInputLayer().getNeuron(0).getOutputSynapses().get(1).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(0).getOutputSynapses().get(1).getWeight() == 0.2f);
		
		assertSame(loadNet.getLayer(1).getNeuron(2),loadNet.getInputLayer().getNeuron(0).getOutputSynapses().get(2).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(0).getOutputSynapses().get(2).getWeight() == 0.3f);
		
		assertSame(loadNet.getLayer(1).getNeuron(0),loadNet.getInputLayer().getNeuron(1).getOutputSynapses().get(0).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(1).getOutputSynapses().get(0).getWeight() == 0.11f);
		
		assertSame(loadNet.getLayer(1).getNeuron(1),loadNet.getInputLayer().getNeuron(1).getOutputSynapses().get(1).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(1).getOutputSynapses().get(1).getWeight() == 0.21f);
		
		assertSame(loadNet.getLayer(1).getNeuron(2),loadNet.getInputLayer().getNeuron(1).getOutputSynapses().get(2).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(1).getOutputSynapses().get(2).getWeight() == 0.31f);
		
		assertSame(loadNet.getLayer(1).getNeuron(0),loadNet.getInputLayer().getNeuron(2).getOutputSynapses().get(0).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(2).getOutputSynapses().get(0).getWeight() == 0.121f);
		
		assertSame(loadNet.getLayer(1).getNeuron(1),loadNet.getInputLayer().getNeuron(2).getOutputSynapses().get(1).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(2).getOutputSynapses().get(1).getWeight() == 0.221f);
		
		assertSame(loadNet.getLayer(1).getNeuron(2),loadNet.getInputLayer().getNeuron(2).getOutputSynapses().get(2).getTargetNeuron());
		assertTrue(loadNet.getInputLayer().getNeuron(2).getOutputSynapses().get(2).getWeight() == 0.321f);
		
		assertSame(loadNet.getOutputLayer().getNeuron(0),loadNet.getLayer(1).getNeuron(0).getOutputSynapses().get(0).getTargetNeuron());
		assertTrue(loadNet.getLayer(1).getNeuron(0).getOutputSynapses().get(0).getWeight() == 0.1231f);
		
		assertSame(loadNet.getOutputLayer().getNeuron(0),loadNet.getLayer(1).getNeuron(1).getOutputSynapses().get(0).getTargetNeuron());
		assertTrue(loadNet.getLayer(1).getNeuron(1).getOutputSynapses().get(0).getWeight() == 0.2231f);
		
		assertSame(loadNet.getOutputLayer().getNeuron(0),loadNet.getLayer(1).getNeuron(2).getOutputSynapses().get(0).getTargetNeuron());
		assertTrue(loadNet.getLayer(1).getNeuron(2).getOutputSynapses().get(0).getWeight() == 0.3231f);
	}

}
