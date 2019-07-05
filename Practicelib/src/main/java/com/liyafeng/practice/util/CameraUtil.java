package com.liyafeng.practice.util;



class  CameraUtil{
    /**
     * 比率相近，尺寸最大的
     *
     * 一般的手机都会提供 16:9 4:3 1:1 这几种preview尺寸
     * 所以我们的控件最好按这个来，保证不会变形
     *
     * @param camera
     * @param p
     * @param viewWidth
     * @param viewHeight
     * @return
     */
    public static Camera.Size findBestPreviewSize(Camera camera, Camera.Parameters p, int viewWidth, int viewHeight) {
        List<Camera.Size> supportedPreviewSizes = p.getSupportedPreviewSizes();

        return getBestSize(camera, viewWidth, viewHeight, supportedPreviewSizes);
    }


    public static Camera.Size findBestPictureSize(Camera camera, Camera.Parameters p, int viewWidth, int viewHeight, Camera.Size previewSize) {

        List<Camera.Size> supportedPreviewSizes = p.getSupportedPictureSizes();

        Collections.sort(supportedPreviewSizes, new Comparator<Camera.Size>() {
            @Override
            public int compare(Camera.Size lhs, Camera.Size rhs) {
                if (lhs.width == rhs.width) {
                    return 0;
                } else if (lhs.width > rhs.width) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        for (int i = 0; i < supportedPreviewSizes.size(); i++) {
            Camera.Size size = supportedPreviewSizes.get(i);
            if (size.width == previewSize.width && size.height == previewSize.height) {
                return size;
            }
        }

        return getBestSize(camera, viewWidth, viewHeight, supportedPreviewSizes);
    }


    private static Camera.Size getBestSize(Camera camera, int viewWidth, int viewHeight, List<Camera.Size> supportedPreviewSizes) {
        if (supportedPreviewSizes == null || supportedPreviewSizes.size() == 0) {
            return camera.new Size(1920, 1080);
        }
        Collections.sort(supportedPreviewSizes, new Comparator<Camera.Size>() {
            @Override
            public int compare(Camera.Size lhs, Camera.Size rhs) {
                if (lhs.width == rhs.width) {
                    return 0;
                } else if (lhs.width > rhs.width) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        float curRadio = viewHeight / viewWidth;

        for (int i = 0; i < supportedPreviewSizes.size(); i++) {
            Camera.Size size = supportedPreviewSizes.get(i);
            //找到第一个必须相近的的比率
            // 宽1920/ 高 1080
            int radio = size.width / size.height;
            if (Math.abs(curRadio - radio) < 0.3) {
                return size;
            }
        }
        return supportedPreviewSizes.get(0);
    }

}


