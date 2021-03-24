package fr.dut.info;

public abstract class MapBuilder {
	private static MapBuilder instance = null;
	
	private MapBuilder() {}
	
	public static MapBuilder getMapBuilder() {
		if (instance == null) {
			instance = new MapBuilder();
		}
		return instance;
	}
	
	public abstract Map createMap(int nbRooms, int lvl);
}
