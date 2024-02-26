package com.texoit.goldenraspberryawardsapi.application.core.domain.studio;

import java.util.UUID;

public class Studio {

    private UUID id;
    private String name;

    public Studio() {
    }

    public Studio(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Studio(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
