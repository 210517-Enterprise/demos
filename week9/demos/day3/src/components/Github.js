import GithubDisplay from "./GithubDisplay";
import { useState } from "react";
import { useForm } from "react-hook-form";

export default function Github() {
  const { register, handleSubmit } = useForm();
  const onSubmit = (data) => updateLogin(data.username);

  const [login, updateLogin] = useState("");

  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>
          Login
          <input {...register("username")} />
        </label>
        <input type="submit" />
      </form>

      <GithubDisplay login={login} />
    </>
  );
}
