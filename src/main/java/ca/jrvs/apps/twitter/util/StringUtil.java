package ca.jrvs.apps.twitter.util;
import java.util.Arrays;

public class StringUtil {

    public static boolean isEmpty(String s){
        return s == null || s.isEmpty();
    }
    public static boolean isEmpty(String... s){
        return Arrays.stream(s).anyMatch(str -> str == null || str.isEmpty());
    }

    public static String capitalize(final String str)
    {
        int strLen;
        if(str == null || (strLen = str.length()) == 0)
        {
            return str;
        }
        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint)
        {
            return str;
        }
        final int newCodePoints[] = new int[strLen];
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint;
        for(int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ){
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint;
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }
}
