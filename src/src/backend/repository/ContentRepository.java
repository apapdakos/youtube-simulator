package backend.repository;
import backend.content.YouTubeContent;

public class ContentRepository {
	private final int MAX_CONTENT;
	public YouTubeContent[] contentList;
	private int contentCount;
	
	
	
	public ContentRepository(int maxNumOfContentEntries){
		this.MAX_CONTENT= maxNumOfContentEntries;
		this.contentList = new YouTubeContent[MAX_CONTENT];
		this.contentCount = 0;
	}
	public int getMAX_CONTENT() {
		return MAX_CONTENT;
	}

	private YouTubeContent[] getContentList() {
		return contentList;
	}
	private void setContentList(YouTubeContent[] contentList) {
		this.contentList = contentList;
	}
	public int getContentCount() {
		return contentCount;
	}
	private void setContentCount(int contentCount) {
		this.contentCount = contentCount;
	}
	
	public boolean addContent(YouTubeContent content) {
		if(this.contentCount<this.MAX_CONTENT && content!= null)
		{
			this.contentList[this.contentCount]=content;
			this.contentCount++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public YouTubeContent findContentById(int contentId) {
		for(int i=0; i<this.contentCount; i++)
		{
			if(this.contentList[i] != null && this.contentList[i].getContentId() == contentId)
			{
				return this.contentList[i];
				
			}			
		}
		return null;
	}
	
	public YouTubeContent findContentByTitle(String title) {
		for(int i=0; i<this.contentCount; i++)
		{
			if(this.contentList[i] != null && this.contentList[i].getTitle().equals(title)) {
				return this.contentList[i];
			}
		}
		return null;
	} 
	
	public YouTubeContent[] getAllContent() {
		YouTubeContent[] getAllCon=new YouTubeContent[this.contentCount];
		for(int i=0; i<this.contentCount; i++)
		{
			 getAllCon[i]=this.contentList[i];
		}
		return getAllCon;
	}
	
	
	
	
}
