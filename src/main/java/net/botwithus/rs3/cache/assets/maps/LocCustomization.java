package net.botwithus.rs3.cache.assets.maps;

import java.nio.ByteBuffer;

public class LocCustomization {
    public int rotation;
    public int type;
    public Object aClass468_8484;

    LocCustomization(ByteBuffer class580_sub33, boolean bool) {
        this(class580_sub33, bool, true);
    }

    LocCustomization(ByteBuffer buffer, int i, boolean bool, boolean bool_0_) {
        boolean bool_1_ = (i & 0x80) != 0;
        if (!bool) {
            type = (i >> 2 & 0x1f);
            rotation = (i & 0x3);
        } else {
            int i_2_ = buffer.get() & 0xff;
            type = (i & 0x7f);
            rotation = i_2_;
        }
        aClass468_8484 = null;
        if (bool_1_)
            aClass468_8484 = new Class468(buffer, bool_0_);
    }

    public LocCustomization(ByteBuffer buffer) {
        this(buffer, false);
    }

    LocCustomization(ByteBuffer buffer, boolean bool, boolean bool_3_) {
        this(buffer, buffer.get() & 0xFF, bool, bool_3_);
    }
}
