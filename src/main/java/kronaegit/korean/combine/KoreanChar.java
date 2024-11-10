package kronaegit.korean.combine;

import kronaegit.korean.part.KoreanPart;
import kronaegit.korean.part.jaeum.ChoSeong;
import kronaegit.korean.part.jaeum.JongSeong;
import kronaegit.korean.part.moeum.Moeum;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class KoreanChar extends CodepointChar {
    public static final int FROM = 44032; // 44032 = 0xAC00 = 가
    public static final int TO = 55203; // 힣

    public static final int BASE = FROM + 1; // 44032+1=44033
    public static final int FACTOR_M = JongSeong.COUNT +  1; // 27+1=28
    public static final int FACTOR_F = FACTOR_M * Moeum.COUNT; // 28*21=588

    private KoreanChar(int codepoint) {
        super(codepoint);
        if(codepoint < FROM || TO < codepoint)
            throw new IndexOutOfBoundsException();
    }

    @Contract("_ -> new")
    public static @NotNull KoreanChar from(@NotNull String c) {
        return new KoreanChar(c.codePointAt(0));
    }
    public static @NotNull List<KoreanChar> fromLot(@NotNull String c) {
        List<KoreanChar> result = new ArrayList<>();
        for (int i = 0; i < c.length(); i++)
            result.add(from(c.substring(i, i + 1)));
        return result;
    }

    public static String s(@NotNull List<KoreanChar> chars) {
        String result = "";
        for (KoreanChar kc : chars)
            result += kc.toString();
        return result;
    }

    public int getChoSeongIndex() {
        int offset = this.codepoint - FROM;
        return offset / FACTOR_F;
    }
    public int getMoeumIndex() {
        int offset = this.codepoint - FROM;
        return (offset % FACTOR_F) / FACTOR_M;
    }
    public int getJongSeongIndex() {
        int offset = this.codepoint - FROM;
        return (offset % FACTOR_M);
    }
    public @NotNull ChoSeong getChoSeong() {
        return ChoSeong.get(getChoSeongIndex());
    }
    public @NotNull Moeum getMoeum() {
        return Moeum.get(getMoeumIndex());
    }
    public @Nullable JongSeong getJongSeong() {
        int result = getJongSeongIndex();
        return result == 0 ? null : JongSeong.get(result - 1);
    }

    public static @NotNull KoreanUnChar combine(KoreanPart part) {
        return KoreanUnChar.get(part);
    }
    @Contract("_, _, _ -> new")
    public static @NotNull KoreanChar combine(@NotNull ChoSeong choSeong, @NotNull Moeum moeum, @Nullable JongSeong jongSeong) {
        return new KoreanChar(FROM + choSeong.getAdding() + moeum.getAdding() + (jongSeong == null ? 0 : jongSeong.getAdding()));
    }
    public static @NotNull KoreanChar combine(@NotNull ChoSeong choSeong, @NotNull Moeum moeum) {
        return combine(choSeong, moeum, null);
    }
}
