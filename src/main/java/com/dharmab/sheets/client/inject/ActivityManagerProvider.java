package com.dharmab.sheets.client.inject;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import javax.inject.Provider;

public class ActivityManagerProvider implements Provider<ActivityManager> {
    @Inject
    private ActivityMapper activityMapper;
    @Inject
    private EventBus eventBus;

    @Override
    public ActivityManager get() {
        return new ActivityManager(activityMapper, eventBus);
    }
}
