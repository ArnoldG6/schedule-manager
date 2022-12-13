/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.tools;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
public class HexColorGenerator {
    public static String generateHexColor(String text) throws Exception{
        try{
            if(text == null) return null;
            String hex256 = Hashing.sha256().hashString(text, StandardCharsets.UTF_8).toString();
            return String.format("#%s",hex256.substring(0,6));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
