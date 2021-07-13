export default function DaysDisplay(props) {
  return (
    <>
      <h3>Days I spend working</h3>

      <ul>
        {props.days.map((day) => (
          <li key={day.id}>{day.name}</li>
        ))}
      </ul>
    </>
  );
}
