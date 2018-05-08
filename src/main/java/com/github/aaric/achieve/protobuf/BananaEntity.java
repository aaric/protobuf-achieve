package com.github.aaric.achieve.protobuf;

import io.protostuff.Tag;

import java.util.Set;

/**
 * BananaEntity
 *
 * @author Aaric, created on 2018-05-08T10:21.
 * @since 0.3.0-SNAPSHOT
 */
public class BananaEntity extends FruitsEntity {

    @Tag(3)
    private Set<String> bursting;

    @Tag(4)
    private Long yellow;

    public BananaEntity(Integer id, String name, Long yellow) {
        super(id, name);
        this.yellow = yellow;
    }

    public Set<String> getBursting() {
        return bursting;
    }

    public void setBursting(Set<String> bursting) {
        this.bursting = bursting;
    }

    public Long getYellow() {
        return yellow;
    }

    public void setYellow(Long yellow) {
        this.yellow = yellow;
    }
}
