package Ex4;


import java.util.Arrays;


public class BHeap {

	public final int MXSize = 21;
	
	public BNode[] trees = new BNode[MXSize];
	
    public BHeap(int data) {
		BNode temp = new BNode(data);
		trees[0] = temp;}
	
	public void join(BHeap h) {
		for (int i = 0; i< MXSize; i++) {
			if(h.trees[i]!=null){
				BNode temp = new BNode(h.trees[i]);
                 if(this.trees[i]==null){
                      this.trees[i]=temp;}
				else if(this.trees[i]!=null){
					this.trees[i]=union(this.trees[i],temp);}}}
		fixHeap();}
		
	private BNode union(BNode Node, BNode Node2) {
		if (Node.getData() < Node2.getData()) {
			Node.children[Node.getDegree()] = Node2;
			Node.degree++;
            return Node;}
		    else {
			BNode temp = new BNode(Node2);
			temp.children[temp.getDegree()] = Node;
			temp.degree++;
			return temp;}
	}

	private void fixHeap() {
		for(int i=MXSize-1;i>0;i--){
			if(this.trees[i-1]!=null && this.trees[i-1].getDegree()==i && this.trees[i]==null){
				this.trees[i]=this.trees[i-1];
				this.trees[i-1]=null;
			}else if(this.trees[i-1]!=null && this.trees[i-1].getDegree()==i && this.trees[i]!=null){
				this.trees[i]=union(this.trees[i],this.trees[i-1]);
				this.trees[i-1]=null;
				fixHeap();}}}
				
	public void printHeap(){
		for(int i=0;i<MXSize;i++){
			if(this.trees[i]!=null){
				System.out.print("rank "+i +": ");
				preOrder(this.trees[i]);
				System.out.println("");}}}
	
    public void preOrder(BNode a){
		System.out.print(a.getData()+" " );
		for(int i=MXSize-1;i>=0;i--){
			if(a.children[i]!=null)
				preOrder(a.children[i]);}}
	
    public int remove() {
			int min=Integer.MAX_VALUE;
			int index=-1;
			for(int i=0;i<MXSize;i++){
				if(this.trees[i]!=null && this.trees[i].getData()<min){
					min=this.trees[i].getData();
					index=i;
				}}
			if(index==-1){
				System.out.println("The Heap is empty");
				return min;}
			else if(index==0){
				this.trees[0]=null;}
			else{
				for(int i=0;i<index;i++){
					 if(this.trees[i]==null){
						this.trees[i]=this.trees[index].children[i];}
					else{
						this.trees[i]=union(this.trees[i],this.trees[index].children[i]);}}
				this.trees[index]=null;}
			fixHeap();
			return min;}

    ///Node inner class
   public class BNode {
		 int data;
		 int degree;
		BNode[] children;

		public BNode(int data) {
			this.data = data;
			this.degree = 0;
			children = new BNode[MXSize];
			for (int i = 0; i < MXSize; i++) {
				children[i] = null;
			}}

		/*public String toString(){
			String str="data:" +data+", degree:"+degree+", parent:("+parent+"), cildren:";
			
			for (int i = 0; i < children.length; i++) {
				if(children[i]!=null){
				str+=","+children[i].getData();}
			}
			return  str;//+ "children:"+Arrays.toString(children);
		}*/

		public BNode(BNode Node) {
			this.data = Node.data;
			this.degree = Node.degree;
			children = new BNode[MXSize];
			for (int i = 0; i < MXSize; i++) {
				this.children[i] = Node.children[i];}
		}
		
	    public BNode[] getChildren() {
			return children;
		}

		public void setChildren(BNode[] children) {
			this.children = children;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public int getDegree() {
			return degree;
		}

		public void setDegree(int degree) {
			this.degree = degree;
		}
}

}
