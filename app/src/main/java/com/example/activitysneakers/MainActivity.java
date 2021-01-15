package com.example.activitysneakers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {
    Canvas canvas;
    SnakeAnimView snakeAnimView;
    Bitmap headAnimBitmap;
Bitmap bg_color;
Bitmap judul;
    //the portion of the bitmap to be drawn in the current frame
    Rect rectToBeDrawn;

    //The dimensions of  a single frame
    int frameHeight = 64;
    int frameWidth = 64;
    int numFrames  = 6;
    int frameNumber;

    int screenWidth;
    int screenHeight;

    //stats
    long lastFrameTime;
    int fps;
    int hi;


    //to start the game from onTouchEvent
    Intent i,p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        headAnimBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.play2);
        bg_color = BitmapFactory.decodeResource(getResources(),R.drawable.forest_background);
        judul = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        snakeAnimView = new SnakeAnimView(this);



            setContentView(snakeAnimView);

            i = new Intent(this,GameActivity.class);
          //  p= new Intent(this,CreditActivity.class);
        p = new Intent(this,CreditActivity.class);
    }

   public class SnakeAnimView extends SurfaceView implements Runnable {
    Thread ourThread = null;
    SurfaceHolder ourHolder;
    volatile boolean playingSnake;
    Paint paint;
    public SnakeAnimView(Context context){
        super(context);
        ourHolder = getHolder();
        paint = new Paint();
    frameHeight = headAnimBitmap.getHeight();
    frameWidth = headAnimBitmap.getWidth();
    }

       @Override
       public void run() {
           while (playingSnake){

               //update();
               Draw();
               //controlFPS();
           }
       }

       private void controlFPS() {
           long timeThisFrame = (System.currentTimeMillis() - lastFrameTime);
           long timeToSleep = 500 - timeThisFrame;

           if (timeThisFrame > 0) {
               fps = (int) (1000 / timeThisFrame);
           }
           if (timeToSleep > 0) {
               try {
                   ourThread.sleep(timeToSleep);
               } catch (InterruptedException e) {

               }
           }
           lastFrameTime = System.currentTimeMillis();
       }
       private void Draw() {

           if (ourHolder.getSurface().isValid()){
               canvas = ourHolder.lockCanvas();

             //  canvas.drawColor(Color.RED);//background color
              Rect destRect2 = new Rect(screenWidth/200,screenHeight/200,screenWidth/1,screenHeight/1);
              canvas.drawBitmap(bg_color,rectToBeDrawn,destRect2,paint);
              // paint.setColor(Color.argb(155,25,55,255));
              // paint.setTextSize(150);
           //   canvas.drawText("The Snakers",50,150,paint);
               paint.setTextSize(25);

               canvas.drawText(" Hi Score: "+hi,10,screenHeight-50,paint);


               //make this Rect whatever size and location as i like

              Rect destRect = new Rect(screenWidth/2-150,screenHeight/2-100,screenWidth/2+150,screenHeight/2+300);


               canvas.drawBitmap(headAnimBitmap,rectToBeDrawn,destRect,paint);
               Rect destRect3 = new Rect(screenWidth/2-500,screenHeight/2-1050,screenWidth/2+500,screenHeight/2+100);
               //Draw the snake head
               canvas.drawBitmap(judul,rectToBeDrawn,destRect3,paint);
               ourHolder.unlockCanvasAndPost(canvas);


           }
       }


       private void update() {

           rectToBeDrawn = new Rect((frameNumber * frameWidth) - 1,0,
                   (frameNumber * frameWidth+frameWidth)-1,frameHeight);

           //now the next frame
           frameNumber++;


           //don't try and draw frames that don't exist
           if (frameNumber == numFrames){
               frameNumber = 0;//back to the first frame
           }
       }


       public void pause(){
           playingSnake = false;
           try {
               ourThread.join();
           }catch (InterruptedException e){

           }
       }

       public void resume(){
           playingSnake = true;
           ourThread = new Thread(this);
           ourThread.start();
       }

       @Override
       public boolean onTouchEvent(MotionEvent event) {
            startActivity(i);
            //mulaiActivity(p);
            //startActivity(p);
        return  true;
       }
   }

    @Override
    protected void onStop() {
        super.onStop();
        while (true){
            snakeAnimView.pause();
            break;
        }
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        snakeAnimView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        snakeAnimView.pause();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            snakeAnimView.pause();
            finish();
            return true;
        }
        return false;
    }
}