package com.kasukusakura.kimiroyli.test;

import com.kasukusakura.kimiroyli.api.perm.PermissionContext;
import com.kasukusakura.kimiroyli.api.perm.StandardPermissions;

import java.io.File;
import java.util.Random;

public class Testing {
    public static void main(String[] args) throws Throwable {
        System.out.println("[TESTING] Executed");
        /*var allStackTraces = Thread.getAllStackTraces();
        for (var thread : allStackTraces.keySet()) {
            System.out.println(thread);
        }*/

        Testing.class.getDeclaredMethod("a").setAccessible(true);


        PermissionContext.permit(Testing.class.getModule(), StandardPermissions.PERMISSION_MANAGER);

        PermissionContext.currentContext().takePermissions(() -> {
            System.out.println(PermissionContext.currentContext());
            return null;
        });

        try {
            System.load(new File("A").getAbsolutePath());
        } catch (UnsatisfiedLinkError ignored) {
        }
        try {
            System.loadLibrary("A");
        } catch (UnsatisfiedLinkError ignored) {
        }

        Runtime.getRuntime().exit(0);
    }

    public static void a() {
        var a = new Random().nextBoolean();
        if (a) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
