package com.victoria.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.victoria.game.Frogger;
import com.victoria.game.try2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1100;
		
		config.height=700; 
		new LwjglApplication(new try2(), config);
	}
}
/*to do list:
 * pusheen floats away with clouds ((hard))
 * if hits bird--> dies check
 * life count check
 * end screen
 * start screen
 * animations...? (most likely no)

 * sound of jumping
 * jumps check
 * 
 *to fix:
 *end screen
 *watercollision()
 * 
 */
/* Rectangle rec = new Rectangle(100,50,20,20);
 * rec.contains(x,y);
 * rec.overlaps(rec2);
 * if(guy.getBoundingRectangle().overlaps(ball.getBoundingRectangle())); 
 * ^allows us to check if two objects overlap
 * if(x1+w1>x2 && x1<x2+w2 && y1+h1>y2 && y1<y2+h2); other way of checking
 * for overlap
 * 
 * ~at top~
 * ShapeRenderer shapeRenderer;
 * ~in create~
 * shapeRenderer = new ShapeRenderer();
 * ~in render~
 * shapeRenderer.begin(ShapeType.Filled);
 * shapeRenderer.setColor(new Color(0.5f,0.5f,0,1));
 * shapeRenderer.rect(200,50,20,20);
 * shapeRenderer.end();
 * GDx.input.isKeyJustPressed(Keys.space);
 * 
 * 
 * 
 * if(cb.createRect(cb.pic).overlaps(guy.getBoundingRectangle())== true){
				
				System.out.println(cb.toString());
				lost=false;
			}
			
			
		}
		/*
		if(lost==true && cy>=313){
			life-=1; //starts with 3 lives
			cx= 0; //if hit by enemy, they start at initial starting position
			cy=0;
			System.out.println(life);
			System.out.println("STRIKE!");
			lostSound.play();
			points-=25;yyy
		}
		
		
		
		
		/*
		for(cloud cb: cloudBoats){
			if(cy>=313 && checkCloudCollision(guy,cb)==false){
				lostSound.play();
				splashSound.play();
				if(life>0){
					life-=1;
					cx=0;
					cy=0;
				}
				
			}
		}*/
 


