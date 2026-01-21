# Lab 2: BST Structural Transformation via Rotations
## CS 302 – Advanced Data Structures and File Processing

## Project Overview
This project implements an algorithm to transform a source Binary Search Tree ($T_1$) into a target Binary Search Tree ($T_2$) using **only tree rotations**. Both trees contain the exact same set of keys, but their initial structures differ. By the end of the algorithm, $T_1$ is structurally identical to $T_2$.

## The Algorithm: Top-Down Structural Matching
The implementation utilizes a **Top-Down Iterative Approach**. Instead of rebuilding the tree, we use $T_2$ as a "blueprint" and "mold" $T_1$ level-by-level.

### Key Strategy:
1.  **Level-Order Traversal:** A `Queue` is used to traverse $T_2$. This ensures we fix the root first, then the children, then the grandchildren.
2.  **Locate & Elevate:** For every node $V$ pulled from the $T_2$ queue, we locate the corresponding node in $T_1$.
3.  **Iterative Rotations:** We perform rotations on the node's current parent in $T_1$ until the node reaches the same position (same parent relationship) it occupies in $T_2$.
    * If the node is a **left child**, we perform `rotateR` on its current parent.
    * If the node is a **right child**, we perform `rotateL` on its current parent.
4.  **Structural Integrity:** Because we process the tree level-by-level, once a parent is fixed, the rotations performed on its children do not change the parent's position, preserving the work done in previous iterations.



---

## Technical Constraints & Implementation
* **Iterative Requirement:** The solution is strictly iterative, avoiding recursion to remain efficient and comply with the lab's specific constraints.
* **Rotation-Only:** No nodes are deleted or created. The transformation is achieved purely through pointer manipulation via the provided `rotateL` and `rotateR` functions.
* **Parent Tracking:** The algorithm effectively manages parent-child relationships, ensuring that as a node "bubbles up," the grandparent pointers are correctly updated to maintain tree connectivity.



---

## Files
* **`BST.java`**: Contains the core logic. The `problem(BST T2)` function houses the transformation algorithm.
* **`Lab2.java`**: The test driver. It generates random BSTs using a fixed seed and verifies that $T_1$ matches $T_2$ in both structure and content.

---

## How to Run
1.  Ensure `Lab2.java` and `BST.java` are in the same directory.
2.  Compile the files:
    ```bash
    javac Lab2.java
    ```
3.  Run the test suite:
    ```bash
    java Lab2
    ```
    The program will output the results of the randomly generated test cases. Note that the seed is fixed to ensure consistent testing.

---

## Complexity Analysis
* **Time Complexity:** $O(n^2)$ in the absolute worst-case (e.g., converting a strictly left-leaning tree to a strictly right-leaning tree), though average performance for typical BST structures is significantly faster.
* **Space Complexity:** $O(n)$ for the `Queue` used in the level-order traversal of $T_2$.
