function primeNumbers(number) {
	// Sieve Of Eratosthenes
	let primesBools = new Array;
	for (let i = 2; i <= number + 1; i++) {
		primesBools[i] = true;
	}
	for (let p = 2; p * p <= number; p++) {
		if (primesBools[p]) {
			for (let i = p * 2; i <= number; i += p) {  // skip the prime (i = p * 2) and sieve off all its multiples in the array (i += p) => arr[i] = false;
				primesBools[i] = false;
			}
		}
	}

	let primes = [];
	for (let i = 2; i <= number; i++) {
		if (primesBools[i]) {
			primes.push(i);
		}
	}

	return primes;
}


function isPrime(num) {
	// if any prime whose square does not exceed n divides it without a remainder, then n is not prime.
	let primes = primeNumbers(Math.floor(Math.sqrt(num)));
	for (let p in primes) {
		if (num % primes[p] == 0) {
			return false;
		}
	}
	return true;
}		


function findGreatestPrimeFactor(num) {
    if (isPrime(num)) {
        return num;
    }

    let gpf = 1;
    for (let i = 2; i * i <= num; i++) {
        if (num % i != 0) {
            continue;
        } else if (isPrime((num / i))) {
            gpf = num / i;
            break;
        } else if (isPrime(i)) {
            gpf = i;
        }
    }
    return gpf;
}