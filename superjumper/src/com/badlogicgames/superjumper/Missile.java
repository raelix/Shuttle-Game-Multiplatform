package com.badlogicgames.superjumper;

public class Missile extends Projectile {

	public Missile (float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

public void updateMissile (float deltaTime,DynamicGameObject obj,DynamicGameObject trg) {

	velocity.add(gravity.x * deltaTime, gravity.y * deltaTime);
	position.add(velocity.x * deltaTime, velocity.y * deltaTime);
	bounds.x = position.x - bounds.width / 2;
	bounds.y = position.y - bounds.height / 2;
	obj.velocity.x=trg.position.x-obj.position.x;
	obj.velocity.y=trg.position.y-obj.position.y;
	stateTime += deltaTime;
}
}
