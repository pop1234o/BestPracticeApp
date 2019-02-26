package com.liyafeng.video.practice.c_video_record;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.io.FileOutputStream;
import java.io.IOException;


class PixelYuvRgb
{
    public PixelYuvRgb()
    {
        Y = 0;
        Cb = 0;
        Cr = 0;
        R = 0;
        G = 0;
        B = 0;
    }

    public int Y, Cb, Cr;
    public int R, G, B;

    public byte y() { return (byte) Y;}

    public byte cb() {return (byte) Cb;}

    public byte cr() { return (byte) Cr;}

    public byte r() {return (byte) R;}

    public byte g() {return (byte) G;}

    public byte b() {return (byte) B;}

    public void setYUV(int y, int u, int v)
    {
        this.Y = y;
        this.Cb = u;
        this.Cr = v;
    }

    public void setYUV(byte y, byte u, byte v)
    {
        setYUV(y & 0xFF, u & 0xFF, v & 0xFF);
    }

    public void setRGB(int r, int g, int b)
    {
        this.R = r;
        this.G = g;
        this.B = b;
    }

    public void setRGB(byte r, byte g, byte b)
    {
        setRGB(r & 0xFF, g & 0xFF, b & 0xFF);
    }

    public byte[] getYUV()
    {
        return new byte[]{y(), cb(), cr()};
    }

    public byte[] getRGB()
    {
        return new byte[]{r(), g(), b()};
    }

    public void yuvToRgb()
    {
        /**
         * Formulae from Android src:
         */
        // R = (int) (Y + (1.370705 * (Cr - 128)));
        // G = (int) (Y - (0.698001 * (Cr - 128)) - (0.337633 * (Cb - 128)));
        // B = (int) (Y + (1.732446 * (Cb - 128)));

        /**
         * Formulae from fourcc:
         * R = Y + 1.402 (Cr-128)
         * G = Y - 0.34414 (Cb-128) - 0.71414 (Cr-128)
         * B = Y + 1.772 (Cb-128)
         */
        R = (int) Math.round(Y + 1.402 * (Cr - 128));
        G = (int) Math.round(Y - 0.34414 * (Cb - 128) - 0.71414 * (Cr - 128));
        B = (int) Math.round(Y + 1.772 * (Cb - 128));

        R = clamp(R, 0, 255);
        G = clamp(G, 0, 255);
        B = clamp(B, 0, 255);
    }

    public void rgbToYuv()
    {
        // TODO:
        // // see http://boofcv.org/javadoc/boofcv/alg/color/ColorYuv.html
    }

    private int clamp(int val, int minval, int maxval)
    {
        if (val < minval)
            return minval;
        else if (val > maxval)
            return maxval;
        else
            return val;
    }

    @Override
    public String toString()
    {
        return "PixelYuvRgb{" +
                "Y=" + Y +
                ", Cb=" + Cb +
                ", Cr=" + Cr +
                ", R=" + R +
                ", G=" + G +
                ", B=" + B +
                '}';
    }
}


// TODO: YUV_420_888
public class YuvUtils
{
    public YuvUtils()
    {
        pixel = new PixelYuvRgb();
    }

    // fields:
    private PixelYuvRgb pixel;


    // methods:
    public void saveBytesToFile(byte[] data, String path)
    {
        FileOutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(path);
            outputStream.write(data);
            outputStream.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @param yuv      src yuv
     * @param width    input width
     * @param height   input width
     * @param rotation 90,180,270
     * @return
     */
    public byte[] nv21RotateClockwise(final byte[] yuv,
                                      final int width,
                                      final int height,
                                      final int rotation)
    {
        // if (rotation == 0)
        //     return yuv;

        // if (rotation % 90 != 0 || rotation < 0 || rotation > 270)
        // {
        //     throw new IllegalArgumentException("0 <= rotation < 360, rotation % 90 == 0");
        // }

        final byte[] output = new byte[yuv.length];
        final int frameSize = width * height;
        final boolean swap = rotation % 180 != 0;
        final boolean xflip = rotation % 270 != 0;
        final boolean yflip = rotation >= 180;

        for (int j = 0; j < height; j++)
        {
            for (int i = 0; i < width; i++)
            {
                final int yIn = j * width + i;
                final int uIn = frameSize + (j >> 1) * width + (i & ~1);
                final int vIn = uIn + 1;

                final int wOut = swap ? height : width;
                final int hOut = swap ? width : height;
                final int iSwapped = swap ? j : i;
                final int jSwapped = swap ? i : j;
                final int iOut = xflip ? wOut - iSwapped - 1 : iSwapped;
                final int jOut = yflip ? hOut - jSwapped - 1 : jSwapped;

                final int yOut = jOut * wOut + iOut;
                final int uOut = frameSize + (jOut >> 1) * wOut + (iOut & ~1);
                final int vOut = uOut + 1;

                output[yOut] = (byte) (0xff & yuv[yIn]);
                output[uOut] = (byte) (0xff & yuv[uIn]);
                output[vOut] = (byte) (0xff & yuv[vIn]);
            }
        }
        return output;
    }

    /**
     * mirror nv21 data inplace
     * TODO: copy src data to dst data
     *
     * @param src
     * @param width
     * @param height
     * @return
     */
    public byte[] nv21MirrorLeftRight(byte[] src, int width, int height)
    {
        int i;
        int left, right;
        byte temp;
        int startPos = 0;

        // mirror Y
        for (i = 0; i < height; i++)
        {
            left = startPos;
            right = startPos + width - 1;
            while (left < right)
            {
                temp = src[left];
                src[left] = src[right];
                src[right] = temp;
                left++;
                right--;
            }
            startPos += width;
        }

        // mirror U and V
        int offset = width * height;
        startPos = 0;
        for (i = 0; i < height / 2; i++)
        {
            left = offset + startPos;
            right = offset + startPos + width - 2;
            while (left < right)
            {
                temp = src[left];
                src[left] = src[right];
                src[right] = temp;
                left++;
                right--;

                temp = src[left];
                src[left] = src[right];
                src[right] = temp;
                left++;
                right--;
            }
            startPos += width;
        }
        return src;
    }


    public byte[] nv21MirrorTopBottom(byte[] src, int width, int height)
    {
        return null;
    }

    private boolean isIntEven(int n)
    {
        return ((n & 0x01) == 0);
        // return n % 2 == 0;
    }

    public byte[] nv21Downsize(byte[] src, int srcWidth, int srcHeight,
                               int dstWidth, int dstHeight)
    {
        final int srcPixelNum = srcHeight * srcWidth;
        final int dstPixelNum = dstHeight * dstWidth;

        final double srcTopStep = ((double) srcHeight) / dstHeight;
        final double srcRightStep = ((double) srcWidth) / dstWidth;

        final int srcOrigOffsetTop = (int) ((srcHeight - dstHeight * srcTopStep) / 2);
        final int srcOrigOffsetRight = (int) ((srcWidth - dstWidth * srcRightStep) / 2);

        byte[] dst = new byte[(dstPixelNum * 3) >> 1];

        int srcTop, srcRight, srcYIdx, srcUIdx, srcVIdx;
        int dstTop, dstRight, dstYIdx, dstUIdx, dstVIdx;
        for (dstTop = 0; dstTop < dstHeight; dstTop++)
        {
            srcTop = (int) (srcOrigOffsetTop + dstTop * srcTopStep);
            for (dstRight = 0; dstRight < dstWidth; dstRight++)
            {
                srcRight = (int) (srcOrigOffsetRight + dstRight * srcRightStep);

                dstYIdx = dstTop * dstWidth + dstRight;
                dstVIdx = dstPixelNum + (dstTop >> 1) * dstWidth + (isIntEven(dstRight) ? dstRight : dstRight - 1);
                dstUIdx = dstVIdx + 1;

                srcYIdx = srcTop * srcWidth + srcRight;
                srcVIdx = srcPixelNum + (srcTop >> 1) * srcWidth + (isIntEven(srcRight) ? srcRight : srcRight - 1);
                srcUIdx = srcVIdx + 1;

                dst[dstYIdx] = src[srcYIdx];
                dst[dstVIdx] = src[srcVIdx];
                dst[dstUIdx] = src[srcUIdx];
            }
        }

        return dst;
    }

    public byte[] nv21ToRGB888(byte[] src, int width, int height)
    {
        int pixelNum = width * height;
        byte[] rgb = new byte[pixelNum * 3];

        int yIdx, uIdx, vIdx;
        int rgbIdx;
        for (int top = 0; top < height; top++)
        {
            for (int right = 0; right < width; right++)
            {
                yIdx = top * width + right;
                vIdx = pixelNum + (top >> 1) * width + (isIntEven(right) ? right : right - 1);
                uIdx = vIdx + 1;
                rgbIdx = yIdx * 3;
                pixel.setYUV(src[yIdx], src[uIdx], src[vIdx]);
                pixel.yuvToRgb();
                rgb[rgbIdx] = pixel.r();
                rgb[rgbIdx + 1] = pixel.g();
                rgb[rgbIdx + 2] = pixel.b();
            }
        }

        return rgb;
    }

    // public ByteBuffer nv21ToByteBuffer(byte[] src, int width, int height)
    // {
    //     int pixels = width * height;
    //
    //     ByteBuffer bbuf = null;
    //     bbuf = ByteBuffer.allocateDirect(1 /*DIM_BATCH_SIZE*/
    //             * pixels
    //             * 3 /*DIM_PIXEL_SIZE*/
    //             * 4 /*getNumBytesPerChannel()*/);
    //     bbuf.order(ByteOrder.nativeOrder());
    //
    //     PixelYuvRgb pix = new PixelYuvRgb();
    //
    //
    //     return bbuf;
    //
    // }


    public byte[] nv21CropArea(byte[] src, int srcWidth, int srcHeight,
                               int dstStartX, int dstStartY, int dstWidth, int dstHeight)
    {
        return null;
    }

    public Bitmap byteRGBToBitmap(byte[] bytes, int w, int h)
    {
        /*
         * bytes = w * h * 3
         */
        int pixelNum = bytes.length / 3;
        int[] tmpRGB = new int[pixelNum];
        for (int i = 0; i < pixelNum; i++)
        {
            int r = bytes[3 * i] & 0xFF;
            int g = bytes[3 * i + 1] & 0xFF;
            int b = bytes[3 * i + 2] & 0xFF;
            tmpRGB[i] = Color.rgb(r, g, b);
        }
        Bitmap bmp = Bitmap.createBitmap(tmpRGB, w, h, Bitmap.Config.ARGB_8888);
        return bmp;
    }

}
