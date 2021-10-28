package com.example.demo.configuration;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
@Component("idGenerator")
public class LongIdGenerator implements IdGenerator<Long> {
    private static AtomicInteger counter = new AtomicInteger(0);
    private static final int IP_RANDOM_INT;
    private static final int JVM_RANDOM_INT;
    private static final int LAST_RANDOM_INT;

    public LongIdGenerator() {
    }

    protected int getCount() {
        if (counter.get() < 0) {
            counter.set(0);
        }
        return counter.getAndIncrement();
    }

    private static int toInt(byte[] bytes) {
        int result = 0;

        for (int i = 0; i < 4; ++i) {
            result = (result << 8) - 128 + bytes[i];
        }

        return result;
    }

    @Override
    public Long generate() {
        long millisecond = System.currentTimeMillis();
        int count = LAST_RANDOM_INT + getCount();
        long id = (long) IP_RANDOM_INT * 0x2386F26FC10000L + millisecond * 100000L + (JVM_RANDOM_INT * 10000L) + (long) count;
        return id;
    }

    static {
        long jvm = System.currentTimeMillis();

        int ip;
        try {
            ip = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception var6) {
            ip = 0;
        }

        Random ipRandom = new Random(ip);
        Random jvmRandom = new Random(jvm);
        Random lastRandom = new Random();
        IP_RANDOM_INT = ipRandom.nextInt(800) + 100;
        JVM_RANDOM_INT = jvmRandom.nextInt(90) + 10;
        LAST_RANDOM_INT = lastRandom.nextInt(10000);
    }
}
