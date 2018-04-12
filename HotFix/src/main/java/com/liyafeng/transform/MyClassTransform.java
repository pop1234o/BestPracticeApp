package com.liyafeng.transform;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by liyafeng on 2018/4/12.
 */

public class MyClassTransform extends Transform {

    public MyClassTransform() {
    }

    @Override
    public String getName() {
        return "MyClassTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<QualifiedContent.Scope> getScopes() {
        return null;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {

    }
}
