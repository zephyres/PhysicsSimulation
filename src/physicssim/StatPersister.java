package physicssim;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class StatPersister {
	private File file;
	
	public StatPersister() {
		String filename = "stats.txt";
		String link = "/";
		String os = System.getProperty("os.name");
		
		if(os.equals("Mac OS X")) {
			link = System.getProperty("user.home") + "/Library/Application Support/PhysicsSim/";
			new File(link).mkdirs();
		}
		
		file = new File(link + filename);
	}
	
	public void save(StatManager sm) {
		try {
			PrintWriter p = new PrintWriter(file);
			p.print(String.format(
				"%d %d %d %d", 
				sm.getCurrentStreak(),
				sm.getLongestStreak(),
				sm.getTotalSolved(),
				sm.getTotalTried()
			));
			p.close();
		} catch(Exception e) {
			System.err.printf("%s\n", e);
		}
	}
	
	public StatManager load() {
		StatManager sm = new StatManager();
		
		try {
			Scanner s = new Scanner(file);
			int[] v = new int[4];
			
			for(int i = 0; s.hasNext(); i++)
				v[i] = Integer.parseInt(s.next());
			
			sm = new StatManager(v[0], v[1], v[2], v[3]);
			
		} catch(Exception e) {}
		
		return sm;
	}
}
