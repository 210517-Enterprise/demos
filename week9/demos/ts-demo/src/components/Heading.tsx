import React from "react";

interface Props {
  name: string;
  location: String;
}

export default function Heading({ name, location }: Props) {
  return (
    <h1>
      Hello {name} from {location}!
    </h1>
  );
}
