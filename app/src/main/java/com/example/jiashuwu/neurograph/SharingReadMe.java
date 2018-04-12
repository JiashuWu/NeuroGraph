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
            "1: year of the data point collection timestamp\n" +
            "2: month of the data point collection timestamp\n" +
            "3: day of the data point collection timestamp\n" +
            "4: hour of the data point collection timestamp\n" +
            "5: minute of the data point collection timestamp\n" +
            "6: second of the data point collection timestamp\n" +
            "7: millisecond of the data point collection timestamp\n" +
            "8: x coordinate of the data point\n" +
            "9: y coordinate of the dtaa point\n" +
            "10: pressure of this touch point\n" +
            "11: touch size of this touch point\n" +
            "12: test ID\n" +
            "13: test type\n" +
            "\tIt contains the following possible test types:\n" +
            "\t(1) parallel_line_practice\n" +
            "\t(2) parallel_line_test\n" +
            "\t(3) circular_motion\n" +
            "\t(4) static_full_background\n" +
            "\t(5) static_corner_background\n" +
            "\t(6) dynamic_blank_background\n" +
            "\t(7) dynamic_seasonal_background\n" +
            "14: image type\n" +
            "\tIt contains the following possible image types:\n" +
            "\t(1) blank\n" +
            "\t(2) parallel_line\n" +
            "\t(3) red_dot\n" +
            "\t(4) blue_dot\n" +
            "\t(5) black_dot\n" +
            "\t(6) spiral\n" +
            "\t(7) pentagon\n" +
            "15: interval duration\n" +
            "\t(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0\n" +
            "\t(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test\n" +
            "\t(c) For dynamic seasonal background test, the interval duration is the period of the image app-disappear seasonality\n" +
            "16: the total number of point collected in this test\n" +
            "17: the date when this test started\n" +
            "\tFORMAT: YYYYMMDD\n" +
            "18: the time when this test started\n" +
            "\tFORMAT: HHMMSSMMM\n" +
            "19: the date when this test ended\n" +
            "\tFORMAT: YYYYMMDD\n" +
            "20: the time when this test ended\n" +
            "\tFORMAT: HHMMSSMMM\n" +
            "21: testee ID\n" +
            "22: testee's name\n" +
            "23: testee's age\n" +
            "24: testee's gender\n" +
            "\tThis can be:\n" +
            "\t(a) Male\n" +
            "\t(b) Female\n" +
            "\t(c) Others\n" +
            "25: testee's education background\n" +
            "\tThis can be:\n" +
            "\t(a) primary school or below\n" +
            "\t(b) high school\n" +
            "\t(c) undergraduate\n" +
            "\t(d) postgraduate\n" +
            "\t(e) doctor or higher\n" +
            "26: testee's previous Alzheimer rating score\n" +
            "\tNOTE: This is not a compulsory field.\n" +
            "\tNOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.\n" +
            "27: testee's treatment information\n" +
            "\tNOTE: This means whether this testee was receiving treatment when they took this test.\n" +
            "\tNOTE: The possible answer will be\n" +
            "\t(1) Yes\n" +
            "\t(2) No\n" +
            "28: testing device's brand\n" +
            "29: testing device's model\n" +
            "30: testing device's product name\n" +
            "31: testing device's manufacturer\n" +
            "32: testing device's screen height in pixel\n" +
            "33: testing device's screen width in pixel\n" +
            "34: testing device's screen testing/drawing height in pixel\n" +
            "35: testing device's screen testing/drawing width in pixel\n" +
            "36: testing device's navigation bar height\n" +
            "37: testing device's Android system version code\n" +
            "\n";



}
