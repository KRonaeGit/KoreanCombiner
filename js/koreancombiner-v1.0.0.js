/**
 * @typedef {Object} KoreanPart
 * @property {string} type
 * @property {number} codepoint
 * @property {string} char
 * @property {number} apply
 * 
 * @typedef {KoreanPart} Jaeum = choseong + jongseong (except duplications)
 * @typedef {KoreanPart} Choseong
 * @typedef {KoreanPart} Jungseong
 * @typedef {KoreanPart} Jongseong
 * 
 * @typedef {Object} CombinableKoreanParts
 * @property {Choseong} choseong
 * @property {Jungseong} jungseong
 * @property {Jongseong} jongseong
 * 
 * @typedef {CombinableKoreanParts} DecombinedKoreanParts
 * @property {string} char
 * @property {string} craft
 * 
 * @typedef {Object} KoreanPartInfo
 * @property {number} count
 * @property {Array.<string>} letters
 */
/**
 * @type {{
 *     codepoint: number,
 *     count: number,
 *     jaeum: KoreanPartInfo,
 *     choseong: KoreanPartInfo,
 *     jungseong: KoreanPartInfo,
 *     jongseong: KoreanPartInfo
 * }}
 */
const Korean = {
    "codepoint": 44032,
    "count": 11172,
    "jaeum": { count: 30, letters: [] },
    "choseong": { count: 19, letters: "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ".split("") },
    "jungseong": { count: 21, letters: [] },
    "jongseong": { count: 27, letters: "ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ".split("") },
};
{
    // 자음
    let i = 0;
    for (let codepoint = 12593; codepoint <= 12622; codepoint++) {
        let char = String.fromCodePoint(codepoint);
        Korean.jaeum[char] = {
            "type": "jaeum",
            "codepoint": codepoint,
            "char": char,
            "apply": null
        }
        Korean.jaeum.letters.push(char);
        i++;
    }
    // 모음 중성
    i = 0;
    for (let codepoint = 12623; codepoint <= 12643; codepoint++) {
        let char = String.fromCodePoint(codepoint);
        Korean.jungseong[char] = {
            "type": "jungseong",
            "codepoint": codepoint,
            "char": char,
            "apply": (Korean.jongseong.count + 1) * i
        }
        Korean.jungseong.letters.push(char);
        i++;
    }
    // 초성
    for (i = 0; i < Korean.choseong.letters.length; i++) {
        let char = Korean.choseong.letters[i];
        Korean.choseong[char] = {
            "type": "choseong",
            "codepoint": Korean.jaeum[char].codepoint,
            "char": char,
            "apply": Korean.jungseong.count * (Korean.jongseong.count + 1) * i
        }
    }
    // 종성
    for (i = 0; i < Korean.jongseong.letters.length; i++) {
        let char = Korean.jongseong.letters[i];
        Korean.jongseong[char] = {
            "type": "jongseong",
            "codepoint": Korean.jaeum[char].codepoint,
            "char": char,
            "apply": i + 1
        }
    }
}
/**
 * 
 * @param {string} char 
 * @param {number || undefined || null} at
 */
Korean.is = (char, at) => {
    let codepoint = char.codePointAt(at);
    if (12593 <= codepoint && codepoint <= 12643) return true;
    if (Korean.codepoint <= codepoint && codepoint <= (Korean.codepoint + Korean.count - 1)) return true;
    return false;
}
/**
 * @param {Choseong || CombinableKoreanParts} choseong
 * @param {Jungseong || null || undefined} jungseong
 * @param {Jongseong || null || undefined} jongseong
 * @returns {string} char
 */
Korean.combine = (choseong, jungseong, jongseong) => {
    if (typeof (choseong) == "string") return Korean.combine(Korean.choseong[choseong], jungseong, jongseong);
    if (typeof (jungseong) == "string") return Korean.combine(choseong, Korean.jungseong[jungseong], jongseong);
    if (typeof (jongseong) == "string") return Korean.combine(choseong, jungseong, Korean.jongseong[jongseong]);
    if (choseong == null || choseong == undefined)
        return "";
    if (jungseong == null || jungseong == undefined) {
        if (choseong.type != "choseong") {
            return Korean.combine(choseong.choseong, choseong.jungseong, choseong.jongseong);
        }
        return String.fromCodePoint(choseong.codepoint);
    }
    return String.fromCodePoint(Korean.codepoint + choseong.apply + jungseong.apply + (jongseong == null || jongseong == undefined ? 0 : jongseong.apply))
}
/**
 * @param {string} char
 * @param {number || null || undefined} at
 * @returns {DecombinedKoreanParts || Array.<DecombinedKoreanParts>}
 */
Korean.decombine = (char, at) => {
    if (at != undefined && at != null) return Korean.decombine(char.substring(at, at + 1));
    if (char.length == 0) {
        return {
            char: char,
            craft: char
        }
    }
    if (char.length > 1) {
        let chars = char.split("");
        let res = [];
        for (let i = 0; i < chars.length; i++) {
            res.push(Korean.decombine(char, i));
        }
        res.craft = "";
        for (let i = 0; i < res.length; i++) {
            res.craft += res[i].craft;
        }
        return res;
    }

    const codepoint = char.codePointAt(0);
    const offset = codepoint - Korean.codepoint;

    if (offset < 0 || offset >= Korean.choseong.count * Korean.jungseong.count * (Korean.jongseong.count + 1)) {
        return {
            char: char,
            choseong: char,
            jungseong: null,
            jongseong: null,
            craft: char
        }
    }

    const choseongIndex = Math.floor(offset / (Korean.jungseong.count * (Korean.jongseong.count + 1)));
    const jungseongIndex = Math.floor((offset % (Korean.jungseong.count * (Korean.jongseong.count + 1))) / (Korean.jongseong.count + 1));
    const jongseongIndex = (offset % (Korean.jongseong.count + 1)) - 1;

    const choseongChar = Korean.choseong.letters[choseongIndex];
    const jungseongChar = Korean.jungseong.letters[jungseongIndex];
    const jongseongChar = jongseongIndex >= 0 ? Korean.jongseong.letters[jongseongIndex] : null;

    const res = {
        char: char,
        choseong: Korean.choseong[choseongChar],
        jungseong: Korean.jungseong[jungseongChar],
        jongseong: jongseongChar ? Korean.jongseong[jongseongChar] : null
    };
    res.craft = res.choseong.char + res.jungseong.char + (res.jongseong ? res.jongseong.char : "");

    return res;
}
Korean.isMoeum = (char) => {
    return Korean.jungseong[char] != undefined;
}
/**
 * 
 * @param {string} a 
 * @param {string || undefined || null} b 
 * @returns {string || Array.<string>}
 */
Korean.craft = (a, b) => {
    if (b == undefined || b == null) {
        let res = [];
        let chars = a.split("");
        let final = "";
        for (let i = 0; i < chars.length; i++) {
            res.push(final = Korean.craft(final, chars[i]));
        }
        return res;
    }
    if (!Korean.is(b)) return a + b;
    if (a.length > 1) {
        return a.substring(0, a.length - 1) + Korean.craft(a.substring(a.length - 1), b);
    }
    if (!Korean.is(a)) return a + b;
    let req = Korean.decombine(a);
    let isMoeum = Korean.isMoeum(b);
    if (isMoeum) {
        if (req.jungseong == null) {
            req.jungseong = b;
            return Korean.combine(req);
        }
        if (req.jongseong == null) {
            return a + b;
        }
        if (req.jongseong != null) {
            let _ = req.jongseong;
            req.jongseong = null;
            return Korean.combine(req) + Korean.combine(_.char, b);
        }
    }
    if (req.jungseong == null) {
        return a + b;
    }
    if (req.jongseong == null) {
        req.jongseong = b;
        return Korean.combine(req);
    }
    if (req.jongseong != null) {
        return a + b;
    }
    return Korean.combine(req);
}
Korean.type = (l, a, m, i) => {
    if (typeof (l) != 'function') return Korean.type((t => l.innerHTML = t), a, m, i);
    if (i == undefined) i = 0;
    if (a.length == i) return;
    return new Promise(res => {
        l(a[i]);
        setTimeout(async () => {
            await Korean.type(l, a, m, ++i);
            res();
        }, m);
    });
}