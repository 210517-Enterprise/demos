import React from "react";
import Heading from "./components/Heading";
import "./App.css";
import GithubDisplay from "./components/GithubDisplay";

function App() {
  return (
    <div className="App">
      <Heading name={"world"} location="Typescript" />
      <GithubDisplay username="bpinkerton" />
    </div>
  );
}

export default App;
