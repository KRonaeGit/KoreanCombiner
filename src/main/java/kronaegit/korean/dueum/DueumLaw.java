package kronaegit.korean.dueum;

import kronaegit.korean.combine.KoreanChar;
import kronaegit.korean.part.jaeum.ChoSeong;
import kronaegit.korean.part.jaeum.JongSeong;
import kronaegit.korean.part.moeum.Moeum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 두음법칙을 구현합니다.
 * 제대로 구현되지 않아 버그 발생의 여지가 있으므로 사용을 권장하지 않습니다.
 */
@Deprecated
public class DueumLaw {
    /**
     * 두음법칙에 따라 변환합니다. 변환이 불가능하다면 <code>null</code>을 반환합니다.
     * 두음법칙은 원래 단어의 한자 뜻에 따라 다르고 더 복잡합니다. 하지만 단어의 뜻을 모르는 경우라고 생각하고 구현된 코드입니다.
     * @param beforeCharHasNieunBadchim 전 글자가 ㄴ(니은) 받침을 가진 여부
     * @param kc 두음법칙을 적용할 글자
     * @return 두음법칙이 적용된 글자
     */

    public static @Nullable KoreanChar law(boolean beforeCharHasNieunBadchim, @NotNull KoreanChar kc) {
        ChoSeong a = kc.getChoSeong();
        Moeum b = kc.getMoeum();
        JongSeong c = kc.getJongSeong();
        if(b.isDouble() && (a.equals(ChoSeong.ㄹ) || a.equals(ChoSeong.ㄴ)))
            return KoreanChar.combine(ChoSeong.ㅇ, b, c);

        if(a.equals(ChoSeong.ㄹ) && b.isSingle() && (!b.equals(Moeum.ㅣ)))
            return KoreanChar.combine(ChoSeong.ㄴ, b, c);

        if(kc.equals(KoreanChar.from("렬")) || kc.equals(KoreanChar.from("률")))
            return KoreanChar.combine(ChoSeong.ㅇ, b, c);

        return null;
    }
    public static String law(String source) {
        List<KoreanChar> chars = KoreanChar.fromLot(source);
        for (int i = 0; i < chars.size(); i++) {
            boolean bchnb = false;
            if(i > 1) {
                JongSeong jongSeong = chars.get(i-1).getJongSeong();
                if(jongSeong != null)
                    bchnb = jongSeong.equals(JongSeong.ㄴ);
            }
            KoreanChar lawed = law(bchnb, chars.get(i));
            chars.set(i, lawed == null ? chars.get(i) : lawed);
        }
        return KoreanChar.s(chars);
    }
}
