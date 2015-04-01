package com.retroDante.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InGameScreen implements Screen{
	private SpriteBatch batch;
	private BitmapFont font;
	
	@Override 
	public void render(float delta){
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(batch, "bienvenu", 50, Gdx.graphics.getHeight() - 50);
		batch.end();
		
	}
	
	@Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
