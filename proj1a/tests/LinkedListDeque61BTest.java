import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDeque61BTest {
    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();
        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();
        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
        /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
           to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
           but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     * *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(2); // [-2, -1, 0, 1, 2]
        assertThat(lld1.toList()).containsExactly(2, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(2, 3).inOrder();
        assertThat(lld1.removeFirst()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(3).inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld1.removeLast()).isEqualTo(3);
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
        assertThat(lld1.removeLast()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void removeFirstLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.removeLast()).isNull();
        assertThat(lld1.removeFirst()).isNull();
    }

    @Test
    public void sizeWithTests() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        for (int i = 0; i < 10; i++) {
            lld1.addLast((i));
        }
        for (int i = 0; i < 9; i++) {
            lld1.removeFirst();
        }
        assertThat(lld1.size()).isEqualTo(1);
    }

    @Test
    public void isEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void emptyAfterAddFirst() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("hello");
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void emptyAfterAddLast() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("hello");
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void sizeIsZero() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void sizeAddsAndCorrect() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("hello");
        lld1.addLast("world");
        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void sizeAddsRemovesCorrect() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("hello");
        lld1.removeLast();
        lld1.addLast("world");
        lld1.removeFirst();
        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void getIndexWithCorrect() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("first");
        lld1.addLast("second");
        lld1.addLast("third");
        assertThat(lld1.get(0)).isEqualTo("first");
        assertThat(lld1.get(1)).isEqualTo("second");
        assertThat(lld1.get(2)).isEqualTo("third");
    }

    @Test
    public void getIndexAndNull() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        assertThat(lld1.get(28723)).isNull();
        assertThat(lld1.get(-1)).isNull();
    }

    @Test
    public void emptyLldNull() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.get(0)).isNull();
    }

    @Test
    public void getRecursiveIndexCorrect() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("first");
        lld1.addLast("second");
        lld1.addLast("third");
        assertThat(lld1.getRecursive(0)).isEqualTo("first");
        assertThat(lld1.getRecursive(1)).isEqualTo("second");
        assertThat(lld1.getRecursive(2)).isEqualTo("third");
    }

    @Test
    public void getRecursiveIndexNull() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("only");
        assertThat(lld1.getRecursive(8)).isNull();
        assertThat(lld1.getRecursive(-1)).isNull();
    }

    @Test
    public void emptyRecursiveNull() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.getRecursive(0)).isNull();
    }
}
