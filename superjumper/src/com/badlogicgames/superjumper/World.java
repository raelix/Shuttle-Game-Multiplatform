package com.badlogicgames.superjumper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class World {
    public interface WorldListener {
        public void jump ();
        public void highJump ();
        public void hit ();
        public void coin ();
        public void life ();
        public void projectile();
    }

    public static final float WORLD_WIDTH = 10;
    public static final float WORLD_HEIGHT = 15 * 20;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_GAME_OVER = 2;
    public final int PLATFORMS_DISTANCE = 10;
    public final Bob bob;
    public final List<Platform> platforms;
    public final List<Spring> springs;
    public final List<Squirrel> squirrels;
    public final List<Projectile> projectiles;
    public final List<Coin> coins;
    public final List<Life> lifes;
    public Castle castle;
    public final WorldListener listener;
    public final Random rand;
    public float heightSoFar;
    public int score;
    public int state;
    

    private Vector2 gravity = new Vector2(0,15);

    public World (WorldListener listener) {
        this.bob = new Bob(4, 2);
        this.platforms = new ArrayList<Platform>();
        this.projectiles = new ArrayList<Projectile>();
        this.springs = new ArrayList<Spring>();
        this.squirrels = new ArrayList<Squirrel>();
        this.coins = new ArrayList<Coin>();
        this.lifes = new ArrayList<Life>();
        this.listener = listener;
        this.rand = new Random();
        this.generateLevel();
        this.setGravity(0, 1);
        this.heightSoFar = 0;
        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
        Life life = new Life(0,0);
        lifes.add(life);
        lifes.add(life);
        lifes.add(life);
    }

    private void generateLevel () {
        float y = Platform.PLATFORM_HEIGHT / 2;
        //float maxJumpHeight = Bob.BOB_JUMP_VELOCITY * Bob.BOB_JUMP_VELOCITY / (2 * this.gravity.y);
        float maxJumpHeight = this.PLATFORMS_DISTANCE;
        
        
        while (y < WORLD_HEIGHT - WORLD_WIDTH / 2) {
            
            int type = rand.nextFloat() > 0.8f ? Platform.PLATFORM_TYPE_MOVING : Platform.PLATFORM_TYPE_STATIC;
            //float x = rand.nextFloat() * (WORLD_WIDTH - Platform.PLATFORM_WIDTH) + Platform.PLATFORM_WIDTH / 2;
            float random = rand.nextFloat();
            float x = rand.nextFloat() > 0.5f ? random *3 : WORLD_WIDTH - random *3;
            Platform platform = new Platform(type, x, y);
            platforms.add(platform);
            if (rand.nextFloat() > 0.9f && type != Platform.PLATFORM_TYPE_MOVING) {
                Spring spring = new Spring(platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT / 2 + Spring.SPRING_HEIGHT / 2);
                springs.add(spring);
            }
            if (y > WORLD_HEIGHT / 3 && rand.nextFloat() > 0.8f) {
                Squirrel squirrel = new Squirrel(platform.position.x + rand.nextFloat(), platform.position.y
                    + Squirrel.SQUIRREL_HEIGHT + rand.nextFloat() * 2);
                squirrels.add(squirrel);
            }
            if (rand.nextFloat() > 0.3f) {
                random = rand.nextFloat();
                Coin coin = new Coin(platform.position.x + (platform.position.x < WORLD_WIDTH /2 ? 3*random : -3*random), 
                    platform.position.y + (rand.nextFloat() > 0.5f ? 3*random : -3*random));
                coins.add(coin);
            }
            y += (maxJumpHeight - 0.5f);
            y -= rand.nextFloat() * (maxJumpHeight / 3);
        }
        castle = new Castle(WORLD_WIDTH / 2, y);
        
        
    }

    public void setGravity(float x, float y){
        this.gravity.x = x;
        this.gravity.y = y;
        bob.setGravityBob(x, y);
    }
    
    public void LifeLess(){
        float len = lifes.size();
        int i=0;
        if(i<len && lifes.size() > 1){
            Life life = lifes.get(i);
            lifes.remove(life);
            len = lifes.size();
        
        }
        
        else state = WORLD_STATE_GAME_OVER;
    }

    public Vector2 getGravity(){
        return gravity;
    }

    public void update (float deltaTime, float accelX) {
    
        updateBob(deltaTime, accelX);
        updatePlatforms(deltaTime);
        updateSquirrels(deltaTime);
        updateCoins(deltaTime);
        updateLifes(deltaTime);
        updateProjectiles(deltaTime);
        if (rand.nextFloat() > 0.5f) score += (int)bob.velocity.y;
        if (bob.state != Bob.BOB_STATE_HIT) checkCollisions();
        checkRemovePlatform();
        checkRemoveProjectile();
        checkGameOver();
        
    }

    private void updateBob (float deltaTime, float accelX) {
        if (bob.state != Bob.BOB_STATE_HIT && bob.position.y <= 0.5f) bob.hitPlatform();
        if (bob.state != Bob.BOB_STATE_HIT) bob.velocity.x = -accelX / 10 * Bob.BOB_MOVE_VELOCITY;
        bob.update(deltaTime);
        heightSoFar = Math.max(bob.position.y, heightSoFar);
    }

    private void updatePlatforms (float deltaTime) {
        int len = platforms.size();
        for (int i = 0; i < len; i++) {
            Platform platform = platforms.get(i);
            platform.update(deltaTime);
            if (platform.state == Platform.PLATFORM_STATE_PULVERIZING && platform.stateTime > Platform.PLATFORM_PULVERIZE_TIME) {
                platforms.remove(platform);
                len = platforms.size();
            }
        }
    }

    private void updateSquirrels (float deltaTime) {
        int len = squirrels.size();
        for (int i = 0; i < len; i++) {
            Squirrel squirrel = squirrels.get(i);
            squirrel.update(deltaTime);
        }
    }

    private void updateCoins (float deltaTime) {
        int len = coins.size();
        for (int i = 0; i < len; i++) {
            Coin coin = coins.get(i);
            coin.update(deltaTime);
        }
    }
    
    private void updateProjectiles (float deltaTime) {
        int len = projectiles.size();
        for (int i = 0; i < len; i++) {
            Projectile projectile = projectiles.get(i);
            projectile.update(deltaTime);
        }
    }

    
    private void updateLifes (float deltaTime) {
        int len = lifes.size();
        for (int i = 0; i < len; i++) {
            Life life = lifes.get(i);
            life.update(deltaTime);
        }
    }
    
    private void checkRemoveProjectile(){
        int i = 0;
        if (!projectiles.isEmpty()) {
        if ( projectiles.get(i).position.y > bob.position.y+12 ) 
            projectiles.remove(i);
        }
    }

    private void checkRemovePlatform() {
        if (bob.position.y > platforms.get(0).position.y+5  ) platforms.remove(0);
    }
    
    private void checkCollisions () {
        checkPlatformCollisions();
        checkDoubleJump();
        checkSquirrelCollisions();
        checkItemCollisions();
        checkCastleCollisions();
        checkVelocity();
        checkProjectileCollisions();
        
    }
    private void checkProjectileCollisions(){
        int i = 0, j = 0;
        if (!projectiles.isEmpty() && !platforms.isEmpty()){
      	  
           /* Platform platformstmp;
        int len= platforms.size();
        int len1 = projectiles.size();
       if (platforms.get(0).position.y > projectiles.get(0).position.y)
                return;
            else if (OverlapTester.overlapRectangles(platforms.get(0).bounds, projectiles.get(0).bounds)){
                platforms.get(0).pulverize();
                projectiles.remove(0);
                return;
            }
            else {
                Platform primaplatform = platforms.get(i);
                for (; i < projectiles.size(); i++) {
                    //#FIXME
                    for (platformstmp = platforms.get(0); projectiles.get(j).position.y > primaplatform.position.y; j++){
                        if (OverlapTester.overlapRectangles(platformstmp.bounds, projectiles.get(j).bounds)) {
                          platforms.get(j).pulverize();
                           projectiles.remove(i);
                        }
                    }
                }
            }
        */
      	 
      	  
      	  for(i=0;i<projectiles.size();i++)
      	  	{
      		  for(j=0;j<platforms.size();j++)
      		  {
      			  Projectile projectile=projectiles.get(i);
      			  Platform platform=platforms.get(j);
      			  if (platform.state != Platform.PLATFORM_STATE_PULVERIZING && OverlapTester.overlapRectangles(platform.bounds, projectile.bounds)) {
      				  projectiles.remove(i);
      				  i--;
      				  platform.pulverize();
                    /*platforms.remove(j);*/
                     
                     break;
                  }
      			  
      			}
      	  	 }
        }
    }
    
    private void checkVelocity () {
        if (bob.velocity.y > bob.MAXVELOCITY){
            bob.setGravityBob(0, 0);
        }
    }

    private void checkPlatformCollisions () {
        //if (bob.velocity.y > 0) return;
        int len = platforms.size();
        for (int i = 0; i < len; i++) {
            Platform platform = platforms.get(i);
            if (bob.position.y > platform.position.y) {
                if (platform.state != Platform.PLATFORM_STATE_PULVERIZING && OverlapTester.overlapRectangles(bob.bounds, platform.bounds)) {
                    bob.hitPlatform();
                    Gdx.input.vibrate(new long[] { 1, 90, 40, 90}, -1); 
                    platform.pulverize();
                    score -= 50;
                    LifeLess();
                    listener.jump();
                    len = platforms.size();
                    break;
                }
            }
        }
    }
        
    private void checkDoubleJump() {
        if(Bob.BOB_DOUBLE_JUMP){
            Bob.BOB_DOUBLE_JUMP=false;
            bob.hitPlatform();
            listener.hit();
        }
    }

    private void checkSquirrelCollisions () {
        int len = squirrels.size();
        for (int i = 0; i < len; i++) {
            Squirrel squirrel = squirrels.get(i);
            if (OverlapTester.overlapRectangles(squirrel.bounds, bob.bounds)) {
                bob.hitSquirrel();
                bob.setGravityBob(0, -5);
                listener.hit(); 
                break;
            }
        }
    }

    private void checkItemCollisions () {
        int len = coins.size();
        for (int i = 0; i < len; i++) {
            int p=0;
            Coin coin = coins.get(i);
            if (OverlapTester.overlapRectangles(bob.bounds, coin.bounds)) {
                
                coins.remove(coin);
              //  LifeLess();
                len = coins.size();
                listener.coin();
                score += Coin.COIN_SCORE;
                break;
            }
        }
        if (bob.velocity.y > 0) return;
        len = springs.size();
        for (int i = 0; i < len; i++) {
            Spring spring = springs.get(i);
            if (bob.position.y > spring.position.y) {
                if (OverlapTester.overlapRectangles(bob.bounds, spring.bounds)) {
                    bob.hitSpring();
                    listener.highJump();
                    break;
                }
            }
        }
    }

    private void checkCastleCollisions () {
        if (OverlapTester.overlapRectangles(castle.bounds, bob.bounds)) {
            state = WORLD_STATE_NEXT_LEVEL;
        }
    }

    private void checkGameOver () {
        if (heightSoFar - 7.5f > bob.position.y) {
            state = WORLD_STATE_GAME_OVER;
        }
        int i = 0;
        Life life = lifes.get(i);
        if (i<0){ state = WORLD_STATE_GAME_OVER;}
    }
}