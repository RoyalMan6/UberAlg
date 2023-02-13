public class VehicleHiringManager {

	//here we will use TreeLocatorMap that have two data structures (BST and Locator) and the key type is String  
	LocatorMap<String> LMap ;
	
	//the constructor
	public VehicleHiringManager() {
		
		LMap = new TreeLocatorMap<String>() ;
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		
		//getter
		return LMap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		
		//setter
		this.LMap = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		
		//we will use add in TreeLocatorMap
		Pair<Boolean,Integer> addresult ;
		addresult = LMap.add(id, loc) ;
		//if it's added the first pair will be true of course
		if(addresult.first == true)
		return true;
		return false;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		
		//we will use move in TreeLocatorMap
		Pair<Boolean,Integer> moveresult ;
		moveresult = LMap.move(id, loc) ;
		
		//if it's moved the first pair will be true
		if(moveresult.first == true)
		return true;
		return false;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		
		//we will use remove in TreeLocatorMap
		Pair<Boolean,Integer> removeresult ;
		removeresult = LMap.remove(id);
		//if it's removed the first pair will be true
		if(removeresult.first == true)
		return true;
		return false;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {

		//we will use getLocation in TreeLocatorMap
		Pair<Location, Integer> getlocresult ;
		getlocresult = LMap.getLoc(id);
		//if the location exist it will return it and null if not
		if(getlocresult.first != null) {
		int x1 = getlocresult.first.x ;
		int y1 = getlocresult.first.y ;
		Location vLoc = new Location(x1,y1) ;
		return vLoc; 
		}
		//here null
		return getlocresult.first;
		
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		Pair<List<String>, Integer> VecINRresult ;
		int x1 = loc.x-r;
		int y1 = loc.y-r;
		int x2 = loc.x+r;
		int y2 = loc.y+r;
		Location low = new Location(x1,y1) ;
		Location up = new Location(x2,y2) ;
		VecINRresult = LMap.getInRange(low,up) ;
		return VecINRresult.first;
	}
}
