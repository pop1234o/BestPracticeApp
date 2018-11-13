package com.liyafeng.video.practice.f_opengl_es;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.liyafeng.video.practice.f_opengl_es.shape.Square;
import com.liyafeng.video.practice.f_opengl_es.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer{

    private Triangle mTriangle;
    private Square mSquare;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
//        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
// Redraw background color
//        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mTriangle.draw();
    }

    /**
     * 加载shader
     * Vertex Shader - OpenGL ES graphics code for rendering the vertices of a shape.
     * Fragment Shader - OpenGL ES code for rendering the face of a shape with colors or textures.
     *
     *
     *
     * @param type
     * @param shaderCode
     * @return
     */
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
