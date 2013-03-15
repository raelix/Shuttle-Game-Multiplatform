
package com.badlogicgames.superjumper;

public class Projectile extends DynamicGameObject {
	public static final float PROJECTILE_WIDTH = 0.5f;
	public static final float PROJECTILE_HEIGHT = 0.8f;
	public static final int PROJECTILE_SCORE = 10;
	public static final float PROJECTILE_VELOCITY = 6;
	private static  float x;
	private static float y;
	private boolean visible;

	float stateTime;

	public Projectile (float startx, float starty) {
		super(x, y, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		x=startx;
		y=starty;
		stateTime = 0;
		velocity.y=PROJECTILE_VELOCITY;
	}

	public void update (float deltaTime) {
		stateTime += deltaTime;
		position.add(0,velocity.y * deltaTime);
		bounds.x = position.x;
		bounds.y = position.y ;
		stateTime += deltaTime;
	}
	
	public float getX() {
		return velocity.x;
	}

	public float getY() {
		return velocity.y;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}

