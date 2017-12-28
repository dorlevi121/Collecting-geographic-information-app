# Collecting-geographic-information-app
## CSV to KML converter
## location detection

This program collects all the csv files from WigleWifi in a specified folder and creates a kml file after filtering the data.

## Contributors

Yarden Mizrahi - 311332183
Dor Levi - 203288139

## Getting Started

Simply run the program through eclipse and follow the instructions (and syntax!).

### Example (filtering by time)
```
Select a folder to scan for csv files: 
>C:\\Users\\dorle\\Desktop\\New folder\\27.10
Enter path to write the CSV file (with the file's name): 
>C:\\Users\\dorle\\Desktop\\Test\\merged csv file.csv
Fetching data from:
...
Created merged csv file.csv successfuly
Create a KML file Sorted by (1)Time, (2)GPS, (3)ID: 
>1
Filter by time syntax:
Start time: year(xxxx):month(xx):day(xx):hr(xx):min(xx):sec(xx) 
End time: year(xxxx):month(xx):day(xx):hr(xx):min(xx):sec(xx)
Enter path to write the KML file (with the file's name): 
>C:\\Users\\Dan\\Desktop\\New folder\\Time filtered.kml
Start time: 
>2017:11:12:13:06:42
End time: 
>2017:11:12:13:09:42
Success!
```

## Functions
### CSV:
• makeCSV: unifies the CSV files and prints them into an ArrayList

### Wifi:
•	WiFi: Make object wifi. Constructor (signal, frequency, id, mac)

•	getSingal: Returns the signal strength of the specified Wifi network.

•	print Union/Sort/Sort2: Returns the data of the specified Wifi network.


### Filters:
•	filterList: compare between 2 wifi by filter.

•	Filter: Interface.

•	IsBelong: Checks if the Wifi network is similar to an existing one.

•	FilterByMAC: Compare between 2 wifi by MAC address.

•	FilterByTime: Compare between 2 wifi by time.


### KML:
•	Kml: responsible of writing the final kml file .


### main:
•	run: runs the program.
