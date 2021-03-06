package com.badlogicgames.superjumper;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Platform extends DynamicGameObject {
	public static final float PLATFORM_WIDTH = 1.4f;
	public static final float PLATFORM_HEIGHT = 1.4f;
	public static final int PLATFORM_TYPE_STATIC = 0;
	public static final int PLATFORM_TYPE_MOVING = 1;
	public static final int PLATFORM_STATE_CIRCLE = 2;
	public static final int PLATFORM_STATE_PULVERIZING = 1;
	public static final float PLATFORM_PULVERIZE_TIME = 0.1f * 4;
	public static final float PLATFORM_VELOCITY = -2;
	public static final Random rand = new Random();
	public static final int NTYPE = 4;
	private static final TextureRegion texture1 = Assets.meteoragrigiaRegion;
	private static final TextureRegion texture2 = Assets.meteorabluRegion;
	private static final TextureRegion texture3 = Assets.meteorarosaRegion;
	private static final TextureRegion texture4 = Assets.meteoragiallaRegion;
	private static final TextureRegion texture5 = Assets.mondofucRegion;
	private static final TextureRegion texture6 = Assets.mondolunaRegion;
	private static final TextureRegion texture7 = Assets.mondorosRegion;
	private static final TextureRegion texture8 = Assets.mondoterraRegion;
	float dstx,dsty;
	int type, rendertype;
	float stateTime, rotation = 0;
	float raggio=0;

	public Platform (int type, float x, float y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		this.type = type;
		this.rendertype = (int)(rand.nextFloat() * NTYPE);
		//Gdx.app.debug("Platform:", "rendertype="+rendertype);
		this.stateTime = 0;
		if (type == PLATFORM_TYPE_MOVING) {
			velocity.y = PLATFORM_VELOCITY;
		} else {
			velocity.x = 0;
			velocity.y = 0;
		}
	}

	public Platform (int type, float x, float y, int rendertype) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		this.type = type;
		this.rendertype = rendertype;
		this.stateTime = 0;
		if (type == PLATFORM_TYPE_MOVING) { 
			velocity.y = PLATFORM_VELOCITY;
		} else {
			velocity.x = 0;
			velocity.y = 0;
		}
	}
	
	public Platform (int type, DynamicGameObject dst) {
		super(dst.position.x, dst.position.y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		this.type = type;
		this.rendertype = (int)(rand.nextFloat() * NTYPE);
		this.dstx=dst.position.x;
		this.dsty=dst.position.y;
		this.stateTime = 0;
		
	}

	public void update (float deltaTime) {
		if (type == PLATFORM_TYPE_MOVING) {
			position.add(velocity.x*deltaTime,velocity.y*deltaTime);
			bounds.x = position.x - PLATFORM_WIDTH / 2;
			bounds.y = position.y - PLATFORM_HEIGHT / 2;
//			if (position.x > World.WORLD_WIDTH/2)velocity.x=-velocity.x;
//			else if (position.x > World.WORLD_WIDTH/2)velocity.x=velocity.x;
			}
	else if (type == PLATFORM_TYPE_STATIC){
			position.add(velocity.x * deltaTime/2,velocity.y * deltaTime/2);
			bounds.x = position.x - PLATFORM_WIDTH / 2;
			bounds.y = position.y - PLATFORM_HEIGHT / 2;
			velocity.y=-5;
		}
	else if (type == PLATFORM_STATE_CIRCLE){
		float k = position.y > World.WORLD_HEIGHT/2 ? 3 : 1;
		if(this.rendertype>1.9f){
		position.x = (float) (dstx + 1.7f*Math.sin(stateTime*k));
		position.y = (float) (dsty + 1.7f*Math.cos(stateTime*k));
		}
		else {
		position.x = (float) (dstx + 1.7f*Math.cos(stateTime*k));
		position.y = (float) (dsty + 1.7f*Math.sin(stateTime*k));
		}
		bounds.x = position.x - PLATFORM_WIDTH / 2;
		bounds.y = position.y - PLATFORM_HEIGHT / 2;
	}
	
		stateTime += deltaTime;
		rotation += deltaTime*30;
		if (rotation > 360) rotation -= 360;
	}
	
	
	

	public void draw (SpriteBatch batch) {
		if	(this.type == PLATFORM_STATE_CIRCLE){
		switch (this.rendertype) {
		case 0:
			//batch.draw(Assets.meteoragrigiaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture1,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1, this.rotation);
			break;
		case 1:
			//batch.draw(Assets.meteorabluRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture2,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1, this.rotation);
			break;
		case 2:
			//batch.draw(Assets.meteorarosaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture3,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1, this.rotation);
			break;
		case 3:
			//batch.draw(Assets.meteoragiallaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture4,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1, this.rotation);
			break;
		default:
			//batch.draw(texture4,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1);
			//batch.draw(Assets.meteoragiallaRegion,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1, this.rotation);
			//Gdx.app.debug("RENDERPLATFORMS", "platform.rendertype = " + this.rendertype);
		}}
		else
			switch (this.rendertype) {
			case 0:
				//batch.draw(Assets.meteoragrigiaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
				batch.draw(texture5,this.position.x - 0.75f,this.position.y,UI.SPRING_WIDTH/2,UI.SPRING_HEIGHT / 2, UI.SPRING_WIDTH ,UI.SPRING_HEIGHT , 1, 1, this.rotation);
				break;
			case 1:
				//batch.draw(Assets.meteorabluRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
				batch.draw(texture6,this.position.x - 0.75f,this.position.y,UI.SPRING_WIDTH/2,UI.SPRING_HEIGHT / 2, UI.SPRING_WIDTH ,UI.SPRING_HEIGHT , 1, 1, this.rotation*0.8f);
				break;
			case 2:
				//batch.draw(Assets.meteorarosaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
				batch.draw(texture7,this.position.x - 0.75f,this.position.y,UI.SPRING_WIDTH/2,UI.SPRING_HEIGHT / 2, UI.SPRING_WIDTH ,UI.SPRING_HEIGHT , 1, 1, this.rotation*1.5f);
				break;
			case 3:
				//batch.draw(Assets.meteoragiallaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
				batch.draw(texture8,this.position.x - 0.75f,this.position.y,UI.SPRING_WIDTH/2,UI.SPRING_HEIGHT/ 2, UI.SPRING_WIDTH,UI.SPRING_HEIGHT , 1, 1, this.rotation/2);
				break;
			default:
				//Gdx.app.debug("RENDERPLATFORMS", "platform.rendertype = " + this.rendertype);
			}
	}
	
	/*public void draw (SpriteBatch batch) {
		switch (this.rendertype) {
		case 0:
			//batch.draw(Assets.meteoragrigiaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture1,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1);
			break;
		case 1:
			//batch.draw(Assets.meteorabluRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture2,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1);
			break;
		case 2:
			//batch.draw(Assets.meteorarosaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture3,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1);
			break;
		case 3:
			//batch.draw(Assets.meteoragiallaRegion, platform.position.x - 0.75f, platform.position.y - 0.75f, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT);
			batch.draw(texture4,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1);
			break;
		default:
			//batch.draw(texture4,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1);
			//batch.draw(Assets.meteoragiallaRegion,this.position.x - 0.75f,this.position.y,Platform.PLATFORM_WIDTH/2,Platform.PLATFORM_HEIGHT/2, Platform.PLATFORM_WIDTH, Platform.PLATFORM_HEIGHT, 1, 1, this.rotation);
			//Gdx.app.debug("RENDERPLATFORMS", "platform.rendertype = " + this.rendertype);
		}
	}*/
}