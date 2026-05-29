package backend.content;

public abstract class YouTubeContent {
	protected  int contentId;
	protected String title;
	protected String primaryCategory;
	protected String channelName;
	protected int likes;
	protected int dislikes;
	private String[] collaboratorNames;
	private int collaboratorCount;
	private static int idCounter = 1;
	private static final int MAX_COLLABORATORS=10;
	
	
	
	public YouTubeContent(String title, String primaryCategory, String channelName) {
		this.contentId = 0;
		this.title = title;
		this.primaryCategory = primaryCategory;
		this.channelName = channelName;
		this.likes = 0;
		this.dislikes = 0;
		this.collaboratorNames = new String[MAX_COLLABORATORS];
		this.collaboratorCount = 0;
		this.contentId = idCounter++;
	}
	
	public YouTubeContent(String title){
		this(title , "Unknown" , "Unknown");
	}
	public int getContentId() {
		return contentId;
	}
	private void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getTitle() {
		return title;
	}
	protected void setTitle(String title) {
		this.title = title;
	}
	public String getPrimaryCategory() {
		return primaryCategory;
	}
	private void setPrimaryCategory(String primaryCategory) {
		this.primaryCategory = primaryCategory;
	}
	private String getChannelName() {
		return channelName;
	}
	private void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public int getLikes() {
		return likes;
	}
	private void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	private void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	private String[] getCollaboratorNames() {
		return collaboratorNames;
	}
	private void setCollaboratorNames(String[] collaboratorNames) {
		this.collaboratorNames = collaboratorNames;
	}
	private int getCollaboratorCount() {
		return collaboratorCount;
	}
	private void setCollaboratorCount(int collaboratorCount) {
		this.collaboratorCount = collaboratorCount;
	}
	private int getMAX_COLLABORATORS() {
		return MAX_COLLABORATORS;
	}
	
	public void addLike() {
		this.likes++;
	}
	
	public void addDislike() {
		this.dislikes++;
	}
	
	public double getLikePercentage() {
		if(this.likes == 0 && this.dislikes ==0)
		{
			return 0.0;
		}
		else {
			return  (double) this.likes / (this.likes + this.dislikes)*100;
		}
	}
	
	
	public String getNonNullCollaborators() {
		String result=""; 
		for(int i=0; i<this.collaboratorCount; i++)
		{
			if(this.collaboratorNames[i] !=null)
			{
				result = result + this.collaboratorNames[i]  ;
				if(i<this.collaboratorCount-1) {
					result=result + ", " ;
				}
			}
			
	    }
		return result;
	}
	
	public void addCollaborator(String name) {
		if(this.collaboratorCount<MAX_COLLABORATORS && name != null)
		{
			this.collaboratorNames[this.collaboratorCount] = name;
			this.collaboratorCount++;
			
		}	
	}
	
	public void removeCollaborator(String name) {
		for(int i=0; i<collaboratorCount; i++)
		{
			if(this.collaboratorCount > 0 && this.collaboratorNames[i] != null && this.collaboratorNames[i].equals(name)) {
				this.collaboratorNames[i] = this.collaboratorNames[this.collaboratorCount - 1];
				this.collaboratorNames[this.collaboratorCount - 1] = null;
				this.collaboratorCount -- ;
				break ;
			}
		}
	}
	
	public String printDetails() { 
		String allInfo = "Content ID: " + this.contentId + "\n";
		allInfo= allInfo + "Title: " + this.title +"\n";
		allInfo= allInfo + "Primary Category: " + this.primaryCategory.toUpperCase() + "\n";
		allInfo= allInfo + "Channel: " + this.channelName + "\n";
		allInfo= allInfo + "Likes: " + this.likes + "\n";
		allInfo= allInfo + "Dislikes: " + this.dislikes + "\n";
		allInfo= allInfo + "Like %:" + this.getLikePercentage() + "\n";
		allInfo= allInfo + "Collaborators: " + this.getNonNullCollaborators() + "\n";
		
		return allInfo;
		
		
		
		}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		YouTubeContent.idCounter = idCounter;
	}
	}

	
		
	
	
