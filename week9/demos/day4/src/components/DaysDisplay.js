import { useState, useEffect, useReducer } from "react";

export default function DaysDisplay({ days }) {
  // we can supply aliases through array desctructuring instead of using [] notation
  const [first] = days;

  const [currentDay, updateCurrentDay] = useState(first); // the parameter is the initial state
  // const [stateVariable, functionToUpdateStateVariable] = useState(initialValue)
  // const currentDay = useState(first);

  const [isMorning, toggleIsMorning] = useReducer(
    (isMorning) => !isMorning,
    true
  );

  useEffect(() => {
    console.log(currentDay);
  });

  useEffect(() => {
    console.log(isMorning);
  }, [isMorning]);

  // = = -> ! =
  // = = = -> ! = =

  return (
    <>
      <h3>Days I spend working</h3>

      <ul>
        {days.map((day) => (
          <li key={day.id}>
            {day.name}
            <button onClick={() => updateCurrentDay(day)}>Today</button>
          </li>
        ))}
      </ul>

      <p>
        It is {currentDay.name} {isMorning ? "Morning" : "Afternoon"}
      </p>

      <button onClick={() => toggleIsMorning()}>Toggle Time of Day</button>
    </>
  );
}
