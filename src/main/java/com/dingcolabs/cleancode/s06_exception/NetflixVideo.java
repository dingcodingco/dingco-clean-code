package com.dingcolabs.cleancode.s06_exception;

/**
 * 06. 예외 / 21) 예외를 일부러 발생시키기 - 넷플릭스 비디오 예제의 최종 버전.
 *
 * <p>"사용권 없음 / 동시 시청 4개 초과" 같은 예외 상황을 각각 별도의 커스텀 예외로 던지고,
 * 호출부에서는 예외 종류에 따라 분기한다. (파이썬: class MustHaveLicenseError(Exception))
 */
public class NetflixVideo {

    // ---- 커스텀 예외 (파이썬: Exception 상속) ----
    public static class MustHaveLicenseException extends RuntimeException {
        public MustHaveLicenseException(String message) {
            super(message);
        }
    }

    public static class CurrentViewCountOverFourException extends RuntimeException {
        public CurrentViewCountOverFourException(String message) {
            super(message);
        }
    }

    // ---- 도메인 모델 (파이썬 객체를 대신하는 최소 stub) ----
    public record License(int currentViewCount) {}

    public record User(boolean licensed, License license) {
        public boolean hasLicensed() {
            return licensed;
        }
    }

    public record Video(String title) {}

    /** 호출부: 예외 종류에 따라 이동할 페이지를 정한다. */
    public String displayVideo(Video video, User user) {
        try {
            Video found = getVideo(video, user);
            return "재생: " + found.title();
        } catch (MustHaveLicenseException e) {
            return "사용권 구매 페이지로 이동";
        } catch (CurrentViewCountOverFourException e) {
            return "메인 페이지로 이동";
        }
    }

    public Video getVideo(Video video, User user) {
        if (!user.hasLicensed()) {
            throw new MustHaveLicenseException("사용권이 있어야만 볼 수 있습니다");
        } else if (user.license().currentViewCount() >= 4) {
            throw new CurrentViewCountOverFourException("현재 볼 수 없습니다");
        }
        return getVideoContents(video);
    }

    private Video getVideoContents(Video video) {
        return video;
    }

    public static void main(String[] args) {
        NetflixVideo netflix = new NetflixVideo();
        Video video = new Video("기묘한 이야기");
        System.out.println(netflix.displayVideo(video, new User(false, new License(0))));  // 사용권 구매 페이지로 이동
        System.out.println(netflix.displayVideo(video, new User(true, new License(4))));   // 메인 페이지로 이동
        System.out.println(netflix.displayVideo(video, new User(true, new License(1))));   // 재생: 기묘한 이야기
    }
}
