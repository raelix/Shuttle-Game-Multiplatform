package com.badlogicgames.superjumper;

import com.badlogic.gdx.math.Vector2;

public class Projectile extends DynamicGameObject {
	public static final float BOB_WIDTH = 0.3f;
	public static final float BOB_HEIGHT = 0.6f;
	public final float MAXVELOCITY = 10f;
	public Vector2 gravity = new Vector2(0,15);
	float stateTime;


	public Projectile (float x, float y) {
		super(x, y, BOB_WIDTH, BOB_HEIGHT);
		stateTime = 0;
	}

	public void setGravity(float x, float y){
		this.gravity.x = x;
		this.gravity.y = y;
	}

	public void setVelocity(float x, float y){
		this.velocity.x = x;
		this.velocity.y = y;
	}
	public void update (float deltaTime) {
		velocity.add(gravity.x * deltaTime, gravity.y * deltaTime);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
		stateTime += deltaTime;
	}

	public void hitSquirrel () {
		velocity.set(0, 0);
		stateTime = 0;
	}

	public void hitPlatform () {
		//velocity.y = BOB_JUMP_VELOCITY;
		//state = BOB_STATE_JUMP;
		stateTime = 0;
	}

	public void hitSpring () {
		//velocity.y = BOB_JUMP_VELOCITY * 1.5f;
		//state = BOB_STATE_JUMP;
		stateTime = 0;
	}
}