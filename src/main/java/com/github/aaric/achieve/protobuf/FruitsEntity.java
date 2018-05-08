package com.github.aaric.achieve.protobuf;

import io.protostuff.Tag;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * FruitsEntity
 *
 * @author Aaric, created on 2018-05-08T10:57.
 * @since 0.3.0-SNAPSHOT
 */
public class FruitsEntity {

    @Tag(1)
    private Integer id;

    @Tag(2)
    private String name;

    public FruitsEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
