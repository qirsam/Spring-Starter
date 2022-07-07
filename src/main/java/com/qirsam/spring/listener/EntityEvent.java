package com.qirsam.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.EventObject;

public class EntityEvent extends ApplicationEvent {

    private final AccessType accessType;

    public EntityEvent(Object entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }

    public AccessType getAccessType() {
        return accessType;
    }
}
