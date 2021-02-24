package com.github.annasajkh;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Game extends ApplicationAdapter 
{
	Food[] foods = new Food[100_000];
	static ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	static Vector2 mousePos = new Vector2();
	static Snake player;
	
	
	@Override
	public void create()
	{
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.x = Gdx.graphics.getWidth() * 0.5f;
		camera.position.y = Gdx.graphics.getHeight() * 0.5f;
		player = new Snake();
		
		for (int i = 0; i < foods.length; i++)
		{
			foods[i] = new Food();
		}
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(camera.combined);
		camera.update();
		
		mousePos.x = Gdx.input.getX();
		mousePos.y = Gdx.input.getY();
		
		Vector3 mousePosTemp = camera.unproject(new Vector3(mousePos.x,mousePos.y,0));
		mousePos.x = mousePosTemp.x;
		mousePos.y = mousePosTemp.y;
		
		shapeRenderer.begin(ShapeType.Filled);
		
		for (int i = 0; i < foods.length; i++)
		{
			if(foods[i].position.x < Gdx.graphics.getWidth() + foods[i].radius * 2 && 
			   foods[i].position.x > -foods[i].radius * 2 &&
			   foods[i].position.y < Gdx.graphics.getHeight() + foods[i].radius * 2 && 
			   foods[i].position.y > -foods[i].radius * 2)
			{
				foods[i].draw(shapeRenderer);

				if(foods[i].inside(player.head))
				{
					foods[i] = new Food();
					player.eat();
				}
			}
			foods[i].update();
		}

		player.draw(shapeRenderer);
		player.update();
		shapeRenderer.end();
	}
	
	@Override
	public void dispose()
	{
		shapeRenderer.dispose();
	}
}
