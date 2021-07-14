import { useState, useEffect } from "react";

export default function PokeDisplay({ name }) {
  const [data, updateData] = useState(null);

  useEffect(() => {
    fetch(`https://pokeapi.co/api/v2/pokemon/${name}`)
      .then((response) => response.json())
      .then(updateData);
  }, [name]);

  return data === null || data.sprites === undefined ? (
    <p>[Pokemon Goes Here]</p>
  ) : (
    <>
      <h1>{data.name}</h1>
      <img alt={name} src={data.sprites.front_default}></img>
      <h2>Abilities</h2>
      <ul>
        {data.abilities.map((ability) => {
          return <li key={ability.ability.name}>{ability.ability.name}</li>;
        })}
      </ul>
    </>
  );
}
