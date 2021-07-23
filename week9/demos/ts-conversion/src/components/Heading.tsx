interface Props {
  name: string;
  location: string;
}

export default function Heading({ name, location }: Props) {
  return (
    <h1>
      Hello {name} from {location}!
    </h1>
  );
}
