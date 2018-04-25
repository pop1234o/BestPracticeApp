package com.liyafeng.practice.basic;

/**
 * Created by liyafeng on 2018/4/25.
 */

public class InitFlow {


    /**
     * PersistentDataBlockService: not able to find package com.google.android.gms
     * android.content.pm.PackageManager$NameNotFoundException: com.google.android.gms
     * at android.app.ApplicationPackageManager.getPackageUid(ApplicationPackageManager.java:231)
     * at com.android.server.PersistentDataBlockService.getAllowedUid(PersistentDataBlockService.java:96)
     * at com.android.server.PersistentDataBlockService.<init>(PersistentDataBlockService.java:87)
     * at java.lang.reflect.Constructor.newInstance(Native Method)
     * at com.android.server.SystemServiceManager.startService(SystemServiceManager.java:89)
     * at com.android.server.SystemServer.startOtherServices(SystemServer.java:637)
     * at com.android.server.SystemServer.run(SystemServer.java:283)
     * at com.android.server.SystemServer.main(SystemServer.java:175)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)
     * <p>
     * ========================================================
     * <p>
     * <p>
     * Unable to load component info ResolveInfo{8790e63 com.tools.cit/.NFCTest m=0x108000}
     * org.xmlpull.v1.XmlPullParserException: No android.nfc.action.TECH_DISCOVERED meta-data
     * at com.android.nfc.RegisteredComponentCache.parseComponentInfo(RegisteredComponentCache.java:186)
     * at com.android.nfc.RegisteredComponentCache.generateComponentsList(RegisteredComponentCache.java:161)
     * at com.android.nfc.RegisteredComponentCache.<init>(RegisteredComponentCache.java:61)
     * at com.android.nfc.NfcDispatcher.<init>(NfcDispatcher.java:90)
     * at com.android.nfc.NfcService.<init>(NfcService.java:818)
     * at com.android.nfc.NfcApplication.onCreate(NfcApplication.java:61)
     * at android.app.Instrumentation.callApplicationOnCreate(Instrumentation.java:1014)
     * at android.app.ActivityThread.handleBindApplication(ActivityThread.java:4707)
     * at android.app.ActivityThread.access$1600(ActivityThread.java:150)
     * at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1405)
     * at android.os.Handler.dispatchMessage(Handler.java:102)
     * at android.os.Looper.loop(Looper.java:148)
     * at android.app.ActivityThread.main(ActivityThread.java:5417)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
