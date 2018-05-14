package jto.run;

import jto.ent.ArticleSaver;

public class DoBackup {
	
	private static final String TARGET_FOLDER = "/Users/john/Desktop/mandi_gray_nowtoronto";
	private static final String TARGET_FILE = "articles_";

	private static jto.ent.ArticleSaver saver ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DoBackup db = new DoBackup();
		db.tryWriteFile() ;
		//db.TestDate();
	}
	
	public void TestDate() {
		System.out.println(jto.util.DateUtil.generateDate());
	}
	
	public DoBackup() {
		saver =new ArticleSaver();
		saver.loadAllArticles();
		
	}
	
	private void tryWriteFile() {
		if(saver.articlesLoaded() && jto.out.WriterOfFiles .DoesFolderExist(TARGET_FOLDER)) {
			System.out.println("okay to write file");
			jto.out.WriterOfFiles.writeAFile(saver.makeSpreadSheet(), TARGET_FOLDER, TARGET_FILE+"_"+jto.util.DateUtil.generateDate()+".csv");
		}else {
			System.out.println("No Good");
		}
	}
}
