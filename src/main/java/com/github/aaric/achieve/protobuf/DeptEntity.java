package com.github.aaric.achieve.protobuf;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * DeptEntity
 *
 * @author Aaric, created on 2018-05-07T17:41.
 * @since 0.2.0-SNAPSHOT
 */
public class DeptEntity {

    private Integer id;
    private String name;

    public DeptEntity() {
    }

    public DeptEntity(Integer id, String name) {
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
