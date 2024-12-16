package net.botwithus.rs3.cache.assets.maps;/* Class471 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.nio.ByteBuffer;

public final class Class471 {
    static int anInt5186;
    public float aFloat5187;
    static Class471[] aClass471Array5188 = new Class471[0];
    static int anInt5189;
    public float aFloat5190;
    public float aFloat5191;
    public float aFloat5192;

    public Class471(ByteBuffer class580Sub33) {
        aFloat5187 = class580Sub33.getFloat();
        aFloat5190 = class580Sub33.getFloat();
        aFloat5191 = class580Sub33.getFloat();
        aFloat5192 = class580Sub33.getFloat();
    }

    public static Class471 method5541() {
        synchronized (aClass471Array5188) {
            if (anInt5186 == 0) {
                Class471 class471 = new Class471();
                return class471;
            }
            aClass471Array5188[--anInt5186].method5552();
            Class471 class471 = aClass471Array5188[anInt5186];
            return class471;
        }
    }

    static Class471 method5542(float f, float f_0_, float f_1_, float f_2_) {
        synchronized (aClass471Array5188) {
            if (anInt5186 == 0) {
                Class471 class471 = new Class471(f, f_0_, f_1_, f_2_);
                return class471;
            }
            aClass471Array5188[--anInt5186].method5554(f, f_0_, f_1_, f_2_);
            Class471 class471 = aClass471Array5188[anInt5186];
            return class471;
        }
    }

    public static Class471 method5543(Class471 class471) {
        synchronized (aClass471Array5188) {
            if (anInt5186 == 0) {
                Class471 class471_3_ = new Class471(class471);
                return class471_3_;
            }
            aClass471Array5188[--anInt5186].method5548(class471);
            Class471 class471_4_ = aClass471Array5188[anInt5186];
            return class471_4_;
        }
    }

    public final void method5544() {
        aFloat5187 = -aFloat5187;
        aFloat5190 = -aFloat5190;
        aFloat5191 = -aFloat5191;
    }

    public Class471() {
        method5552();
    }

    public Class471(float f, float f_5_, float f_6_, float f_7_) {
        method5554(f, f_5_, f_6_, f_7_);
    }

    public Class471(Class471 class471_8_) {
        method5548(class471_8_);
    }

    public final void method5545() {
        aFloat5187 = -aFloat5187;
        aFloat5190 = -aFloat5190;
        aFloat5191 = -aFloat5191;
    }

    public void method5547() {
        synchronized (aClass471Array5188) {
            if (anInt5186 < anInt5189 - 1)
                aClass471Array5188[anInt5186++] = this;
        }
    }

    public void method5548(Class471 class471_9_) {
        aFloat5187 = class471_9_.aFloat5187;
        aFloat5190 = class471_9_.aFloat5190;
        aFloat5191 = class471_9_.aFloat5191;
        aFloat5192 = class471_9_.aFloat5192;
    }

    public void method5549(Class459 class459, float f) {
        method5550(class459.aFloat5109, class459.aFloat5112, class459.aFloat5113, f);
    }

    public void method5550(float f, float f_10_, float f_11_, float f_12_) {
        float f_13_ = (float) Math.sin((double) (f_12_ * 0.5F));
        float f_14_ = (float) Math.cos((double) (f_12_ * 0.5F));
        aFloat5187 = f * f_13_;
        aFloat5190 = f_10_ * f_13_;
        aFloat5191 = f_11_ * f_13_;
        aFloat5192 = f_14_;
    }

    public static void method5551(int i) {
        anInt5189 = i;
        aClass471Array5188 = new Class471[i];
        anInt5186 = 0;
    }

    final void method5552() {
        aFloat5191 = 0.0F;
        aFloat5190 = 0.0F;
        aFloat5187 = 0.0F;
        aFloat5192 = 1.0F;
    }

    void method5554(float f, float f_15_, float f_16_, float f_17_) {
        aFloat5187 = f;
        aFloat5190 = f_15_;
        aFloat5191 = f_16_;
        aFloat5192 = f_17_;
    }

    public static final Class471 method5555(Class471 class471) {
        Class471 class471_18_ = method5543(class471);
        class471_18_.method5545();
        return class471_18_;
    }

    public void method5558(float f, float f_20_, float f_21_) {
        method5550(0.0F, 1.0F, 0.0F, f);
        Class471 class471_22_ = method5541();
        class471_22_.method5550(1.0F, 0.0F, 0.0F, f_20_);
        method5577(class471_22_);
        class471_22_.method5550(0.0F, 0.0F, 1.0F, f_21_);
        method5577(class471_22_);
        class471_22_.method5547();
    }

    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (object instanceof Class471) {
            Class471 class471_29_ = (Class471) object;
            return (aFloat5187 == class471_29_.aFloat5187 && aFloat5190 == class471_29_.aFloat5190 && aFloat5191 == class471_29_.aFloat5191 && aFloat5192 == class471_29_.aFloat5192);
        }
        return false;
    }

    public String toString() {
        return new StringBuilder().append(aFloat5187).append(",").append(aFloat5190).append(",").append(aFloat5191).append(",").append(aFloat5192).toString();
    }

    static {
        new Class471();
    }

    public boolean method5564(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (object instanceof Class471) {
            Class471 class471_30_ = (Class471) object;
            return (aFloat5187 == class471_30_.aFloat5187 && aFloat5190 == class471_30_.aFloat5190 && aFloat5191 == class471_30_.aFloat5191 && aFloat5192 == class471_30_.aFloat5192);
        }
        return false;
    }

    public String method5565() {
        return new StringBuilder().append(aFloat5187).append(",").append(aFloat5190).append(",").append(aFloat5191).append(",").append(aFloat5192).toString();
    }

    public Class471(float f, float f_31_, float f_32_) {
        method5558(f, f_31_, f_32_);
    }

    final void method5567() {
        aFloat5191 = 0.0F;
        aFloat5190 = 0.0F;
        aFloat5187 = 0.0F;
        aFloat5192 = 1.0F;
    }

    final void method5568() {
        aFloat5191 = 0.0F;
        aFloat5190 = 0.0F;
        aFloat5187 = 0.0F;
        aFloat5192 = 1.0F;
    }

    final void method5569() {
        aFloat5191 = 0.0F;
        aFloat5190 = 0.0F;
        aFloat5187 = 0.0F;
        aFloat5192 = 1.0F;
    }

    final void method5570() {
        aFloat5187 = -aFloat5187;
        aFloat5190 = -aFloat5190;
        aFloat5191 = -aFloat5191;
        aFloat5192 = -aFloat5192;
    }

    final void method5571() {
        aFloat5187 = -aFloat5187;
        aFloat5190 = -aFloat5190;
        aFloat5191 = -aFloat5191;
        aFloat5192 = -aFloat5192;
    }

    final void method5572() {
        aFloat5187 = -aFloat5187;
        aFloat5190 = -aFloat5190;
        aFloat5191 = -aFloat5191;
        aFloat5192 = -aFloat5192;
    }

    public final void method5573() {
        aFloat5187 = -aFloat5187;
        aFloat5190 = -aFloat5190;
        aFloat5191 = -aFloat5191;
    }

    public void method5575() {
        synchronized (aClass471Array5188) {
            if (anInt5186 < anInt5189 - 1)
                aClass471Array5188[anInt5186++] = this;
        }
    }

    final void method5576() {
        aFloat5187 = -aFloat5187;
        aFloat5190 = -aFloat5190;
        aFloat5191 = -aFloat5191;
        aFloat5192 = -aFloat5192;
    }

    public final void method5577(Class471 class471_34_) {
        method5554((class471_34_.aFloat5192 * aFloat5187 + class471_34_.aFloat5187 * aFloat5192 + class471_34_.aFloat5190 * aFloat5191 - class471_34_.aFloat5191 * aFloat5190), (class471_34_.aFloat5192 * aFloat5190 - class471_34_.aFloat5187 * aFloat5191 + class471_34_.aFloat5190 * aFloat5192 + class471_34_.aFloat5191 * aFloat5187), (class471_34_.aFloat5192 * aFloat5191 + class471_34_.aFloat5187 * aFloat5190 - class471_34_.aFloat5190 * aFloat5187 + class471_34_.aFloat5191 * aFloat5192), (class471_34_.aFloat5192 * aFloat5192 - class471_34_.aFloat5187 * aFloat5187 - class471_34_.aFloat5190 * aFloat5190 - class471_34_.aFloat5191 * aFloat5191));
    }

    final void method5578(float f) {
        aFloat5187 *= f;
        aFloat5190 *= f;
        aFloat5191 *= f;
        aFloat5192 *= f;
    }
}
