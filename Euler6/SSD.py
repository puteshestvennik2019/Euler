"""
// This problem is not difficult, but requires a bit of 'mathematical recursion' (Pascal's triangle - based)
// The sum of squares is represented in the pyramid with a square base, but let's start with 2-d first:
// each level of 2-dimensional pyramid holds one less element than the level right above it: 1 -> 2 - > 3 etc.
//    X
//   X X
//  X X X
// ...
// On adding the third dimension/depth, we get a pyramid of squares (pyramid numbers or square pyramidal number): 1 -> 4 -> 9 etc. 
// So far, so good.
// Each level (i.e. squared number) in this pyramid represented as the sum of two consecutive triangular numbers (n and n + 1 on the left-justified Pascal's triangle): 
// 4 = 3 + 1
// 9 = 6 + 3
// 16 = 10 + 6
// and so on
// Therefore the sum of n consecutive squares equals the sum of n pairs of two consecutive relevant triangular numbers:
// (tiangular numbers with corresponding indexes, non-zero-based: [1]: 1, [2]: 4, [3]: 10, [4]: 20, [5]: 35, [6]: 56, [7]: 84...)
// 1 + 4 + 9 + 16 = 1 (base case) + (1 + 3) + (3 + 6) + (6 + 10) = 30
// in turn, the sum of k triangular elements equals the k-th tetrahedral number => 
// (tetrahedral numbers with corresponding indexes, non-zero-based: [1]: 1, [2]: 3, [3]: 6, [4]: 10, [5]: 15, [6]: 21, [7]: 28...)
// => (the sum of k - 1 triangular elements) + (the sum of k triangular elements) = (k-1)-th + k-th tetrahedral numbers (with k-th number being "k plus two choose three"):
// 1^2 + 2^2 + 3^2 + ... (n-1)^2 + n^2 = 1 + (1 + 3) + ... + (n * (n + 1) / 2 + (n + 1) * ((n + 1) + 1) / 2) = "("n plus one choose three") + ("n plus two choose three")" = 
// = (n - 1) * n * (n + 1) / 3!    +    n * (n + 1) * (n + 2) / 3!   =   (2n^3 + 3n^2 + n) / 6
"""

def SSD(n):
	return int(abs(pow(n * (n + 1) / 2, 2) - (2*n*n*n + 3*n*n + n) / 6))