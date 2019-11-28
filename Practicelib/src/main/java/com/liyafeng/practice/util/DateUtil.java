package com.liyafeng.practice.util;

public class DateUtil {

    public static String parseTime(int duration) {
        int second = duration / 1000;
        int min = second / 60;
        int sec = second % 60;

        String m = "00";
        if (min > 0 && min < 10) {
            m = "0" + min;
        } else if (min >= 10) {
            m = String.valueOf(min);
        }


        String s = "00";
        if (sec > 0 && sec < 10) {
            s = "0" + sec;
        } else if (sec >= 10) {
            s = String.valueOf(sec);
        }

        return m + ":" + s;

    }


    public static String getTimeString(int second) {
        if (second < 10) {
            return second + "秒";
        }
        if (second < 60) {
            return second + "秒";
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return minute + "分" + second + "秒";
                }
                return minute + "分" + second + "秒";
            }
            if (second < 10) {
                return minute + "分" + second + "秒";
            }
            return minute + "分" + second + "秒";
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return hour + "时" + minute + "分" + second + "秒";
                }
                return hour + "时" + minute + "分" + second + "秒";
            }
            if (second < 10) {
                return hour + "时" + minute + "分" + second + "秒";
            }
            return hour + "时" + minute + "分" + second + "秒";
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + "时" + minute + "分" + second + "秒";
            }
            return hour + "时" + minute + "分" + second + "秒";
        }
        if (second < 10) {
            return hour + "时" + minute + "分" + second + "秒";
        }
        return hour + "时" + minute + "分" + second + "秒";
    }

    public static String getTime(int second) {
        if (second < 10) {
            return "00:0" + second;
        }
        if (second < 60) {
            return "00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + minute + ":0" + second;
            }
            return "0" + hour + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + minute + ":0" + second;
        }
        return hour + minute + ":" + second;
    }
}
