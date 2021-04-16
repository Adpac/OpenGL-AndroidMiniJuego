package com.example.interaccion;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;
//figura de un chocolate
public class Figura4 {
    private float posx;
    private float posy;
    private float tamaño;
    private Rectangulo fondo;
    private boolean mostrarimg=false;
    private float verticescuadrado[]=new float[]{
            -0.8f,-0.8f,
            -0.8f,0.8f,
            0.8f,-0.8f,
            0.8f,0.8f,
    };
    private float vertices[] = new float[] {
            -0.4f+0.2f,-0.0f,
            0.4f,-0.2f,
            -0.4f,-1.2f,
            0.4f-0.2f,-1.4f,
    };
    private float colores[] = new float[] {
            83/255f, 42/255f, 36/255f, 1,
            83/255f, 42/255f, 36/255f, 1,
            83/255f, 42/255f, 36/255f, 1,
            83/255f, 42/255f, 36/255f, 1,
    };
    private short indices[] = new short [] {
            0,1,2,1,2,3
    };
    public void modtamaño(){
        for (int i=0; i<vertices.length; i++){
            if(i%2==0){
                vertices[i]=(vertices[i]*tamaño+posx);
            }else {
                vertices[i]=(vertices[i]*tamaño+posy+0.7f);
            }
        }
        for (int i=0; i<verticeslinea.length; i++){
            if(i%2==0){
                verticeslinea[i]=(verticeslinea[i]*tamaño+posx);
            }else {
                verticeslinea[i]=(verticeslinea[i]*tamaño+posy+0.7f);
            }
        }
        for (int i=0; i<verticescuadrado.length; i++){
            if(i%2==0){
                verticescuadrado[i]=(verticescuadrado[i]+posx);
            }else {
                verticescuadrado[i]=(verticescuadrado[i]+posy);
            }
        }
    }
    private float verticeslinea[] = new float[] {
            -0.4f+0.1f,-0.4f,
            0.4f-0.1f,-0.6f,
            -0.4f+0.1f,-0.7f,
            0.4f-0.1f,-0.9f,
            -0.4f+0.09f,-1.0f,
            0.4f-0.12f,-1.2f,
            -0.4f+0.4f,-0.0f-0.05f,
            -0.4f+0.2f,-1.2f-0.05f,
            -0.4f+0.6f,-0.0f-0.1f,
            -0.4f+0.4f,-1.2f-0.1f,
    };
    private short indicesl[] = new short [] {
            0,1,
            2,3,
            4,5,
            6,7,
            8,9
    };
    private float coloresl[] = new float[] {
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
            18/255f, 7/255f, 4/255f, 1, // 0
    };

    FloatBuffer bufVertices;
    FloatBuffer bufColores;
    ShortBuffer bufIndices;
    FloatBuffer bufVerticesl;
    FloatBuffer bufColoresl;
    ShortBuffer bufIndicesl;
    public Figura4(float posx, float posy,float tamaño){
        this.posx=posx;
        this.posy=posy;
        this.tamaño=tamaño;
        modtamaño();
        ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
        bufByte.order(ByteOrder.nativeOrder());
        bufVertices = bufByte.asFloatBuffer();
        bufVertices.put(vertices);
        bufVertices.rewind();
        bufByte = ByteBuffer.allocateDirect(colores.length * 4);
        bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden del byte nativo
        bufColores = bufByte.asFloatBuffer(); // Convierte de byte a float
        bufColores.put(colores);
        bufColores.rewind();
        bufByte = ByteBuffer.allocateDirect(indices.length * 2);
        bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
        bufIndices = bufByte.asShortBuffer(); // Convierte de byte a short
        bufIndices.put(indices);
        bufIndices.rewind();
        ByteBuffer bufBytel = ByteBuffer.allocateDirect(verticeslinea.length * 4);
        bufBytel.order(ByteOrder.nativeOrder()); // Utiliza el orden del byte nativo
        bufVerticesl = bufBytel.asFloatBuffer(); // Convierte de byte a float
        bufVerticesl.put(verticeslinea);
        bufVerticesl.rewind();
        bufBytel = ByteBuffer.allocateDirect(coloresl.length * 4);
        bufBytel.order(ByteOrder.nativeOrder()); // Utiliza el orden del byte nativo
        bufColoresl = bufBytel.asFloatBuffer(); // Convierte de byte a float
        bufColoresl.put(coloresl);
        bufColoresl.rewind();
        bufBytel = ByteBuffer.allocateDirect(indicesl.length * 2);
        bufBytel.order(ByteOrder.nativeOrder());
        bufIndicesl = bufBytel.asShortBuffer();
        bufIndicesl.put(indicesl);
        bufIndicesl.rewind();
        fondo=new Rectangulo(verticescuadrado);
    }

    public boolean tocofigura(float posacx, float posacy ){
        boolean retorno=false;
        if(puntoEstaDentroDelRectangulo(posacx,posacy,-0.8f+posx,-0.8f+posy,1.6f,1.6f)){
            if(mostrarimg==false){
                mostrarimg=true;
                retorno=true;
            }else {
                retorno=false;
            }
            return retorno;
        }else {
            return false;
        }
    }
    public boolean isMostrarimg() {
        return mostrarimg;
    }
    public void setMostrarimg(boolean mostrarimg) {
        this.mostrarimg = mostrarimg;
    }
    private boolean puntoEstaDentroDelRectangulo(float posx, float posy, float x,
                                                 float y, float ancho, float alto) {
        return ((x < posx) && (posx < (x + ancho)) && (y < posy) && posy < y + alto);
    }
    public void dibuja(GL10 gl) {
        fondo.dibuja(gl);
        if(mostrarimg){
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, bufColores);
            gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                    GL10.GL_UNSIGNED_SHORT, bufIndices);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVerticesl);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, bufColoresl);
            gl.glEnable(GL10.GL_LINE_SMOOTH);
            gl.glDrawElements(GL10.GL_LINES, indicesl.length,
                    GL10.GL_UNSIGNED_SHORT, bufIndicesl);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        }


    }
}
