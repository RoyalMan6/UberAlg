
public class TreeLocator<T> implements Locator<T> {
	private TreeLocatorNode<T> root, current ;
	
	public TreeLocator() {
		current = root = null;
	}
	
	//add get >> same as add,find in BST but some changes
	@Override
	public int add(T e, Location loc) {
		int toFind =0;
		//if the tree empty
		if(empty()) {
			current = root = new TreeLocatorNode<T>(e, loc) ;
			return toFind;
		}
		TreeLocatorNode<T> p = root;
		TreeLocatorNode<T> q = root;
		int x = loc.x;
		int y = loc.y;
		int ourPX ; 
		int ourPY ;
		// if we the location is already exist we will add the data
			while(p != null) {
				q=p;
				toFind++;
				ourPX = p.loc.x; 
				ourPY = p.loc.y;
				if((ourPX == x) && (ourPY == y)) {
					p.data.insert(e);
					return toFind;
					
				}
				else if((ourPX > x) && (ourPY >= y)) {
					p = p.child1;
				}
				else if((ourPX >= x) && (ourPY < y)) {
					p = p.child2;
				}
				else if((ourPX < x) && (ourPY <= y)) {
					p = p.child3;
				}
				else if((ourPX <= x) && (ourPY > y)) {
					p = p.child4;
				}
			}
			
			ourPX = q.loc.x; 
			ourPY = q.loc.y;
			//if the location dosen't exist we will add data and the new location
			TreeLocatorNode<T> tmp = new TreeLocatorNode<T>(e, loc) ;
			//the q will be parent and we will insert directly
			if((ourPX > x) && (ourPY >= y)) {
				q.child1 = tmp;
			}
			else if((ourPX >= x) && (ourPY < y)) {
				q.child2 = tmp;
			}
			else if((ourPX < x) && (ourPY <= y)) {
				q.child3 = tmp;	
			}
			else if((ourPX <= x) && (ourPY > y)) {
				q.child4 = tmp;
			}

		//return the number of comparison
		return toFind;
		
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		TreeLocatorNode<T> p = root ;
		int x = loc.x;
		int y = loc.y;
		int toFind =0;
		int ourPX ; 
		int ourPY ;
		//if there's no data
		if(empty())
			return new Pair<List<T>,Integer>(new LinkedList<T>(),toFind) ;
		//search inside the tree until we reach to the right location then return the list(data) and number of comparison
					while(p != null) {
						toFind++;
						ourPX = p.loc.x; 
						ourPY = p.loc.y;
						//bring the list data
						if((ourPX == x) && (ourPY == y)) {
							return new Pair<List<T>,Integer>(p.data,toFind) ; }
						else if((ourPX > x) && (ourPY >= y)) {
							p = p.child1;
						}
						else if((ourPX >= x) && (ourPY < y)) {
							p = p.child2;
						}
						else if((ourPX < x) && (ourPY <= y)) {
							p = p.child3;
						}
						else if((ourPX <= x) && (ourPY > y)) {
							p = p.child4;
						}
						
						
					}
				//not found
					
		return new Pair<List<T>,Integer>(new LinkedList<T>(),toFind) ;
	}

	
	
	//here we will remove the data from location just not the location!!! so we will find the location the delete the specific data،we can put it (empty) list or null 
	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		TreeLocatorNode<T> p = root ;
		int toFind =0;
		int x = loc.x;
		int y = loc.y;
		int ourPX ; 
		int ourPY ;
		if(empty())
			return new Pair<Boolean,Integer>(false,toFind) ;
					while(p != null) {
						toFind++;
						 ourPX = p.loc.x; 
						 ourPY = p.loc.y;
						//search until we reach in the right location then send the list and the element we want delete it to remove method
						if((ourPX == x) && (ourPY == y)) {
			    // this will go to (removeE) function and it will delete all all occurrences of e in the linked list and return true if it delete (found it) and if not false
			    //we will give it the linked list (p.data) and the data (e)
							boolean ifFou = removeElement(e, p.data) ;
							//if we found it the first pair will be true and that mean it's deleted and if not (ifFou) will be false
							if(ifFou)
							return new Pair<Boolean,Integer>(ifFou,toFind) ;
							return new Pair<Boolean,Integer>(ifFou,toFind) ;
							}
						//if the location dosen't match we will check the child
						else if((ourPX > x) && (ourPY >= y)) {
							p = p.child1;
						}
						else if((ourPX >= x) && (ourPY < y)) {
							p = p.child2;
						}
						else if((ourPX < x) && (ourPY <= y)) {
							p = p.child3;
						}
						else if((ourPX <= x) && (ourPY > y)) {
							p = p.child4;
						}
						
						
					}
				//if we don't found we will return false and the number of comparisons		
		return new Pair<Boolean,Integer>(false,toFind) ;
	}
	//same get all in BST >> we can do the same and we can put it in any order dosen't matter
	@Override
	public List<Pair<Location, List<T>>> getAll() {
		
		List<Pair<Location, List<T>>> entire = new LinkedList<Pair<Location,List<T>>>() ;
		//if it's empty
		if(empty()) {
			return entire; 
			}
		else {
			TreeLocatorNode<T> pnode = root;
			//we wall call all (inOrder) insert
			All(entire,pnode) ;
			return entire;
		}
	}
	//here we will have pair that contains list pair has (the location and his data), and the number of key comparisons made
	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		
		int x1= lowerLeft.x;
		int y1 = lowerLeft.y;
		int x2 = upperRight.x;
		int y2= upperRight.y;
		int toFind=0;
		Location upperLeft = new Location(x1,y2);
		Location lowerRight = new Location(x2,y1);
		Pair<List<Pair<Location, List<T>>>, Integer> allIn = new Pair<List<Pair<Location,List<T>>>,Integer>(new LinkedList<Pair<Location,List<T>>>(), toFind) ;
		//check null or not
		if(empty()) {
			return allIn; 
			}
		else {
			TreeLocatorNode<T> pnode = root;
			inRangeGET(x1,x2,y1,y2,pnode,allIn);
		}
		return allIn;
	}
	//for inRange
	private void inRangeGET(int INx1,int INx2,int INy1,int INy2,TreeLocatorNode<T> p, Pair<List<Pair<Location,List<T>>>,Integer> allIn) {
		
		//condition to stop
		if(p == null) {
			return;
		}

		else {
			//our position location
			int ourPX = p.loc.x; 
			int ourPY = p.loc.y;
			//just for me
			int ch1,ch2,ch3,ch4;
			ch1=ch2=ch3=ch4=0;
			Location upperLeft = new Location(INx1,INy2);
			Location lowerRight = new Location(INx2,INy1);
			//number of comparison ++ (toFind)
			
			//number of comparison ++ (toFind)
			allIn.second++;
			if((INx1<=ourPX)&&(INx2>=ourPX)&&(INy1<=ourPY)&&(INy2>=ourPY)) {
				//we will reach to first pair (list) and insert into it if it's satisfy the condition
				//every pair will have list of data and the location
				//list of lists
				allIn.first.insert(new Pair<Location,List<T>>(p.loc,p.data));
				//child 1
				inRangeGET(INx1, INx2, INy1, INy2, p.child1, allIn);
				//child 2
				inRangeGET(INx1, INx2, INy1, INy2, p.child2, allIn);
				//child 3
				inRangeGET(INx1, INx2, INy1, INy2, p.child3, allIn);
				//child 4
				inRangeGET(INx1, INx2, INy1, INy2, p.child4, allIn);
			}
			//the four corners
			////////////////////////////////////
			
			////////////////////////////////////////////////////////////////
			//if it's in first child only the rectangle
			else if((ourPX > INx2) && (ourPY >= INy2)) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child1, allIn);
				ch1++;
			}
			//if it's in second child only the rectangle
			else if((ourPX>= INx2) && (ourPY < INy1)) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child2, allIn);
				ch2++;
			}
			//corner
			
			//if it's in third child only the rectangle
			else if((ourPX < INx1) && (ourPY <= INy1)) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child3, allIn);
				ch3++;
			}
			//if it's in fourth child only the rectangle
			//y check
			else if((ourPX <= INx1) && (ourPY > INy2)) {
				inRangeGET(INx1, INx2, INy1,INy2, p.child4, allIn);
				ch4++;
			}
			//corner
			else if(ourPX == INx1 && ourPY == INy2) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child3, allIn);
				inRangeGET(INx1, INx2, INy1, INy2, p.child4, allIn);
				ch3++; ch4++;
			}
			//if it's in two child .. left
			//the rectangle is the left
			else if(ourPX>=INx2) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child1, allIn);
				inRangeGET(INx1, INx2, INy1, INy2, p.child2, allIn);
				ch1++;ch2++;
				if(ourPX == INx2)
					inRangeGET(INx1, INx2, INy1, INy2, p.child4, allIn);
				
			}
			//if it's in two child .. right
			//the rectangle is the right
			else if(ourPX<=INx1) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child3, allIn);
				inRangeGET(INx1, INx2, INy1, INy2, p.child4, allIn);
				ch3++; ch4++;
				
			}
			//the rectangle is down y
			else if(ourPY>=INy2) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child1, allIn);
				inRangeGET(INx1, INx2, INy1, INy2, p.child4, allIn);
				ch3++; ch4++;
			}
			//if it's in two child
			//the rectangle is up y
			else if(ourPY <=INy1) {
				
				
				inRangeGET(INx1, INx2, INy1, INy2, p.child2, allIn);
				inRangeGET(INx1, INx2, INy1, INy2, p.child3, allIn); 
				ch2++; ch3++;
				
			}
			//the four corners
			//corner
			else if(ourPX == INx1 && ourPY == INy1) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child2, allIn);
				inRangeGET(INx1, INx2, INy1, INy2, p.child3, allIn);
				ch2++; ch3++;
			}
			//corner
			else if(ourPX == INx2 && ourPY == INy2) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child1, allIn);
				inRangeGET(INx1, INx2, INy1, INy2, p.child4, allIn);
				ch1++; ch4++;
			}
	
			
			else if (ourPX == INx1) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child4, allIn) ;
			}
			else if(ourPX == INx2) {
				inRangeGET(INx1, INx2, INy1, INy2, p.child3, allIn); }		
		}
	
		
		
	}
	
	//just for check if the tree empty or not just
	private boolean empty() {
		return root == null;
	}
	
	//for remove
	private boolean removeElement(T e,List<T> listing) {
		boolean ifFou = false;
		//if the list is empty
		if(listing.empty()) 
			return ifFou;
			while(!listing.last()) {
				
				
				//if we found it we will delete it and put ifFou = true,, so it will return true when only found + it will delete all occurrences
				if(listing.retrieve().equals(e)) {
					listing.remove();
					ifFou = true;
					
				}
				// next until last
				else {
					listing.findNext();
				}	
			}
			
		
		//cause maybe the last = e
		if(listing.retrieve().equals(e)) {
			listing.remove();
			ifFou = true;
		}
		
		return ifFou;
	}
	//for get all
	private void All(List<Pair<Location,List<T>>> ent, TreeLocatorNode<T> roo) {
		if(roo == null) {
			return;
		}
		else {
			//insert father information
			ent.insert(new Pair<Location,List<T>>(roo.loc,roo.data));
			//then all the Child
			//child 1
			All(ent,roo.child1);
			//child 2
			All(ent,roo.child2);
			//child 3
			All(ent,roo.child3);
			//child 4
			All(ent,roo.child4);
			
			
		}
		
	}

}
