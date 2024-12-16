package net.botwithus.rs3.cache.utils.loc;/* Class468 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.nio.ByteBuffer;

public class Class468 {
    public Class459 aClass459_5173;
    public Class471 aClass471_5175;
    Class459 aClass459_5176;

    public Class468(ByteBuffer class580_sub33, boolean bool) {
        method5533(class580_sub33, bool);
    }

    public Class468() {
        aClass471_5175 = new Class471();
        aClass459_5173 = new Class459();
        this.aClass459_5176 = new Class459(1.0F, 1.0F, 1.0F);
    }

    void method5528(ByteBuffer class580_sub33) {
        int flags = class580_sub33.get() & 0xff;
        float f = 0.0F;
        float f_0_ = 0.0F;
        float f_1_ = 0.0F;
        float f_2_ = 1.0F;
        if ((flags & 0x1) != 0) {
            f = class580_sub33.getShort();
            f_0_ = class580_sub33.getShort();
            f_1_ = class580_sub33.getShort();
            f_2_ = class580_sub33.getShort();
        }
        aClass471_5175 = new Class471(f, f_0_, f_1_, f_2_);
        float f_3_ = 0.0F;
        float f_4_ = 0.0F;
        float f_5_ = 0.0F;
        if ((flags & 0x2) != 0)
            f_3_ = class580_sub33.getShort();
        if ((flags & 0x4) != 0)
            f_4_ = class580_sub33.getShort();
        if ((flags & 0x8) != 0)
            f_5_ = class580_sub33.getShort();
        aClass459_5173 = new Class459(f_3_, f_4_, f_5_);
        float f_6_ = 1.0F;
        float f_7_ = 1.0F;
        float f_8_ = 1.0F;
        if ((flags & 0x10) != 0) {
            float f_9_ = class580_sub33.getShort();
            f_6_ = f_7_ = f_8_ = f_9_;
        } else {
            if ((flags & 0x20) != 0)
                f_6_ = class580_sub33.getShort();
            if ((flags & 0x40) != 0)
                f_7_ = class580_sub33.getShort();
            if ((flags & 0x80) != 0)
                f_8_ = class580_sub33.getShort();
        }
        this.aClass459_5176 = new Class459(f_6_, f_7_, f_8_);
    }

    public String toString() {
        return "[" + aClass471_5175.toString() + "|" + aClass459_5173.toString() + "|" + this.aClass459_5176.toString() + "]";
    }

    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (object instanceof Class468 class468_12_) {
            return (aClass471_5175.equals(class468_12_.aClass471_5175) && aClass459_5173.method5379(class468_12_.aClass459_5173) && (this.aClass459_5176.method5379(class468_12_.aClass459_5176)));
        }
        return false;
    }

    void method5533(ByteBuffer class580_sub33, boolean bool) {
        if (bool)
            method5528(class580_sub33);
        else {
            aClass471_5175 = new Class471(class580_sub33);
            aClass459_5173 = new Class459(class580_sub33);
            this.aClass459_5176 = new Class459(class580_sub33);
        }
    }

}
