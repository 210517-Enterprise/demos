import PokeDisplay from "./PokeDisplay";
import { useState } from "react";
import React from "react";

//to provide a search bar for users to query the api and display a pokemon
export default function Pokemon() {
  const [input, updateInput] = useState(null);
  const inputRef = React.createRef();
  return (
    <>
      <input
        id="text-bar"
        type="text"
        placeholder="Enter a pokemon name"
        ref={inputRef}
      ></input>
      <button onClick={() => updateInput(inputRef.current.value)}>
        Submit
      </button>
      <PokeDisplay name={input} />
    </>
  );
}
