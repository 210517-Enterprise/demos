import "./App.css";
import React from "react";
import HelloWorld from "./components/HelloWorld";
import DaysDisplay from "./components/DaysDisplay";
import { daysOfWorkWeek } from "./util/daysOfWeek";

const flag = true;

export default function App() {
  return (
    <div className="App">
      <HelloWorld noun="World" location="React" korak={flag} />
      <DaysDisplay days={daysOfWorkWeek} />
    </div>
  );
}
