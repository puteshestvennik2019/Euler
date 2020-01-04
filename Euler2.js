function fiboEvenSum(n) {
  
  // generate an array of Fib numbers for n
  let fib = [1,2]
  for (let i = 2; i < n; i++) {
    fib[i] = fib[i-1] + fib[i-2];
  }

  // sum even-valued terms -> even values appear in the array every 3rd index, as a result of addition of two odd numbers
  let res = 0
  for (let i = 1; i < n; i+=3) {
    res += fib[i];
  }
  
  return res
  
}

fiboEvenSum(10);
