package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tank {
    private TanchikiGame game;
    private Weapon weapon;
    private Texture texture;
    private Texture textureTurret;
    private Vector2 position;

    private float speed;
    private float angle;

    private float turretAngle;
    private float fireTimer;

    private int width;
    private int height;

    public Tank(TanchikiGame game){
        this.game = game;
        this.weapon = new Weapon();
        this.texture = new Texture("Tank.png");
        this.textureTurret = new Texture("simple_weapon.png");
        this.position = new Vector2(640, 360);
        this.speed = 100.0f;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x - width/2, position.y - height/2, width/2, height/2, width, height, 1, 1, angle, 0, 0, width, height, false, false);
        batch.draw(textureTurret, position.x - width/2, position.y - height/2, width/2, height/2, width, height, 1, 1, turretAngle, 0, 0, width, height, false, false);

    }

    public void update(float dt){
        checkMovement(dt);
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();
        float angleTo = Utils.getAngle(position.x, position.y, mx, my);
        turretAngle = Utils.makeRotation(turretAngle, angleTo, 270.0f, dt);
        turretAngle = Utils.angleToFromNegPiToPosPi(turretAngle);

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            fire(dt);
        }
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

    public void fire(float dt){
        fireTimer += dt;
        if (fireTimer >= weapon.getFirePeriod()){
            fireTimer = 0.0f;
            float angleRadian = (float) Math.toRadians(turretAngle);
            game.getBulletEmitter().activate(position.x, position.y, 320.0f * (float)Math.cos(angleRadian), 320.0f * (float)Math.sin(angleRadian));
        }
    }
}
