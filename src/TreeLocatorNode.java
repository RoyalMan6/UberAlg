class TreeLocatorNode<T> {
	public List<T> data;
	public Location loc ;
	public TreeLocatorNode<T> child1, child2,child3,child4;

	public TreeLocatorNode(T d,Location loc) {
		data = new LinkedList<T>() ;
		data.insert(d);
		this.loc = loc;
		child1=null;
		child2=null;
		child3=null;
		child4=null;
		
		
	}
}
