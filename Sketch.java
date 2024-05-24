import processing.core.PApplet;

/**
 * @description Code for the Snowflake Game
 * @author Steve Lin
 */

public class Sketch extends PApplet {

  // initialize variables 

  float[] fltCircleY = new float[25];
  float[] fltCircleX = new float[25];
  float[] fltCircleSpeed = new float[25];
  boolean[] ballHideStatus = new boolean[25];
  
  float fltBlueCircleX = 150;
  float fltBlueCircleY = 150;

  int intLives = 3; 
  float fltSnowfallSpeed = 1;

  public void settings() {
    size(800, 400);
  }

  public void setup() {

    // randomizes snowflake speed and x and y postition
    background(210, 255, 173);
    for (int i = 0; i < fltCircleY.length; i++) {

      fltCircleY[i] = random(height);
      fltCircleX[i] = random(width);
      fltCircleSpeed[i] = random(2);
      ballHideStatus[i] = false;

    }
  }

  public void draw() {

    background(50);

    // prints snowflakes to screen
    for (int i = 0; i < fltCircleY.length; i++) {

      if (!ballHideStatus[i]) {
        fill(255);
        ellipse(fltCircleX[i], fltCircleY[i], 30, 30);
        fltCircleY[i] += fltSnowfallSpeed;

        if (fltCircleY[i] > height) {
          fltCircleY[i] = 0;

        }

        // If the blue circle collides with the snowflake the lives decrease by one
        if (dist(fltCircleX[i], fltCircleY[i], fltBlueCircleX, fltBlueCircleY) < 35) {

          intLives--; 
          ballHideStatus[i] = true; 

        }

        // hides the snow if the it is clicked 
        if (mousePressed) {

          if (dist(fltCircleX[i], fltCircleY[i], mouseX, mouseY) < 15) {
          ballHideStatus[i] = true; 

          }
        }
      }
    }

    // prints lives to screen
    fill(255, 0, 0);

    for (int i = 0; i < intLives; i++) {
      rect(width - 50 - i * 30, 20, 20, 20);
    }

    // print the blue circle
    fill(0, 0, 255);
    ellipse(fltBlueCircleX, fltBlueCircleY, 40, 40);

    // moves the blue circle using WASD and slows/speeds up the circle via arrow keys
    if (keyPressed) {

      if (key == 'w') {
        fltBlueCircleY -= 2;
      } 

      else if (key == 's') {
        fltBlueCircleY += 2;
      } 

      else if (key == 'a') {
        fltBlueCircleX -= 2;
      } 
      
      else if (key == 'd') {
        fltBlueCircleX += 2;
      }

      else if (keyCode == UP && fltSnowfallSpeed > 0.1f) {
        fltSnowfallSpeed -= 0.1f;
      }

      else if (keyCode == DOWN) {
        fltSnowfallSpeed += 0.1f;
      }
    }

    // turns the screen white and ends program if there are no more lives
    if (intLives == 0) {
      background(255);
      noLoop();

    }
  }
}