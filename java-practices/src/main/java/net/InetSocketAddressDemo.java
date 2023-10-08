package net;

import org.apache.hadoop.net.NetUtils;
import sun.net.spi.nameservice.NameService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public class InetSocketAddressDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(InetAddress.getLocalHost());

        InetSocketAddress addr1 = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 8088);
        System.out.println(addr1);
        InetSocketAddress addr2 = NetUtils.getConnectAddress(addr1);
        System.out.println(addr2);
        InetAddress addr3 = addr2.getAddress();
        System.out.println(addr3);
        Class clazz = Class.forName("java.net.InetAddress");
        Field nsField = clazz.getDeclaredField("nameServices");
        nsField.setAccessible(true);
        List<Object> nsList = (List<Object>) nsField.get(addr3);
        for (Object item : nsList) {
            NameService ns = (NameService) item;
            System.out.println(ns.getClass());
            System.out.println(ns.getHostByAddr(addr3.getAddress()));
        }
        System.out.println(addr3.getCanonicalHostName());
        System.out.println(addr3.getHostName());

        Field implField = clazz.getDeclaredField("impl");
        implField.setAccessible(true);
        Object impl = implField.get(addr3);
        System.out.println(impl.getClass());
        Method getHostMethod = Class.forName(String.valueOf(impl.getClass()).split("\\s+")[1]).getMethod("getHostByAddr", Class.forName("[B"));
        getHostMethod.setAccessible(true);
        System.out.println(getHostMethod.invoke(impl, addr3.getAddress()));
    }
}
