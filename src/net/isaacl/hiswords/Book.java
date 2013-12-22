package net.isaacl.hiswords;

public class Book {
	public String name;
	public int numChapts;
	public String synopsis;
	public Boolean isOldTestament;
	
	@Override
	public String toString() {
		return ("Book: " + this.name +
				", NumChapts: " + this.numChapts);
	}
}
