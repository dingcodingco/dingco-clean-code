package com.dingcolabs.cleancode.s07_08_refactoring;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 07~08. 리팩토링 - 만화방 예제의 최종 클린 버전.
 *
 * <p>파이썬 원본은 dict/list 로 데이터를 다뤘지만, Java 에서는 record 로 모델링한다.
 * "계산"과 "포맷팅"을 분리하는 리팩토링의 최종 결과물이다.
 * (계산: {@link #createOrderDataList}, 포맷팅: {@link #formatOutput})
 */
public class ComicRentalStore {

    // ---- 도메인 모델 (파이썬 dict -> record) ----
    public record Cartoon(String name, String genre) {}

    public record ConsumptionHistory(String cartoonId, int viewCount) {}

    public record OrderHistory(String customer, List<ConsumptionHistory> consumptionHistories) {}

    /** 계산된 출력용 데이터 (계산과 포맷팅 분리 결과) */
    public record RentalLine(String cartoonName, int amount, int viewCount) {}

    public record OrderData(String customer, int totalAmount, int point, List<RentalLine> lines) {}

    private final Map<String, Cartoon> cartoonInfo;

    public ComicRentalStore(Map<String, Cartoon> cartoonInfo) {
        this.cartoonInfo = cartoonInfo;
    }

    /** 최종 진입점: 계산 결과 데이터를 만든 뒤 출력 문자열로 포맷팅한다. */
    public String getResult(List<OrderHistory> orderHistories) {
        return formatOutput(createOrderDataList(orderHistories));
    }

    // ---- 계산 단계 ----
    private List<OrderData> createOrderDataList(List<OrderHistory> orderHistories) {
        List<OrderData> orderDataList = new ArrayList<>();
        for (OrderHistory orderHistory : orderHistories) {
            List<RentalLine> lines = new ArrayList<>();
            for (ConsumptionHistory history : orderHistory.consumptionHistories()) {
                Cartoon cartoon = getCartoon(history);
                lines.add(new RentalLine(cartoon.name(), calculateCostOfComic(cartoon, history), history.viewCount()));
            }
            orderDataList.add(new OrderData(
                    orderHistory.customer(),
                    calculateTotalAmount(orderHistory),
                    calculatePoint(orderHistory),
                    lines));
        }
        return orderDataList;
    }

    private Cartoon getCartoon(ConsumptionHistory history) {
        return cartoonInfo.get(history.cartoonId());
    }

    private int calculateCostOfComic(Cartoon cartoon, ConsumptionHistory history) {
        int viewCount = history.viewCount();
        int amount = 0;
        switch (cartoon.genre()) {
            case "판타지" -> amount += 1000 * (viewCount - 30);
            case "코믹" -> {
                amount = 30000;
                if (viewCount > 20) {
                    amount += 10000 + 500 * (viewCount - 20);
                }
            }
            default -> amount = 4000 * viewCount;
        }
        return amount;
    }

    private int calculatePoint(OrderHistory orderHistory) {
        int point = 0;
        for (ConsumptionHistory history : orderHistory.consumptionHistories()) {
            point += pointOf(history);
        }
        return point;
    }

    private int pointOf(ConsumptionHistory history) {
        int point = Math.max(history.viewCount() - 30, 0);
        if (getCartoon(history).genre().equals("소년만화")) {
            point += history.viewCount() / 5; // math.floor(view/5)
        }
        return point;
    }

    private int calculateTotalAmount(OrderHistory orderHistory) {
        int totalAmount = 0;
        for (ConsumptionHistory history : orderHistory.consumptionHistories()) {
            totalAmount += calculateCostOfComic(getCartoon(history), history);
        }
        return totalAmount;
    }

    // ---- 포맷팅 단계 ----
    private String formatOutput(List<OrderData> orderDataList) {
        StringBuilder result = new StringBuilder();
        for (OrderData orderData : orderDataList) {
            result.append(orderData.customer()).append(" 주문 내역\n");
            for (RentalLine line : orderData.lines()) {
                result.append(line.cartoonName()).append(" : ")
                        .append(line.amount()).append("원 ")
                        .append(line.viewCount()).append(" 권 대여\n");
            }
            result.append("총액 ").append(orderData.totalAmount()).append("원 ");
            result.append("적립 포인트 ").append(orderData.point()).append("점\n \n");
        }
        return result.toString();
    }

    // ---- 샘플 입력 (파이썬 input_cartoon_info / input_order_histories) ----
    public static Map<String, Cartoon> sampleCartoonInfo() {
        Map<String, Cartoon> info = new LinkedHashMap<>();
        info.put("진격의거인", new Cartoon("진격의 거인", "판타지"));
        info.put("원피스", new Cartoon("원피스", "소년만화"));
        info.put("짱구", new Cartoon("짱구는 못말려", "코믹"));
        info.put("괴짜가족", new Cartoon("괴짜 가족", "코믹"));
        return info;
    }

    public static List<OrderHistory> sampleOrderHistories() {
        return List.of(
                new OrderHistory("의정부고", List.of(
                        new ConsumptionHistory("진격의거인", 55),
                        new ConsumptionHistory("원피스", 40),
                        new ConsumptionHistory("짱구", 20),
                        new ConsumptionHistory("괴짜가족", 15))),
                new OrderHistory("의여고", List.of(
                        new ConsumptionHistory("진격의거인", 20),
                        new ConsumptionHistory("원피스", 10),
                        new ConsumptionHistory("짱구", 50),
                        new ConsumptionHistory("괴짜가족", 60))));
    }

    public static void main(String[] args) {
        ComicRentalStore store = new ComicRentalStore(sampleCartoonInfo());
        System.out.print(store.getResult(sampleOrderHistories()));
    }
}
