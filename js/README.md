# KoreanCombiner.js
JavaScript 에서 한국어 글자를 분리/조합 하고
웹사이트에서 자주 사용하는 한글 관련 기능을 지원합니다.

## 사용법
```html
<script src="https://github.com/KRonaeGit/KoreanCombiner/releases/download/v1.0.1/koreancombiner-v1.0.0.min.js"></script>
```
위의 코드를 `<head>` 태그 안에 붙여넣으면 사용이 가능합니다!

### 조합하기
조합은 `Korean.combine(...)`을 사용합니다.
```js
Korean.combine("ㄱ"); // ㄱ
Korean.combine("ㄱ","ㅏ"); // 가
Korean.combine("ㄱ","ㅏ","ㅁ"); // 감

Korean.combine(Korean.choseong.ㄴ, Korean.jungseong.ㅏ, Korean.jongseong.ㄹ); // 날

Korean.combine({
  choseong: "ㄷ",
  jungseong: "ㅗ",
  jongseong: "ㄱ"
}); // 독

Korean.combine({
  choseong: Korean.choseong.ㄹ
  jungseong: Korean.jungseong.ㅡ
  jongseong: Korean.jongseong.ㄹ
}); // 를
```

### 분해하기
분해는 `Korean.decombine(...)`을 사용합니다.
```js
// 한글자 분해
Korean.decombine("맛").char; // 맛
Korean.decombine("맛").choseong.char; // ㅁ
Korean.decombine("맛").jungseong.char; // ㅏ
Korean.decombine("맛").jongseong.char; // ㅅ

// 여러글자 분해
Korean.decombine("안녕")[0]; // Korean.decombine("안") 과 같음
Korean.decombine("안녕")[1]; // Korean.decombine("녕") 과 같음
Korean.decombine("안녕").craft; // ㅇㅏㄴㄴㅕㅇ << 아주 유용함
```

### (추가적인기능) 타이핑
(두개의 파라미터 모두 전달된 경우)
글자를 타이핑했을 때 결과를 반환합니다
```js
Korean.craft("ㅇ","ㅏ") // 아
Korean.craft("아","ㄴ") // 안
Korean.craft("안","ㄴ") // 안ㄴ
Korean.craft("안ㄴ","ㅕ") // 안녀
Korean.craft("안녀","ㅇ") // 안녕
```

(한개의 파라미터만 전달된 경우)
글자를 타이핑했을 때의 배열을 반환합니다
예시) ㅈ -> 자 -> 잘 -> 잙 -> 잘가
```js
Korean.craft("잘가") // [ "ㅈ", "자", "잘", "잙", "잘가" ]
```

타이핑 애니매이션을 적용합니다
```js
Korean.type(
    셀렉터,
    메시지,
    딜레이 // ms(1000ms = 1s)
);
Korean.type(
    "#msg", // 아이디가 msg 인 요소
    "더 나은 세상", // 메시지
    50 // 50ms (0.05초씩 딜레이를 주며 타이핑 애니메이션 적용)
```
