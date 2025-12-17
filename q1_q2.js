// Q1: (4 points)
// Create an array containing the following 3 elements:
// - your favorite color
// - the number of pets you have
// - a boolean value describing whether you have previous programming experience

console.log("=== Q1: Array Creation ===");

const myArray = [
    "Blue",      // favorite color
    2,           // number of pets
    true         // has previous programming experience
];

console.log("My Array:", myArray);
console.log("Favorite Color:", myArray[0]);
console.log("Number of Pets:", myArray[1]);
console.log("Previous Programming Experience:", myArray[2]);

console.log("\n");

// Q2: (4 points)
// Write a script code to calculate and display the sum of all the numbers 
// divisible by 7 between 18 and 534
// Example: 21+28+35+...+525+532

console.log("=== Q2: Sum of Numbers Divisible by 7 ===");

let sum = 0;
let numbers = [];

// Find all numbers divisible by 7 between 18 and 534
for (let i = 18; i <= 534; i++) {
    if (i % 7 === 0) {
        sum += i;
        numbers.push(i);
    }
}

console.log("Numbers divisible by 7 between 18 and 534:");
console.log(numbers.join(" + "));
console.log("\nSum:", sum);
console.log("Count of numbers:", numbers.length);
