package kronaegit.korean.part.jaeum;

import kronaegit.korean.combine.KoreanUnChar;
import kronaegit.korean.part.moeum.Moeum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Objects;

public abstract class JongSeong implements Jaeum {
    public static final int COUNT = 27;
    public static final JongSeong ㄱ = get( 0);
    public static final JongSeong ㄲ = get( 1);
    public static final JongSeong ㄳ = get( 2);
    public static final JongSeong ㄴ = get( 3);
    public static final JongSeong ㄵ = get( 4);
    public static final JongSeong ㄶ = get( 5);
    public static final JongSeong ㄷ = get( 6);
    public static final JongSeong ㄹ = get( 7);
    public static final JongSeong ㄺ = get( 8);
    public static final JongSeong ㄻ = get( 9);
    public static final JongSeong ㄼ = get(10);
    public static final JongSeong ㄽ = get(11);
    public static final JongSeong ㄾ = get(12);
    public static final JongSeong ㄿ = get(13);
    public static final JongSeong ㅀ = get(14);
    public static final JongSeong ㅁ = get(15);
    public static final JongSeong ㅂ = get(16);
    public static final JongSeong ㅄ = get(17);
    public static final JongSeong ㅅ = get(18);
    public static final JongSeong ㅆ = get(19);
    public static final JongSeong ㅇ = get(20);
    public static final JongSeong ㅈ = get(21);
    public static final JongSeong ㅊ = get(22);
    public static final JongSeong ㅋ = get(23);
    public static final JongSeong ㅌ = get(24);
    public static final JongSeong ㅍ = get(25);
    public static final JongSeong ㅎ = get(26);

    @Override
    public int getAdding() {
        return getJongSeongIndex() + 1;
    }
    @Override
    public abstract boolean isDouble();
    public abstract boolean isDoubleBadchim();
    @Override
    public @Nullable ChoSeong asChoSeong() {
        int index = getChoSeongIndex();
        return index == -1 ? null : ChoSeong.get(index);
    }
    @Override
    public JongSeong asJongSeong() {
        return this;
    }

    @Override
    public abstract @Range(from=-1,to=ChoSeong.COUNT-1) int getChoSeongIndex();
    @Override
    public abstract @Range(from=0,to=JongSeong.COUNT-1) int getJongSeongIndex();
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
    @Override
    public String toString() {
        return KoreanUnChar.get(this).toString();
    }
    private static @NotNull JongSeong custom(int choSeongIndex, int jongSeongIndex, boolean isDouble, boolean isDoubleBadChim) {
        if(choSeongIndex < -1 || ChoSeong.COUNT-1 < choSeongIndex)
            throw new IndexOutOfBoundsException();
        if(jongSeongIndex < 0 || JongSeong.COUNT-1 < jongSeongIndex)
            throw new IndexOutOfBoundsException();

        if(isDoubleBadChim && (!isDouble))
            throw new IllegalArgumentException();

        return new JongSeong() {
            @Override
            public @Range(from = -1, to = ChoSeong.COUNT - 1) int getChoSeongIndex() {
                return choSeongIndex;
            }

            @Override
            public @Range(from = 0, to = JongSeong.COUNT - 1) int getJongSeongIndex() {
                return jongSeongIndex;
            }
            @Override
            public boolean isDouble() {
                return isDouble;
            }

            @Override
            public boolean isDoubleBadchim() {
                return isDoubleBadChim;
            }
        };
    }
    private static @NotNull JongSeong single(int choSeongIndex, int jongSeongIndex) {
        return custom(choSeongIndex, jongSeongIndex, false, false);
    }
    private static @NotNull JongSeong doubles(int choSeongIndex, int jongSeongIndex) {
        return custom(choSeongIndex, jongSeongIndex, true, false);
    }
    private static @NotNull JongSeong badchim(int choSeongIndex, int jongSeongIndex) {
        return custom(choSeongIndex, jongSeongIndex, true, true);
    }
    public static JongSeong get(int jongSeongIndex) {
        return switch (jongSeongIndex) {
            case  0 -> single ( 0, jongSeongIndex); // ㄱ
            case  1 -> doubles( 1, jongSeongIndex); // ㄲ
            case  2 -> doubles(-1, jongSeongIndex); // ㄳ
            case  3 -> single ( 2, jongSeongIndex); // ㄴ
            case  4 -> doubles(-1, jongSeongIndex); // ㄵ
            case  5 -> doubles(-1, jongSeongIndex); // ㄶ
            case  6 -> single ( 3, jongSeongIndex); // ㄷ
            case  7 -> single ( 5, jongSeongIndex); // ㄹ
            case  8 -> doubles(-1, jongSeongIndex); // ㄺ
            case  9 -> doubles(-1, jongSeongIndex); // ㄻ
            case 10 -> doubles(-1, jongSeongIndex); // ㄼ
            case 11 -> doubles(-1, jongSeongIndex); // ㄽ
            case 12 -> doubles(-1, jongSeongIndex); // ㄾ
            case 13 -> doubles(-1, jongSeongIndex); // ㄿ
            case 14 -> doubles(-1, jongSeongIndex); // ㅀ
            case 15 -> single ( 6, jongSeongIndex); // ㅁ
            case 16 -> single ( 7, jongSeongIndex); // ㅂ
            case 17 -> doubles(-1, jongSeongIndex); // ㅄ
            case 18 -> single ( 9, jongSeongIndex); // ㅅ
            case 19 -> doubles(10, jongSeongIndex); // ㅆ
            case 20 -> single (11, jongSeongIndex); // ㅇ
            case 21 -> single (12, jongSeongIndex); // ㅈ
            case 22 -> single (14, jongSeongIndex); // ㅊ
            case 23 -> single (15, jongSeongIndex); // ㅋ
            case 24 -> single (16, jongSeongIndex); // ㅌ
            case 25 -> single (17, jongSeongIndex); // ㅍ
            case 26 -> single (18, jongSeongIndex); // ㅎ
            default -> throw new IndexOutOfBoundsException();
        };
    }
}
