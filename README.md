# Collecting-geographic-information-app
This program enables geographic information collection and production of insights
and From this information the program display the information in graphical tools.

## Contributors
Yarden Mizrahi - 311332183
Dor Levi - 203288139

## Prerequisites
Please Make sure you have SQL Workbench and eclipce installed on your computer. 

## Getting Started
 Simply press at the "Run" button in eclipce.

## Installing
At the second you click on the "Run" butten, a window will pop up in front of you.
In this window, you will be faced many possibilities:
• Receive information: From a folder or a specific file.
• Different ways to filter the information: by location ,by time and by ID. And in each filter you have to enter the information he demands(You can see the correct syntax that the filter asks for if you place the mouse on the filter itself).
• Implements algorithm number 1 and algorithm number 2;

## Project:

### CSV:
#### makeCSV: unifies the CSV files and prints them into an ArrayList
• getAllcsvFileListFromFolder: 
This function make list with all the CSV path
• readFilesAndAddToUnionList:
This function unifies all CSV files to the ArrayList
• writeListToCSVFile: This function write the ArrayList to CSV file by the format(ColumnNamesList)
• writeListToCSVFile2:
This function write the ArrayList to CSV file by the format(ColumnNamesList)

### Wifi:
• WiFi: Make object wifi. 

### GPSPoints:
• GPSPoint: An Object that represent the wifi GPS data.

### Tests:
• Test_algo2Network.
• Test_GPSPoint.
• Test_Line_Algo2.
• Test_Parameters.
• Test_Calculate_Algo2.

### Main:
• main.

### MainWatch:
• watch.

### SQL:
• MySQL.

### Wifi:
• WiFi: This class make object wifi.

### Filters:
•Filter: Interface Filter
• FilterGPS: This class compare between 2 wifi by GPS
• FilterID: This class compare between 2 wifi by ID
• filterList: This class compare between 2 wifi by filter
• FilterMAC: This class compare between 2 wifi by MAC address
• FilterSSID: This class compare between 2 wifi by SSID
• FilterTime: This class compare between 2 wifi by Time

### KML:
• Kml: responsible of writing the final kml file .
•	makeKML: creating the kml and sending it to directory.

### GUI:
• gui
• clickFilter

### weightedCenterPoint:
• Algo_2Function: This class represents functions that read comb CSV and no GPS CSV, calculate.
• algo1:This class represents functions that read comb CSV.
• Algo2_line:  This class represents an object of algo2 line.
• algo2Network:This class defines for every WIFI network that was scanned by the wigleWifi app it's 2 unique elements.
• Calculate_Algo2:This class represents an object of Location and PI for Algo2.
• Line_Algo2:This class represents an object of the final line in the csv .
• Parameters:this class represents all the constants Parameters.

