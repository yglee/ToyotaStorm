Here you will find Storm Topologies relating to Toyota's Self Driving Car use cases. It is developed on top of Apache Storm's storm-starter examples. 

Storm is a distributed realtime computation system. Similar to how Hadoop provides a set of general primitives for doing batch processing, Storm provides a set of general primitives for doing realtime computation. Storm is simple, can be used with any programming language, [is used by many companies](http://storm-project.net/documentation/Powered-By.html), and is a lot of fun to use!

The [Rationale page](http://storm-project.net/documentation/Rationale.html) explains what Storm is and why it was built. [This presentation](http://vimeo.com/40972420) is also a good introduction to the project.

Storm has a website at [storm-project.net](http://storm-project.net). Follow [@stormprocessor](https://twitter.com/stormprocessor) on Twitter for updates on the project.


## Install Maven
http://maven.apache.org/


## Install Latest Version of Apache Storm
http://storm.apache.org/downloads.html


## How to run topologies with Maven
From the top level directory of storm project, run: 

$ mvn clean install -DskipTests=true

Then cd into the examples/storm-starter directory and run: 

$ mvn compile exec:java -Dstorm.topology=storm.starter.[Topology Name]

i.e. mvn compile exec:java -Dstorm.topology=storm.starter.CollaborativeNavigationTopology


## files added:
In "storm-starter/src/jvm/storm/starter"
  CollaborativeNavigationTopology.java
In "storm-core/src/jvm/backtype/storm/testing"
  TestDistanceSpout.java 
 

## License

Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.


## Team Members

* Hyo Jeong (hyo.jeong@sv.cmu.edu)
* Ambarish Karole (ambarish.karole@sv.cmu.edu)
* Grace Lee (grace.lee@sv.cmu.edu)
* AJ Ruiz (aj.ruiz@sv.cmu.edu)
* Harikumar Sulochana (harikumar.kumar.sulochana@sv.cmu.edu)

## Supervising Faculty

* Prof. Patrick Tague
* Prof. Jia Zhang

