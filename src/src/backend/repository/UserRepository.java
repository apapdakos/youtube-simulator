package backend.repository;

import backend.user.Viewer;

public class UserRepository {
	private static int MAX_VIEWERS =100;
	private Viewer[] viewers;
	private int count;
	
	
	public UserRepository() {
		MAX_VIEWERS = 100;
		this.viewers = new Viewer[MAX_VIEWERS];
		this.count = 0;
	}
	private int getMAX_VIEWERS() {
		return MAX_VIEWERS;
	}
	
	public Viewer[] getViewers() {
		return viewers;
	}
	private void setViewers(Viewer[] viewers) {
		this.viewers = viewers;
	}
	private int getCount() {
		return count;
	}
	private void setCount(int count) {
		this.count = count;
	}
	
	public boolean addViewer(Viewer viewer) {
		if(viewer == null) {
			return false;
		}
		if(this.count< MAX_VIEWERS) {
			for(int i=0; i<this.count; i++)
			{
				if(this.viewers[i] != null && viewers[i].getViewerEmail().equals(viewer.getViewerEmail())) {
					return false;
				}
			}
		}
		if(this.count< MAX_VIEWERS) {
			this.viewers[this.count]=viewer;
			this.count++;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void deactivateViewer(Viewer viewer) {
			if(viewer != null)
			{
				viewer.setActive(false);
			}
	}
	
	public void activateViewer(Viewer viewer) {
		if(viewer != null)
		{
			viewer.setActive(true);
		}
	}
	
	public Viewer findViewerByEmail(String viewerEmail) {
		if(viewerEmail != null )
		{
			for(int i=0; i<this.count; i++)
			{
				if(this.viewers[i] != null && this.viewers[i].getViewerEmail().equals(viewerEmail))
				{
					return viewers[i];
				}
			}
		}
		return null;
	}
	
	
	
}
