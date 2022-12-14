/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.business.exceptions;

public final class SingletonInstanceException extends ScheduleManagerException{
    public SingletonInstanceException(String message){
        super(message);
    }
    public SingletonInstanceException(){
        super("Singleton Class Exception");
    }
}
