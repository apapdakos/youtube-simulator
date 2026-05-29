package frontEnd;

import backend.content.Playlist;
import backend.content.Video;
import backend.content.YouTubeContent;
import backend.repository.ContentRepository;
import backend.repository.UserRepository;
import backend.service.YouTubeService;
import backend.user.Viewer;

/**
 * Main driver class for the project.
 * Demonstrates the use of object-oriented programming principles such as:
 * - Encapsulation (repositories and controlled access)
 * - Inheritance (Video and Playlist inherit from YTContent)
 * - Polymorphism (YTContent reference holding Video or Playlist objects)
 * - Abstraction (YouTubeService handles recommendations without exposing logic details)
 */
public class YouTubeDriver {

    public static void main(String[] args) {

        // Creating repositories and service class
        ContentRepository contentRepo = new ContentRepository(100);
        UserRepository viewerRepo = new UserRepository();
        YouTubeService youTubeService = new YouTubeService(contentRepo, viewerRepo);

        // Creating Video objects
        Video v1 = new Video("How I study for exams (realistically)", "Education", "NikosStudies", 780);
        Video v2 = new Video("24h in Athens on a budget", "Vlog", "CityWalks", 920);
        Video v3 = new Video("Java OOP in 30 minutes", "Education", "CodeQuick", 1800);
        Video v4 = new Video("Top 10 football skills", "Sports", "BallMaster", 640);
        Video v5 = new Video("PC build guide 2026", "Tech", "BuildLab", 1320);
        Video v6 = new Video("Lo-fi beats to study", "Music", "LoFiRoom", 3600);
        Video v7 = new Video("Street food in Patras", "Food", "StreetBites", 840);
        Video v8 = new Video("Speedrun: hardest level", "Gaming", "FastRun", 510);
        Video v9 = new Video("Interview: young founder", "Business", "StartUpTalks", 1500);
        Video v10 = new Video("Beginner yoga flow", "Fitness", "MoveDaily", 900);

        contentRepo.addContent(v1);
        contentRepo.addContent(v2);
        contentRepo.addContent(v3);
        contentRepo.addContent(v4);
        contentRepo.addContent(v5);
        contentRepo.addContent(v6);
        contentRepo.addContent(v7);
        contentRepo.addContent(v8);
        contentRepo.addContent(v9);
        contentRepo.addContent(v10);

        // Extra videos used inside playlists (playlists must store Video objects, not just titles)
        Video j1 = new Video("Classes & Objects", "Education", "CodeQuick", 600);
        Video j2 = new Video("Encapsulation", "Education", "CodeQuick", 720);
        Video j3 = new Video("Inheritance", "Education", "CodeQuick", 690);
        Video j4 = new Video("Polymorphism", "Education", "CodeQuick", 750);

        Video g1 = new Video("Warm-up basics", "Fitness", "MoveDaily", 480);
        Video g2 = new Video("Full body day 1", "Fitness", "MoveDaily", 1100);
        Video g3 = new Video("Upper body", "Fitness", "MoveDaily", 980);
        Video g4 = new Video("Lower body", "Fitness", "MoveDaily", 1020);
        Video g5 = new Video("Stretching", "Fitness", "MoveDaily", 540);

        Video e1 = new Video("Cuts & transitions", "Tech", "BuildLab", 650);
        Video e2 = new Video("Audio basics", "Tech", "BuildLab", 720);
        Video e3 = new Video("Export settings", "Tech", "BuildLab", 560);

        contentRepo.addContent(j1);
        contentRepo.addContent(j2);
        contentRepo.addContent(j3);
        contentRepo.addContent(j4);
        contentRepo.addContent(g1);
        contentRepo.addContent(g2);
        contentRepo.addContent(g3);
        contentRepo.addContent(g4);
        contentRepo.addContent(g5);
        contentRepo.addContent(e1);
        contentRepo.addContent(e2);
        contentRepo.addContent(e3);


        // Add sections (chapters) inside videos
        v3.addSection("Intro", "00:00");
        v3.addSection("Classes & Objects", "02:10");
        v3.addSection("Encapsulation", "10:05");
        v3.addSection("Inheritance", "16:40");
        v3.addSection("Polymorphism", "23:15");

        v5.addSection("Parts overview", "00:00");
        v5.addSection("Compatibility checks", "04:20");
        v5.addSection("Assembly tips", "12:30");
        v5.addSection("BIOS & setup", "21:10");

        // Show that sections exist (explicit output)
        System.out.println("\n---------------------------------");
        System.out.println("Video with sections (chapters):");
        System.out.println(v3.printDetails());
        System.out.println("\n---------------------------------");

        // Likes / Dislikes (YouTube-style feedback)
        v3.addLike();
        v3.addLike();
        v3.addLike();
        v3.addDislike();

        v6.addLike();
        v6.addLike();

        v8.addLike();
        v8.addLike();
        v8.addLike();

        // Retrieve and print likes/dislikes
        System.out.println("\n---------------------------------");
        Video storedVideo = (Video) contentRepo.findContentByTitle("Java OOP in 30 minutes");
        if (storedVideo != null) {
            System.out.println("\n Feedback for 'Java OOP in 30 minutes': ");
            System.out.println("+ Likes: " + storedVideo.getLikes());
            System.out.println("- Dislikes: " + storedVideo.getDislikes());
            System.out.println(" Like %: " + String.format("%.1f", storedVideo.getLikePercentage()));
        } else {
            System.out.println("[ERROR] Video not found in contentRepo.");
        }
        System.out.println("\n---------------------------------");

        
        // Creating Playlist objects (flat list of videos - no modules)
        Playlist p1 = new Playlist("Java OOP Crash Course", "Education", "CodeQuick", 10);
        p1.addVideo(j1);
        p1.addVideo(j2);
        p1.addVideo(j3);
        p1.addVideo(j4);

        Playlist p2 = new Playlist("Gym Starter Pack", "Fitness", "MoveDaily", 10);
        p2.addVideo(g1);
        p2.addVideo(g2);
        p2.addVideo(g3);
        p2.addVideo(g4);
        p2.addVideo(g5);

        Playlist p3 = new Playlist("Beginner editing", "Tech", "BuildLab", 6);
        p3.addVideo(e1);
        p3.addVideo(e2);
        p3.addVideo(e3);

        contentRepo.addContent(p1);
        contentRepo.addContent(p2);
        contentRepo.addContent(p3);

        // Modify playlist entries
        Playlist storedP2 = (Playlist) contentRepo.findContentByTitle("Gym Starter Pack");
        Playlist storedP1 = (Playlist) contentRepo.findContentByTitle("Java OOP Crash Course");

        if (storedP2 != null) {
            storedP2.removeVideo("Lower body");
        } else {
            System.out.println("[ERROR] 'Gym Starter Pack' not found in contentRepo.");
        }

        if (storedP1 != null) {
            storedP1.updateVideoTitle(1, "Encapsulation (Updated)");
        } else {
            System.out.println("[ERROR] 'Java OOP Crash Course' not found in contentRepo.");
        }

        // Add collaborators
        Video oop = (Video) contentRepo.findContentByTitle("Java OOP in 30 minutes");
        if (oop != null) {
            oop.addCollaborator("Guest: Maria");
            oop.addCollaborator("Guest: George");
        }

        Playlist javaCourse = (Playlist) contentRepo.findContentByTitle("Java OOP Crash Course");
        if (javaCourse != null) {
            javaCourse.addCollaborator("TA: Alex");
        }

        // Print all content polymorphically
        YouTubeContent[] allContent = contentRepo.getAllContent();
        System.out.println("\n All Content Details:");
        System.out.println("\n---------------------------------");
        for (YouTubeContent c : allContent) {
            if (c != null) {
                System.out.println("\n---------------------------------");
                System.out.println(c.printDetails());
            }
        }
        System.out.println("\n---------------------------------");

        // Viewers (no subscription plan)
        Viewer viewer1 = new Viewer("alice@example.com", "SecurePass1!");
        Viewer viewer2 = new Viewer("bob@example.com", "StrongPass2@");
        Viewer viewer3 = new Viewer("charlie@example.com", "UltraPass3$");

        // Watch history stores videos only
        viewer1.watchContent((Video)v3);
        viewer1.watchContent((Video)v1);

        viewer2.watchContent((Video)v8);
        viewer2.watchContent((Video)v4);

        viewer3.watchContent((Video)v6);
        viewer3.watchContent((Video)v7);

        viewerRepo.addViewer(viewer1);
        viewerRepo.addViewer(viewer2);
        viewerRepo.addViewer(viewer3);

        // Deactivate a viewer
        viewerRepo.deactivateViewer(viewerRepo.findViewerByEmail("bob@example.com"));
        
        // Favorite categories
        Viewer alice = viewerRepo.findViewerByEmail("alice@example.com");
        Viewer bob = viewerRepo.findViewerByEmail("bob@example.com");
        Viewer charlie = viewerRepo.findViewerByEmail("charlie@example.com");

        if (alice != null) alice.setFavoriteCategories(new String[]{"Education", "Tech"});
        if (bob != null) bob.setFavoriteCategories(new String[]{"Gaming", "Sports"});
        if (charlie != null) charlie.setFavoriteCategories(new String[]{"Music", "Food"});

        for (Viewer v : viewerRepo.getViewers()) {
            if (v != null) v.printViewerDetails();
        }
 


        // Recommendations (explicit output)
        System.out.println("\n---------------------------------");
        System.out.println("Recommendations for ACTIVE viewers:");

        YouTubeContent[] recsAlice = youTubeService.getRecommendedContentByFavoriteCategories(alice, 4);
        youTubeService.printRecommendations(recsAlice, alice);

        YouTubeContent[] recsCharlie = youTubeService.getRecommendedContentByFavoriteCategories(charlie, 4);
        youTubeService.printRecommendations(recsCharlie, charlie);

        // Demonstrate that deactivated viewers receive no recommendations, then re-activate
        System.out.println("\nRecommendations for DEACTIVATED viewer (should be empty):");
        YouTubeContent[] recsBobDeact = youTubeService.getRecommendedContentByFavoriteCategories(bob, 4);
        youTubeService.printRecommendations(recsBobDeact, bob);

        viewerRepo.activateViewer(bob);
        System.out.println("\nRecommendations after re-activation:");
        YouTubeContent[] recsBob = youTubeService.getRecommendedContentByFavoriteCategories(bob, 4);
        youTubeService.printRecommendations(recsBob, bob);
        System.out.println("\n---------------------------------");
    }
}
