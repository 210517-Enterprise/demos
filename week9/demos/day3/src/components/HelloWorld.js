export default function HelloWorld({ noun, location, korak }) {
  return (
    <>
      <h1>
        Hello {noun} from {location}!
      </h1>

      {korak ? <p>Yahaha! You found me!</p> : <p>Keep looking, bud.</p>}
    </>
  );
}
