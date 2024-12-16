package net.botwithus.logging;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public final class ConsoleHandler extends Handler implements Iterable<LogRecord> {

    private final Queue<LogRecord> logs;

    public ConsoleHandler() {
        this.logs = new ConcurrentLinkedQueue<>();
        setFormatter(new SimpleFormatter());
    }

    @Override
    public void publish(LogRecord record) {
        if (isLoggable(record)) {
            logs.add(record);
            if(logs.size() > 100) {
                logs.poll();
            }
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {
        logs.clear();
    }

    public Queue<LogRecord> getLogs() {
        return logs;
    }

    @Override
    public Iterator<LogRecord> iterator() {
        return logs.iterator();
    }
}
