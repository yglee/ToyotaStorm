/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package storm.starter;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.testing.TestDistanceSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.util.Map;

/**
 * This is a basic example of a Storm topology.
 */
public class CollaborativeNavigationTopology {

  public static class CollaborativeNavigationBolt extends BaseRichBolt {
    OutputCollector _collector;

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
      _collector = collector;
    }

    @Override
    public void execute(Tuple tuple) {
      String distance = tuple.getString(0);
      // if there are cars that are less than 2.0 meters away from me, do something
      if (Float.parseFloat(distance) < 2.0) {
    	  // Ideally in here, I can tell my autonomous car to factor in the fact that there are 
    	  // cars nearby. 
    	  
    	  // Stream a tuple of "do not merge" message for every car that is less than 2 meters away from me.
    	  Values carNearByMessage = new Values("Danger! Do not Merge -- There is a car nearby.");
    	  System.out.println("Detected that there is a car less than two meters away with id: " +tuple.getSourceComponent());
    	  _collector.emit(tuple, carNearByMessage);
      }
      _collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields("word"));
    }


  }

  public static void main(String[] args) throws Exception {
    TopologyBuilder builder = new TopologyBuilder();
    //neighboring car 1
    builder.setSpout("neighboringCar1", new TestDistanceSpout(), 10);
    builder.setSpout("neighboringCar2", new TestDistanceSpout(), 10);
    builder.setSpout("neighboringCar3", new TestDistanceSpout(), 10);
    //myCar bolt takes in input streams from neighboringCar1, neighboringCar2, and neighboringCar3
    builder.setBolt("myCar", new CollaborativeNavigationBolt(), 3).shuffleGrouping("neighboringCar1").shuffleGrouping("neighboringCar3").shuffleGrouping("neighboringCar3");

    Config conf = new Config();
    conf.setDebug(true);

    if (args != null && args.length > 0) {
      conf.setNumWorkers(3);
      StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
    }
    else {
      LocalCluster cluster = new LocalCluster();
      cluster.submitTopology("test", conf, builder.createTopology());
      Utils.sleep(10000);
      cluster.killTopology("test");
      cluster.shutdown();
    }
  }
}
