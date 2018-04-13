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
13: test ID
14: test type
	It contains the following possible test types:
	(1) parallel_line_practice
	(2) parallel_line_test
	(3) circular_motion
	(4) static_full_background
	(5) static_corner_background
	(6) dynamic_blank_background
	(7) dynamic_seasonal_background
15: image type
	It contains the following possible image types:
	(1) blank
	(2) parallel_line
	(3) red_dot
	(4) blue_dot
	(5) black_dot
	(6) spiral
	(7) pentagon
16: interval duration
	(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0
	(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test
	(c) For dynamic seasonal background test, the interval duration is the period of the image app-disappear seasonality
17: the total number of point collected in this test
18: the date when this test started
	FORMAT: YYYYMMDD
19: the time when this test started
	FORMAT: HHMMSSMMM
20: the date when this test ended
	FORMAT: YYYYMMDD
21: the time when this test ended
	FORMAT: HHMMSSMMM
22: painter width
	NOTE: This is the painter width testee used during this test.
	NOTE: Painter width will not affect any test data, it is only used for the convinence for the testee to see the line more clearly.
	POSSIBLE VALUES:
	(a) 5
	(b) 10
	(c) 15
23: language used during test
	POSSIBLE VALUES:
	(a) English
	(b) Simplified Chinese
	(c) Traditional Chinese
	(d) Dutch
	(e) French
	(f) Greman
	(g) Italian
	(h) Japanese
	(i) Portuguese
	(j) Russian
	(k) Spanish
24: font size used during the test
	NOTE: The font size will not affect any test data
	POSSIBLE VALUES:
	(a) Normal
	(b) Large
	NOTE: Normal is the normal font size of Android system. Large is also a Android System defined font size, which is 1.5 times larger than Normal font size.
25: testee ID
26: testee's name
27: testee's age
28: testee's gender
	This can be:
	(a) Male
	(b) Female
	(c) Others
29: testee's education background
	This can be:
	(a) primary school or below
	(b) high school
	(c) undergraduate
	(d) postgraduate
	(e) doctor or higher
30: testee's previous Alzheimer rating score
	NOTE: This is not a compulsory field.
	NOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.
31: testee's treatment information
	NOTE: This means whether this testee was receiving treatment when they took this test.
	NOTE: The possible answer will be
	(1) Yes
	(2) No
32: testing device's brand
33: testing device's model
34: testing device's product name
35: testing device's manufacturer
36: testing device's screen height in pixel
37: testing device's screen width in pixel
38: testing device's screen testing/drawing height in pixel
39: testing device's screen testing/drawing width in pixel
40: testing device's navigation bar height
41: testing device's Android system version code
42: Neurograph version name





## END OF NEUROGRAPH_DATA_FILE_README.TXT