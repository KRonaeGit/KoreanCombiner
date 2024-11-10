package kronaegit.korean.combine;

import kronaegit.korean.part.KoreanPart;
import kronaegit.korean.part.jaeum.ChoSeong;
import kronaegit.korean.part.jaeum.JongSeong;
import kronaegit.korean.part.moeum.Moeum;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class KoreanUnChar extends CodepointChar {
    public static final int JAEUM_FROM = 12593;
    public static final int JAEUM_TO   = 12622;
    public static final int MOEUM_FROM = 12623;
    public static final int MOEUM_TO   = 12643;
    private KoreanUnChar(int codepoint) {
        super(codepoint);
        if(codepoint < JAEUM_FROM || 12643 < MOEUM_TO)
            throw new IndexOutOfBoundsException();
    }
    public boolean isJaeum() {
        return JAEUM_FROM <= codepoint && codepoint <= JAEUM_TO;
    }
    public boolean isMoeum() {
        return MOEUM_FROM <= codepoint && codepoint <= MOEUM_TO;
    }
    public static @NotNull KoreanUnChar get(KoreanPart part) {
        if (part instanceof ChoSeong detailed) {
            return getDetailed(detailed);
        } else if (part instanceof Moeum detailed) {
            return getDetailed(detailed);
        } else if (part instanceof JongSeong detailed) {
            return getDetailed(detailed);
        }
        throw new ClassCastException();
    }
    @Contract(pure = true)
    private static @NotNull KoreanUnChar getDetailed(@NotNull ChoSeong detailed) {
        return switch (detailed.getChoSeongIndex()) {
            case  0 -> new KoreanUnChar(12593);
            case  1 -> new KoreanUnChar(12594);
            case  2 -> new KoreanUnChar(12596);
            case  3 -> new KoreanUnChar(12599);
            case  4 -> new KoreanUnChar(12600);
            case  5 -> new KoreanUnChar(12601);
            case  6 -> new KoreanUnChar(12609);
            case  7 -> new KoreanUnChar(12610);
            case  8 -> new KoreanUnChar(12611);
            case  9 -> new KoreanUnChar(12613);
            case 10 -> new KoreanUnChar(12614);
            case 11 -> new KoreanUnChar(12615);
            case 12 -> new KoreanUnChar(12616);
            case 13 -> new KoreanUnChar(12617);
            case 14 -> new KoreanUnChar(12618);
            case 15 -> new KoreanUnChar(12619);
            case 16 -> new KoreanUnChar(12620);
            case 17 -> new KoreanUnChar(12621);
            case 18 -> new KoreanUnChar(12622);
            default -> throw new IllegalArgumentException();
        };
    }
    @Contract(pure = true)
    private static @NotNull KoreanUnChar getDetailed(@NotNull Moeum detailed) {
        return switch (detailed.getMoeumIndex()) {
            case  0 -> new KoreanUnChar(12623);
            case  1 -> new KoreanUnChar(12624);
            case  2 -> new KoreanUnChar(12625);
            case  3 -> new KoreanUnChar(12626);
            case  4 -> new KoreanUnChar(12627);
            case  5 -> new KoreanUnChar(12628);
            case  6 -> new KoreanUnChar(12629);
            case  7 -> new KoreanUnChar(12630);
            case  8 -> new KoreanUnChar(12631);
            case  9 -> new KoreanUnChar(12632);
            case 10 -> new KoreanUnChar(12633);
            case 11 -> new KoreanUnChar(12634);
            case 12 -> new KoreanUnChar(12635);
            case 13 -> new KoreanUnChar(12636);
            case 14 -> new KoreanUnChar(12637);
            case 15 -> new KoreanUnChar(12638);
            case 16 -> new KoreanUnChar(12639);
            case 17 -> new KoreanUnChar(12640);
            case 18 -> new KoreanUnChar(12641);
            case 19 -> new KoreanUnChar(12642);
            case 20 -> new KoreanUnChar(12643);
            default -> throw new IllegalArgumentException();
        };
    }
    @Contract(pure = true)
    private static @NotNull KoreanUnChar getDetailed(@NotNull JongSeong detailed) {
        return switch (detailed.getJongSeongIndex()) {
            case  0 -> new KoreanUnChar(12593);
            case  1 -> new KoreanUnChar(12594);
            case  2 -> new KoreanUnChar(12595);
            case  3 -> new KoreanUnChar(12596);
            case  4 -> new KoreanUnChar(12597);
            case  5 -> new KoreanUnChar(12598);
            case  6 -> new KoreanUnChar(12599);
            case  7 -> new KoreanUnChar(12601);
            case  8 -> new KoreanUnChar(12602);
            case  9 -> new KoreanUnChar(12603);
            case 10 -> new KoreanUnChar(12604);
            case 11 -> new KoreanUnChar(12605);
            case 12 -> new KoreanUnChar(12606);
            case 13 -> new KoreanUnChar(12607);
            case 14 -> new KoreanUnChar(12608);
            case 15 -> new KoreanUnChar(12609);
            case 16 -> new KoreanUnChar(12610);
            case 17 -> new KoreanUnChar(12612);
            case 18 -> new KoreanUnChar(12613);
            case 19 -> new KoreanUnChar(12614);
            case 20 -> new KoreanUnChar(12615);
            case 21 -> new KoreanUnChar(12616);
            case 22 -> new KoreanUnChar(12618);
            case 23 -> new KoreanUnChar(12619);
            case 24 -> new KoreanUnChar(12620);
            case 25 -> new KoreanUnChar(12621);
            case 26 -> new KoreanUnChar(12622);
            default -> throw new IllegalArgumentException();
        };
    }
}
