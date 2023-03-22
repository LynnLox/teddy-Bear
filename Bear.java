/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogl_ind;

/**
 *
 * @author lynn_
 */
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import com.jogamp.opengl.util.FPSAnimator;

public class Bear implements GLEventListener {
    private final GLU glu = new GLU();
    private float rot;

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0.3f, -3.0f); // translates the bear figure

        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[]{1f, 1f, 1f, 1}, 0);
// specular highlights 

        //face
        gl.glRotatef(rot, 0f, 1f, 0f); //rotates along y axis 
        gl.glColor3f(1, 1, 1);//white
        Sphere(gl, 0.3, 100, 10); 
        
        gl.glColor3f(0.92f, 0.78f, 0.62f);//brown
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[]{1f, 1f, 1f, 1}, 0);
        //two ears
        gl.glColor3f(0.92f, 0.78f, 0.62f);//brown
        gl.glTranslatef(0f, 0.15f, -0.25f);
        Sphere(gl, 0.10, 100, 10);
        gl.glTranslatef(0f, 0f, 0.5f);
        Sphere(gl, 0.12, 100, 10);
        //torso
        gl.glTranslatef(0f, -0.7f, -0.25f);
        Sphere(gl, 0.4, 100, 10);
        rot += 1f; //sets speed of rotation
        //arms and legs 
        gl.glColor3f(1, 1, 1);//white
        gl.glTranslatef(0f, 0.15f, -0.4f);
        Sphere(gl, 0.12, 100, 10);
        gl.glTranslatef(0f, 0f, 0.8f);
        Sphere(gl, 0.12, 100, 10);
        gl.glTranslatef(0f, -0.45f, -0.65f);
        Sphere(gl, 0.12, 100, 10);
        gl.glTranslatef(0f, 0f, 0.525f);
        Sphere(gl, 0.12, 100, 10);
        //
        gl.glColor3f(0, 0, 0);//black
        gl.glTranslatef(0.25f, 0.9f, -0.15f);
        Sphere(gl, 0.04, 100, 10);
        gl.glTranslatef(0f, 0, -0.25f);
        Sphere(gl, 0.04, 100, 10);
        //snout
        gl.glColor3f(0.92f, 0.78f, 0.62f);//brown
        gl.glTranslatef(0f,-0.11f,0.125f);
        Sphere(gl, 0.1, 100, 10);
        gl.glColor3f(0, 0, 0);//black
        gl.glTranslatef(0.04f, 0.06f, 0f);
        Sphere(gl, 0.04, 100, 10);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // method body
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 g1 = drawable.getGL().getGL2();
        g1.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        g1.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 16);
        g1.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_TRUE);
        g1.glEnable(GL2.GL_NORMALIZE);
        g1.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        final GL2 gl = drawable.getGL().getGL2();

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glClearColor(0.52F, 0.37F, 0.26F, 1);
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void Sphere(GL2 gl, double radius, int slices, int stacks) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        if (slices < 3) {
            throw new IllegalArgumentException("Number of slices must be at least 3.");
        }
        if (stacks < 2) {
            throw new IllegalArgumentException("Number of stacks must be at least 2.");
        }
        for (int j = 0; j < stacks; j++) {
            double latitude1 = (Math.PI / stacks) * j - Math.PI / 2;
            double latitude2 = (Math.PI / stacks) * (j + 1) - Math.PI / 2;
            double sinLat1 = Math.sin(latitude1);
            double cosLat1 = Math.cos(latitude1);
            double sinLat2 = Math.sin(latitude2);
            double cosLat2 = Math.cos(latitude2);

            gl.glBegin(GL2.GL_QUAD_STRIP);

            for (int i = 0; i <= slices; i++) {
                double longitude = (2 * Math.PI / slices) * i;
                double sinLong = Math.sin(longitude);
                double cosLong = Math.cos(longitude);
                double x1 = cosLong * cosLat1;
                double y1 = sinLong * cosLat1;
                double z1 = sinLat1;
                double x2 = cosLong * cosLat2;
                double y2 = sinLong * cosLat2;
                double z2 = sinLat2;
                gl.glNormal3d(x2, y2, z2);
                gl.glVertex3d(radius * x2, radius * y2, radius * z2);
                gl.glNormal3d(x1, y1, z1);
                gl.glVertex3d(radius * x1, radius * y1, radius * z1);
            }
            gl.glEnd();
        }
    }

    public static void main(String[] args)
    {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Bear r = new Bear();

        glcanvas.addGLEventListener(r);
        glcanvas.setSize(800, 800);

        final JFrame frame = new JFrame("Bear");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();
    }
}
