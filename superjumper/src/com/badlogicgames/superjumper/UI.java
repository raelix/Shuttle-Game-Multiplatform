package com.badlogicgames.superjumper;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public interface UI {//MUST FIX LEVELBAR & TEXT RESIZE
	public static final float SCREENWIDTH = Gdx.graphics.getWidth();
	public static final float SCREENHEIGHT = Gdx.graphics.getHeight();
	//ACTIVITY LAYOUT
	public static final float SCREENPOSITIONX=SCREENWIDTH*1.6f;
	public static final float SCREENPOSITIONY=SCREENHEIGHT*1.1f;
	public static final float HALFSCREENWIDTH = SCREENWIDTH/2;
	public static final float HALFSCREENHEIGHT = SCREENHEIGHT/2;
	//FIRST SCREEN
	public static final float FIRSTEXT=UI.SCREENHEIGHT*0.60f;
	public static final float SECONDTEXT=UI.SCREENHEIGHT*0.52f;
	public static final float THIRDTEXT=UI.SCREENHEIGHT*0.44f;
	public static final float FIRSTSCREENTEXTSCALE = (-SCREENWIDTH *0.0008f)*2f;
	//BUTTON
	public static final float BUTTONRESUMEPOSITIONX = SCREENWIDTH*0.35f;
	public static final float BUTTONQUITPOSITIONX = BUTTONRESUMEPOSITIONX;
	public static final float BUTTONRESUMEPOSITIONY = SCREENHEIGHT/2;
	public static final float BUTTONQUITPOSITIONY = BUTTONRESUMEPOSITIONY*0.8f;
	public static final float BUTTONWIDTH = SCREENWIDTH*0.33f;
	public static final float BUTTONHEIGHT = SCREENHEIGHT*0.3f;
	//SCORE POSITION
	public static final float SCOREPOSITIONX = SCREENWIDTH * 0.29f;
	public static final float SCOREPOSITIONY = SCREENHEIGHT * 0.968f;
	//TEXT SCALE FIXME
	public static final float TEXTSCALE = -SCREENWIDTH *0.0012f;
	public static final float TEXTSCALEX = SCREENWIDTH *0.0008f;//FIX ME IN CLASS
	public static final float TEXTSCALEY = SCREENWIDTH *0.0008f;//FIX ME IN CLASS
	//ICONS IN WORLD FIXME
	public static final float INDICATORSIZE = SCREENWIDTH/7;
	public static final float AMMOPOSITIONX = SCREENWIDTH *0.82f;
	public static final float AMMOPOSITIONY = SCREENHEIGHT * 0.9f;
	public static final float POSITIONPORTAPROJX = AMMOPOSITIONX*1.04f;
	public static final float POSITIONPORTAPROJY = SCREENHEIGHT *0.8f;
	public static final float LIFEPOSITIONX = AMMOPOSITIONX;
	public static final float LIFEPOSITIONY = SCREENHEIGHT * 0.8f;
	public static final float POSITIONPORTALIFEX = POSITIONPORTAPROJX;
	public static final float POSITIONPORTALIFEY = SCREENHEIGHT *0.7f;
	public static final float POSITIONPAUSEX = POSITIONPORTAPROJX*1.08f;
	public static final float POSITIONPAUSEY = SCREENHEIGHT *0.95f;
	public static final float TUBOWIDTH = SCREENWIDTH * 0.64f;
	public static final float TUBOHEIGHT = SCREENHEIGHT * 0.43f;
	public static final float TUBOPOSITIONX = 0;
	public static final float TUBOPOSITIONY = SCREENHEIGHT * 0.597f;
	public static final float LEVELPOSITIONX = -53;//FIXME
	public static final float LEVELPOSITIONY = 0;//FIXME
	public static final float BUBBLEPOSITIONX = AMMOPOSITIONX*1.1f;
	public static final float BUBBLEPOSITIONY = SCREENHEIGHT * 0.4f;
	public static final float MISSILEPOSITIONX = BUBBLEPOSITIONX;
	public static final float MISSILEPOSITIONY = SCREENHEIGHT * 0.3f;
	public static final float SUPERMISSILEPOSITIONX = BUBBLEPOSITIONX;
	public static final float SUPERMISSILEPOSITIONY = SCREENHEIGHT * 0.2f;
	
}
