
package  sample;
import java.io.*;
import javax.swing.JOptionPane;

public class VideoDownloader {

   VideoDownloader(){

        String url = JOptionPane.showInputDialog("Enter the video URL: ");
        downloadVideo(url);
    }

    public static void downloadVideo(String url) {
        try {
            ProcessBuilder pb = new ProcessBuilder("yt-dlp", url);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
            int status = p.waitFor();
            if (status == 0) {
                JOptionPane.showMessageDialog(null, "Download completed!");
            } else {
                JOptionPane.showMessageDialog(null, "Download failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
