export default function DaysDisplay(props) {
  return (
    <>
      <h3>Days I spend working</h3>

      <ul>
        {props.days.map((day) => (
          <li>{day}</li>
        ))}
      </ul>
    </>
  );
}
