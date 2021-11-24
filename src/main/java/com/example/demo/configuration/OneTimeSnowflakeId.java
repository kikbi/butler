package com.example.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
public class OneTimeSnowflakeId implements GenId {
    private static final Logger logger = LoggerFactory.getLogger(OneTimeSnowflakeId.class);
    private Worker worker;
    private long startTime = _curSecond();
    private long time;
    private long segment = 0L;
    private long workerId = getWorker().getWorkerId();
    private long sequence = 0L;
    private static final long segmentBits = 9L;
    private static final long workerIdBits = 10L;
    private static final long sequenceBits = 13L;
    private static final long timestampShift = 32L;
    private static final long segmentShift = 23L;
    private static final long workerIdShift = 13L;
    private static final long sequenceMask = 8191L;
    private static final long maxSegment = 511L;
    private long twepoch = 1483200000L;
    private long _counter = 0L;

    public OneTimeSnowflakeId() {
        time = startTime - twepoch;
    }

    public Worker getWorker() {
        return (Worker) (worker == null ? new DefaultWorker() : worker);
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public synchronized long get() {
        long id = getNextId();
        testSpeedLimit();
        return id;
    }

    @Override
    public synchronized long[] getRangeId(int sizeOfIds) {
        long[] r = new long[]{getNextId(), 0L};
        sequence = sequence + (long) sizeOfIds - 1L - 1L;
        _counter = _counter + (long) sizeOfIds - 1L - 1L;
        if (sequence >> 13 > 0L) {
            sequence &= 8191L;
            if (segment >= 511L) {
                ++time;
                segment = 0L;
            } else {
                ++segment;
            }
        }

        r[1] = getNextId();
        testSpeedLimit();
        return r;
    }

    private long getNextId() {
        ++sequence;
        ++_counter;
        if (sequence >> 13 > 0L) {
            if (segment >= 511L) {
                ++time;
            } else {
                sequence = 0L;
                ++segment;
            }
        }

        return time << 32 | segment << 23 | workerId << 13 | sequence;
    }

    private long _curSecond() {
        return System.currentTimeMillis() / 1000L;
    }

    private synchronized void testSpeedLimit() {
        long spentTime = _curSecond() - startTime + 1L;
        if (spentTime <= 0L || spentTime << 22 <= _counter) {
            try {
                wait(10L);
                testSpeedLimit();
            } catch (Exception var4) {
                logger.error(var4.getMessage(), var4);
            }

        }
    }
}
