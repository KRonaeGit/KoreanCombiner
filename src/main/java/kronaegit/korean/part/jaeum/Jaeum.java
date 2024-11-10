package kronaegit.korean.part.jaeum;

import kronaegit.korean.part.KoreanPart;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * 한글의 자음을 의미합니다.
 */
public interface Jaeum extends KoreanPart {
    /**
     * @return 유니코드 코드포인트에 더해질 값입니다. 자세한 설명은 <code>KoreanPart.getAdding()</code> 을 참조하세요.
     */
    @Override
    int getAdding();

    /**
     * @return 겹닿소리(쌍자음) 또는 겹받힘인지 그 여부를 반환합니다.
     */
    @Override
    boolean isDouble();

    /**
     * @return 초성으로 변환한 index 값을 반환합니다. 변환할 수 없는 경우(Ex. ㄺ -> 초성) <code>-1</code> 을 반환합니다.
     */
    @Range(from=-1,to=ChoSeong.COUNT-1) int getChoSeongIndex();
    /**
     * @return 종성으로 변환한 index 값을 반환합니다. 변환할 수 없는 경우(Ex. ㅃ -> 종성) <code>-1</code> 을 반환합니다.
     */
    @Range(from=-1,to=JongSeong.COUNT-1) int getJongSeongIndex();

    /**
     * @return 초성으로 변환합니다. 변환할 수 없는 경우(Ex. ㄺ -> 초성) <code>null</code> 을 반환합니다.
     */
    @Nullable ChoSeong asChoSeong();

    /**
     * @return 종성으로 변환합니다. 변환할 수 없는 경우(Ex. ㅃ -> 종성) <code>null</code> 을 반환합니다.
     */
    @Nullable JongSeong asJongSeong();
}
