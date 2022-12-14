package org.una.tools;

import org.una.settings.UniversalSettings;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleTools {
    public static int translateDaysValue(String day){
        if(day == null) return 0;
        if(day.equalsIgnoreCase(UniversalSettings.MONDAY_ES.value))
            return 1;
        else if (day.equalsIgnoreCase(UniversalSettings.TUESDAY_ES.value))
            return 2;
        else if (day.equalsIgnoreCase(UniversalSettings.WEDNESDAY_ES.value))
            return 3;
        else if(day.equalsIgnoreCase(UniversalSettings.THURSDAY_ES.value))
            return 4;
        else if (day.equalsIgnoreCase(UniversalSettings.FRIDAY_ES.value))
            return 5;
        return 0;
    }
    public static int translateHoursValue(String hour){
        if(hour == null) return 0;
        String[] splitHourStr = hour.split(":");
        return Integer.parseInt(splitHourStr[0]);
    }
    public static int compareHourAndDaysValues(String day1,String day2,String hour1,String hour2 ){
        int day1Value = translateDaysValue(day1);
        int day2Value = translateDaysValue(day2);
        int hour1Value = translateHoursValue(hour1);
        int hour2Value = translateHoursValue(hour2);
        if(day1Value > day2Value)
            return 1;
        if(day1Value < day2Value)
            return -1;
        return Integer.compare(hour1Value, hour2Value);
    }
    public static String calculateMaxPossibleFinalHour(List<String> hoursList){
        if (hoursList ==null) return null;
        int hoursListSize = hoursList.size();
        if (hoursListSize  <= 1) return null;
        String finalHour = hoursList.get(hoursListSize-1);
        String hours = finalHour.split(":")[0];
        String minutes = finalHour.split(":")[1];
        int resultingHour = Integer.parseInt(hours)+1;
        String resultingHourStr = Integer.toString(resultingHour);
        if(resultingHour <= 9)
            resultingHourStr = "0"+resultingHourStr;
        return String.format("%s:%s",resultingHourStr,minutes);
    }

    public static List<String> filterHoursGreaterThan(List<String> hours, String hour){
        if(hours == null) return null;
        if(hour == null) return hours;
        return hours.stream().filter(h-> h.compareTo(hour) > 0).collect(Collectors.toList());

    }
}
