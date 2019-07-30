package com.liyafeng.practice.util;


class BitmapUtils {

    //将bitmap调整到指定大小
    public static Bitmap sizeBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {//这时候origin还有吗？
            origin.recycle();
        }
        return newBM;
    }

    //按比例缩放
    public static Bitmap scaleBitmap(Bitmap origin, float scale) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(scale, scale);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {//从中间截取一个正方形
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int cropWidth = w >= h ? h : w;// 裁切后所取的正方形区域边长

        return Bitmap.createBitmap(bitmap, (bitmap.getWidth() - cropWidth) / 2,
                (bitmap.getHeight() - cropWidth) / 2, cropWidth, cropWidth);
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {//把图片裁剪成圆形
        if (bitmap == null) {
            return null;
        }
        bitmap = cropBitmap(bitmap);//裁剪成正方形
        try {
            Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(circleBitmap);
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight()));
            float roundPx = 0.0f;
            roundPx = bitmap.getWidth();
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            final Rect src = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            canvas.drawBitmap(bitmap, src, rect, paint);
            return circleBitmap;
        } catch (Exception e) {
            return bitmap;
        }
    }

}