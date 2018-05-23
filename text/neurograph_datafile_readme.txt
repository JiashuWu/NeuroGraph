##########################
#------------------------#
#|# NEURO GRAPH README #|#
#------------------------#
##########################


THANK YOU FOR USING NEUROGRAPH


PLEASE READ THE FOLLOWING DESCRIPTIONS TO HELP YOU UNDERSTAND THE DATA FILES;


This is the readme.txt file. This file contains some explanation of the data file.


	PART A: Introduction of files
	PART B: Explanation for NeurographOutputDataFile.csv -> .csv format data file
	PART C: Explanation for NeurographOutputDataFile.txt -> .txt format data file
	PART D: Explanation for NeurographOutputDatabase.db  -> .db  format data file (db stands for "database")




============================
| NeurographOutputDataFile |
============================


The  .txt  format file was named as NeurographOutputDataFile + file generation timestamp + .txt
The  .csv  format file was named as NeurographOutputDataFile + file generation timestamp + .csv
The  .db   format file was named as NeurographOutputDataFile + file generation timestamp + .db
The readme format file was named as NeurographOutputDataFileReadme.txt



================================
| NeurographOutputDataFile.csv |
================================

Meaning of each column in the csv data file:

######

1: point serial number
2: year of the data point collection timestamp
	FORMAT: YYYY
3: month of the data point collection timestamp
	FORMAT: MM
4: day of the data point collection timestamp
	FORMAT: DD
5: hour of the data point collection timestamp
	FORMAT: HH
6: minute of the data point collection timestamp
	FORMAT: MM
7 second of the data point collection timestamp
	FORMAT: SS
8: millisecond of the data point collection timestamp
	FORMAT: SSS
9: x coordinate of the data point
10: y coordinate of the dtaa point
11: pressure of this touch point
12: touch size of this touch point
13: tool type
	It contains the following possible tool types:
	(1) 0  ->  UNKNOWN
	(2) 1  ->  FINGER
	(3) 2  ->  STYLUS (PEN etc.)
	(4) 3  ->  MOUSE (MOUSE OR TRACKPAD) 
	(5) 4  ->  ERASER
	(6) -1 ->  Special placeholder for ParallelLinePractice ONLY
14: test ID
15: test type
	It contains the following possible test types:
	(1) parallel_line_practice
	(2) parallel_line_test
	(3) circular_motion
	(4) static_full_background
	(5) static_corner_background
	(6) dynamic_blank_background
	(7) dynamic_seasonal_background
16: image type
	It contains the following possible image types:
	(1) blank
	(2) parallel_line
	(3) red_dot
	(4) blue_dot
	(5) black_dot
	(6) spiral
	(7) pentagon
17: interval duration
	(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0
	(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test
	(c) For dynamic seasonal background test, the interval duration is the period of the image appear-disappear seasonality
18: number of points
	NOTE: The total number of points collected in this test
19: the date when this test started
	FORMAT: YYYYMMDD
20: the time when this test started
	FORMAT: HHMMSSMMM
21: the date when this test ended
	FORMAT: YYYYMMDD
22: the time when this test ended
	FORMAT: HHMMSSMMM
23: painter width
	NOTE: This is the painter width testee used during this test.
	NOTE: Painter width will not affect any test data, it is only used for the convinence for the testee to see the line more clearly.
	POSSIBLE VALUES:
	(a) 5
	(b) 10
	(c) 15
24: language used during test
	POSSIBLE VALUES:
	(a) English
	(b) Simplified Chinese
	(c) Traditional Chinese
	(d) Dutch
	(e) French
	(f) German
	(g) Italian
	(h) Japanese
	(i) Portuguese
	(j) Russian
	(k) Spanish
25: font size used during the test
	NOTE: The font size will not affect any test data, it is only used to help people with visualization difficulties.
	POSSIBLE VALUES:
	(a) Normal
	(b) Large
	NOTE: Normal is the normal font size of Android system. Large is also a Android System defined font size, which is 1.5 times larger than Normal font size.
26: testee ID
27: testee's name
28: registration code
29: testee's age
30: testee's gender
	This can be:
	(a) Male
	(b) Female
	(c) Others
31: testee's education background
	This can be:
	(a) primary school or below
	(b) high school
	(c) undergraduate
	(d) postgraduate
	(e) doctor or higher
32: testee's previous Alzheimer rating score
	NOTE: This is not a compulsory field.
	NOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.
33: testee's treatment information
	NOTE: This means whether this testee was receiving treatment when they took this test.
	NOTE: The possible answer will be
	(1) Yes
	(2) No
34: testing device's brand
35: testing device's model
36: testing device's product name
37: testing device's manufacturer
38: testing device's screen height in pixel
39: testing device's screen width in pixel
40: testing device's screen testing/drawing height in pixel
	NOTE: This is the height of the testing/drawing area of the screen. It may be different from the device screen height since there is a navigation bar which is not touchable.
41: testing device's screen testing/drawing width in pixel
42: testing device's navigation bar height
43: testing device's Android system version code
44: Neurograph version name




================================
| NeurographOutputDataFile.txt |
================================

######

Meaning of each row in the csv data file:

For each test, you will see the following information:

TEST INFORMATION
test ID
user ID
test starting time
	FORMAT YYYY-MM-DD HH:MM:SS.SSS
test ending time
	FORMAT YYYY-MM-DD HH:MM:SS.SSS
test type
	It contains the following possible test types:
	(1) parallel_line_practice
	(2) parallel_line_test
	(3) circular_motion
	(4) static_full_background
	(5) static_corner_background
	(6) dynamic_blank_background
	(7) dynamic_seasonal_background
image type
	It contains the following possible image types:
	(1) blank
	(2) parallel_line
	(3) red_dot
	(4) blue_dot
	(5) black_dot
	(6) spiral
	(7) pentagon
interval duration
	(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0
	(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test
	(c) For dynamic seasonal background test, the interval duration is the period of the image appear-disappear seasonality
number of points
	NOTE: The total number of points collected in this test
painter width
	NOTE: This is the painter width testee used during this test.
	NOTE: Painter width will not affect any test data, it is only used for the convinence for the testee to see the line more clearly.
	POSSIBLE VALUES:
	(a) 5
	(b) 10
	(c) 15
language during test
	POSSIBLE VALUES:
	(a) English
	(b) Simplified Chinese
	(c) Traditional Chinese
	(d) Dutch
	(e) French
	(f) German
	(g) Italian
	(h) Japanese
	(i) Portuguese
	(j) Russian
	(k) Spanish
is scale during test
	NOTE: The font size will not affect any test data, it is only used to help people with visualization difficulties.
	POSSIBLE VALUES:
	(a) Normal
	(b) Large
	NOTE: Normal is the normal font size of Android system. Large is also a Android System defined font size, which is 1.5 times larger than Normal font size.

TESTEE INFORMATION
user name
age
gender
	This can be:
	(a) Male
	(b) Female
	(c) Others
education
	This can be:
	(a) primary school or below
	(b) high school
	(c) undergraduate
	(d) postgraduate
	(e) doctor or higher
rating score
	NOTE: This is not a compulsory field.
	NOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.
current receive treatment
	NOTE: This means whether this testee was receiving treatment when they took this test.
	NOTE: The possible answer will be
	(1) Yes
	(2) No

DEVICE INFORMATION
device brand
device model
device product name
device manufacturer
device screen height in pixels
device screen width in pixels
device screen testing/drawing height in pixels
	NOTE: This is the height of the testing/drawing area of the screen. It is different from the device screen height since there is a navigation bar which is not touchable.
device screen testing/drawing width in pixels
device navigation bar height
device Android system version code

NEUROGRAPH INFORMATION
Neurograph version

TEST POINT DATA INFORMATION
point serial number | timestamp of the point | x coordinate | y coordinate | pressure | touch point size | tool type


NOTE:
	point serial number    -> the serial number / order of this point in this test
	timestamp of the point -> the timestamp this point was collected
	x Coordinate           -> x coordinate of this point
	y coordinate           -> y coordinate of this point
	pressure               -> the pressure value of this touch, some of the devices may not be able to get this value, it depends on the hardware
	touch point size       -> the touch point size of this point, some of the devices may not be able to get this value, it depends on the hardware
	tool type              -> the tool which was used to produce this touch point
	POSSIBLE TOOL TYPES:
		(1)  0  -> UNKNOWN TOOL TYPE
		(2)  1  -> FINGER
		(3)  2  -> STYLUS (PEN etc.)
		(4)  3  -> MOUSE (MOUSE OR TRACKPAD)
		(5)  4  -> ERASER
		(6)  -1 -> Special placeholder for ParallalLinePractice ONLY


===============================
| NeurographOutputDatabase.db |
===============================


This is the database file which stores all the data. This can be analysed in database analysing softwares. One of the recommendation is "SqliteSpy".



### END OF NEUROGRAPH_DATA_FILE_README.TXT ###


