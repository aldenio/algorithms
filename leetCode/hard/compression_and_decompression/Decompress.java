package leetCode.hard.compression_and_decompression;


/**
 * LeetCode 471
 * 
 * In this exercise, you're going to decompress a compressed string.
 * Your input is a compressed string of the format number[string] and the decompressed output form should be the string written number times. 
 * For example:
 *    The input "3[abc]4[ab]c"
 *    Would be output as "abcabcabcababababc"
 * 
 * Other rules:
 *    Number can have more than one digit. For example, 10[a] is allowed, and just means aaaaaaaaaa
 *    One repetition can occur inside another. For example, 2[3[a]b] decompresses into aaabaaab
 *    Characters allowed as input include digits, small English letters and brackets [ ].
 *    Digits are only to represent amount of repetitions.
 *    Letters are just letters.
 *    Brackets are only part of syntax of writing repeated substring.
 *    Input is always valid, so no need to check its validity.
 *    
 * Learning objectives:
 *    This question gives you the chance to practice with strings, recursion, algorithm, compilers, automata, and loops. 
 *    It’s also an opportunity to work on coding with better efficiency.
 */
public class Decompress {
    class MutableInt {
        public int value;
    }
    
    public String decompress(String compressed) {
        return decompress(compressed, new MutableInt());
    }

    public String decompress(String compressed, MutableInt position) {
        // the result buffer
        var result = new StringBuilder();
        // search to the end of the compressed string or to the end of the repeatable block
        while (position.value < compressed.length() && compressed.charAt(position.value) != ']') {
            // while the current char is a lowercase letter, append to result
            while (Character.isLowerCase(compressed.charAt(position.value))) {
                result.append(compressed.charAt(position.value));
                position.value++;
            }

            //if it is not a lowercase letter, it may be a number of repetitions
            var repetition = new StringBuilder("0");
            while (Character.isDigit(compressed.charAt(position.value))) {
                repetition.append(compressed.charAt(position.value));
                position.value++;
            }
            // if it is neither a lowercase letter, nor a number, it can only be a repetition start.
            if (compressed.charAt(position.value) == '[') {
                position.value++;
                var internal = decompress(compressed, position);
                var repeated = repeat(internal, Integer.parseInt(repetition.toString()));
                result.append(repeated);
                position.value++;
            }
        }
        return result.toString();
    }

    public StringBuilder repeat(String s, int times) {
        var builder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            builder.append(s);
        }
        return builder;
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            Decompress decompress = new Decompress();
            System.out.println(decompress.decompress("1["+args[i]+"]"));
        }
    }

}