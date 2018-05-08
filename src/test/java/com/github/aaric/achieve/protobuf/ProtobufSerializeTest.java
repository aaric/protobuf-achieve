package com.github.aaric.achieve.protobuf;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.HashSet;
import java.util.Set;

/**
 * ProtobufSerializeTest
 *
 * @author Aaric, created on 2018-05-08T09:20.
 * @since 0.3.0-SNAPSHOT
 */
public class ProtobufSerializeTest {

    private BananaEntity bananaEntity;

    @Before
    public void begin() {
        bananaEntity = new BananaEntity(1, "banana", 1234L);
        Set<String> bursting = new HashSet<>();
        bursting.add("triangle");
        bursting.add("square");
        bursting.add("polygon");
        bananaEntity.setBursting(bursting);
    }

    @Test
    public void testSerialize() {
        // 0801120662616E616E61 --> 1,2
        // 0801120662616E616E6120D209 --> 1,2,4
        // 0801120662616E616E611A067371756172651A07706F6C79676F6E1A08747269616E676C6520D209 --> 1,2,3,4
        byte[] data = ProtobufIOUtil.toByteArray(bananaEntity, RuntimeSchema.createFrom(BananaEntity.class), LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        System.out.println(DatatypeConverter.printHexBinary(data));
        Assert.assertNotNull(data);
    }

    @Test
    public void testDeserialize() {
        RuntimeSchema<BananaEntity> schema = RuntimeSchema.createFrom(BananaEntity.class);
        BananaEntity object = schema.newMessage();
        ProtobufIOUtil.mergeFrom(DatatypeConverter.parseHexBinary("0801120662616E616E6120D209"), object, schema);
        System.out.println(object);
        Assert.assertNotNull(object);
    }

    @Test
    public void testProtobufSerialize() {
        byte[] data = ProtobufSerializeUtils.serialize(bananaEntity, BananaEntity.class);
        System.out.println(DatatypeConverter.printHexBinary(data));
        Assert.assertNotNull(data);
    }

    @Test
    public void testProtobufDeserialize() {
        byte[] data = DatatypeConverter.parseHexBinary("0801120662616E616E61");
        BananaEntity bananaEntity2 = ProtobufSerializeUtils.deserialize(data, BananaEntity.class);
        System.out.println(bananaEntity2);
        Assert.assertNotNull(bananaEntity2);
    }

    /**
     * ProtobufSerializeUtils
     */
    private static class ProtobufSerializeUtils {

        public static <T> byte[] serialize(T object, Class<T> clazz) {
            return ProtobufIOUtil.toByteArray(object, RuntimeSchema.createFrom(clazz), LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        }

        public static <T> T deserialize(byte[] data, Class<T> clazz) {
            RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
            T object = schema.newMessage();
            ProtobufIOUtil.mergeFrom(data, object, schema);
            return object;
        }
    }
}
