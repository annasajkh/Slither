package com.github.annasajkh;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Circle
{
	Vector2 position;
	Color color;
	float radius;
	
	public boolean collide(Circle other)
	{
		return position.dst(other.position) <= radius + other.radius;
	}
	
	public boolean inside(Circle other)
	{
		return position.dst(other.position) <= other.radius;
	}
}
