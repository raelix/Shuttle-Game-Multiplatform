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

import java.util.LinkedList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class FirstScreen implements Screen {
	Game game;
	Spring ruota;
	LinkedList<FloatingText> testo;
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Rectangle clickBounds;
	Vector3 touchPoint;
	Texture helpImage;
	TextureRegion helpRegion;

	public FirstScreen (Game game) {
		this.game = game;
		this.ruota=new Spring(-300,-30);
		this.testo=new LinkedList<FloatingText>();
		this.testo.offer(new FloatingText(UI.HALFSCREENWIDTH,UI.FIRSTEXT+50, "Welcome",0.2f));
		this.testo.offer(new FloatingText(UI.HALFSCREENWIDTH,UI.SECONDTEXT+50, "To",0.2f));
		this.testo.offer(new FloatingText(UI.HALFSCREENWIDTH,UI.THIRDTEXT+50,"Game",0.2f));
		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(Gdx.graphics.getWidth()/ 2, Gdx.graphics.getHeight() /2, 0);
		clickBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
	}

	public void update (float deltaTime) {
		ruota.update(deltaTime);
		for(int i=0;i<testo.size();i++){
		if ( testo.get(i).stateTime > testo.get(i).duration)
			testo.get(i).update(0);
		else testo.get(i).update(deltaTime);
		}
			if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (OverlapTester.pointInRectangle(clickBounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	public void draw (float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.welcome, 0, 0, UI.SCREENPOSITIONX,UI.SCREENPOSITIONY);
		
		//MainMenuScreen.drawGradient(batcher, Assets.rect, 0, 0, 320, 480,Color.BLACK,Assets.colore, false);
		batcher.end();
		batcher.enableBlending();
		batcher.begin();
		ruota.draw(batcher, Assets.ruotaRegion,Gdx.graphics.getHeight()+150f,Gdx.graphics.getHeight()+150f);
		//batcher.draw(Assets.ruota, 0, 0, UI.SCREENWIDTH,UI.SCREENHEIGHT);
		
		Assets.handfontsmall.scale(-UI.FIRSTSCREENTEXTSCALE);
		Assets.handfontsmall.getRegion().getTexture().setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.MipMapLinearNearest);
		for(int i=0;i<testo.size();i++)
		testo.get(i).draw(batcher);
		Assets.handfontsmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Assets.handfontsmall.scale(UI.FIRSTSCREENTEXTSCALE);
		//batcher.draw(Assets.icontextback, 320, 0, -54, 54);
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

	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
