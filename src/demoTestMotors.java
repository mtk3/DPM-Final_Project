


import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;

public class demoTestMotors {
	
	// Loading motor is connected to output B
	public static final EV3LargeRegulatedMotor loadingMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("B"));
	// Gear motor is connected to output C
	public static final EV3LargeRegulatedMotor winchMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("C"));
	
	// Instance the text display
	public static int buttonC;
	
	//final static TextLCD t = LocalEV3.get().getTextLCD();
		
		public  void load(){
		
			
			// set winchMotor acceleration and speed
			winchMotor.setAcceleration(300);
			winchMotor.setSpeed(300);
			
			// set loadingMotor initial acceleration and speed
			loadingMotor.setAcceleration(300);
			loadingMotor.setSpeed(400);
			
			Sound.setVolume(40);
			
			
				
				// Get the position of the target from the user
				
					
					// TODO: get in front of the dispenser
					
					// ready the loading arm to receive the ball
					loadingMotor.rotate(90);
					
					// beep, and wait 5 seconds to receive the ball
					//Sound.beep();
					try {
					    Thread.sleep(5000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					
					// pull back the elastic to the desired position
					winchMotor.rotate(1500);
										
					// load the ball into the launcher and hold the elastic in position
					loadingMotor.rotate(-110);
					
					// TODO: navigate to the firing line and turn to the firing position
			
		}
		
		public void launcher(){
			
			// unwind the winch to ensure the launcher can fire at full power
			winchMotor.rotate(-850); 		// full unwind is (-1440), (-1000),(-900) = still slightly too far, (-800)
			
			// set the loading arm to firing acceleration and speed, then release the ball
			loadingMotor.setAcceleration(4000);
			loadingMotor.setSpeed(500);
			loadingMotor.rotate(150);
			
			// reset the arm acceleration, speed and position
			loadingMotor.setAcceleration(300);
			loadingMotor.setSpeed(400);
			loadingMotor.rotate(-150);
			
			// reset the winch position
			winchMotor.rotate(-740);		// fully unwind as if it was (-1440)
		}
		
		public static int getButtonChoise()
		{	
			// holds the user's button choice
			int buttonChoice;
			
			// poll the button input until either the up, left or right button is pressed
			do {
				
				// clear the display
				

				buttonChoice = Button.waitForAnyPress();
			} while (buttonChoice != Button.ID_LEFT
					&& buttonChoice != Button.ID_RIGHT && buttonChoice != Button.ID_UP && buttonChoice!= Button.ID_DOWN
					&& buttonChoice != Button.ID_ESCAPE);
			
			// return the button pressed by the user
			return buttonChoice;
		}
}