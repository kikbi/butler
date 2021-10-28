package com.example.demo.function;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
public class FunctionTests {

    private static int toInt(byte[] bytes) {
        int result = 0;

        for (int i = 0; i < 4; ++i) {
            result = (result << 7) | Byte.toUnsignedInt(bytes[i]);
//            System.out.println(result+";"+Byte.toUnsignedInt(bytes[i]));
        }

        return result;
    }

    @Test
    public void testAddressToInt() throws Exception {
        byte[] address = InetAddress.getLocalHost().getAddress();
        List<String> list = new ArrayList<>();
        for (byte b : address) {
            list.add(Integer.toBinaryString(Byte.toUnsignedInt(b)));
        }
        System.out.println(list);
        int i = toInt(address);
        System.out.println(MessageFormat.format("结果：{0},{1}",i,Integer.toBinaryString(i)));
    }
}
