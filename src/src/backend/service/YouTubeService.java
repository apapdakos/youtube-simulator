package backend.service;
import backend.repository.UserRepository;
import backend.user.Viewer;
import backend.content.YouTubeContent;
import backend.repository.ContentRepository;

public class YouTubeService {
	private ContentRepository contentRepo;
	private UserRepository userRepo;
	
	
	public YouTubeService(ContentRepository contentRepo, UserRepository userRepo) {
		this.contentRepo = contentRepo;
		this.userRepo = userRepo;
	}
	private ContentRepository getContentRepo() {
		return contentRepo;
	}
	private void setContentRepo(ContentRepository contentRepo) {
		this.contentRepo = contentRepo;
	}
	private UserRepository getUserRepo() {
		return userRepo;
	}
	private void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public YouTubeContent[] getRecommendedContentByFavoriteCategories(Viewer viewer, int maxResults) {
			if(viewer == null ){
				return new YouTubeContent[0];
			}
			if(viewer.isActive()) {
				int recommendationCount = 0;
				YouTubeContent[] tempRecoms = new YouTubeContent[maxResults];
				YouTubeContent[] allVideos = this.contentRepo.getAllContent();

				
				for(int i=0; i<viewer.getFavoriteCount(); i++)
				{
					
					String currentFavCategory = viewer.getFavoriteCategories()[i];
					
					for(int j= allVideos.length - 1; j>=0; j--) {
						if(recommendationCount<maxResults && this.contentRepo.contentList[j] != null && allVideos[j].getPrimaryCategory().equals(currentFavCategory)) {
							tempRecoms[recommendationCount] = this.contentRepo.contentList[j];
							recommendationCount++;
							break; 
						}
					}
				}
				YouTubeContent[] finalRecommendations = new YouTubeContent[recommendationCount];
				for (int i = 0; i < recommendationCount; i++) {
					finalRecommendations[i] = tempRecoms[i];
				}

				return finalRecommendations;
				
			}
			else 
			{
				return new YouTubeContent[0];
			}
	}
			
	


	public void printRecommendations(YouTubeContent[] content, Viewer viewer) {
		if(viewer==null || content== null) {
			return;
		}
		System.out.println("Recommended content for Viewer " + viewer.getViewerEmail());
		
		for(int i=0; i<content.length; i++)
		{
			if(content[i] != null)
			{
				System.out.println("- " + content[i].getTitle() + " (+ " + content[i].getLikes() + ", - " + content[i].getDislikes() + ")");
			}
		}
		
	}
}

