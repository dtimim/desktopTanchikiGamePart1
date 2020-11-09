package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TanchikiGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Map map;
	private Tank tank;
	private BulletEmitter bulletEmitter;

	// Идеи:
	// ====================
	// Вектора/эмиттеры
	// Прицеливаемся мышкой
	// Завести константы
	// Не выходить за экран
	// Разрушаемые стены / карта (материалы стен)
	// Разные виды оружия
	// Боты
	// Эмиттер для пуль нужен
	// Отделить башню от корпуса
	// * Размер норм
	// Полоса хелф пауэ
	// powerups
	// штаб


	public BulletEmitter getBulletEmitter() {
		return bulletEmitter;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new Map();
		tank = new Tank(this);
		bulletEmitter = new BulletEmitter();
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0.6f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch);
		tank.render(batch);
		bulletEmitter.render(batch);
		batch.end();
	}

	public void update(float dt){
		tank.update(dt);
		bulletEmitter.update(dt);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
