//Q1
const q1Array = [
  "blue",         // color
  2,             // pets
  true           // experience
];

// show
console.log('Q1 - Array:', q1Array);
console.log('Elements [0]:', q1Array[0], '- Type:', typeof q1Array[0]);
console.log('Elements [1]:', q1Array[1], '- Type:', typeof q1Array[1]);
console.log('Elements [2]:', q1Array[2], '- Type:', typeof q1Array[2]);

console.log('\n----------------------------\n');

// Q2:
let sum = 0;
let numbers = [];


let start = Math.ceil(18 / 7) * 7;  // 21
let end = Math.floor(534 / 7) * 7;   // 532

for (let i = start; i <= end; i += 7) {
  sum += i;
  numbers.push(i);
}

// show
console.log('Q2 - Calculate sum of numbers divisible by 7 between 18 and 534');
console.log('Numbers:', numbers.join(' + '));
console.log('Sum:', sum);
console.log('Total count:', numbers.length);