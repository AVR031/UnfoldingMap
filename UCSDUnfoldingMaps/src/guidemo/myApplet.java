package guidemo;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import processing.core.*;

public class myApplet extends PApplet{

	private static final long serialVersionUID = 1L;
	
	public String URL = "https://images.pexels.com/photos/457882/pexels-photo-457882.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260";
	private PImage backImg;
	
	public void setup()
	{
		size(800, 600);
		backImg = loadImage(URL, "jpg");
	}
	public void draw()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ss");
		int a = Integer.parseInt(sdf.format(cal.getTime()));
		
		backImg.resize(0, height);
		image(backImg,0,0);
		ellipse(width/2, height/5, width/5, height/5);
		if(a>0 && a<21)
		{
			fill(255,255,0);
		}
		else if( a>20 && a<41)
		{
			fill(253,94,83);
		}
		else
		{
			fill(192,192,192);
		}
	}
}