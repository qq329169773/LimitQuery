package com.jd.MethodLimit;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.util.Utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public static void main(String[] args) throws InterruptedException, IOException {
    	Map<String, MethodExecuteLimitBean> data = new HashMap<>();
		for(int index = 0 ; index < 100000 ; index++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
					} catch (InterruptedException e) {
 						e.printStackTrace();
					}
				}
			}).start();
 		}
  		System.out.println("finish" + data.size());
  		Reader reader = new InputStreamReader(System.in);
  		reader.read();
	}
}
