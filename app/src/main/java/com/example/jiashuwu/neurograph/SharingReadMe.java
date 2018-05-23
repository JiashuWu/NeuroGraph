package com.example.jiashuwu.neurograph;

/**
 * Created by Jiashu Wu on 12/04/2018.
 */

public class SharingReadMe {

    public static String readme = "##########################\n" +
            "#------------------------#\n" +
            "#|# NEURO GRAPH README #|#\n" +
            "#------------------------#\n" +
            "##########################\n" +
            "\n" +
            "\n" +
            "THANK YOU FOR USING NEUROGRAPH\n" +
            "\n" +
            "\n" +
            "PLEASE READ THE FOLLOWING DESCRIPTIONS TO HELP YOU UNDERSTAND THE DATA FILES;\n" +
            "\n" +
            "\n" +
            "This is the readme.txt file. This file contains some explanation of the data file.\n" +
            "\n" +
            "\n" +
            "\tPART A: Introduction of files\n" +
            "\tPART B: Explanation for NeurographOutputDataFile.csv -> .csv format data file\n" +
            "\tPART C: Explanation for NeurographOutputDataFile.txt -> .txt format data file\n" +
            "\tPART D: Explanation for NeurographOutputDatabase.db  -> .db  format data file (db stands for \"database\")\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "============================\n" +
            "| NeurographOutputDataFile |\n" +
            "============================\n" +
            "\n" +
            "\n" +
            "The  .txt  format file was named as NeurographOutputDataFile + file generation timestamp + .txt\n" +
            "The  .csv  format file was named as NeurographOutputDataFile + file generation timestamp + .csv\n" +
            "The  .db   format file was named as NeurographOutputDataFile + file generation timestamp + .db\n" +
            "The readme format file was named as NeurographOutputDataFileReadme.txt\n" +
            "\n" +
            "\n" +
            "\n" +
            "================================\n" +
            "| NeurographOutputDataFile.csv |\n" +
            "================================\n" +
            "\n" +
            "Meaning of each column in the csv data file:\n" +
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
            "13: tool type\n" +
            "\tIt contains the following possible tool types:\n" +
            "\t(1) 0  ->  UNKNOWN\n" +
            "\t(2) 1  ->  FINGER\n" +
            "\t(3) 2  ->  STYLUS (PEN etc.)\n" +
            "\t(4) 3  ->  MOUSE (MOUSE OR TRACKPAD) \n" +
            "\t(5) 4  ->  ERASER\n" +
            "\t(6) -1 ->  Special placeholder for ParallelLinePractice ONLY\n" +
            "14: test ID\n" +
            "15: test type\n" +
            "\tIt contains the following possible test types:\n" +
            "\t(1) parallel_line_practice\n" +
            "\t(2) parallel_line_test\n" +
            "\t(3) circular_motion\n" +
            "\t(4) static_full_background\n" +
            "\t(5) static_corner_background\n" +
            "\t(6) dynamic_blank_background\n" +
            "\t(7) dynamic_seasonal_background\n" +
            "16: image type\n" +
            "\tIt contains the following possible image types:\n" +
            "\t(1) blank\n" +
            "\t(2) parallel_line\n" +
            "\t(3) red_dot\n" +
            "\t(4) blue_dot\n" +
            "\t(5) black_dot\n" +
            "\t(6) spiral\n" +
            "\t(7) pentagon\n" +
            "17: interval duration\n" +
            "\t(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0\n" +
            "\t(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test\n" +
            "\t(c) For dynamic seasonal background test, the interval duration is the period of the image appear-disappear seasonality\n" +
            "18: number of points\n" +
            "\tNOTE: The total number of points collected in this test\n" +
            "19: the date when this test started\n" +
            "\tFORMAT: YYYYMMDD\n" +
            "20: the time when this test started\n" +
            "\tFORMAT: HHMMSSMMM\n" +
            "21: the date when this test ended\n" +
            "\tFORMAT: YYYYMMDD\n" +
            "22: the time when this test ended\n" +
            "\tFORMAT: HHMMSSMMM\n" +
            "23: painter width\n" +
            "\tNOTE: This is the painter width testee used during this test.\n" +
            "\tNOTE: Painter width will not affect any test data, it is only used for the convinence for the testee to see the line more clearly.\n" +
            "\tPOSSIBLE VALUES:\n" +
            "\t(a) 5\n" +
            "\t(b) 10\n" +
            "\t(c) 15\n" +
            "24: language used during test\n" +
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
            "25: font size used during the test\n" +
            "\tNOTE: The font size will not affect any test data, it is only used to help people with visualization difficulties.\n" +
            "\tPOSSIBLE VALUES:\n" +
            "\t(a) Normal\n" +
            "\t(b) Large\n" +
            "\tNOTE: Normal is the normal font size of Android system. Large is also a Android System defined font size, which is 1.5 times larger than Normal font size.\n" +
            "26: testee ID\n" +
            "27: testee's name\n" +
            "28: registration code\n" +
            "29: testee's age\n" +
            "30: testee's gender\n" +
            "\tThis can be:\n" +
            "\t(a) Male\n" +
            "\t(b) Female\n" +
            "\t(c) Others\n" +
            "31: testee's education background\n" +
            "\tThis can be:\n" +
            "\t(a) primary school or below\n" +
            "\t(b) high school\n" +
            "\t(c) undergraduate\n" +
            "\t(d) postgraduate\n" +
            "\t(e) doctor or higher\n" +
            "32: testee's previous Alzheimer rating score\n" +
            "\tNOTE: This is not a compulsory field.\n" +
            "\tNOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.\n" +
            "33: testee's treatment information\n" +
            "\tNOTE: This means whether this testee was receiving treatment when they took this test.\n" +
            "\tNOTE: The possible answer will be\n" +
            "\t(1) Yes\n" +
            "\t(2) No\n" +
            "34: testing device's brand\n" +
            "35: testing device's model\n" +
            "36: testing device's product name\n" +
            "37: testing device's manufacturer\n" +
            "38: testing device's screen height in pixel\n" +
            "39: testing device's screen width in pixel\n" +
            "40: testing device's screen testing/drawing height in pixel\n" +
            "\tNOTE: This is the height of the testing/drawing area of the screen. It may be different from the device screen height since there is a navigation bar which is not touchable.\n" +
            "41: testing device's screen testing/drawing width in pixel\n" +
            "42: testing device's navigation bar height\n" +
            "43: testing device's Android system version code\n" +
            "44: Neurograph version name\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "================================\n" +
            "| NeurographOutputDataFile.txt |\n" +
            "================================\n" +
            "\n" +
            "######\n" +
            "\n" +
            "Meaning of each row in the csv data file:\n" +
            "\n" +
            "For each test, you will see the following information:\n" +
            "\n" +
            "TEST INFORMATION\n" +
            "test ID\n" +
            "user ID\n" +
            "test starting time\n" +
            "\tFORMAT YYYY-MM-DD HH:MM:SS.SSS\n" +
            "test ending time\n" +
            "\tFORMAT YYYY-MM-DD HH:MM:SS.SSS\n" +
            "test type\n" +
            "\tIt contains the following possible test types:\n" +
            "\t(1) parallel_line_practice\n" +
            "\t(2) parallel_line_test\n" +
            "\t(3) circular_motion\n" +
            "\t(4) static_full_background\n" +
            "\t(5) static_corner_background\n" +
            "\t(6) dynamic_blank_background\n" +
            "\t(7) dynamic_seasonal_background\n" +
            "image type\n" +
            "\tIt contains the following possible image types:\n" +
            "\t(1) blank\n" +
            "\t(2) parallel_line\n" +
            "\t(3) red_dot\n" +
            "\t(4) blue_dot\n" +
            "\t(5) black_dot\n" +
            "\t(6) spiral\n" +
            "\t(7) pentagon\n" +
            "interval duration\n" +
            "\t(a) For static tests, parallel line test, parallel line practice and circular motion test, the interval duration is 0\n" +
            "\t(b) For dynamic blank background test, the interval duration is the duration for image appearing at the beginning of the test\n" +
            "\t(c) For dynamic seasonal background test, the interval duration is the period of the image appear-disappear seasonality\n" +
            "number of points\n" +
            "\tNOTE: The total number of points collected in this test\n" +
            "painter width\n" +
            "\tNOTE: This is the painter width testee used during this test.\n" +
            "\tNOTE: Painter width will not affect any test data, it is only used for the convinence for the testee to see the line more clearly.\n" +
            "\tPOSSIBLE VALUES:\n" +
            "\t(a) 5\n" +
            "\t(b) 10\n" +
            "\t(c) 15\n" +
            "language during test\n" +
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
            "is scale during test\n" +
            "\tNOTE: The font size will not affect any test data, it is only used to help people with visualization difficulties.\n" +
            "\tPOSSIBLE VALUES:\n" +
            "\t(a) Normal\n" +
            "\t(b) Large\n" +
            "\tNOTE: Normal is the normal font size of Android system. Large is also a Android System defined font size, which is 1.5 times larger than Normal font size.\n" +
            "\n" +
            "TESTEE INFORMATION\n" +
            "user name\n" +
            "registration code\n" +
            "age\n" +
            "gender\n" +
            "\tThis can be:\n" +
            "\t(a) Male\n" +
            "\t(b) Female\n" +
            "\t(c) Others\n" +
            "education\n" +
            "\tThis can be:\n" +
            "\t(a) primary school or below\n" +
            "\t(b) high school\n" +
            "\t(c) undergraduate\n" +
            "\t(d) postgraduate\n" +
            "\t(e) doctor or higher\n" +
            "rating score\n" +
            "\tNOTE: This is not a compulsory field.\n" +
            "\tNOTE: If testee left it as blank, it means the previous Alzheimer rating score was not applicable for this testee. If this is the case, default value 0 will be used as placeholder.\n" +
            "current receive treatment\n" +
            "\tNOTE: This means whether this testee was receiving treatment when they took this test.\n" +
            "\tNOTE: The possible answer will be\n" +
            "\t(1) Yes\n" +
            "\t(2) No\n" +
            "\n" +
            "DEVICE INFORMATION\n" +
            "device brand\n" +
            "device model\n" +
            "device product name\n" +
            "device manufacturer\n" +
            "device screen height in pixels\n" +
            "device screen width in pixels\n" +
            "device screen testing/drawing height in pixels\n" +
            "\tNOTE: This is the height of the testing/drawing area of the screen. It is different from the device screen height since there is a navigation bar which is not touchable.\n" +
            "device screen testing/drawing width in pixels\n" +
            "device navigation bar height\n" +
            "device Android system version code\n" +
            "\n" +
            "NEUROGRAPH INFORMATION\n" +
            "Neurograph version\n" +
            "\n" +
            "TEST POINT DATA INFORMATION\n" +
            "point serial number | timestamp of the point | x coordinate | y coordinate | pressure | touch point size | tool type\n" +
            "\n" +
            "\n" +
            "NOTE:\n" +
            "\tpoint serial number    -> the serial number / order of this point in this test\n" +
            "\ttimestamp of the point -> the timestamp this point was collected\n" +
            "\tx Coordinate           -> x coordinate of this point\n" +
            "\ty coordinate           -> y coordinate of this point\n" +
            "\tpressure               -> the pressure value of this touch, some of the devices may not be able to get this value, it depends on the hardware\n" +
            "\ttouch point size       -> the touch point size of this point, some of the devices may not be able to get this value, it depends on the hardware\n" +
            "\ttool type              -> the tool which was used to produce this touch point\n" +
            "\tPOSSIBLE TOOL TYPES:\n" +
            "\t\t(1)  0  -> UNKNOWN TOOL TYPE\n" +
            "\t\t(2)  1  -> FINGER\n" +
            "\t\t(3)  2  -> STYLUS (PEN etc.)\n" +
            "\t\t(4)  3  -> MOUSE (MOUSE OR TRACKPAD)\n" +
            "\t\t(5)  4  -> ERASER\n" +
            "\t\t(6)  -1 -> Special placeholder for ParallalLinePractice ONLY\n" +
            "\n" +
            "\n" +
            "===============================\n" +
            "| NeurographOutputDatabase.db |\n" +
            "===============================\n" +
            "\n" +
            "\n" +
            "This is the database file which stores all the data. This can be analysed in database analysing softwares. One of the recommendation is \"SqliteSpy\".\n" +
            "\n" +
            "\n" +
            "\n" +
            "### END OF NEUROGRAPH_DATA_FILE_README.TXT ###\n" +
            "\n" +
            "\n";

    public static String email_content_title = "Dear user,\n" +
            "\n" +
            "Thank you for using Neurograph. This is your data file email.\n" +
            "\n" +
            "Kind regards,\n" +
            "Neurograph team\n\n";





}
