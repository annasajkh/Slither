package com.github.annasajkh;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Body extends Circle
{		
	public Body(Vector2 position,Color color,float radius)
	{
		this.position = position;
		this.color = color;
		this.radius = radius;
	}
	
	public void draw(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.setColor(color);
		shapeRenderer.circle(position.x,position.y,radius);
		
	}
	

}
