package com.github.annasajkh;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Snake
{
	List<Body> bodies;
	Body head;
	float size = 20;
	private int eatCounter = 0;
	private int maxCounter = 5;
	float speed = 200;
	
	public Snake()
	{
		bodies = new ArrayList<>();
		bodies.add(new Body(new Vector2(Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.5f),Color.GREEN,size));
		head = bodies.get(0);
	}
	
	public void eat()
	{
		eatCounter++;
		
		if(eatCounter > maxCounter)
		{
			addBody();
			//maxCounter += maxCounter * 0.5;
			eatCounter = 0;
		}
	}
	
	public void addBody()
	{
		size += 0.01f;
		
		if(size > 100)
		{
			size = 100;
		}
		bodies.add(new Body(bodies.get(bodies.size() - 1).position.cpy(),Color.GREEN,size));
	}
	
	public void update()
	{
		for (int i = bodies.size()-1; i >= 1 ; i--)
		{
			if(bodies.get(i).position.dst(bodies.get(i - 1).position) > head.radius * 0.3f)
			{				
				bodies.get(i).position.add(bodies.get(i - 1).position.cpy()
																		.sub(bodies.get(i).position)
																		.nor()
																		.scl(Gdx.graphics.getDeltaTime())
																		.scl(speed));
			}
		}
		
		head.radius = size;
		for (int i = 1; i < bodies.size(); i++)
		{
			bodies.get(i).radius = size;
			bodies.get(i)
					.position
					.add(new Vector2(Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.5f)
					.sub(Game.mousePos)
					.nor()
					.scl(Gdx.graphics.getDeltaTime()).scl(speed));
		}
		
	}
	
	public void draw(ShapeRenderer shapeRenderer)
	{
		for(Body body : bodies)
		{
			body.draw(shapeRenderer);
		}
		
		Vector2 eyeDir = new Vector2(Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.5f).sub(Game.mousePos)
																								   .nor()
																								   .scl(size * 0.5f);
		Vector2 eyeDirRight = eyeDir.cpy().rotate(-90);
		Vector2 eyeDirLeft = eyeDir.cpy().rotate(90);
		
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(head.position.x + eyeDirRight.x,head.position.y + eyeDirRight.y,size * 0.3f);
		shapeRenderer.circle(head.position.x + eyeDirLeft.x,head.position.y + eyeDirLeft.y,size * 0.3f);

		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(head.position.x + eyeDirRight.x,head.position.y + eyeDirRight.y,size * 0.2f);
		shapeRenderer.circle(head.position.x + eyeDirLeft.x,head.position.y + eyeDirLeft.y,size * 0.2f);
	}

}
