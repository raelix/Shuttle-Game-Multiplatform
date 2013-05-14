/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogicgames.superjumper;

public class Platform extends DynamicGameObject {
	public static final float PLATFORM_WIDTH = 2.5f;
	public static final float PLATFORM_HEIGHT = 2.5f;
	public static final int PLATFORM_TYPE_STATIC = 0;
	public static final int PLATFORM_TYPE_MOVING = 1;
	public static final int PLATFORM_STATE_NORMAL = 0;
	public static final int PLATFORM_STATE_PULVERIZING = 1;
	public static final float PLATFORM_PULVERIZE_TIME = 0.1f * 4;
	public static final float PLATFORM_VELOCITY = -2;

	int type;
	int state;
	float stateTime;
	

	public Platform (int type, float x, float y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		this.type = type;
		this.state = PLATFORM_STATE_NORMAL;
		this.stateTime = 0;
		if (type == PLATFORM_TYPE_MOVING) {
			
			velocity.y = PLATFORM_VELOCITY;
		} else {
			velocity.x = 0;
			velocity.y = 0;
		}
	}

	public void update (float deltaTime) {
		if (type == PLATFORM_TYPE_MOVING) {
			position.add(velocity.x*deltaTime,velocity.y*deltaTime);
			bounds.x = position.x - PLATFORM_WIDTH / 2;
			bounds.y = position.y - PLATFORM_HEIGHT / 2;
			//if (position.x < PLATFORM_HEIGHT / 2) {
			//	velocity.y = -velocity.y;
			//	position.y = PLATFORM_HEIGHT / 2;
			//}
			//if (position.x > World.WORLD_HEIGHT - PLATFORM_HEIGHT / 2) {
			//	velocity.y = -velocity.y;
			//	position.x = World.WORLD_HEIGHT - PLATFORM_HEIGHT / 2;
			//}
			if (position.x > World.WORLD_WIDTH/2)velocity.x=-velocity.x;
			else if (position.x > World.WORLD_WIDTH/2)velocity.x=velocity.x;
		} else {
			position.add(velocity.x * deltaTime/2,velocity.y * deltaTime/2);
			bounds.x = position.x - PLATFORM_WIDTH / 2;
			bounds.y = position.y - PLATFORM_HEIGHT / 2;
		}
		stateTime += deltaTime;
	}

}
