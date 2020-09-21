package Woahan;

import java.util.ArrayList;
import java.util.List;

public class Task2020_2 {
    public static void main(String[] args) {
        String input = "my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "game.txt 100b\n" +
                "mov!e.mkv 10000b";
        System.out.print(new Task2020_2().solution(input));
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
    FileSizeGroup fileSizeGroup;

    public String solution(String S) {
        // 4. 4가지 타입 중 하나로 분류
        this.fileSizeGroup = new FileSizeGroup();

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
                    String fileType = fileFullName.substring(i + 1);
                    // 4. 4가지 타입 중 하나로 분류
                    insertFileSizeToFileGroup(fileType, size);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 5. 해당 라인의 용량을 저장
        for (FileType value : FileType.values()) {
            String type = value.name;
            int size = fileSizeGroup.getSumSize(value);
            // 6. 출력 포맷의 맞게 출력
            sb.append(type).append(" ").append(size).append("b");
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void insertFileSizeToFileGroup(String fileType, int size) {
        switch (fileType) {
            //music
            case "mp3" :
            case "aac" :
            case "flac" : {
                this.fileSizeGroup.musicSizes.add(size);
                return;
            }
            //image
            case "jpg" :
            case "bmp" :
            case "gif" : {
                this.fileSizeGroup.imageSizes.add(size);
                return;
            }
            //movie
            case "mp4" :
            case "avi" :
            case "mkv" : {
                this.fileSizeGroup.movieSizes.add(size);
                return;
            }
            //movie
            case "txt" : {
                this.fileSizeGroup.textSizes.add(size);
                return;
            }
            //other
            default : {
                this.fileSizeGroup.otherSizes.add(size);
                return;
            }
        }
    }

    class FileSizeGroup {
        List<Integer> musicSizes;
        List<Integer> imageSizes;
        List<Integer> movieSizes;
        List<Integer> textSizes;
        List<Integer> otherSizes;

        public FileSizeGroup() {
            this.musicSizes = new ArrayList<>();
            this.imageSizes = new ArrayList<>();
            this.movieSizes = new ArrayList<>();
            this.textSizes = new ArrayList<>();
            this.otherSizes = new ArrayList<>();
        }

        int getSumSize(FileType fileType){
            int sum = 0;

            if (fileType == FileType.MUSIC)
                sum = getSumFrom(musicSizes);

            if (fileType == FileType.IMAGE)
                sum = getSumFrom(imageSizes);

            if (fileType == FileType.MOVIE)
                sum = getSumFrom(imageSizes);

            if (fileType == FileType.TEXT)
                sum = getSumFrom(textSizes);

            if (fileType == FileType.OTHER)
                sum = getSumFrom(otherSizes);

            return sum;
        }

        private int getSumFrom(List<Integer> list) {
            int sum = 0;
            for (Integer val : list) {
                sum += val;
            }
            return sum;
        }
    }

    enum FileType {
        MUSIC("music"), IMAGE("images"),
        MOVIE("movies"), TEXT("text"),
        OTHER("other");

        String name;

        FileType(String name) {
            this.name = name;
        }
    }
}
