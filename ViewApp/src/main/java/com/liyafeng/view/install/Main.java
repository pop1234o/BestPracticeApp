package com.liyafeng.view.install;

public class Main {

    /**
     * 通过intent安装apk
     * 使用Intent安装APK方法(兼容Android N)
     * <p>
     * Android N之前，通过Intent安装一个APK代码差多不是下面这个样子的:
     * <p>
     * Intent install = new Intent(Intent.ACTION_VIEW);
     * install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     * File apkFile = new File(Environment.getExternalStorageDirectory() + "/download/" + "app.apk";
     * install.setDataAndType(Uri.fromFile(apkFile)), "application/vnd.android.package-archive");
     * startActivity(install)
     * 上面代码在Android N(API 26)之前是没有问题的，但是从Android 7.0开始，系统修改了安全机制: 限定应用在默认情况下只能访问自身应用数据。所以当我们想通过File对象访问其它package数据时，就需要借助于ContentProvider、FileProvider这些组件，否则会报FileUriExposedException异常。
     * <p>
     * 相应的，显然上面的代码在Android N中会有FileUriExposedException的问题，所以我们需要借助FileProvider做出下面的修改。
     * <p>
     * 使用FileProvider的步骤
     * <p>
     * 添加Provider到AndroidManifest.xml
     * <p>
     * <provider
     * android:name="android.support.v4.content.FileProvider"
     * android:authorities="应用包名.fileprovider"
     * android:exported="false"
     * android:grantUriPermissions="true">
     * <p>
     * <meta-data
     * android:name="android.support.FILE_PROVIDER_PATHS"
     * android:resource="@xml/file_paths"/>
     * <p>
     * </provider>
     * 创建@xml/file_paths对应的资源文件
     * <p>
     * <?xml version="1.0" encoding="utf-8"?>
     * <paths>
     * <external-path
     * name="download"
     * path="" />
     * <external-files-path
     * name="Download"
     * path="" />
     * </paths>
     * paths里面用来放你需要访问的非本应用路径，只有通过这里申明后，才能在程序中使用不报错。
     * <p>
     * files-path 对应 Context.getFilesDir()
     * cache-path 对应 getCacheDir()
     * external-path 对应 Environment.getExternalStorageDirectory()
     * external-files-path 对应 Context#getExternalFilesDir(String) Context.getExternalFilesDir(null)
     * external-cache-path 对应 Context.getExternalCacheDir()
     * 具体其中的path和name就需要你自己来根据需求确定了。
     * <p>
     * 应用代码
     * <p>
     * Intent install = new Intent(Intent.ACTION_VIEW);
     * install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     * File apkFile = new File(Environment.getExternalStorageDirectory() + "/download/" + "app.apk";
     * <p>
     * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
     * install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
     * Uri contentUri = FileProvider.getUriForFile(context, "应用报名.fileProvider", apkFile);
     * install.setDataAndType(contentUri, "application/vnd.android.package-archive");
     * } else {
     * install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
     * }
     * <p>
     * startActivity(install);
     *
     * @param args
     */
    public static void main(String[] args) {


    }

    /**
     * https://www.jianshu.com/p/85913ed97af5
     * 下载apk后安装apk适配6.0 7.0 8.0
     *
     * 8.0安装apk需要权限
     *  <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
     *  也可以在代码中申请
     *
     */
    public void a1(){}


    /**
     * FileProvider的使用方法
     * https://developer.android.google.cn/reference/android/support/v4/content/FileProvider
     * ===============
     * FileProvider is a special subclass of ContentProvider that
     * facilitates secure sharing of files associated with an app by creating
     * a content:// Uri for a file instead of a file:/// Uri.
     * 安全的多进程共享文件，用content:// 而不是file:///
     *
     * ========使用方法=========
     * 定义
     * <manifest>
     *     ...
     *     <application>
     *         ...
     *         <provider
     *             android:name="android.support.v4.content.FileProvider"
     *             android:authorities="com.mydomain.fileprovider" //你自己的域名
     *             android:exported="false"
     *             android:grantUriPermissions="true">
     *              <meta-data
     *                  android:name="android.support.FILE_PROVIDER_PATHS"
     *                  android:resource="@xml/file_paths" />
     *         </provider>
     *         ...
     *     </application>
     * </manifest>
     *
     * 在res下创建xml文件夹 ，定义 file_paths.xml 声明哪些文件夹（路径）是对外公开的
     * <paths xmlns:android="http://schemas.android.com/apk/res/android">
     *     <files-path name="my_images" path="images/"/>
     *     <files-path name="my_docs" path="docs/"/>
     *     path表明了files-path下的子目录
     *     ...
     * </paths>
     *
     * files-path 对应 Context.getFilesDir()
     * cache-path 对应 Context.getCacheDir()
     * external-path 对应 Environment.getExternalStorageDirectory()
     * external-files-path 对应 Context.getExternalFilesDir(String)
     * external-cache-path 对应 Context.getExternalCacheDir()
     *
     *
     * File imagePath = new File(Context.getFilesDir(), "images");
     * File newFile = new File(imagePath, "default_image.jpg");
     * Uri contentUri = getUriForFile(getContext(), "com.mydomain.fileprovider", newFile);
     *
     *  As a result of the previous snippet, getUriForFile() returns the content URI
     * content://com.mydomain.fileprovider/my_images/default_image.jpg.//my_images只是个名称
     *
     *
     */
    public void a2(){}
}
