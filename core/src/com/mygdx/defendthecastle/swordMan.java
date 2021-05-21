package com.mygdx.defendthecastle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

public class swordMan {
    Random random = new Random();
    public int x,y,damage,speed,width,height,health;
    public float attackSpeed,clock;

    public swordMan() {
        attackSpeed = 1;
        health = 100;
        speed = 150;
        damage = 10;
        width = 100;
        height = 100;
        x = -50;
        y = random.nextInt(250)+50;
        clock = 0f;
    }
    public void move(float delta) {
        x += speed * delta;
    }
    public boolean atCastle() {
        if (x <= ((y-2299)/-3.06f)+150) {
            return false;
        }
        else {
            return true;
        }
    }
    public int attack() {
        clock += Gdx.graphics.getDeltaTime();
        if (clock >= attackSpeed) {
            clock = 0;

            return damage;
        }
        else {
            return 0;
        }
    }
    public boolean contains (float mouseX, float mouseY) {
        return x <= mouseX && x + width >= mouseX && y <= mouseY && y + height >= mouseY;
    }
}
