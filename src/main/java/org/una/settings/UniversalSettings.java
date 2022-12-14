/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.settings;

public enum UniversalSettings {
    EMAIL_VALID_PATTERN("([!#-'*+/-9=?A-Z^-~-]+(\\.[!#-'*+/-9=?A-Z^-~-]+)*|\"([]!#-[^-~ \\t]|(\\\\[\\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\\.[!#-'*+/-9=?A-Z^-~-]+)*|\\[[\\t -Z^-~]*])"),
    BLOCK_1_ES("Ciclo I"),
    BLOCK_2_ES("Ciclo II"),
    BLOCK_3_ES("Ciclo III"),
    MONDAY_ES("Lunes"),
    TUESDAY_ES("Martes"),
    WEDNESDAY_ES("Miércoles"),
    THURSDAY_ES("Jueves"),
    FRIDAY_ES("Viernes"),
    APP_TITLE_ES("Gestor de Horarios-Registro UNA");
    /*("Lunes","Martes","Miércoles","Jueves","Viernes");*/
    public final String value;
    UniversalSettings(String value) {
        this.value = value;
    }
}
