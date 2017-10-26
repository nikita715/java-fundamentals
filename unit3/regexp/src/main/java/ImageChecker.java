import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageChecker {
    private static final Pattern regexpForImgTag = Pattern.compile(
            "<img[^.]*src=\"./Новая фундаментальная физика \\(Статья А.Н. Ховалкина\\)_files/pic(.*).jpg\".*[^.]*>");
    // <img src="./Новая фундаментальная физика (Статья А.Н. Ховалкина)_files/pic_home.gif">
    private String fileText;

    public ImageChecker(URL url) {
        readFile(url);
    }

    private void readFile(URL url) {
        BufferedReader br;
        try {
            URLConnection conn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "Windows-1251"));
            StringBuilder s = new StringBuilder();
            br.lines().forEach(s1 -> s.append(s1).append("\n"));
            fileText = s.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAscendingIndexesOfImages() {
        Matcher matcher = regexpForImgTag.matcher(fileText);
        ArrayList<Integer> imgIndexes = new ArrayList<>();
        while (matcher.find()) {
            imgIndexes.add(Integer.parseInt(matcher.group(1)));
        }
        for (int i = 1; i < imgIndexes.size() - 1; i++) {
            if (imgIndexes.get(i) < imgIndexes.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("https://drive.google.com/uc?authuser=0&id=0B29M3sfdctitNnNub1lkdTYxUXc&export=download");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ImageChecker imageChecker = new ImageChecker(url);
        System.out.println(imageChecker.checkAscendingIndexesOfImages());
    }
}
