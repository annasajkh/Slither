package com.github.annasajkh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Food extends Circle
{
	public Food()
	{
		color = new Color(MathUtils.random(),MathUtils.random(),MathUtils.random(),1);
		position = new Vector2(MathUtils.random(-10_000,10_000),MathUtils.random(-10_000,10_000));
		radius = 10;
	}
	
	public void update()
	{
		position.add(new Vector2(Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.5f)
				.sub(Game.mousePos)
				.nor()
				.scl(Gdx.graphics.getDeltaTime()).scl(Game.player.speed));
	}
	
	public void draw(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.setColor(color);
		shapeRenderer.circle(position.x,position.y,radius);
	}

}
