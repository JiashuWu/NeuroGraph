##########################
#------------------------#
#|# NEURO GRAPH README #|#
#------------------------#
##########################

Thank you for using Neurograph.

PLEASE READ THE FOLLOWING DESCRIPTIONS TO HELP YOU UNDERSTAND THE DATA FILES;

This is the readme.txt file. This file contains some explaination of the data file.

Meaning of every column in the csv data file:

######
1: year of the data point collection timestamp
2: month of the data point collection timestamp
3: day of the data point collection timestamp
4: hour of the data point collection timestamp
5: minute of the data point collection timestamp
6: second of the data point collection timestamp
7: millisecond of the data point collection timestamp
8: x coordinate of the data point
9: y coordinate of the dtaa point
10: pressure of this touch point
11: touch size of this touch point
12: test ID
13: test type
	It contains the following possible test types:
	(1) parallel_line_practice
	(2) parallel_line_test
	(3) circular_motion
	(4) static_full_background
	(5) static_corner_background
	(6) dynamic_blank_background
	(7) dynamic_seasonal_background
14: image type
	It contains the following possible image types:
	(1) blank
	(2) parallel_line
	(3) red_dot
	(4) blue_dot
	(5) black_dot
	(6) spiral
	(7) pentagon
15: interval duration
	(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0
	(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test
	(c) For dynamic seasonal background test, the interval duration is the period of the image app-disappear seasonality
16: the total number of point collected in this test
17: the date when this test started
	FORMAT: YYYYMMDD
18: the time when this test started
	FORMAT: HHMMSSMMM
19: the date when this test ended
	FORMAT: YYYYMMDD
20: the time when this test ended
	FORMAT: HHMMSSMMM
21: testee ID
22: testee's name
23: testee's age
24: testee's gender
	This can be:
	(a) Male
	(b) Female
	(c) Others
25: testee's education background
	This can be:
	(a) primary school or below
	(b) high school
	(c) undergraduate
	(d) postgraduate
	(e) doctor or higher
26: testee's previous Alzheimer rating score
	NOTE: This is not a compulsory field.
	NOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.
27: testee's treatment information
	NOTE: This means whether this testee was receiving treatment when they took this test.
	NOTE: The possible answer will be
	(1) Yes
	(2) No
28: testing device's brand
29: testing device's model
30: testing device's product name
31: testing device's manufacturer
32: testing device's screen height in pixel
33: testing device's screen width in pixel
34: testing device's screen testing/drawing height in pixel
35: testing device's screen testing/drawing width in pixel
36: testing device's navigation bar height
37: testing device's Android system version code

