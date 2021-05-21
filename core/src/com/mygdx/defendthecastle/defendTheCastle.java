package com.mygdx.defendthecastle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;

public class defendTheCastle extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Rectangle castle;
	OrthographicCamera camera;
	BitmapFont font;
	int screenWidth,screenHeight;
	float clock,stateTime;
	int castleHealth;
	Texture spriteSheet;
	Array<swordMan> swordMen;
	Animation<TextureRegion> swordManAnimation,swordManSwingAnimation;
	InputProcessor inputProcessor;
	gameStages gameStage;
	
	@Override
	public void create () {
		// Initialize SpriteBatch,BitmapFont
		batch = new SpriteBatch();
		font = new BitmapFont();

		// Loads images
		background = new Texture("Images/background.png");

		// Initialize Camera
		screenWidth = 1152;
		screenHeight = 648;

		camera = new OrthographicCamera();
		camera.setToOrtho(false,screenWidth,screenHeight);

		// Initialize castle settings
		castleHealth = 1000;
		castle = new Rectangle();
		castle.width = 238;
		castle.height = screenHeight-100;
		castle.x = screenWidth - castle.width;

		// Initialize Clock
		clock = 0;

		// Initialize swordMan sprite
		spriteSheet = new Texture("Sprites/swordManSprite.png");

		TextureRegion[][] sp = TextureRegion.split(spriteSheet,spriteSheet.getWidth()/6,spriteSheet.getHeight()/1);
		TextureRegion[] spriteAnimation = new TextureRegion[6];

		for (int x = 0; x < 6; x++) {
			spriteAnimation[x] = sp[0][x];
		}
		swordManAnimation = new Animation<TextureRegion>(.1f,spriteAnimation);

		spriteSheet = new Texture("Sprites/swordManSwingSprite.png");

		sp = TextureRegion.split(spriteSheet,spriteSheet.getWidth()/6,spriteSheet.getHeight()/1);
		spriteAnimation = new TextureRegion[6];

		for (int x = 0; x < 6; x++) {
			spriteAnimation[x] = sp[0][x];
		}
		swordManSwingAnimation = new Animation<TextureRegion>(.1f,spriteAnimation);

		swordMen = new Array<>();

		// Initialize gameStage
		gameStage = new gameStages();
	}

	@Override
	public void render () {
		stateTime += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(1, 0, 0, 1);
		// Render background
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();

		// Render Stats
		renderStats(batch);

		// Render swordMen
		renderSwordMen(batch);

		// Spawn Troops
		spawnEnemies();
		isClicked();
		if (gameStage.isRoundOver()) {
			upgradeMenu menu = new upgradeMenu();
			menu.render(Gdx.graphics.getDeltaTime());
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
	public boolean isGameOver() {
		return false;
	}
	public void clickEnemy() {
		for (int x = 0; x < swordMen.size; x++) {
		}
	}
	public void spawnEnemies() {
		swordMen = gameStage.returnSwordMan();
	}
	public void renderStats(SpriteBatch batch) {
		String text = "Castle Health: " + castleHealth;
		text += "\nRemaining Enemies: " + swordMen.size;
		batch.begin();
		font.draw(batch,text, 10,screenHeight-10);
		batch.end();
	}
	public void renderSwordMen(SpriteBatch batch) {
		TextureRegion swordRender = swordManAnimation.getKeyFrame(stateTime,true);
		TextureRegion swingRender = swordManSwingAnimation.getKeyFrame(stateTime,true);
		batch.begin();
		for (swordMan troop: swordMen) {
			if (troop.atCastle()) {
				castleHealth -= troop.attack();
				batch.draw(swingRender,troop.x,troop.y);
			}
			else {
				batch.draw(swordRender,troop.x,troop.y);
				troop.move(Gdx.graphics.getDeltaTime());
			}
		}
		batch.end();
	}
	// Checks to see if enemy is clicked
	public void isClicked() {
		inputProcessor = new inputProcess();
		Vector3 mousePos = new Vector3(Gdx.input.getX(),screenHeight- Gdx.input.getY(),0);
		//System.out.println();
		for (int x = 0; x < swordMen.size; x++) {
			mousePos.x = Gdx.input.getX();
			mousePos.y = screenHeight- Gdx.input.getY();
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				//System.out.println("x: " + mousePos.x + " | y: " + mousePos.y);
				if (swordMen.get(x).contains(mousePos.x,mousePos.y)) {
					swordMen.get(x).health -= 10;
					System.out.println(swordMen.get(x).health);
				}
			}
			if (swordMen.get(x).health <= 0) {
				swordMen.removeIndex(x);
			}
		}
	}
}
