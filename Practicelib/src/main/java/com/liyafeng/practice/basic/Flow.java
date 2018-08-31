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


    /**
     * at com.liyafeng.MainActivity.onCreate(MainActivity.java:41)
     * at android.app.Activity.performCreate(Activity.java:6102)
     * at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1106)
     * at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2280)
     * at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2389)
     * at android.app.ActivityThread.access$800(ActivityThread.java:151)
     * at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1305)
     * at android.os.Handler.dispatchMessage(Handler.java:102)
     * at android.os.Looper.loop(Looper.java:135)
     * at android.app.ActivityThread.main(ActivityThread.java:5271)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at java.lang.reflect.Method.invoke(Method.java:372)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:902)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:697)
     */
    void startActivityFlow() {

        //新的进程都是从zygote中fork出来的，ZygoteInit有socket接受消息
        //收到消息后创建新的进程，代码从ZygoteInit中开始，然后创建ActivityThread
        //里面接受AMS的启动页面消息，收到消息后创建Activity对象
        //通过WMS来将视图显示

    }

    /**
     * at com.pinzhikeji.snack.mobile.ui.dialog.MobileNumberDialog$3.onClick(MobileNumberDialog.java:106)
     * at android.view.View.performClick(View.java:4781)
     * at android.view.View$PerformClick.run(View.java:19874)
     * at android.os.Handler.handleCallback(Handler.java:739)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:135)
     * at android.app.ActivityThread.main(ActivityThread.java:5271)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at java.lang.reflect.Method.invoke(Method.java:372)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:902)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:697)
     */
    void clickFlow() {


    }


    /**
     * at com.pinzhikeji.snack.view.fragment.orderdish.OrderDishFragment.onCreateView(OrderDishFragment.java:362)
     * at android.support.v4.app.Fragment.performCreateView(Fragment.java:2080)
     * at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1108)
     * at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1290)
     * at android.support.v4.app.BackStackRecord.run(BackStackRecord.java:801)
     * at android.support.v4.app.FragmentManagerImpl.execPendingActions(FragmentManager.java:1677)
     * at android.support.v4.app.FragmentController.execPendingActions(FragmentController.java:388)
     * at android.support.v4.app.FragmentActivity.onStart(FragmentActivity.java:604)
     * at android.support.v7.app.AppCompatActivity.onStart(AppCompatActivity.java:178)
     * at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1238)
     * at android.app.Activity.performStart(Activity.java:6302)
     * at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2379)
     * at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2476) 
     * at android.app.ActivityThread.access$900(ActivityThread.java:150) 
     * at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1344) 
     * at android.os.Handler.dispatchMessage(Handler.java:102) 
     * at android.os.Looper.loop(Looper.java:148) 
     * at android.app.ActivityThread.main(ActivityThread.java:5417) 
     * at java.lang.reflect.Method.invoke(Native Method) 
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726) 
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616) 
     */
    void fragmentFlow() {

    }

    /**
     * Process: com.liyafeng.view, PID: 10301
     * java.lang.OutOfMemoryError: pthread_create (1040KB stack) failed: Try again
     * at java.lang.Thread.nativeCreate(Native Method)
     * at java.lang.Thread.start(Thread.java:729)
     * at com.liyafeng.view.MainActivity.onCreate(MainActivity.java:149)
     * at android.app.Activity.performCreate(Activity.java:6861)
     * at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1119)
     * at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2693)
     * at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2801)
     * at android.app.ActivityThread.-wrap12(ActivityThread.java)
     * at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1548)
     * at android.os.Handler.dispatchMessage(Handler.java:102)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.app.ActivityThread.main(ActivityThread.java:6365)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:901)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:791)
     */
    void oomFlow() {

    }

    /**
     * java.lang.NullPointerException: Attempt to get length of null array
     * at com.liyafeng.view.camera.CameraActivity$1$2.onPictureTaken(CameraActivity.java:56)
     * at android.hardware.Camera$EventHandler.handleMessage(Camera.java:1175)
     * at android.os.Handler.dispatchMessage(Handler.java:102)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.app.ActivityThread.main(ActivityThread.java:6365)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:901)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:791)
     */
    void camareFlow() {
    }


    /**
     * WindowAnimator: Failed to dispatch window animation state change.
     * android.os.DeadObjectException
     * at android.os.BinderProxy.transactNative(Native Method)
     * at android.os.BinderProxy.transact(Binder.java:503)
     * at android.view.IWindow$Stub$Proxy.onAnimationStarted(IWindow.java:520)
     * at com.android.server.wm.WindowAnimator.updateWindowsLocked(WindowAnimator.java:282)
     * at com.android.server.wm.WindowAnimator.animateLocked(WindowAnimator.java:678)
     * at com.android.server.wm.WindowAnimator.access$000(WindowAnimator.java:53)
     * at com.android.server.wm.WindowAnimator$1.doFrame(WindowAnimator.java:123)
     * at android.view.Choreographer$CallbackRecord.run(Choreographer.java:856)
     * at android.view.Choreographer.doCallbacks(Choreographer.java:670)
     * at android.view.Choreographer.doFrame(Choreographer.java:603)
     * at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:844)
     * at android.os.Handler.handleCallback(Handler.java:739)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:148)
     * at android.os.HandlerThread.run(HandlerThread.java:61)
     * at com.android.server.ServiceThread.run(ServiceThread.java:46)
     */
    void animatorFlow() {

    }


    /**
     * java.lang.IllegalStateException: Can't change tag of fragment NewsFragment{85e9cba #0 id=0x7f080058 news}: was news now flash
     * at android.support.v4.app.BackStackRecord.doAddOp(BackStackRecord.java:405)
     * at android.support.v4.app.BackStackRecord.replace(BackStackRecord.java:439)
     * at com.qusukj.baoguan.ui.activity.MainActivity.showFragment(MainActivity.java:109)
     * at com.qusukj.baoguan.ui.activity.MainActivity.access$700(MainActivity.java:23)
     * at com.qusukj.baoguan.ui.activity.MainActivity$1.onClick(MainActivity.java:165)
     * at android.view.View.performClick(View.java:5647)
     * at android.view.View$PerformClick.run(View.java:22462)
     * at android.os.Handler.handleCallback(Handler.java:754)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.app.ActivityThread.main(ActivityThread.java:6365)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:901)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:791)
     */
    void click() {

    }

    /**
     * 24.406 7774-7774/com.qusukj.baoguan E/XgStat: java.lang.IllegalArgumentException: No Retrofit annotation found. (parameter #1)
     * for method BaoGuanService.addComment
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:752)
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:743)
     * at retrofit2.ServiceMethod$Builder.parameterError(ServiceMethod.java:761)
     * at retrofit2.ServiceMethod$Builder.parseParameter(ServiceMethod.java:351)
     * at retrofit2.ServiceMethod$Builder.build(ServiceMethod.java:204)
     * at retrofit2.Retrofit.loadServiceMethod(Retrofit.java:170)
     * at retrofit2.Retrofit$1.invoke(Retrofit.java:147)
     * at java.lang.reflect.Proxy.invoke(Proxy.java:813)
     * at $Proxy0.addComment(Unknown Source)
     * at com.qusukj.baoguan.ui.activity.CommentActivity.commit(CommentActivity.java:101)
     * at com.qusukj.baoguan.ui.activity.CommentActivity.onClick(CommentActivity.java:80)
     * at
     */
    void retrofit() {

    }


    /**
     * Caused by: android.content.res.Resources$NotFoundException: File res/drawable/bg_rating_bar.xml from drawable resource ID #0x7f070066
     * at android.content.res.ResourcesImpl.loadDrawableForCookie(ResourcesImpl.java:748)
     * at android.content.res.ResourcesImpl.loadDrawable(ResourcesImpl.java:585)
     * at android.content.res.MiuiResourcesImpl.loadDrawable(MiuiResourcesImpl.java:308)
     * at android.content.res.Resources.loadDrawable(Resources.java:876)
     * at android.content.res.TypedArray.getDrawable(TypedArray.java:930)
     * at android.widget.ProgressBar.<init>(ProgressBar.java:283)
     * at android.widget.AbsSeekBar.<init>(AbsSeekBar.java:94)
     * at android.widget.RatingBar.<init>(RatingBar.java:88)
     * at android.widget.RatingBar.<init>(RatingBar.java:84)
     * at android.support.v7.widget.AppCompatRatingBar.<init>(AppCompatRatingBar.java:45)
     * at android.support.v7.widget.AppCompatRatingBar.<init>(AppCompatRatingBar.java:41)
     * at android.support.v7.app.AppCompatViewInflater.createView(AppCompatViewInflater.java:136)
     * at android.support.v7.app.AppCompatDelegateImplV9.createView(AppCompatDelegateImplV9.java:1029)
     * at android.support.v7.app.AppCompatDelegateImplV9.onCreateView(AppCompatDelegateImplV9.java:1087)
     * at android.support.v4.view.LayoutInflaterCompatHC$FactoryWrapperHC.onCreateView(LayoutInflaterCompatHC.java:47)
     * at android.view.LayoutInflater.createViewFromTag(LayoutInflater.java:794)
     * at android.view.LayoutInflater.createViewFromTag(LayoutInflater.java:752)
     * at android.view.LayoutInflater.rInflate(LayoutInflater.java:883)
     * at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:846)
     * at android.view.LayoutInflater.inflate(LayoutInflater.java:522)
     * at android.view.LayoutInflater.inflate(LayoutInflater.java:430)
     * at com.qusukj.baoguan.ui.adapter.FlashListAdapter.onCreateViewHolder(FlashListAdapter.java:92)
     * at com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter.onCreateViewHolder(LRecyclerViewAdapter.java:166)
     * at android.support.v7.widget.RecyclerView$Adapter.createViewHolder(RecyclerView.java:6365)
     * at android.support.v7.widget.RecyclerView$Recycler.tryGetViewHolderForPositionByDeadline(RecyclerView.java:5563)
     * at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:5448)
     * at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:5444)
     * at android.support.v7.widget.LinearLayoutManager$LayoutState.next(LinearLayoutManager.java:2224)
     * at android.support.v7.widget.LinearLayoutManager.layoutChunk(LinearLayoutManager.java:1551)
     * at android.support.v7.widget.LinearLayoutManager.fill(LinearLayoutManager.java:1511)
     * at android.support.v7.widget.LinearLayoutManager.onLayoutChildren(LinearLayoutManager.java:595)
     * at android.support.v7.widget.RecyclerView.dispatchLayoutStep2(RecyclerView.java:3600)
     * at android.support.v7.widget.RecyclerView.dispatchLayout(RecyclerView.java:3329)
     * at android.support.v7.widget.RecyclerView.onLayout(RecyclerView.java:3867)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.RelativeLayout.onLayout(RelativeLayout.java:1079)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at com.android.internal.policy.DecorView.onLayout(DecorView.java:729)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.view.ViewRootImpl.performLayout(ViewRootImpl.java:2407)
     * at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2129)
     * at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1315)
     * at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6430)
     * at android.view.Choreographer$CallbackRecord.run(Choreographer.java:876)
     * at android.view.Choreographer.doCallbacks(Choreographer.java:688)
     * at android.view.Choreographer.doFrame(Choreographer.java:623)
     * at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:862)
     * at android.os.Handler.handleCallback(Handler.java:754)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.app.ActivityThread.main(ActivityThread.java:6365)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:901)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:791)
     */
    void recyclerview() {

    }


    /**
     * at retrofit2.adapter.rxjava2.BodyObservable$BodyObserver.onNext(BodyObservable.java:54)
     * at retrofit2.adapter.rxjava2.BodyObservable$BodyObserver.onNext(BodyObservable.java:37)
     * at retrofit2.adapter.rxjava2.CallEnqueueObservable$CallCallback.onResponse(CallEnqueueObservable.java:58)
     * at retrofit2.OkHttpCall$1.onResponse(OkHttpCall.java:123)
     * at okhttp3.RealCall$AsyncCall.execute(RealCall.java:153)
     * at okhttp3.internal.NamedRunnable.run(NamedRunnable.java:32)
     * at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1133)
     * at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:607)
     * at java.lang.Thread.run(Thread.java:760)
     */
    void retrofitrxjava() {

    }


    /**
     * 04:10.608 15019-15019/com.insurchain.insur_wallet E/XgStat: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.view.ViewGroup.addView(android.view.View)' on a null object reference
     * at com.insurchain.insur_wallet.feature.guess.activity.info.GuessInfoActivity.refreshData(GuessInfoActivity.kt:134)
     * at com.insurchain.insur_wallet.feature.guess.activity.info.GuessInfoPresenter$loadInfo$1.onSuccess(GuessInfoPresenter.kt:15)
     * at com.insurchain.insur_wallet.feature.guess.activity.info.GuessInfoPresenter$loadInfo$1.onSuccess(GuessInfoPresenter.kt:13)
     * at com.insurchain.insur_wallet.data.source.observer.HttpObserverV3.onNext(HttpObserverV3.kt:15)
     * at com.insurchain.insur_wallet.data.source.observer.HttpObserverV3.onNext(HttpObserverV3.kt:9)
     * at io.reactivex.internal.operators.observable.ObservableSubscribeOn$SubscribeOnObserver.onNext(ObservableSubscribeOn.java:58)
     * at io.reactivex.internal.operators.observable.ObservableScalarXMap$ScalarDisposable.run(ObservableScalarXMap.java:248)
     * at io.reactivex.internal.operators.observable.ObservableJust.subscribeActual(ObservableJust.java:35)
     * at io.reactivex.Observable.subscribe(Observable.java:11442)
     * at io.reactivex.internal.operators.observable.ObservableSubscribeOn$SubscribeTask.run(ObservableSubscribeOn.java:96)
     * at io.reactivex.android.schedulers.HandlerScheduler$ScheduledRunnable.run(HandlerScheduler.java:109)
     * at android.os.Handler.handleCallback(Handler.java:754)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:163)
     */
    void rxjava() {

    }


    /**
     * @FormUrlEncoded POST专用
     * 177-15177/com.insurchain.insur_wallet E/XgStat: java.lang.IllegalArgumentException: @Field parameters can only be used with form encoding. (parameter #2)
     * for method NewApiStores.likeComment
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:755)
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:746)
     * at retrofit2.ServiceMethod$Builder.parameterError(ServiceMethod.java:764)
     * at retrofit2.ServiceMethod$Builder.parseParameterAnnotation(ServiceMethod.java:536)
     * at retrofit2.ServiceMethod$Builder.parseParameter(ServiceMethod.java:339)
     * at retrofit2.ServiceMethod$Builder.build(ServiceMethod.java:207)
     * at retrofit2.Retrofit.loadServiceMethod(Retrofit.java:170)
     * at retrofit2.Retrofit$1.invoke(Retrofit.java:147)
     * at java.lang.reflect.Proxy.invoke(Proxy.java:813)
     * at $Proxy4.likeComment(Unknown Source)
     * at com.insurchain.insur_wallet.data.source.repository.NewApiStores$DefaultImpls.likeComment$default(NewApiStores.kt:64)
     * at com.insurchain.insur_wallet.data.source.repository.NewDataRepository.likeComment(NewDataRepository.kt:15)
     * at com.insurchain.insur_wallet.feature.guess.activity.info.GuessInfoListAdapter$CommentHolder$2.onClick(GuessInfoListAdapter.java:110)
     * at android.view.View.performClick(View.java:5647)
     * at android.view.View$PerformClick.run(View.java:22462)
     * at android.os.Handler.handleCallback(Handler.java:754)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.app.ActivityThread.main(ActivityThread.java:6365)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:901)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:791)
     * <p>
     * <p>
     * ================================
     * <p>
     * 17728-17728/com.insurchain.insur_wallet E/AndroidRuntime: FATAL EXCEPTION: main
     * Process: com.insurchain.insur_wallet, PID: 17728
     * java.lang.IllegalArgumentException: @Body parameters cannot be used with form or multi-part encoding. (parameter #1)
     * for method NewApiStores.likeComment
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:755)
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:746)
     * at retrofit2.ServiceMethod$Builder.parameterError(ServiceMethod.java:764)
     * at retrofit2.ServiceMethod$Builder.parseParameterAnnotation(ServiceMethod.java:704)
     * at retrofit2.ServiceMethod$Builder.parseParameter(ServiceMethod.java:339)
     * at retrofit2.ServiceMethod$Builder.build(ServiceMethod.java:207)
     * at retrofit2.Retrofit.loadServiceMethod(Retrofit.java:170)
     * at retrofit2.Retrofit$1.invoke(Retrofit.java:147)
     * at java.lang.reflect.Proxy.invoke(Proxy.java:813)
     * at $Proxy4.likeComment(Unknown Source)
     * at com.insurchain.insur_wallet.data.source.repository.NewApiStores$DefaultImpls.likeComment$default(NewApiStores.kt:65)
     * at com.insurchain.insur_wallet.data.source.repository.NewDataRepository.likeComment(NewDataRepository.kt:15)
     * at com.insurchain.insur_wallet.feature.guess.activity.info.GuessInfoListAdapter$CommentHolder$2.onClick(GuessInfoListAdapter.java:110)
     * at android.view.View.performClick(View.java:5647)
     * at android.view.View$PerformClick.run(View.java:22462)
     * at android.os.Handler.handleCallback(Handler.java:754)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.app.ActivityThread.main(ActivityThread.java:6365)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:901)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:791)
     * 08-24 20:12:56.149 17728-17728/com.insurchain.insur_wallet D/XgStat: [main(1): ProGuard:141] - has caught the following uncaught exception:
     * 08-24 20:12:56.150 17728-17728/com.insurchain.insur_wallet E/XgStat: java.lang.IllegalArgumentException: @Body parameters cannot be used with form or multi-part encoding. (parameter #1)
     * for method NewApiStores.likeComment
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:755)
     * at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:746)
     * at retrofit2.ServiceMethod$Builder.parameterError(ServiceMethod.java:764)
     * at retrofit2.ServiceMethod$Builder.parseParameterAnnotation(ServiceMethod.java:704)
     * at retrofit2.ServiceMethod$Builder.parseParameter(ServiceMethod.java:339)
     * at retrofit2.ServiceMethod$Builder.build(ServiceMethod.java:207)
     * at retrofit2.Retrofit.loadServiceMethod(Retrofit.java:170)
     * at retrofit2.Retrofit$1.invoke(Retrofit.java:147)
     * at java.lang.reflect.Proxy.invoke(Proxy.java:813)
     * at $Proxy4.likeComment(Unknown Source)
     * at com.insurchain.insur_wallet.data.source.repository.NewApiStores$DefaultImpls.likeComment$default(NewApiStores.kt:65)
     * at com.insurchain.insur_wallet.data.source.repository.NewDataRepository.likeComment(NewDataRepository.kt:15)
     * at com.insurchain.insur_wallet.feature.guess.activity.info.GuessInfoListAdapter$CommentHolder$2.onClick(GuessInfoListAdapter.java:110)
     * at android.view.View.performClick(View.java:5647)
     * at android.view.View$PerformClick.run(View.java:22462)
     * at android.os.Handler.handleCallback(Handler.java:754)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:163)
     */
    void retrofitFiel() {

    }


    /**
     * ndroid.text.SpannableStringInternal.checkRange(SpannableStringInternal.java:428)
     * at android.text.SpannableStringInternal.setSpan(SpannableStringInternal.java:155)
     * at android.text.SpannableString.setSpan(SpannableString.java:46)
     * at com.insurchain.insur_wallet.util.AppUtil.setLink(AppUtil.kt:242)
     * at com.insurchain.insur_wallet.feature.guess.adapter.GuessListAdapter.onBindViewHolder(GuessListAdapter.kt:75)
     * at com.insurchain.insur_wallet.feature.guess.adapter.GuessListAdapter.onBindViewHolder(GuessListAdapter.kt:28)
     * at com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter.onBindViewHolder(LRecyclerViewAdapter.java:179)
     * at com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter.onBindViewHolder(LRecyclerViewAdapter.java:210)
     * at android.support.v7.widget.RecyclerView$Adapter.bindViewHolder(RecyclerView.java:6714)
     * at android.support.v7.widget.RecyclerView$Recycler.tryBindViewHolderByDeadline(RecyclerView.java:5647)
     * at android.support.v7.widget.RecyclerView$Recycler.tryGetViewHolderForPositionByDeadline(RecyclerView.java:5913)
     * at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:5752)
     * at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:5748)
     * at android.support.v7.widget.LinearLayoutManager$LayoutState.next(LinearLayoutManager.java:2232)
     * at android.support.v7.widget.LinearLayoutManager.layoutChunk(LinearLayoutManager.java:1559)
     * at android.support.v7.widget.LinearLayoutManager.fill(LinearLayoutManager.java:1519)
     * at android.support.v7.widget.LinearLayoutManager.onLayoutChildren(LinearLayoutManager.java:614)
     * at android.support.v7.widget.RecyclerView.dispatchLayoutStep2(RecyclerView.java:3812)
     * at android.support.v7.widget.RecyclerView.dispatchLayout(RecyclerView.java:3529)
     * at android.support.v7.widget.RecyclerView.onLayout(RecyclerView.java:4082)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.RelativeLayout.onLayout(RelativeLayout.java:1079)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.support.v4.view.ViewPager.onLayout(ViewPager.java:1769)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.support.design.widget.HeaderScrollingViewBehavior.layoutChild(HeaderScrollingViewBehavior.java:132)
     * at android.support.design.widget.ViewOffsetBehavior.onLayoutChild(ViewOffsetBehavior.java:42)
     * at android.support.design.widget.AppBarLayout$ScrollingViewBehavior.onLayoutChild(AppBarLayout.java:1361)
     * at android.support.design.widget.CoordinatorLayout.onLayout(CoordinatorLayout.java:894)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.support.v4.widget.SwipeRefreshLayout.onLayout(SwipeRefreshLayout.java:606)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1741)
     * at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1585)
     * at android.widget.LinearLayout.onLayout(LinearLayout.java:1494)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
     * at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
     * at com.android.internal.policy.DecorView.onLayout(DecorView.java:729)
     * at android.view.View.layout(View.java:17663)
     * at android.view.ViewGroup.layout(ViewGroup.java:5577)
     * at android.view.ViewRootImpl.performLayout(ViewRootImpl.java:2407)
     * at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2129)
     * at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1315)
     * at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6430)
     * at android.view.Choreographer$CallbackRecord.run(Choreographer.java:876)
     * at android.view.Choreographer.doCallbacks(Choreographer.java:688)
     * at android.view.Choreographer.doFrame(Choreographer.java:623)
     * at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:862)
     * at android.os.Handler.handleCallback(Handler.java:754)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.app.ActivityThread.main(ActivityThread.java:6365)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.ZygoteInit$Met
     */
    void recyc() {

    }
}
