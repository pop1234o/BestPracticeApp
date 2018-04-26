package com.liyafeng.practice.basic;

/**
 * Created by liyafeng on 2018/4/25.
 */

public class Flow {


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
     * <p>
     * =======================================================
     * android.os.DeadObjectException
     * at android.os.BinderProxy.transactNative(Native Method)
     * at android.os.BinderProxy.transact(Binder.java:503)
     * at android.nfc.INfcAdapter$Stub$Proxy.setForegroundDispatch(INfcAdapter.java:525)
     * at android.nfc.NfcAdapter.disableForegroundDispatchInternal(NfcAdapter.java:1245)
     * at android.nfc.NfcAdapter.disableForegroundDispatch(NfcAdapter.java:1233)
     * at com.pinzhikeji.snack.MainActivity.onPause(MainActivity.java:1934)
     * at android.app.Activity.performPause(Activity.java:6397)
     * at android.app.Instrumentation.callActivityOnPause(Instrumentation.java:1312)
     * at android.app.ActivityThread.performPauseActivity(ActivityThread.java:3367)
     * at android.app.ActivityThread.performPauseActivity(ActivityThread.java:3340)
     * at android.app.ActivityThread.handlePauseActivity(ActivityThread.java:3315)
     * at android.app.ActivityThread.access$1100(ActivityThread.java:150)
     * at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1355)
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

    /**
     * at com.pinzhikeji.snack.adapter.RemarksAdapter.onBindViewHolder(RemarksAdapter.java:65)
     * at com.pinzhikeji.snack.adapter.RemarksAdapter.onBindViewHolder(RemarksAdapter.java:24)
     * at android.support.v7.widget.RecyclerView$Adapter.onBindViewHolder(RecyclerView.java:5825)
     * at android.support.v7.widget.RecyclerView$Adapter.bindViewHolder(RecyclerView.java:5858)
     * at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:5094)
     * at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:4970)
     * at android.support.v7.widget.LinearLayoutManager$LayoutState.next(LinearLayoutManager.java:2029)
     * at android.support.v7.widget.GridLayoutManager.layoutChunk(GridLayoutManager.java:541)
     * at android.support.v7.widget.LinearLayoutManager.fill(LinearLayoutManager.java:1377)
     * at android.support.v7.widget.LinearLayoutManager.onLayoutChildren(LinearLayoutManager.java:578)
     * at android.support.v7.widget.GridLayoutManager.onLayoutChildren(GridLayoutManager.java:170)
     * at android.support.v7.widget.RecyclerView.dispatchLayoutStep2(RecyclerView.java:3315)
     * at android.support.v7.widget.RecyclerView.onMeasure(RecyclerView.java:2843)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:5956)
     * at android.widget.LinearLayout.measureChildBeforeLayout(LinearLayout.java:1465)
     * at android.widget.LinearLayout.measureVertical(LinearLayout.java:748)
     * at android.widget.LinearLayout.onMeasure(LinearLayout.java:630)
     * at android.view.View.measure(View.java:18813)
     * at android.widget.RelativeLayout.measureChildHorizontal(RelativeLayout.java:716)
     * at android.widget.RelativeLayout.onMeasure(RelativeLayout.java:462)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:5956)
     * at android.widget.FrameLayout.onMeasure(FrameLayout.java:194)
     * at android.view.View.measure(View.java:18813)
     * at android.widget.RelativeLayout.measureChildHorizontal(RelativeLayout.java:716)
     * at android.widget.RelativeLayout.onMeasure(RelativeLayout.java:462)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:5956)
     * at android.widget.FrameLayout.onMeasure(FrameLayout.java:194)
     * at android.support.v7.widget.ContentFrameLayout.onMeasure(ContentFrameLayout.java:135)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:5956)
     * at android.widget.LinearLayout.measureChildBeforeLayout(LinearLayout.java:1465)
     * at android.widget.LinearLayout.measureVertical(LinearLayout.java:748)
     * at android.widget.LinearLayout.onMeasure(LinearLayout.java:630)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:5956)
     * at android.widget.FrameLayout.onMeasure(FrameLayout.java:194)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:5956)
     * at android.widget.LinearLayout.measureChildBeforeLayout(LinearLayout.java:1465)
     * at android.widget.LinearLayout.measureVertical(LinearLayout.java:748)
     * at android.widget.LinearLayout.onMeasure(LinearLayout.java:630)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:5956)
     * at android.widget.FrameLayout.onMeasure(FrameLayout.java:194)
     * at com.android.internal.policy.PhoneWindow$DecorView.onMeasure(PhoneWindow.java:2643)
     * at android.view.View.measure(View.java:18813)
     * at android.view.ViewRootImpl.performMeasure(ViewRootImpl.java:2100)
     * at android.view.ViewRootImpl.measureHierarchy(ViewRootImpl.java:1216)
     * at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:1452)
     * at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1107)
     * at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6013)
     * at android.view.Choreogr
     */
    public void measureFlow() {

    }
}
