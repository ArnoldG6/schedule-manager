package org.una.tools;

import org.una.settings.UniversalSettings;

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
}
