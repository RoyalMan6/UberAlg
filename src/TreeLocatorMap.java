
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	//we have two data structures in this project (BST,TreeLocator) we will use it both here
	//we will use BST to make the data UNIQUE because we can't have same vehicle in same place!
	private Map<K, Location> BSt;
	//we will use Locator for locations
	private Locator<K> Loc;
	
	//has a no-argument constructor
	public TreeLocatorMap() {
		//the first type K >> <STRING> Because it's unique(We can't have same car in same location) and second T type location <DATA>
		BSt = new BST<K,Location>() ;
		//K here is that t there it's the data type
		Loc = new TreeLocator<K>() ;
	}
	//GETTERS
	//just we will return it
	@Override
	public Map<K, Location> getMap() {
		
		return BSt;
	}
	//return the loc
	@Override
	public Locator<K> getLocator() {
		
		return Loc;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		//We will search for key in BST (cause BST doesn't allow Repetition) >> key if it dosen't exist I will add the location
		
		//we will use find() method in BST it will search it if the key found, the first return pair will contain (false) so we will not enter the if condition and we will not insert
		Pair<Boolean, Integer> addsearch = BSt.find(k) ;
		//here if the key not found so okay we will add the key
		if(addsearch.first == false) {   
			// we will insert in loc and BSt if it dosen't exist !!
			Loc.add(k, loc);
			Pair<Boolean, Integer> ins = BSt.insert(k, loc);
			return ins;
		}
		//because the key is already exist so we will not add so it's false
		addsearch.first = false;
		//if it exist we will return the pair it will contain false and number of comparison >> like what (find) return
		return addsearch;
	}

	
	//move to move the car from location to another location just
	//loc here is the new location
	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		// k here is the vehicle
		//Cause all keys in BST I will search on it
		 Pair<Boolean, Integer> movesearch = BSt.find(k);
		 if(movesearch.first != false) {
			 
			 //search if the first pair true it will put current in the location we want to move from (it) so we can know the location from retrieve >> remember The data is location
			 Loc.remove(k, BSt.retrieve()) ;
			 //we will add it again (new location)
			 Loc.add(k, loc) ;
			// after search the current in what we want to update
			 BSt.update(loc);
		 }
		 
		return movesearch;
	}

	//we will return location if it exist and number of comparison just
	@Override
	public Pair<Location, Integer> getLoc(K k) {
		
		//if it exists the first pair will be true and current on it
		Pair<Boolean, Integer> getsearch = BSt.find(k) ;
		//retrieve will bring the location and the search.second will bring number of comparison
		//else if it's not found we will return null and number of comparison of course
		if(getsearch.first)
		return new Pair<Location, Integer>(BSt.retrieve(),getsearch.second) ;
		return new Pair<Location, Integer>(null,getsearch.second) ;
			
			
	}

	
	//removes the element with key k if it ((exists))
	@Override
	public Pair<Boolean, Integer> remove(K k) {
		
		//first we will use find to make sure it is exist and it will put the current on right position
		Pair<Boolean, Integer> removesearch = BSt.find(k) ;
		if(removesearch.first) {
			//we will remove it from Locator, the location in is the data in BST so we will call (.retrieve)
			Loc.remove(k, BSt.retrieve()) ;
			//and BST
			BSt.remove(k) ;
			
		}
		//here we will return search cause if it exist it will enter the condition the first pair will be true sure and it will return false if it's not exist
		return removesearch;
	}

	//it's already in the BST so we will call it just
	@Override
	public List<K> getAll() {

		//in BST we have getAll and it show all keys in increasing order like we want
		return BSt.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		
		Pair<List<K>, Integer> InRangeresult;
		//call inRange in TreeLocator
		Pair<List<Pair<Location, List<K>>>, Integer> all = Loc.inRange(lowerLeft, upperRight) ;
		List<Pair<Location, List<K>>> list = all.first;
		int comp = all.second;
		//the all keys in range we want to be here
		List<K> keys = new LinkedList<K>() ;
		if(!list.empty()) {
			list.findFirst();
			while(!list.last()) {
				//the vehicles in loc object is Unique beacause we use BST in the add here so no worries the vehicles is unique
				//copyTo will copy the element in the list
				copyTo(keys, list.retrieve().second);
				list.findNext();
			}
			//also check the last 
			copyTo(keys, list.retrieve().second);
			
		}
		//method inRange in TreeLocator count the number of comparison so we will use it (all.second)
		InRangeresult = new Pair<List<K>,Integer>(keys,all.second);
		comp=0;
		return InRangeresult;
	}
	//copy list 2 to list 1 the result
	private void copyTo(List<K> list1,List<K> list2) {
		int comp=0;
		
		//if it's empty
		if(list2.empty())
			return;
		//notice here we don't add until we check it's unique so we've check before so we will add directly
		//we will start from the beginning until the last
		list2.findFirst();
		while(!list2.last()) {
				list1.insert(list2.retrieve());
			list2.findNext();
			comp++;
		}
		//the last also insert it if dosen't there
			list1.insert(list2.retrieve());
		//not important I can delete it
		list2.findNext() ;
		
	}
	
	
	

}
