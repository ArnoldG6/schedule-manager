package org.una.tools;

import org.una.settings.UniversalSettings;

public class ScheduleTools {
    public static int translateDaysValue(String day){
        if(day == null) return 0;
        if(day.equalsIgnoreCase(UniversalSettings.MONDAY_ES.value))
            return 100;
        else if (day.equalsIgnoreCase(UniversalSettings.TUESDAY_ES.value))
            return 200;
        else if (day.equalsIgnoreCase(UniversalSettings.WEDNESDAY_ES.value))
            return 300;
        else if(day.equalsIgnoreCase(UniversalSettings.THURSDAY_ES.value))
            return 400;
        else if (day.equalsIgnoreCase(UniversalSettings.FRIDAY_ES.value))
            return 500;
        return 0;
    }
    public static int translateHoursValue(String hour){
        if(hour == null) return 0;
        String[] splitHourStr = hour.split(":");
        return Integer.parseInt(splitHourStr[0]);
    }
    public static int compareHourAndDaysValues(String day1,String day2,String hour1,String hour2 ){
        /*
              result.getAvailableSpaceStackPaneList().sort((o1, o2) -> {
            int o1DayValue = ScheduleTools.translateDaysValue(o1.getDay());
            int o2DayValue = ScheduleTools.translateDaysValue(o2.getDay());
            if (o1DayValue == o2DayValue) return 0;
            return o1DayValue < o2DayValue ? -1 : 1;
        });
        */
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
}
