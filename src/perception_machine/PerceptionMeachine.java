package perception_machine;

public class PerceptionMeachine {
	public static int[][] xy;
	public static int [] a;
	public static int b=0;
	public static float rate;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    
	}
	/**
	 * 
	 * @param max_steps max steps to iteration
	 * @param rate learning rate
	 * @param data train data
	 * @param labels labels of train data
	 * @param n number of train data
	 * @param m dimension of train data
	 */	 
	 
	public void train(int max_steps,float rate,int[][] data, int[] labels,int n,int m) {
		this.rate=rate;
		int [][] cov =new int[n][n];
		 for(int i=0;i<n;i++) {
			 for(int j=0;j<n;j++) {
				 xy[i][j]=data[i][j]*labels[i];
			 }
			 for (int j=i;j<n;j++) {
				 int sum=dot(data[i],data[j]);
				 cov[i][j]=sum;
				 cov[j][i]=sum;
			 }
		 }
		a=new int[n];
		boolean all_correct=false;
		while(max_steps>0 && !all_correct) {
			 all_correct=true;
			 for (int i=0;i<n;i++) {
				 int predict=predict(data[i]);
				 if(predict!=labels[i]) {
					 a[i]+=1;
					 b+=labels[i];
					 all_correct=false;
				 }
			 }
			 max_steps--;
		}
		
		
	}
	/**
	 * @param data  data to predict its label
	 * @return label of data
	 */
	
   public int predict(int[] data) {
	   int sum=0;
	   for(int i=0;i<data.length;i++) {
		   sum+=rate*(a[i]*dot(xy[i],data)+b);
	   }
	   return sum>=0 ?1:-1;
	   
   }
   /**
    * @param x
    * @param y
    * @return dot product of x and y
    */
   private int dot(int[] x,int[] y) {
//	   if(x.length!=y.length) 
	   int sum=0;
	   for(int i=0;i<x.length;i++) sum+=x[i]*y[i];
	   return sum;
   }
}
