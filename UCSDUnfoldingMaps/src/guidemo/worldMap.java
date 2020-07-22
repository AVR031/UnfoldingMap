package guidemo;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class worldMap extends PApplet{

	private static final long serialVersionUID = 1L;
	private UnfoldingMap map;
	
	public void setup()
	{
		size(950,600, OPENGL);
		map = new UnfoldingMap(this, 125, 50, 700, 500, new OpenStreetMap.OpenStreetMapProvider());
		map.zoomLevel(1);
		MapUtils.createMouseEventDispatcher(this, map);
	}
	public void draw()
	{
		background(100);
		map.draw();
		addKey();
	}
	public void addKey()
	{
		Location valLoc = new Location(30.6f, -96.35f);
		SimplePointMarker sp = new SimplePointMarker(valLoc);
		map.addMarker(sp);
	}
}