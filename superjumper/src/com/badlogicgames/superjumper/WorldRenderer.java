package com.badlogicgames.superjumper;
/*VIEW*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.GradientColorValue;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.FloatArray;

public class WorldRenderer {

	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	private static float[] verts = new float[20];
	static int a=0;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	TextureRegion background;


	public WorldRenderer (SpriteBatch batch, World world) {
		this.world = world;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;

	}

	public void render () {
		if (world.bob.position.y+5 > cam.position.y) cam.position.y = world.bob.position.y+5;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}

	public void renderBackground () {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);
		//Gradient Background 
		batch.begin();
		batch.disableBlending();
		drawGradient(batch, Assets.rect, 0, 0, 10, 110,Color.BLACK,Assets.colore, false);
		batch.enableBlending();
		batch.end();
	}

	public void renderObjects () {
		batch.enableBlending();
		batch.begin();
		renderStars();
		renderBob();
		renderPlatforms();
		renderItems();
		renderLifes();
		renderSquirrels();
		renderCastle();
		renderProjectiles();
		batch.end();
	}

	
	
	private void renderBob () {
		TextureRegion keyFrame;
		int i;
//render world terra
		batch.draw(Assets.backgroundRegion1, 0, -1, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		switch (world.bob.state) {
		case Bob.BOB_STATE_FALL:
			keyFrame = Assets.bobFall.getKeyFrame(world.bob.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Bob.BOB_STATE_JUMP:
			keyFrame = Assets.bobJump.getKeyFrame(world.bob.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Bob.BOB_STATE_HIT:
		default:
			keyFrame = Assets.bobHit;
		}
		/*	float side = world.bob.velocity.x < 0 ? -1 : 1;*/
		//Particelle di fuoco dietro Bob
		
      if(world.nosinuse==1)
       {
              Assets.particleClouds.start();
              Assets.particleClouds.setPosition(world.bob.position.x,world.bob.position.y-1);
              Assets.particleClouds.draw(batch, Gdx.graphics.getDeltaTime());
       }
      else
       {    
      	Assets.particleEffect.start();
      	Assets.particleEffect.setPosition(world.bob.position.x,world.bob.position.y-1);
      	Assets.particleEffect.draw(batch, Gdx.graphics.getDeltaTime());
       }
		if(CharScreen.state==1)
		batch.draw(keyFrame, world.bob.position.x -0.65f, world.bob.position.y -2.5f, 2.3f, 3f);
		else {
			keyFrame = Assets.bobfJump.getKeyFrame(world.bob.stateTime, Animation.ANIMATION_LOOPING);
			batch.draw(keyFrame, world.bob.position.x -0.65f, world.bob.position.y -2.5f, 2.3f, 3f);
			}
	}

	private void renderPlatforms () {
		int len = world.platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = world.platforms.get(i);
			TextureRegion keyFrame ;
			if (platform.state == Platform.PLATFORM_STATE_PULVERIZING) {
				keyFrame = Assets.brakingPlatform.getKeyFrame(platform.stateTime, Animation.ANIMATION_LOOPING);
				batch.draw(keyFrame, platform.position.x - 1, platform.position.y - 0.25f, 4, 4);
			}
			else {keyFrame = Assets.coinAnim.getKeyFrame(platform.stateTime, Animation.ANIMATION_LOOPING);
			batch.draw(keyFrame, platform.position.x - 0.75f, platform.position.y - 0.75f, 1.5f, 1.5f);
			}}
	}


	
	private void renderItems () {
		int len = world.springs.size();
		for (int i = 0; i < len; i++) {
			Spring spring = world.springs.get(i);
			batch.draw(Assets.spring, spring.position.x - 0.5f, spring.position.y - 0.5f, 1, 1);
		}

		len = world.coins.size();
		for (int i = 0; i < len; i++) {
			Coin coin = world.coins.get(i);
			TextureRegion keyFrame = Assets.platform.getKeyFrame(coin.stateTime, Animation.ANIMATION_LOOPING);
			if (coin.state == Coin.COIN_STATE_PULVERIZING) {
			}
			batch.draw(keyFrame, coin.position.x - 1, coin.position.y - 0.25f, 2.5f, 2.5f);
		}
	}

	private void renderLifes(){
		int len = world.lifes.size();
		for (int i = 0; i < len; i++) {
			Life life = world.lifes.get(i);
			TextureRegion keyFrame = Assets.lifeAnim.getKeyFrame(life.stateTime, Animation.ANIMATION_LOOPING);	
			batch.draw(keyFrame, cam.position.x - FRUSTUM_WIDTH/2, cam.position.y + i+1, 0.5f, 0.5f);
			/*TextureRegion breakanim = Assets.breakanim.getKeyFrame(life.stateTime, Animation.ANIMATION_LOOPING);;	
			batch.draw(breakanim, cam.position.x, cam.position.y , 7, 6f);
*/

		}
	}

	private void renderProjectiles(){
		int len = world.projectiles.size();
		for (int i = 0; i < len; i++) {
			Projectile projectile = world.projectiles.get(i);
			TextureRegion keyFrame = Assets.projAnim.getKeyFrame(projectile.stateTime, Animation.ANIMATION_LOOPING);	
			batch.draw(keyFrame, projectile.position.x -0.07f , projectile.position.y+0.4f, 0.3f,0.6f);
		}
	}

	private void renderSquirrels () {
		int len = world.squirrels.size();
		for (int i = 0; i < len; i++) {
			Squirrel squirrel = world.squirrels.get(i);
			
			if (squirrel.state == Squirrel.BUBBLE_CLISION ) {
				TextureRegion keyFrame = Assets.bubbles;
				batch.draw(keyFrame,world.bob.position.x-1.2f , world.bob.position.y-2.3f , 2.5f, 3f);
			}
			else {
				TextureRegion keyFrame = Assets.portagadget.getKeyFrame(squirrel.stateTime, Animation.ANIMATION_LOOPING);
				batch.draw(keyFrame, squirrel.position.x - 0.6f, squirrel.position.y - 0.6f, 1.3f, 1.3f);
		}
			if(squirrel.nosuse == 1 ){
				TextureRegion keyFrame = Assets.nos;
				batch.draw(keyFrame,cam.position.x + 3.4f, cam.position.y - 5.8f , 1.5f, 1.5f);
			}
			}
	}
	
	

	private void renderCastle () {
		Castle castle = world.castle;
		batch.draw(Assets.castle, castle.position.x - 1, castle.position.y - 1, 2, 2);
	}

	private void renderStars () {
		int len = world.stars.size();
		for (int i = 0; i < len; i++) {
			Star star = world.stars.get(i);
			TextureRegion keyFrame = Assets.star1Region;
			if (star.type == Star.STAR_TYPE_MOVING ) {
				keyFrame = Assets.staranim.getKeyFrame(star.stateTime, Animation.ANIMATION_LOOPING);
				batch.draw(keyFrame, star.position.x , star.position.y , 0.18f, 0.15f);
			}
			else if(star.type != Star.STAR_TYPE_MOVING ){
				keyFrame = Assets.star1Region;
			batch.draw(keyFrame, star.position.x , star.position.y , 0.13f, 0.1f);}
		}
	}
	public static void drawGradient(SpriteBatch batch, TextureRegion tex, float x, float y,
		float width, float height, Color a, Color b, boolean horiz) {
		float ca = a.toFloatBits();
		float cb = b.toFloatBits();

		int idx = 0;
		verts[idx++] = x;
		verts[idx++] = y;
		verts[idx++] = horiz ? ca : cb; // bottom left
		verts[idx++] = tex.getU(); //NOTE: texture coords origin is top left
		verts[idx++] = tex.getV2();

		verts[idx++] = x;
		verts[idx++] = y + height;
		verts[idx++] = ca; // top left
		verts[idx++] = tex.getU();
		verts[idx++] = tex.getV();

		verts[idx++] = x + width;
		verts[idx++] = y + height;
		verts[idx++] = horiz ? cb : ca; // top right
		verts[idx++] = tex.getU2();
		verts[idx++] = tex.getV();

		verts[idx++] = x + width;
		verts[idx++] = y;
		verts[idx++] = cb; // bottom right
		verts[idx++] = tex.getU2();
		verts[idx++] = tex.getV2();

		batch.draw(tex.getTexture(), verts, 0, verts.length);
	}
}


