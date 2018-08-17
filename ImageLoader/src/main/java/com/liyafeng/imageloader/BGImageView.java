package com.liyafeng.imageloader;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 这个类可以根据宽高来请求图片
 */
public class BGImageView extends SimpleDraweeView {

    private PipelineDraweeControllerBuilder controllerBuilder;

    public BGImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init();
    }

    public BGImageView(Context context) {
        super(context);
        init();
    }

    public BGImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BGImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BGImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        controllerBuilder = Fresco.newDraweeControllerBuilder();
    }


    public void setImageURI(String uri, int width, int height) {

        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri));
        if (width > 0 && height > 0) {
            builder.setResizeOptions(new ResizeOptions(width, height));
        }
        ImageRequest request = builder.build();
        DraweeController controller = controllerBuilder
                .setImageRequest(request)
                .setOldController(getController())
                .build();
        setController(controller);


//        DraweeController controller =
//                controllerBuilder
//                        .setCallerContext(null)
//                        .setUri(uri)
//
//                        .setOldController(getController())
//                        .build();
//        setController(controller);

    }
}
