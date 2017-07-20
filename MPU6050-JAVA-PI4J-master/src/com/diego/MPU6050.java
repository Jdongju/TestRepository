
package com.diego;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MPU6050 {
    
   // Configuration Logs -------------------------------              
        public static final byte SMPLRT_DIV     = 25;
        public static final byte CONFIG         = 26;
        public static final byte ACCEL_CONFIG   = 28;
        public static final byte FIFO_EN        = 35;
        public static final byte INT_ENABLE     = 56;  
        public static final byte PWR_MGMT_1     = 107;
        public static final byte PWR_MGMT_2     = 108;
     // end of the configuration log ---------------------
        
    // Accelerometer Resolutions --------------------------------
        public static final double resolAc0  = 0.000061035; 
        public static final double resolAc1  = 0.00012207;
        public static final double resolAc2  = 0.00024414;
        public static final double resolAc3  = 0.000488281;
   // End of accelerometer resolutions ---------------------

     // Class variables for the I2C bus
        private I2CBus bus;
        private I2CDevice acelerometro;
   // End of the variables for the I2C bus
    
  // Variables to store accelerometer components .................
        private double acX = 0.0, acY = 0.0, acZ = 0.0;
     // End of variables ............................................ (I.e.
        
    // Class builder    
    public MPU6050() throws IOException, I2CFactory.UnsupportedBusNumberException {  
        
      // Initializing I2C communication ********************
            bus = I2CFactory.getInstance( I2CBus.BUS_1 );
            acelerometro = bus.getDevice( 0x68 ); 
        // End of I2C communication initialization ******
            
         // Initializing the accelerometer *****************************************
            
            
            acelerometro.write( SMPLRT_DIV,     (byte) 0b00000000 );
            acelerometro.write( CONFIG,         (byte) 0b00000000 );
            
            acelerometro.write( ACCEL_CONFIG,   (byte) 0b00000000 );
            acelerometro.write( ACCEL_CONFIG,   (byte) 0b00001000 );
            acelerometro.write( ACCEL_CONFIG,   (byte) 0b00010000 );
            acelerometro.write( ACCEL_CONFIG,   (byte) 0b00011000 );
            
            acelerometro.write( FIFO_EN,        (byte) 0b00000000 );
            acelerometro.write( INT_ENABLE,     (byte) 0b00000000 );
            acelerometro.write( PWR_MGMT_1,     (byte) 0b00000001 );
            acelerometro.write( PWR_MGMT_2,     (byte) 0b00000000 );
            
        // End of accelerometer setup ******************************
            
        comenzarLectura();
    }// End of constructor   
    
   // Method to read the MPU6050
    public void comenzarLectura(){
              
        // Arrays to store the values ​​of the components X, Y, Z of the accelerometer
        // and the gyroscope
            byte[] accelData = new byte[6];
      // End of the declaration of the arrays ......................................... ....
            
        // Delay time in the While loop **
            final int tiempo = 1;
      // End of delay time ************
            
        // Sampling time for low pass filter and pass high ***
            final double t = tiempo / 1000.0;
        // End sampling time **********************************
            
            
        // Variables for lowpass accelerometer filter ******************
            double acX_anterior = 0.0, acY_anterior = 0.0, acZ_anterior = 0.0;
            double acX_filtrado = 0.0, acY_filtrado = 0.0, acZ_filtrado = 0.0;
            
         /* Wo1 is the cutoff frequency
             Ao1 is the filter gain */
            final double ao1 = 1, Wo1 = 5;  
                         
           // Definition of the constants for low pass filter
                final double a = ( ao1*t ) / ( 2 + Wo1*t );
                final double b = t / ( 2 + Wo1*t );
                final double c = ( Wo1*t - 2 ) / ( 2 + Wo1*t );
          // End of constants definition *************
        // End of variables for low pass filter ************************
                                
        int i = 0;
        while( true ){
            try {
                
                 // Read the accelerometer ******************************************
                    acelerometro.read(0x3B, accelData, 0, 6);                    
                    acX = (resolAc0 * make16( accelData[0],  accelData[1] ));
                    acY = (resolAc0 * make16( accelData[2] , accelData[3] ));
                    acZ = (resolAc0 * make16( accelData[4] , accelData[5] ));                       
               // End of lecutra ********************************************  
                                             
             // Equation in low pass filter differences *****************
                    acX_filtrado = a*acX + b*acX_anterior - c*acX_filtrado;
                    acX_anterior = acX;
                    
                    acY_filtrado = a*acY + b*acY_anterior - c*acY_filtrado;
                    acY_anterior = acY;
                    
                    acZ_filtrado = a*acZ + b*acZ_anterior - c*acZ_filtrado;
                    acZ_anterior = acZ;
                // End of equation in low pass filter differences *******    
                    
                // Calculation of alpha and filtered theta ****************************************** **************
                    double w1 = Math.sqrt( Math.pow( acZ_filtrado , 2) + Math.pow( acY_filtrado , 2) );
                    double v1 = Math.sqrt( Math.pow( acX_filtrado , 2) + Math.pow( acY_filtrado , 2) );
                
                    double theta_filtrado = Math.toDegrees( Math.atan( acX_filtrado / w1 ) );                               
                    double alpha_filtrado = Math.toDegrees( Math.atan( acZ_filtrado / v1 ) );
                    System.out.printf("Theta = "+  theta_filtrado+"Alpha = "+alpha_filtrado+"\n");
										
             // End of alpha and filtered theta calculation **************************************** (I.e.              
                    
            Thread.sleep( tiempo );
                    
            } catch (IOException ex) {
                Logger.getLogger(MPU6050.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MPU6050.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                i++;
            }       
                
        } // End of while loop
    }  // End method to read the accelerometer  
    
    private int make16( int high, int low ){
        // This method converts two 8-bit variables into a single 16-bit variable
        return ( (high << 8) | low );
    }
}
