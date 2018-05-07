package com.github.aaric.achieve.protobuf;

import com.google.protobuf.Timestamp;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;

/**
 * ProtobufTest
 *
 * @author Aaric, created on 2018-04-28T17:18.
 * @since 0.0.1-SNAPSHOT
 */
public class ProtobufTest {

    @Test
    public void testToByteArray() {
        DeptProto.Dept dept1 = DeptProto.Dept.newBuilder()
                .setId(1)
                .setName("dept1")
                .build();
        DeptProto.Dept dept2 = DeptProto.Dept.newBuilder()
                .setId(2)
                .setName("dept2")
                .build();
        long millis = Calendar.getInstance().getTimeInMillis();
        Timestamp.Builder current = Timestamp.newBuilder()
                .setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1000000));
        UserProto.User user = UserProto.User.newBuilder()
                .setId(1)
                .setUsername("Nick")
                .setPassword("123456")
                .addDepts(dept1)
                .addDepts(dept2)
                .setLastLoginTime(current)
                .build();
        System.out.println(user);
        byte[] userBytes = user.toByteArray();
        System.out.println(DatatypeConverter.printHexBinary(userBytes));
        Assert.assertNotNull(userBytes);
    }

    @Test
    public void testToObject() throws Exception {
        byte[] deptBytes = DatatypeConverter.parseHexBinary("080112044E69636B1A063132333435362A090801120564657074312A09080212056465707432");
        DeptProto.Dept dept = DeptProto.Dept.parseFrom(deptBytes);
        System.out.println(dept);
        Assert.assertNotNull(dept);
    }

    @Test
    public void testTimestamp() {
        long millis = Calendar.getInstance().getTimeInMillis();
        System.out.println(millis);
        Timestamp.Builder current = Timestamp.newBuilder()
                .setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1000000));
        System.out.println(current);
        Date date = new Date(current.getSeconds() * 1000 + current.getNanos() / 1000000);
        System.out.println(date.getTime());
    }
}
