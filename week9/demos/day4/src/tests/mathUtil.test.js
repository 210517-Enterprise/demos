import { findSum } from "../util/mathUtil";

describe("Testing functionality of mathUtil file", () => {
  it("Takes in two numbers and returns the sum", () => {
    expect(findSum(1, 2)).toBe(3);
    expect(findSum(4, 6)).toBe(10);
  });

  it("Takes in three numbers and returns the sum", () => {
    expect(findSum(1, 2, 3)).toBe(6);
  });
});
