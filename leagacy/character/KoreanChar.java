package bef.character;

import io.github.kronae.korean.combiner.annotation.StringLimit;
import bef.part.KoreanPart;
import bef.part.consonant.Jongsung;
import bef.part.consonant.Chosung;
import bef.part.vowel.JungSeong;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public class KoreanChar {
    private final int codePoint;
    private @Nullable KoreanPart c;
    private @Nullable JungSeong m;
    private @Nullable Jongsung f;

    /**
     * It takes one Korean character and turns it into a KoreanChar.
     * @param character Korean one letter
     */
    public KoreanChar(@StringLimit(length=1) @NotNull String character) {
        if(character.length() != 1)
            throw new IllegalArgumentException("KoreanChar must be 1 character.");

        this.codePoint = character.codePointAt(0);
        checkCodePoint();
    }

    /**
     * KoreanChar is created using Unicode codePoint.
     * Range: 12593~12643 & 44032~55203
     * @param codePoint Unicode codePoint
     */
    public KoreanChar(int codePoint) {
        this.codePoint = codePoint;
        checkCodePoint();
    }

    /**
     * Create KoreanChar instance with KoreanChar index.
     * @param index -50 ~ -21   : ㄱ(G) ~ ㅎ(H)
     *              -20 ~ 0     : ㅏ(A) ~ ㅣ(I)
     *              1   ~ 11172 : 가(GA) ~ 힣(HIH)
     */
    public KoreanChar(@Range(from=-50, to=11172) short index) {
        if(index <= 0) {
            this.codePoint = 12593 + 50 + index;
        } else {
            this.codePoint = 44032 -  1 + index;
        }
        checkCodePoint();
    }

    /**
     * This method check that the codepoint is korean.
     */
    private void checkCodePoint() {
        if(!((12593 <= codePoint && codePoint <= 12643) || (44032 <= codePoint && codePoint <= 55203)))
            throw new IllegalArgumentException("This is not modern Korean(" + codePoint + ").");
    }

    /**
     * Convert KoreanChar to Korean Hangul characters.
     * @return Korean one letter
     */
    @Override
    public @StringLimit(length=1) String toString() {
        return Character.toString(codePoint);
    }

    /**
     * If getMiddle() is null, it returns a consonant, vowel, or neuter. (KoreanPart)
     * If getMiddle() is not null, it returns the initial consonant. (KoreanInitialSound)
     * @return KoreanPart
     */
    @NotNull
    public KoreanPart getFirst() {
        if(c == null) {
            if(12593 <= codePoint && codePoint <= 12643) {
                int charId = codePoint - 12593;
                c = id2part(charId);
                return c;
            }

            int charId = codePoint - 44032;
            c = iid2i(charId / 588);
        }
        return c;
    }

    /**
     * Returns null if neuter is not combined.
     * If not, returns neuter.
     * @return KoreanMidSound
     */
    @Nullable
    public JungSeong getMiddle() {
        if(m == null) {
            if(12593 <= codePoint && codePoint <= 12643)
                return null;

            int charId = codePoint - 44032;
            int c_last = charId % 588;

            KoreanPart part = id2part(c_last / 28 + 30);
            if(part instanceof JungSeong kms) {
                m = kms;
            } else {
                throw new RuntimeException("Unknown error: This is NOT a KoreanMidSound. (" + part + ")");
            }
        }
        return m;
    }

    /**
     * If the final consonant is not combined, null is returned.
     * If not, it returns the final consonant.
     * @return KoreanFinalSound
     */
    @Nullable
    public Jongsung getFinal() {
        if(f == null) {
            if(12593 <= codePoint && codePoint <= 12643)
                return null;

            int charId = codePoint - 44032;
            int c_last = charId % 588;
            int m_last = c_last % 28;

            f = fid2f(m_last);
        }
        return f;
    }

    /**
     * The combination of initial, medial and final consonants is listed based on the symbol +.
     * At this time, if the value is empty, it is not written down.
     * Ex) Kew -> ㄲ+ㅞ
     * Ex) Chicken -> ㄷ+ㅏ+ㄺ
     * @return the RECIPE
     */
    public String getRecipe() {
        return  getMiddle() == null ? getFirst().toString() :
                getFinal()  == null ? getFirst() + "+" + getMiddle().toString() :
                getFirst() + "+" + getMiddle().toString() + "+" + getFinal().toString();
    }

    /**
     * Convert charId to KoreanPart
     */
    @Contract(pure = true)
    public static @NotNull KoreanPart id2part(@Range(from=0, to=50) int charId) {
        return  charId == 0  ? Chosung.ㄱ :
                charId == 1  ? Chosung.ㄲ :
                charId == 2  ? Jongsung.ㄳ :
                charId == 3  ? Chosung.ㄴ :
                charId == 4  ? Jongsung.ㄵ :
                charId == 5  ? Jongsung.ㄶ :
                charId == 6  ? Chosung.ㄷ :
                charId == 7  ? Chosung.ㄸ :
                charId == 8  ? Chosung.ㄹ :
                charId == 9  ? Jongsung.ㄺ :
                charId == 10 ? Jongsung.ㄻ :
                charId == 11 ? Jongsung.ㄼ :
                charId == 12 ? Jongsung.ㄽ :
                charId == 13 ? Jongsung.ㄾ :
                charId == 14 ? Jongsung.ㄿ :
                charId == 15 ? Jongsung.ㅀ :
                charId == 16 ? Chosung.ㅁ :
                charId == 17 ? Chosung.ㅂ :
                charId == 18 ? Chosung.ㅃ :
                charId == 19 ? Jongsung.ㅄ :
                charId == 20 ? Chosung.ㅅ :
                charId == 21 ? Chosung.ㅆ :
                charId == 22 ? Chosung.ㅇ :
                charId == 23 ? Chosung.ㅈ :
                charId == 24 ? Chosung.ㅉ :
                charId == 25 ? Chosung.ㅊ :
                charId == 26 ? Chosung.ㅋ :
                charId == 27 ? Chosung.ㅌ :
                charId == 28 ? Chosung.ㅍ :
                charId == 29 ? Chosung.ㅎ :
                charId == 30 ? JungSeong.ㅏ :
                charId == 31 ? JungSeong.ㅐ :
                charId == 32 ? JungSeong.ㅑ :
                charId == 33 ? JungSeong.ㅒ :
                charId == 34 ? JungSeong.ㅓ :
                charId == 35 ? JungSeong.ㅔ :
                charId == 36 ? JungSeong.ㅕ :
                charId == 37 ? JungSeong.ㅖ :
                charId == 38 ? JungSeong.ㅗ :
                charId == 39 ? JungSeong.ㅘ :
                charId == 40 ? JungSeong.ㅙ :
                charId == 41 ? JungSeong.ㅚ :
                charId == 42 ? JungSeong.ㅛ :
                charId == 43 ? JungSeong.ㅜ :
                charId == 44 ? JungSeong.ㅝ :
                charId == 45 ? JungSeong.ㅞ :
                charId == 46 ? JungSeong.ㅟ :
                charId == 47 ? JungSeong.ㅠ :
                charId == 48 ? JungSeong.ㅡ :
                charId == 49 ? JungSeong.ㅢ :
                               JungSeong.ㅣ ;
    }
    /**
     * Convert adding Id to KoreanFinalSound
     */
    @Contract(pure = true)
    public static @NotNull Jongsung fid2f(@Range(from=1, to=27) int ai) {
        return  ai == 1  ? Jongsung.ㄱ :
                ai == 2  ? Jongsung.ㄲ :
                ai == 3  ? Jongsung.ㄳ :
                ai == 4  ? Jongsung.ㄴ :
                ai == 5  ? Jongsung.ㄵ :
                ai == 6  ? Jongsung.ㄶ :
                ai == 7  ? Jongsung.ㄷ :
                ai == 8  ? Jongsung.ㄹ :
                ai == 9  ? Jongsung.ㄺ :
                ai == 10 ? Jongsung.ㄻ :
                ai == 11 ? Jongsung.ㄼ :
                ai == 12 ? Jongsung.ㄽ :
                ai == 13 ? Jongsung.ㄾ :
                ai == 14 ? Jongsung.ㄿ :
                ai == 15 ? Jongsung.ㅀ :
                ai == 16 ? Jongsung.ㅁ :
                ai == 17 ? Jongsung.ㅂ :
                ai == 18 ? Jongsung.ㅄ :
                ai == 19 ? Jongsung.ㅅ :
                ai == 20 ? Jongsung.ㅆ :
                ai == 21 ? Jongsung.ㅇ :
                ai == 22 ? Jongsung.ㅈ :
                ai == 23 ? Jongsung.ㅊ :
                ai == 24 ? Jongsung.ㅋ :
                ai == 25 ? Jongsung.ㅌ :
                ai == 26 ? Jongsung.ㅍ :
                           Jongsung.ㅎ ;
    }
    /**
     * Convert charId to KoreanInitialSound
     */
    @Contract(pure = true)
    public static @NotNull Chosung iid2i(@Range(from=0, to=18) int charId) {
        return  charId == 0  ? Chosung.ㄱ :
                charId == 1  ? Chosung.ㄲ :
                charId == 2  ? Chosung.ㄴ :
                charId == 3  ? Chosung.ㄷ :
                charId == 4  ? Chosung.ㄸ :
                charId == 5  ? Chosung.ㄹ :
                charId == 6  ? Chosung.ㅁ :
                charId == 7  ? Chosung.ㅂ :
                charId == 8  ? Chosung.ㅃ :
                charId == 9  ? Chosung.ㅅ :
                charId == 10 ? Chosung.ㅆ :
                charId == 11 ? Chosung.ㅇ :
                charId == 12 ? Chosung.ㅈ :
                charId == 13 ? Chosung.ㅉ :
                charId == 14 ? Chosung.ㅊ :
                charId == 15 ? Chosung.ㅋ :
                charId == 16 ? Chosung.ㅌ :
                charId == 17 ? Chosung.ㅍ :
                               Chosung.ㅎ ;
    }

    /**
     * @return Returns Unicode Codepoint of the KoreanChar.
     */
    public int getUnicodeCodePoint() {
        return codePoint;
    }
    /*
    public KoreanChar(@Range(from=-50, to=11172) short index) {
        if(index <= 0) {
            this.codePoint = 12593 + 50 + index;
        } else {
            this.codePoint = 44032 -  1 + index;
        }
        checkCodePoint();
    }
     */
    public short getKoreanCharId() {
        if(12593 <= codePoint && codePoint <= 12643)
            return (short) -(codePoint - 12593 - 50);

        return (short) (codePoint - 44032 + 1);
    }
}
