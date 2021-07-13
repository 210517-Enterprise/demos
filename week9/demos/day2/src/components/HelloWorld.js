export default function HelloWorld(props) {
  return (
    <>
      <h1>
        Hello {props.noun} from {props.location}!
      </h1>

      {props.korak ? <p>Yahaha! You found me!</p> : <p>Keep looking, bud.</p>}
    </>
  );
}
