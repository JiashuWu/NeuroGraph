package com.example.jiashuwu.neurograph;

/**
 * Created by Jiashu Wu on 12/04/2018.
 */

public class SharingReadMe {

    public static String readme =
            "##########################\n" +
                    "#------------------------#\n" +
                    "#|# NEURO GRAPH README #|#\n" +
                    "#------------------------#\n" +
                    "##########################\n" +
                    "\n" +
                    "Thank you for using Neurograph.\n" +
                    "\n" +
                    "PLEASE READ THE FOLLOWING DESCRIPTIONS TO HELP YOU UNDERSTAND THE DATA FILES;\n" +
                    "\n" +
                    "This is the readme.txt file. This file contains some explaination of the data file.\n" +
                    "\n" +
                    "Meaning of every column in the csv data file:\n" +
                    "\n" +
                    "######\n" +
                    "\n" +
                    "1: point serial number\n" +
                    "2: year of the data point collection timestamp\n" +
                    "\tFORMAT: YYYY\n" +
                    "3: month of the data point collection timestamp\n" +
                    "\tFORMAT: MM\n" +
                    "4: day of the data point collection timestamp\n" +
                    "\tFORMAT: DD\n" +
                    "5: hour of the data point collection timestamp\n" +
                    "\tFORMAT: HH\n" +
                    "6: minute of the data point collection timestamp\n" +
                    "\tFORMAT: MM\n" +
                    "7 second of the data point collection timestamp\n" +
                    "\tFORMAT: SS\n" +
                    "8: millisecond of the data point collection timestamp\n" +
                    "\tFORMAT: SSS\n" +
                    "9: x coordinate of the data point\n" +
                    "10: y coordinate of the dtaa point\n" +
                    "11: pressure of this touch point\n" +
                    "12: touch size of this touch point\n" +
                    "13: test ID\n" +
                    "14: test type\n" +
                    "\tIt contains the following possible test types:\n" +
                    "\t(1) parallel_line_practice\n" +
                    "\t(2) parallel_line_test\n" +
                    "\t(3) circular_motion\n" +
                    "\t(4) static_full_background\n" +
                    "\t(5) static_corner_background\n" +
                    "\t(6) dynamic_blank_background\n" +
                    "\t(7) dynamic_seasonal_background\n" +
                    "15: image type\n" +
                    "\tIt contains the following possible image types:\n" +
                    "\t(1) blank\n" +
                    "\t(2) parallel_line\n" +
                    "\t(3) red_dot\n" +
                    "\t(4) blue_dot\n" +
                    "\t(5) black_dot\n" +
                    "\t(6) spiral\n" +
                    "\t(7) pentagon\n" +
                    "16: interval duration\n" +
                    "\t(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0\n" +
                    "\t(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test\n" +
                    "\t(c) For dynamic seasonal background test, the interval duration is the period of the image app-disappear seasonality\n" +
                    "17: the total number of point collected in this test\n" +
                    "18: the date when this test started\n" +
                    "\tFORMAT: YYYYMMDD\n" +
                    "19: the time when this test started\n" +
                    "\tFORMAT: HHMMSSMMM\n" +
                    "20: the date when this test ended\n" +
                    "\tFORMAT: YYYYMMDD\n" +
                    "21: the time when this test ended\n" +
                    "\tFORMAT: HHMMSSMMM\n" +
                    "22: painter width\n" +
                    "\tNOTE: This is the painter width testee used during this test.\n" +
                    "\tNOTE: Painter width will not affect any test data, it is only used for the convinence for the testee to see the line more clearly.\n" +
                    "\tPOSSIBLE VALUES:\n" +
                    "\t(a) 5\n" +
                    "\t(b) 10\n" +
                    "\t(c) 15\n" +
                    "23: language used during test\n" +
                    "\tPOSSIBLE VALUES:\n" +
                    "\t(a) English\n" +
                    "\t(b) Simplified Chinese\n" +
                    "\t(c) Traditional Chinese\n" +
                    "\t(d) Dutch\n" +
                    "\t(e) French\n" +
                    "\t(f) German\n" +
                    "\t(g) Italian\n" +
                    "\t(h) Japanese\n" +
                    "\t(i) Portuguese\n" +
                    "\t(j) Russian\n" +
                    "\t(k) Spanish\n" +
                    "24: font size used during the test\n" +
                    "\tNOTE: The font size will not affect any test data\n" +
                    "\tPOSSIBLE VALUES:\n" +
                    "\t(a) Normal\n" +
                    "\t(b) Large\n" +
                    "\tNOTE: Normal is the normal font size of Android system. Large is also a Android System defined font size, which is 1.5 times larger than Normal font size.\n" +
                    "25: testee ID\n" +
                    "26: testee's name\n" +
                    "27: testee's age\n" +
                    "28: testee's gender\n" +
                    "\tThis can be:\n" +
                    "\t(a) Male\n" +
                    "\t(b) Female\n" +
                    "\t(c) Others\n" +
                    "29: testee's education background\n" +
                    "\tThis can be:\n" +
                    "\t(a) primary school or below\n" +
                    "\t(b) high school\n" +
                    "\t(c) undergraduate\n" +
                    "\t(d) postgraduate\n" +
                    "\t(e) doctor or higher\n" +
                    "30: testee's previous Alzheimer rating score\n" +
                    "\tNOTE: This is not a compulsory field.\n" +
                    "\tNOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.\n" +
                    "31: testee's treatment information\n" +
                    "\tNOTE: This means whether this testee was receiving treatment when they took this test.\n" +
                    "\tNOTE: The possible answer will be\n" +
                    "\t(1) Yes\n" +
                    "\t(2) No\n" +
                    "32: testing device's brand\n" +
                    "33: testing device's model\n" +
                    "34: testing device's product name\n" +
                    "35: testing device's manufacturer\n" +
                    "36: testing device's screen height in pixel\n" +
                    "37: testing device's screen width in pixel\n" +
                    "38: testing device's screen testing/drawing height in pixel\n" +
                    "39: testing device's screen testing/drawing width in pixel\n" +
                    "40: testing device's navigation bar height\n" +
                    "41: testing device's Android system version code\n" +
                    "42: Neurograph version name\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "## END OF NEUROGRAPH_DATA_FILE_README.TXT";



}
