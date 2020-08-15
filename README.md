# Hotplate
###### LinkedRRSet Output
<img src="Hotplate/DSA-2019-A1-LinkedRRSet.png" height="400">

## Overview
The class Element represents a single unit that can be part of multiple elements forming a grid hotplate. Elements in the hotplate run in their own thread to heat and cool when temperatures are applied. All Element instances share a static field value called heatConstant, which is what the element temperature change will be incremented by. The class keeps track of which other elements it is horizontally or vertically connected to in a List called neighbours. A neighbouring element should be able to be added to the list via an addNeighbour method. The start method should start running an Element instance in its own individual thread. While running, each element should be able to compare the average temperatures of its neighbours (above, below, left and right) with its own temperature and adjusts its current temperature before sleeping for a small period of time. This should mean that if an individual element unit is clicked on the Hotplate (e.g. from a completely cold Hotplate, being clicked to heat it), the surrounding elements should also follow and eventually match the temperature of the element that was clicked.

###### Element
To test this a main method in a test class has been created, creating two Element objects. The first element temperature will be set at 300, and the second element will be set at 0. Using an appropriate heat constant, each element thread should be started and should periodically print out temperatures which will eventually reach an approximately equal temperature. 

<img src="Hotplate/DSA-2019-A1-Element.png" height="400">


###### HotplateGUI


<img src="Hotplate/DSA-2019-A1-HotplateGUI.png" height="400"> 
