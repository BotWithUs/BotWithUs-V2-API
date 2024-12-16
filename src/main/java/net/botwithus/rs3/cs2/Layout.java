package net.botwithus.rs3.cs2;

import net.botwithus.rs3.interfaces.Component;
import net.botwithus.rs3.world.Coordinate;

import java.util.logging.Logger;

public enum Layout {
    INTEGER(Integer.class) {
        @Override
        public Object parse(Object value) {
            if (value instanceof Integer i) {
                return i;
            }
            if (value instanceof Long l) {
                return l.intValue();
            }
            if (value instanceof String s) {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    log.warning("Failed to parse integer: " + s);
                    return 0;
                }
            }
            return 0;
        }
    },
    LONG(Long.class) {
        @Override
        public Object parse(Object value) {
            if (value instanceof Long l) {
                return l;
            }
            if (value instanceof Integer i) {
                return (long) i;
            }
            if (value instanceof String s) {
                try {
                    return Long.parseLong(s);
                } catch (NumberFormatException e) {
                    log.warning("Failed to parse long: " + s);
                    return 0L;
                }
            }
            return 0L;
        }
    },
    STRING(String.class) {
        @Override
        public Object parse(Object value) {
            return value.toString();
        }
    },
    COMPONENT(Integer.class) {
        @Override
        public Object parse(Object value) {
            if (value instanceof Component c) {
                return c.getRoot().getInterfaceId() << 16 | c.getComponentId();
            }
            return null;
        }
    },
    COORDINATE(Integer.class) {
        @Override
        public Object parse(Object value) {
            if (value instanceof Coordinate c) {
                return (c.z() << 28) | (c.x() << 14) | c.y();
            }
            return 0;
        }
    };

    private final Class<?> base;

    Layout(Class<?> base) {
        this.base = base;
    }

    public Class<?> base() {
        return base;
    }

    public boolean is(Object value) {
        return base.isInstance(value);
    }

    public abstract Object parse(Object value);


    private static final Logger log = Logger.getLogger(Layout.class.getName());
}
