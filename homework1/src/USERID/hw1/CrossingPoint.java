package USERID.hw1;

public class CrossingPoint {
	/**
	 * locates the crossing point of an ar 
	 * @param ar the array whose crossing point to find
	 */
	public void locate(boolean ar[][]){
		int trueCol = -1;
		int trueRow = -1;
		int lookUps = 0;
		for(int i = 0; i < ar[0].length; i++){
			if(trueRow!=-1 && trueCol != -1){
				break;
			}
			for(int j = 0; j < ar.length; j++){
				if(ar[j][i]==true){
					//TT
					//T
					if(isInArray(ar,j+1,i) && ar[j+1][i]==true && 
							isInArray(ar,j,i+1) && ar[j][i+1]==true){
						lookUps+=2;
						trueRow = j;
						trueCol = i;
						break;
					}
					//T
					//TT
					else if(isInArray(ar,j-1,i) && ar[j-1][i]==true && 
							isInArray(ar,j,i+1) && ar[j][i+1]==true){
						lookUps+=2;
						trueRow = j;
						trueCol = i;
						break;
					}
					//TT
					// T
					else if(isInArray(ar,j,i-1) && ar[j][i-1]==true && 
							isInArray(ar,j+1,i) && ar[j+1][i]==true){
						lookUps +=2;
						trueRow = j;
						trueCol = i;
						break;
					}
					// T
					//TT
					else if(isInArray(ar,j-1,i) && ar[j-1][i]==true && 
							isInArray(ar,j,i-1) && ar[j][i-1]==true){
						lookUps += 2;
						trueRow = j;
						trueCol = i;
						break;
					}
				}
				else{
					lookUps+=1;
				}
			}
		}
		if(trueCol == -1 || trueRow == -1){
			return;
		}
		//if make it here, there is a crossing point at (trueRow,trueCol)
		System.out.println("Crossing Point: (" + trueRow + "," + trueCol + ")");
		System.out.println("Required " + lookUps + " array lookups to find");
	}
	
	//helper function 
	public boolean isInArray(boolean ar[][], int row, int col){
		if (row >= ar.length || row < 0) return false;
		if (col >= ar[0].length || col < 0) return false;
		return true;
	}
}
