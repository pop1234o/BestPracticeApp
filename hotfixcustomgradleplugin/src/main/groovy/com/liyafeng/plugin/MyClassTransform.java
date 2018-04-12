package com.liyafeng.plugin;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;

import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by liyafeng on 2018/4/12.
 */

public class MyClassTransform extends Transform {

    private final Project mProject;

    public MyClassTransform(Project p) {
        this.mProject = p;
    }

    @Override
    public String getName() {
        return "MyClassTransform";
    }

    /**
     * 表示要处理（转化）的数据类型
     * 还可以是RESOURCES DEX等类型
     *
     * @return
     */
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    /**
     * 表示处理对象的范围
     * EXTERNAL_LIBRARIES        只有外部库
     * PROJECT                       只有项目内容
     * PROJECT_LOCAL_DEPS            只有项目的本地依赖(本地jar)
     * PROVIDED_ONLY                 只提供本地或远程依赖项
     * SUB_PROJECTS              只有子项目。
     * SUB_PROJECTS_LOCAL_DEPS   只有子项目的本地依赖项(本地jar)。
     * TESTED_CODE                   由当前变量(包括依赖项)测试的代码
     *
     * @return
     */
    @Override
    public Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    /**
     * 表示是否支持增量编译
     * @return
     */
    @Override
    public boolean isIncremental() {
        return false;
    }

    /**
     *
     * @param context
     * @param inputs 输入流 两种格式，一种jar包格式，一种是目录格式
     * @param referencedInputs
     * @param outputProvider 获取输出目录，我们修改后的文件输入到这里
     * @param isIncremental
     * @throws IOException
     * @throws TransformException
     * @throws InterruptedException
     */
    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
    }
}
