package jto.run;

import jto.ent.ArticleSaver;

public class TestURLStringTrimmer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		TestURLStringTrimmer t = new TestURLStringTrimmer();
		t.testURL("http://nationalpost.com/opinion/christie-blatchford-man-accused-of-mandi-gray-sex-assault-finds-hes-guilty-until-proven-innocent");
		t.testURL("https://www.vice.com/en_ca/article/j5ab5b/why-mandi-gray-documented-her-sexual-assault-case/");
	}
	
	
	
	
	public void testURL(String s) {
		System.out.println("before trimming "+s);
		System.out.println("after trimming "+ArticleSaver.removeTrailingSlash(s));
	}
}
