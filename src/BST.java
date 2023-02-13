
public class BST<K extends Comparable<K>, T> implements Map<K, T> {

	private BSTNode<K, T> root, current;
	
	@Override
	public boolean empty() {
		
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		
		return current.data;
	}

	@Override
	public void update(T e) {
		
		current.data = e;

	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		
		BSTNode<K,T> p = root ;
		
		int toFind =0;
	/* every time we join the loop toFind increase number comparisons */
		if(empty())
			return new Pair<Boolean,Integer>(false,toFind) ;
		while(p != null) {
			toFind ++;
		if(key.compareTo(p.key)==0) {
			current = p;
			
			return new Pair<Boolean, Integer>(true,toFind) ;
		}
		else if(key.compareTo(p.key) < 0) {
			p = p.left ;
			
		}
		/* key bigger then we will go right */
		else {
			p = p.right;
			
		}
		}
		/* check number to find !!!!!!!!!!!!!!! */
		return new Pair<Boolean, Integer>(false,toFind) ;
	}
	
	

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		
		
		int toFind=0;
		/* if it's empty we will add as root */
		if(empty()) {
			current = root = new BSTNode<K,T>(key,data);
			return new Pair<Boolean,Integer>(true,toFind) ;
		}
		BSTNode<K,T> p = root ;
		BSTNode<K,T> q = root ;
		/* if the key is already there we will not add it! */
		while(p != null) {
			q=p ;
			toFind++;
			if(key.compareTo(p.key)==0) {
				return new Pair<Boolean, Integer>(false,toFind) ;
			}
			else if(key.compareTo(p.key) < 0) {
				p = p.left ;
				
			}
			/* key bigger then we will go right */
			else {
				p = p.right;
				
			}
		}
		/* okay we found it, q will be the parent after loop finish so we will add right or left, if it's > right and if it's < left */
		/* check to find here */
		if(key.compareTo(q.key) > 0) {
			q.right = new BSTNode<K,T>(key,data);
			current = q.right ;
			
		}
		else {
			q.left = new BSTNode<K,T>(key,data);
			current = q.left ;
		}
		
		return new Pair<Boolean,Integer>(true,toFind) ;
		
	}
	@Override
	public Pair<Boolean, Integer> remove(K key) {
		// Search for k
		K k1 = key;
		int toFind = 0;
		BSTNode<K,T> p = root;
		BSTNode<K,T> q = null; // Parent of p 
		// the goal here (if,else)to find and put the p in the element we want to delete and q to his father
		while (p != null) {
				toFind++;
		         if (k1.compareTo(p.key) < 0) {
		            q = p;
		            
		            p = p.left;
		         } else if (k1.compareTo(p.key) > 0) {
		        	 q = p;
		        	 
		        	 p = p.right;
		        	 
		         }
		         
		         else { // Found the key
		        	 // Check the three cases
		        	 //// this else if it's have 2 child
		        	 if ((p.left != null) && (p.right != null)) {
				
				// Case 3: two children
				// Search for the min in the right subtree the min value
				BSTNode<K,T> min = p.right;
				q = p;
				while (min.left != null) {
					q = min;
                  min = min.left;
               }
				p.key = min.key; p.data = min.data; k1 = min.key; p = min;
				
		        	 }
     
				if (p.left != null) { // One child
					p = p.left;
    
				} else { // One or (no) children
					p = p.right; }
					if (q == null) { // No parent for p, root must change
					root = p;
					} 
       
       //connect q with p .. depends on the position
					else {
						if (k1.compareTo(q.key) < 0) {
						q.left = p;
						
       
						} else {
						q.right = p; }
				}
					current = root;
					
					return new Pair<Boolean,Integer>(true,toFind);
         } 
		         }
		       	
		         return new Pair<Boolean,Integer>(false,toFind);
	
}
	@Override
	public List<K> getAll() {
		List<K> all = new LinkedList<K>() ;
		/* we will use another recursive method to do the job then return the list (IN ORDER) as question want */
		// root == null is not important
		BSTNode<K,T> p = root;
		if(empty())
			return all;
		getAllRec(p, all);
		return all;	
	}
	/* this method will Returns all keys of the map as a list sorted in increasing order */
	private void getAllRec(BSTNode<K,T> p, List<K> all) {
		//check maybe it's null
		if(p==null)
			return;
		getAllRec(p.left, all);
		all.insert(p.key);
		getAllRec(p.right, all);
		
		
	}
	
	
	
	

}
