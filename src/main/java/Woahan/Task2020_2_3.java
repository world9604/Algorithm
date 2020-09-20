package Woahan;

import java.util.*;

public class Task2020_2_3 {
    public static void main(String[] args) {
        String input = "my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "mov!e.mkv 10000b";
        System.out.print(new Task2020_2_3().solution(input));
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
    Music music;
    Images image;
    Movies movie;
    Other other;

    public String solution(String S) {
        // 4. 4가지 타입 중 하나로 분류
        this.music = new Music();
        this.image = new Images();
        this.movie = new Movies();
        this.other = new Other();
        FileType[] fileList = {music, image, movie, other};

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
                    /*System.out.println(fileSizeList);
                    System.out.println(size);
                    System.out.println();*/
                    // 4. 4가지 타입 중 하나로 분류
                    insertFileTypeFactory(fileType, size);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 5. 해당 라인의 용량을 저장
        for (FileType file : fileList) {
            String type = file.getType();
            int size = file.getSize();
            // 6. 출력 포맷의 맞게 출력
            sb.append(type).append(" ").append(size).append("b");
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void insertFileTypeFactory(String fileType, int size) {
        switch (fileType) {
            //music
            case "mp3" :
            case "aac" :
            case "flac" : {
                this.music.addSize(size);
                return;
            }
            //image
            case "jpg" :
            case "bmp" :
            case "gif" : {
                this.image.addSize(size);
                return;
            }
            //movie
            case "mp4" :
            case "avi" :
            case "mkv" : {
                this.movie.addSize(size);
                return;
            }
            //other
            default : {
                this.other.addSize(size);
                return;
            }
        }
    }

    interface FileType {
        String getType();
        int getSize();
        void addSize(String type, int size);
    }

    class Music implements FileType {
        Set<String> extensions = new HashSet();
        List<Integer> sizes;

        public Music() {
            this.sizes = new ArrayList<>();
            this.extensions.addAll(Arrays.asList(new String[]{"mp3", "aac", "flac"}));
        }

        @Override
        public String getType() {
            return "music";
        }

        @Override
        public int getSize() {
            int sum = 0;
            for (Integer size : this.sizes) {
                sum += size;
            }
            return sum;
        }

        private boolean isRightType(String type) {
            return extensions.contains(type);
        }

        @Override
        public void addSize(String type, int size) {
            if (isRightType(type))
                this.sizes.add(size);
        }
    }

    class Images implements FileType {
        String[] extensions = {"jpg", "bmp", "gif"};
        List<Integer> sizes;

        public Images() {
            this.sizes = new ArrayList<>();
        }

        @Override
        public String getType() {
            return "images";
        }

        @Override
        public int getSize() {
            int sum = 0;
            for (Integer size : this.sizes) {
                sum += size;
            }
            return sum;
        }

        @Override
        public void addSize(int size) {
            this.sizes.add(size);
        }
    }

    class Movies implements FileType {
        String[] extensions = {"mkv", "mp4", "avi"};
        List<Integer> sizes;

        public Movies() {
            this.sizes = new ArrayList<>();
        }

        @Override
        public String getType() {
            return "movies";
        }

        @Override
        public int getSize() {
            int sum = 0;
            for (Integer size : this.sizes) {
                sum += size;
            }
            return sum;
        }

        @Override
        public void addSize(int size) {
            this.sizes.add(size);
        }
    }

    class Other implements FileType {
        List<Integer> sizes;

        public Other() {
            this.sizes = new ArrayList<>();
        }

        @Override
        public String getType() {
            return "other";
        }

        @Override
        public int getSize() {
            int sum = 0;
            for (Integer size : this.sizes) {
                sum += size;
            }
            return sum;
        }

        @Override
        public void addSize(int size) {
            this.sizes.add(size);
        }
    }
}
