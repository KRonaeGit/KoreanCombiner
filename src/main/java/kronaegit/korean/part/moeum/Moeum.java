package kronaegit.korean.part.moeum;

import kronaegit.korean.combine.KoreanUnChar;
import kronaegit.korean.part.KoreanPart;
import kronaegit.korean.part.jaeum.ChoSeong;
import kronaegit.korean.part.jaeum.JongSeong;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

/**
 * 한글의 모음을 구현하는 클래스입니다.
 * <code>Moeum.ㅏ</code>, <code>Moeum.ㅑ</code> 와 같이 모음을 사용할 수 있습니다.
 */
public abstract class Moeum implements KoreanPart {
    public static final int COUNT = 21;

    public static final Moeum ㅏ = get(0);
    public static final Moeum ㅐ = get(1);
    public static final Moeum ㅑ = get(2);
    public static final Moeum ㅒ = get(3);
    public static final Moeum ㅓ = get(4);
    public static final Moeum ㅔ = get(5);
    public static final Moeum ㅕ = get(6);
    public static final Moeum ㅖ = get(7);
    public static final Moeum ㅗ = get(8);
    public static final Moeum ㅘ = get(9);
    public static final Moeum ㅙ = get(10);
    public static final Moeum ㅚ = get(11);
    public static final Moeum ㅛ = get(12);
    public static final Moeum ㅜ = get(13);
    public static final Moeum ㅝ = get(14);
    public static final Moeum ㅞ = get(15);
    public static final Moeum ㅟ = get(16);
    public static final Moeum ㅠ = get(17);
    public static final Moeum ㅡ = get(18);
    public static final Moeum ㅢ = get(19);
    public static final Moeum ㅣ = get(20);

    /**
     * @return 유니코드 코드포인트에 더해질 값입니다. 자세한 설명은 <code>KoreanPart.getAdding()</code> 을 참조하세요.
     */
    @Override
    public int getAdding() {
        return (JongSeong.COUNT+1) * getMoeumIndex();
    }

    /**
     * 자세한 정보는 <code>isDoubleY()</code> 메소드와 <code>isDoubleW()</code> 메소드의 설명을 참조하세요.
     * @return 이중모음 여부를 반환합니다.
     */
    @Override
    public boolean isDouble() {
        return isDoubleY() || isDoubleW();
    }

    /**
     * <code>isDoubleW()</code> 값과 동시에 true 일 수는 없지만 동시에 false 일 수는 있습니다.
     * 이 값이 true 라면 <code>isDouble()</code> 의 값은 무조건 true 입니다.
     * Y형 이중모음은 Y 발음을 하는 모음으로 ㅕ,ㅖ 같이 발음을 영어로 쓸 때 yeo, ye 처럼 첫 글자가 y 인 모음을 의미합니다.
     * @return Y형 이중모음 여부를 반환합니다.
     */
    public abstract boolean isDoubleY();

    /**
     * <code>isDoubleY()</code> 값과 동시에 true 일 수는 없지만 동시에 false 일 수는 있습니다.
     * 이 값이 true 라면 <code>isDouble()</code> 의 값은 무조건 true 입니다.
     * W형 이중모음은 W 발음을 하는 모음으로 ㅙ,ㅞ 와 같이 발음을 영어로 쓸 때 wae, we 처럼 첫 글자가 W 인 모음을 의미합니다.
     * @return W형 이중모음 여부를 반환합니다.
     */
    public abstract boolean isDoubleW();

    /**
     * @return 해당 모음의 index 번호를 반환합니다.
     */
    public abstract @Range(from=0,to=Moeum.COUNT-1) int getMoeumIndex();

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof ChoSeong that)) return false;
        return that.getAdding() == this.getAdding();
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getAdding());
    }

    /**
     * @return 글자로 변환된 모음을 반환합니다. (Ex. ㅑ,ㅐ,ㅗ,ㅜ,ㅗ,ㅘ)
     */
    @Override
    public String toString() {
        return KoreanUnChar.get(this).toString();
    }

    private static @NotNull Moeum custom(int index, boolean doubleY, boolean doubleW) {
        if(index < 0 || COUNT-1 < index)
            throw new IndexOutOfBoundsException();
        if(doubleY && doubleW)
            throw new IllegalArgumentException();

        return new Moeum() {
            @Override
            public int getMoeumIndex() {
                return index;
            }
            @Override
            public boolean isDoubleY() {
                return doubleY;
            }
            @Override
            public boolean isDoubleW() {
                return doubleW;
            }
        };
    }
    private static @NotNull Moeum single(int index) {
        return custom(index, false, false);
    }
    private static @NotNull Moeum doubleY(int index) {
        return custom(index, true, false);
    }
    private static @NotNull Moeum doubleW(int index) {
        return custom(index, false, true);
    }
    public static Moeum get(int index) {
        return switch (index) {
            case  0 -> single (index); // ㅏ
            case  1 -> single (index); // ㅐ
            case  2 -> doubleY(index); // ㅑ
            case  3 -> doubleY(index); // ㅒ
            case  4 -> single (index); // ㅓ
            case  5 -> single (index); // ㅔ
            case  6 -> doubleY(index); // ㅕ
            case  7 -> doubleY(index); // ㅖ
            case  8 -> single (index); // ㅗ
            case  9 -> doubleW(index); // ㅘ
            case 10 -> doubleW(index); // ㅙ
            case 11 -> single (index); // ㅚ
            case 12 -> doubleY(index); // ㅛ
            case 13 -> single (index); // ㅜ
            case 14 -> doubleW(index); // ㅝ
            case 15 -> doubleW(index); // ㅞ
            case 16 -> doubleW(index); // ㅟ
            case 17 -> doubleY(index); // ㅠ
            case 18 -> single (index); // ㅡ
            case 19 -> single (index); // ㅢ
            case 20 -> single (index); // ㅣ
            default -> throw new IndexOutOfBoundsException();
        };
    }
}
