package kronaegit.korean.part.jaeum;

import kronaegit.korean.combine.KoreanUnChar;
import kronaegit.korean.part.moeum.Moeum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

public abstract class ChoSeong implements Jaeum {
    public static final int COUNT = 19;

    public static final ChoSeong ㄱ = get(0);
    public static final ChoSeong ㄲ = get(1);
    public static final ChoSeong ㄴ = get(2);
    public static final ChoSeong ㄷ = get(3);
    public static final ChoSeong ㄸ = get(4);
    public static final ChoSeong ㄹ = get(5);
    public static final ChoSeong ㅁ = get(6);
    public static final ChoSeong ㅂ = get(7);
    public static final ChoSeong ㅃ = get(8);
    public static final ChoSeong ㅅ = get(9);
    public static final ChoSeong ㅆ = get(10);
    public static final ChoSeong ㅇ = get(11);
    public static final ChoSeong ㅈ = get(12);
    public static final ChoSeong ㅉ = get(13);
    public static final ChoSeong ㅊ = get(14);
    public static final ChoSeong ㅋ = get(15);
    public static final ChoSeong ㅌ = get(16);
    public static final ChoSeong ㅍ = get(17);
    public static final ChoSeong ㅎ = get(18);

    @Override
    public int getAdding() {
        return Moeum.COUNT * (JongSeong.COUNT + 1) * getChoSeongIndex();
    }
    @Override
    public abstract @Range(from=0,to=ChoSeong.COUNT-1) int getChoSeongIndex();
    @Override
    public abstract @Range(from=-1,to=JongSeong.COUNT-1) int getJongSeongIndex();

    /**
     * @return Is 겹닿소리
     */
    @Override
    public abstract boolean isDouble();
    @Override
    public ChoSeong asChoSeong() {
        return this;
    }
    @Override
    public JongSeong asJongSeong() {
        int index = getJongSeongIndex();
        return index == -1 ? null : JongSeong.get(index);
    }
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
    private static @NotNull ChoSeong custom(int choSeongIndex, int jongSeongIndex, boolean isDouble) {
        if(choSeongIndex < 0 || ChoSeong.COUNT-1 < choSeongIndex)
            throw new IndexOutOfBoundsException();
        if(jongSeongIndex < -1 || JongSeong.COUNT-1 < jongSeongIndex)
            throw new IndexOutOfBoundsException();

        return new ChoSeong() {
            @Override
            public int getChoSeongIndex() {
                return choSeongIndex;
            }
            @Override
            public int getJongSeongIndex() {
                return jongSeongIndex;
            }
            @Override
            public boolean isDouble() {
                return isDouble;
            }
        };
    }
    private static @NotNull ChoSeong single(int index, int jongSeongIndex) {
        return custom(index, jongSeongIndex, false);
    }
    private static @NotNull ChoSeong doubles(int index, int jongSeongIndex) {
        return custom(index, jongSeongIndex, true);
    }
    public static ChoSeong get(int choSeongIndex) {
        return switch (choSeongIndex) {
            case  0 -> single (choSeongIndex, 0); // ㄱ
            case  1 -> doubles(choSeongIndex, 1); // ㄲ
            case  2 -> single (choSeongIndex, 3); // ㄴ
            case  3 -> single (choSeongIndex, 6); // ㄷ
            case  4 -> doubles(choSeongIndex, -1); // ㄸ
            case  5 -> single (choSeongIndex, 7); // ㄹ
            case  6 -> single (choSeongIndex, 15); // ㅁ
            case  7 -> single (choSeongIndex, 16); // ㅂ
            case  8 -> doubles(choSeongIndex, -1); // ㅃ
            case  9 -> single (choSeongIndex, 18); // ㅅ
            case 10 -> doubles(choSeongIndex, 19); // ㅆ
            case 11 -> single (choSeongIndex, 20); // ㅇ
            case 12 -> single (choSeongIndex, 21); // ㅈ
            case 13 -> doubles(choSeongIndex, -1); // ㅉ
            case 14 -> single (choSeongIndex, 22); // ㅊ
            case 15 -> single (choSeongIndex, 23); // ㅋ
            case 16 -> single (choSeongIndex, 24); // ㅌ
            case 17 -> single (choSeongIndex, 25); // ㅍ
            case 18 -> single (choSeongIndex, 26); // ㅎ
            default -> throw new IndexOutOfBoundsException();
        };
    }
}