import "./App.css";
import React from "react";
import DaysDisplay from "./components/DaysDisplay";
import { daysOfWorkWeek } from "./util/daysOfWeek";

export default function App() {
  return (
    <div className="App">
      <DaysDisplay days={daysOfWorkWeek} />
    </div>
  );
}
