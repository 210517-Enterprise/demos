import "./App.css";
import GithubDisplay from "./components/GithubDisplay";
import Heading from "./components/Heading";

const world = "World";

function App() {
  return (
    <div className="App">
      <Heading name={world} location="Typescript" />
      <GithubDisplay login="bpinkerton" />
    </div>
  );
}

export default App;
