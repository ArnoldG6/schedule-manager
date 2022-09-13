/**
 * @author ArnoldG6
 */
package org.una.exceptions;

public final class SingletonInstanceException extends ScheduleManagerException{
    public SingletonInstanceException(String message){
        super(message);
    }
    public SingletonInstanceException(){
        super("Singleton Class Exception");
    }
}
