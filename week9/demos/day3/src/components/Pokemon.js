import PokeDisplay from "./PokeDisplay";
import { useState } from "react";
import { useForm } from "react-hook-form";
import React from "react";

//to provide a search bar for users to query the api and display a pokemon
export default function Pokemon() {
  const { register, handleSubmit } = useForm();
  const onSubmit = (data) => updatePokemonName(data.pokemonName);

  const [pokemonName, updatePokemonName] = useState("");

  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>
          Pokemon Name
          <input {...register("pokemonName", { required: true })} />
        </label>
        <input type="submit" />
      </form>
      <PokeDisplay name={pokemonName} />
    </>
  );
}
