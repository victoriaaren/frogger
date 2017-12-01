/* Victoria Ren
 * This program is a game based off of the classic Frogger
 * The main character (pusheen) is to hop across obstacles such as dangerous birds
 * and use clouds as boats to cross a "river"/water cloud. Pusheen is to reach the end
 * and land on four main pillows to complete the game. The code uses multiple functions, and mostly 3 classes*
 * for game play.*/
package com.victoria.game;
//import features
import java.util.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class try2 extends ApplicationAdapter implements InputProcessor {
	//sounds and music loading
	Music music;
	Sound jumpSound;
	Sound lostSound;
	Sound splashSound;
	Sound failSound;
	Sound winSound;
	Sound arriveSound;
	
	//important values to be kept throughout the game
	int life=3; //3 lives
	int points=0; //points
	int numCloud; //counter of clouds reached
	
//ArrayLists to store birds/clouds for the game
//as well as sprite images for images that appear more than once
	private static ArrayList<bird> enemies;
	private static ArrayList<cloud> cloudBoats;
	private static ArrayList<Sprite>endPillow;
	private static ArrayList<Sprite>finPil;
	
	//set up textures and sprites for pictures
	SpriteBatch batch;
	Texture cat;
	Texture b;
	Texture pCloud;
	Texture pCloud2;
	Texture rCloud;
	Texture wCloud;
	Texture wind;
	Texture pillowB;
	Texture pillowA;
	Texture loseScreen;
	Texture winScreen;
	Texture life1;
	Texture life2;
	Texture life3;
	
	Texture bb;
	Texture e2;
	Texture e3;
	
	Texture c1;
	Texture c2;
	Texture c3;
	Texture c4;
	
	//sprites
	Sprite guy;
	Sprite back;
	Sprite pC;
	Sprite pC2;
	Sprite rC;
	Sprite wC;
	Sprite w;
	Sprite pB4;
	Sprite pA;
	Sprite lS;
	Sprite wS;
	Sprite l1;
	Sprite l2;
	Sprite l3;
	
	//bird enemies
	bird birdy;
	bird birdy2;
	bird birdy3;
	
	//cloud boats
	cloud cloudy;
	cloud cloudy2;
	cloud cloudy3;
	cloud cloudy4;
	
	//pusheen's starting position!
	int cx=0;
	int cy=0;

	@Override
	public void create () {
		//reset it up and define/load files
		Gdx.input.setInputProcessor(this);
		//music
		music= Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
		jumpSound= Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
		lostSound= Gdx.audio.newSound(Gdx.files.internal("lost.mp3"));
		failSound= Gdx.audio.newSound(Gdx.files.internal("fail.mp3"));
		splashSound= Gdx.audio.newSound(Gdx.files.internal("splash.wav"));
		winSound= Gdx.audio.newSound(Gdx.files.internal("finish.wav"));
		arriveSound= Gdx.audio.newSound(Gdx.files.internal("arrive.mp3"));
		
		//arraylists
		enemies= new ArrayList<bird>();
		cloudBoats= new ArrayList<cloud>();
		endPillow= new ArrayList<Sprite>();
		finPil= new ArrayList<Sprite>();	
		
		//loading images
		batch = new SpriteBatch();
		b = new Texture("bground.jpg");
		back= new Sprite(b);
		pCloud= new Texture("pinkCloud.psd");
		pC= new Sprite(pCloud);
		pCloud2= new Texture("pinkCloud.psd");
		pC2= new Sprite(pCloud2);
		rCloud= new Texture("roadCloud.psd");
		rC= new Sprite(rCloud);
		wCloud= new Texture("waterCloud.png");
		wC= new Sprite(wCloud);
		loseScreen= new Texture("loseScreen.png");
		lS= new Sprite(loseScreen);
		winScreen= new Texture("winScreen.png");
		wS= new Sprite(winScreen);
		life1= new Texture("1l.png");
		l1= new Sprite(life1);
		life2= new Texture("2l.png");
		l2= new Sprite(life2);
		life3= new Texture("3l.png");
		l3= new Sprite(life3);
		
		cat = new Texture("pusheen.psd");
		guy=new Sprite(cat);
		
		
	    bb=new Texture("bird1_1.psd");
		e2=new Texture("bird2_2.psd");		
		e3=new Texture("bird3_1.psd");
		
		c1= new Texture("cloud1.psd");
		c2= new Texture("cloud2.psd");
		c3= new Texture("cloud3.psd");
		c4= new Texture("cloud4.psd");
		
		//add to arraylist so there are multiple objects at corresponding locations
		for(int i=0; i<4; i++){
			pillowB= new Texture("pillowBefore.psd");
			pB4= new Sprite(pillowB);
			pB4.setPosition(200*(i+1), 570);
			endPillow.add(pB4);
		}
		//add to arraylist so there are multiple objects at corresponding locations
		for(int i=0; i<4; i++){
			pillowA= new Texture("pillowAfter.psd");
			pA= new Sprite(pillowA);
			pA.setPosition(-1000, -1000);
			finPil.add(pA);
		}
		//add to arraylist so there are multiple objects, at corresponding locations
		for(int i=0; i<5; i++){
			birdy= new bird(bb,i*250,63,5,1);	
			birdy2= new bird(e2,i*200,126,3,0); 
			birdy3= new bird(e3,i*200,192,4,1);
			enemies.add(birdy);
			enemies.add(birdy2);
			enemies.add(birdy3);
		}
		//add to arraylist so there are multiple objects
		for(int i=0; i<5; i++){
			cloudy = new cloud(c1,i*250,313,5,1);	
			cloudBoats.add(cloudy);
		}
		//add to arraylist so there are multiple objects
		for(int i=0; i<4; i++){
			cloudy2= new cloud(c2,i*300,510,10,0);
			cloudBoats.add(cloudy2);
			cloudy3= new cloud(c3,i*300,440,5,0);
			cloudBoats.add(cloudy3);
		}
		//add to arraylist so there are multiple objects
		for(int i=0; i<3; i++){
			cloudy4= new cloud(c4,i*400,380,15,1);
			cloudBoats.add(cloudy4);
		}
		//createRect acts as getBoundingRectangle() and creates a rect around a texture
		for(bird en: enemies){
			en.createRect(en.pic);
		}
		//set them in a place where set will not interfere with the game beforehand
		lS.setPosition(-20000, -20000);
		wS.setPosition(-20000, -20000);
		//lives
		l1.setPosition(-1000,-1000);
		l2.setPosition(-1000, -1000);
		
		//background music
		music.play();
	}

	@Override
	public void render () {
		/*keyboard input--> for all the movements of the cat*/
			//KeyJustPressed()--> meaning you press it once then it jumps
			//so that the cat does not endlessly drift
		if(Gdx.input.isKeyJustPressed(Keys.LEFT) && ((cx)>=61)){ //border so pusheen doesn't jump into the void
			cx-=61;
			jumpSound.play(); //play the sound for every jump
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT) && cx<1035){ //border
			cx+=61;
			jumpSound.play();
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP) && cy<=635){ //border
			cy+=63;
			jumpSound.play();
			points+=50;
			System.out.println(points);
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN) && (cy>=7)){ //border
			cy-=63;
			jumpSound.play();
			points+=10;
			System.out.println(points);
		}
		
		//move function made in bird/cloud classes
		//for loop makes all the birds/clouds move simultaneously 
		for(bird en: enemies){
			en.move();
			checkBirdCollision(guy,en);
			//function made to determine outcome of pusheen meeting an angry bird
			
		}
		//flag to determine the cat actually landed on a cloud automatically put as true until proven false
		boolean lost=true;
		
		//checks each object, moves them all, and check for collision
		for(cloud cb: cloudBoats){
			cb.move(); //move function made in class, allows the cloud textures to move across the screen
			checkCloudCollision(guy,cb); //function made to dictate what happens when cat meets cloud
			//if the rects collide between cloud and guy, then cat is alive
			if(cb.createRect(cb.pic).overlaps(guy.getBoundingRectangle())== true){
				lost=false;
				
			}
			
		}
		//y must be > 315 since the cat moves up 63 pixels a time, otherwise it would keep hitting the
		//water first and constantly lose, <567 --> same logic to keep pusheen from constantly dying
		if(lost==true && cy>315 && cy<567){
			life-=1; //starts with 3 lives
			cx= 0; //if hit by enemy, they start at initial starting position
			cy=0;
			System.out.println("STRIKE!");
			System.out.println(life+" lives left.");
			lostSound.play();
			splashSound.play();
			points-=25;
		}
		
		//once you collide with the final pillow image, put it where it is supposed to be put and blit it		
		for(int i=0; i<4; i++){
			if(guy.getBoundingRectangle().overlaps(endPillow.get(i).getBoundingRectangle())){
				//put it on top 
				finPil.get(i).setPosition(200*(i+1),570);
				//set pusheen back at the start
				cx=0;
				cy=0;	
				numCloud++; //counter to keep track
				arriveSound.play();
			}
		
		}
		//counter counts how many final clouds have been reached, there's four in total
		if(numCloud==4){
			drawWinScreen(); //yayy
			System.out.println("You got: "+points+" points!");
		}
		//number of lives pictures
		
		if(life==3){
			l3.setPosition(0,670); //set it in right place
		}
		if(life==2){
			l2.setPosition(0, 670); //right place
			l3.setPosition(-1000, -1000); //out of sight out of mind
		}
		if(life ==1){
			l1.setPosition(0,670);
			l2.setPosition(-1000,-1000); //out of sight out of mind
		}
		if(life ==0){
			l1.setPosition(-1000, -1000);
		}
			
		// ALL DRAWING ----------------------------------------------
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//backdrops
		pC.setPosition(0,0);
		pC2.setPosition(0,247);
		rC.setPosition(0, 62);
		wC.setPosition(0,313);
		guy.setPosition((cx),(cy));

		back.draw(batch);
		pC.draw(batch);
		pC2.draw(batch);
		rC.draw(batch);
		wC.draw(batch);
		
		//for loops to draw everything in the array lists
		for(Sprite p:endPillow){
			p.draw(batch);
		}
		
		for(Sprite ep: finPil){
			ep.draw(batch);
		}
	
		for(bird en: enemies){
			en.draw(batch);
		}
		
		for(cloud cb: cloudBoats){
			cb.draw(batch);
			
		}
		//to be drawn on last since they should be on top
		guy.draw(batch);
		lS.draw(batch);
		wS.draw(batch);
		l1.draw(batch);
		l2.draw(batch);
		l3.draw(batch);
		batch.end(); //done drawing

		
	}
	
//******************************************************************************
	//function sets the losing screen picture where it should be and tells the user
	//how many points they have
	public void DrawEndScreen(){
		failSound.play();
		System.out.println("You got: "+points+" points!");
		lS.setPosition(0,0);
	}
	
	//function sets the winning screen picture where it should be, plays a victory sound
	//and user can exit the game
	public void drawWinScreen(){
		winSound.play();
		wS.setPosition(0,0);
	}

	//checks for collision between guy and bird
	public boolean checkBirdCollision(Sprite good, bird b){
		//createRect() function made in bird class--> acts as getBoundingRectangle()
		if(good.getBoundingRectangle().overlaps(b.createRect(b.pic))){
			if(life>0){ 
				life-=1; //starts with 3 lives
				cx= 0; //if hit by enemy, they start at initial starting position
				cy=0;
				System.out.println("STRIKE!");
				lostSound.play();
				points-=25; //minus those points :(
			}
			if(life<=0){ //if pusheen dies
				DrawEndScreen();
			}
			
			return true;
		}
		else{
			return false;
		}
	}
	
	


	public boolean checkCloudCollision(Sprite s, cloud c){
		//if guy collides with a cloud, and CY IS GREATER THAN 313--> point of which pusheen
		//has to cross a lake meaning only in this part the code is applicable
		if(s.getBoundingRectangle().overlaps(c.createRect(c.pic)) && cy>313){
			//make pusheen move exactly like a cloud, so use the fields about cloud
			//and apply to pusheen (ex. getDir, getSpeed)
			if(c.getDir()==1){
				System.out.println(Math.round(c.getSpeed()/5f));
				cx+=Math.round(c.getSpeed()/5f);
				//pusheen cannot go off screen so this restricts him and makes him lose
				if(cx>1100){
					life-=1;
					lostSound.play();
					cx=0;
					cy=0;
				}
			}
			//other direction
			if(c.getDir()==0){
				cx-=Math.round(c.getSpeed()/5f);
				if(cx<=-250){
					life-=1;
					lostSound.play();
					cx=0;
					cy=0;
				}
			}
			return true;	
		}
		else{
			return false;
				
			}

	}
//class for the moving birds that can hurt pusheen as pusheen crosses on "road"
//each bird has its own x,y value, direction it's facing, speed, & a texture to display it
	public class bird{
		//fields
		private float x;
		private int y;
		private int dir;
		private int speed;
		Texture pic;
		private final int RIGHT=1;
		private final int LEFT=0;
		//constructor	
		public bird(Texture p,int x,int y, int speed,int dir){
			pic = p;
			this.x=x;
			this.y=y;
			this.speed=speed;
			this.dir=dir;
		
		}
	
		//*************************************used to test out createRect()
		public void drawRect(ShapeRenderer r, Texture p) {
			r.begin(ShapeType.Line);
			r.setColor(new Color(0.5f,0.5f,0,1));
			r.rect(x,y,p.getWidth(),p.getHeight());
			r.end();
		}

		//create rectangle around enemy
		public Rectangle createRect(Texture p){
			//corner, and dimensions
			Rectangle rec = new Rectangle(x,y,p.getWidth(),p.getHeight());
			return rec;
		}
		
		//allows bird to draw itself
		public void draw(SpriteBatch batch){
			batch.draw(pic,x,y);
		}
		
		//makes the bird move off the screen
		public void move(){ //makes the bird move off the screen
			if(dir==RIGHT){
				x+=speed/5f; //goes towards right
				if(x>1100){ //once it hits edge... comes back around like a loop
					x=-50;
				}
			
			}
			if(dir==LEFT){
				x-=speed/5f;
				if(x<=-100){ //loops back around off-screen
					x=1100;
				}
			}	
		}
		//functions allow private fields to be accessed throughout entire code	
		public float getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
	}
	
//****************************************************************************
	//class is very similar to bird function, except it describes the clouds that
	//pusheen can jump on to avoid sinking in water, has it's own unique fields
	public class cloud{
		private float x;
		private int y;
		private int dir;
		private int speed;
		Texture pic;
		private final int RIGHT=1;
		private final int LEFT=0;
		//constructor
		public cloud(Texture p,int x,int y, int speed,int dir){
			pic = p;
			this.x=x;
			this.y=y;
			this.speed=speed;
			this.dir=dir;
		}
		//used to test createRect	
		public void drawRect(ShapeRenderer r, Texture p) {
			r.begin(ShapeType.Line);
			r.setColor(new Color(0.5f,0.5f,0,1));
			r.rect(x,y,p.getWidth(),p.getHeight());
			r.end();
			
		}
		//same as earlier createRect, essentially acts as getBoundingRectangle()
		public Rectangle createRect(Texture p){
				Rectangle rec = new Rectangle(x,y,p.getWidth(),p.getHeight());
				return rec;
		}
		//sprite draws itself
		public void draw(SpriteBatch batch){
			batch.draw(pic,x,y);
		}
		//move function, allows cloud to move across screen in a certain direction randomly assigned to it
		public void move(){ //makes the cloud move
			if(dir==RIGHT){
				x+=speed/5f;
				if(x>1100){
					x=-250; //image should fully go off screen before reappearing
					//250 is largest image width to allow this to happen
				}
			}
			if(dir==LEFT){
				x-=speed/5f;
				if(x<=-250){
					x=1100; //reset and loop around
				}
			}	
		}
		//allows fields to be accessible throughout the rest of the code	
		public float getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int getDir(){
			return dir;
		}
		
		//to be used to make pusheen move with the cloud
		public float getSpeed(){
			return speed;
		}

	}
	
	
	//built in functions in a class (I didn't really use these so I'm not going to comment them out)
	public class Game extends ApplicationAdapter implements InputProcessor{

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
