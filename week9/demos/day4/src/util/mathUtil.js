export function findSum(...numbers) {
  let sum = 0;
  numbers.forEach((x) => (sum += x));
  return sum;
}
