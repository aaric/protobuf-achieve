package com.github.aaric.achieve.protobuf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ProtobufPerformanceTest
 *
 * @author Aaric, created on 2018-05-07T17:37.
 * @since 0.2.0-SNAPSHOT
 */
public class ProtobufPerformanceTest {

    @Test
    public void testPerformance() throws Exception {
        long start;
        long count = 10000;

        // 1.protobuf
        DeptProto.Dept dept;
        DeptProto.DeptList.Builder deptListBuilder = DeptProto.DeptList.newBuilder();
        for (int i = 1; i <= count; i++) {
            dept = DeptProto.Dept.newBuilder()
                    .setId(i)
                    .setName(MessageFormat.format("dept{0,number,000000}", i))
                    .build();
            deptListBuilder.addDept(dept);
        }
        byte[] deptListBytes = deptListBuilder.build().toByteArray();
        start = Calendar.getInstance().getTimeInMillis();
        DeptProto.DeptList deptList = DeptProto.DeptList.parseFrom(deptListBytes);
        //System.out.println(deptList.getDeptList().size());
        System.out.println(String.format("1.protobuf: %d, %dByte", Calendar.getInstance().getTimeInMillis() - start, deptListBytes.length));

        // json
        String result1, result2;
        List<DeptEntity> deptEntityList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            deptEntityList.add(new DeptEntity(i, MessageFormat.format("dept{0,number,000000}", i)));
        }
        result1 = new Gson().toJson(deptEntityList);
        result2 = JSON.toJSONString(deptEntityList);

        // 2.gson
        start = Calendar.getInstance().getTimeInMillis();
        List<DeptEntity> deptEntityList1 = new Gson().fromJson(result1, new TypeToken<List<DeptEntity>>() {
        }.getType());
        //System.out.println(deptEntityList1.size());
        System.out.println(String.format("2.gson: %d, %dByte", Calendar.getInstance().getTimeInMillis() - start, result1.getBytes().length));

        // 3.fastjson
        start = Calendar.getInstance().getTimeInMillis();
        List<DeptEntity> deptEntityList2 = JSON.parseObject(result2, new TypeReference<List<DeptEntity>>() {
        }.getType());
        //System.out.println(deptEntityList2.size());
        System.out.println(String.format("3.fastjson: %d, %dByte", Calendar.getInstance().getTimeInMillis() - start, result2.getBytes().length));

        // performance:
        // protobuf > gson > fastjson
    }
}
