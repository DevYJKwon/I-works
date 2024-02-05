package com.example.iworks.domain.notification.domain;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class NotificationId implements Serializable {
    private int notificationCategoryCode;
    private int notificationOwnerId;

    public NotificationId(int notificationCategoryCode, int notificationOwnerId) {
        this.notificationCategoryCode = notificationCategoryCode;
        this.notificationOwnerId = notificationOwnerId;
    }
    public NotificationId() {
    }
}