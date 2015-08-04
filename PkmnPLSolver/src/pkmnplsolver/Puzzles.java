package pkmnplsolver;

public class Puzzles {
	
	public static GameState puzzleExample() {
		return new GameState(2, new String[]{
			"      ",
			"      ",
			"      ",
			"      ",
			"      ",
			"      ",
			"      ",
			"      ",
			"      ",
			" Z    ",
			" Z XY ",
			"ZXXYY "});
	}
	
	public static GameState class3Puzzle27() {
		return new GameState(4, new String[]{
			"      ",
			"      ",
			"      ",
			"      ",
			"      ",
			"  Y   ",
			"  G   ",
			"  CY  ",
			"  GR  ",
			"  GR  ",
			"  YC  ",
			"  CRG "});
	}
	
	public static GameState class3Puzzle29() {
		return new GameState(5, new String[]{
			"      ",
			"      ",
			"      ",
			"      ",
			"      ",
			"  R   ",
			"  C   ",
			"  RP  ",
			"  CR  ",
			"  RP  ",
			"  PR  ",
			"  CC  "});
	}
	
	public static GameState class3Puzzle30() {
		return new GameState(4, new String[]{
			"      ",
			"     B",
			"   Y C",
			"  YY C",
			"  YG P",
			"  GC C",
			"  PC R",
			"  RR P",
			"  PCCP",
			"  PCGC",
			"  GGYB",
			"  GGYB"});
	}
	
}
