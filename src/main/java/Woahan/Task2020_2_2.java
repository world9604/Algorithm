package Woahan;

import java.util.*;

public class Task2020_2_2 {
    public static void main(String[] args) {
        String input = "my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "game.txt 100b\n" +
                "mov!e.mkv 10000b";
        System.out.print(new Task2020_2_2().solution(input));
    }
    /*
    파일이름
    1.영 대/소문자
    2.특수문자(^&'@{}[],$=!-#()%.+~_)
    3.숫자(0~9)

    확장자
    1.영 소문자,
    2.숫자(0~9)

    파일 크기
    1. 양수 ~ 1,000,000b(백만 바이트)

    1. 띄어쓰기를 기준으로, 파일이름과 크기를 분리
    2. 파일 이름 중에서 끝에서 부터 탐색하여 (.(dot))을 찾아내고
    3. dot 뒤의 문자열을 파일 타입으로 정의
    4. 4가지 타입 중 하나로 분류
    5. 해당 라인의 용량을 저장
    6. 출력 포맷의 맞게 출력
    */
    public String solution(String S) {
        // 각 라인을 분리
        String[] rows = S.split("\n");

        for (String row : rows) {
            // 1. 띄어쓰기를 기준으로, 파일이름과 크기를 분리
            String fileFullName = row.split(" ")[0];
            String sizeStr = row.split(" ")[1];
            int size = Integer.parseInt(sizeStr.substring(0, sizeStr.length() - 1));

            // 2. 파일 이름 중에서 끝에서 부터 탐색하여 (.(dot))을 찾아내고
            // 3. dot 뒤의 문자열을 파일 타입으로 정의
            for (int i = fileFullName.length() - 1; i >= 0; i--) {
                if (fileFullName.charAt(i) == '.') {
                    String fileTypeStr = fileFullName.substring(i + 1);
                    // 4. 4가지 타입 중 하나로 분류
                    FileType fileType = FileType.convertStrToFileType(fileTypeStr);
                    FileTypeGroup fileTypeGroup = FileTypeGroup.findByFileType(fileType);
                    fileTypeGroup.addSize(size);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 5. 해당 라인의 용량을 저장
        for (FileTypeGroup value : FileTypeGroup.values()) {
            String type = value.title;
            int size = value.getTotal();
            // 6. 출력 포맷의 맞게 출력
            sb.append(type).append(" ").append(size).append("b");
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    enum FileTypeGroup {
        MUSIC("music", Arrays.asList(FileType.MP3, FileType.AAC, FileType.FLAC)),
        IMAGE("images", Arrays.asList(FileType.JPG, FileType.BMP, FileType.GIF)),
        MOVIE("movies", Arrays.asList(FileType.MP4, FileType.AVI, FileType.MKV)),
        TEXT("text", Arrays.asList(FileType.TXT)),
        OTHER("other", Arrays.asList(FileType.OTHER));

        String title;
        List<FileType> fileTypes;
        int total;

        FileTypeGroup(String title, List<FileType> fileTypes) {
            this.title = title;
            this.fileTypes = fileTypes;
        }

        void addSize(int size) {
            this.total += size;
        }

        int getTotal() {
            return total;
        }

        public static FileTypeGroup findByFileType(FileType fileType){
            return Arrays.stream(FileTypeGroup.values())
                    .filter(FileTypeGroup -> FileTypeGroup.hasFileType(fileType))
                    .findAny()
                    .orElse(OTHER);
        }

        public boolean hasFileType(FileType fileType) {
            return  fileTypes.stream()
                    .anyMatch(elem -> elem == fileType);
        }
    }

    enum FileType {
        MP3("mp3"), AAC("aac"), FLAC("flac"),
        JPG("jpg"), BMP("bmp"), GIF("gif"),
        MP4("mp4"), AVI("avi"), MKV("mkv"),
        TXT("txt"),
        OTHER("other");

        String title;

        FileType(String title) {
            this.title = title;
        }

        public static FileType convertStrToFileType(String title) {
            return  Arrays.stream(FileType.values())
                    .filter(elem -> elem.title.equals(title))
                    .findAny()
                    .orElse(OTHER);
        }
    }
}
