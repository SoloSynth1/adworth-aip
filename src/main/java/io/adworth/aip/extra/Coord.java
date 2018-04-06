package io.adworth.aip.extra;

public class Coord {
	private int x;
	private int y;

	public Coord(){
		
	}
	
	public Coord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int[] getCoord() {
		int array[] = {this.x, this.y};
		return array;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean setCoord(int[] xy){
		try{
			this.x = xy[0];
			this.y = xy[1];
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean setX(int x){
		try{
			this.x = x;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean setY(int y){
		try{
			this.y = y;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
