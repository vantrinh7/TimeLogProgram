
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Created by peter on 2/7/17.
 */
public class BinaryHeapBasicTest {

	// Create binary heap variables to test the methods
	private static BinaryHeap<Integer> emptyBH;
	private static BinaryHeap<Integer> filledBH;
	private static BinaryHeap<Integer> filledBH2;

	@BeforeClass
	public static void init() {

		// Create filled and empty arrays for corresponding heaps
		Integer[] intArray = { 1, 10, 5, 2, 6, 3, 7, 9, 4, 8 };
		Integer[] intArray2 = { 1, 10, 5, 2, 6, 3, 7, 9, 4, 8 };
		Integer[] emptyArray = {};
		emptyBH = new BinaryHeap<Integer>(10, emptyArray);
		filledBH = new BinaryHeap<Integer>(intArray);
		filledBH2 = new BinaryHeap<Integer>(intArray2);
	}

	@AfterClass
	public static void tearDown() {
		emptyBH = null;
		filledBH = null;
		filledBH2 = null;
	}

	@Test
	public void testConstructor() {

		// Case 1: Test the constructor that takes in an array
		Integer[] intArray = { 1 };
		BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>(intArray);
		assertEquals("First element should be 1", 1, testHeap.getElement(0));
		assertEquals("Second element should be null", null, testHeap.getElement(1));

		// Case 2: Test constructor that takes in capacity and an array
		BinaryHeap<Integer> testHeap2 = new BinaryHeap<Integer>(5, intArray);
		assertEquals("First element should be 1", 1, testHeap2.getElement(0));
		assertEquals("Second element should be null", null, testHeap2.getElement(1));
		assertEquals("Capacity should be 5", 5, testHeap2.capacity());
	}

	@Test
	public void leftChildTest() {

		// Expect to get index 1 as the left child of index 0
		assertEquals("leftChild test", 1, filledBH.leftChild(0));
	}

	@Test
	public void rightChildTest() {

		// Expect to get index 2 as the right child of index 0
		assertEquals("empty size test", 2, filledBH.rightChild(0));
	}

	@Test
	public void parentTest() {

		// Expect to get index 3 as the right child of index 8
		assertEquals("empty size test", 3, filledBH.parent(8));
	}

	@Test
	public void getElementTest() {

		// Expect to get value 3 at index 5
		assertEquals("getElement  test", 3, filledBH.getElement(5));
	}

	@Test
	public void setElementTest() {

		// Fifth element is now 6, set it to 100
		filledBH2.setElement(5, 100);

		// Expect 100 at the fifth element
		assertEquals("getElement  test", 100, filledBH2.getElement(5));
	}

	@Test
	public void swapTest() {

		// Swap index 0 and index 1
		filledBH2.swap(0, 1);

		// Index 0 has value 1 and index 1 has value 10, we expect that swapping
		// would result in index 0 having value 10 and index 1 having value 1
		assertEquals("swap test", 10, filledBH2.getElement(0));
		assertEquals("swap test", 1, filledBH2.getElement(1));
	}

	@Test
	public void maxHeapifyTest() {

		// Revert back to the original heap after the swap test
		filledBH.swap(0, 1);

		// If we draw out the heap in tree form, notice that index 4 has value 6, while
		// its left child, index 9, has value 8. Thus, calling maxHeapify on index 4
		// will result in swapping values between it and its child. Expect that index 4
		// now has value 8, and index 9 now has value 6
		filledBH.maxHeapify(4);
		assertEquals("maxHeap test1", 8, filledBH.getElement(4));
		assertEquals("maxHeap test2", 6, filledBH.getElement(9));
	}

	@Test
	public void buildMaxHeap() {

		// Draw out the tree and track the algorithm, we can see our expected array
		// after building max heap indexing from 0 to 9 is as followed:
		// 10-9-7-4-8-3-5-2-1-6. Now we test each element in the heap
		filledBH.buildMaxHeap();
		assertEquals("buildMaxHeap test", 10, filledBH.getElement(0));
		assertEquals("buildMaxHeap test", 9, filledBH.getElement(1));
		assertEquals("buildMaxHeap test", 7, filledBH.getElement(2));
		assertEquals("buildMaxHeap test", 4, filledBH.getElement(3));
		assertEquals("buildMaxHeap test", 8, filledBH.getElement(4));
		assertEquals("buildMaxHeap test", 3, filledBH.getElement(5));
		assertEquals("buildMaxHeap test", 5, filledBH.getElement(6));
		assertEquals("buildMaxHeap test", 2, filledBH.getElement(7));
		assertEquals("buildMaxHeap test", 1, filledBH.getElement(8));
		assertEquals("buildMaxHeap test", 6, filledBH.getElement(9));
	}

	@Test
	public void isEmptyTest() {

		// We expect the empty heap is empty, and the filled heap is not
		assertEquals("isEmpty Test", true, emptyBH.isEmpty());
		assertEquals("isEmpty Test", false, filledBH.isEmpty());
	}

	@Test
	public void capacityTest() {

		// We expect the capacity to be 10, which is what was given
		assertEquals("empty size test", 10, emptyBH.capacity());
		assertEquals("empty size test", 10, filledBH.capacity());
	}

	@Test
	public void sizeTest() {

		// We expect empty heap has size 0, and filled heap has size 10
		assertEquals("empty size test", 0, emptyBH.size());
		assertEquals("empty size test", 10, filledBH.size());
	}

	@Test
	public void setSize() {

		// We expect size of heaps to be set the desired amount
		filledBH.setSize(filledBH.size() - 1);
		emptyBH.setSize(5);
		assertEquals("set size test", 9, filledBH.size());
		assertEquals("set size test", 5, emptyBH.size());
	}
}
