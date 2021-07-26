package com.namangarg.androiddocumentscannerandfilter.Helper;

public class ContrastHelper {

    private static double VERY_DARK_IMAGE = 1.5;
    private static double DARK_IMAGE = 1.4;
    private static double NORMAL_IMAGE = 1.3;
    private static double BRIGHT_IMAGE = 1.25;
    private static double VERY_BRIGHT_IMAGE = 1.2;

    public static double getContrastValue(int n){
        switch (n){
            case 1: return VERY_DARK_IMAGE;
            case 2: return DARK_IMAGE;
            case 3: return NORMAL_IMAGE;
            case 4: return BRIGHT_IMAGE;
            case 5: return VERY_BRIGHT_IMAGE;
        }
        return NORMAL_IMAGE;
    }
}
