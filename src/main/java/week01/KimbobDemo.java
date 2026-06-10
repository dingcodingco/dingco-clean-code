package week01;

// 04. 함수 - 1 : Extract Method 시연용 (김밥 주문하기). 스크린샷 시연용.
public class KimbobDemo {

    Plate orderKimbob(Cook cook, Ingredients ingredients) {
        if (!ingredients.isEmpty()) {
            var rice = ingredients.getRice();              // -> 재료 준비하기
            var sesame = ingredients.getSesame();          // -> 재료 준비하기
            cook.seasonToTaste(rice);                      // -> 재료 준비하기
            cook.putIn(rice, sesame);                      // -> 재료 준비하기
            cook.stirIn(rice);                             // -> 재료 준비하기

            var kimbob = makeKimbob(cook, ingredients);

            while (kimbob.isTooShort()) {                  // -> 플레이팅 준비하기
                cook.cut(kimbob);                          // -> 플레이팅 준비하기
            }
            return cook.putOnAPlate(kimbob);               // -> 플레이팅 준비하기
        } else {
            return null;
        }
    }

    private static Kimbob makeKimbob(Cook cook, Ingredients ingredients) {
        var kimbob = cook.makeBaseKimbob(ingredients.getKim());  // -> 김밥 만들기
        cook.grap(kimbob);                             // -> 김밥 만들기
        while (kimbob.isAlmostFullWithRice()) {        // -> 김밥 만들기
            cook.spreadTheRice(kimbob);                // -> 김밥 만들기
        }
        cook.addIngredient(kimbob, ingredients.getHam());       // -> 김밥 만들기
        cook.addIngredient(kimbob, ingredients.getCarrot());    // -> 김밥 만들기
        cook.addIngredient(kimbob, ingredients.getCucumber());  // -> 김밥 만들기
        cook.addIngredient(kimbob, ingredients.getSpinach());   // -> 김밥 만들기
        return kimbob;
    }

    // 시연용 stub 타입
    interface Plate {}
    interface Kimbob { boolean isAlmostFullWithRice(); boolean isTooShort(); }
    interface Cook {
        void seasonToTaste(Object rice); void putIn(Object a, Object b); void stirIn(Object rice);
        Kimbob makeBaseKimbob(Object kim); void grap(Kimbob k); void spreadTheRice(Kimbob k);
        void addIngredient(Kimbob k, Object i); void cut(Kimbob k); Plate putOnAPlate(Kimbob k);
    }
    interface Ingredients {
        boolean isEmpty(); Object getRice(); Object getSesame(); Object getKim();
        Object getHam(); Object getCarrot(); Object getCucumber(); Object getSpinach();
    }
}
