package regexp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageChecker {
    private static final Pattern regexpForImgTag = Pattern.compile(
            "<img[^.]*src=\"./Новая фундаментальная физика \\(Статья А.Н. Ховалкина\\)_files/pic(.*).jpg\".*[^.]*>");
    // <img src="./Новая фундаментальная физика (Статья А.Н. Ховалкина)_files/pic_home.gif">
    private String fileText;

    public ImageChecker(String fileName) {
        InputStream fis = null;
        fis = this.getClass().getClassLoader().getResourceAsStream(fileName);
        readFile(fis);
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ImageChecker(InputStream fis) {
        readFile(fis);
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(InputStream fis) {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(fis, "Windows-1251"));
            StringBuilder s = new StringBuilder();
            br.lines().forEach(s1 -> s.append(s1).append("\n"));
            fileText = s.toString();
        } catch (UnsupportedEncodingException e) {
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
        ImageChecker imageChecker = new ImageChecker("Java.SE.03.Information handling_task_attachment.html");
        System.out.println(imageChecker.checkAscendingIndexesOfImages());
    }
}
