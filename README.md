# ps-assignment
## Problem Statement
Our sales team has just struck a deal with Acme Inc to become the exclusive provider for routing their product shipments via 3rd party trucking fleets. The catch is that we can only route one shipment to one driver per day.
Each day we get the list of shipment destinations that are available for us to offer to drivers in our network. Fortunately our team of highly trained data scientists have developed a mathematical model for determining which drivers are best suited to deliver each shipment.
With that hard work done, now all we have to do is implement a program that assigns each shipment destination to a given driver while maximizing the total suitability of all shipments to all drivers.

The top-secret algorithm is:
- If the length of the shipment's destination street name is even, the base suitability score (SS) is the number of vowels in the driver’s name multiplied by 1.5.
- If the length of the shipment's destination street name is odd, the base SS is the number of consonants in the driver’s name multiplied by 1.
 If the length of the shipment's destination street name shares any common factors (besides 1) with the length of the driver’s name, the SS is increased by 50% above the base SS.
 
Write an application in the language of your choice that assigns shipment destinations to drivers in a way that maximizes the total SS over the set of drivers. Each driver can only have one shipment and each shipment can only be offered to one driver. Your program should run on the command line and take as input two newline separated files, the first containing the street addresses of the shipment destinations and the second containing the names of the drivers. The output should be the total SS and a matching between shipment destinations and drivers. You do not need to worry about malformed input, but you should certainly handle both upper and lower case names.

## Approach
Core problem in the question is the [Assignment Problem](https://en.wikipedia.org/wiki/Assignment_problem). In general, assignment problem involves finding the combinations resulting in minimum sum. But in the current problem, we are trying to find the combinations that maximizes the Suitability score. To achieve this, I approached the problem this way

- Creating the grid with Suitability Scores for each shipping destination and for all drivers in nxn matrix form
- Now by subtracting the maximum value in the grid from every value in the grid and keeping the absolute remaining value, our problem can be converted to a minimization problem instead of maximization problem. 
- By applying the popular algorithm, [Hungarian Algorithm](https://en.wikipedia.org/wiki/Hungarian_algorithm), for Assignment problem we an get the drivers mapped to shipping destinations in the best possible manner


## Requirements to run this service locally
On Mac
- jenv ( brew install jenv )
- java18 ( brew install openjdk ) ( using [jenv](https://www.jenv.be/) is ideal, so that your workspaces wouldn't be disrupted )
- jenv add jenv add /Library/Java/JavaVirtualMachines/jdk-18.0.1.1.jdk/Contents/Home
- maven (brew install maven )

### Instructions

- git clone git@github.com:hsure/ps-assignment.git
- cd ps-assignment
- jenv local 18.0
- mvn package (builds the jar)
- java -jar target/DriverShipments-0.0.1-SNAPSHOT.jar **destinations-file-path** **drivers-file-path**

Note: provide destinations file and drivers file while running the last command

### Test results

![Sample test results](images/TestResults.png)

### Coverage report

Have covered only the happy paths
![Coverage report](images/Coverage.png)

### Thanks to

[KevinStern](https://github.com/KevinStern) for the [Hungarian Algorithm Implementation](https://github.com/KevinStern/software-and-algorithms/blob/master/src/main/java/blogspot/software_and_algorithms/stern_library/optimization/HungarianAlgorithm.java)
