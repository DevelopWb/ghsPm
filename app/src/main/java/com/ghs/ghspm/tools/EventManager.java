package com.ghs.ghspm.tools;

import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Pencilso on 2017/7/22.
 */

public class EventManager {
    private static final EventBus libraryEvent = EventBus.builder().build();

    /**
     * 蓝牙依赖内部的EventBus
     *
     * @return
     */
    public static EventBus getLibraryEvent() {
        return libraryEvent;
    }

    public static void removeAllEvent() {
        if (libraryEvent != null) libraryEvent.removeAllStickyEvents();
    }



    public static void sendStringMsg(String str) {
        libraryEvent.post(str);
    }
    public static void sendImage(ImageView iv) {
        libraryEvent.post(iv);
    }
}

