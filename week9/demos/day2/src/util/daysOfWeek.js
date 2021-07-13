const weekDays = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];

export const daysOfWorkWeek = weekDays.map((day, i) => ({
  id: i,
  name: day,
}));
