package com.mygdx.defendthecastle;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class upgradeMenu implements Screen {
    int castleHealth, groundTroops, rifleMen,clickDamage;
    int screenWidth,screenHeight;
    Rectangle background;
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer shapeRenderer;

    public upgradeMenu() {
        castleHealth = 0;
        groundTroops = 0;
        rifleMen = 0;
        clickDamage = 0;

        screenWidth = 1152;
        screenHeight = 648;

        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        background.width = screenWidth-200;
        background.height = screenHeight-200;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin();
        shapeRenderer.setColor(0,1,1,1);
        shapeRenderer.rect(screenWidth/2-background.width/2,screenHeight/2-background.height/2,background.width,background.height);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
