package guidemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class lifeExpectancy extends PApplet{

	private static final long serialVersionUID = 1L;
	private UnfoldingMap map;
	
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	Map<String, Float> dataLife;
	
	public void setup() 
	{
		size(800,600, OPENGL);
		map = new UnfoldingMap(this,50, 50, 800, 600, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		dataLife = loadDataFile("LifeExpectancyWorldBank.csv");
		
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	public Map<String, Float> loadDataFile(String fileName)
	{
		Map<String, Float> lifeMap = new HashMap<String, Float>();
		String[] rows = loadStrings(fileName);
		
		for (String row : rows) {
			String[] spl  = row.split(",");
			if(spl[5].length()>3)
			{
				float val = Float.parseFloat(spl[5]);
				lifeMap.put(spl[4], val);
			}
		}
				
		return lifeMap;
	}
	
	public void shadeCountries()
	{
		for(Marker marker: countryMarkers)
		{
			String cid = marker.getId();
			if(dataLife.containsKey(cid))
			{
				float life = dataLife.get(cid); 
				int colorLevel = (int) map(life,40,90,10,255);
				marker.setColor(color(255-colorLevel,100, colorLevel));
			}
			else
			{
				marker.setColor(color(150,150,150));
			}
		}
	}
	
	public void draw()
	{
		map.draw();
	}
}
