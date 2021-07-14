import "./App.css";
import React from "react";
import AppRouter from "./components/AppRouter";
import AppMenu from "./components/AppMenu";
import "bootstrap/dist/css/bootstrap.min.css";
// import Github from "./components/Github";
// import Pokemon from "./components/Pokemon";
// import DaysDisplay from "./components/DaysDisplay";
// import { daysOfWorkWeek } from "./util/daysOfWeek";

export default function App() {
  return (
    <div className="App">
      <AppMenu />
      <AppRouter />
    </div>
  );
}
