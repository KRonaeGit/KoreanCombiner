package bef.part.consonant;

import bef.part.KoreanPart;

public interface KoreanConsonant extends KoreanPart {
    int getAdding();
    int getId();
    boolean equals(KoreanPart part);
}
