package physicssim;

public class StatManager {
	private int totalSolved, currentStreak, longestStreak, totalTried;
	
	public StatManager() {
		totalSolved = 0;
		currentStreak = 0;
		longestStreak = 0;
		totalTried = 0;
	}
	
	public StatManager(int cs, int ls, int ts, int tt) {
		currentStreak = cs;
		longestStreak = ls;
		totalSolved = ts;
		totalTried = tt;
	}

	public int getTotalSolved() {
		return totalSolved;
	}

	public void setTotalSolved(int totalSolved) {
		this.totalSolved = totalSolved;
	}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

	public int getLongestStreak() {
		return longestStreak;
	}

	public void setLongestStreak(int longestStreak) {
		this.longestStreak = longestStreak;
	}

	public int getTotalTried() {
		return totalTried;
	}

	public void setTotalTried(int totalTried) {
		this.totalTried = totalTried;
	}
	
	public void right() {
		setTotalSolved(getTotalSolved() + 1);
		setTotalTried(getTotalTried() + 1);
		setCurrentStreak(getCurrentStreak() + 1);
		
		if(getCurrentStreak() > getLongestStreak())
			setLongestStreak(getCurrentStreak());
	}
	
	public void wrong() {
		setTotalTried(getTotalTried() + 1);
		setCurrentStreak(0);
	}
	
	public String toString() {
		return String.format(
			"Current streak: %d\n"
			+ "Longest streak: %d\n"
			+ "Total solved: %d\n"
			+ "Total tried: %d\n"
			+ "Accuracy: %.2f%%",
			currentStreak, longestStreak, totalSolved,
			totalTried, (float) totalSolved / totalTried * 100
		);
	}
}
