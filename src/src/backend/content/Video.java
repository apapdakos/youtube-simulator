package backend.content;


import backend.user.Viewer;

public class Video extends YouTubeContent {
	private int durationSeconds;
	private String[][] sections;
	private int sectionCount;
	private static final int MAX_SECTIONS=15;
	private Viewer[] viewersWatched;
	private int viewerCount;
	private static final int MAX_VIEWERS_PER_VIDEO=50;
	
	 
	
	public Video(String title, String category, String channelName, int durationSeconds){
		super(title, category, channelName);
		this.durationSeconds = durationSeconds;
		this.sections = new String[MAX_SECTIONS][2];
		this.sectionCount = 0;
		this.viewersWatched = new Viewer[MAX_VIEWERS_PER_VIDEO];
		this.viewerCount = 0;
	}
	
	public Video(String title, int durationSeconds){
		this(title , "Unknown", "Unknown" ,durationSeconds);
	}
	private int getDurationSeconds() {
		return durationSeconds;
	}
	private void setDurationSeconds(int durationSeconds) {
		this.durationSeconds = durationSeconds;
	}
	private String[][] getSections() {
		return sections;
	}
	private void setSections(String[][] sections) {
		this.sections = sections;
	}
	private int getSectionCount() {
		return sectionCount;
	}
	private void setSectionCount(int sectionCount) {
		this.sectionCount = sectionCount;
	}
	private int getMAX_SECTIONS() {
		return MAX_SECTIONS;
	}
	
	private Viewer[] getViewersWatched() {
		return viewersWatched;
	}
	private void setViewersWatched(Viewer[] viewersWatched) {
		this.viewersWatched = viewersWatched;
	}
	private int getViewerCount() {
		return viewerCount;
	}
	private void setViewerCount(int viewerCount) {
		this.viewerCount = viewerCount;
	}
	private int getMAX_VIEWERS_PER_VIDEO() {
		return MAX_VIEWERS_PER_VIDEO;
	}
	
	public void addSection(String sectionTitle, String timestamp) {
			if(this.sectionCount< MAX_SECTIONS) {
				this.sections[this.sectionCount][0]= sectionTitle;
				this.sections[this.sectionCount][1]=timestamp;
				this.sectionCount++ ;
			}
		
	}
	
	public void addSection(String sectionTitle) {
		this.addSection(sectionTitle , "Unknown");
	}
	
	public void removeSectionByTitle(String sectionTitle) {
		for(int i=0; i<this.sectionCount; i++) {
			if(this.sections[i][0] != null && this.sections[i][0].equals(sectionTitle)) {
				this.sections[i][0]=this.sections[this.sectionCount-1][0];
				this.sections[i][1]=this.sections[this.sectionCount - 1][1];
				this.sections[this.sectionCount-1][0]=null;
				this.sections[this.sectionCount-1][1]=null;
				this.sectionCount--;
				break;
			}
		}
	}
	
	public void updateSectionTitle(int index, String newTitle) {
		if (index >= 0 && index < this.sectionCount && newTitle != null) {
			this.sections[index][0]=newTitle;
		}
	}
	
	public boolean addViewer(Viewer viewer) 
	{
		for(int i=0; i<this.viewerCount; i++)
		{
			if(this.viewersWatched[i] == viewer)
			{
			return false;
			}
		}
		
		if(this.viewerCount >= MAX_VIEWERS_PER_VIDEO) {
			return false;
		}
		
		this.viewersWatched[this.viewerCount] = viewer;
		this.viewerCount++ ;
		return true;
	}

	public String printDetails() {
		String videoInfo = super.printDetails();
		videoInfo=videoInfo + "Duration: " + this.durationSeconds + " seconds\n";
		
		if(this.sectionCount==0)
		{
			videoInfo=videoInfo + "Sections (0): No sections added yet.\n" ; 
		}
		else if(this.sectionCount> 0) {
			videoInfo=videoInfo + "Sections (" + this.sectionCount + "):\n" ;
			for(int i=0; i<this.sectionCount; i++)
			{
				videoInfo=videoInfo + " - " + "[" + this.sections[i][1] + "] " + this.sections[i][0] + "\n"; ; 
			}
			
			
		}
		videoInfo=videoInfo + "Viewers watched: " + this.viewerCount;
	
		return videoInfo;
	}
}
