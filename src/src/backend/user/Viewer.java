package backend.user;
import backend.content.Video;

public class Viewer {
	private String viewerEmail;
	private String viewerPassword;
	private boolean active;
	private Video[] watchHistory;
	private int watchCount;
	private String[] favoriteCategories;
	private int favoriteCount;
	
	
	
	
	public Viewer (String email, String password){
		this.setViewerEmail(email);
		this.setViewerPassword(password);
		this.active = true;
		this.watchHistory = new Video[10];
		this.watchCount = 0;
		this.favoriteCategories = new String[3];
		this.favoriteCount = 0;
	}
	public String getViewerEmail() {
		return viewerEmail;
	}
	
	private String getViewerPassword() {
		return viewerPassword;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	private Video[] getWatchHistory() {
		return watchHistory;
	}
	private void setWatchHistory(Video[] watchHistory) {
		this.watchHistory = watchHistory;
	}
	private int getWatchCount() {
		return watchCount;
	}
	private void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}
	public String[] getFavoriteCategories() {
		return favoriteCategories;
	}
	
	public void setFavoriteCategories(String[] favoriteCategories) {
	    if (favoriteCategories != null) {
	        this.favoriteCategories = favoriteCategories;
	        
	        int count = 0;
	        for (int i = 0; i < favoriteCategories.length; i++) {
	            if (favoriteCategories[i] != null) {
	                count++;
	            }
	        }
	        this.favoriteCount = count; 
	    }
	}
	
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	
	private void setViewerEmail(String email) {
		if(email !=null && email.matches ("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			this.viewerEmail=email;
		}
	}
	
	private void setViewerPassword(String password) {
		if(password != null && password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
			this.viewerPassword=password;
		}
	}
	
	public void watchContent(Video content) {
		if (this.watchCount < this.watchHistory.length && content != null) {
			
			this.watchHistory[watchCount]=content;
			this.watchCount ++ ; 
			System.out.println(this.viewerEmail + " watched " + content.getTitle());
		}
		
		
	}
	
	public boolean isValid() {
		if(this.viewerPassword != null && this.viewerEmail !=null)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public void printHistory() {
		System.out.println("Watch History:");
		for(int i=0; i<this.getWatchCount(); i++)
		{
			System.out.println("- " + this.watchHistory[i].getTitle());
		}
		System.out.print("\n");
	}
	
	public void printViewerDetails() {
		
		System.out.println("---------------------------------");
		
		System.out.println("Email: " + this.viewerEmail);
		
		String name = null;
		if(this.active==true)
		{
			name ="Active";
		}
		else if(this.active == false) {
			name="Deactivated";
		}
		System.out.println("Status: " + name);
		
		
		System.out.print("Favorite Categories:");
		for(int i=0; i<this.favoriteCategories.length; i++)
		{
			if(this.favoriteCategories[i] != null) 
			{
				System.out.print(" " + this.favoriteCategories[i].toUpperCase());
				if(i<this.favoriteCount-1) {
					System.out.print(",");
				}
				else if(i==this.favoriteCount-1)
				{
					System.out.println(" ");
				}
			}
			
		}
		this.printHistory();
	}
	
	
	
}
