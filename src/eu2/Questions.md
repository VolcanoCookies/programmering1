### Time complexity when comparing elements
Worst-case: O(n^2)
Best-case: O(n^2)
Average: O(n^2)

### Time complexity when swapping elements
Worst-case: O(n^2)
Best-case: O(1)
Average: O(n^2)

### Difference to selection time compexity
Number of comparisons is equal, O(n^2) is all scenarios
Number of swaps is:
Worst-case: O(n)
Best-case: O(1)


## Prove algorithm

Let length be n.

After the first iteration, the algorithm has traversed the whole array.
Thus ensuring the first element is the lowest in the whole array.
Then the algorithm traverses the whole array except for the first element,
thus ensuring the second element is the lowest element in the second lowest in the whole
array, this because the first element is already the shortest.
This continues where the algorithm iterates over the whole array starting from xi to xn and going to the end,
all elements before xi are the i lowest elements in the array sorted from lowest to highest.
