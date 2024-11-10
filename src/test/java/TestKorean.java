import kronaegit.korean.combine.KoreanChar;
import kronaegit.korean.dueum.DueumLaw;
import kronaegit.korean.part.jaeum.ChoSeong;
import kronaegit.korean.part.jaeum.JongSeong;
import kronaegit.korean.part.moeum.Moeum;

public class TestKorean {
    public static void main(String[] args) {
        ChoSeong  a = ChoSeong.ㅎ;
        Moeum     b = Moeum.ㅣ;
        JongSeong c = JongSeong.ㅎ;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println();

        KoreanChar kc = KoreanChar.combine(a, b, c);
        System.out.println(kc);

        System.out.println();

        System.out.println(kc.getChoSeong());
        System.out.println(kc.getMoeum());
        System.out.println(kc.getJongSeong());

        System.out.println();
        System.out.println();

        for (int i = 0; i < ChoSeong.COUNT; i++)
            System.out.println(ChoSeong.get(i));
        System.out.println();
        for (int i = 0; i < Moeum.COUNT; i++)
            System.out.println(Moeum.get(i));
        System.out.println();
        for (int i = 0; i < JongSeong.COUNT; i++)
            System.out.println(JongSeong.get(i));

        System.out.println();
        System.out.println();

        System.out.println(KoreanChar.from("꿽"));
    }
}
