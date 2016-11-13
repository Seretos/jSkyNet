package tests.network;

import static org.junit.Assert.*;

import org.junit.Test;

import network.CLayer;
import network.CNeuron;
import network.CSynapse;
import network.INeuron;
import network.neuron.CBinaryNeuron;

public class CNetworkIntegrationTest {

	@Test
	public void XOR_network(){
		INeuron input1 = new CBinaryNeuron(1.0f);
		INeuron input2 = new CBinaryNeuron(1.0f);
		INeuron hidden1 = new CNeuron();
		INeuron hidden2 = new CBinaryNeuron(1.0f);
		INeuron hidden3 = new CNeuron();
		INeuron output = new CBinaryNeuron(1.0f);
		
		CSynapse syn1 = new CSynapse(input1,hidden1,1.0f);
		CSynapse syn2 = new CSynapse(input1,hidden2,0.5f);
		CSynapse syn3 = new CSynapse(input2,hidden2,0.5f);
		CSynapse syn4 = new CSynapse(input2,hidden3,1.0f);
		CSynapse syn5 = new CSynapse(hidden1,output,1.0f);
		CSynapse syn6 = new CSynapse(hidden2,output,-2.0f);
		CSynapse syn7 = new CSynapse(hidden3,output,1.0f);
		
		input1.addOutputSynapse(syn1);
		input1.addOutputSynapse(syn2);
		input2.addOutputSynapse(syn3);
		input2.addOutputSynapse(syn4);
		
		hidden1.addOutputSynapse(syn5);
		hidden2.addOutputSynapse(syn6);
		hidden3.addOutputSynapse(syn7);
		
		hidden1.addInputSynapse(syn1);
		hidden2.addInputSynapse(syn2);
		hidden2.addInputSynapse(syn3);
		hidden3.addInputSynapse(syn4);
		
		output.addInputSynapse(syn5);
		output.addInputSynapse(syn6);
		output.addInputSynapse(syn7);

		input1.calculate(1.0f);
		input2.calculate(1.0f);

		input1.request();
		input2.request();
		hidden1.response();
		hidden2.response();
		hidden3.response();
		hidden1.request();
		hidden2.request();
		hidden3.request();
		output.response();
		
		assertTrue(0.0f == output.getValue());
		
		input1.calculate(1.0f);
		input2.calculate(0.0f);

		input1.request();
		input2.request();
		hidden1.response();
		hidden2.response();
		hidden3.response();
		hidden1.request();
		hidden2.request();
		hidden3.request();
		output.response();
		
		assertTrue(1.0f == output.getValue());
		
		input1.calculate(0.0f);
		input2.calculate(1.0f);

		input1.request();
		input2.request();
		hidden1.response();
		hidden2.response();
		hidden3.response();
		hidden1.request();
		hidden2.request();
		hidden3.request();
		output.response();
		
		assertTrue(1.0f == output.getValue());
	}
	
	@Test
	public void OR_network(){
		INeuron input1 = new CBinaryNeuron(1.0f);
		INeuron input2 = new CBinaryNeuron(1.0f);
		INeuron output = new CBinaryNeuron(1.0f);
		
		CSynapse syn1 = new CSynapse(input1,output,1.0f);
		CSynapse syn2 = new CSynapse(input2,output,1.0f);
		
		input1.addOutputSynapse(syn1);
		input2.addOutputSynapse(syn2);
		output.addInputSynapse(syn1);
		output.addInputSynapse(syn2);
		
		input1.calculate(0.5f);
		input2.calculate(0.5f);
		
		input1.request();
		input2.request();
		output.response();
		
		assertTrue(0.0f == output.getValue());
		
		input1.calculate(1.0f);
		input2.calculate(0.0f);
		
		input1.request();
		input2.request();
		output.response();
		
		assertTrue(1.0f == output.getValue());
		
		input1.calculate(0.0f);
		input2.calculate(1.0f);
		
		input1.request();
		input2.request();
		output.response();
		
		assertTrue(1.0f == output.getValue());
		
		input1.calculate(1.0f);
		input2.calculate(1.0f);
		
		input1.request();
		input2.request();
		output.response();
		
		assertTrue(1.0f == output.getValue());
	}
	
	@Test
	public void AND_network(){
		INeuron input1 = new CBinaryNeuron(1.0f);
		INeuron input2 = new CBinaryNeuron(1.0f);
		INeuron output = new CBinaryNeuron(1.0f);
		
		CSynapse syn1 = new CSynapse(input1,output,0.5f);
		CSynapse syn2 = new CSynapse(input2,output,0.5f);
		
		input1.addOutputSynapse(syn1);
		input2.addOutputSynapse(syn2);
		output.addInputSynapse(syn1);
		output.addInputSynapse(syn2);
		
		input1.calculate(0.5f);
		input2.calculate(0.5f);
		
		input1.request();
		input2.request();
		output.response();
		
		assertTrue(0.0f == output.getValue());
		
		input1.calculate(1.0f);
		input2.calculate(1.0f);
		
		input1.request();
		input2.request();
		output.response();
		
		assertTrue(1.0f == output.getValue());
		
		input1.calculate(1.0f);
		input2.calculate(0.5f);
		
		input1.request();
		input2.request();
		output.response();
		
		assertTrue(0.0f == output.getValue());
		
		input1.calculate(0.0f);
		input2.calculate(0.0f);
		
		input1.request();
		input2.request();
		output.response();

		assertTrue(0.0f == output.getValue());
		
		input1.calculate(5.0f);
		input2.calculate(0.0f);
		
		input1.request();
		input2.request();
		output.response();

		assertTrue(0.0f == output.getValue());
	}
	
	@Test
	public void simple_network(){
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron output = new CNeuron();
		CSynapse syn1 = new CSynapse(input1,output,1.0f);
		CSynapse syn2 = new CSynapse(input2,output,1.0f);
		
		input1.setValue(0.5f);
		input2.setValue(0.6f);
		
		input1.addOutputSynapse(syn1);
		input2.addOutputSynapse(syn2);
		output.addInputSynapse(syn1);
		output.addInputSynapse(syn2);
		
		assertTrue(0.5f == input1.getValue());
		assertTrue(0.6f == input2.getValue());
		assertTrue(0.0f == output.getValue());
		assertTrue(0.0f == syn1.getInput());
		assertTrue(0.0f == syn2.getInput());
		
		input1.request();
		input2.request();
		output.request();
		
		assertTrue(0.0f == input1.getValue());
		assertTrue(0.0f == input2.getValue());
		assertTrue(0.0f == output.getValue());
		assertTrue(0.5f == syn1.getInput());
		assertTrue(0.6f == syn2.getInput());
		
		input1.response();
		input2.response();
		output.response();
		
		assertTrue(0.0f == input1.getValue());
		assertTrue(0.0f == input2.getValue());
		assertTrue(1.1f == output.getValue());
		
		output.clear();
		assertTrue(0.0f == syn1.getInput());
		assertTrue(0.0f == syn2.getInput());
	}
	
	@Test
	public void simple_network_widthWeight(){
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron output = new CNeuron();
		CSynapse syn1 = new CSynapse(input1,output,0.5f);
		CSynapse syn2 = new CSynapse(input2,output,0.6f);
		
		input1.setValue(0.5f);
		input2.setValue(0.6f);
		
		input1.addOutputSynapse(syn1);
		input2.addOutputSynapse(syn2);
		output.addInputSynapse(syn1);
		output.addInputSynapse(syn2);
		
		assertTrue(0.5f == input1.getValue());
		assertTrue(0.6f == input2.getValue());
		assertTrue(0.0f == output.getValue());
		assertTrue(0.0f == syn1.getInput());
		assertTrue(0.0f == syn2.getInput());		
		input1.request();
		input2.request();
		output.request();
		
		assertTrue(0.0f == input1.getValue());
		assertTrue(0.0f == input2.getValue());
		assertTrue(0.0f == output.getValue());
		assertTrue(0.5f == syn1.getInput());
		assertTrue(0.6f == syn2.getInput());
		
		input1.response();
		input2.response();
		output.response();
		
		assertTrue(0.0f == input1.getValue());
		assertTrue(0.0f == input2.getValue());
		assertTrue(0.61f == output.getValue());
		
		output.clear();
		
		assertTrue(0.0f == syn1.getInput());
		assertTrue(0.0f == syn2.getInput());
	}
	
	@Test
	public void simple_layer_network(){
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron hidden1 = new CNeuron();
		INeuron hidden2 = new CNeuron();
		INeuron output = new CNeuron();
		
		CSynapse syn1 = new CSynapse(input1,hidden1,1.0f);
		CSynapse syn2 = new CSynapse(input2,hidden2,1.0f);
		CSynapse syn3 = new CSynapse(hidden1,output,0.5f);
		CSynapse syn4 = new CSynapse(hidden2,output,0.5f);
		
		CLayer inputLayer = new CLayer();
		CLayer hiddenLayer = new CLayer();
		CLayer outputLayer = new CLayer();
		
		input1.addOutputSynapse(syn1);
		input2.addOutputSynapse(syn2);
		hidden1.addInputSynapse(syn1);
		hidden2.addInputSynapse(syn2);
		hidden1.addOutputSynapse(syn3);
		hidden2.addOutputSynapse(syn4);
		output.addInputSynapse(syn3);
		output.addInputSynapse(syn4);
		
		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		hiddenLayer.addNeuron(hidden1);
		hiddenLayer.addNeuron(hidden2);
		outputLayer.addNeuron(output);
		
		input1.setValue(1.0f);
		input2.setValue(2.0f);
		
		inputLayer.request();
		hiddenLayer.response();
		
		assertTrue(0.0f == input1.getValue());
		assertTrue(0.0f == input2.getValue());
		assertTrue(1.0f == hidden1.getValue());
		assertTrue(2.0f == hidden2.getValue());
		
		hiddenLayer.request();
		outputLayer.response();
		
		assertTrue(0.0f == hidden1.getValue());
		assertTrue(0.0f == hidden2.getValue());
		assertTrue(1.5f == output.getValue());
	}
}
