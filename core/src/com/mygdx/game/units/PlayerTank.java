package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TanchikiGame;
import com.mygdx.game.Weapon;
import com.mygdx.game.utils.TankOwner;

public class PlayerTank extends Tank {
    int lifes;

    public PlayerTank(TanchikiGame game, TextureAtlas atlas) {
        super(game);
        this.ownerType = TankOwner.PLAYER;
        this.weapon = new Weapon(atlas);
        this.texture = atlas.findRegion("playerTankBase");
        this.textureHP = atlas.findRegion("bar");
        this.position = new Vector2(640.0f, 360.0f);
        this.speed = 100.0f;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 10;
        this.hp = this.hpMax - 2;
        this.circle = new Circle(position.x, position.y, (width + height)/2);
        this.lifes = 5;
    }

    @Override
    public void destroy() {
        lifes--;
        hp = hpMax;
    }

    public void update(float dt){

        checkMovement(dt);
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();

        rotateTurretToPoint(mx, my, dt);

        if (Gdx.input.isTouched()){
            fire(dt);
        }

        super.update(dt);
    }

    public void checkMovement(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= speed * dt;
            angle = 180.0f;
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.x += speed * dt;
            angle = 0.0f;
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.y += speed * dt;
            angle = 90.0f;
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.y -= speed * dt;
            angle = 270.0f;
        }
    }
}
