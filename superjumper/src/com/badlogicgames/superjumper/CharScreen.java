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

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CharScreen implements Screen {
	Game game;
	float velX, velY;
	boolean flinging = false;
	float initialScale = 1;
	OrthographicCamera guiCam;
	public final Bob bob;
	public final Bob bobfem;
	SpriteBatch batcher;
	Rectangle nextBounds;
	Vector3 touchPoint;
	Rectangle backBounds;
	GestureDetector gestureDetector;
	Rectangle character;
	public static int state=1;
	public  int choose=0;
	public CharScreen (Game game) {
		this.game = game;
		this.bob = new Bob(130,125);
		this.bobfem = new Bob(-130,180);
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		nextBounds = new Rectangle(320 - 64, 0, 64, 64);
		backBounds = new Rectangle(0, 0, 64, 64);
		character= new Rectangle(120, 150,120, 150);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		this.bob.CHARSCREENUSE=1;
		this.bobfem.CHARSCREENUSE=1;
		gestureDetector = new GestureDetector(20, 0.5f, 2, 0.15f, new GestureListener() {
			
			@Override
			public boolean zoom (float initialDistance, float distance) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDown (float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean tap (float x, float y, int count, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean pinch (Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean pan (float x, float y, float deltaX, float deltaY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean longPress (float x, float y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean fling (float velocityX, float velocityY, int button) {
				// TODO Auto-generated method stub
				Gdx.app.debug("x"+velocityX, "y"+velocityY);
				 if(Math.abs(velocityX)>Math.abs(velocityY)){
                if(velocityX>0 && bob.position.x<320){
            
                       bob.velocity.x+=790;
                       bob.velocity.y+=160;
                       bobfem.velocity.y-=200;
                }else if (velocityX<0&& bob.position.x>120){
               	 if(bob.position.x>310)
               	choose=1;
               	 if(choose==1){
               	  bob.velocity.x-=790;
               	  bob.velocity.y-=160;
               	  bobfem.velocity.y+=200;
               	 
               	 }
              
                } else {
                  // Do nothing.
                }
        }else{

           // Ignore the input, because we don't care about up/down swipes.
        }
  return true; 

			}
		});
		Gdx.input.setInputProcessor(gestureDetector);
		
	}

	public void update (float deltaTime) {
		
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (OverlapTester.pointInRectangle(nextBounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new GameScreen(game));
				return;
			}
			else if (OverlapTester.pointInRectangle(backBounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
			else if (OverlapTester.pointInRectangle(character, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
			if(state==0)state=1;
			else state=0;
				return;
			}
		}
		bob.update(deltaTime);
		bobfem.update(deltaTime);
		if(bob.position.x<320&&bob.position.x>0)state=1;
		else if(bobfem.position.x<320&&bobfem.position.x>0)state=0;
		//Gdx.app.debug("x"+bob.position.x, "y"+bob.position.y);
		bobfem.position.x=bob.position.x-210;
		//bobfem.position.y=-80;
		if(bob.position.x>320)
		{
			bob.velocity.x=0;
			 bob.velocity.y=0;
			 bobfem.velocity.y=0;
		}
		if(bobfem.position.x<-100)
		{
			bob.velocity.x=0;
			bob.velocity.y=0;
			 bobfem.velocity.y=0;
		}
		
			
		
	}

	public void draw (float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.disableBlending();
		batcher.begin();
		MainMenuScreen.drawGradient(batcher, Assets.rect, 0, 0, 320, 480,Color.BLACK,Color.BLUE, false);
		batcher.end();

		batcher.enableBlending();
		batcher.begin();
		Assets.font.draw(batcher, "Choose Character", 29,440);
		Assets.font.draw(batcher, "GO", 280,35);
		Assets.font.draw(batcher, "BACK", 3,35);
	
			batcher.draw(Assets.backgroundRegion,bob.position.x ,bob.position.y ,130,130);
			batcher.draw(Assets.backgroundRegion10,bobfem.position.x ,bobfem.position.y ,130,130);
		
			//batcher.draw(Assets.backgroundRegion,bob.position.x ,bob.position.y ,25, 35, 120, 150, 1, 1, 180);
			//batcher.draw(Assets.backgroundRegion10,bobfem.position.x ,bobfem.position.y ,25, 35, 120, 150, 1, 1, 180);
		batcher.end();

		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void render (float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize (int width, int height) {
	}

	public int state() {
		return state;
	}
	
	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
		Assets.backgroundmain4.dispose();
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}

	
}
