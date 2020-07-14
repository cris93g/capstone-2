package gameFrontend;

import static gameHelpers.LibraryHelpers.*;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public class Drawing {
	private ArrayList<Button> buttonList;
	private TrueTypeFont font,nfont;
	private Font awtFont,normalFont;
	public Drawing() {
		buttonList= new ArrayList<Button>();
		awtFont= new Font("Times New Roman",Font.BOLD,50);
		normalFont= new Font("Times New Roman",Font.BOLD,24);
		font= new TrueTypeFont(awtFont,false);
		nfont= new TrueTypeFont(normalFont,false);
		
	}
	public void drawString(int x,int y,String text) {
		font.drawString(x, y, text);
	}
	public void normaldrawString(int x,int y,String text) {
		nfont.drawString(x, y, text);
	}
public void addButton(String name,String textureName,int x,int y) {
	buttonList.add(new Button(name,uploadImage(textureName),x,y));
}
public boolean isButtonClicked(String buttonName) {
	Button b = getButton(buttonName);
	float mouseY=HEIGHT-Mouse.getY()-1;
	if(Mouse.getX()>b.getX() && Mouse.getX()<b.getX()+b.getWidth() && mouseY >b.getY() && mouseY<b.getY()+b.getWidth()) {
		return true;
	}
	return false;
}

private Button getButton(String buttonName) {
	for(Button b: buttonList) {
		if(b.getName().equals(buttonName)) {
			return b;
		}
	}
	return null;
}
public void draw() {
	for (Button b : buttonList) {
		drawFourSidedImage(b.getTexture(),b.getX(),b.getY(),b.getWidth(),b.getHeight());
	}
}
}
