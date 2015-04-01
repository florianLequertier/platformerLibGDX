package com.retroDante.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class GameCamera extends OrthographicCamera{
	
	GameCamera()
	{
		super();
	}
	
	GameCamera(Vector2 viewportDimension)
	{
		super(viewportDimension.x, viewportDimension.y);
	}
	
	GameCamera(float viewportWidth, float viewportHeigth)
	{
		super(viewportWidth, viewportHeigth);
	}
}
