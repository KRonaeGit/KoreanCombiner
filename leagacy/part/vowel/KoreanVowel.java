package bef.part.vowel;

import bef.part.KoreanPart;

public interface KoreanVowel extends KoreanPart {
    int getAdding();
    int getId();
    boolean equals(KoreanPart part);
}
