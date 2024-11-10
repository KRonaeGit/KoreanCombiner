package kronaegit.korean.combine;

import java.util.Objects;

public abstract class CodepointChar {
    protected final int codepoint;
    CodepointChar(int codepoint) {
        this.codepoint = codepoint;
    }
    public int getCodepoint() {
        return codepoint;
    }
    public String toString() {
        return Character.toString(codepoint);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodepointChar that = (CodepointChar) o;
        return codepoint == that.codepoint;
    }
    @Override
    public int hashCode() {
        return Objects.hash(codepoint);
    }
}
