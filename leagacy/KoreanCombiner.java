package bef;

import bef.character.KoreanChar;
import bef.part.consonant.Chosung;
import bef.part.consonant.Jongsung;
import bef.part.vowel.JungSeong;
import bef.part.KoreanPart;
import bef.part.vowel.KoreanVowel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KoreanCombiner {
    /**
     * The entered Korean syllables are combined into KoreanChar.
     * You can combine like: I, M, F, I+M, I+M+F
     * @param chosung When two or more are combined, the initial consonant comes here. If you combine just one, consonants, vowels, and final consonants can come here.
     * @param jungsung If parameter i is written, this can be not null. Here, the neuter consonant is used.
     * @param jongsung If both i and m are written, this can not be null. Here, the final consonant is used.
     * @return The entered Korean syllables are combined into KoreanChar.
     * At this time, you can use KoreanChar.toString() to check one Korean character combined.
     */
    @Contract("_, null, !null -> fail")
    public static @NotNull KoreanChar combine(@NotNull Chosung chosung, @Nullable JungSeong jungsung, @Nullable Jongsung jongsung) {
        if(jungsung == null && jongsung != null)
            throw new IllegalArgumentException("Korean cannot combine with InitialSound + FinalSound.");

        if(jongsung == null) {
            if(jungsung == null) {
                return new KoreanChar(12593 + chosung.getId());
            }

            if(chosung instanceof Jongsung)
                throw new IllegalArgumentException("Korean cannot combine with FinalSound + MidSound.");

            if(chosung instanceof KoreanVowel)
                throw new IllegalArgumentException("Korean cannot combine with KoreanVowel + MidSound.");

            return new KoreanChar(chosung.getAdding() + jungsung.getAdding());
        }

        if(chosung instanceof Jongsung)
            throw new IllegalArgumentException("Korean cannot combine with FinalSound + MidSound + FinalSound.");

        if(chosung instanceof KoreanVowel)
            throw new IllegalArgumentException("Korean cannot combine with KoreanVowel + MidSound + FinalSound.");

        return new KoreanChar(chosung.getAdding() + jungsung.getAdding() + jongsung.getAdding());
    }
    /**
     * The entered Korean syllables are combined into KoreanChar.
     * You can combine like: I, M, F, I+M, I+M+F
     * @param part When two or more are combined, the initial consonant comes here. If you combine just one, consonants, vowels, and final consonants can come here.
     * @param mvl If parameter i is written, this can be not null. Here, the neuter consonant is used.
     * @param lcl If both i and m are written, this can not be null. Here, the final consonant is used.
     * @return The entered Korean syllables are combined into KoreanChar.
     * At this time, you can use KoreanChar.toString() to check one Korean character combined.
     */
    @Contract("_, null, !null -> fail")
    public static @NotNull KoreanChar combine(@NotNull KoreanPart part) {
        if(mvl == null && lcl != null)
            throw new IllegalArgumentException("Korean cannot combine with InitialSound + FinalSound.");

        if(lcl == null) {
            if(mvl == null) {
                return new KoreanChar(12593 + part.getId());
            }

            if(part instanceof Jongsung)
                throw new IllegalArgumentException("Korean cannot combine with FinalSound + MidSound.");

            if(part instanceof KoreanVowel)
                throw new IllegalArgumentException("Korean cannot combine with KoreanVowel + MidSound.");

            return new KoreanChar(part.getAdding() + mvl.getAdding());
        }

        if(part instanceof Jongsung)
            throw new IllegalArgumentException("Korean cannot combine with FinalSound + MidSound + FinalSound.");

        if(part instanceof KoreanVowel)
            throw new IllegalArgumentException("Korean cannot combine with KoreanVowel + MidSound + FinalSound.");

        return new KoreanChar(part.getAdding() + mvl.getAdding() + lcl.getAdding());
    }
}
