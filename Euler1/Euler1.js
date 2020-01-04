function multiplesOf3and5(number) {
  // a number contains x multiples of 3, where x = floor(number / 3)
  // a numbers contains y multiples of 5, where y = number // 5 ('// = Math.floor in Python)
  // all there is left to do, is to exclude numbers that 'overlap' in both arrays -> overlaps = number // 15
  // sum of multiples = element * Triangular Number Sequence
  // 
  let x = Math.floor((number - 1)/ 3);
  let y = Math.floor((number - 1)/ 5);
  let overlap = Math.floor((number - 1)/ 15);
  return 3 * x * (x + 1) / 2 + 5 * y * (y + 1) / 2 - 15 * overlap * (overlap + 1) / 2;
}

multiplesOf3and5(1000);
