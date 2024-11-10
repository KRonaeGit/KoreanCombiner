package kronaegit.korean.part;

/**
 * KoreanPart 클래스를 통해 한글의 각 음절을 구현할 수 있습니다.
 * 이 클래스 하위에 있는 클래스는 다음과 같습니다: Jaeum, Moeum(ChoSeong, JongSeong)
 */
public interface KoreanPart {
    /**
     * Korean Combiner 는 유니코드 코드포인트를 계산하며 작동합니다.
     * 각 음절의 <code>getAdding()</code> 값을 합한 후 <code>KoreanChar.FROM</code> 을 더하여 코드포인트를 구할 수 있으며
     * <code>Character.toString(codepoint)</code> 를 통해 코드포인트를 유니코드 글자로 변환할 수 있습니다.
     * 즉, <code>getAdding()</code> 메소드는 코드포인트 계산을 위해 필요합니다.
     * @return 유니코드 코드포인트 계산을 위해 더해야 할 값을 반환합니다.
     */
    int getAdding();

    /**
     * 겹쳐진 글자인지 확인합니다. <code>isSingle()</code> 메소드의 반환값에 반대됩니다.
     * @return 이중모음(Ex. ㅑ), 겹닿소리(쌍자음 : Ex. ㄲ) 또는 겹받침(Ex. ㄺ) 일 때 true 를 반환합니다.
     */
    boolean isDouble();

    /**
     * 겹쳐지지 않은 글자인지 확인합니다. <code>isDouble()</code> 메소드의 반환값에 반대됩니다.
     * 단모음(Ex. ㅏ) 또는 닿소리(단자음 : Ex. ㄱ) 인 경우 true 를 반환합니다.
     * @return <code>isDouble()</code> 의 값에 반대되는 값을 반환합니다.
     */
    default boolean isSingle() {
        return !isDouble();
    }
}
