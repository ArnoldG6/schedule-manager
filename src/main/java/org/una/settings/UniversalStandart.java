/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.settings;

public enum UniversalStandart {
    EMAIL_VALID_PATTERN("([!#-'*+/-9=?A-Z^-~-]+(\\.[!#-'*+/-9=?A-Z^-~-]+)*|\"([]!#-[^-~ \\t]|(\\\\[\\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\\.[!#-'*+/-9=?A-Z^-~-]+)*|\\[[\\t -Z^-~]*])"),
    BLOCK_1_ES("CICLO I"),
    BLOCK_2_ES("CICLO II"),
    BLOCK_3_ES("CICLO III");
    public final String value;
    UniversalStandart(String value) {
        this.value = value;
    }
}
