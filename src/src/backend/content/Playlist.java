package backend.content;

public class Playlist extends YouTubeContent{
	private Video[] videos;
	private int videoCount;
	
	
	
	public Playlist(String title, String category, String channelName, int maxVideos) {
		super(title, category, channelName);
		this.videos = new Video[maxVideos];
		this.videoCount = 0;
	}
	
	public Playlist(String title, int maxVideos){ 
		this(title, "Unknown", "Unknown" ,maxVideos);
	}
	private Video[] getVideos() {
		return videos;
	}
	private void setVideos(Video[] videos) {
		this.videos = videos;
	}
	private int getVideoCount() {
		return videoCount;
	}
	private void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}
	
	public boolean addVideo(Video video) {
		if(this.videoCount < this.videos.length)
		{
			this.videos[this.videoCount]=video;
			this.videoCount++;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void removeVideo(String videoTitle) {
		if(this.videoCount>0)
		{
			for(int i=0; i<this.videoCount; i++)
			{
				if(this.videos[i] != null && this.videos[i].getTitle().equals(videoTitle))
				{
					this.videos[i]=this.videos[videoCount-1];
					this.videos[videoCount-1]=null;
					videoCount--;
					break;
				}
			}
		}
	}
	
	public void updateVideoTitle(int index, String newTitle) {
		if(index >= 0 && index < this.videoCount && newTitle != null)
		{
			this.videos[index].setTitle(newTitle);
		}
	}
	
	public String printDetails() {
		String playInfo= super.printDetails();
		playInfo = playInfo + "Videos: " ; 
		for(int i=0; i<this.videoCount; i++)
		{
			playInfo= playInfo + this.videos[i].getTitle() ;
			if(i<this.videoCount-1) {
				playInfo=playInfo + ", ";
			}
		}
		
		return playInfo;
	}
	
}
