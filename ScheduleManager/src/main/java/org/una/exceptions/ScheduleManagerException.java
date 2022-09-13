/**
 * @author ArnoldG6
 */
package org.una.exceptions;

public class ScheduleManagerException extends Exception {
/*
    ScheduleManagerException class is the base class for
    specific ScheduleManager-child exceptions.
 */
    ScheduleManagerException(){
        super();
    }
    ScheduleManagerException(String message){
        super(message);
    }
    ScheduleManagerException(String message, Throwable cause){
        super(message,cause);
    }
    ScheduleManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }
    ScheduleManagerException(Throwable cause){
        super(cause);
    }
}
