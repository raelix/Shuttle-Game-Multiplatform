
package com.badlogicgames.superjumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Assets {
	public static ParticleEffect particleEffect;
	public static Array laserPEmitters;
	public static Texture star;
	public static Texture shuttle;
	public static Texture shuttle1;
	public static Texture portaproj;
	public static Texture disegno;
	public static Texture nuvole;
	public static Texture nuvole1;
	public static Texture nuvole2;
	public static Texture nuvole3;
	public static Texture coin1;
	public static Texture coin2;
	public static Texture coin3;
	public static Texture coin4;
	public static Texture coin6;
	public static Texture coin7;
	public static Texture coin8;
	public static Texture coin9;
	public static Texture Pause;
	public static Texture background;
	public static Texture background1;
	public static Texture background2;
	public static Texture background3;
	public static Texture background4;
	public static Texture background5;
	public static Texture background6;
	public static Texture background7;
	public static Texture background8;
	public static Texture background9;
	public static Texture background10;
	public static Texture background11;
	public static Texture background12;
	public static Texture background13;
	public static Texture background14;
	public static Texture background15;
	public static Texture background16;
	public static Texture background17,background18,background19,background20;
	public static Texture backgroundmain;
	public static Texture backgroundmain1;
	public static Texture projectile;
	public static Texture projectile1;
	public static Texture items;
	public static Texture life;
	public static Texture life1;
	public static Texture mainmenu;
	public static Texture SoundOn;
	public static Texture SoundOff;
	public static TextureRegion starRegion;
	public static TextureRegion backgroundRegion;
	public static TextureRegion backgroundRegion1;
	public static TextureRegion backgroundRegion2;
	public static TextureRegion backgroundRegion3;
	public static TextureRegion disegno1;
	public static TextureRegion backgroundRegion4;
	public static TextureRegion backgroundRegion5;
	public static TextureRegion backgroundRegion6,backgroundRegion7,backgroundRegion8,backgroundRegion9,backgroundRegion10,backgroundRegion11,backgroundRegion12,backgroundRegion13,backgroundRegion14,backgroundRegion15,backgroundRegion16,backgroundRegion17,backgroundRegion18,backgroundRegion19,backgroundRegion20,backgroundRegion21;
	public static TextureRegion backgroundRegionmain;
	public static TextureRegion mainMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion ready;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;
	public static TextureRegion spring;
	public static TextureRegion castle;
	public static Animation coinAnim;
	public static Animation backAnim;
	public static Animation lifeAnim;
	public static Animation projAnim;
	public static Animation bobJump;
	public static Animation breakanim;
	public static Animation bobFall;
	public static TextureRegion bobHit;
	public static Animation squirrelFly;
	public static TextureRegion platform;
	public static Animation brakingPlatform;
	public static BitmapFont font;
	public static Pixmap pixmap;
	public static Texture tmptext;
	public static TextureRegion  rect;
	public static Texture tmptext1;
	public static Pixmap pixmap1;
	public static Color colore;
	public static Music music;
	public static Sound jumpSound;
	public static Sound highJumpSound;
	public static Sound hitSound;
	public static Sound coinSound;
	public static Sound clickSound;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load () {
		pixmap1=new Pixmap(512, 512, Pixmap.Format.RGBA8888);
		tmptext1 = new Texture(pixmap1);
		colore=new Color();
		colore.set(0, 0, 1, 1);
		colore.mul(4.7f);
		pixmap1.setColor(colore);
		pixmap1.fillRectangle(0, 0, 512, 512);
		tmptext1.draw(pixmap1, 0, 0);
		rect = new TextureRegion(tmptext1, 0, 0, 10, 15);
		//fuoco dietro bob
		particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal("data/ecco.p"), Gdx.files.internal("data"));
		laserPEmitters = new Array(particleEffect.getEmitters());
		particleEffect.getEmitters();
		particleEffect.allowCompletion();
		star = loadTexture("data/particle.png");
		nuvole = loadTexture("data/nuvole.png");
		nuvole1 = loadTexture("data/nuvole1.png");
		nuvole2 = loadTexture("data/nuvole2.png");
		nuvole3 = loadTexture("data/nuvole3.png");
		coin1 = loadTexture("data/coin1.png");
		coin2 = loadTexture("data/coin2.png");
		coin3 = loadTexture("data/coin3.png");
		coin4 = loadTexture("data/coin4.png");
		coin6 = loadTexture("data/coin6.png");
		coin7 = loadTexture("data/coin7.png");
		coin8 = loadTexture("data/coin8.png");
		coin9 = loadTexture("data/coin9.png");
		background = loadTexture("data/worldini.png");

		backgroundmain = loadTexture("data/mainsfondo1.png");
		backgroundmain1 = loadTexture("data/mainsfondo2.png");
		Pause = loadTexture("data/pause.png");
		shuttle = loadTexture("data/alieno1.png");
		shuttle1 = loadTexture("data/alieno2.png");
		mainmenu = loadTexture("data/imain.png");
		SoundOn = loadTexture("data/play.png");
		SoundOff = loadTexture("data/stop.png");
		life = loadTexture("data/life.png");
		life1= loadTexture("data/life1.png");
		projectile= loadTexture("data/projectile.png");
		projectile1= loadTexture("data/projectile.png");
		portaproj= loadTexture("data/portaproj.png");
		pixmap = new Pixmap(2048, 2048, Pixmap.Format.RGBA8888);
		tmptext = new Texture(pixmap);
		DrawSmiley();
		backgroundmain.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		backgroundmain1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		shuttle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		shuttle1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		mainmenu.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		SoundOn.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		SoundOff.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Pause.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		life.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		life1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		starRegion= new TextureRegion(star, 0, 0, 128, 128);
		backgroundRegion = new TextureRegion(tmptext, 0, 0, 1024, 1280);
		backgroundRegion1 = new TextureRegion(background, 2, 4, 1024, 1024);
		backgroundRegionmain = new TextureRegion(backgroundmain, 3,0, 1024, 1024);
		items = loadTexture("data/items.png");
		items.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		mainMenu = new TextureRegion(mainmenu, 0, 0, 346, 512);
		pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
		ready = new TextureRegion(items, 320, 224, 192, 32);
		gameOver = new TextureRegion(items, 352, 256, 160, 96);
		highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
		logo = new TextureRegion(items, 0, 352, 274, 142);
		soundOff = new TextureRegion(SoundOff, 0, 0, 110, 128);
		soundOn = new TextureRegion(SoundOn, 0, 0, 110, 128);
		arrow = new TextureRegion(items, 0, 64, 64, 64);
		pause = new TextureRegion(Pause, 0, 0, 120, 128);
		spring = new TextureRegion(items, 128, 0, 32, 32);
		castle = new TextureRegion(items, 128, 64, 64, 64);
		coinAnim = new Animation(0.2f, new TextureRegion(coin1, 0, 0, 128, 128), new TextureRegion(coin2, 0, 0, 128, 128),
			new TextureRegion(coin3, 0, 0, 128, 128), new TextureRegion(coin4, 0, 0, 128, 128));
		backAnim = new Animation(0.2f, new TextureRegion(backgroundmain, 0, 0, 1024, 1024), new TextureRegion(backgroundmain1, 0, 0, 1024, 1024));
		breakanim = new Animation(0.2f, new TextureRegion(coin6, 0, 0, 128, 128), new TextureRegion(coin7, 0, 0, 128, 128),
			new TextureRegion(coin8, 0, 0, 128, 128), new TextureRegion(coin9, 0, 0, 128, 128));
		lifeAnim = new Animation(0.5f, new TextureRegion(life, 0, 0, 120, 128), new TextureRegion(life1, 0, 0, 120, 128));
		projAnim = new Animation(0.2f, new TextureRegion(projectile, 0, 0, 64, 64), new TextureRegion(projectile1, 1, 0, 64, 64));
		bobJump = new Animation(0.2f, new TextureRegion(shuttle, 0, 0, 512, 512), new TextureRegion(shuttle1, 0, 0, 512, 512));
		bobFall = new Animation(0.2f, new TextureRegion(shuttle, 0, 0, 512, 512), new TextureRegion(shuttle1, 0, 0, 512, 512));
		bobHit = new TextureRegion(items, 128, 128, 32, 32);
		squirrelFly = new Animation(0.2f, new TextureRegion(items, 0, 160, 32, 32), new TextureRegion(items, 32, 160, 32, 32));
		platform = new TextureRegion(nuvole, 0, 0, 250, 250);
		brakingPlatform = new Animation(0.2f, new TextureRegion(nuvole, 0, 0, 250, 250), new TextureRegion(nuvole1, 0, 0, 250, 250),
			new TextureRegion(nuvole2, -1, -3, 250, 250), new TextureRegion(nuvole3, 0, 0, 250, 250));
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		if (Settings.soundEnabled) music.play();
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("data/jump.wav"));
		highJumpSound = Gdx.audio.newSound(Gdx.files.internal("data/highjump.wav"));
		hitSound = Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
		coinSound = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
	}
	private static void DrawSmiley(){
		Gdx.app.log("MyLibGDXGame", "Game.DrawSmiley()");

		pixmap.setColor(1, 1, 0, 1);
		pixmap.fillCircle(512/2, 512/2, 512/2);

		//first draw a black circle for the smile
		pixmap.setColor(0, 0, 0,1);
		pixmap.fillCircle(512/2, 280, 160);

		//then a yellow larger over it, to make it look like a partial circle/ a smile
		pixmap.setColor(1, 1, 0, 1);
		pixmap.fillCircle(512/2, 200, 200);

		//now draw the two eyes
		pixmap.setColor(0, 0, 0,1);
		pixmap.fillCircle(512/3, 200, 60);
		pixmap.fillCircle(512-512/3, 200, 60);

		tmptext.draw(pixmap, 0, 0);

		//tmptext.bind();
	}
	public static void playSound (Sound sound) {
		if (Settings.soundEnabled) sound.play(1);
	}
}