package fr.dut.info.rooms;

public class MapBuilder {
	private static MapBuilder instance = null;
	
	private MapBuilder() {}
	
	public static MapBuilder getMapBuilder() {
		if (instance == null) {
			instance = new MapBuilder();
		}
		return instance;
	}
	
	public Map createMap(int nbRooms, int lvl) {
		
	}
}
