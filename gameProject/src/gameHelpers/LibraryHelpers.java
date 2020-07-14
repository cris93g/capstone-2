package gameHelpers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
public class LibraryHelpers {
	public static final int WIDTH=1280,HEIGHT=960;
	
	public static void Start() {
		Display.setTitle("Game");
		try {
			//display screen
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//camera
		glOrtho(0,WIDTH,HEIGHT,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		//allows to draw textures to screen
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
	}
	//draws a 4 shape item
	public static void DrawQuad(float x,float y,float width,float height) {
		glBegin(GL_QUADS);
		glVertex2f(x,y);//top left
		glVertex2f(x+width,y);//top right
		glVertex2f(x+width,y+height);//bottom right
		glVertex2f(x,y+height);//bottom left
		glEnd();
	
	}
	
	public static boolean checkCollision(float x1,float y1,float width1,float height1,float x2,float y2,float width2,float height2) {
		if(x1+width1>x2 && x1<x2+width2 && y1 + height1 >y2 && y1 < y2+height2) {
			return true;
		}
		return false;
	}
	public static void drawFourSidedImage(Texture tex,float x,float y,float width,float height) {
		tex.bind();
		glTranslatef(x,y,0);
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(0,0);
		glTexCoord2f(1,0);
		glVertex2f(width,0);
		glTexCoord2f(1,1);
		glVertex2f(width,height);
		glTexCoord2f(0,1);
		glVertex2f(0,height);
		glEnd();
		glLoadIdentity();
			
	}
	public static void drawRotatingImage(Texture tex,float x,float y,float width,float height,float angle) {
		tex.bind();
		glTranslatef(x+width/2,y+height/2,0);
		glRotatef(angle,0,0,1);
		glTranslatef(-width/3,-height/2,0);
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(0,0);
		glTexCoord2f(1,0);
		glVertex2f(width,0);
		glTexCoord2f(1,1);
		glVertex2f(width,height);
		glTexCoord2f(0,1);
		glVertex2f(0,height);
		glEnd();
		glLoadIdentity();
			
	}
	
	public static Texture uploadTexture(String path,String fileType) {
		Texture tex=null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex=TextureLoader.getTexture(fileType,in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
	public static Texture uploadImage(String name) {
			Texture tex =null;
			tex=uploadTexture("assets/"+name +".png","ONG");
			return tex;
	}
	
}
