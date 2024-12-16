package net.botwithus.rs3.cache.assets.maps;/* Class459 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.nio.ByteBuffer;

public class Class459 {

    public float aFloat5109;
    public float aFloat5112;
    public float aFloat5113;

    public Class459() {
        /* empty */
    }

    public Class459(float f, float f_2_, float f_3_) {
        aFloat5109 = f;
        aFloat5112 = f_2_;
        aFloat5113 = f_3_;
    }

    public Class459(ByteBuffer class580_sub33) {
        aFloat5109 = class580_sub33.getFloat();
        aFloat5112 = class580_sub33.getFloat();
        aFloat5113 = class580_sub33.getFloat();
    }

    public boolean method5379(Class459 class459_24_) {
        return aFloat5109 == class459_24_.aFloat5109 && aFloat5112 == class459_24_.aFloat5112 && aFloat5113 == class459_24_.aFloat5113;
    }

    public String toString() {
        return aFloat5109 + ", " + aFloat5112 + ", " + aFloat5113;
    }

}
