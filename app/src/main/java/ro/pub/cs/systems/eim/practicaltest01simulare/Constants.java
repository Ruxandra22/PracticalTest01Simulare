package ro.pub.cs.systems.eim.practicaltest01simulare;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by Ruxandra on 3/29/2018.
 */

public class Constants {

    final public static String TAG = "[Started Service]";

    final public static String ACTION_STRING = "ro.pub.cs.systems.eim.practicetest01simulare.string";
    final public static int NUMBER_OF_CLICKS_THRESHOLD = 10;
    final public static String SERVICE_STOPPED = "Service stoppped";
    final public static String SERVICE_STARTED = "Service started";

    final public static String ACTION_INTEGER = "ro.pub.cs.systems.eim.practicetest01simulare.integer";
    final public static String ACTION_ARRAY_LIST = "ro.pub.cs.systems.eim.practicetest01simulare.arraylist";

    final public static int MESSAGE_STRING = 1;
    final public static int MESSAGE_INTEGER = 2;
    final public static int MESSAGE_ARRAY_LIST = 3;

    final public static String DATA = "ro.pub.cs.systems.eim.practicetest01simulare.data";

    final public static String STRING_DATA = "EIM";
    final public static int INTEGER_DATA = Integer.parseInt(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
    final public static ArrayList<String> ARRAY_LIST_DATA = new ArrayList<>(Arrays.asList("EIM", "laborator"));

}