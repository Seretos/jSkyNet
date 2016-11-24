jSkyNet
=======

a little java neuronal network test

Build
-----

you require maven and ant to build the package

```php
ant build
```

Install
-------

if you want to edit this package in eclipse run the following command to install the required jar-files for eclipse.

```php
ant install
```

Usage
-----

create a network

```java
import net.sky.network.CNetwork;
import net.sky.network.type.CHebbNetwork;
import net.sky.network.type.CBackpropagationNetwork;

/*create a static network*/
CNetwork network = new CNetwork();

/*create a hebb network. if a neuron activate another, the synapses weight
will be recalculated*/
CHebbNetwork network = new CHebbNetwork(0.1f);

/*create a a backpropagation network. if the output difference higher than the tolerance, the network will be recalculated with the backpropagation algorithmus*/
CBackpropagationNetwork network = new CBackpropagationNetwork(0.1f, 0.01f);
```

create a neuron

```java
import net.sky.network.CNeuron;
import net.sky.network.INeuron;
import net.sky.network.CLayer;

INeuron neuron = new CNeuron(); //create a neuron
CLayer layer = new CLayer();	//create a layer to capsule neurons

layer.addNeuron(neuron);		//add the neuron to the layer

network.setInputLayer(layer);	//add the layer as input layer to the network
network.setOutputLayer(layer);	//add the layer as output layer to the network
network.addLayer(layer);		//add the layer as hidden layer to the network
```

create a synapse

```java
import net.sky.network.CSynapse;

CSynapse synapse = new CSynapse(neuron1,neuron2,0.1f);	//create a synapse from neuron1 to neuron2 with an weight of 0.1f
network.addSynapse(synapse);							//add the synapse to the network
```

execute the network

```java
/*on hebb network every synapses will be recalculated on execute*/
network.execute(); 	//send the values from one neuron to the next ones
network.run(2);		//run the execute method 2 times
```

train a backpropagation network

```java
import net.sky.network.CTrainingSet;

//create your training sets
CTrainingSet t1 = new CTrainingSet();
CTrainingSet t2 = new CTrainingSet();
CTrainingSet t3 = new CTrainingSet();

t1.addInput(1.0f).addInput(0.0f).addOutput(1.0f);
t2.addInput(0.0f).addInput(1.0f).addOutput(1.0f);
t3.addInput(1.0f).addInput(1.0f).addOutput(1.0f);

//add the sets to an list
List<CTrainingSet> sets = new ArrayList<CTrainingSet>();
sets.add(t1);
sets.add(t2);
sets.add(t3);

//train the network with the training set. the second parameter is the step count from input to output neurons
network.train(sets,2);
```

save network to file

```java
CJSONConverter converter = new CJSONConverter(new CJSONDefaultNeuronConverter());
converter.addNeuronConverter(new CJSONBinaryNeuronConverter());
converter.addNeuronConverter(new CJSONUnsignedLinearNeuronConverter());
converter.addNeuronConverter(new CJSONLinearNeuronConverter());
converter.addNeuronConverter(new CJSONLogisticNeuronConverter());
converter.addNeuronConverter(new CJSONNormalizeNeuronConverter());
converter.addNeuronConverter(new CJSONTangentNeuronConverter());

JSONObject obj = converter.convertNetwork(network);

try (FileWriter file = new FileWriter("/path/to/file.ext")) {
	file.write(obj.toJSONString());
}
```

TODO:
-----

- network builder
- evolutionary algorithm
